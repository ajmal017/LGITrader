package com.ibtrader.strategy;

import java.sql.Timestamp;
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
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;
import com.ibtrader.interactive.TIMApiWrapper;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyCancelPosition extends StrategyImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyCancelPosition.class);
	Position cancelPosition = null;
	
	@Override
	public long execute(Share _share, Market _market) {
		// TODO Auto-generated method stub
	long returnValue=-1;
	try		
    {	
		_log.info("UserAccount: detectada posible entrada de " + _share.getName() +  "Tick:" + _share.getSymbol() + ",PrecioCompra:" + this.getValueIn());
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
	   
	   
	  
	   if (cancelPosition.getPendingcancelled()==1)
		{
			
			// las PendingSubmit hay que eliminarlas porque no estan negociando. P.e. Prohibido operar a corto, bloqueo de consola
			if (cancelPosition.getState_in().toString().equals(PositionStates.statusTWSCallBack.PendingSubmit.toString()))
			{
				PositionLocalServiceUtil.deletePosition(cancelPosition.getPositionId());
				
			}
			else  
			{					
				
				/* 1. CANCELAMOS LA ENTRADA 
				 * 2. CANCELAMOS LA SALIDA 
				 */
				//getDate_real_out()==null &&   this.getState().equals(PositionStates.status.BUY_OK.toString()); 
				if (cancelPosition.IsPendingOut() && cancelPosition.getPositionId_tws_out()>=0)
					returnValue =  cancelPosition.getPositionId_tws_out();
				if (cancelPosition.IsPendingIn()  && cancelPosition.getPositionId_tws_in()>=0)
					returnValue =  cancelPosition.getPositionId_tws_in();
				
				if (returnValue!=-1)
				{
					cancelPosition.setPendingcancelled(0);
					PositionLocalServiceUtil.updatePosition(cancelPosition);
					returnValue =  cancelPosition.getPositionId();
				}
			}
		}			
	   
		/* Posicion en MYSQL de CONTROL */
		
		
		/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
	
		

		
    }
	catch (Exception er)
        {
			_log.info(er.getMessage());
			er.printStackTrace();
		
        }
	return returnValue;
	}
	
	public IBStrategyCancelPosition() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl) {
	
	boolean verified = false;
	List<Position> lCancelled = null;
	try
    {
		/* cancelada y abierta */
		lCancelled = PositionLocalServiceUtil.findByCancelShareCompanyGroup(_share.getCompanyId(), _share.getGroupId(),1, _share.getShareId());
		if (lCancelled!=null && !lCancelled.isEmpty())
		{
			for (Position position : lCancelled)
			{
				if (position.IsCancelable())
				{
					
					cancelPosition  = position;
					verified = Boolean.TRUE;
					break;
					
				}
			}
			
			
		}
    }	

	catch (Exception er)
    {
		_log.info(er.getMessage());
		//er.printStackTrace();
		this.setVerified(Boolean.FALSE);				
    }

	return verified;
	}
	
}