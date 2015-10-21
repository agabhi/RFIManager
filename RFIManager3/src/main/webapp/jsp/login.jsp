<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
	<head>
	    <title>infraCMS - Project Management</title>
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/custom-theme/jquery-ui-1.10.0.custom.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ui.jqgrid.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/custom/main.css">
	</head>
	
	
	<body ng-app="indexApp" ng-controller="IndexController">



<div id="wrap">
    <header id="header" class="navbar home-page-header">
       <div class="clearfix">
           <ul class="pull-left clearfix" style="margin: 0px;">
               <li class="pull-right" style="padding-right: 10px;"><a href="javascript:demo()">Demo</a></li>
           </ul>
           <ul class="pull-right clearfix" style="margin: 0px;">
               <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
               		<li class="pull-right" style="padding-left: 10px;"><a  href="<%=request.getContextPath() %>/login">Login</a></li>
               </sec:authorize>
               <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
               <li class="pull-right" style="padding-left: 10px;"><a  href="<%=request.getContextPath() %>/j_spring_security_logout">Logout</a></li>
               <li class="pull-right" style="padding-left: 10px;">
               <%-- <div style="padding:3px 0px;">
               	<span class="glyphicon glyphicon-user"></span>
                   <%=((com.derive.conbase.security.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getFullName()%>
                </div> --%>
               </li>
               </sec:authorize>
           </ul>
       </div>
    </header>
    <section id="content" class="no-border">
        <div class="row row-padder home-center-box" style="padding-top: 40px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-7" style="color: white">
                        <img src="<%=request.getContextPath()%>/assets/img/infraCMSlogo.png"/>

                        <h1 style="margin-bottom: 30px;margin-top: 40px;">Project management for construction projects</h1>
                        <ul>
                            <li style="font-size: 2.0em;font-weight: 200;list-style:none ;margin-bottom: 15px;color:#B3D8FF"><span class="glyphicon glyphicon-ok"></span><div style="display:inline-block;margin-left:10px;">Get free from scattered excel spreadsheets.</div></li>
                            <li style="font-size: 2.0em;font-weight: 200;list-style:none ;margin-bottom: 15px;color:#B3D8FF"><span class="glyphicon glyphicon-ok"></span><div style="display:inline-block;margin-left:10px;">Collaborate with multiple users.</div></li>
                            <li style="font-size: 2.0em;font-weight: 200;list-style:none ;margin-bottom: 15px;color:#B3D8FF"><span class="glyphicon glyphicon-ok"></span><div style="display:inline-block;margin-left:10px;">Store you records in the cloud with ensured backup.</div></li>
                            <li style="font-size: 2.0em;font-weight: 200;list-style:none ;margin-bottom: 15px;color:#B3D8FF"><span class="glyphicon glyphicon-ok"></span><div style="display:inline-block;margin-left:10px;">Monitor data with layer charts.</div></li>
                        </ul>
                    </div>
                    <div class="col-md-5" style="color: white">
                        <ul style="margin-bottom: 110px;">
                            <li class="nav-link"><a href="javascript:demo()">Demo</a></li>
                            <%-- <li class="nav-link"><a href="<%=request.getContextPath()%>/pricing">Pricing</a></li> --%>
                            <li class="nav-link"><a href="<%=request.getContextPath()%>/assets/RFI_Manager_Setup.pdf">Docs</a></li>
                            <li class="btn btn-warning">Contact</li>
                        </ul>
                        <form role="form" name="registrationForm" method="POST">
                            <div class="form-group">
                                <input type="text" class="form-control" id="fullName" name="fullName"
                                       placeholder="Your Fullname" ng-model="user.fullName" style="height:60px;font-size: 16px;" required>
                                <div class="error" ng-show="showValidationErrors && registrationForm.fullName.$error.required" id="fullNameError"></div>
                            </div>
                            <div class="form-group">
                                <input type="email" ng-model="user.email" class="form-control" name="email"
                                       placeholder="Enter email" style="height:60px;font-size: 16px;" required>
                                <div class="error" ng-show="showValidationErrors && registrationForm.email.$error.required"  id="emailReqError"></div>
                                <div class="error" ng-show="showValidationErrors && registrationForm.email.$error.email" id="emailInvalidError"></div>
                            </div>
                            <div class="form-group">
                                <input type="phone" class="form-control" name="password"
                                       placeholder="Phone number" ng-model="user.password" style="height:60px;font-size: 16px;" ng-minlength="6" required>
                                <div class="error" ng-show="showValidationErrors && registrationForm.password.$error.required" id="passReqError"></div>
                                <div class="error" ng-show="showValidationErrors && registrationForm.password.$error.minlength" id="passLengthError"></div>
                            </div>
                            <button type="button" ng-click="register()" class="btn btn-warning btn-lg btn-block" style="height:60px;font-size: 18px;">Submit</button>
                            <h6>Works on IE9 & above, Mozilla, Chrome, Safari & Opera</h6>
                            <h5 style="display:none">By signing up you agree to our <a href="javascript:void(0)" style="color:#B3D8FF">Terms & Service</a></h5>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="container">
                <div class="row">
                    <div class="col-md-6" style="font-size: 14px;">
                        <h3 style="font-weight: bold;color:#326bb1;;"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;Get free from scattered excel</h3>
                        <div style="color:#4b4b4b">
	                        <p>InfraCMS allows you to allows you to create custom records online. You will now never need to maintain your data in the excel. However, you can export the records in excel format at any point of time.</p>
	                    </div>
                    </div>
                    <div class="col-md-6" style="font-size: 14px;">
                        <h3 style="font-weight: bold;color:#326bb1;;"><span class="glyphicon glyphicon-road"></span>&nbsp;&nbsp;Monitor Layers</h3>
                        <div style="color:#4b4b4b">
	                        <p>InfraCMS creates layer charts for length based constructions such as roads & canals. The charts gets auto generated from the input data.</p>
	                        <p>With the help of these charts you can easily locate gaps in the construction and monitor the work done on any given stretch.</p>
	                    </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6" style="font-size: 14px;">
                        <h3 style="font-weight: bold;color:#326bb1;;"><span class="glyphicon glyphicon-cloud"></span>&nbsp;&nbsp;Web based in the cloud</h3>
                        <div style="color:#4b4b4b">
	                        <p>With InfraCMS, you get web based access to your records. You can access them just from anywhere in the world where you have a PC or mobile with the internet connection.</p>
	                    </div>
                    </div>
                    <div class="col-md-6" style="font-size: 14px;">
                        <h3 style="font-weight: bold;color:#326bb1;;"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Collaborate</h3>
                        <div style="color:#4b4b4b">
                        	<p>Add multiple users to your project, so that they can work along with you on the project.</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>
    <footer id="footer" >
        <div class="container">
            <img src="<%=request.getContextPath()%>/assets/img/logodark.png" height="40"/>
            &copy; infraCMS 2014 | All Rights Reserved
        </div>
    </footer>
</div> 

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Message</h4>
      </div>
      <div class="modal-body">
        	<div ng-show="modal.error" class="alert alert-danger">
		      <strong>Error!</strong>
		    </div>
		    <div ng-show="modal.success" class="alert alert-success">
		      <strong>Success!</strong>
		    </div>
		    <div ng-bind="modal.message"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>

<form  style = "display:none" id = "loginform" name="loginform" action="<%=request.getContextPath()%>/j_spring_security_check" method='POST' class="jqtransform">
			<input type="text" name="j_username" value="abhishekA" />
			<input type="password" name="j_password" value="abhi#132"/>
			<input type="submit"  value="Login"/>
		</form>
	
<script src="<%=request.getContextPath()%>/assets/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>

<script>
	function demo() {
		$("#loginform").submit();
		return false;
	}
</script>

</body>
	
		
	

	
</html>