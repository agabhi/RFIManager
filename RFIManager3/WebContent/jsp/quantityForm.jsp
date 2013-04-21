<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.ExpandedRFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EDIT RFI</title>
    
    <script type="text/javascript" src="js/jquery.form.js"></script> 


    	

<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />



<script>

function isEmpty(value){
	   value= value.replace(/^\s+|\s+$/, '');
	    return (value === undefined || value == null || value.length <= 0) ? true : false;
	}


Array.prototype.contains = function (element) {
	   for (var i = 0; i < this.length; i++) {
	   if (this[i] == element) {
	   return true;
	   }
	   }
	   return false;
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


	function chainageCheck(fromvalue,tovalue,msg) {
		   //alert("chainagecheck");
		   //alert(fromvalue.value+","+tovalue.value+","+msg);
		   if (fromvalue == null || 
					tovalue == null)
			{
				
			}
			else{
				//alert("chainage check1");
				if(+fromvalue.value > +tovalue.value) {
					//alert("chainage check 2");
					//alert(fromvalue.value);
					//alert(tovalue.value);
					alert(msg);
					return false;
					
				}
				//alert("chainage check 3");
				
			}
		   return true;
		 }

	function chainageNumber(chainage,msg) {
		   //alert(chainage);
		   if (isInteger(chainage) == false) 
		    {
			   //alert("chainage number1");
		     	alert(msg);
		     	//alert("chainage number2");
		     	return false;
		    }
		   //alert("chainage number3");
		   return true;
	}



function changeActivity()
{
	document.getElementById("itemDescription").value = "";
	changeWorkItem();
}

function changeActivity_edit()
{
	document.getElementById("itemDescription_edit").value = "";
	changeWorkItem_edit();
}


function changeWorkItem()
{
	var itemDescription = document.getElementById("itemDescription").value;
	var url;
	if(!isEmpty(itemDescription))
	{
		url = "/RFIManager3/getBoqWorkItems.htm?itemDesc="+itemDescription;
	}
	else
	{
		url = "/RFIManager3/getBoqWorkItems.htm?noItemDesc=1";
	}
	
	$.post(url,
				  {  },
				  function(xml){
					  		var count = 0;
							var optionsSide = new Array();
							var sideBox = document.getElementById("side");
					  		$(xml).find("side").each(function() 
									{
							  			if( $(this).text() != "BS")
					  					{
							  				optionsSide[count] = $(this).text();
										 	++count;
					  					}
									});
							  		sideBox.options.length=optionsSide.length;
							  		for(var i=0;i<optionsSide.length;i++)
									{
										sideBox.options[i].text=optionsSide[i];
										sideBox.options[i].value=optionsSide[i];
									}
							  		
					  		count = 0;
							var optionsLayer = new Array();
							var layerBox = document.getElementById("layer");
					  		$(xml).find("layer").each(function() 
									{
					  				optionsLayer[count] = $(this).text();
								 		++count;
									});
							  		layerBox.options.length=optionsLayer.length;
							  		for(var i=0;i<optionsLayer.length;i++)
									{
										layerBox.options[i].text=optionsLayer[i];
										layerBox.options[i].value=optionsLayer[i];
									}
					  
				  });
	
}


function changeWorkItem_edit()
{
	var itemDescription = document.getElementById("itemDescription_edit").value;
	var url;
	if(!isEmpty(itemDescription))
	{
		url = "/RFIManager3/getBoqWorkItems.htm?itemDesc="+itemDescription;
	}
	else
	{
		url = "/RFIManager3/getBoqWorkItems.htm?noItemDesc=1";
	}
	
	$.post(url,
				  {  },
				  function(xml){
					  		var count = 0;
							var optionsSide = new Array();
							var sideBox = document.getElementById("side_edit");
					  		$(xml).find("side").each(function() 
									{
					  			
							  			if( $(this).text() != "BS")
					  					{
							  				optionsSide[count] = $(this).text();
										 	++count;
					  					}
									});
							  		sideBox.options.length=optionsSide.length;
							  		for(var i=0;i<optionsSide.length;i++)
									{
										sideBox.options[i].text=optionsSide[i];
										sideBox.options[i].value=optionsSide[i];
									}
							  		
					  		count = 0;
							var optionsLayer = new Array();
							var layerBox = document.getElementById("layer_edit");
					  		$(xml).find("layer").each(function() 
									{
					  				optionsLayer[count] = $(this).text();
								 		++count;
									});
							  		layerBox.options.length=optionsLayer.length;
							  		for(var i=0;i<optionsLayer.length;i++)
									{
										layerBox.options[i].text=optionsLayer[i];
										layerBox.options[i].value=optionsLayer[i];
									}
					  
				  });
	
}


function addQuantity()
{
	if (!chainageNumber(document.getElementById("fromChainage").value,"From Chainage should be a non-negative Integer!")) {
		return false;
	}
	if (!chainageNumber(document.getElementById("toChainage").value,"To Chainage should be a non-negative Integer!")) {
		//alert("check3");
		return false;
	}
	if (!chainageCheck(document.getElementById("fromChainage"),document.getElementById("toChainage"),"From Chainage should be a less than To Chainage!")) {
		//alert("check5");
		return false;
	}
	
	if(isEmpty(document.getElementById("activity").value))
	{
		alert("Please select an Activity!");
		return false;
	}
	
	
	if(isEmpty(document.getElementById("itemDescription").value))
	{
		alert("Please select an Item!");
		return false;
	}
	
	if(isEmpty(document.getElementById("side").value))
	{
		alert("Please select a Side!");
		return false;
	}
	
	if(isEmpty(document.getElementById("layer").value))
	{
		alert("Please select a Layer!");
		return false;
	}
	
	if(!isNumeric(document.getElementById("quantity").value))
	{
		alert("Area should be a numeric value!");
		return false;
	}
	
	document.getElementById("actionType").value = "add";
	
	
	var queryString = $('#quantityAddForm').serialize();
	//alert(queryString);
	
	var url1 ="quantity.htm?"+queryString;
	
	$.post(url1,
				  {  },
				  function(xml){
					  if( $(xml).find("resultCode").text() == "success")
						  document.getElementById('quantityAddForm').reset();
						  $("#list").trigger("reloadGrid");alert($(xml).find("resultText").text()); 
				  });
	
	
	return false;
	
}

function editQuantity()
{
	if (!chainageNumber(document.getElementById("fromChainage_edit").value,"From Chainage should be a non-negative Integer!")) {
		return false;
	}
	if (!chainageNumber(document.getElementById("toChainage_edit").value,"To Chainage should be a non-negative Integer!")) {
		//alert("check3");
		return false;
	}
	if (!chainageCheck(document.getElementById("fromChainage_edit"),document.getElementById("toChainage_edit"),"From Chainage should be a less than To Chainage!")) {
		//alert("check5");
		return false;
	}
	
	if(isEmpty(document.getElementById("activity_edit").value))
	{
		alert("Please select an Activity!");
		return false;
	}
	
	if(isEmpty(document.getElementById("itemDescription_edit").value))
	{
		alert("Please select an Item!");
		return false;
	}
	
	if(isEmpty(document.getElementById("side_edit").value))
	{
		alert("Please select a Side!");
		return false;
	}
	
	if(isEmpty(document.getElementById("layer_edit").value))
	{
		alert("Please select a Layer!");
		return false;
	}
	
	if(!isNumeric(document.getElementById("quantity_edit").value))
	{
		alert("Area should be a numeric value!");
		return false;
	}
	
	document.getElementById("actionType_edit").value = "edit";
	
	
	var queryString = $('#quantityEditForm').serialize();
	//alert(queryString);
	
	var url1 ="quantity.htm?"+queryString;
	
	$.post(url1,
				  {  },
				  function(xml){
					  if( $(xml).find("resultCode").text() == "success")
						  document.getElementById('quantityEditForm').reset();
						  $("#list").trigger("reloadGrid");alert($(xml).find("resultText").text()); 
				  });
	
	
	return false;

	
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


	

<script type="text/javascript">

$(function(){ 
  var mygrid = $("#list").jqGrid({
    url:'quantityXml.htm',
    datatype: 'xml',
    mtype: 'POST',
    colNames:['QuantityId', 'Activity', 'Item','From','To','Side', 'Layer','Area'],
    colModel :[
	  {name:'quantityId', index:'quantity_id', align:'left', searchoptions:{sopt:['eq','ne']}},
	  {name:'activity', index:'activity', align:'left', searchoptions:{sopt:['eq','ne']}},
	  {name:'item', index:'item_description', align:'left', searchoptions:{sopt:['eq','ne']}}, 
      {name:'fromChainage', index:'from_chainage', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}, searchrules:{custom:true, custom_func:function(value){if(!isNumeric(value))return [false,"From field should be numeric"]; else return [true,""]} }}, 
      {name:'toChainage', index:'to_chainage', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}, searchrules:{custom:true, custom_func:function(value){if(!isNumeric(value))return [false,"To field should be numeric"]; else return [true,""]} }},
      
      {name:'side', index:'side', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'layer', index:'layer', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'quantity', index:'quantity', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}
    ],
    pager: '#pager',
    rowNum:100,
    rowList:[100,500,1000],
    sortname: 'item_description',
    sortorder: 'desc',
    viewrecords: true,
    gridview: true,
    //width: "1200",
    //shrinkToFit:false,
    autowidth: true,
    //forceFit:true,
    height:'100%',
    multiselect: true,
    caption: 'Quantities List'
  });

  $(window).bind('resize', function() {
	  var width = jQuery("#container").attr('clientWidth');
	    $("#list").setGridWidth(width);
	}).trigger('resize');

jQuery("#list").jqGrid('navGrid','#pager',
		  {
		  	edit:false,add:false,del:false,search:true
		  },
		  {}, // edit options
		  {}, // add options
		  {}, //del options
		  {multipleSearch:true, multipleGroup:true, showQuery: true, closeAfterSearch:true, closeAfterReset:true, beforeShowSearch:function(){mygrid[0].clearToolbar();}} // search options
		  );


jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Edit",
	onClickButton:function(){
		var gsr = jQuery("#list").jqGrid('getGridParam','selrow');
		if(gsr){
			var sel_id = $("#list").jqGrid('getGridParam', 'selrow');
		  	var quantityId = $("#list").jqGrid('getCell', sel_id, 'quantityId');
		  	$.post("quantityDetails.htm",
					  { quantityId: quantityId },
					  function(xml){
						 var i = 0;
						$(xml).find("cell").each(function() 
						{
							if( i == 0)
								document.getElementById("quantityId_edit").value = $(this).text();
							
							if( i == 1)
							{
								document.getElementById("activity_edit").value = $(this).text();
								changeActivity_edit();
							}
							
							if( i == 2)
							{
								document.getElementById("itemDescription_edit").value = $(this).text();
								changeWorkItem_edit();
							}
							
							if( i == 3)
								document.getElementById("fromChainage_edit").value = $(this).text();
							
							if( i == 4)
								document.getElementById("toChainage_edit").value = $(this).text();
							
							if( i == 5)
								document.getElementById("side_edit").value = $(this).text();
							
							if( i == 6)
								document.getElementById("layer_edit").value = $(this).text();
							
							if( i == 7)
								document.getElementById("quantity_edit").value = $(this).text();
							
							++i;
						});
					  });
		} else {
			alert("Please select Row");
		}
	} 
});

jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Delete",
	onClickButton:function(){
		
		var s;
		s = jQuery("#list").jqGrid('getGridParam','selarrrow');
		if(s)
		{
			if (s.length > 0)
			{
				var c = 0;
				var url = 'quantityXml.htm?';
				for(c = 0; c < s.length; ++c)
				{
					url = url + "quantityId=" + jQuery("#list").getCell(s[c],'quantityId') +"&";
					
					//myWindow.document.write("Print RFI Number "+jQuery("#list").getCell(s[c],'rfiNumber'));
					//myWindow.document.write("Print RFI Number "+jQuery("#list").getCell(s[c],'rfiNumber'));
					    
				}
				//var myWindow = window.open(url,'','width=0,height=0');
				//myWindow.print();
				$.post(url,
				  {deleteQuantity : 'true'  },
				  function(xml){
					  $("#list").trigger("reloadGrid");alert("Quantities deleted successfully!!");
				  });
		
			}
			else
			{
				alert("No Area selected to delete!");
					
			}
		}
		else
		{
			alert("No Area selected to delete!");
				
		}
	} 

});



 jQuery("#list").jqGrid('filterToolbar',{stringResult: false,searchOnEnter : false, beforeSearch:function(){var grid = $("#list");
 grid.jqGrid('setGridParam',{search:false});

 var postData = grid.jqGrid('getGridParam','postData');
 $.extend(postData,{filters:""});
 // for singe search you should replace the line with
 $.extend(postData,{searchField:"",searchString:"",searchOper:""});

// grid.trigger("reloadGrid",[{page:1}]);
 }});
//jQuery("#list").jqGrid('filterToolbar');
// $(".collapsibleContainerContent").slideToggle(); 
}); 

function doOnLoad()
{
	changeActivity();
	changeWorkItem();
	changeActivity_edit();
	changeWorkItem_edit();
}

</script>



	
	
</head>

<body onload="doOnLoad();">


<table id="container" width="100%" cellspacing="0" cellpadding="0">
	<tr >
		<td valign="top" style="border-bottom:1 px solid gray;padding-right:5;padding-bottom:10">
				<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Add Area</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			    </tr> 
				<tr>
					<td>
						<form:form id="quantityAddForm" method="POST" commandName="quantity" cssStyle="margin: 0px; padding: 0px;">
						<form:errors path="*" />
		
						<input type="hidden" id="actionType" name="actionType" value="add">
						
						<table>	
						
								<tr>
									<td>
										<label><font size="4">Activity</font></label>
									</td>
									<td style="border:0" colspan="3">
										<form:select id="activity" path="activity" onchange="changeActivity()"> 
											<form:options items="${activityList}"/> 
										</form:select>
									</td>
									
								</tr>					
						
								<tr>
									<td>
										<label><font size="4">Description</font></label>
									</td>
									<td style="border:0" colspan="3">
										<form:select id="itemDescription" path="itemDescription" onchange="changeWorkItem()"> 
											<form:options items="${workItemDescriptionList}"/> 
										</form:select>
									</td>
									
								</tr>
									
								<tr>
									<td style="border:0"><label><font size="4"><font color="#000066"></font>From Chainage</font></font></label></td><td style="border:0"><form:input id="fromChainage" path="fromChainage" /></td>
									<td style="border:0"><label><font size="4">To Chainage</font></label></td><td style="border:0"><form:input id="toChainage" path="toChainage" /></td>
								</tr>
														
								<tr>
									<td>
										<label><font size="4">Layer</font></label>
									</td>
									<td style="border:0">
										<form:select id="layer" path="layer"> 
											<form:options items="${layerList}"/> 
										</form:select>
									</td>
									<td>
										<label><font size="4">Side</font></label>
									</td>
									<td style="border:0" >
										<form:select id="side" path="side"> 
											<form:options items="${sideList}"/> 
										</form:select> 
									</td>
								</tr>
														
								<tr>
									<td>
										<label><font size="4">Area</font></label>
									</td>
									<td style="border:0" colspan="3">
										<form:input id="quantity" path="quantity" />
									</td>
								</tr>
								
								<tr>
									<td colspan="4">
										<input id="submitButton" type="submit" value="Add" onClick="return addQuantity()">
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
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Edit Area</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr> 
				<tr>
					<td>
						<form:form id="quantityEditForm" method="POST" commandName="quantity" cssStyle="margin: 0px; padding: 0px;">
							<form:errors path="*" />
							<form:hidden id="quantityId_edit" path="quantityId" />
							<input type="hidden" id="actionType_edit" name="actionType" value="edit">
							<table>
							
							
								<tr>
									<td>
										<label><font size="4">Activity</font></label>
									</td>
									<td style="border:0" colspan="3">
										<form:select id="activity_edit" path="activity" onchange="changeActivity_edit()"> 
											<form:options items="${activityList}"/> 
										</form:select>
									</td>
									
								</tr>
								
								
								<tr>
									<td>
										<label><font size="4">Description</font></label>
									</td>
									<td style="border:0" colspan="3">
										<form:select id="itemDescription_edit" path="itemDescription" onchange="changeWorkItem_edit()"> 
											<form:options items="${workItemDescriptionList}"/> 
										</form:select>
									</td>
									
								</tr>
								
								<tr>
		       							<td style="border:0"><label><font size="4"><font color="#000066"></font>From Chainage</font></font></label></td><td style="border:0"><form:input id="fromChainage_edit" path="fromChainage" /></td>
			   							<td style="border:0"><label><font size="4">To Chainage</font></label></td><td style="border:0"><form:input id="toChainage_edit" path="toChainage" /></td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Layer</font></label>
									</td>
									<td style="border:0">
										<form:select id="layer_edit" path="layer"> 
											<form:options items="${layerList}"/> 
										</form:select>
									</td>
									<td>
										<label><font size="4">Side</font></label>
									</td>
									<td style="border:0" >
										<form:select id="side_edit" path="side"> 
											<form:options items="${sideList}"/> 
										</form:select> 
									</td>
								</tr>
								
								<tr>
									<td>
										<label><font size="4">Area</font></label>
									</td>
									<td style="border:0" colspan="3">
										<form:input id="quantity_edit" path="quantity" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<input id="submitButton" type="submit" value="Edit" onClick="return editQuantity()">
									</td>
									
								</tr>
								
							</table>
						</form:form>
					</td>
				</tr> 
			</table>
		</td>
		
		<td valign="top" style="border-bottom:1 px solid gray;padding-bottom:10">
				<table class="curvedEdges" style="width:100%;height:150%" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
					<tr>
						<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;">
							<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
								<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
				        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Import Area List</label>
				        		</td></tr>
				        	</table>
				        	
				        </td>
				     </tr> 
				
					<tr>
						<td style="padding-top:12;padding-bottom:0;font-size:large">
							<form:form commandName="fileUpload" action="quantityFileUpload.htm" enctype="multipart/form-data" cssStyle="margin: 0px; padding: 0px;">
							<p style="font-family: Verdana, Geneva, sans-serif;">
								Please specify a file:<br><br>
							<input type="file" name="file" style="width:100%;font-size:large;font-family: Verdana,Geneva,sans-serif;"/>
							</p>
							<br>
							<input type="submit" value="Import" style="font-size:large;"/>
								
							</form:form>
						</td>
					</tr>
			</table>
			
		</td>
		
		
	</tr>
	
	<tr>
			<td colspan="3">
				<br>
				<table id="list"><tr><td /></tr></table> 
				<div id="pager"></div>
			</td>
	</tr>

	
	
</table>
 
</body>
</html> 
	