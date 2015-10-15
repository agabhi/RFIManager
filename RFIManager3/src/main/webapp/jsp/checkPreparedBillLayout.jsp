<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.RFIBill, com.mysql.jdbc.StringUtils, java.text.DecimalFormat;" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>

<script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
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

<script>

function finalizeBill()
{
	var r=confirm("You will not be able to revert this bill. Are you really sure you want to finalize the bill?");
	if (!(r==true))
  	{
  		return false;
  	}
	else
	{
		return true;
	}
}

function exportBill()
{
		var url = '<%=request.getContextPath()%>/exportPreparedBill.htm';
		window.location = url;
  		return false;
}

</script>

</head>
<form:form id="finalizeBillForm" method="POST" commandName="finalizeBill" cssStyle="margin: 0px; padding: 0px;" action="<%=request.getContextPath()%>/finalizePreparedBill.htm">
	<form:errors path="*" />
	
<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="width:100%;border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="8">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Finalize the Bill</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr>
			     
			     <tr>
			     	<td style="border:0"><label><font size="4"><font color="#000066"></font>Enter Bill Number</font></font></label></td><td style="border:0"><form:input id="billNumber" path="billNumber" /></td>
					<td style="border:0"><label><font size="4"><font color="#000066"></font>From Date (dd-mm-yyyy)</font></font></label></td><td style="border:0"><form:input id="billFromDate" path="billFromDate" /></td>
					<td style="border:0"><label><font size="4"><font color="#000066"></font>To Date (dd-mm-yyyy)</font></font></label></td><td style="border:0"><form:input id="billToDate" path="billToDate" /></td>
					<td style="border:0"><label><font size="4"><font color="#000066"></font>Bill Date (dd-mm-yyyy)</font></font></label></td><td style="border:0"><form:input id="billDate" path="billDate" /></td>
				</tr>
				<tr>
					<td>
						<input id="finalizeBillButton" type="submit" value="Finalize Bill" onclick="finalizeBill();">
						<input id="exportBillButton" type="button" value="Export Bill" onclick="exportBill();">
					</td>
				</tr>
</table>
</form:form>
				
<%! DecimalFormat twoDForm = new DecimalFormat("#.##"); %>					

<hr color="blue;">
<div style="page-break-after: always">

<a href="<%=request.getContextPath()%>/intermediateCalculationSheet.htm">View Calculation sheet</a>&nbsp;
<a href="<%=request.getContextPath()%>/intermediateDeductionSheet.htm">View Deduction sheet</a>
<c:forEach var="itemEntry" items="${itemMap}">
	<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="14">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">${itemEntry.key}</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			
			<tr bgcolor="#F2F2F2">
					<th style="width:10%">
				   		Issue date
				   </th>
				   <th style="width:10%">
				   		Activity
				   </th>
			       <th style="width:20%">
				   		Item
				   </th>
				   <th style="width:10%">
				   		WI Remarks
				   </th>
				   <th style="width:5%">
				   		Side
				   </th>
				   <th style="width:5%">
				   		Layer
				   </th>
				   <th style="width:5%">
				   		RFI No.
				   </th>
				   <th style="width:5%">
				   		From CH
				   </th>
				   <th style="width:5%">
				   		To CH
				   </th>
				   <th style="width:5%">
				   		Quantity
				   </th>
				   <th style="width:5%">
				   		Deduction Quantity
				   </th>
				   <th style="width:5%">
				   		Payable Quantity
				   </th>
				   <th style="width:5%">
				   		Rate
				   </th>
				   <th style="width:5%">
				   		Amount
				   </th>              
			</tr>
		<c:forEach var="activityEntry" items="${itemEntry.value}">
			<c:forEach var="sideEntry" items="${activityEntry.value}">
				<c:forEach var="layerEntry" items="${sideEntry.value}">
					<c:forEach var="billRow" items="${layerEntry.value}" >
							<% RFIBill rfiBill = (RFIBill)pageContext.getAttribute("billRow");
							if(rfiBill != null) { %>
							<tr style="page-break-inside:avoid;">
								<td><%= rfiBill.getIssueDateAsText() %></td>
								<td>${activityEntry.key}</td>
								<td>${itemEntry.key}</td>
								<td><% if(rfiBill.getWiRemarks() == null) {  } else { out.println(rfiBill.getWiRemarks()); }%></td>
								<td>${sideEntry.key}</td>
								<td>${layerEntry.key}</td>
								<td><%= rfiBill.getRfiNumber() %></td>
								<td><%= rfiBill.getBillFrom() %></td>
								<td><%= rfiBill.getBillTo() %></td>
								<td><% if(rfiBill.getQuantity() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getQuantity())); }%></td>
								<td><% if(rfiBill.getDeductQuantity() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getDeductQuantity())); }%></td>
								<td><% if(rfiBill.getPayableQuantity() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getPayableQuantity())); }%></td>
								<td><% if(rfiBill.getRate() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getRate())); }%></td>
								<td><% if(rfiBill.getAmount() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getAmount())); }%></td>
							
							</tr>
							 
							<% } %>
							
						</c:forEach>
				</c:forEach>
			</c:forEach>
		</c:forEach>

</table>
<br>	
</c:forEach>


</div>