<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.StatusBarChartCell, com.mysql.jdbc.StringUtils" %>
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

<%
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		  int length = to - from;
	%>
	
<br>
<div>
<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="6">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Gaps List (<%=from %> to <%=to %>)</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			
			<tr bgcolor="#F2F2F2">
			       <th>Side</th>
			       <th>Item</th>
			       <th>Layer</th>
			       <th>From CH</th>
			       <th>To CH</th>
			</tr>
			
<c:forEach var="sideEntry" items="${sideMap}">
	<% int[] gap = new int[length]; %>
	
	<c:forEach var="itemEntry" items="${sideEntry.value}">
		<c:forEach var="layerEntry" items="${itemEntry.value}">
				<% int index = 0; %>
					<c:forEach var="chainageCell" items="${layerEntry.value}" >
						<% StatusBarChartCell sbcc = (StatusBarChartCell)pageContext.getAttribute("chainageCell");
							int fromCell = from+index*10;
							int toCell = fromCell+10;
						if(sbcc != null) { 
							if (sbcc.getStatus() != null && !StringUtils.isEmptyOrWhitespaceOnly(sbcc.getStatus())) {%>
							<% gap[index] = 1;%>
							
							<% } else { 
										if (gap[index] == 1) { %>
										<tr>
											<td>${sideEntry.key}</td>
											<td>${itemEntry.key}</td>
											<td>${layerEntry.key}</td>
											<td><%=fromCell %></td>
											<td><%=toCell %></td>
										</tr>
										<% } %>
											
							<% } 
						}%>
						
						<% ++index; %>
					</c:forEach>
				
		</c:forEach>
	</c:forEach>
	
</c:forEach>
</table>

</div>