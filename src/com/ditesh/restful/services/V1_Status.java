package com.ditesh.restful.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ditesh.restful.dao.OracleConnection;

@Path("/v1/status")
public class V1_Status {

	private static final String api_version = "00.01.00";
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Rest Web Services</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version:</p>"+api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		Connection conn = null;
		String returnString = null;
		try {
			conn = OracleConnection.OracleDataSource().getConnection();
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}

			query.close();

			returnString = "<p>Database Status</p><p>Database Data/Time return:"
					+ myString + "</p>";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return returnString;
	}
}
