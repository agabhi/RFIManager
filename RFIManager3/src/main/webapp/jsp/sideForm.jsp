<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.ExpandedRFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Side</title>
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


function add()
{
	if(isEmpty(document.getElementById("side").value))
	{
		alert("Side cannot be blank!");
		return false;
	}
	
	document.getElementById("actionType").value = "add";
	return true;
	
}

function edit()
{
	if(isEmpty(document.getElementById("side_edit").value))
	{
		alert("Select a Side!");
		return false;
	}
	
	if(isEmpty(document.getElementById("sideNew_edit").value))
	{
		alert("New Side cannot be blank!");
		return false;
	}
	
	document.getElementById("actionType_edit").value = "edit";
	return true;
	
}


function deleteSide()
{
	if(isEmpty(document.getElementById("side_edit").value))
	{
		alert("Select a Side!");
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
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Add Side</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="sideForm" method="POST" commandName="side" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<input type="hidden" id="actionType" name="actionType" value="add">
							<table>
								<tr>
									<td>
										<label><font size="4">Side</font></label>
									</td>
									<td style="border:0">
										<form:input id="side" path="side" /> 
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
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Delete Side</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="sideForm" method="POST" commandName="side" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<input type="hidden" id="actionType_edit" name="actionType" value="edit">
							<table>
							
								<tr>
									<td>
										<label><font size="4">Side</font></label>
									</td>
									<td style="border:0">
										<form:select id="side_edit" path="side"> 
											<form:options items="${sideList}"/> 
										</form:select>
									</td>
								</tr>
							<!-- 							
								<tr>
									<td>
										<label><font size="4">New Value</font></label>
									</td>
									<td style="border:0">
										<form:input id="sideNew_edit" path="newSide" />
									</td>
								</tr>
							 -->	
								<tr>
									<td colspan="2">
										<!-- <input id="submitButton" type="submit" value="Edit" onClick="return edit()"> -->
										<input id="submitButton" type="submit" value="Delete" onClick="return deleteSide()">
									</td>
									
								</tr>
								
							</table>
						</form:form>
					</td>
				</tr> 
			</table>
		</td>
		
	</tr>
	
	<tr><td valign="top" style="padding-top:10" colspan="2">
		<table border="1" class="curvedEdges" style="text-align:center;width:100%;" cellspacing="0" cellpadding="5"  > 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="6">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Side List</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			
			
			
			<tr bgcolor="#F2F2F2">
			       <th>
				   		Side
				   </th>
			</tr>
			
			<c:forEach var="side" items="${sideList}">
			<tr>
				<td>
					${side}
				</td>
			</tr>
			</c:forEach>
		</table>
		
</td></tr>
</table>
     
</body>
</html> 
	