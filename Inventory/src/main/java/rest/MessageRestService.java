package rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService {

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		Statement stmt=conn.createStatement();  
		ResultSet rs=stmt.executeQuery("select username from login");  
		String result = null;
		if (rs.next()) {
			result = "Restful example : " + msg + rs.getString(1);
			System.out.println( msg + rs.getString(1));

	    }
		conn.close();

		return Response.status(200).entity(result).build();

	}

}