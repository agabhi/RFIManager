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

function isInteger(value)
{
	var test_result = /^\d+$/.test(value);
	return test_result
}


function fetchWorkItemDetails()
{
	
	//document.getElementById("newItemDescription_edit").value = document.getElementById("itemDescription_edit").value;
	if(!isEmpty(document.getElementById("boqNumber_edit").value))
	{
		$.post("workItemDetails.htm",
				  { itemDescription: document.getElementById("itemDescription_edit").value },
				  function(xml){
					  if($(xml).find("boqNumber"))
					  	document.getElementById("boqNumber_edit").value = $(xml).find("boqNumber").text();
					  
					  if($(xml).find("sequence"))
						  	document.getElementById("sequence_edit").value = $(xml).find("sequence").text();
					  
					  	var layerCombo = document.getElementById("layers_edit");
	  					for (var i=0; i < layerCombo.length; i++)
	  					{
	  							layerCombo[i].selected = false;
	  					}
	  					
	  					var sideCombo = document.getElementById("sides_edit");
	  					for (var i=0; i < sideCombo.length; i++)
	  					{
	  							sideCombo[i].selected = false;
	  					}
	  					
	  					var activityCombo = document.getElementById("activities_edit");
	  					for (var i=0; i < activityCombo.length; i++)
	  					{
	  							activityCombo[i].selected = false;
	  					}
	  					
	  					
					  
					  $(xml).find("layer").each(function() 
								{
					  				var option = $(this).text();
					  				var layerCombo = document.getElementById("layers_edit");
				  					for (var i=0; i < layerCombo.length; i++)
				  					{
				  						if (layerCombo[i].value == option)
				  						{
				  							layerCombo[i].selected = true;
				  						}
				  					}
					  			});
					  
					  $(xml).find("side").each(function() 
								{
					  				var option = $(this).text();
					  				var sideCombo = document.getElementById("sides_edit");
				  					for (var i=0; i < sideCombo.length; i++)
				  					{
				  						if (sideCombo[i].value == option)
				  						{
				  							sideCombo[i].selected = true;
				  						}
				  					}
					  			});
					  
					  $(xml).find("activity").each(function() 
								{
						  			var option = $(this).text();
					  				var activityCombo = document.getElementById("activities_edit");
				  					for (var i=0; i < activityCombo.length; i++)
				  					{
				  						if (activityCombo[i].value == option)
				  						{
				  							activityCombo[i].selected = true;
				  						}
				  					}
					  			});
			});
		
		
	}
}

function add()
{
	if(isEmpty(document.getElementById("itemDescription").value))
	{
		alert("Description cannot be blank!");
		return false;
	}
	
	if(isEmpty(document.getElementById("boqNumber").value))
	{
		alert("BOQ No. cannot be blank!");
		return false;
	}
	
	if(isEmpty(document.getElementById("sequence").value))
	{
		alert("Please enter a sequence number!");
		return false;
	}
	
	if(!isInteger(document.getElementById("sequence").value))
	{
		alert("Sequence No. should be a non-negative integer!");
		return false;
	}
	
	if(isEmpty(document.getElementById("layers").value))
	{
		alert("Please select a layer!");
		return false;
	}
	
	if(isEmpty(document.getElementById("sides").value))
	{
		alert("Please select a side!");
		return false;
	}
	
	if(isEmpty(document.getElementById("activities").value))
	{
		alert("Please select a activity!");
		return false;
	}
	
	document.getElementById("actionType").value = "add";
	return true;
	
}

function edit()
{
	if(isEmpty(document.getElementById("itemDescription_edit").value))
	{
		alert("Select a Description!");
		return false;
	}
	
	if(isEmpty(document.getElementById("boqNumber_edit").value))
	{
		alert("BOQ No. cannot be blank!");
		return false;
	}
	
	if(isEmpty(document.getElementById("sequence_edit").value))
	{
		alert("Please enter a sequence number!");
		return false;
	}
	
	if(!isInteger(document.getElementById("sequence_edit").value))
	{
		alert("Sequence No. should be a non-negative integer!");
		return false;
	}
	
	if(isEmpty(document.getElementById("layers_edit").value))
	{
		alert("Please select a layer!");
		return false;
	}
	
	if(isEmpty(document.getElementById("sides_edit").value))
	{
		alert("Please select a side!");
		return false;
	}
	
	if(isEmpty(document.getElementById("activities_edit").value))
	{
		alert("Please select a activity!");
		return false;
	}
	
	document.getElementById("actionType_edit").value = "edit";
	return true;
	
}


function deleteWorkItem()
{
	if(isEmpty(document.getElementById("itemDescription_edit").value))
	{
		alert("Select a Description!");
		return false;
	}
	//document.getElementById("newItemDescription_edit").value = document.getElementById("itemDescription").value;
	document.getElementById("actionType_edit").value = "delete";
	return true;
}

</script>

	
</head>

<body>
<table id="container" style="width:100%;" cellspacing="0" cellpadding="0">
	<tr >
		<td valign="top" style="border-bottom:1 px solid gray;padding-right:5;padding-bottom:10">
				<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Add Work Item</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="workItemForm" method="POST" commandName="workItem" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<input type="hidden" id="actionType" name="actionType" value="add">
							<table style="width:100%">
								<tr>
									<td>
										<label><font size="4">Description</font></label>
									</td>
									<td colspan="3">
										<form:input id="itemDescription" path="itemDescription" cssStyle="width:100%"/> 
									</td>
									
									<td>
										<label><font size="4">Sequence</font></label>
									</td>
									<td>
										<form:input id="sequence" path="sequence"/> 
									</td>
									
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Activities</font></label>
									</td>
									<td style="border:0" >
										<form:select id="activities" path="activities" size="2" multiple="multiple"> 
											<form:options items="${activityList}"/> 
										</form:select> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">BOQ No.</font></label>
									</td>
									<td>
										<form:select id="boqNumber" path="boqNumber"> 
											<form:options items="${boqNumberList}"/> 
										</form:select>
									</td>
									<td>
										<label><font size="4">Layers</font></label>
									</td>
									<td style="border:0">
										<form:select id="layers" path="layers" multiple="multiple" size="2"> 
											<form:options items="${layerList}"/> 
										</form:select>
									</td>
									<td>
										<label><font size="4">Sides</font></label>
									</td>
									<td style="border:0" >
										<form:select id="sides" path="sides" size="2" multiple="multiple"> 
											<form:options items="${sideList}"/> 
										</form:select> 
									</td>
								</tr>
								
								<tr>
									<td colspan="6">
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
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Edit Work Item</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="workItemForm" method="POST" commandName="workItem" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<input type="hidden" id="actionType_edit" name="actionType" value="edit">
							<table style="width:100%">
								<tr>
									<td>
										<label><font size="4">Description</font></label>
									</td>
									<td colspan="3">
										<form:select id="itemDescription_edit" cssStyle="width:100%" path="itemDescription" onchange="fetchWorkItemDetails()"> 
											<form:options items="${workItemDescriptionList}"/> 
										</form:select> 
									</td>
									
									<td>
										<label><font size="4">Sequence</font></label>
									</td>
									<td>
										<form:input id="sequence_edit" path="sequence"/> 
									</td>
									
									
								</tr>
								<tr>
									<td>
										<label><font size="4">Activities</font></label>
									</td>
									<td style="border:0" >
										<form:select id="activities_edit" path="activities" size="2" multiple="multiple"> 
											<form:options items="${activityList}"/> 
										</form:select> 
									</td>
								</tr>
								<tr>
									<td>
										<label><font size="4">BOQ No.</font></label>
									</td>
									<td>
										<form:select id="boqNumber_edit" path="boqNumber"> 
											<form:options items="${boqNumberList}"/> 
										</form:select>
									</td>
									<td>
										<label><font size="4">Layers</font></label>
									</td>
									<td style="border:0">
										<form:select id="layers_edit" path="layers" multiple="multiple" size="2"> 
											<form:options items="${layerList}"/> 
										</form:select>
									</td>
									<td>
										<label><font size="4">Sides</font></label>
									</td>
									<td style="border:0" >
										<form:select id="sides_edit" path="sides" size="2" multiple="multiple"> 
											<form:options items="${sideList}"/> 
										</form:select> 
									</td>
								</tr>
								
								<tr>
									<td colspan="6">
										<input id="submitButton" type="submit" value="Edit" onClick="return edit()">
										<input id="submitButton" type="submit" value="Delete" onClick="deleteWorkItem()">
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
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Work Items List</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			
			
			
			<tr bgcolor="#F2F2F2">
					<th style="width:5%">
				   		Sequence
				   </th>
			       <th style="width:35%">
				   		Work Item
				   </th>
				   <th style="width:20%">
				   		Activities
				   </th>
				   <th style="width:10%">
				   		BOQ No.
				   </th>
				   <th style="width:15%">
				   		Layers
				   </th>
				   <th style="width:15%">
				   		Sides
				   </th>
			</tr>
			
			<c:forEach var="workItem" items="${workItemsList}">
			<tr>
				<td style="padding-left:5">
					${workItem.sequence}
				</td>
				<td style="text-align:left;">
					${workItem.itemDescription}
				</td>
				<td>
					${workItem.activities}
				</td>
				<td>
					${workItem.boqNumber}
				</td>
				<td>
					${workItem.layers}
				</td>
				<td>
					${workItem.sides}
				</td>
			</tr>
			</c:forEach>
		</table>
		
</td></tr>
</table>
     
</body>
</html> 
	