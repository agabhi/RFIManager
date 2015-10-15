<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.ExpandedRFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EDIT RFI</title>
	<script src="codebase/dhtmlxcommon.js"></script>
    
    <link rel="stylesheet" type="text/css" href="codebase/dhtmlxcalendar.css"></link>
    <link rel="stylesheet" type="text/css" href="codebase/dhtmlxcalendar_dhx_skyblue.css"></link>
    
    <script src="codebase/dhtmlxcalendar.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script> 
    <script src="script/rfi.js"></script>


    	

<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />



<script>

function changeActivity()
{
	document.getElementById("item").value = "";
	changeWorkItem();
	document.getElementById("boqNumber").value = "";
	changeBoqNumber();
}


function changeBoqNumber()
{
	var boqNumber = document.getElementById("boqNumber").value;
	var activity = document.getElementById("activity").value;
		if(!isEmpty(boqNumber))
		{
			url = "<%=request.getContextPath()%>/getBoqWorkItems.htm?boqNumber="+encodeURIComponent(boqNumber)+"&activity="+encodeURIComponent(activity);
			jQuery.ajaxSetup({async:false});
			itemURL(url);
			jQuery.ajaxSetup({async:true});
			if(document.getElementById("item").options.length > 1)
			{
				document.getElementById("item").options[1].selected = true;
			}
		}
		else
		{
			url = "<%=request.getContextPath()%>/getBoqWorkItems.htm?noItemDesc=1"+"&activity="+encodeURIComponent(activity);
			jQuery.ajaxSetup({async:false});
			boqURL(url);
			jQuery.ajaxSetup({async:true});
		}
}	 
	
function itemURL(url)
{
		$.post(url,
				  {  },
				  function(xml){
					  var count = 1;
					  var options1 = new Array();
					  options1[0] = "";
					  var itemBox = document.getElementById("item");
					  $(xml).find("item").each(function() 
							{
						 		options1[count] = $(this).text();
						 		++count;
							});
					  		
					  		itemBox.options.length=options1.length;
					  		for(var i=0;i<options1.length;i++)
							{
								itemBox.options[i].value=options1[i];
								if(options1[i] == "")
								{
									itemBox.options[i].text="-Select Item-";
								}
								else
								{
									itemBox.options[i].text=options1[i];
								}
							}
					count = 0;
					var optionsSide = new Array();
					var sideBox = document.getElementById("side");
			  		$(xml).find("side").each(function() 
							{
			  				optionsSide[count] = $(this).text();
						 		++count;
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


function changeWorkItem()
{
	var itemDescription= document.getElementById("item").value;
	var activity = document.getElementById("activity").value;
	if(!isEmpty(itemDescription))
	{
		url = "<%=request.getContextPath()%>/getBoqWorkItems.htm?itemDesc="+encodeURIComponent(itemDescription)+"&activity="+encodeURIComponent(activity);
		jQuery.ajaxSetup({async:false});
		boqURL(url);
		jQuery.ajaxSetup({async:true});
		if(document.getElementById("boqNumber").options.length > 1)
		{
			document.getElementById("boqNumber").options[1].selected = true;
		}
	}
	else
	{
		url = "<%=request.getContextPath()%>/getBoqWorkItems.htm?noBoqNumber=1"+"&activity="+encodeURIComponent(activity);
		jQuery.ajaxSetup({async:false});
		itemURL(url);
		jQuery.ajaxSetup({async:true});
	}
}	
function boqURL(url)
{
	
	$.post(url,
				  {  },
				  function(xml){
					  var count = 1;
					  var options1 = new Array();
					  options1[0] = "";
					  var itemBox = document.getElementById("boqNumber");
					  $(xml).find("boqNumber").each(function() 
							{
						  		options1[count] = $(this).text();
						 		++count;
							});
					  		itemBox.options.length=options1.length;
					  		for(var i=0;i<options1.length;i++)
							{
								itemBox.options[i].value=options1[i];
								if(options1[i] == "")
								{
									itemBox.options[i].text="-Select BOQ-";
								}
								else
								{
									itemBox.options[i].text=options1[i];
								}
							}
					  		
					  		
					  		
			  		count = 0;
					var optionsSide = new Array();
					var sideBox = document.getElementById("side");
			  		$(xml).find("side").each(function() 
							{
			  				optionsSide[count] = $(this).text();
						 		++count;
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



function saveAndPrint(){  
	var rfiNum = document.getElementById("rfiNumber").value;
	clickSubmit("print", rfiNum);
	return false;
}

function save(){  
	clickSubmit("true");
	return false;
}
</script>

 
 
<script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.mi.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>

<script src="js/JsonXml.js" type="text/javascript"></script>
<script src="js/grid.import.js" type="text/javascript"></script>


	<link rel="stylesheet" href="jqtransformplugin/jqtransform.css" type="text/css" media="all" />
	<script type="text/javascript" src="jqtransformplugin/jquery.jqtransform.js" ></script>
	
	<link rel="stylesheet" href="js/diQuery-collapsiblePanel.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/diQuery-collapsiblePanel.js" ></script>
	
	
	<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        $(".collapsibleContainer").collapsiblePanel();
    });
    
</script>
	
	

<style>
.ui-jqgrid {font-size:0.9em}

table.curvedEdges { border:1px solid lightgray;-webkit-border-radius:8px;-moz-border-radius:8px;border-radius:8px; }
table.curvedEdges td table.curvedEdges th { border-bottom:1px dotted black;padding:5px; }

table.curvedEdges1 { border:1px solid lightgray;-webkit-border-top-left-radius:8px;-moz-border-top-left-radius:8px;border-top-left-radius:8px;-webkit-border-top-right-radius:8px;-moz-border-top-right-radius:8px;border-top-right-radius:8px; }
table.curvedEdges1 td table.curvedEdges1 th { border-bottom:1px dotted black;padding:5px; }

</style>


<script type="text/javascript">
/*
$(function() {
    //find all form with class jqtransform and apply the plugin
	$('form.jqtransform').jqTransform({imgPath:'jqtransformplugin/img/'});
});
*/
</script>

	
   <script>
   
  
      
   var row_clicked_number = 0;   
   var approval_row_counter = 0;
   var bill_row_counter = 1;   

   
   var myCalendar1;
   var myCalendar2;
   
   var approvalDate = new Array();
	
	
	
   //myCalendar.show();
</script>	
	

<script type="text/javascript">
function loadRFIDetails(checkSelRow)
{
	var rfiNumber;
	var billNumber;
	if(checkSelRow == true)
	{
		var sel_id = $("#list").jqGrid('getGridParam', 'selrow');
	  	rfiNumber = $("#list").jqGrid('getCell', sel_id, 'rfiNumber');
	  	billNumber = $("#list").jqGrid('getCell', sel_id, 'billNumber');
	}
	else
	{
		rfiNumber = document.getElementById("rfiNumber").value;
	}
	  //alert(myCellData);
	
	if(!isEmpty(billNumber))
	{
		alert("You can't edit this RFI because this RFI is already billed or included in the current bill!");
		return false;
	}

	$.post("rfiDetails.htm",
			  { rfiNumber: rfiNumber },
			  function(xml){
				myCalendar2.clearSensitiveRange();
				removeAllApprovals();
				document.getElementById("breakIndicator").checked = false;
				swapBreakRfi();
				//dblclick(0,0);
				document.getElementById("rfiHeader").innerText="Edit RFI Number: "+$(xml).find("rfiNumber").text();
			    //alert("Data Loaded: " + xml);
			    //alert($(xml).find("rfiNumber").text());
			    document.getElementById("rfiNumber").value = $(xml).find("rfiNumber").text();
			    document.getElementById("rfiCode").value = $(xml).find("rfiCode").text();
			    document.getElementById("activity").value = $(xml).find("activity").text();
			    changeActivity();
			    
			    
			    document.getElementById("status").value = $(xml).find("status").text();
			    document.getElementById("fromChainage").value = $(xml).find("fromChainage").text();
			    document.getElementById("toChainage").value = $(xml).find("toChainage").text();
			    //document.getElementById("billNumber").value = $(xml).find("billNumber").text();
			    var item = $(xml).find("itemDescription").text();
			    item = item.replace("&amp;", "&");
			    document.getElementById("item").value = item;
			    changeWorkItem();
			    document.getElementById("wiRemarks").value = $(xml).find("wiRemarks").text();
			    document.getElementById("boqNumber").value = $(xml).find("boqNumber").text();
			    document.getElementById("issueDate").value = $(xml).find("issueDateAsText").text();
			    myCalendar1.setDate(document.getElementById("issueDate").value);
			    document.getElementById("inspectionDate").value = $(xml).find("inspectionDateAsText").text();
			    myCalendar2.setDate(document.getElementById("inspectionDate").value);
			    document.getElementById("remarks").value = $(xml).find("remarks").text();
			    document.getElementById("grade").value = $(xml).find("grade").text();
			    document.getElementById("side").value = $(xml).find("side").text();
			    document.getElementById("layer").value = $(xml).find("layer").text();
			    var i = 1;
				var approvalCounter = 1;
				$(xml).find("approval").each(function() 
					{
						//alert($(this).find("approvedFrom").text());
						var approvedFrom = $(this).find("approvedFrom").text();
						var approvedTo = $(this).find("approvedTo").text();
						var approvedSide = $(this).find("approvedSide").text();
						var approvedLayer = $(this).find("approvedLayer").text();
						var approvalDate1 = $(this).find("approvalDateAsText").text();
						var breadth = $(this).find("breadth").text();
						var depth = $(this).find("depth").text();
						var area = $(this).find("area").text();
						var quantity = $(this).find("quantity").text();
						
						
						if(!isEmpty(approvedFrom))
						{
							 createApproval();
							 //alert("Inside if - approvalFrom"+i+" = "+mygrid.cellById(ids[i-1][0], 14).getValue());
							 document.getElementById("approvalFrom"+approvalCounter).value = approvedFrom;
							 document.getElementById("approvalTo"+approvalCounter).value = approvedTo;
							 document.getElementById("approvalSide"+approvalCounter).value = approvedSide;
							 document.getElementById("approvalLayer"+approvalCounter).value = approvedLayer;
							 document.getElementById("breadth"+approvalCounter).value = breadth;
							 document.getElementById("depth"+approvalCounter).value = depth;
							 document.getElementById("area"+approvalCounter).value = area;
							 document.getElementById("quantity"+approvalCounter).value = quantity;
							 document.getElementById("approvalDate"+approvalCounter).value = approvalDate1;
							 approvalDate[approvalCounter-1].setDate(document.getElementById("approvalDate"+approvalCounter).value);
							 approvalCounter=approvalCounter+1;
						}
					 	else
					 	{
					 		createApproval();
						 	document.getElementById("approvalFrom"+approvalCounter).value = approvedFrom;
						 	document.getElementById("approvalTo"+approvalCounter).value = approvedTo;
						 	document.getElementById("approvalSide"+approvalCounter).value = approvedSide;
						 	document.getElementById("approvalLayer"+approvalCounter).value = approvedLayer;
						 	document.getElementById("breadth"+approvalCounter).value = breadth;
						 	document.getElementById("depth"+approvalCounter).value = depth;
						 	document.getElementById("area"+approvalCounter).value = area;
						 	document.getElementById("quantity"+approvalCounter).value = quantity;
						 	approvalCounter=approvalCounter+1;
					 	}
					});
							 
						i=i+1;
						statusChange();
						enable("rfiDetailsTable");
						myCalendar2.setSensitiveRange(myCalendar1.getFormatedDate()+" 00:00", null);
						var dat = myCalendar2.getDate();
						var formatedInspectionDate = dat.getDate()+"-"+(+dat.getMonth()+1)+"-"+dat.getFullYear();
						//alert("Inspection date is - " + formatedInspectionDate);
						approvalDate[0].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[1].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[2].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[3].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[4].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[5].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[6].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[7].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[8].setSensitiveRange(formatedInspectionDate, null);
						approvalDate[9].setSensitiveRange(formatedInspectionDate, null);
					  
			});
}

</script>


<script>
$(function(){ 
  var mygrid = $("#list").jqGrid({
    url:'rfiXml.htm',
    datatype: 'xml',
    mtype: 'POST',
    colNames:['RFI Code','RFI No.', 'Issue date','BOQ No.', 'Activity', 'Item','WI Remarks', 'From','To','Side', 'Layer','Status','Gr.','Insp Date','Created By', 'Updated by','Appr From','Appr To','Appr Side', 'Appr Layer','Appr Date','Bill No.','Quantity', 'Remarks'],
    colModel :[ 
	  {name:'rfiCode', index:'rfi_code', width:'40', searchoptions:{sopt:['eq','ne']}}, 
      {name:'rfiNumber', index:'rfi_number', width:'60', align:'right', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'issueDate', index:'issue_date', width:'100', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      //{name:'issueDate', index:'issue_date', width:'5', align:'right', search:true, searchoptions:{dataInit:function(el){$(el).datepicker({dateFormat:'yy-mm-dd',onClose: function(dateText, inst) {$("#list")[0].triggerToolbar();}});} }}, 
      {name:'boqNumber', index:'boq_number', width:'60', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'activity', index:'activity', width:'80', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'item', index:'item_description', width:'400', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'wiRemarks', index:'wi_remarks', width:'200', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'fromChainage', index:'from_chainage', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}, searchrules:{custom:true, custom_func:function(value){if(!isNumeric(value))return [false,"From field should be numeric"]; else return [true,""]} }}, 
      {name:'toChainage', index:'to_chainage', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}, searchrules:{custom:true, custom_func:function(value){if(!isNumeric(value))return [false,"To field should be numeric"]; else return [true,""]} }},
      
      {name:'side', index:'side', width:'80', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'layer', index:'layer', width:'80', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'status', index:'status', width:'100', searchoptions:{sopt:['eq','ne']}},
      {name:'grade', index:'grade', width:'60', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'inspectionDate', index:'inspection_date', width:'140', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'createdBy', index:'created_by_username', width:'100', searchoptions:{sopt:['eq','ne']}}, 
      {name:'lastUpdatedBy', index:'last_edited_by_username', width:'100', searchoptions:{sopt:['eq','ne']}},
      
      {name:'approvedFrom', index:'approved_from', width:'90', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvedTo', index:'approved_to', width:'90', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvedSide', index:'approved_side', width:'90', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvedLayer', index:'approved_layer', width:'100', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvalDate', index:'approval_Date', width:'100', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'billNumber', index:'bill_number', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'quantity', index:'quantity', width:'60', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'remarks', index:'remarks', width:'500', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}
    ],
    pager: '#pager',
    rowNum:5,
    rowList:[5,10,20],
    sortname: 'rfi_number',
    sortorder: 'desc',
    viewrecords: true,
    gridview: true,
    //width: "1200",
    shrinkToFit:false,
    autowidth: true,
    //forceFit:true,
    height:'100%',
    multiselect: true,
    caption: 'RFI List'
  });

  $(window).bind('resize', function() {
	  var width = jQuery("#container").attr('clientWidth');
	    $("#list").setGridWidth(width);
	}).trigger('resize');

/*
jQuery(window).bind('resize', function() {

    // Get width of parent container
    var width = jQuery("#container").attr('clientWidth');
    if (width == null || width < 1){
        // For IE, revert to offsetWidth if necessary
        width = jQuery("#container").attr('offsetWidth');
    }
    width = width - 2; // Fudge factor to prevent horizontal scrollbars
    if (width > 0 &&
        // Only resize if new width exceeds a minimal threshold
        // Fixes IE issue with in-place resizing when mousing-over frame bars
        Math.abs(width - jQuery("#list").width()) > 5)
    {
        jQuery("#list").setGridWidth(width);
    }

}).trigger('resize');
*/
  jQuery("#list").jqGrid('navGrid','#pager',
		  {
		  	edit:false,add:false,del:false,search:true
		  },
		  {}, // edit options
		  {}, // add options
		  {}, //del options
		  {multipleSearch:true, multipleGroup:true, showQuery: true, closeAfterSearch:true, closeAfterReset:true, beforeShowSearch:function(){mygrid[0].clearToolbar();}} // search options
		  );

  //jQuery("#list").jqGrid('navGrid','#pager',{del:false,add:false,edit:false,search:true});
  /*
  jQuery("#list").jqGrid('navButtonAdd',"#pager",{caption:"Toggle",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s',
	onClickButton:function(){
		mygrid[0].toggleToolbar()
	} 
});
jQuery("#list").jqGrid('navButtonAdd',"#pager",{caption:"Clear",title:"Clear Search",buttonicon :'ui-icon-refresh',
	onClickButton:function(){
		alert("Clearing2");
		mygrid[0].clearToolbar();
		alert("Cleared");
	} 
});
*/

jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Edit",
	onClickButton:function(){
		var gsr = jQuery("#list").jqGrid('getGridParam','selrow');
		if(gsr){
			loadRFIDetails(true);
			//jQuery("#list").jqGrid('GridToForm',gsr,"#editForm");
		} else {
			alert("Please select Row");
		}
	} 
});

jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Export",
	onClickButton:function(){
		//alert(jQuery("#list").jqGridExport());
		 
		 jQuery("#list").excelExport({url:'rfiXml.htm'});
	} 
});


jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Print",
	onClickButton:function(){
		
		var s;
		s = jQuery("#list").jqGrid('getGridParam','selarrrow');
		if(s)
		{
			if (s.length > 0)
			{
				var c = 0;
				var url = 'rfiPrint.htm?';
				for(c = 0; c < s.length; ++c)
				{
					url = url + "rfiNumber=" + jQuery("#list").getCell(s[c],'rfiNumber') +"&";
					
					//myWindow.document.write("Print RFI Number "+jQuery("#list").getCell(s[c],'rfiNumber'));
					//myWindow.document.write("Print RFI Number "+jQuery("#list").getCell(s[c],'rfiNumber'));
					    
				}
				var myWindow = window.open(url,'','width=0,height=0');
				myWindow.print();
			}
			else
			{
				alert("No RFI selected to print!");
					
			}
		}
		else
		{
			alert("No RFI selected to print!");
				
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
 $(".collapsibleContainerContent").slideToggle(); 
}); 

</script>



	
	
</head>

<body onload="doOnLoad();">
<table id="container" style="width:100%;height:100%" cellspacing="0" cellpadding="0">
	<tr style="height:40%;width:100%">
		<td valign="top" style="width:100%">
			<table id="list" width="100%" height="80%"><tr style="width:100%"><td/></tr></table> 
			<div id="pager" style="width:100%"></div> 
		</td>
	</tr>
	<tr style="height:3%"><td bgcolor="#FFFFFF">&nbsp;</td></tr>
	<tr style="height:57%"><td valign="top" >
	
		<form:form method="POST" id="editForm" commandName="editRfi" name="editForm" cssStyle="margin: 0px; padding: 0px;" class="jqtransform">
			<form:errors path="*" />
		 	<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="6">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Edit RFI Number: </label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
			</tr>
			<tr>
				<td colspan="6" style="width:100%">
					<div class="collapsibleContainer" title="Click here to show/hide other details" style="width:100%">
							<table cellpadding="5" >
								<tr>
									<td style="font-family: Verdana, Geneva, sans-serif;width:10%"><label>Break this RFI</label></td>
									<td style="border:0;width:90%" colspan="5"><form:checkbox id="breakIndicator" path="breakIndicator" onchange="swapBreakRfi()"/></td>
								</tr>
								<tr>
									<td style="font-family: Verdana, Geneva, sans-serif;width:10%"><label>RFI Number</label></td>
									<td colspan="2" style="border:0;width:60%">
										<form:input id = "rfiNumber" name="rfiNumber" path="rfiNumber" readonly="true"/>
										<label id="breakSeperator">-</label>
										<form:input id = "breakRfiString" path="breakRfiString"/>
									</td>
									
									<td style="font-family: Verdana, Geneva, sans-serif;width:10%">
								        <label>RFI Type</label>
								        </td>
								        <td style="border:0;width:20%">
										<form:select id = "rfiCode" name="rfiCode" path="rfiCode"> 
											<form:options items="${rfiCodeList}" /> 
										</form:select> 
										</td>
										
										<td style="font-family: Verdana, Geneva, sans-serif;width:10%">
								        <label>BOQ No.</label>
								        </td>
								        <td style="border:0;width:20%">
										<form:select id = "boqNumber" name="boqNumber" path="boqNumber" onchange="changeBoqNumber()">
											<option value="">-Select BOQ-</option> 
											<form:options items="${boqNumberList}" /> 
										</form:select> 
										</td>
										
								</tr>
								<tr>
								       
										<td style="padding-left:5px;font-family: Verdana, Geneva, sans-serif;width:10%">
										<label>Activity</label>
								       </td>
								        <td style="border:0;width:30%">
										<form:select id = "activity" name="activity" path="activity" onchange="changeActivity()">
											<form:options items="${activityList}"/> 
										</form:select> 
										</td>
										
								</tr>
								<tr>
								       
										<td style="padding-left:5px;font-family: Verdana, Geneva, sans-serif;width:10%">
										<label>Item</label>
								       </td>
								        <td style="border:0;width:30%">
										<form:select id = "item" name="item" path="itemDescription" onchange="changeWorkItem()">
											<option value="">-Select Item-</option> 
											<form:options items="${itemsList}"/> 
										</form:select> 
										</td>
										
										<td style="font-family: Verdana, Geneva, sans-serif;width:10%">
									   <label>Layer</label>
									   </td>
								        <td style="border:0;width:20%">
										<form:select id = "layer" name="layer" path="layer"> 
										</form:select> 
										</td>
										
										<td style="font-family: Verdana, Geneva, sans-serif;width:10%"><label>Issue Date</label></td><td style="border:0;width:20%"><form:input id="issueDate" name="issueDate" path="issueDate" readonly="true"/></td>
										
								</tr>
								
								<tr>
			
									<td style="padding-left:20px;font-family: Verdana, Geneva, sans-serif;width:10%"><label>WI Remarks</label></td><td colspan="3" style="border:0;width:60%"><form:textarea id = "wiRemarks" name="wiRemarks" path="wiRemarks" /></td>
									<!-- 
										<td style="font-family: Verdana, Geneva, sans-serif;width:10%">
									   <label>Bill Number</label>
									   </td>
								        <td style="border:0;width:20%">
										<form:select id = "billNumber" path="billNumber"> 
											<form:options items="${billList}"/>  
										</form:select> 
										</td>
										-->
								</tr>
								
								<tr>
								       <td style="padding-left:5px;font-family: Verdana, Geneva, sans-serif;"><label>From Chainage</label></td><td style="border:0"><form:input id="fromChainage" name="fromChainage" path="fromChainage" /></td>
									   <td style="font-family: Verdana, Geneva, sans-serif;"><label>To Chainage</label></td><td style="border:0"><form:input id="toChainage" name="toChainage" path="toChainage" /></td>
									   
									   <td style="font-family: Verdana, Geneva, sans-serif;">
								        <label>Side</label>
								        </td>
								        <td style="border:0">
										<form:select id = "side" name = "side" path="side"> 
										</form:select> 
										</td>
								</tr>
						</table>
					</div>
			</td>
			</tr>
					
			<tr>
			       
				   <td style="padding-left:20px;font-family: Verdana, Geneva, sans-serif;width:1px"><label>Inspection Date</label></td><td style="border:0;width:30%"><form:input id ="inspectionDate" name="inspectionDate" path="inspectionDate" readonly="true"/></td>
			
			       
			       <td style="font-family: Verdana, Geneva, sans-serif;width:10%">
			        <label>Status</label>
			        </td>
			        <td style="border:0;width:20%">
					<form:select id = "status" path="status" onchange="statusChange();"> 
						<form:options items="${statusList}"/> 
					</form:select> 
					</td>
					
					<td style="font-family: Verdana, Geneva, sans-serif;width:10%">
			        	<label>Grade</label>
			     	</td>
		        	<td style="border:0;width:20%">
						<form:select id = "grade" path="grade">
							<option value="">-Select Grade-</option> 
							<form:options items="${gradeList}"/> 
						</form:select> 
					</td>
			       
				   
				   
				   
			</tr>
			<tr>
			
				<td style="padding-left:20px;font-family: Verdana, Geneva, sans-serif;width:10%"><label>Remarks</label></td><td colspan="3" style="border:0;width:60%"><form:textarea id = "remarks" name="remarks" path="remarks" /></td>
				<!-- 
					<td style="font-family: Verdana, Geneva, sans-serif;width:10%">
				   <label>Bill Number</label>
				   </td>
			        <td style="border:0;width:20%">
					<form:select id = "billNumber" path="billNumber"> 
						<form:options items="${billList}"/>  
					</form:select> 
					</td>
					-->
			</tr>
			<tr id="approvalBox">
			<td colspan="2" style="vertical-align:top;padding-left:20px;">
					<label style="font-family: Verdana, Geneva, sans-serif;">Approvals</label>&nbsp;
					<a href="javascript:createApproval();"><img src="images/add-icon.png"  width="15" height="15"/></a>&nbsp;
					<a href="javascript:removeApproval();"><img src="images/minus_icon.png" width="15" height="15"/></a>
					<hr/>
						<table id="approvalTable">
			
							<tr id="captionRow" style="display:none">
								<td style="border:0"><label>From</label></td>
								<td style="border:0"><label>To</label></td>
								<td style="border:0"><label>Side</label></td>
								<td style="border:0"><label>Layer</label></td>
								<td style="border:0"><label>Date</label></td>
								<td style="border:0"><label>Breadth</label></td>
								<td style="border:0"><label>Depth</label></td>
								<td style="border:0"><label>Area</label></td>
								<td style="border:0"><label>Quantity</label></td>
							</tr>
	
							<tr id="approvalRow1" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom1" path="approvalFrom1"/></td>
								<td style="border:0"><form:input id ="approvalTo1" path="approvalTo1"/></td>
								<td style="border:0">
										<form:select id = "approvalSide1" path="approvalSide1"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer1" path="approvalLayer1" disabled="true"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate1" path="approvalDate1" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth1" path="breadth1"/></td>
								<td style="border:0"><form:input id ="depth1" path="depth1"/></td>
								<td style="border:0"><form:input id ="area1" path="area1"/></td>
								<td style="border:0"><form:input id ="quantity1" path="quantity1"/></td>
							</tr>
			
			
							<tr id="approvalRow2" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom2" path="approvalFrom2"/></td>
								<td style="border:0"><form:input id ="approvalTo2" path="approvalTo2"/></td>
								<td style="border:0">
										<form:select id = "approvalSide2" path="approvalSide2"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer2" path="approvalLayer2"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate2" path="approvalDate2" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth2" path="breadth2"/></td>
								<td style="border:0"><form:input id ="depth2" path="depth2"/></td>
								<td style="border:0"><form:input id ="area2" path="area2"/></td>
								<td style="border:0"><form:input id ="quantity2" path="quantity2"/></td>
							</tr>
							
							<tr id="approvalRow3" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom3" path="approvalFrom3"/></td>
								<td style="border:0"><form:input id ="approvalTo3" path="approvalTo3"/></td>
								<td style="border:0">
										<form:select id = "approvalSide3" path="approvalSide3"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer3" path="approvalLayer3"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate3" path="approvalDate3" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth3" path="breadth3"/></td>
								<td style="border:0"><form:input id ="depth3" path="depth3"/></td>
								<td style="border:0"><form:input id ="area3" path="area3"/></td>
								<td style="border:0"><form:input id ="quantity3" path="quantity3"/></td>
							</tr>
							
							<tr id="approvalRow4" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom4" path="approvalFrom4"/></td>
								<td style="border:0"><form:input id ="approvalTo4" path="approvalTo4"/></td>
								<td style="border:0">
										<form:select id = "approvalSide4" path="approvalSide4"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer4" path="approvalLayer4"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate4" path="approvalDate4" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth4" path="breadth4"/></td>
								<td style="border:0"><form:input id ="depth4" path="depth4"/></td>
								<td style="border:0"><form:input id ="area4" path="area4"/></td>
								<td style="border:0"><form:input id ="quantity4" path="quantity4"/></td>
							</tr>
							
							<tr id="approvalRow5" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom5" path="approvalFrom5"/></td>
								<td style="border:0"><form:input id ="approvalTo5" path="approvalTo5"/></td>
								<td style="border:0">
										<form:select id = "approvalSide5" path="approvalSide5"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer5" path="approvalLayer5"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate5" path="approvalDate5" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth5" path="breadth5"/></td>
								<td style="border:0"><form:input id ="depth5" path="depth5"/></td>
								<td style="border:0"><form:input id ="area5" path="area5"/></td>
								<td style="border:0"><form:input id ="quantity5" path="quantity5"/></td>
							</tr>
							
							<tr id="approvalRow6" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom6" path="approvalFrom6"/></td>
								<td style="border:0"><form:input id ="approvalTo6" path="approvalTo6"/></td>
								<td style="border:0">
										<form:select id = "approvalSide6" path="approvalSide6"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer6" path="approvalLayer6"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate6" path="approvalDate6" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth6" path="breadth6"/></td>
								<td style="border:0"><form:input id ="depth6" path="depth6"/></td>
								<td style="border:0"><form:input id ="area6" path="area6"/></td>
								<td style="border:0"><form:input id ="quantity6" path="quantity6"/></td>
							</tr>
							
							<tr id="approvalRow7" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom7" path="approvalFrom7"/></td>
								<td style="border:0"><form:input id ="approvalTo7" path="approvalTo7"/></td>
								<td style="border:0">
										<form:select id = "approvalSide7" path="approvalSide7"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer7" path="approvalLayer7"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate7" path="approvalDate7" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth7" path="breadth7"/></td>
								<td style="border:0"><form:input id ="depth7" path="depth7"/></td>
								<td style="border:0"><form:input id ="area7" path="area7"/></td>
								<td style="border:0"><form:input id ="quantity7" path="quantity7"/></td>
							</tr>
							
							<tr id="approvalRow8" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom8" path="approvalFrom8"/></td>
								<td style="border:0"><form:input id ="approvalTo8" path="approvalTo8"/></td>
								<td style="border:0">
										<form:select id = "approvalSide8" path="approvalSide8"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer8" path="approvalLayer8"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate8" path="approvalDate8" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth8" path="breadth8"/></td>
								<td style="border:0"><form:input id ="depth8" path="depth8"/></td>
								<td style="border:0"><form:input id ="area8" path="area8"/></td>
								<td style="border:0"><form:input id ="quantity8" path="quantity8"/></td>
							</tr>
							
							<tr id="approvalRow9" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom9" path="approvalFrom9"/></td>
								<td style="border:0"><form:input id ="approvalTo9" path="approvalTo9"/></td>
								<td style="border:0">
										<form:select id = "approvalSide9" path="approvalSide9"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer9" path="approvalLayer9"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate9" path="approvalDate9" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth9" path="breadth9"/></td>
								<td style="border:0"><form:input id ="depth9" path="depth9"/></td>
								<td style="border:0"><form:input id ="area9" path="area9"/></td>
								<td style="border:0"><form:input id ="quantity9" path="quantity9"/></td>
							</tr>
							
							<tr id="approvalRow10" style="display:none">
								<td style="border:0"><form:input id ="approvalFrom10" path="approvalFrom10"/></td>
								<td style="border:0"><form:input id ="approvalTo10" path="approvalTo10"/></td>
								<td style="border:0">
										<form:select id = "approvalSide10" path="approvalSide10"> 
											<form:options items="${sideList}"/> 
										</form:select> 
								</td>
								<td style="border:0">
										<form:select id = "approvalLayer10" path="approvalLayer10"> 
											<form:options items="${layerList}"/>  
										</form:select> 
								</td>
								<td style="border:0"><form:input id ="approvalDate10" path="approvalDate10" readonly="true"/></td>
								<td style="border:0"><form:input id ="breadth10" path="breadth10"/></td>
								<td style="border:0"><form:input id ="depth10" path="depth10"/></td>
								<td style="border:0"><form:input id ="area10" path="area10"/></td>
								<td style="border:0"><form:input id ="quantity10" path="quantity10"/></td>
							</tr>
		
				</table>
			
		
		</td>
		
				<td id="billingBox" colspan="2" style="vertical-align:top">
				&nbsp;
				</td>
	</tr>
		
		<tr style="width:100%" bgcolor="#F4F4F4" >
			<td colspan="4" style="width:100%">
			<input id="submitButton" type="submit" value="Save" onclick="javascript: save(); return false;">
			<!-- <input id="clearButton" type="submit" value="Clear changes" onclick="javscript: clickClearChanges(); return false;"> -->
			<input id="saveButton" type="submit" value="Save & Print" onclick="javscript:saveAndPrint(); return false;">
				</td>
		</tr>
		</table>
	</form:form>
</td></tr>
</table>
	<script>
	disable("rfiDetailsTable");
	document.getElementById("billingBox").style.display='none';
	/*
	var status = document.getElementById("status");
	 if(status != null){
		 if(status.value == "Approved") {
			 document.getElementById("approvalBox").style.display='';
			 document.getElementById("billNumber").style.display='';
		 }
		 else {
			 document.getElementById("approvalBox").style.display='none';
			 document.getElementById("billNumber").style.display='none';
		 }
	 }
	 */
	 /*
	alert("Loading");
	 
	 mygrid = new dhtmlXGridObject('mygrid_container');
	 
	    mygrid.setImagePath("codebase/imgs/");
	 
	    mygrid.setHeader("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	 
//mygrid = new dhtmlXGridFromTable('tblToGrid');
alert("Loaded");
//mygrid.setInitWidthsP("2,2,4,13,3,3,2,2,4,1,6,5,5,3,3,3,3,2,3,3,3,3,*");
mygrid.setInitWidthsP("3,3,6,17,3,3,3,3,6,2,8,5,5,3,3,3,3,6,3,*");
//mygrid.adjustColumnSize(0);
//mygrid.adjustColumnSize(1);
//mygrid.adjustColumnSize(2);
//mygrid.adjustColumnSize(3);
//mygrid.adjustColumnSize(4);
//mygrid.adjustColumnSize(5);
//mygrid.adjustColumnSize(6);
//mygrid.adjustColumnSize(7);
//mygrid.adjustColumnSize(8);
//mygrid.adjustColumnSize(9);
//mygrid.adjustColumnSize(10);
//mygrid.adjustColumnSize(11);
//mygrid.adjustColumnSize(12);
//mygrid.adjustColumnSize(13);
//mygrid.adjustColumnSize(14);
//mygrid.adjustColumnSize(15);
//mygrid.adjustColumnSize(16);
//mygrid.adjustColumnSize(17);

//mygrid.enableAutoWidth(true);
//mygrid.setColWidth(12, "*");
//mygrid.setInitWidths("*,*,*,*,*,*,*,*,*,*,*,*,*");
mygrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
//mygrid.getCombo(2).put("<c:forEach items="${sideList}" var="side" varStatus="status">,${side}</c:forEach>");
//mygrid.getCombo(2).put(1,"LHS");
//mygrid.getCombo(2).put(2,"RHS");
//mygrid.getCombo(5).put(2, 3);
mygrid.setColSorting("str,int,date,str,int,int,str,int,str,str,date,str,str,int,int,str,str,date,int,str");
mygrid.attachHeader("#select_filter,#numeric_filter,#text_filter,#text_filter,#numeric_filter,#numeric_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#numeric_filter,#numeric_filter,#text_filter,#text_filter,#text_filter,#numeric_filter,#text_filter");
//mygrid.setNumberFormat("0000000",4,"+","");
//mygrid.setDateFormat("%d-%m-%y");
mygrid.setSkin("dhx_skyblue");
//@abhishek - below are the two lines for paging
mygrid.enablePaging(true, 100, 10, "pagingArea", true, "infoArea");
mygrid.setPagingSkin("bricks");
mygrid.init();
mygrid.enableSmartRendering(true,100);
//mygrid.changePage(1);
//mygrid.changePageRelative(0);
mygrid.load("http://localhost:8080<%=request.getContextPath()%>/rfiXml.htm");

//mygrid.groupBy(1);
//mygrid.unGroup();

mygrid.attachEvent("onRowDblClicked",    function (rId,cInd){
	dblclick(rId,cInd);});
//mygrid.enablePaging(true, 10, 3, "pagingArea", true, "recinfoArea");
//mygrid.init();
//alert("Converting");
//mygrid.toPDF('http://localhost:8080/RFIManager/generate', 'color');

//myDataProcessor.init(mygrid);
//myCalendar1.setDate("22-08-2011");
*/

</script>
    
     
</body>
</html> 
	