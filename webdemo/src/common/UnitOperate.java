package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.AES;
import common.DBHelper;
import erp.Brand;
import erp.ResultData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import hibernate.*;
public class UnitOperate extends HttpServlet {
	
	 Session session=null;

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
		
		
		Brand brand=userRequest(request);
		try {
		if(brand.getRequestType().equals("select"))
		{
			 QueryResponse(response,mySqlQuery(brand));
		}else{
			
				OperatelResponse( response,mySqOperatel(brand));
			
		      }
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				
	
	}
	
	public Brand userRequest(HttpServletRequest request)
	{
		StringBuffer jb=new StringBuffer();
		String line=null;
		Brand  brand=new Brand();
		
		BufferedReader reader;
		try {
			reader = request.getReader();
			while((line=reader.readLine())!=null)
			{
				jb.append(line);
			}
			 
			JSONObject jon=JSONObject.fromObject(AES.decode(jb.toString()));
			brand=(Brand)JSONObject.toBean(jon,Brand.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return brand;
		
	}
	
	public int mySqOperatel(Brand brand) throws SQLException
	{
		int result = -1;
		String name= brand.getName();
		String note= brand.getNote();
		int  id=brand.getUnitId();
		 session= HibernateSessionFactory.getSession();
		
	     if(brand.getRequestType().equals("insert"))
		 {   
	    	 try{
	    	 Unit unit=new Unit();
		     unit.setName(name);
		     unit.setNote(note);
			 Transaction tx=session.beginTransaction();
			 session.saveOrUpdate(unit);
			 tx.commit();
			 
			 result=unit.getUnitId();
	    	 }catch(RuntimeException re)
	    	 {
	    		 result=0;
	    		 
	    	 }
		 }else if(brand.getRequestType().equals("update"))
		 {
			 try{
				 Unit unit = (Unit)session.get(Unit.class, id);
				 Transaction tx=session.beginTransaction();
				 unit.setName(name);
				 unit.setNote(note);
				 tx.commit();
				 result=1;
		    	 }catch(RuntimeException re)
		    	 {
		    		 result=0;
		    		 
		    	 }
		 }else{
			 
			 try{
				 Unit unit = (Unit)session.get(Unit.class, id);
				 Transaction tx=session.beginTransaction();
				 if(unit!= null ){  
                     session.delete(unit);  
                   }  
				 tx.commit();
				 result=1;
		    	 }catch(RuntimeException re)
		    	 {
		    		 result=0;
		    		 
		    	 }
			 
		 }
	     
	     return result;
	    
	}
	
	public List<Unit> mySqlQuery(Brand brand)
	{  
		 session= HibernateSessionFactory.getSession();
		 Criteria crit=session.createCriteria(Unit.class);
		 List<Unit> unitList=null;
		 try{
		 Transaction tx=session.beginTransaction();
		 crit.add(Restrictions.like("name", "%"+brand.getName()+"%"));
		 unitList=crit.list();
		 tx.commit();
		 } catch (RuntimeException re) {  
	         
	           
	        }  
	     return unitList;
	    
	}
	
	public void OperatelResponse(HttpServletResponse response,int result )
	{
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			 ResultData resultdata=new ResultData();
			   resultdata.setResult(result);
			   JSONObject jsonobj=JSONObject.fromObject(resultdata);
			    out.write(jsonobj.toString());
				session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void QueryResponse(HttpServletResponse response,List<Unit> result )
	{
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
		 
		   JSONArray array=JSONArray.fromObject(result);
			out.write(array.toString());
			session.close();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
