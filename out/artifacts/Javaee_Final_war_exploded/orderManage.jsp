<%@ page import="java.util.List" %>
<%@ page import="Model.SaleRecord" %>
<%@ page import="Database.SaleRecordDataController" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="Model.Product" %>
<%@ page import="Database.ProductDataController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>orderManage</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/orderManage.css" rel="stylesheet">
</head>

<body>
<%
    if (session.getAttribute("CurrentUser") == null) {
        response.sendRedirect("/login.jsp");
        return;
    }
%>
<div class="container">
    <div class="row">
        <div class="page-header">
            <h1>订单管理 <small>增加&修改订单</small>
                <a href="index.jsp"><button type="button" class="btn btn-primary" style="float: right;">返回主页</button></a>
                <button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary"
                        style="float: right; margin-right: 10px;">新增订单
                </button>

            </h1>
        </div>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>订单编号</th>
                    <th>商品名称</th>
                    <th>商品编号</th>
                    <th>数量</th>
                    <th>总价格</th>
                    <th>时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="tableBody">
                <%
                    try {
                        SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                        List<SaleRecord> saleRecords = saleRecordDataController.queryAll();
                        saleRecordDataController.closeConnection();

                        ProductDataController productDataController = new ProductDataController();
                        List<Product> products = productDataController.queryAll();
                        productDataController.closeConnection();
                        productDataController = null;
                        // session.setAttribute("Products", products);
                        pageContext.setAttribute("Products", products);

                        for (SaleRecord saleRecord : saleRecords) {

                            productDataController = new ProductDataController();
                            Product product = productDataController.queryByID(saleRecord.getProductID());
                            productDataController.closeConnection();
                %>

                <form action="/saleRecord/update" method="post">
                    <tr>
                        <td><%=saleRecord.getSaleRecordID()%>
                            <input hidden name="saleRecordID" value="<%=saleRecord.getSaleRecordID()%>">
                        </td>
                        <td><%=product.getProductName()%>
                        <td>
                            <select name="productID" style="width: 90%;" class="form-control" required>
                                <%
                                    for (Product product2 : products) {
                                        out.println("<option value=\"" + product2.getProductID() + "\">" + product2.getProductID() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                        </td>
                        <td><input name="productNum" style="width: 20%" value="<%=saleRecord.getProductNum()%>"
                                   class="form-control" required>
                        </td>
                        <td><%=saleRecord.getTotalPrice()%>
                        </td>
                        <td><%=saleRecord.getDate()%>
                            <input hidden name="date" value="<%=saleRecord.getDate()%>">
                        </td>
                        <td>
                            <ul style="padding-left: 0;">
                                <li style="color: royalblue;list-style: none;text-decoration: none;cursor: pointer">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                    <button type="submit"
                                            style="padding-left: 5px;color: royalblue;text-decoration: none;"
                                            class="btn btn-link">修改
                                    </button>
                                </li>
                                <li style="color: red;list-style: none;text-decoration: none;cursor: pointer;">
                                    <span class="glyphicon glyphicon-trash"></span>
                                    <a href="${pageContext.request.contextPath}/saleRecord/delete?saleRecordID=<%=saleRecord.getSaleRecordID()%>">
                                        <button type="button"
                                                style="color: red;padding-left: 5px;text-decoration: none;"
                                                class="btn btn-link">取消订单
                                        </button>
                                    </a>
                                </li>

                            </ul>
                        </td>
                    </tr>
                </form>


                <%

                        }

                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    } catch (NamingException namingException) {
                        namingException.printStackTrace();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (InstantiationException instantiationException) {
                        instantiationException.printStackTrace();
                    }

                %>

                </tbody>
            </table>

            <script>
                <%
                    if (session.getAttribute("Code") != null) {
                        %>
                        alert("<%=session.getAttribute("Message").toString()%>");
                        <%
                        session.removeAttribute("Code");
                        session.removeAttribute("Message");
                    }
                 %>
            </script>

            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" style="top: 20vh;" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">
                                <!--增加-->新增订单</h4>
                        </div>
                        <div class="modal-body">
                            <form id="editForm"
                                  action="/saleRecord/add" method="post">
                                <%--                                这个东西循环出来--%>
                                <div class="form-group table-responsive" style="display: inline-block;width: 100%;">

                                    <table class="table table-striped">
                                        <thead>
                                        <th>商品</th>
                                        <th>数量</th>
<%--                                        <th>价格</th>--%>
                                        </thead>
                                        <tbody>
                                        <td>
                                            <select onchange='commodityChangeHandler()' type="text"
                                            <%--                                                    style="width: 40%;float: left;transform: translateY(6px);"--%>
                                                    class="form-control" id="exampleInputEmail1" placeholder="Name"
                                                    name="productID" required>
                                                <%
                                                    List<Product> products = (List<Product>) pageContext.getAttribute("Products");
                                                    for (Product product : products) {
                                                        out.println("<option value=\"" + product.getProductID() + "\" price=\"" + product.getProductPrice() + "\">" + product.getProductName() + "</option>");
                                                    }
                                                %>

                                            </select>
                                        </td>
                                        <td>
                                            <input value="0" style="width: 50%;" class="form-control" name="productNum" required>
                                        </td>
<%--                                        <td>--%>
<%--                                            <span style="line-height: 34px;padding-left: 5%;font-size: 18px;display: inline-block;width: 20%;">20￥</span>--%>
<%--                                        </td>--%>

                                        </tbody>
                                    </table>


                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" form="editForm" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js "></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js "></script>
<script src="./js/libs/anime.min.js "></script>
<script src="./js/orderManage.js "></script>
</body>

</html>