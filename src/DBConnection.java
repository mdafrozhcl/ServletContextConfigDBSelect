

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBConnection
 */
public class DBConnection extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ServletContext context = getServletContext();
		String driver = context.getInitParameter("db_driver");
		String connection_url = context.getInitParameter("db_connection");
		String user=context.getInitParameter("username");
		String pwd=context.getInitParameter("password");
		try{
			Class.forName(driver);
			Connection con= DriverManager.getConnection(connection_url,user,pwd);
			PreparedStatement stmt = con.prepareStatement("Select * from employee");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				out.print("Emp Name: "+rs.getString(1)+" Emp lastname: "+rs.getString(2)+" DoB: "+rs.getDate(3)+" Role: "+rs.getString(4)+"<br>");
			}
			
		}catch(Exception e){
			out.print(e);
		}
		
	}


}
