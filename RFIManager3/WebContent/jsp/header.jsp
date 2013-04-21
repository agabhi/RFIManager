<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table style="width:100%;height:100%" cellspacing="0" cellpadding="0">
	<tr style="width:100%">
		<td align="left" style="padding-left:20px;color:white">
			<font size="15">RFI</font>
			<font size="5" style="font-family:Comic Sans MS;font-style:italic">MANAGER</font>
			<br>
			<a href="/RFIManager3/home.htm" style="color:white">Home</a>
		</td>
		
		<td align="right" style="padding-right:20px;color:white">
		<font size="4">Welcome, <sec:authentication property="principal.username"/>!</font><br>
		<a href="/RFIManager3/j_spring_security_logout" style="color:white">Logout</a>
		</td>
	</tr>
</table>