/* 
 * 
 * 
 * https://www.rankia.com/blog/opiniones/3132668-utilizando-pivot-points-para-entrar-mercado
https://www.mql5.com/es/articles/1465

Pivot Points existen en todos los plazos temporales: cinco minutos, 1 hora, diario, semanal o mensual. 

ESTRATEGIA :

1) Calcular los R1-PP-S1 de la barra Inicial ( BARRA 0 )
2) Considerar la apertura de BARRA 1 ( Nivel 1 )
3) Si Nivel1 > PP ( Abrir CORTOS )
4) Si Nivel1 < PP ( Abrir LARGOS )
5) STOP en los niveles R1 y S1
6) PROFIT en PP


CONDICIONES PREVIAS

Valores Optimizables

a) Considerar siempre como nivel de ENTRADA = Nivel1 + o - ( 5 ) Puntos
b) Distancia desde ENTRADA - PROFIT ( 10 )
c) Distancia desde ENTRADA - (R1 o S1) ( 50 )
d) Riesgo.......Ratio ENTRADA-(R1 o S1) / ENTRADA-PROFIT ( 2.5 )

De esta manera limitamos la operativa a las barras que nos son favorables, auqellas que tienen recorrido hasta el PP, y no tienen mucha distancia hasta el nivel R1 o S1.


P (Pivot Point, o punto de giro)= (M�ximo del periodo anterior+M�nimo del periodo anterior+Cierre) todo dividido entre tres.
Podr�amos pensar que el punto de giro, se deber�a encontrar justo en el centro, pero no es as�. Al incluir el cierre en la ecuaci�n, el punto de giro, puede coincidir o no con la parte central del rango de la barra anterior.
Resistencia 1=2*El Pivot Point-El m�nimo de la cotizaci�n.
Resistencia 2=Pivot Point+(Resistencia primera-El soporte primero)
Soporte 1=2* (Pivot Point-El m�ximo de la cotizaci�n)
Soporte 2= Pivot Point-(Resistencia primera-Soporte primero)
 * 
 * 
 * 
 */



package com.ibtrader.strategy;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
import com.ibtrader.data.model.AuditIndicatorsStrategy;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.AuditIndicatorsStrategyLocalServiceUtil;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;
import com.ibtrader.data.service.persistence.impl.AuditIndicatorsStrategyPersistenceImpl;
import com.ibtrader.techindicadors.IBTraderPivotPointIndicator;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.BaseIndicatorUtil;
import com.ibtrader.util.AroonIndicatorUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.DirectionalMovementADXRUtil;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;


/* https://compraraccionesdebolsa.com/los-sistemas-de-trading/triple-pantalla-Elder/ 
 * http://www.megabolsa.com/2015/08/01/tecnica-de-trading-de-triple-pantalla/*/

public class IBStrategyPivotsPoints extends StrategyImpl {

	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyPivotsPoints.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, String> Parameters = new HashMap<String,String>();
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER = "Exponencial Mobile Average Periods Number {26}";  // offset desde inicio de mercado en minutos	
	private static String _EXPANDO_PIVOTPOINT_CANDLE_SIZE = "PivotPoint Candle Size (Minutes) {5}";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED  = "% Gap To Buy Sell After PIVOT POINT Value {0.3}";  // offset hasta desde inicio de mercado en minutos		
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET = "Mobile Average Trade Until x Minutes From CloseMarket {0}"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET = "OffSet From Open Market (Minutes) To Start Trading {0}";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE = "Operation Type [ALL, BUY, SELL]";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_PIVOTSPOINTS_VOLUME_INCREASED  = "Volume Increased [TRUE, FALSE]";  // offset desde inicio de mercado en minutos

	
	
	long  _num_macdT = 5;   // Tiempo de barras
	long _num_macdP = 0;
	String operationfilter="";    // ALL, BUY, SELL
	boolean volume_increased = Boolean.FALSE;

	
	boolean bBuyOperation = Boolean.FALSE;									
	boolean bSellOperation = Boolean.FALSE;
	JSONObject _tradeDescription;// // acumular la traza de los valores introducidos
	
	SimpleDateFormat TimeFormat = new SimpleDateFormat (Utilities.__IBTRADER_SHORT_HOUR_FORMAT);
	SimpleDateFormat auditTimeFormat = new SimpleDateFormat (Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);

	double stopLost = 0 ; // metemos el stop lost calculado  segun BUY o SELL , de acuerdo a la R2 o S2
	
	@Override
	public long  execute(Share _share, Market _market,  Date backtestingdDate)
	{
		long returnValue =-1;
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
				
	    		
	    		User user = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
			    boolean bOrderIsWithinBudget =   PositionLocalServiceUtil.IsinRangeUserBudget(user,_share.getMultiplier() *  this.getValueIn() * number_to_purchase, position_mode, _share.getCompanyId(), _share.getGroupId());
			    if (!bOrderIsWithinBudget)
			    	return returnValue;
	    		
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
				BuyPositionTWS.action(action);			
				
				_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
				
				this.setTargetOrder(BuyPositionTWS);			
  			}
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			Position BuyPositionSystem = PositionLocalServiceUtil.createPosition(CounterLocalServiceUtil.increment(Position.class.getName()));
			BuyPositionSystem.setBacktestingId(ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
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
       		
    		/* BuyPositionSystem.setPercentualstoplost_out(Utilities.RoundPrice(Math.abs(this.getValueIn() - stopLost)  / this.getValueIn() * 100));
       		BuyPositionSystem.setPricestoplost_out(Utilities.RoundPrice(stopLost));
       		*/
    		double stoplost =_share.getPercentual_stop_lost();
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_LOST,0)>0)
    			stoplost =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_LOST,0);    			
    		if (stoplost>0)    			
    			BuyPositionSystem.setPercentualstoplost_out(stoplost);
    		/* else
    			BuyPositionSystem.setPercentualstoplost_out(_defaultstop_percent);
    		*/
    		/* tralling stop lost */
    		double percentualtraillingstoplost =_share.getPercentual_trailling_stop_lost();
    		double pricetrailingstop;
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_TRAILLING_STOP_LOST,0)>0)
    			percentualtraillingstoplost =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_TRAILLING_STOP_LOST,0);
    		
    		if (percentualtraillingstoplost>0)    			
    		{	
    			BuyPositionSystem.setPercentual_trailling_stop_lost(percentualtraillingstoplost);
    			if (BuyPositionSystem.getType().equals(PositionStates.statusTWSFire.BUY.toString()))  // operacion de compra normal..??
    				pricetrailingstop = (this.getValueIn() - (this.getValueIn() * percentualtraillingstoplost /100));
    			else
    				pricetrailingstop = (this.getValueIn() + (this.getValueIn() * percentualtraillingstoplost /100));
    			
    			BuyPositionSystem.setPricetrailling_stop_lost(Utilities.RoundPrice(pricetrailingstop));
    		}	
    		double stopprofit =_share.getPercentual_stop_profit();
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_PROFIT,0)>0)
    			stopprofit =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_PROFIT,0);    	
    		
    		if (stopprofit>0)
    			BuyPositionSystem.setPercentualstopprofit_out(stopprofit);
    		
			
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
	
	
	public IBStrategyPivotsPoints() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* 
	backtestingdDate --> backtestingdDate, SI ES BACKTESTING  O NO 
	*/
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
	

		boolean verified = Boolean.FALSE;
		boolean existsPosition = Boolean.FALSE;

		try
	    {
			
		if (_strategyImpl.getStrategyparamsoverride()==null)
			return Boolean.FALSE;
		
		/* TIMEZONE AJUSTADO */
		//Date _FromNow =  !isSimulation_mode() ?    Utilities.getDate(_IBUser) : backtestingdDate;
		Date _FromNow =  !isSimulation_mode() ?    new Date() : backtestingdDate;
		Calendar _calendarFromNow = Calendar.getInstance();
		_calendarFromNow.setTime(_FromNow);
	
		long currentSeconds = _calendarFromNow.get(Calendar.SECOND);
		_calendarFromNow.set(Calendar.SECOND, 0);
		_calendarFromNow.set(Calendar.MILLISECOND, 0);
		
		long _ModMinuteToEntry = _calendarFromNow.get(Calendar.MINUTE) % _num_macdT;
		if (_ModMinuteToEntry!=0)  // NO ESTOY EN EL MINUTO 5,10,15,20..etc (para las barras de 5)
			return Boolean.FALSE;
		
	    this.setJsonStrategyShareParams(JSONFactoryUtil.createJSONObject(_strategyImpl.getStrategyparamsoverride()));					
		
		String HoraActual = "";
		Calendar calFechaActualWithDeadLine;
		Calendar calFechaFinMercado;
		User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
		HoraActual = Utilities.getWebFormattedTime(_calendarFromNow.getTime());
		
		Market marketRealOpenCloseTimes = Utilities.getOpenCloseMarket(_share, backtestingdDate, isSimulation_mode());
		if (Validator.isNull(marketRealOpenCloseTimes)) return false;
		if (!isSimulation_mode())
		{
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(HoraActual); 
			calFechaFinMercado = Utilities.getNewCalendarWithHour(marketRealOpenCloseTimes.getEnd_hour()); 
		}
		else	
		{
				
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(backtestingdDate, HoraActual);
			calFechaFinMercado = Utilities.getNewCalendarWithHour(backtestingdDate, marketRealOpenCloseTimes.getEnd_hour()); 			
		}	
		
		
		_num_macdP 						= this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER,0);
		_num_macdT 		 				= this.getJsonStrategyShareParams().getLong(_EXPANDO_PIVOTPOINT_CANDLE_SIZE,0);		
		operationfilter					= this.getJsonStrategyShareParams().getString(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,"ALL").trim();
		volume_increased 				= this.getJsonStrategyShareParams().getBoolean(_EXPANDO_PIVOTSPOINTS_VOLUME_INCREASED,Boolean.FALSE);

		
		if (_num_macdT==0 || _num_macdT ==0)
			return Boolean.FALSE;
		

		
		calFechaActualWithDeadLine.add(Calendar.MINUTE, this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET));
		existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),
				Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId()), Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
		
		if (!existsPosition &&  !calFechaActualWithDeadLine.after(calFechaFinMercado))		
		{	
			// supuestamente estamos leyendo...verificamos si con respecto al mercado ya tenemos los max y min
			// comparamos que la hora de lectura final haya sobrepasado el actual 
			// HHMM
			// HORA DE FIN DE CALCULO DE MAX Y MINIMOS.
			String StartHourTrading = "";
			if (!isSimulation_mode())
				 StartHourTrading =  Utilities.getActualHourFormatPlusMinutes(_calendarFromNow, marketRealOpenCloseTimes.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));
			else				
				 StartHourTrading = Utilities.getActualHourFormatPlusMinutes(marketRealOpenCloseTimes.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));
			// COMPROBAMOS ALGUN TIPO DE ERROR 
			if (StartHourTrading.contains("-1"))
			{
				_log.info("[ Errores formateando las horas de StarHourTrading de la accion. Hora[" + marketRealOpenCloseTimes.getStart_hour()  + "], Offset1[" + this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET)+ "]");
				return Boolean.FALSE;
			}
				
			_log.debug("HoraActual:" + HoraActual + ",StartHourTrading:" + StartHourTrading);
			if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa
			{
			
				// ya no obtenemos el maximo y minimo, sino el correspondiente al tramo que me han dicho
				Double lastRealtime = null;	
								
				if (!isSimulation_mode())
				{
					Realtime oShareLastRTime =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_FromNow);
					lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
					
				}					
				else
				{
					HistoricalRealtime oShareLastRTime = HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_FromNow);
					lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
					
				}
				
				if  (Validator.isNotNull(lastRealtime))
				{
					Double _avgMobileExponential = BaseIndicatorUtil.getExponentialAvgMobile(_calendarFromNow.getTime(), lastRealtime.doubleValue(), _num_macdT, _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_macdP , isSimulation_mode(), _market);
					
					
					
					
					if (Validator.isNull(_avgMobileExponential))
						return Boolean.FALSE;
						
					/* MODO PIVOT POINTS DE LA ULTIMA SESION */
					IBTraderPivotPointIndicator  _pivotPointData  = BaseIndicatorUtil.getSessionPivotPointIndicator(_calendarFromNow.getTime(), _num_macdT, _share, isSimulation_mode(), _market);
					/* MODO PIVOT POINTS DE LA ULTIMA  BARRA 
					IBTraderPivotPointIndicator _pivotPreviousBarPointData  = BaseIndicatorUtil.getBarPivotPointIndicator(_calendarFromNow.getTime(), _num_macdT, _share, isSimulation_mode(), _market);
					*/
					 
					
					if (currentSeconds<3)						
					{
						_log.debug(_share.getSymbol() + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));								
						_log.debug("lastRealtime.doubleValue() " + lastRealtime);
						_log.debug("avgMobileExponential.doubleValue():" + (Validator.isNotNull(_avgMobileExponential) ? _avgMobileExponential.doubleValue() : 0));						
						_log.debug("Last Bar Session PP :" + (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPoint() : 0));
						_log.debug("Last Bar Session   PP R1 :" + (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPointResistance1() : 0));
						_log.debug("Last Bar Session  PP S1 :" + (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPointSupport1() : 0));
						
						/* _log.debug("Last Session  PP :" + (Validator.isNotNull(_pivotSessionPointData) ? _pivotSessionPointData.getPivotPoint() : 0));
						_log.debug("Last Session  PP R1 :" + (Validator.isNotNull(_pivotSessionPointData) ? _pivotSessionPointData.getPivotPointResistance1() : 0));
						_log.debug("Last Session  PP S1 :" + (Validator.isNotNull(_pivotSessionPointData) ? _pivotSessionPointData.getPivotPointSupport1() : 0));

						*/
					}
					
					
					
					if (_avgMobileExponential!=null && _pivotPointData!=null &&  _pivotPointData.getPivotPoint()>0)
					{
					
										
						boolean _BuySuccess = false;
						boolean _SellSuccess = false;
						
						/* usamos r1 o s1 , 2, 3  como ruptura */
						double BuyValueWithGapR1 = 
								(_pivotPointData.getPivotPointResistance1() * this.getJsonStrategyShareParams().getDouble(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED) / 100) +  _pivotPointData.getPivotPointResistance1();
						double BuyValueWithGapR2 = 
								(_pivotPointData.getPivotPointResistance2() * this.getJsonStrategyShareParams().getDouble(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED) / 100) +  _pivotPointData.getPivotPointResistance2();
						double BuyValueWithGapR3 = 
								(_pivotPointData.getPivotPointResistance3() * this.getJsonStrategyShareParams().getDouble(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED) / 100) +  _pivotPointData.getPivotPointResistance3();		
												
											
						double SellValueWithGapS1 = _pivotPointData.getPivotPointSupport1()  - (_pivotPointData.getPivotPointSupport1()  * this.getJsonStrategyShareParams().getDouble(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED) / 100) ;  
						double SellValueWithGapS2 = _pivotPointData.getPivotPointSupport2()  - (_pivotPointData.getPivotPointSupport2()  * this.getJsonStrategyShareParams().getDouble(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED) / 100) ;  
						double SellValueWithGapS3 = _pivotPointData.getPivotPointSupport3()  - (_pivotPointData.getPivotPointSupport3()  * this.getJsonStrategyShareParams().getDouble(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED) / 100) ;  

						
						// 
						_BuySuccess =   lastRealtime.doubleValue() >_avgMobileExponential.doubleValue() &&
											(lastRealtime.doubleValue() > BuyValueWithGapR1 ||
													lastRealtime.doubleValue() > BuyValueWithGapR2 ||
															lastRealtime.doubleValue() > BuyValueWithGapR3);
						// 
						_SellSuccess =  lastRealtime.doubleValue() <_avgMobileExponential.doubleValue() &&
										(lastRealtime.doubleValue() < SellValueWithGapS1 
											|| lastRealtime.doubleValue() < SellValueWithGapS2
												|| lastRealtime.doubleValue() < SellValueWithGapS3);
						
						
						_BuySuccess = _BuySuccess &&  
								(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.BUY.toString())); 
	
						_SellSuccess = _SellSuccess  &&  
								(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.SELL.toString()));
						
						boolean   _BuySuccessR1 = Boolean.FALSE;							
						boolean   _BuySuccessR2 = Boolean.FALSE; 								
						boolean   _BuySuccessR3 = Boolean.FALSE; 
										
						boolean   _SellSuccessS1 = Boolean.FALSE; 							
						boolean   _SellSuccessS2 = Boolean.FALSE; 								
						boolean   _SellSuccessS3 = Boolean.FALSE; 	
						
						
						boolean bVolIncreased = Boolean.TRUE;
						long    _volume = 0;
						long _volume_previous = 0;
						Calendar _previousBarDate = Calendar.getInstance();
						Calendar _previousInitialBarDate = Calendar.getInstance();

						if (_BuySuccess || _SellSuccess)
						{
							
							if (volume_increased)
							{
								_previousBarDate.setTime(_calendarFromNow.getTime());
								_previousBarDate.add(Calendar.MINUTE, - (int) _num_macdT);
								
								_volume = BaseIndicatorUtil.getVolumeBetweenBars(_previousBarDate.getTime(), _calendarFromNow.getTime(), _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), isSimulation_mode());
								//_volume =  Validator.isNull(oRTimeEnTramo) ? null : oRTimeEnTramo.getVolume();
								if (_volume>0)
								{	
									_previousInitialBarDate.setTime(_previousBarDate.getTime());
									_previousInitialBarDate.add(Calendar.MINUTE, - (int) _num_macdT);				
									_volume_previous = BaseIndicatorUtil.getVolumeBetweenBars(_previousInitialBarDate.getTime(), _previousBarDate.getTime(), _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), isSimulation_mode());				
									bVolIncreased  = _volume > _volume_previous;
								}
							}
							
							
							/* SACAMOS EL MAXIMO DE LA SESION PARA DESCARTAR VOLVER A ENTRAR EN  LAS MISMAS ZONAS */
							
							double  minSession=0;	 
							double  maxSession=0;
							
							
							/* INICIO DEL DIA Y FIN PARA SACAR EL MAXIMO Y MINIMO DE LA SESION,
							 * DEL HISTORICAL SOLO SACAMOS HASTA EL MOMENTO  */
							Calendar _dayFrom = Calendar.getInstance();
							_dayFrom.setTime(_calendarFromNow.getTime());
							_dayFrom.set(Calendar.HOUR_OF_DAY, 0); 
							_dayFrom.set(Calendar.MINUTE, 0);
							_dayFrom.set(Calendar.SECOND, 0);
							Calendar _dayTo = Calendar.getInstance();
							_dayTo.setTime(_calendarFromNow.getTime()); // YA VIENE RELLENA CON LA FECHA DEL MOMENTO DEL BACKTESTING 
							
							
							/* VERIFICAMOPS QUE NO SE HAYAN ALCANZADO PREVIAMENTE LOS VALORES, EXCEPTO EN LA BARRA ACTUAL  */
							_dayTo.add(Calendar.MINUTE, - (int) _num_macdT);
							if (!isSimulation_mode())
							{								
								Realtime MinMaxSession =  RealtimeLocalServiceUtil.findMinMaxRealTime(_dayFrom.getTime(), _dayTo.getTime(), _share.getShareId(), _share.getCompanyId(), _share.getGroupId());
								if (Validator.isNotNull(MinMaxSession))
								{
									maxSession =  MinMaxSession.getMax_value();					
									minSession	= MinMaxSession.getMin_value();
								}
							}					
							else
							{
								
								HistoricalRealtime MinMaxSessionH =  HistoricalRealtimeLocalServiceUtil.findMinMaxRealTime(_dayFrom.getTime(), _dayTo.getTime(), _share.getShareId(), _share.getCompanyId(), _share.getGroupId());
								if (Validator.isNotNull(MinMaxSessionH))
								{
									maxSession =  MinMaxSessionH.getMax_value();					
									minSession	= MinMaxSessionH.getMin_value();
								}								
								
							}
							
							/* CONTROLAMOS QUE NO HAYAN  SUPERADO EN LA SESION LOS SOPORTES Y RESISTENCIAS */
							_BuySuccessR1 = lastRealtime.doubleValue() > BuyValueWithGapR1 && maxSession <= lastRealtime.doubleValue();				
							_BuySuccessR2 = lastRealtime.doubleValue() > BuyValueWithGapR2 && maxSession <= lastRealtime.doubleValue();			
							_BuySuccessR3 = lastRealtime.doubleValue() > BuyValueWithGapR3 && maxSession <= lastRealtime.doubleValue();	
							
							_SellSuccessS1 = lastRealtime.doubleValue() < SellValueWithGapS1 && minSession >= lastRealtime.doubleValue();								
							_SellSuccessS2 = lastRealtime.doubleValue() < SellValueWithGapS2 && minSession >= lastRealtime.doubleValue();							
							_SellSuccessS3 = lastRealtime.doubleValue() < SellValueWithGapS3 && minSession >= lastRealtime.doubleValue();		
							
							
							_BuySuccess = _BuySuccess && (_BuySuccessR1 || _BuySuccessR2 || _BuySuccessR3);
							_SellSuccess = _SellSuccess && (_SellSuccessS1 || _SellSuccessS2 || _SellSuccessS3);
							
						}
						
						
						/*  SACAMOS DEPURACION EN DURANTE LOS TRES PRIMEROS SEGUNDOS EN LOS CORTES DE BARRAS */
						if (currentSeconds<3)						
						{
							_log.debug("_pivotPointData.getPivotPoint() for :" + _share.getSymbol() + ":" +  (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPoint() : 0) + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));
							_log.debug("_pivotPointData.getPivotPoint() for :" + _share.getSymbol() + ":" +  (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPoint() : 0) + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));
							_log.debug("_pivotPointData.R1 for :" + _share.getSymbol() + ":" +  (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPointResistance1() : 0) + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));
							_log.debug("_pivotPointData.S1 for :" + _share.getSymbol() + ":" +  (Validator.isNotNull(_pivotPointData) ? _pivotPointData.getPivotPointSupport1() : 0) + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));
							_log.debug("lastRealtime.doubleValue() >_avgMobileExponential.doubleValue()" + _BuySuccess);
							_log.debug("lastRealtime.doubleValue() <_avgMobileExponential.doubleValue()" + _SellSuccess);
	
							
						}
						
						/* fecha hora venicmiento  NO proxima */ 
						boolean  IsFutureTradeable = Utilities.IsFutureTradeable(_share);
						
						
						if (bVolIncreased && IsFutureTradeable && (_BuySuccess || _SellSuccess))
						{
							
							_log.info("bVolIncreased:" + bVolIncreased + ",volume:" + _volume  + ",volume_previous:" + _volume_previous 
									+ ",Volume From:" + _previousBarDate.getTime()  + ",Volume Until:" + _calendarFromNow.getTime()  +
									",Volume_Previous  From:" + _previousInitialBarDate.getTime()  + ",Volume Until:" + _previousBarDate.getTime()  + 
									"._BuySuccess:," + _BuySuccess   + "._SellSuccess:," + _SellSuccess + " for :" + _share.getSymbol());
							
							
						    this.setValueIn(lastRealtime.doubleValue());											
							this.setVerified(Boolean.TRUE);												
							verified = true;							
							this.bBuyOperation = _BuySuccess;									
							this.bSellOperation = _SellSuccess;
														
							_tradeDescription = JSONFactoryUtil.createJSONObject();
							_tradeDescription.put("lastRealtime.doubleValue()", lastRealtime.doubleValue());
							_tradeDescription.put("_avgMobileExponential", _avgMobileExponential);	
							_tradeDescription.put("_num_macdP", _num_macdP);
							_tradeDescription.put("_num_macdT", _num_macdT);
							_tradeDescription.put("operationfilter", operationfilter);						
							_tradeDescription.put("_pivotPointData.getPivotPoint()", _pivotPointData.getPivotPoint());	
							_tradeDescription.put("_pivotPointData.R1()", _pivotPointData.getPivotPointResistance1());												
							_tradeDescription.put("_pivotPointData.S1()", _pivotPointData.getPivotPointSupport1());			
							_tradeDescription.put("_pivotPointData.R2()", _pivotPointData.getPivotPointResistance2());												
							_tradeDescription.put("_pivotPointData.S2()", _pivotPointData.getPivotPointSupport2());
							_tradeDescription.put("stopLost", stopLost);																			
							_tradeDescription.put("VolumeIncreased", bVolIncreased);
							

						
						}
						
						/* ALMACENAMOS LOS VALORES DE AUDITORIA SI ES TIEMPO REAL */
						if (!isSimulation_mode())
						{
							jsonStrategyIndicators= JSONFactoryUtil.createJSONObject();
							jsonStrategyIndicators.put("_avgMobileExponential", _avgMobileExponential);
							jsonStrategyIndicators.put("shareId", _share.getShareId());
							jsonStrategyIndicators.put("bartime", auditTimeFormat.format(_calendarFromNow.getTime()));
							jsonStrategyIndicators.put("lastRealtime", lastRealtime.doubleValue());
							jsonStrategyIndicators.put("periods", _num_macdP);
							jsonStrategyIndicators.put("barsize", _num_macdT);								
							jsonStrategyIndicators.put("_pivotPointData.getPivotPoint()", _pivotPointData.getPivotPoint());								
							jsonStrategyIndicators.put("_pivotPointData.R1()", _pivotPointData.getPivotPointResistance1());								
							jsonStrategyIndicators.put("_pivotPointData.S1()", _pivotPointData.getPivotPointSupport1());
							jsonStrategyIndicators.put("_pivotPointData.R2()", _pivotPointData.getPivotPointResistance2());								
							jsonStrategyIndicators.put("_pivotPointData.S2()", _pivotPointData.getPivotPointSupport2());	
							jsonStrategyIndicators.put("stopLost", stopLost);
							

							
							try 
							{
								AuditIndicatorsStrategyPK pkAudit = new AuditIndicatorsStrategyPK(_share.getGroupId(), _share.getCompanyId(), auditTimeFormat.format(_calendarFromNow.getTime()), _strategyImpl.getClass().getName(), _share.getShareId());
								AuditIndicatorsStrategy auditStrategy = AuditIndicatorsStrategyLocalServiceUtil.fetchAuditIndicatorsStrategy(pkAudit);
								if (Validator.isNull(auditStrategy))
								{
									auditStrategy = AuditIndicatorsStrategyLocalServiceUtil.createAuditIndicatorsStrategy(pkAudit);
									auditStrategy.setAuditData(Validator.isNotNull(jsonStrategyIndicators) ? jsonStrategyIndicators.toString() : StringPool.BLANK);
									AuditIndicatorsStrategyLocalServiceUtil.addAuditIndicatorsStrategy(auditStrategy);
									
								}
							}
							catch (Exception e)	{}
							
						}	
					
				 } // if  (Validator.isNotNull(oShareLastRTime))			   	
				} // if (_avgMobileExponential!=null)			
			}// NO EXISTE POSICION 
		} // if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa	
	    }
		catch (Exception er)
	    {
			_log.info(er.getMessage());
			this.setVerified(Boolean.FALSE);				
	    }
		return verified;
	}


	@Override
	public void init(long companyId) {
	// TODO Auto-generated method stub
	// CREAMOS LOS EXPANDOS NECESARIOS 
		
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER, String.valueOf(ExpandoColumnConstants.INTEGER));
	Parameters.put(_EXPANDO_PIVOTPOINT_CANDLE_SIZE,  String.valueOf(ExpandoColumnConstants.INTEGER));		
	Parameters.put(_EXPANDO_GAP_TO_ENTER_PIVOTPOINTREACHED,  String.valueOf(ExpandoColumnConstants.DOUBLE));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,  String.valueOf(ExpandoColumnConstants.STRING_ARRAY));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_PIVOTSPOINTS_VOLUME_INCREASED,  String.valueOf(ExpandoColumnConstants.STRING_ARRAY));  // ESTE ES EL UNICO DOUBLE

	
	ExpandoTable expandoTable;
	try {
		expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategyPivotsPoints.class.getName());
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