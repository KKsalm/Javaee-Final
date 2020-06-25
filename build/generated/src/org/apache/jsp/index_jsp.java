package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.InitialContext;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.Context;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>$Title$</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
