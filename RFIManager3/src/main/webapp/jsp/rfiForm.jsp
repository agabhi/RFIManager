<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
	<title>ADD RFI</title>
	<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxgrid.css">
	<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxgrid_skin.css">
	<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxgrid_dhx_skyblue.css">
	<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxgrid_pgn_bricks.css">
    <script src="codebase/dhtmlxcommon.js"></script>
    
	<link rel="stylesheet" type="text/css" href="codebase/dhtmlxcalendar.css"></link>
    <link rel="stylesheet" type="text/css" href="codebase/dhtmlxcalendar_dhx_skyblue.css"></link>
    
    <script src="codebase/dhtmlxcalendar.js"></script>	

<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />


 
 
<script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.mi.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>

<script src="js/JsonXml.js" type="text/javascript"></script>
<script src="js/grid.import.js" type="text/javascript"></script>



<script type="text/javascript">

function changeActivity()
{
	document.getElementById("itemDescription").value = "";
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
			if(document.getElementById("itemDescription").options.length > 1)
			{
				document.getElementById("itemDescription").options[1].selected = true;
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
					  var itemBox = document.getElementById("itemDescription");
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
	var itemDescription= document.getElementById("itemDescription").value;
	var activity= document.getElementById("activity").value;
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
	
	clickSubmit("print");
	return false;
}

function save(){  
	clickSubmit("true");
	return false;
}

$(function(){ 
  var mygrid = $("#list").jqGrid({
    url:'todayRfiXml.htm',
    datatype: 'xml',
    mtype: 'POST',
    colNames:['RFI Code','RFI No.', 'Issue date','BOQ No.', 'Activity', 'Item','WI Remarks', 'From','To','Side', 'Layer','Status','Gr.','Insp Date','Created By', 'Updated by','Appr From','Appr To','Appr Side', 'Appr Layer','Appr Date','Bill No.','Quantity', 'Remarks'],
    colModel :[ 
	  {name:'rfiCode', index:'rfi_code', width:'40', searchoptions:{sopt:['eq','ne']}}, 
      {name:'rfiNumber', index:'rfi_number', width:'60', align:'right', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'issueDate', index:'issue_date', width:'100'}, 
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
      
      {name:'approvedFrom', index:'approved_from', width:'90', hidden:true, align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvedTo', index:'approved_to', width:'90', hidden:true, align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvedSide', index:'approved_side', width:'90', hidden:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvedLayer', index:'approved_layer', width:'100', hidden:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'approvalDate', index:'approval_Date', width:'100', hidden:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'billNumber', index:'bill_number', width:'80', align:'right', hidden:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'quantity', index:'quantity', width:'60', hidden:true, align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
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
    caption: 'Today\'s RFI'
  });
/*
  $(window).bind('resize', function() {
	  var width = jQuery("#container").attr('clientWidth');
	    $("#list").setGridWidth(width);
	}).trigger('resize');
*/
jQuery("#list").jqGrid('navGrid','#pager',
		  {
		  	edit:false,add:false,del:false,search:false
		  },
		  {}, // edit options
		  {}, // add options
		  {}//del options
		  );
		  
jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Export",
	onClickButton:function(){
		//alert(jQuery("#list").jqGridExport());
		 
		 jQuery("#list").excelExport({url:'todayRfiXml.htm'});
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
}); 

</script>



	
   <script>
   
   function clear(table_id)
   {
       var inputs=document.getElementById(table_id).getElementsByTagName('input');
       for(var i=0; i<inputs.length; ++i)
       {
           if(inputs[i].type != "button")
           	inputs[i].value="";
       	}
       inputs=document.getElementById(table_id).getElementsByTagName('select');
       for(var i=0; i<inputs.length; ++i)
       	{
           inputs[i].value="";
       	}
       inputs=document.getElementById(table_id).getElementsByTagName('textarea');
       for(var i=0; i<inputs.length; ++i)
       {
           inputs[i].value="";
       	}
       
       	myissueDate.setDate(new Date());
		document.getElementById("issueDate").value= myissueDate.getFormatedDate();

		var d = new Date((new Date()).getTime()+86400000);
		d.setHours(9);
		d.setMinutes(0,0,0);
		myinspectionDate.setDate(d);
		//myinspectionDate.setDate(new Date((new Date()).getTime()+86400000));
		document.getElementById("inspectionDate").value= myinspectionDate.getFormatedDate();
   }
   
   function compareDates(date1, date2) {
	   if(date1 != null && date2 != null) {
		   if(date1 > date2) {
			   return 1;
		   }
		   else {
			   return 2;
		   }
	   }
	   return 0;
   }
 
 function compareOnlyDates(date1, date2) {
	   if(date1 != null && date2 != null) {
		   //alert(date1);
		   //alert(date2);
		   date1.setHours(0);
		   date1.setMinutes(0);
		   date2.setHours(0);
		   date2.setMinutes(0);
		   if(date1 > date2) {
			   return 1;
		   }
		   else {
			   return 2;
		   }
	   }
	   return 0;
   }
   
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
		   if (isNumeric(chainage) == false) 
		    {
			   //alert("chainage number1");
		     	alert(msg);
		     	//alert("chainage number2");
		     	return false;
		    }
		   //alert("chainage number3");
		   return true;
	}
   
	function clickSubmit(successMsg) {
	//document.getElementById("issueDate").value = document.getElementById("issueDate").value + " 00:00";
	
		if (!isInteger(document.getElementById("fromChainage").value) ) {
			alert("From Chainage should be a non-negative integer!");
			return false;
		}
		if (!isInteger(document.getElementById("toChainage").value) ) {
			alert("To Chainage should be a non-negative integer!");
			return false;
		}
		if (!chainageCheck(document.getElementById("fromChainage"),document.getElementById("toChainage"),"From Chainage should be a less than To Chainage!")) {
			//alert("check5");
			return false;
		}
		
		if(isEmpty(document.getElementById("itemDescription").value))
		{
			alert("Please select an Item!");
		}
		
		if(isEmpty(document.getElementById("side").value))
		{
			alert("Please select a Side!");
			return false;
		}
		
		if(isEmpty(document.getElementById("activity").value))
		{
			alert("Please select an Activity!");
			return false;
		}
		
		if(isEmpty(document.getElementById("layer").value))
		{
			alert("Please select a Layer!");
			return false;
		}
		
		if(isEmpty(document.getElementById("boqNumber").value))
		{
			alert("Please select a BOQ Number!");
		}
		
		if(compareDates(myissueDate.getDate(), myinspectionDate.getDate()) == 1) {
			
			alert("Inspection date should be later than Issue date");
			return false;
		}
		var queryString = $('#addForm').serialize();
		//alert(queryString);
		
		var url1 ="addRFI.htm?"+queryString;
		/*
		$.get(url1, function(data) {
			alert("RFI Edited successfully!");
			});
			*/
		jQuery.ajaxSetup({async:false});
		if (successMsg == "print")
		{
			$.post(url1,
					  {  },
					  function(xml){$(xml).find("rfi").each(function() 
								{
						  clear("rfiDetailsTable");document.getElementById("rfiHeader").innerText="Add RFI Number: "+$(this).find("nextRfiNumber").text();$("#list").trigger("reloadGrid");
						  var url = 'rfiPrint.htm?';
							url = url + "rfiNumber=" + $(this).find("generatedRfiNumber").text() +"&";
							var myWindow = window.open(url,'','width=0,height=0');
							myWindow.print();
								});
					  });
		}
		else
		{
			$.post(url1,
					  {  },
					  function(xml){$(xml).find("rfi").each(function() 
								{
								  	if($(this).find("status").text() == "error")
								  	{
								  		var r=confirm("Do you want to proceed?\n"+$(this).find("message").text());
								  		if (r==true)
								  	  	{
								  			$.post(url1,
													  {force : "true"  },
													  function(xml){$(xml).find("rfi").each(function() 
																{
														  			clear("rfiDetailsTable");document.getElementById("rfiHeader").innerText="Add RFI Number: "+$(this).find("nextRfiNumber").text();$("#list").trigger("reloadGrid");alert("RFI Added successfully");
																});
													  });
											
								  	  	}
								  		else
								  		{
								  		}
								  	}
								  	else
								  	{
								  		clear("rfiDetailsTable");document.getElementById("rfiHeader").innerText="Add RFI Number: "+$(this).find("nextRfiNumber").text();$("#list").trigger("reloadGrid");alert("RFI Added successfully");
								  	}
								});
					  });
		}
		jQuery.ajaxSetup({async:true});
		//alert("RFI Edited successfully!");
		/*
		$('#editForm').ajaxForm(function() { 
            alert("RFI Edited successfully!"); 
        }); 
			*/
		
//document.getElementById("issueDate").value = document.getElementById("issueDate").value + " 00:00";	
return true;

		
		
}
   
   var myissueDate;
   var myinspectionDate;
	function doOnLoad() {
		changeActivity();
		//document.getElementById("issueDate").value = co
		myissueDate = new dhtmlXCalendarObject(["issueDate"]);
		//myissueDate.show();
		myissueDate.setDateFormat("%d-%m-%Y");
		myissueDate.hideTime();
		myissueDate.setDate(new Date());
		document.getElementById("issueDate").value= myissueDate.getFormatedDate();
		//myissueDate.se
		//myissueDate.hide();
		//alert((new Date()).getTime());
		
		myinspectionDate = new dhtmlXCalendarObject(["inspectionDate"]);
		//myinspectionDate.show();
		myinspectionDate.setDateFormat("%d-%m-%Y %H:%i");
		var d = new Date((new Date()).getTime()+86400000);
		d.setHours(9);
		d.setMinutes(0,0,0);
		myinspectionDate.setDate(d);
		//myinspectionDate.setDate(new Date((new Date()).getTime()+86400000));
		document.getElementById("inspectionDate").value= myinspectionDate.getFormatedDate();
		
		var myEvent = myissueDate.attachEvent("onClick", function (){
			var dat = new Date(myissueDate.getDate().getTime()+86400000);
			dat.setHours(myinspectionDate.getDate().getHours());
			dat.setMinutes(myinspectionDate.getDate().getMinutes());
			myinspectionDate.setDate(dat);
			document.getElementById("inspectionDate").value= myinspectionDate.getFormatedDate();
		});	
	}
	
	
   //myCalendar.show();
</script>	
	


	
</head>

<body onload="doOnLoad();">
<table style="width:100%;height:100%;" cellspacing="0" cellpadding="0">
<tr style="width:100%;height:25%;border-BOTTOM: 1px solid #000000;" ><td style="width:100%;" valign="top">
	<form:form id="addForm" method="POST" commandName="addRfi" cssStyle="margin: 0px; padding: 0px;">
	
		 <form:errors path="*" />
		 
		 <table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Add RFI Number: ${nextRfiNumber}</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
		     </tr> 
		
		<tr>
		       <td style="border:0">
		        <label><font size="3">RFI Type</font></label>
		        </td>
		        <td style="border:0">
				<form:select path="rfiCode"> 
					<form:options items="${rfiCodeList}" /> 
				</form:select> 
				</td>
				
				<td style="border:0">
		        <label><font size="3">Activity</font></label>
		        </td>
		        <td style="border:0">
				<form:select id="activity" path="activity" onchange="changeActivity()"> 
					<form:options items="${activityList}"/>
				</form:select> 
				</td>
		</tr> 
		
		<tr>
		       <td style="border:0">
		        <label><font size="3">BOQ No.</font></label>
		        </td>
		        <td style="border:0">
				<form:select id="boqNumber" path="boqNumber" onchange="changeBoqNumber()">
					<option value="">-Select BOQ-</option>
					<form:options items="${boqNumberList}" /> 
				</form:select> 
				</td>
				
				
		</tr>
		
		<tr>
		       <td style="border:0"><label><font size="3">Item</font></label>
		       </td>
		        <td style="border:0" colspan="3">
				<form:select id="itemDescription" path="itemDescription" onchange="changeWorkItem()">
					<option value="">-Select Item-</option>
					<form:options items="${itemsList}"/> 
				</form:select> 
				
			    <font size="3">- WI Remarks</font><form:input id="wiRemarks" path="wiRemarks" />
			    </td>
		</tr>
		
		<tr>
		       <td style="border:0"><label><font size="3"><font color="#000066"></font>From Chainage</font></font></label></td><td style="border:0"><form:input id="fromChainage" path="fromChainage" /></td>
			   <td style="border:0"><label><font size="3">To Chainage</font></label></td><td style="border:0"><form:input id="toChainage" path="toChainage" /></td>
		</tr>
		<tr>
		        <td style="border:0">
		        <label><font size="3">Side</font></label>
		        </td>
		        <td style="border:0">
				<form:select id="side" path="side"> 
				</form:select> 
				</td>
			   
			   <td style="border:0">
			   <label><font size="3">Layer</font></label>
			   </td>
		        <td style="border:0">
				<form:select id="layer" path="layer"> 
				</form:select> 
				</td>
		</tr>
		<tr>
		       <td style="border:0"><label><font size="3">Issue Date</font></label></td><td style="border:0"><form:input id ="issueDate" path="issueDate" readonly="true"/></td>
			   <td style="border:0"><label><font size="3">Inspection Date</font></label></td><td style="border:0"><form:input id ="inspectionDate" path="inspectionDate" readonly="true"/></td>
		</tr>
		<tr>
		       
			   <td style="border:0"><label><font size="3">Remarks</font></label></td><td style="border:0"><form:textarea id="remarks" path="remarks" /></td>
		</tr>

		<tr style="width:100%;" >
			<td colspan="4" style="width:100%;padding-top: 15px;" >
				<table style="width:100%;" cellspacing="0" cellpadding="0"  bgcolor="#F4F4F4">
					<tr><td>
						<input id="submitButton" type="button" value="Save" onclick="save();">
						<input type="button" value="Save & Print" onclick="saveAndPrint();">
					</td></tr>
				</table>
			</td>
		</tr>
		
	</table>
	</form:form>
	
</td></tr>

<tr>
<td>

<table id="list" width="100%" ><tr style="width:100%"><td/></tr></table> 
			<div id="pager" style="width:100%"></div>
    
    
</td></tr>

</table>

	 
</body>
 
	