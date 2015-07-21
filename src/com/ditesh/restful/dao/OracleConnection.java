package com.ditesh.restful.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleConnection {

	private static DataSource OracleDataSource = null;
	private static Context context = null;
	
	public static DataSource OracleDataSource() throws Exception{
		if(OracleDataSource != null){
			return OracleDataSource;
		}
		try{
			if(context == null){
				context = new InitialContext();
			}
			OracleDataSource = (DataSource) context.lookup("java:comp/env/jdbc/rest");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return OracleDataSource;
	}
}
