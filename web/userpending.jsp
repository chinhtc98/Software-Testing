<%-- 
    Document   : userpending
    Created on : Mar 15, 2022, 2:20:52 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Pending</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-header clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right">
                            <i class="fas fa-filter">Filter</i>
                            <select class="select">
                                <option><a href="#">Spring 2022</a></option>
                                <option><a href="#">Fall 2021</a></option>
                                <option><a href="#">Summer 2021</a></option>
                                <option><a href="#">Spring 2021</a></option>
                            </select>
                        </ul>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th style="width : 10px">#</th>
                                    <th>Email</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <c:forEach var="invi" items="${sessionScope.INVITATION}" varStatus="count">
                                <tbody>
                                    <tr>
                                        <td style="width: 50px">${count.count}</td>
                                        <td style="width: 100px">${invi.userInvited}</td>
                                        <td style="width: 100px">${invi.status.name}....</td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right">
                            <!-- <li><a href="#">&laquo;</a></li> -->
                            <li><a href="#">Previous</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">Next</a></li>
                            <!-- <li><a href="#">&raquo;</a></li> -->
                        </ul>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </body>
</html>
