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
import erp.GetUserData;
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
		
		
		GetUserData getUserData=userRequest(request);
		
		if(getUserData.getRequestType().equals("select"))
		{
			 QueryResponse(response,mySqlQuery(getUserData));
		}else{
			OperatelResponse( response,mySqOperatel(getUserData));
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
	
	public int mySqOperatel(GetUserData getUserData)
	{
		int result = -1;
		String name= getUserData.getName();
		String note= getUserData.getNote();
		String original=getUserData.getOriginal();
		String sql = null;
		
	     try {
	    	 if(getUserData.getRequestType().equals("insert"))
	    	 {
	    		 sql="insert into brand(name,note) values ("+"'"+name+"','"+note+"'"+")";
	    	 }else if(getUserData.getRequestType().equals("update"))
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
	
	public ResultSet mySqlQuery(GetUserData getUserData)
	{  
		ResultSet result =null;
		String name= getUserData.getName();
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
			 ReturnUserData resultdata=new ReturnUserData();
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
			ArrayList<GetUserData>  userList=new ArrayList<GetUserData> ();
		   
		   while(rs.next())
			{
			   GetUserData  getUserData=new GetUserData();
			   getUserData.setName(rs.getString("name"));
			   userList.add(getUserData);
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