package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqlOperate extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
	    	 
	    	String	 sql=" alter table  webdb.stock add selectImage int(20)";
	    	String	 sqle="CREATE TABLE IF NOT EXISTS webdb.employee( id int(20) not null AUTO_INCREMENT,name varchar(300) not null,selectImage int(20),note varchar(300),PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=UTF8";
	    	String	 sqlc=" CREATE TABLE IF NOT EXISTS webdb.customcategory( id int(20) not null AUTO_INCREMENT,name varchar(300) not null,selectImage int(20),note varchar(300),PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=UTF8";
	    	
	    	
	    
	    	 
		    DBHelper db = new DBHelper(sql);  
		    DBHelper dbe = new DBHelper(sqle);  
		    DBHelper dbc = new DBHelper(sqlc);  
			db.pst.executeUpdate();
			dbe.pst.executeUpdate();
			dbc.pst.executeUpdate();
			 db.close();
			 dbe.close();
			 dbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
