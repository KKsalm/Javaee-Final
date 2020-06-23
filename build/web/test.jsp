<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <%
            out.print("MySQL 数据源测试开始..." + "<br/>");
            DataSource ds = null;
            try {
                InitialContext ctx = new InitialContext();
                ds = (DataSource) ctx.lookup("java:comp/env/jdbc/yhtSaleSystem");
                Connection conn = ds.getConnection();
                conn.close();
                out.print("MySQL 数据源测试成功！");
            } catch (Exception ex) {
                out.print("出现意外，信息是:" + ex.getMessage());
                ex.printStackTrace();
            }
        %>
    </body>
</html>