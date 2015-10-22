<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.ExpandedRFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>BOQ</title>
    <script type="text/javascript" src="js/jquery.form.js"></script> 
    <script src="script/rfi.js"></script>


    	

<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />



<script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.mi.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>

<script src="js/JsonXml.js" type="text/javascript"></script>
<script src="js/grid.import.js" type="text/javascript"></script>	
	

<style>
.ui-jqgrid {font-size:0.9em}

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
function isNumeric(value) {
	  if(!(isEmpty(value))){
		   if (value != null && !value.toString().match(/^[-]?\d*\.?\d*$/)) return false;
		   return true;
	  }
	  else {
		  return false;
	  }
}


function fetchBOQDetails()
{
	if(!isEmpty(document.getElementById("boqNumber_edit").value))
	{
		$.post("boqDetails.htm",
				  { boqNumber: document.getElementById("boqNumber_edit").value },
				  function(xml){
					  if($(xml).find("category"))
					  	document.getElementById("category_edit").value = $(xml).find("category").text();
					  if($(xml).find("categorySequence"))
					  	document.getElementById("categorySequence_edit").value = $(xml).find("categorySequence").text();
					  if($(xml).find("description"))
					  	document.getElementById("description_edit").value = $(xml).find("description").text();
					  if($(xml).find("unit"))
					  	document.getElementById("unit_edit").value = $(xml).find("unit").text();
					  if($(xml).find("quantity"))
					  	document.getElementById("quantity_edit").value = $(xml).find("quantity").text();
					  if($(xml).find("amount"))
					  	document.getElementById("amount_edit").value = $(xml).find("amount").text();
					  if($(xml).find("rate"))
					  document.getElementById("rate_edit").value = $(xml).find("rate").text();
			});
		
		
	}
	else
	{
		document.getElementById("category_edit").value = "";
		document.getElementById("categorySequence_edit").value = "";
		document.getElementById("description_edit").value = "";
		document.getElementById("unit_edit").value = "";
		document.getElementById("quantity_edit").value = "";
		document.getElementById("rate_edit").value = "";
		document.getElementById("amount_edit").value = "";
	}
}

function add()
{
	if(isEmpty(document.getElementById("boqNumber").value))
	{
		alert("BOQ No. cannot be blank!");
		return false;
	}
	
	if(isEmpty(document.getElementById("description").value))
	{
		alert("Description cannot be blank!");
		return false;
	}
	
	if(!isNumeric(document.getElementById("categorySequence").value))
	{
		alert("Category Sequence should be a number");
		return false;
	}
	
	if (!(isEmpty(document.getElementById("quantity").value) || isNumeric(document.getElementById("quantity").value)))
	{
		alert("Quantity should either be empty or a number");
		return false;
	}
	
	if (!(isEmpty(document.getElementById("rate").value) || isNumeric(document.getElementById("rate").value)))
	{
		alert("Rate should either be empty or a number");
		return false;
	}
	
	if (!(isEmpty(document.getElementById("amount").value) || isNumeric(document.getElementById("amount").value)))
	{
		alert("Amount should either be empty or a number");
		return false;
	}
	
	document.getElementById("actionType").value = "add";
	return true;
	
}

function edit()
{
	if(isEmpty(document.getElementById("boqNumber_edit").value))
	{
		alert("Select a BOQ No.!");
		return false;
	}
	
	if(isEmpty(document.getElementById("description_edit").value))
	{
		alert("Description cannot be blank!");
		return false;
	}
	
	if(!isNumeric(document.getElementById("categorySequence_edit").value))
	{
		alert("Category Sequence should be a number");
		return false;
	}
	
	if (!(isEmpty(document.getElementById("quantity_edit").value) || isNumeric(document.getElementById("quantity_edit").value)))
	{
		alert("Quantity should either be empty or a number");
		return false;
	}
	
	if (!(isEmpty(document.getElementById("rate_edit").value) || isNumeric(document.getElementById("rate_edit").value)))
	{
		alert("Rate should either be empty or a number");
		return false;
	}
	
	if (!(isEmpty(document.getElementById("amount_edit").value) || isNumeric(document.getElementById("amount_edit").value)))
	{
		alert("Amount should either be empty or a number");
		return false;
	}
	
	document.getElementById("actionType_edit").value = "edit";
	return true;
	
}


function deleteBoq()
{
	if(isEmpty(document.getElementById("boqNumber_edit").value))
	{
		alert("Select a BOQ No.!");
		return false;
	}
	document.getElementById("actionType_edit").value = "delete";
	return true;
}

</script>

	
</head>

<body>
<table id="container" style="width:100%;" cellspacing="0" cellpadding="0">
	<tr width:100%">
		<td valign="top" style="border-bottom:1 px solid gray;padding-right:5;padding-bottom:10">
				<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Add BOQ Item</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="boqForm" method="POST" commandName="boq" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<input type="hidden" id="actionType" name="actionType" value="add">
							<table>
								<tr>
									<td>
										<label><font size="4">Category</font></label>
									</td>
									<td style="border:0">
										<form:input id="category" path="category" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Category Seq</font></label>
									</td>
									<td style="border:0">
										<form:input id="categorySequence" path="categorySequence" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">BOQ No.</font></label>
									</td>
									<td style="border:0">
										<form:input id="boqNumber" path="boqNumber" />
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Description</font></label>
									</td>
									<td style="border:0">
										<form:textarea id="description" path="description" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Unit</font></label>
									</td>
									<td style="border:0">
										<form:input id="unit" path="unit" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Quantity</font></label>
									</td>
									<td style="border:0">
										<form:input id="quantity" path="quantity" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Rate</font></label>
									</td>
									<td style="border:0">
										<form:input id="rate" path="rate" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Amount</font></label>
									</td>
									<td style="border:0">
										<form:input id="amount" path="amount" /> 
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<input id="submitButton" type="submit" value="Add" onClick="return add()">
									</td>
									
								</tr>
								
							</table>
						</form:form>
					</td>
				</tr> 
			</table>
		</td>
		
		
		
		<td valign="top" style="border-bottom:1 px solid gray;padding-right:5;padding-bottom:10">
				<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Edit BOQ Item</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="boqForm" method="POST" commandName="boq" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<input type="hidden" id="actionType_edit" name="actionType" value="edit">
							<table>
							
								<tr>
									<td>
										<label><font size="4">BOQ No.</font></label>
									</td>
									<td style="border:0">
										<form:select id="boqNumber_edit" path="boqNumber" onchange="fetchBOQDetails()"> 
											<form:options items="${boqNumberList}"/> 
										</form:select>
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Category</font></label>
									</td>
									<td style="border:0">
										<form:input id="category_edit" path="category" />
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Category Seq</font></label>
									</td>
									<td style="border:0">
										<form:input id="categorySequence_edit" path="categorySequence" /> 
									</td>
								</tr>
								
								
								
								<tr>
									<td>
										<label><font size="4">Description</font></label>
									</td>
									<td style="border:0">
										<form:textarea id="description_edit" path="description" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Unit</font></label>
									</td>
									<td style="border:0">
										<form:input id="unit_edit" path="unit" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Quantity</font></label>
									</td>
									<td style="border:0">
										<form:input id="quantity_edit" path="quantity" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Rate</font></label>
									</td>
									<td style="border:0">
										<form:input id="rate_edit" path="rate" /> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Amount</font></label>
									</td>
									<td style="border:0">
										<form:input id="amount_edit" path="amount" /> 
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<input id="submitButton" type="submit" value="Edit" onClick="return edit()">
										<input id="submitButton" type="submit" value="Delete" onClick="return deleteBoq()">
									</td>
									
								</tr>
								
							</table>
						</form:form>
					</td>
				</tr> 
			</table>
		</td>
		
		<td valign="top" style="border-bottom:1 px solid gray;
		padding-bottom:10">
				<table class="curvedEdges" style="width:100%;height:150%" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Import BOQ</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				
				<tr>
				<td style="padding-top:12;padding-bottom:0;font-size:large">
					<form:form commandName="fileUpload" action="boqFileUpload.htm" enctype="multipart/form-data" cssStyle="margin: 0px; padding: 0px;">
					<p style="font-family: Verdana, Geneva, sans-serif;">
						Please specify a file:<br><br>
					<input type="file" name="file" style="font-size:large;font-family: Verdana,Geneva,sans-serif;"/>
					</p>
					<br>
					<input type="submit" value="Import" style="font-size:large;"/>
						
					</form:form>
				</td>
			</tr>
			
			<tr>
				<td style="padding-top:12;padding-bottom:0;font-size:large">
					<a href="<%=request.getContextPath()%>/assets/Boq.xls">Download sample file</a>
				</td>
			</tr>
			</table>
			
		</td>
		
		
	</tr>
	<c:forEach var="entry" items="${boqMap}">
	<tr><td valign="top" style="padding-top:10" colspan="3">
		<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="6">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">${entry.key}</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			
			
			
			<tr bgcolor="#F2F2F2">
			       <th style="width:5%">
				   		BOQ No.
				   </th>
				   <th style="width:75%">
				   		Description
				   </th>
				   <th style="width:5%">
				   		Unit
				   </th>
				   <th style="width:5%">
				   		Total Qty.
				   </th>
				   <th style="width:5%">
				   		Rate
				   </th>
				   <th style="width:5%">
				   		Amount
				   </th>       
			</tr>
			
			<c:forEach var="boq" items="${entry.value}">
			<tr>
				<td>
					${boq.boqNumber}
				</td>
				<td style="text-align:left;padding-left:5">
					${boq.description}
				</td>
				<td>
					${boq.unit}
				</td>
				<td>
					${boq.quantity}
				</td>
				<td>
					${boq.rate}
				</td>
				<td>
					${boq.displayAmount}
				</td>
			</tr>
			</c:forEach>
		</table>
		
</td></tr>
</c:forEach>
</table>
     
</body>
</html> 
	