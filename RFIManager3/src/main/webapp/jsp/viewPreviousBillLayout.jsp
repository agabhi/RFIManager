<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.RFIBill, com.mysql.jdbc.StringUtils, java.text.DecimalFormat" %>
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

<script>

function editBill()
{
	var r=confirm("This is an old bill. Are you really sure you want to edit the bill?");
	if (!(r==true))
  	{
  		return false;
  	}
	else
	{
		return true;	
	}
}

</script>

</head>
<form:form id="editBillForm" method="POST" commandName="editBill" cssStyle="margin: 0px; padding: 0px;">
<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="width:100%;border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="8">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Edit the Bill</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr>
			     
			     <tr>
			     	<td style="border:0"><label><font size="4">Bill Number</font></label></td><td style="border:0"><form:input id="billNumber" path="billNumber" disabled="true"/></td>
					<td style="border:0"><label><font size="4">From Date</font></label></td><td style="border:0"><form:input id="billFromDate" path="billFromDate" disabled="true"/></td>
					<td style="border:0"><label><font size="4">To Date</font></label></td><td style="border:0"><form:input id="billToDate" path="billToDate" disabled="true"/></td>
					<td style="border:0"><label><font size="4">Bill Date</font></label></td><td style="border:0"><form:input id="billDate" path="billDate" disabled="true"/></td>
				</tr>
				<tr>
					<td>
						<input id="editBillButton" type="submit" value="Edit Bill" onclick="editBill();">
					</td>
				</tr>
</table>
</form:form>
					
<%! DecimalFormat twoDForm = new DecimalFormat("#.##"); %>
<hr color="blue;">
<div style="page-break-after: always">


<c:forEach var="itemEntry" items="${itemMap}">
	<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="13">
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
			       <th style="width:30%">
				   		Item
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
			<c:forEach var="billRow" items="${itemEntry.value}" >
							<% RFIBill rfiBill = (RFIBill)pageContext.getAttribute("billRow");
							if(rfiBill != null) { %>
							<tr style="page-break-inside:avoid;">
								<td><%= rfiBill.getIssueDateAsText() %></td>
								<td><%= rfiBill.getBillActivity() %></td>
								<td><%= rfiBill.getItemDescription() %></td>
								<td><%= rfiBill.getBillSide() %></td>
								<td><%= rfiBill.getBillLayer() %></td>
								<td><%= rfiBill.getRfiNumber() %></td>
								<td><%= rfiBill.getBillFrom() %></td>
								<td><%= rfiBill.getBillTo() %></td>
								<td><% if(rfiBill.getQuantity() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getQuantity())); }%></td>
								<td><% if(rfiBill.getDeductQuantity() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getDeductQuantity())); }%></td>
								<td><% if(rfiBill.getPayableQuantity() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getPayableQuantity())); }%></td>
								<td><% if(rfiBill.getRate() == null) { out.println("-"); } else { out.println(rfiBill.getRate()); }%></td>
								<td><% if(rfiBill.getAmount() == null) { out.println("-"); } else { out.println(twoDForm.format(rfiBill.getAmount())); }%></td>
							
							</tr>
							 
							<% } %>
							
			</c:forEach>

</table>
<br>	
</c:forEach>


</div>