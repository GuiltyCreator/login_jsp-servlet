<%--
  Created by IntelliJ IDEA.
  User: a7279
  Date: 2019/7/26
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
    <style>
        h2{
            color: dimgray;
            font-size: 30px;
        }
    </style>
</head>
<body>
<%
    String rememberMe = (String)session.getAttribute("rememberMe");
    String loginUser = (String) session.getAttribute("username");
    String loginPassword = (String) session.getAttribute("password");
    /*String loginUser=request.getParameter("username");
    String loginPassword=request.getParameter("password");*/
    System.out.println(loginUser);
%>

<h2>登录账号：<%=loginUser%></h2>
<h2>登录密码：<%=loginPassword%></h2>
<h2>是否记住密码？<%=rememberMe%></h2>


</body>
</html>
