<%@page import="org.apache.log4j.Logger,org.springframework.security.core.Authentication" %>

<head>
	<title>
		RFIManager
	</title>

<style>

table.curvedEdges { border:1px solid lightgray;-webkit-border-radius:8px;-moz-border-radius:8px;border-radius:8px; }
table.curvedEdges td table.curvedEdges th { border-bottom:1px dotted black;padding:5px; }

table.curvedEdges1 { border:1px solid lightgray;-webkit-border-top-left-radius:8px;-moz-border-top-left-radius:8px;border-top-left-radius:8px;-webkit-border-top-right-radius:8px;-moz-border-top-right-radius:8px;border-top-right-radius:8px; }
table.curvedEdges1 td table.curvedEdges1 th { border-bottom:1px dotted black;padding:5px; }

</style>

</head>

	
<html>
<body>
<%
Logger logger = Logger.getLogger(org.springframework.security.core.Authentication.class);
logger.debug("\n\n\n\n***************I am in this world***************\n\n\n\n\n");

%>
<table height="100%" cellpadding="0" cellspacing="0">
	<tr height="10%">
		<td colspan="3">
		&nbsp;
		</td>
	</tr>
	<tr height="90%">
		<td style="width:70%;background-image:url(/RFIManager3/images/road1.gif);background-repeat:no-repeat">
			&nbsp;
		</td>
		
		<td style="width:30%;padding-top:100;padding-right:100;text-align:left;vertical-align:text-top;">
			<table class="curvedEdges" style="width:100%" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
					<tr>
						<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
							<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
								<tr><td style="background-image:url(/RFIManager3/css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
				        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Enter Login credentials</label>
				        		</td></tr>
				        	</table>
				        	
				        </td>
				     </tr> 
					<tr>
						<td>
							<% if("1".equals(request.getParameter("login_error"))) { %>
							<font color="red">Invalid username or password</font>
							<% } %>
						<form name="f" action="/RFIManager3/j_spring_security_check" method='POST' class="jqtransform">
							<table>
								<tr>
									<td>
										<label for="j_username">Username</label>
									</td>
									<td>
										<input type="text" name="j_username" />
									</td>
								</tr>
								
								<tr>
									<td>				
										<label for="j_password">Password</label>
									</td>
									<td>
										<input type="password" name="j_password" />
									</td>
								</tr>
								<!-- 
								<tr>
									<td colspan="2">				
										<input type='checkbox' name='_spring_security_remember_me'/> Remember me on this computer.
									</td>
								</tr>
								-->
								<tr>
									<td colspan="2">
										<input type="submit" name="submit" value="Login"/>
									</td>
								</tr>
							</table>
							<!-- <input name="reset" type="reset"/></td> -->
						</form>
						</td>
					</tr>
				</table>
		<td>
	</tr>
	
</table>
	</body>
</html>
