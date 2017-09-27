package erp;

import hibernate.HibernateSessionFactory;
import hibernate.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import common.DBHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserSelectAll extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Session session=HibernateSessionFactory.getSession();
		 Criteria crit=session.createCriteria(Unit.class);
		 Transaction tx=session.beginTransaction();
		 List<Unit> unitList=crit.list();
		 tx.commit();
		JSONArray array=JSONArray.fromObject(unitList);
		
		out.write(array.toString());


		
		session.close();
		
	}

}
