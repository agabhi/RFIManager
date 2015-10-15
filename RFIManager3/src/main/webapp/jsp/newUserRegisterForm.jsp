<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
	<title>Register User</title>

<style>

table.curvedEdges { border:1px solid lightgray;-webkit-border-radius:8px;-moz-border-radius:8px;border-radius:8px; }
table.curvedEdges td table.curvedEdges th { border-bottom:1px dotted black;padding:5px; }

table.curvedEdges1 { border:1px solid lightgray;-webkit-border-top-left-radius:8px;-moz-border-top-left-radius:8px;border-top-left-radius:8px;-webkit-border-top-right-radius:8px;-moz-border-top-right-radius:8px;border-top-right-radius:8px; }
table.curvedEdges1 td table.curvedEdges1 th { border-bottom:1px dotted black;padding:5px; }

</style>

<script>
function isEmpty(value){
	   value= value.replace(/^\s+|\s+$/, '');
	    return (value === undefined || value == null || value.length <= 0) ? true : false;
	}
	
function deleteUser(username)
{
	var r=confirm("Are you sure you want to delete the user '"+username+"''?");
	if (r==true)
  	{
		var url = '<%=request.getContextPath()%>/deleteUser.htm?username='+username;
		window.location = url; 
  	}
	else
	{
	}
}

function save()
{
	var firstname = document.getElementById("firstName").value;
	var username = document.getElementById("userName").value;
	var password = document.getElementById("password").value;
	var repeatPassword = document.getElementById("repeatPassword").value;
	
	if(isEmpty(username))
	{
		alert("Username cannot be blank!");
		return false;
	}
	
	if(isEmpty(firstname))
	{
		alert("First Name cannot be blank!");
		return false;
	}
	
	if(isEmpty(password))
	{
		alert("Password cannot be blank!");
		return false;
	}
	
	if(isEmpty(repeatPassword))
	{
		alert("Both password do not match!");
		return false;
	}
	
	if(password != repeatPassword)
	{
		alert("Both password do not match!");
		return false;
	}
	
	return true;
}

</script>

</head>

<body>
<form:form id="addForm" method="POST" commandName="userRegister" cssStyle="margin: 0px; padding: 0px;">
				
	<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="2">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">New User Registration</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
		     </tr> 
		 	<tr>
		 	<td colspan="2">
		 		<form:errors path="*" />
		 		<% if("success".equals(request.getParameter("result"))) { %>
							<font color="blue">User created successfully!!</font>
							<% } %>
		 	</td>
		 	</tr>
		 	
			<tr>
		       <td style="border:0">
		       
	
		        <label><font size="4">Role</font></label>
		        </td>
		        <td style="border:0">
					<form:select id="role" path="role"> 
					<option value="ROLE_USER">User</option>
					<option value="ROLE_ADMIN">Admin</option>
				</form:select> 
				</td>
			</tr>
			
			<tr>
		       <td style="border:0">
		       
	
		        <label><font size="4">First Name</font></label>
		        </td>
		        <td style="border:0">
					<form:input id="firstName" path="firstName" /> 
				</td>
			</tr>
			
			<tr>
		       <td style="border:0">
		       
	
		        <label><font size="4">Last Name</font></label>
		        </td>
		        <td style="border:0">
					<form:input id="lastName" path="lastName" /> 
				</td>
			</tr>
			
			<tr>
		       <td style="border:0">
		       
	
		        <label><font size="4">Username</font></label>
		        </td>
		        <td style="border:0">
					<form:input id="userName" path="userName" /> 
				</td>
			</tr>
			
			<tr>
		        <td style="border:0">
					<label><font size="4">Password</font></label>
		        </td>
		        <td style="border:0">
					<form:password id="password" path="password" /> 
				</td>
			</tr>
			<tr>
		        <td style="border:0">
					<label><font size="4">Repeat password</font></label>
		        </td>
		        <td style="border:0">
					<input type="password" id="repeatPassword" /> 
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="submitButton" type="submit" value="Save" onclick="return save();">
				</td>
			</tr>
			
			
	
</table>

	 </form:form>
	<hr/>
	
	<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="5">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Users List</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			<tr bgcolor="#F2F2F2">
				   <th>
				   		Username
				   </th>
			       <th>
				   		First Name
				   </th>
				   <th>
				   		Last Name
				   </th>
				   <th>
				   		Role
				   </th>
				   <th>
				   		&nbsp;
				   </th>
			</tr>

			<c:forEach var="user" items="${users}">
				<tr style="page-break-inside:avoid;">
					<td>${user.userName}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.role}</td>
					<td><a href="javascript:deleteUser('${user.userName}')">Delete</a></td>
				</tr>
			</c:forEach>

</table>
	 
	 
</body>
 
	