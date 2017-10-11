package vn.sogo.lmscms.helpers;

import java.sql.*;

import java.util.List;

//import javax.persistence.ParameterMode;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.procedure.ParameterRegistration;
//import org.hibernate.procedure.ProcedureCall;
//import org.hibernate.procedure.ProcedureOutputs;
//import org.hibernate.result.ResultSetOutput;
//import org.springframework.beans.factory.annotation.Autowired;

public class MySqlHelper {
//	@Autowired
//	SessionFactory sessionFactory;
	
	private String jcdbDriver = "";
	private String dbUrl = "";
	private String dbUser = "";
	private String dbPass = "";
	
	public void setJcdbDriver(String jcdbDriver) {
		this.jcdbDriver = jcdbDriver;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public <T> List<T> ExecuteStoreProc(String storeName, Class<T> resultClasses) throws Exception {
		return ExecuteStoreProc(storeName, new Object[]{}, resultClasses);
	}
	
	public <T> List<T> ExecuteStoreProc(String storeName, Object[] params, Class<T> resultClasses) throws Exception {
		List<T> result = null;
		
		Connection conn = null;
		
		try{
			CallableStatement stmt = null;
			Class.forName(jcdbDriver);
			
			
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sqlParamText = "";
						
			for (int i = 0; i < params.length; i++) {
				sqlParamText += sqlParamText.isEmpty() ? "?" : ", ?";
			}
			
			
			String sql = String.format("{call %s (%s)}", storeName, sqlParamText);
			stmt = conn.prepareCall(sql);
			
			Integer index = 1;
			for(Object param : params){
				stmt.setObject(index, param);
				index++;
			}
			
			stmt.execute();
			
			ResultSet resultSet = stmt.getResultSet();
			
			ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
			result = resultSetMapper.mapRersultSetToListObject(resultSet, resultClasses);
			
			conn.close();
			
		}
		catch(Exception ex){
			if(conn != null && conn.isClosed()){
				conn.close();
			}
			throw ex;
		}
		
		return result;
	}
	
//	@SuppressWarnings("unchecked")
//	public <T> List<T> ExecuteStoreProc(String storeName, Object[] params, Class<T> resultClasses) {
//		List<T> result = null;
//		Session currentSession = sessionFactory.getCurrentSession();
//		Transaction transaction = currentSession.beginTransaction();
//		try {
//			ProcedureCall procedureCall = currentSession.createStoredProcedureCall(storeName, resultClasses);
//	
//			int index = 0;
//			for (Object param : params) {
//				if(param == null){
//					procedureCall.registerParameter(index + 1, Integer.class, ParameterMode.IN).bindValue(0);
//				}
//				else{
//					procedureCall.registerParameter(index + 1, GetParamClass(param), ParameterMode.IN).bindValue(param);	
//				}
//				index++;
//			}
//			ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
//			ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
//			result = resultSetOutput.getResultList();
//		}
//		catch(Exception ex){
//			transaction.rollback();
//			throw ex;
//		}
//		transaction.commit();
//		return result;
//	}
	
	public <T> T ExecuteStoreProcSingleResult(String storeName, Class<T> resultClasses) throws Exception {
		return ExecuteStoreProcSingleResult(storeName, new Object[]{}, resultClasses);
	}

//	@SuppressWarnings("unchecked")
//	public <T> T ExecuteStoreProcSingleResult(String storeName, Object[] params, Class<T> resultClasses) {
//		T result = null;
//		Session currentSession = sessionFactory.getCurrentSession();
//		Transaction transaction = currentSession.beginTransaction();
//		try{
//	
//			ProcedureCall procedureCall = currentSession.createStoredProcedureCall(storeName, resultClasses);
//		
//			
//			int index = 0;
//			for (Object param : params) {
//				//ParameterRegistration<Object> obj = procedureCall.registerParameter(index + 1, GetParamClass(param), ParameterMode.IN); 
//				if(param == null){
//					procedureCall.registerParameter(index + 1, Integer.class, ParameterMode.IN).bindValue(0);
//				}
//				else{
//					procedureCall.registerParameter(index + 1, GetParamClass(param), ParameterMode.IN).bindValue(param);
//				}
////				obj.enablePassingNulls(true);
////				obj.bindValue(param);
////				procedureCall.getParameterRegistration(index + 1).enablePassingNulls(true);
//				index++;
//			}
//			
//			ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
//			ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
//			result = (T) resultSetOutput.getSingleResult();
//		}
//		catch(Exception ex){
//			transaction.rollback();
//			throw ex;
//		}
//		transaction.commit();
//		return result;
//	}
	
	public <T> T ExecuteStoreProcSingleResult(String storeName, Object[] params, Class<T> resultClasses) throws Exception {
		T result = null;
		Connection conn = null;
		
		try{
			
			CallableStatement stmt = null;
			Class.forName(jcdbDriver);
	
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sqlParamText = "";
						
			for (int i = 0; i < params.length; i++) {
				sqlParamText += sqlParamText.isEmpty() ? "?" : ", ?";
			}
			
			
			String sql = String.format("{call %s (%s)}", storeName, sqlParamText);
			stmt = conn.prepareCall(sql);
			
			Integer index = 1;
			for(Object param : params){
				stmt.setObject(index, param);
				index++;
			}
			
			stmt.execute();
			
			ResultSet resultSet = stmt.getResultSet();
			
			ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
			result = resultSetMapper.mapRersultSetToObject(resultSet, resultClasses);
			
			conn.close();
		}
		catch(Exception ex){
			if(conn != null && conn.isClosed()){
				conn.close();
			}
			throw ex;
		}
		
		return result;
	}

//	@SuppressWarnings("rawtypes")
//	private Class GetParamClass(Object o) {
//		Class res = Object.class;
//		if (o instanceof Integer) {
//			res = Integer.class;
//		} else if (o instanceof String) {
//			res = String.class;
//		} else if (o instanceof Boolean) {
//			res = Boolean.class;
//		} else if (o instanceof Double) {
//			res = Double.class;
//		} else if (o instanceof Float) {
//			res = Float.class;
//		}
//		else if(o instanceof Timestamp){
//			res = Timestamp.class;
//		}
//
//		return res;
//	}
}
