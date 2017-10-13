<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@page import="java.text.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>显示服务器的当前时间</title>
</head>
<body>
现在服务器的时间是：
<font color="red">
<%
SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
out.println(format.format(new Date()));

 %>



</font>
</body>
</html>