package com.ibtrader.data.service.persistence.impl;

import java.util.Date;
import java.util.List;

import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.impl.PositionImpl;
import com.ibtrader.data.service.persistence.PositionFinder;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.ibtrader.util.*;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.StringUtil;

public class PositionFinderImpl  extends PositionFinderBaseImpl  implements PositionFinder {
	
	private Log _log = LogFactoryUtil.getLog(PositionFinderImpl.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<Position> getIntradiaPositions(Date to,long groupId, long companyId, String positionMode)
	{
	 List<Position> lResults = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),GET_INTRADIA_POSITIONS_);
        
        SQLQuery q = session.createSQLQuery(sql);
        q.setCacheable(false);
        q.addEntity("IBTrader_Position", PositionImpl.class);
        
        	
         
        QueryPos qPos = QueryPos.getInstance(q);	         
        qPos.add(to);
        qPos.add(companyId);
        qPos.add(groupId);      
        qPos.add(positionMode);
        lResults = (List<Position>) QueryUtil.list(q, getDialect(), 0, 10000);
        if (!lResults.isEmpty())
    		return lResults;
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	
	/* backtestingId : las simulaciones llevan un codigo, resto , -1 */
	 
	@SuppressWarnings("unchecked")
	public List getPositionClosedResults(Date from, Date to,long groupId, long companyId, String positionMode, long backtestingId)
	{
	 List lResults = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),GET_CLOSED_POSITION_RESULTS_);
        
        SQLQuery q = session.createSQLQuery(sql);
        q.setCacheable(false);
      //  q.addEntity("IBTrader_Realtime", RealtimeImpl.class);
        q.addScalar("OPERACIONES", com.liferay.portal.kernel.dao.orm.Type.LONG);	   
        q.addScalar("MARGENBENEFICIO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
        q.addScalar("BENEFICIO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
	    q.addScalar("INVERTIDO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);	   
	    q.addScalar("TIPO", com.liferay.portal.kernel.dao.orm.Type.STRING);
	    
        	
         
        QueryPos qPos = QueryPos.getInstance(q);	    
        qPos.add(from);
        qPos.add(to);
        qPos.add(companyId);
        qPos.add(groupId);      
        qPos.add(positionMode);
        qPos.add(backtestingId);
        
        lResults = (List) QueryUtil.list(q, getDialect(), 0, 100);
        if (!lResults.isEmpty())
    		return lResults;
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public List getPositionOpenResults( Date to,long groupId, long companyId, String positionMode)
	{
	 List lResults = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),GET_OPENED_POSITION_RESULTS_);
        
        SQLQuery q = session.createSQLQuery(sql);
        q.setCacheable(false);
      //          	   
        q.addScalar("MARGENBENEFICIO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
        q.addScalar("BENEFICIO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);	 
        q.addScalar("LIQUIDO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
         
        QueryPos qPos = QueryPos.getInstance(q);	          
        qPos.add(to);
        qPos.add(companyId);
        qPos.add(groupId);     
        qPos.add(positionMode);
        
        _log.debug(q.toString());
        _log.debug(to);
        _log.debug(companyId);
        _log.debug(groupId);
        _log.debug(positionMode);
        
        
        
        lResults = (List) QueryUtil.list(q, getDialect(), 0, 100);
        if (!lResults.isEmpty())
    		return lResults;
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public List getCurrentLiquidTraded( long groupId, long companyId, String positionMode)
	{
	 List lResults = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),GET_CURRENTAMOUNT_TRADED);
        
        SQLQuery q = session.createSQLQuery(sql);
        q.setCacheable(false);
      //          	         
        q.addScalar("LIQUIDO", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
         
        QueryPos qPos = QueryPos.getInstance(q);	          
      
        qPos.add(companyId);
        qPos.add(groupId);     
        qPos.add(positionMode);
        
        _log.debug(q.toString());
    
        _log.debug(companyId);
        _log.debug(groupId);
        _log.debug(positionMode);
        
        
        
        lResults = (List) QueryUtil.list(q, getDialect(), 0, 100);
        if (!lResults.isEmpty())
    		return lResults;
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	 
		
	@SuppressWarnings("unchecked")
	public List  getDayTradingPatternPositions(Date from , Date to, long groupId, long companyId, String positionMode, String positionState)
	{
	 List lResults = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),GET_DAYTRADER_PATTERN_POSITIONS);
        
        SQLQuery q = session.createSQLQuery(sql);
        q.setCacheable(false);
        q.addScalar("totaloperaciones", com.liferay.portal.kernel.dao.orm.Type.LONG);	    
        
        QueryPos qPos = QueryPos.getInstance(q);	          
        qPos.add(to);
        qPos.add(from);
        qPos.add(positionMode);
        qPos.add(positionState.toString());
        qPos.add(companyId);
        qPos.add(groupId);     
    
        
        _log.debug(q.toString());
        _log.debug(to);        
        _log.debug(from);
        _log.debug(companyId);
        _log.debug(groupId);
        _log.debug(positionMode);
        _log.debug(positionState.toString());
        
        
        
        lResults = (List) QueryUtil.list(q, getDialect(), 0, 100);
        if (!lResults.isEmpty())
    		return lResults;
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
		public static final String GET_OPENED_POSITION_RESULTS_ = PositionFinder.class.getName() + ".getPositionOpenResults";
		public static final String GET_CLOSED_POSITION_RESULTS_ = PositionFinder.class.getName() + ".getPositionClosedResults";
		public static final String GET_INTRADIA_POSITIONS_ = PositionFinder.class.getName() + ".getIntradiaPositions";
		public static final String GET_DAYTRADER_PATTERN_POSITIONS = PositionFinder.class.getName() + ".getDayTradingPatternPositions";		
		public static final String GET_CURRENTAMOUNT_TRADED = PositionFinder.class.getName() + ".getCurrentLiquidTraded";
		
		
}
