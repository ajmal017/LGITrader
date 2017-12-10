package com.ibtrader.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;




import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.cron.IBTraderRead;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.StrategyLocalServiceUtil;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.ibtrader.util.Utilities;
import com.ibtrader.util.*;
import com.ibtrader.interactive.TIMApiGITrader;


public class CronUtil {

	private final static Log _log = LogFactoryUtil.getLog(CronUtil.class);

	
	public static void StartReadingCron(Message _message) throws Exception {
	{
		Config _Conf_CronRunning = null;
		int _CRON_RUNNING = -1;	  // el dos
		List<Config> lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyCRON_READING_STATUS, true);
		if (!lConfig.isEmpty())
		{
			_Conf_CronRunning = lConfig.get(0);
			_CRON_RUNNING = Long.valueOf(_Conf_CronRunning.getValue()).intValue();
		}
		
		
		
		int 	_CLIENT_ID = 6;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = 7497;
		
		 /* UNA GLOBAL POR DEFECCTO */
		lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_HOST, true);
		if (!lConfig.isEmpty())  _HOST = lConfig.get(0).getValue();
		
		 /* UNA GLOBAL POR DEFECCTO */
		 lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_PORT, true);
		if (!lConfig.isEmpty())  _PORT = Integer.valueOf(lConfig.get(0).getValue());
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
		TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);

		
		
		/* activamos el scheduler de MYSQL por si no lo est�.No me tira desde el my.ini 
		TradingMarketDAO.StartTradingSchedulerMYSQL();*/
		_log.info("Starting Trading_Read process, verifying Cron Runnig Status --> " + _CRON_RUNNING); 
		
		// verificamos si hay mercado abierto.
		/* if (lActiveMarkets.size()>0)
		{
			*/
		if (oTWS.GITraderTWSIsConnected())
		{
			oTWS.GITraderDisconnectFromTWS();
		}
		
		if (_CRON_RUNNING==0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
		{
			
		
		oTWS.GITraderConnetToTWS();
		if (oTWS.GITraderTWSIsConnected() )
		{
		
		
		
		Contract oContrat = null;
		
		/* VERIFICAMOS MERCADOS ACTIVOS */
	    
	    java.util.List<Share> lShare = null;
	    /* 1. VERIFICAMOS QUE EXISTA UNA PETICION PARA EL HISTORICAL DATA QUE NO HAYA FINALIZADO SIN ERROR */
	    
	    boolean bAllRequested = false;
	    ArrayList<String> lShareRequested = new ArrayList();
	    
	    /* LANZAMOS LA OPERACION DE CONTINUO */ 
	    _Conf_CronRunning.setValue(String.valueOf(1));
    	ConfigLocalServiceUtil.updateConfig(_Conf_CronRunning);
    	
	    while (true)
	    {	
	    if (!bAllRequested)
	    {
	    	 /* pnoemos el flag de RUNNING A 1 */
	    	 // empezamos a contar desde 5 o 10 minutos antes de la apertura para contar precios
	    	String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 
	    	
	    	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,true);
		    			
		    if (!lActiveMarkets.isEmpty())
		    {
		    	
		    	/* recorremos mercados de todas las companes  */
		    	
		    	for (int j=0;j<lActiveMarkets.size();j++)
		    	{
		    		
		    		Market oMarket = lActiveMarkets.get(j);
		    		
	    			
		    		 lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(), true, oMarket.getGroupId(), oMarket.getCompanyId());
		         	
		    	    //	lShare = ShareDAO.getListActiveShareByMarket(oMarket.getMarketID());
		    			    		
		    		
		    		if (!lShare.isEmpty())
		    		{
		    			
		    			//MyLog.log(Priority.INFO, "Recorremos acciones lShare!=null" + String.valueOf(lShare!=null) + "|Size:" + lShare.size());
		    		
			    		for (int z=0;z<lShare.size();z++)
			        	{

			    			boolean bToRequest=true;
			    			
			    			
			    			Share oShare = (Share)lShare.get(z);
			    			
			    			String _Expiration = "";
			      		    if (oShare.getExpiry_date()!=null) _Expiration = sdf.format(oShare.getExpiry_date());			    					    		
			    			if  (oShare.getLast_error_data_read()!=null && !oShare.getLast_error_data_read().trim().equals(""))
			    			{
			    				
			    				lShareRequested.remove(oShare.getSymbol());
			    			}
			    			
			    			if (lShareRequested!=null && lShareRequested.contains(oShare.getSymbol() + "|" + _Expiration))
			    			{
			    				bToRequest = false; 
			    				
			    			}
			    			else
			    			{
			    				lShareRequested.add(oShare.getSymbol() + "|" + _Expiration);
			    			}
			    			if (bToRequest)
			    			{
			    				
				    			
			    				if (oShare.getSecurity_type().equals("FUT"))
			    				{
			    					oContrat = new FutContract( oShare.getSymbol(), _Expiration);
			    					//oContrat.multiplier(String.valueOf(oShare.getMultiplier()));		    					
			    					oContrat.exchange(oShare.getExchange());
			    					oContrat.currency(oMarket.getCurrency());
			    				}
			    				else		    					
			    					oContrat = new StkContract( oShare.getSymbol());
			    				
				    			
				    			//oContrat = (Contract) oTWS.createContract(oShare.getSymbol(), oMarket.getSecurity_type(),oMarket.getExchange(),oMarket.getCurrency());//, _Expiration, "", 0);
				    			
				    			_log.info("TradingRead de :" + oShare.getSymbol() + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());
				    			
				    			
				    			// USAMOS EL CLIENT_ID UNICO PARA GENERAR INTERVALOS DE ORDERSID PARA 
				    			// EVITAR CONCURRENCIA Y GENERAR CODIGOS IGUALES
				    			
				    			long  _INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(IBOrder.class.getName()) +  _CLIENT_ID;
				    			
				    			/* insertamos control de ordenes de peticion */
				    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
				    			_order.setCompanyId(oMarket.getMarketId());
				    			_order.setGroupId(oMarket.getGroupId());
				    			_order.setShareID(oShare.getShareId());
				    			/* pedimos tiempo real */
				    			IBOrderLocalServiceUtil.updateIBOrder(_order);

				    			try {
									oTWS.GITraderGetRealTimeContract(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									oTWS.GITraderConnetToTWS();
									//if (oTWS.GITraderTWSIsConnected())
									if (oTWS.GITraderTWSIsConnected())
									{
										oTWS.GITraderDisconnectFromTWS(); 
										
									}
								}
			    			}
			        		
			        	}
		
		    		}
		    		
		    	}
		    	// fin de mercamos y acciones.
		    	
		    }
	    /* } */
	 		// una vez que estamos dentro..verificamos que haya conexion y este el mercado abierto.
			boolean IsConnected = oTWS.GITraderTWSIsConnected();		   // en debug est� siempre a false		
			if (lActiveMarkets.isEmpty()  ||  !IsConnected)
			{
				if (IsConnected)
				{
					oTWS.disconnectFromTWS();
					
				}
				_log.info("Ending Trading_Read Process..There is no open market or console closed");
			 	break; 
			}
		    	
	    	// fin while
	    }   // fin is connected
	     
		else
			{
			
			_log.info("Ending Trading_Read process..No conectado a la TWS");
			}	
		}	  
	 }
	else
	{
		_log.info("Ending Trading_Read process..No conectado a la TWS");
	}
			
		
	}  // end is runnung
	}	}
	public static void StartTradingCron(Message _message) throws Exception {

		/* TENEMOS TAREAS CON EL TIEMPO REAL DE CADA ACCION 
		 * 
		 * 1. ACCEDER A LAS ACCIONES DEL MODELO DE ACCION
		 * 1.5. VERIFICAR QUE NO HAY OPERACION DE ESE MODELO PENDIENTE
		 * 2. TESTEAR SI SE CUMPLE ALGUNA. VERIFICAR MAXIMOS Y MINIMOS  
		 * 3. SI SE CUMPLE...LANZAR OPERACION ... GUARDAR OPERACION DE COMPRA VENTA Y CONTROLAMOS.
		 * 4. SEGUIMOS MIENTRAS HAYA ACCIONES CON POSIBILIDADES.
		 * 5. falta controlar cuando entra el modelo (OffSet con respecto a la hora inicil de mercado?)
		 * PENSAR EN LOS BUCLES HACERLOS DENTRO...
		 * */
		Config _Conf_CronRunning = null;
		int _CRON_RUNNING = -1;	  // el dos
		List<Config> lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyCRON_TRADING_STATUS, true);
		if (!lConfig.isEmpty())
		{
			_Conf_CronRunning = lConfig.get(0);
			_CRON_RUNNING = Long.valueOf(_Conf_CronRunning.getValue()).intValue();
		}
		
		
		
		int 	_CLIENT_ID = 2;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = 7497;
		 /* UNA GLOBAL POR DEFECCTO */
		lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_HOST, true);
		if (!lConfig.isEmpty())  _HOST = lConfig.get(0).getValue();
		 /* UNA GLOBAL POR DEFECCTO */
		 lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_PORT, true);
		if (!lConfig.isEmpty())  _PORT = Integer.valueOf(lConfig.get(0).getValue());

		
		TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);	
		 
		String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 
   	 
   	 	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,true);
		
		
		
		_log.info("Starting Trading Cron  Process..");
		
		if (oTWS.GITraderTWSIsConnected())
		{
			oTWS.GITraderDisconnectFromTWS();
		}
		
		if (_CRON_RUNNING==0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
		{
			
		
		oTWS.GITraderConnetToTWS();
		if (oTWS.GITraderTWSIsConnected() && !lActiveMarkets.isEmpty())
		{
			while (true)
			{	

			//oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);
			
			//if (oTWS.GITraderTWSIsConnected())  oTWS.GITraderDisconnectFromTWS(); 
			
			
			if (!oTWS.GITraderTWSIsConnected()) oTWS.GITraderConnetToTWS();    	
	
	
	    	/* recorremos mercados */
	    	List<Share> lShare;	    	
	    	for (Market oMarket : lActiveMarkets)
	    	{
	    		
    			
	    		 lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(), true,oMarket.getGroupId(),oMarket.getCompanyId());
	         	
	    		 for (Share oShare :lShare)
	    		 {
		    			/* recorremos mercados y acciones  para verificar las estrategias. Por reflexion, creamos la factoria
						 * de objetos que implementa cada strategia */
		    			List<Strategy> _lStrategies = StrategyLocalServiceUtil.findByActiveCompanyId(true, oShare.getCompanyId());
		    			List<StrategyShare> _lStrategiesOfShare = StrategyShareLocalServiceUtil.getByGroupCompanyShareId(oShare.getGroupId(), 
		    						oShare.getCompanyId(), oShare.getShareId());
		    			
		    			for (Strategy oStrategy :_lStrategies)
		    			{
		    				
		    				if (Utilities.fn_IsStrategyInShareStrategies(oStrategy.getStrategyID(),_lStrategiesOfShare))
		    				{		    					
		    					
		    					
		    							    					
		    					StrategyImpl _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(oStrategy.getClassName()).newInstance();
		    					_strategyImpl.init(oShare.getCompanyId());   // verify if custom fields are created and filled 	    						    				
		    					if (_strategyImpl.verify(oShare, oMarket))
		    							_strategyImpl.execute(oShare, oMarket);	
		    					
		    				}
		    			}
	    		 }
	    	} // END FOR ACTIVE MARKETS
				    		
			} // end wile true 	
		}
		else
		{
		  _log.info("Ending Trading process..No conectado a la TWS");
		}					
	}  //if (_CRON_RUNNING==0) 
}
		
	
	

}