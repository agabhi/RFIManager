<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
    <body topmargin="0" leftmargin="0" >
		<table border="0" cellspacing="0" cellpadding="0" style="width:100%;height:100%">
			
			<tr style="height:10%">
				<td colspan="3" style="background-image:url(images/bg1.gif); background-repeat: repeat-x;" >
					<tiles:insertAttribute name="header" />
				</td>
		    </tr>
			
			<tr style="height:2%">
				<td colspan="3">
		                   &nbsp;
		        </td>
			</tr>
			
			<tr style="height:83%">
				<td valign="top" style="width:15%" bgcolor="white">
					<tiles:insertAttribute name="menu" />
				</td>
				<td valign="top" style="width:82%">
					<tiles:insertAttribute name="body" />
				</td>
				<td valign="top" style="width:3%">
					&nbsp;
				</td>
			</tr>
			
			<tr style="height:5%">
				<td colspan="3">
		                   &nbsp;
		        </td>
			</tr>
		
		</table>
	</body>
</html>
