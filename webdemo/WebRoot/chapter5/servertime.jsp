<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@page import="java.text.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��ʾ�������ĵ�ǰʱ��</title>
</head>
<body>
���ڷ�������ʱ���ǣ�
<font color="red">
<%
SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
out.println(format.format(new Date()));

 %>



</font>
</body>
</html>