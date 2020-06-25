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
            int storeID = 2;
            try {
                //new DBCP....
                Context ctx = new InitialContext();
                //通过JNDI查找DataSource
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/yhtSaleSystem");
                conn = ds.getConnection();
                pstmt = conn.prepareStatement("INSERT INTO dailysaleamount(date,amount,storeID)"
                        + "SELECT date, sum(totalPrice)amount, storeID "
                        + "FROM salerecord WHERE storeID = '" + storeID
                        + "'group by year(date), month(date), day(date);");

                pstmt.executeUpdate();
                out.print("No results from query");
//                if (rs.next()) {
//                    do {
//                        out.print(rs.getString("yy") + "-");
//                        out.print(rs.getString("mm") + "-");
//                        out.print(rs.getString("dd") + "-");
//                        out.print(rs.getString("销售额") + "\n");
//                    } while (rs.next());
//
//                } else {
//                    out.print("No results from query");
//                }

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