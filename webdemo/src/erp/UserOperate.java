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
import common.AES;
import common.DBHelper;
import erp.Brand;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class UserOperate extends HttpServlet {
	
	private DBHelper db=null;

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
		
		if(brand.getRequestType().equals("select"))
		{
			 QueryResponse(response,mySqlQuery(brand));
		}else{
			OperatelResponse( response,mySqOperatel(brand));
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
	
	public int mySqOperatel(Brand brand)
	{
		int result = -1;
		String name= brand.getName();
		String note= brand.getNote();
		String original=brand.getOriginal();
		String sql = null;
		
	     try {
	    	 if(brand.getRequestType().equals("insert"))
	    	 {
	    		 sql="insert into brand(name,note) values ("+"'"+name+"','"+note+"'"+")";
	    	 }else if(brand.getRequestType().equals("update"))
	    	 {
	    		 sql="update brand set name='"+name+"' where name = '"+original+"' and note = '"+note+"' ";
	    	 }else{
	    		 sql="delete from brand  where name = '"+name+"' ";
	    	 }
	    	 
	    
	    	 
		     DBHelper db = new DBHelper(sql);  
			result = db.pst.executeUpdate();
			 db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return result;
	    
	}
	
	public ResultSet mySqlQuery(Brand brand)
	{  
		ResultSet result =null;
		String name= brand.getName();
		 String sql="select * from brand where name like '%"+name+"%' ";
	     db = new DBHelper(sql);  
	     try {
	    	
			result = db.pst.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	     return result;
	    
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void QueryResponse(HttpServletResponse response,ResultSet result )
	{
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			ResultSet rs=result;
			ArrayList<Brand>  userList=new ArrayList<Brand> ();
		   
		   while(rs.next())
			{
			   Brand  brand=new Brand();
			   brand.setName(rs.getString("name"));
			   userList.add(brand);
			}
		  db.close();
		 
		   JSONArray array=JSONArray.fromObject(userList);
			out.write(array.toString());
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
