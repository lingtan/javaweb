package erp;

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
		
		
		GetUserData getUserData=userRequest(request);
		try {
			 switch(getUserData.getClassType())
			 {
			 case 1:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new Brand()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new Brand()));
						
					      }
		    break;
		    
			 case 2:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new ProductCategory()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new ProductCategory()));
						
					      }
		    break;
		    
			 case 3:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new Unit()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new Unit()));
						
					      }
		    break;
		    
			 case 4:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new Stock()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new Stock()));
						
					      }
		    break;
		    
			 case 5:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new Employee()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new Employee()));
						
					      }
		    break;
		    
			 case 6:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new Consignment()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new Consignment()));
						
					      }
		    break;
			 case 7:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new  SupplierCategory()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new  SupplierCategory()));
						
					      }
		    break;
			 case 8:
				 if(getUserData.getRequestType().equals("select"))
					{   
						 QueryResponse(response,mySqlQuery(getUserData,new  CustomCategory()));
					}else{
						
							OperatelResponse( response,mySqOperatel(getUserData,new  CustomCategory()));
						
					      }
		    break;
				 
				 
				 
			
				 
			 
			 }
		
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				
	
	}
	
	public GetUserData userRequest(HttpServletRequest request)
	{
		StringBuffer jb=new StringBuffer();
		String line=null;
		GetUserData  getUserData=new GetUserData();
		
		BufferedReader reader;
		try {
			reader = request.getReader();
			while((line=reader.readLine())!=null)
			{
				jb.append(line);
			}
			 
			JSONObject jon=JSONObject.fromObject(AES.decode(jb.toString()));
			getUserData=(GetUserData)JSONObject.toBean(jon,GetUserData.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getUserData;
		
	}
	
	public int mySqOperatel(GetUserData getUserData,BaseData db) throws SQLException
	{ 
		int result = -1;
		String name= getUserData.getName();
		String note= getUserData.getNote();
		int  id=getUserData.getUnitId();
		 session= HibernateSessionFactory.getSession();
		
	     if(getUserData.getRequestType().equals("insert"))
		 {   
	    	 try{
	    	
	    	 db.setName(name);
	    	 db.setNote(note);
			 Transaction tx=session.beginTransaction();
			 session.saveOrUpdate(db);
			 tx.commit();
			 
			 result=db.getUnitId();
	    	 }catch(RuntimeException re)
	    	 {
	    		 result=0;
	    		 
	    	 }
		 }else if(getUserData.getRequestType().equals("update"))
		 {
			 try{
				 
				
				db= (BaseData) session.get(db.getClass(), id);
				 Transaction tx=session.beginTransaction();
				db.setName(name);
				 db.setNote(note);
				 tx.commit();
				 result=1;
		    	 }catch(RuntimeException re)
		    	 {
		    		 result=0;
		    		 
		    	 }
		 }else{
			 
			 try{
				 db = (BaseData)session.get(db.getClass(), id);
				 Transaction tx=session.beginTransaction();
				 if(db!= null ){  
                     session.delete(db);  
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
	
	public Object mySqlQuery(GetUserData getUserData,BaseData db)
	{  
		 session= HibernateSessionFactory.getSession();
		 Criteria crit=session.createCriteria(db.getClass());
		 List<BaseData> unitList=null;
		 try{
		 Transaction tx=session.beginTransaction();
		 crit.add(Restrictions.like("name", "%"+getUserData.getName()+"%"));
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
			 ReturnUserData resultdata=new ReturnUserData();
			   resultdata.setResult(result);
			   JSONObject jsonobj=JSONObject.fromObject(resultdata);
			    out.write(jsonobj.toString());
				session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void QueryResponse(HttpServletResponse response,Object result )
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
