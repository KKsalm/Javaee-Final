<%@ page import="Database.MaterialDataController" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Material" %>
<%@ page import="Database.PurchaseDataController" %>
<%@ page import="Model.PurchaseRecord" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>进货管理</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/getCommodity.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="page-header" style="display: block;">
            <h1>进货管理 <small>增加进货记录</small>
                <a href="/index.jsp"><button type="button" class="btn btn-primary" style="float: right;">返回主页</button></a>
                <button type="button" class="btn btn-primary" style="float: right; display: inline-block; margin-right: 10px;" data-toggle="modal" data-target="#myModal">新增进货记录</button>
                <button type="button" class="btn btn-primary" style="float: right; display: inline-block; margin-right: 10px;" data-toggle="modal" data-target="#myModal3">删除原料</button>
                <button type="button" class="btn btn-primary" style="float: right; display: inline-block; margin-right: 10px;" data-toggle="modal" data-target="#myModal2">新增原料</button>

            </h1>
        </div>
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <%
                if (session.getAttribute("CurrentUser") == null) {
                    response.sendRedirect("/login.jsp");
                    return;
                }
            %>

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

            <%


                try {
                    MaterialDataController materialDataController = new MaterialDataController();
                    List<Material> materials = materialDataController.queryAll();
                    materialDataController.closeConnection();
                    pageContext.setAttribute("Materials", materials);

                    PurchaseDataController purchaseDataController = new PurchaseDataController();
                    List<PurchaseRecord> purchaseRecords = purchaseDataController.queryAll();
                    purchaseDataController.closeConnection();
                    purchaseDataController = null;

                    List<Date> dateList = new ArrayList<>();
                    for (int i = purchaseRecords.size() - 1; i >= 0; i--) {
                        if (dateList.isEmpty()) {
                            dateList.add(purchaseRecords.get(i).getDate());
                        } else if (!purchaseRecords.get(i).getDate().equals(dateList.get(dateList.size() - 1))) {
                            dateList.add(purchaseRecords.get(i).getDate());
                        }
                    }

                    pageContext.setAttribute("PurchaseRecords", purchaseRecords);

                    for (Date date : dateList) {
                        float dayTotalPurchase = 0;
                        purchaseDataController = new PurchaseDataController();
                        List<PurchaseRecord> todayPurchaseRecords = purchaseDataController.queryByDate(date);
                        purchaseDataController.closeConnection();
                        for (PurchaseRecord purchaseRecord : todayPurchaseRecords) {
                            dayTotalPurchase += purchaseRecord.getTotalPrice();
                        }


            %>

            <div class="panel panel-default">
                <div class="panel-heading" style="overflow: hidden;" role="tab" id="heading<%=date%>>">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=date%>"
                           aria-expanded="true" aria-controls="collapseOne">
                            <%=date%>进货记录
                        </a>
<%--                        <button style="float: right;" data-toggle="modal" data-target="#myModal" type="button"--%>
<%--                                class="btn btn-link">新增原料记录--%>
<%--                        </button>--%>
                    </h4>
                </div>
                <div id="collapse<%=date%>" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading<%=date%>">
                    <div class="panel-body">
                        <h3>总价格: <%=dayTotalPurchase%>
                        </h3>

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>进货记录编号</th>
                                <th>原料名称</th>
                                <th>原料编号</th>
                                <th>数量</th>
                                <th>单价</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody class="tableBody">
                                <%

                                for (PurchaseRecord purchaseRecord : todayPurchaseRecords) {
                                    materialDataController = new MaterialDataController();
                                    Material material = materialDataController.queryByID(purchaseRecord.getMaterialID());
                                    materialDataController.closeConnection();

                            %>

                            <form action="/purchase/update" method="post">
                                <tr>
                                    <td><%=purchaseRecord.getPurchaseRecordID()%>
                                        <input hidden name="purchaseRecordID" value="<%=purchaseRecord.getPurchaseRecordID()%>">
                                    </td>
                                    <td style="width: 150px"><%=material.getMaterialName()%>
                                    </td>
                                    <td>
                                        <select  type="text" class="form-control" name="materialID" required>
                                        <%
                                            for ( Material material1 : materials) {
                                                out.print("<option value=\""+ material1.getMaterialID() +"\" >" + material1.getMaterialName() + "</option>");
                                            }
                                        %>
                                        </select>
                                    </td>
                                    <td>
                                        <input style="width: 30%" class="form-control" name="number" value="<%=purchaseRecord.getNumber()%>">
                                    </td>
                                    <td><%=material.getPrice()%>
                                    </td>
                                    <input hidden name="date" value="<%=date%>">
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
                                                <a href="/purchase/delete?purchaseRecordID=<%=purchaseRecord.getPurchaseRecordID()%>">
                                                    <button type="button"
                                                            style="color: #ff0000;padding-left: 5px;text-decoration: none;"
                                                            class="btn btn-link">删除记录
                                                    </button>
                                                </a>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>
                            </form>
                            <%
                                }
                            %>
                            </tbody>


                            <%--        <ul class="pagination">--%>
                            <%--            <li>--%>
                            <%--                <a href="#" aria-label="Previous">--%>
                            <%--                    <span aria-hidden="true">&laquo;</span>--%>
                            <%--                </a>--%>
                            <%--            </li>--%>
                            <%--            <li><a href="#">1</a></li>--%>
                            <%--            <li><a href="#">2</a></li>--%>
                            <%--            <li><a href="#">3</a></li>--%>
                            <%--            <li><a href="#">4</a></li>--%>
                            <%--            <li><a href="#">5</a></li>--%>
                            <%--            <li>--%>
                            <%--                <a href="#" aria-label="Next">--%>
                            <%--                    <span aria-hidden="true">&raquo;</span>--%>
                            <%--                </a>--%>
                            <%--            </li>--%>
                            <%--        </ul>--%>
                        </table>
                    </div>
                </div>
            </div>
            <%

                    }
                } catch (NamingException namingException) {
                    namingException.printStackTrace();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                }
            %>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" style="top: 20vh;" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增原料记录</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="/purchase/add" method="post">
                    <div>
                        <label >原料编号</label>
<%--                        <input type="text" class="form-control" id="exampleInputEmail1"--%>
<%--                               placeholder="Name"--%>
<%--                               required>--%>
                        <select class="form-control" name="materialID">
                            <%
                                List<Material> materials = (List<Material>) pageContext.getAttribute("Materials");
                                for ( Material material1 : materials) {
                                    out.print("<option value=\""+ material1.getMaterialID() +"\" >" + material1.getMaterialName() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div>
                        <label for="amount">数量</label>
                        <input type="text" class="form-control" id="amount" name="number" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="submit" form="editForm" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" style="top: 20vh;" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">新增原料</h4>
            </div>
            <div class="modal-body">
                <form id="editForm2" action="/material/add" method="post">
                    <div>
                        <label >原料名称</label>
                        <input type="text" class="form-control" name="materialName" >
                    </div>
                    <div>
                        <label for="amount2">原料价格</label>
                        <input type="text" class="form-control" id="amount2" name="price" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" form="editForm2" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" style="top: 20vh;" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel3">删除原料</h4>
            </div>
            <div class="modal-body">
                <form id="editForm3" action="/material/delete" method="get">
                    <div>
                        <label >原料编号</label>
                        <select class="form-control" name="materialID">
                            <%
                                materials = (List<Material>) pageContext.getAttribute("Materials");
                                for ( Material material1 : materials) {
                                    out.print("<option value=\""+ material1.getMaterialID() +"\" >" + material1.getMaterialName() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" form="editForm3" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

                <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
                <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                <script src="./js/libs/anime.min.js"></script>
                <script src="./js/getCommodity.js"></script>
</body>

</html>