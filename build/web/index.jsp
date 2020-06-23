<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>$Title$</title>
    </head>
    <body>
        <%
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                //new DBCP....
                Context ctx = new InitialContext();
                //通过JNDI查找DataSource
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/yhtSaleSystem");
                conn = ds.getConnection();
                pstmt = conn.prepareStatement("select * from user");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    do {
                        out.print(rs.getString("username"));
                    } while (rs.next());

                } else {
                    out.print("No results from query");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    //将Connection放到连接池中
                    conn.close();
                }
            }
        %>
    </body>
</html>
