<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.RFIBill, com.mysql.jdbc.StringUtils" %>
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

function finalizeBill()
{
	var r=confirm("You will not be able to revert this bill. Are you really sure you want to finalize the bill?");
	if (!(r==true))
  	{
  		return false;
  	}
	else
	{
		
	}
}

</script>

</head>

<div style="page-break-after: always">


<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Bill Inbox</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			<tr bgcolor="#F2F2F2">
				   <th>
				   		Bill Number
				   </th>
			       <th>
				   		Bill Duration Start
				   </th>
				   <th>
				   		Bill Duration End
				   </th>
				   <th>
				   		Bill Date
				   </th>
			</tr>

			<c:forEach var="bill" items="${bills}">
				<tr style="page-break-inside:avoid;">
					<td><a href="showPreviousBill.htm?billNumber=${bill.billNumber}">${bill.billNumber}</a></td>
					<td>${bill.billFromDateAsText}</td>
					<td>${bill.billToDateAsText}</td>
					<td>${bill.billDateAsText}</td>
				</tr>
			</c:forEach>

</table>
</div>