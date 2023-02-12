<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="/header.jsp" %>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="index.html">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                          <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
                            <a href="<c:url value="/user/add"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                              <i class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
                          </div>
                          <div class="card shadow mb-4">
                            <div class="card-header py-3">
                              <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
                            </div>
                            <div class="card-body">
<%--                                <form action="/user/list" method="get">--%>
                              <div class="table-responsive">
                          <table class="table">
                            <tr>
                              <th>Id</th>
                              <th>Nazwa użytkownika</th>
                              <th>Email</th>
                              <th>Akcja</th>
                            </tr>
                            <c:forEach items="${users}" var="user">
                              <tr>
                                <td>${user.id}</td>
                                <td>${user.userName}</td>
                                <td>${user.email}</td>
                                <td>
                                  <a href='<c:url value="/user/delete?id=${user.id}"/>'>Usuń</a>
                                  <a href='<c:url value="/user/edit?id=${user.id}"/>'>Edit</a>
                                  <a href='<c:url value="/user/show?id=${user.id}"/>'>Pokaż</a>
                                </td>
                              </tr>
                            </c:forEach>
                          </table>
                              </div>
                            </div>
                          </div>
                        </div>
            </div>
            <!-- /.container-fluid -->
        </div>
</div>
<!-- End of Main Content -->

<%@ include file="/footer.jsp" %>
</body>
</html>

