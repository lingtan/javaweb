package erp;

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

import Tools.AES;
import Tools.DBHelper;
import erp.GetUserData;
import erp.ReturnUserData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import hibernate.*;

public class BrandOperate extends HttpServlet {

	Session session = null;

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GetUserData getUserData = userRequest(request);
		try {
			switch (getUserData.getClassType()) {
			case 1:
				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,
							mySqlQuery(getUserData, new Brand()));
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData, new Brand()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData, new Brand()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new Brand()));
				}
				break;

			case 2:
				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new ProductCategory()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData, new ProductCategory()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new ProductCategory()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new ProductCategory()));
				}
				
				break;

			case 3:
				
				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new Unit()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData, new Unit()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new Unit()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new Unit()));
				}
				
			
				break;

			case 4:
				

				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new Stock()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData, new Stock()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new Stock()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new Stock()));
				}
				
		
				break;

			case 5:
				
				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new Employee()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData, new Employee()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new Employee()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new Employee()));
				}
				
				break;

			case 6:
				

				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new Consignment()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData,new Consignment()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new Consignment()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new Consignment()));
				}
				
			
				break;
			case 7:
				
				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new SupplierCategory()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData,new SupplierCategory()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new SupplierCategory()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new SupplierCategory()));
				}
				
			
				break;
			case 8:
				
				if (getUserData.getRequestType().equals("select")) {
					QueryResponse(response,mySqlQuery(getUserData, new CustomCategory()));
					
				} else if (getUserData.getRequestType().equals("insert")) {

					OperatelResponse(response,insertOperatel(getUserData,new CustomCategory()));

				}else if(getUserData.getRequestType().equals("update"))
				{
					OperatelResponse(response,updateOperatel(getUserData,new CustomCategory()));
				}else if(getUserData.getRequestType().equals("delete"))
				{
					OperatelResponse(response,deleteOperatel(getUserData, new CustomCategory()));
				}
				
			
				break;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public GetUserData userRequest(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		GetUserData getUserData = new GetUserData();

		BufferedReader reader;
		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}

			JSONObject jon = JSONObject.fromObject(AES.decode(jb.toString()));
			getUserData = (GetUserData) JSONObject.toBean(jon,
					GetUserData.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getUserData;

	}

	public int insertOperatel(GetUserData getUserData, BaseData db)
			throws SQLException {
		int result = -1;
		String name = getUserData.getName();
		String note = getUserData.getNote();
		List<BaseData> baseDataList = null;
		session = HibernateSessionFactory.getSession();
		Criteria crit = session.createCriteria(db.getClass());

		try {

			Transaction tx = session.beginTransaction();
			crit.add(Restrictions.like("name", getUserData.getName()));
			baseDataList = crit.list();
			tx.commit();
			if (baseDataList.size() <= 0) {
				Transaction txi = session.beginTransaction();
				db.setName(name);
				db.setNote(note);
				session.saveOrUpdate(db);
				result = db.getUnitId();
				txi.commit();
			} else {
				result = 0;
			}

			result = db.getUnitId();
		} catch (RuntimeException re) {

		}

		return result;

	}

	
	public int updateOperatel(GetUserData getUserData, BaseData db)
	{
		int result = -1;
		String name = getUserData.getName();
		String note = getUserData.getNote();
		int id = getUserData.getUnitId();
		session = HibernateSessionFactory.getSession();
		
		try {

			db = (BaseData) session.get(db.getClass(), id);
			Transaction tx = session.beginTransaction();
			db.setName(name);
			db.setNote(note);
			tx.commit();
			result = db.getUnitId();
		} catch (RuntimeException re) {
			

		}
		
		return result;
		
	}
	
	public int deleteOperatel(GetUserData getUserData, BaseData db)
	{
		int result = -1;
		int id = getUserData.getUnitId();
		session = HibernateSessionFactory.getSession();
		Criteria crit = session.createCriteria(Product.class);
		List<ProductBean> productList = null;
		try {
			
			Transaction tx = session.beginTransaction();
			crit.add(Restrictions.like("category_name",  getUserData.getName()
					));
			productList=crit.list();
			tx.commit();
			
			if(productList.size()<=0)
			{

				db = (BaseData) session.get(db.getClass(), id);
				Transaction txd = session.beginTransaction();
				if (db != null) {
					session.delete(db);
				}
				txd.commit();
				result = 1;
			}else
			{
				result=-2;
			}
			
		} catch (RuntimeException re) {
			
			
		}
		
		return result;

	}
	
	public Object mySqlQuery(GetUserData getUserData, BaseData db) {
		session = HibernateSessionFactory.getSession();
		Criteria crit = session.createCriteria(db.getClass());
		List<BaseData> unitList = null;
		try {
			Transaction tx = session.beginTransaction();
			crit.add(Restrictions.like("name", "%" + getUserData.getName()
					+ "%"));
			unitList = crit.list();
			tx.commit();
		} catch (RuntimeException re) {

		}
		return unitList;

	}

	public void OperatelResponse(HttpServletResponse response, int result) {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			ReturnUserData resultdata = new ReturnUserData();
			resultdata.setResult(result);
			JSONObject jsonobj = JSONObject.fromObject(resultdata);
			out.write(jsonobj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void QueryResponse(HttpServletResponse response, Object result) {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();

			JSONArray array = JSONArray.fromObject(result);
			out.write(array.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
