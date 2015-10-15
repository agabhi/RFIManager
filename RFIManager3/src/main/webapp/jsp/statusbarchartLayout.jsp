<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.StatusBarChartCell, com.mysql.jdbc.StringUtils, java.util.Date" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<style>
.ui-jqgrid {font-size:0.9em}

table.curvedEdges { border:1px solid lightgray;-webkit-border-radius:8px;-moz-border-radius:8px;border-radius:8px; }
table.curvedEdges td table.curvedEdges th { border-bottom:1px dotted black;padding:9px; }

table.curvedEdges1 { border:1px solid lightgray;-webkit-border-top-left-radius:8px;-moz-border-top-left-radius:8px;border-top-left-radius:8px;-webkit-border-top-right-radius:8px;-moz-border-top-right-radius:8px;border-top-right-radius:8px; }
table.curvedEdges1 td table.curvedEdges1 th { border-bottom:1px dotted black;padding:9px; }

.rot-neg-90 {
  /* rotate -90 deg, not sure if a negative number is supported so I used 270 */
  -moz-transform: rotate(270deg);
  -moz-transform-origin: 50% 50%;
  -webkit-transform: rotate(270deg);
  -webkit-transform-origin: 50% 50%;
  /* IE support too convoluted for the time I've got on my hands... */
}

</style>

</head>


<div style="page-break-after: always">
<hr color="blue;">

<table width="100%">
<tr>
<td style="text-align:left;">
<label ><%=request.getParameter("activity")  %></label>
</td>
<td style="text-align:right;">
<label ><%=new Date()  %></label>
</td>
</tr>
</table>

<table>
	<tr>
		<c:forEach var="statusColorEntry" items="${statusColorMap}">
			<td style="border:2px solid #999;background-color:${statusColorEntry.value};">
				&nbsp;
			</td>
			<td style="padding-right:20px">
				${statusColorEntry.key}
			</td>
		</c:forEach>
</table>
<table cellspacing="0" cellpadding="0" style="page-break-inside:auto;border-spacing:0;border-collapse:collapse" >
<c:forEach var="sideEntry" items="${sideMap}">
	<% int[] gap = new int[1000]; %>
	<tr style="page-break-inside:avoid;page-break-after:auto"><td>
&nbsp;
	<table cellspacing="0" cellpadding="0" style="border-spacing:0;border-collapse:collapse">
	<tr>
		<td  rowspan="${layerShowCount}" style="border:2px solid #999;background-color:#F2F2F2">
			<table>
				<tr style="page-break-inside:avoid;">
					<td class="rot-neg-90">
						${sideEntry.key}
					</td>
				</tr>
			</table>
		</td>		
		<td height="60">&nbsp;</td>
		<%
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		int chCount = 0;
		for (int i=from+10; i <= to; i=i+10) { %>
			<td valign="middle" style="background-color:#F2F2F2;text-align:center;word-break:break-all; min-width: 9px;max-width: 9px;text-align:middle;border:1px solid #999;">
						<%if(chCount%5 == 0) { %>	
							<font size="2"><%=i %></font>
						<%}  ++chCount; %>
			</td>
		<%} %>
	</tr>
	
	<c:forEach var="itemEntry" items="${sideEntry.value}" >
	<% String newItemBorder = "2px solid #999";%>
		<c:forEach var="layerEntry" items="${itemEntry.value}">
				<tr style="page-break-inside:avoid;page-break-after:auto">
					<td nowrap="nowrap" style="border:1px solid #999;border-top:<%=newItemBorder%>;background-color:#F2F2F2">
									${itemEntry.key} - Layer ${layerEntry.key}
					</td>
					<% int index = 0; %>
					<c:forEach var="chainageCell" items="${layerEntry.value}" >
						<% StatusBarChartCell sbcc = (StatusBarChartCell)pageContext.getAttribute("chainageCell");
						if(sbcc != null) { 
							if (sbcc.getStatus() != null && !StringUtils.isEmptyOrWhitespaceOnly(sbcc.getStatus())) {%>
							<% gap[index] = 1;%>
							<td style="border:1px solid #999;border-top:<%=newItemBorder%>;background-color:<%=sbcc.getColor() %>;max-height: 9px;min-width: 9px;max-width: 9px;overflow: hidden;" onclick="javascript: alert('<%= sbcc.getPopup()%>');">&nbsp;</td>
							
							<% } else { 
										if (gap[index] == 1) { %>
											<td style="border:1px solid #999;border-top:<%=newItemBorder%>;background-color:red;max-height: 9px;min-width: 9px;max-width: 9px;overflow: hidden;">&nbsp;</td>
										<% } else { %>
											<td style="border:1px solid #999;border-top:<%=newItemBorder%>;max-height: 9px;min-width: 9px;max-width: 9px;overflow: hidden;">&nbsp;</td>
										<% } %>
							<% } 
						} else {%>
									<td style="border-top:<%=newItemBorder%>;max-height: 9px;min-width: 9px;max-width: 9px;overflow: hidden;">&nbsp;</td>
						<% } %>
						
						<% ++index; %>
					</c:forEach>
				</tr>
		</c:forEach>
		<% newItemBorder = "1px solid #999"; %>
	</c:forEach>
	
</table>
</td></tr>
</c:forEach>
</table>

</div>