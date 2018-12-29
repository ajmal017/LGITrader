/*La idea es automatizar este modelo sobre ESZ4, actual vencimiento diciembre del futuro SP500, este campo Symbol cambiar� cada trimestre igual que en el anterior desarrollo.

- Se debe capturar el tiempo real tick desde las 15:00 hora Espa�a hasta el cierre a 22:00
  en per�odo temporal 5 minutos, donde la primera captura ser� el precio que refleje a las 15:00:00,  el segundo 15:05:00, tercero 15:10:00 y sucesivos.

- A estas capturas de 5 minutos le aplicamos una media m�vil simple de 8 per�odos.

- Cuando al cierre de una barra el precio rompa por encima de la mediamovil de 8 per�odos y se sit�e por encima en el 75% del rango de la misma, se genera una orden de compra de 1 Futuro a mercado "Market". Y a al inversa cuando la barra perfore la media, vender�.

- El stop se ejecuta cuando una barra cierre en su 100% por debajo de la media movil.

- Cuando alcance 10 puntos de beneficio se ejecuta stop profit, aqu� genera un campo donde el trader pueda modificar esta variable.

- Por ejemplo si se combra a 1980 y se vende a 1990, en este precio har�a 2 contratos, 1 para cerrar la compra en 80 y otro abre un corto en 90.

- Es posible que debamos limitar el n�mero de operaciones que realice en el d�a, tenlo en cuenta para crear un contador.

- No necesitamos base de datos anteriores.

1- Si la barra no cumple los 2 criterios, no compra, eso lo tenemos claro.
 A/ La barra debe cruzar la MM8 y al cierre el 75% de su cuerpo debe ser superior al precio cierre de la MM.
y B/ Adem�s, el precio cierre de la barra ser� => que el 75% del rango.

2- La particularidad de este caso afecta a la primera sentencia, el cierre de la barra se sit�a en su 100% por encima de la MM, con lo cual debe comprar, el filtro B aqu� no afecta.
*/

package com.ibtrader.strategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.MobileAvgUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategySimpleMobileAverage extends StrategyImpl {

	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategySimpleMobileAverage.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, String> Parameters = new HashMap<String,String>();
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER = "Mobile Average Periods Number {8}";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE = "Mobile Average Candle Size (Minutes) {5}";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_GAP_SIZE = "Mobile Average Percentual Gap Size {75}"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET = "Mobile Average Trade Until x Minutes From CloseMarket"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET = "OffSet From Open Market (Minutes) To Start Trading";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE = "Operation Type [ALL, BUY, SELL]";  // offset desde inicio de mercado en minutos
	
	long _num_macdP = 8;   // Periodos
	long  _num_macdT = 5;   // Tiempo de barras
	
	double _entryrate=75.0;    // Umbral de superacion, en porcentaje
	String operationfilter="";    // ALL, BUY, SELL
	
	
	boolean bBuyOperation = Boolean.FALSE;									
	boolean bSellOperation = Boolean.FALSE;
	JSONObject _tradeDescription;// // acumular la traza de los valores introducidos
	
	SimpleDateFormat TimeFormat = new SimpleDateFormat (Utilities.__IBTRADER_SHORT_HOUR_FORMAT);
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
		boolean verified = Boolean.FALSE;
		boolean existsPosition = Boolean.FALSE;

		try
	    {
			
		if (_strategyImpl.getStrategyparamsoverride()==null)
			return Boolean.FALSE;
			
	    this.setJsonStrategyShareParams(JSONFactoryUtil.createJSONObject(_strategyImpl.getStrategyparamsoverride()));					

	    /* SOLO PODEMOS ENTRAR EN EL PERIODO MARCADO DE CADA MINUTO, PARA LO CUAL OBTENEMOS EL RESTO */	
		User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());

		/* TIMEZONE AJUSTADO */
		Date _FromNow =  !isSimulation_mode() ?   Utilities.getDate(_IBUser) : backtestingdDate;
		Calendar _calendarFromNow = Calendar.getInstance();
		_calendarFromNow.setTime(_FromNow);		
		_calendarFromNow.set(Calendar.SECOND, 0);
		_calendarFromNow.set(Calendar.MILLISECOND, 0);
		
		long _ModMinuteToEntry = _calendarFromNow.get(Calendar.MINUTE) % _num_macdT;
		if (_ModMinuteToEntry!=0)  // NO ESTOY EN EL MINUTO 5,10,15,20..etc (para las barras de 5)
			return Boolean.FALSE;
			
	
		String HoraActual = "";	
		Calendar calFechaActualWithDeadLine;
		Calendar calFechaFinMercado;
		
		if (!isSimulation_mode())
		{
			HoraActual = Utilities.getHourNowFormat(_IBUser);
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(HoraActual);
			calFechaFinMercado = Utilities.getNewCalendarWithHour(_market.getEnd_hour());
		}
		else	
		{
			HoraActual = Utilities.getHourNowFormat(_IBUser,backtestingdDate);
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(backtestingdDate, HoraActual);
			calFechaFinMercado = Utilities.getNewCalendarWithHour(backtestingdDate, _market.getEnd_hour());			
		}		
		
//		StrategyShare _strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),_strategyImpl.getStrategyId());
									
		calFechaActualWithDeadLine.add(Calendar.MINUTE, this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET));
		existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),
				Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId()), Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);		/*  CONTROLAMOS EL DEADLINE PARA COMPRAR */ 
		// maximos y minimos...ya lo tenemos de la tabla.
		//RealTime oShareMixMaxRTime = RealTimeDAO.getMinMaxRealTime(ShareStrategy.getShareId().intValue());

		// verificamos compra previa. No compramos si hay una compra previa y estamos en el margen de tiempo de compra.
		// verificamos si ha pasado el tiempo necesario con los offset de lectura 		
		// 00
		//_log.info("existsPosition:" +  existsPosition);
		
		_num_macdP = this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER,0);
		_num_macdT = this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,0);
		_entryrate = this.getJsonStrategyShareParams().getDouble(_EXPANDO_MOBILE_AVERAGE_GAP_SIZE,0.0f) / 100;
		operationfilter = this.getJsonStrategyShareParams().getString(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,"ALL").trim();
		
		if (_num_macdP==0 || _num_macdT==0 || _entryrate==0)
			return Boolean.FALSE;
		
		
		
		if (!existsPosition &&  !calFechaActualWithDeadLine.after(calFechaFinMercado))		
		{	
			// supuestamente estamos leyendo...verificamos si con respecto al mercado ya tenemos los max y min
			// comparamos que la hora de lectura final haya sobrepasado el actual 
			// HHMM
			// HORA DE FIN DE CALCULO DE MAX Y MINIMOS.		
			String StartHourTrading = "";
			if (!isSimulation_mode())
				 StartHourTrading =  Utilities.getActualHourFormatPlusMinutes(_calendarFromNow, _market.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));
			else				
				 StartHourTrading = Utilities.getActualHourFormatPlusMinutes(_market.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));			// COMPROBAMOS ALGUN TIPO DE ERROR 
			if (StartHourTrading.contains("-1"))
			{
				_log.info("[ Errores formateando las horas de StarHourTrading de la accion. Hora[" + _market.getStart_hour()  + "], Offset1[" + this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET)+ "]");
				return Boolean.FALSE;
			}
				
			
			if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa
			{
			
			// ya no obtenemos el maximo y minimo, sino el correspondiente al tramo que me han dicho
			
			
			
			Double _avgMobileSimple = MobileAvgUtil.getSimpleAvgMobile(_calendarFromNow.getTime(), _num_macdT, _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_macdP, isSimulation_mode(), _market);
			
			if (_log.isDebugEnabled())
				_log.debug("_avgMobileSimple for :" + _share.getSymbol() + ":" +  _avgMobileSimple.doubleValue() + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));
			
			if (_avgMobileSimple!=null)
			{
				// todo fue bien, tenemos media movil y de los periodos solicitados.
				// buscamos el cierre de la barra, ultimo valor < que el MINUTE.00  (15.00, 20,00, 25.00)
				Double vRealtime = null;
				if (!isSimulation_mode())
				{
					Realtime oRTimeEnTramo =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _calendarFromNow.getTime());
					vRealtime  = Validator.isNull(oRTimeEnTramo) ? null : oRTimeEnTramo.getValue();
				}
				else
				{
					HistoricalRealtime oRTimeEnTramo =  HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _calendarFromNow.getTime());
					vRealtime  = Validator.isNull(oRTimeEnTramo) ? null : oRTimeEnTramo.getValue();
				}
				if (_log.isDebugEnabled())
					_log.debug("oRTimeEnTramo for :" + _share.getSymbol() + ":" +  vRealtime);
				
				if (Validator.isNotNull(vRealtime))
				{
					/* PERIODO CERO , JUSTAMENTE LA BARRA ANTERIOR */
					double max_value = 0;
					double min_value = 0;
					double _WidthRangeBar = 0;
							
					if (!isSimulation_mode())
					{
						Realtime oRTimeWidthRange = MobileAvgUtil.getMinMaxBarFromShare(_calendarFromNow,  _num_macdT, 0,_share.getShareId(), _share.getCompanyId(), _share.getGroupId()) ;
						max_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMax_value()>0 ? oRTimeWidthRange.getMax_value() : 0; 
						min_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMin_value()>0 ? oRTimeWidthRange.getMin_value() : 0;
					}
					else
					{
						HistoricalRealtime oRTimeWidthRange = MobileAvgUtil.getHistoricalMinMaxBarFromShare(_calendarFromNow,  _num_macdT, 0,_share.getShareId(), _share.getCompanyId(), _share.getGroupId()) ;
						max_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMax_value()>0 ? oRTimeWidthRange.getMax_value() : 0; 
						min_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMin_value()>0 ? oRTimeWidthRange.getMin_value() : 0;
					}
					
					if (max_value >0  && min_value>0)
					{
						/* RAGO DE LA barra */
						 _WidthRangeBar = max_value - min_value; 
						/* VARIABLE PARA CONTROLAR QUE SI LA BARRA NO CORTE LA MM, NOS ASEGUREMOS QUE LA
						 * BARRA N-1 SI LA CORTE 
						 */
							
						if (_log.isDebugEnabled())
							_log.debug("_WidthRangeBar for :" + _share.getSymbol() + ":MAX" + max_value + ",MIN:" + min_value );
						
						boolean _AvgMovil_InsideBar  = (max_value > _avgMobileSimple.doubleValue()  &&
								min_value< _avgMobileSimple.doubleValue());
						
						
						if (_log.isDebugEnabled())
							_log.debug("_AvgMovil_InsideBar for :" + _share.getSymbol() + _AvgMovil_InsideBar);
						
						boolean _BuySuccess = false;
						boolean _SellSuccess = false;
						
						// que no entre si es la primera posicion y viene de una mecanismo de continuidad de la se�al anterior

						
						double _WidthBarRangePercent =  _WidthRangeBar *  _entryrate;

						/* A/ La barra debe cruzar la MM8 y al cierre el 75% de su cuerpo debe ser superior al precio cierre de la MM.
						y B/ Adem�s, el precio cierre de la barra ser� => que el 75% del rango.*/
						
						/* 2- La particularidad de este caso afecta a la primera sentencia, el cierre de la barra se sit�a en su 100% por encima de la MM, con lo cual debe comprar, el filtro B aqu� no afecta.
						 * */
						_BuySuccess = MobileAvgUtil._IsBuySignalMM8_5MINBar(_avgMobileSimple, max_value,min_value, _WidthBarRangePercent, vRealtime);
						_SellSuccess = MobileAvgUtil._IsSellSignalMM8_5MINBar(_avgMobileSimple, max_value,min_value, _WidthBarRangePercent, vRealtime);
						/* tipos de operacion */
						_BuySuccess = _BuySuccess &&  
								(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.BUY.toString())); 

						
						_SellSuccess = _SellSuccess  &&  
								(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.SELL.toString()));
						
						if (_log.isDebugEnabled())
							_log.debug("_BuySuccess._SellSuccess, _AvgMovil_InsideBar for :" + _share.getSymbol() + _BuySuccess + "," + _SellSuccess+ "," +_AvgMovil_InsideBar );
						
						if (_AvgMovil_InsideBar  && (_BuySuccess || _SellSuccess))
						{
							
						    this.setValueIn(vRealtime.doubleValue());											
							this.setVerified(Boolean.TRUE);												
							verified = true;
							/* OJO, PUEDE SER QUE NO DE SE�AL DE COMPRA Y VENTA SEGUN CONDICIONES,
							 * SI NO QUE SE VERIFICA QUE N NO CORTE LA BARRA LA MM  SI
							 */					
							this.bBuyOperation = _BuySuccess;									
							this.bSellOperation = _SellSuccess;
							
							
							_tradeDescription = JSONFactoryUtil.createJSONObject();
							_tradeDescription.put("_WidthBarRangePercent", _WidthBarRangePercent);
							_tradeDescription.put("_AvgMovil_InsideBar", _AvgMovil_InsideBar);
							_tradeDescription.put("_WidthRangeBar", _WidthRangeBar);
							_tradeDescription.put("oRTimeWidthRange.getMax_value()", max_value);
							_tradeDescription.put("oRTimeWidthRange.getMin_value()", min_value);
							_tradeDescription.put("oRTimeEnTramo", vRealtime.doubleValue());
							_tradeDescription.put("_avgMobileSimple", _avgMobileSimple.doubleValue());
							_tradeDescription.put("_periods:", _num_macdP);
							_tradeDescription.put("_candlesize(minutes):", _num_macdT);
							_tradeDescription.put("_barentryrate", _entryrate);
							_tradeDescription.put("operationfilter", operationfilter);						
							_tradeDescription.put("_calendarFromNow", TimeFormat.format(_calendarFromNow.getTime()));
							
							
							
						}
					}

				}
			}
			   	
		  }
	    }// NO EXISTE POSICION 
	    }
		catch (Exception er)
	    {
			_log.info(er.getMessage());
			this.setVerified(Boolean.FALSE);				
	    }

		return verified;
	}
	@Override
	public  long  execute(Share _share, Market _market,  Date backtestingdDate)
	{
		// TODO Auto-generated method stub
		long returnValue=-1;
		try		
        {
			String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId()); 
			boolean existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),
					_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);			
			if (existsPosition)
				return returnValue;
			_log.info("UserAccount: detectada posible entrada de " + _share.getName() +  "Tick:" + _share.getSymbol() + ",PrecioCompra:" + this.getValueIn());
			_log.info(_tradeDescription);
			// hace falta???????? ..creo que si, para tener control sobre la operacion de compra /venta 
			SimpleDateFormat sdf = new SimpleDateFormat (Utilities._IBTRADER_FUTURE_SHORT_DATE);
			boolean bIsFutureStock = _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS)  && _share.getExpiry_date()!=null;
			String _Expiration = "";
		    if (bIsFutureStock)
				_Expiration = sdf.format(_share.getExpiry_date());		    
		    Contract oContrat = null;
  		    
  		   if (_share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
  		   {
  			  oContrat = new FutContract( _share.getSymbol(), _Expiration);
 			  //oContrat.multiplier(String.valueOf(oShare.getMultiplier()));		    					
			  oContrat.exchange(_share.getExchange());
			  oContrat.currency(_market.getCurrency());
  		   }
		   else		    					
				oContrat = new StkContract( _share.getSymbol());
  		   
  		   /* NECESARIO PRA LANZAR COMPRA DESDE EL CROUTIL */
  		   this.setTargetContract(oContrat);
  		   
			long number_to_purchase = _share.getNumbertopurchase();
			String action = PositionStates.statusTWSFire.BUY.toString();
		    if (bSellOperation)
		    {
			    action = PositionStates.statusTWSFire.SELL.toString();
		    }
			// colocamos operacion de compra
  			if (!isSimulation_mode())
  			{ 
				Order BuyPositionTWS = new Order();
				BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));		
				
				/* EXISTE ALGO SOBREESCRITO */
	    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0)>0)
	    			number_to_purchase =this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0);    	
				
				BuyPositionTWS.totalQuantity(number_to_purchase);
				BuyPositionTWS.orderType(PositionStates.ordertypes.MKT.toString());		    
				// precio del tick más o menos un porcentaje ...normalmente %1
				// ojo con los FUTUROS..llevan cambios porcentuales
				
				/* ****************************************************************
				 * ****************************************************************
				 * SIEMPRE HAY QUE PONERLO AUNQUE VAYA A MERCADO lmtprice & auxprice
				 * ****************************************************************
				 * **************************************************************** */
				
				BuyPositionTWS.lmtPrice(Utilities.RoundPrice(this.getValueLimitIn()));
				BuyPositionTWS.auxPrice(Utilities.RoundPrice(this.getValueLimitIn()));					
				/*  SI ES UNA COMPRA, NOS POSICIONAMOS CORTOS SI BAJA EL MINIMO */		
				BuyPositionTWS.action(PositionStates.statusTWSFire.BUY.toString());			
				if (bSellOperation)
				{
					BuyPositionTWS.action(PositionStates.statusTWSFire.SELL.toString());
				}
				this.setTargetOrder(BuyPositionTWS);	
				
				_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
  			}
			
					
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			Position BuyPositionSystem = PositionLocalServiceUtil.createPosition(CounterLocalServiceUtil.increment(Position.class.getName()));
			BuyPositionSystem.setBacktestingId(ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
			BuyPositionSystem.setDescription("");
			BuyPositionSystem.setPrice_in( this.getValueIn());  // ojo, es estimativo
			BuyPositionSystem.setDate_in(!isSimulation_mode() ? new Date() : backtestingdDate);// .setDate_buy(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			BuyPositionSystem.setShare_number(number_to_purchase);
			BuyPositionSystem.setShareId(_share.getShareId());
		
			/* BuyPositionSystem.setState_in(PositionStates.statusTWSCallBack.PendingSubmit.toString());
			BuyPositionSystem.setState(PositionStates.status.PENDING_BUY.toString()); */
			BuyPositionSystem.setType(action);
			BuyPositionSystem.setCompanyId(_share.getCompanyId());
			BuyPositionSystem.setGroupId(_share.getGroupId());
			BuyPositionSystem.setStrategy_in(this.getClass().getName());
			BuyPositionSystem.setDescription(_tradeDescription.toString());
			
    		/* BuyPositionSystem.setSell_percentual_stop_lost(ShareStrategy.getSell_percentual_stop_lost());
			BuyPositionSystem.setSell_percentual_stop_profit(ShareStrategy.getSell_percentual_stop_profit());*/			
			
			/* MODO FAKE CUENTA DEMO */
			BuyPositionSystem.setPosition_mode(position_mode);			
    		BuyPositionSystem = Utilities.fillStatesOrder(BuyPositionSystem);
			/* END MODO FAKE CUENTA DEMO */
			PositionLocalServiceUtil.updatePosition(BuyPositionSystem);
			/* Posicion en MYSQL de CONTROL */
			_log.info("Opening order " + BuyPositionSystem.getPositionId());
			
			/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
			returnValue =  BuyPositionSystem.getPositionId();

			
        }
		catch (Exception er)
	        {
				_log.info(er.getMessage());
				er.printStackTrace();
			
	        }
		return returnValue;	
	}
	
	
	
	public IBStrategySimpleMobileAverage() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init(long companyId) {
	// TODO Auto-generated method stub
	// CREAMOS LOS EXPANDOS NECESARIOS 
		
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER, String.valueOf(ExpandoColumnConstants.INTEGER));
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,  String.valueOf(ExpandoColumnConstants.INTEGER));	
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_GAP_SIZE,  String.valueOf(ExpandoColumnConstants.DOUBLE));  // ESTE ES EL UNICO DOUBLE	
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,  String.valueOf(ExpandoColumnConstants.STRING_ARRAY));  // ESTE ES EL UNICO DOUBLE
		
	ExpandoTable expandoTable;
	try {
		expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategySimpleMobileAverage.class.getName());
		long i = 0;
		for (Map.Entry<String, String> parameter : Parameters.entrySet()) {
		
			String _paramName = parameter.getKey();
			String _paramValue = parameter.getValue();
			
			/* EXISTE, SI NO , LO CREAMOS */
			ExpandoColumn ExistsExpando = ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), _paramName);
			
			if (ExistsExpando==null)
			{
				ExpandoColumn paramColumn = ExpandoColumnLocalServiceUtil.createExpandoColumn(CounterLocalServiceUtil.increment(ExpandoColumn.class.getName()));
				paramColumn.setName(_paramName);
				paramColumn.setType(Integer.parseInt(_paramValue));	
				paramColumn.setTypeSettings("hidden=0\nindex-type=0\nvisible-with-update-permission=0");
				paramColumn.setCompanyId(companyId);					
				paramColumn.setTableId(expandoTable.getTableId());
				ExpandoColumnLocalServiceUtil.updateExpandoColumn(paramColumn);
				
				ExpandoColumns.add(paramColumn);
			}
			else
			{
				ExpandoColumns.add(ExistsExpando);	
			}
			
		}
		/* method of the interface */
		this.setIBStrategyParams(ExpandoColumns);
		
		
	} catch (PortalException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	/* CUSTOM VALIDATOR OF EXPANDO FIELDS 
	 * strategyshare.strategyminmax.errorparams  =  El formato de los par�metros es err
	// strategyshare.strategyminmax.errorparams2  = El desplazamiento desde inicio de mercado debe ser menor que el fin.
	 * 
	 * */
	@Override
	public boolean validateParams(Map<String, String> paramValues) {
	// TODO Auto-generated method stub
	//return super.validateParams(paramValues);
	this.setValidateParamsKeysError("");
	boolean bOK = Boolean.TRUE;
	for (Map.Entry<String, String> parameter : paramValues.entrySet()) {
		String _paramValue = parameter.getValue();
		
		/* PUEDEN VENIR LISTA DE VALORES EN EL NOMBRE DEL EXPANDO []  */		
		 boolean bParamList = parameter.getKey().contains("[") && parameter.getKey().contains("]");
		if (!bParamList && !Utilities.isNumber(_paramValue))
		{
			bOK=Boolean.FALSE;
			this.setValidateParamsKeysError("strategyshare.strategyminmax.errorparams");
			break;
				
		}	
		
	}
	return bOK;	
		
	}
}	