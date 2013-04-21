<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.RFIBill, com.mysql.jdbc.StringUtils" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>

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
			     	<td style="border:0"><label><font size="4"><font color="#000066"></font>Bill Number</font></font></label></td><td style="border:0"><form:input id="billNumber" path="billNumber" disabled="true"/></td>
					<td style="border:0"><label><font size="4"><font color="#000066"></font>From Date</font></font></label></td><td style="border:0"><form:input id="billFromDate" path="billFromDate" /></td>
					<td style="border:0"><label><font size="4"><font color="#000066"></font>To Date</font></font></label></td><td style="border:0"><form:input id="billToDate" path="billToDate" /></td>
					<td style="border:0"><label><font size="4"><font color="#000066"></font>Bill Date</font></font></label></td><td style="border:0"><form:input id="billDate" path="billDate" /></td>
				</tr>
				<tr>
					<td>
						<input id="finalizeBillButton" type="button" value="Edit Bill" onclick="javascript: editBill(); return false;">
					</td>
				</tr>
</table>
</form:form>

<hr color="blue;">

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

$(function(){ 
  var mygrid = $("#approvedList").jqGrid({
    url:'prepareRfiXml.htm?prepared=approvedOnly',
    datatype: 'xml',
    mtype: 'POST',
    colNames:['RFI Code','RFI No.', 'Issue date','BOQ No.', 'Activity', 'Item','WI Remarks', 'From','To','Side', 'Layer','Status','Gr.','Insp Date','Created By', 'Updated by','Appr From','Appr To','Appr Side', 'Appr Layer','Appr Date','Bill No.','Quantity', 'Remarks'],
    colModel :[ 
	  {name:'rfiCode', index:'rfi_code', width:'40', searchoptions:{sopt:['eq','ne']}}, 
      {name:'rfiNumber', index:'rfi_number', width:'60', align:'right', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'issueDate', index:'issue_date', width:'100', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      //{name:'issueDate', index:'issue_date', width:'5', align:'right', search:true, searchoptions:{dataInit:function(el){$(el).datepicker({dateFormat:'yy-mm-dd',onClose: function(dateText, inst) {$("#approvedList")[0].triggerToolbar();}});} }}, 
      {name:'boqNumber', index:'boq_number', width:'60', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'activity', index:'activity', width:'100', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'item', index:'item_description', width:'400', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'wiRemarks', index:'wi_remarks', width:'200', align:'left', searchoptions:{sopt:['eq','ne']}},
      {name:'fromChainage', index:'from_chainage', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
      {name:'toChainage', index:'to_chainage', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      
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
      {name:'quantity', index:'quantity', width:'60', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      {name:'billNumber', index:'bill_number', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
      
      {name:'remarks', index:'remarks', width:'500', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}
    ],
    pager: '#approvedPager',
    rowNum:20,
    rowList:[20,100],
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
    caption: 'Unbilled RFI List'
  });

  $(window).bind('resize', function() {
	  var width = jQuery("#container").attr('clientWidth');
	    $("#approvedList").setGridWidth(width);
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
        Math.abs(width - jQuery("#approvedList").width()) > 5)
    {
        jQuery("#approvedList").setGridWidth(width);
    }

}).trigger('resize');
*/
  jQuery("#approvedList").jqGrid('navGrid','#approvedPager',
		  {
		  	edit:false,add:false,del:false,search:true
		  },
		  {}, // edit options
		  {}, // add options
		  {}, //del options
		  {multipleSearch:true, multipleGroup:true, showQuery: true, closeAfterSearch:true, closeAfterReset:true, beforeShowSearch:function(){mygrid[0].clearToolbar();}} // search options
		  );

  //jQuery("#approvedList").jqGrid('navGrid','#approvedPager',{del:false,add:false,edit:false,search:true});
  /*
  jQuery("#approvedList").jqGrid('navButtonAdd',"#approvedPager",{caption:"Toggle",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s',
	onClickButton:function(){
		mygrid[0].toggleToolbar()
	} 
});
jQuery("#approvedList").jqGrid('navButtonAdd',"#approvedPager",{caption:"Clear",title:"Clear Search",buttonicon :'ui-icon-refresh',
	onClickButton:function(){
		alert("Clearing2");
		mygrid[0].clearToolbar();
		alert("Cleared");
	} 
});
*/

jQuery("#approvedList").jqGrid('navButtonAdd','#approvedPager',{caption:"Add to Bill",
	onClickButton:function(){
		
		var s;
		s = jQuery("#approvedList").jqGrid('getGridParam','selarrrow');
		if(s)
		{
			if (s.length > 0)
			{
				var c = 0;
				var url = 'changeRfiBillNumber.htm?previousBill=add&billNumber=<%=request.getParameter("billNumber")%>&';
				for(c = 0; c < s.length; ++c)
				{
					url = url + "rfiNumber=" + jQuery("#approvedList").getCell(s[c],'rfiNumber') +"&";
					
					//myWindow.document.write("Print RFI Number "+jQuery("#approvedList").getCell(s[c],'rfiNumber'));
					//myWindow.document.write("Print RFI Number "+jQuery("#approvedList").getCell(s[c],'rfiNumber'));
					    
				}
				
				$.post(url,
						  {  },
						  function(xml){
							  //alert("reloading 1");
							  $("#approvedList").trigger("reloadGrid");
							  //alert("reloading 11");
							  $("#billedList").trigger("reloadGrid");
							  //alert("reloading 111");
						  });
				
			}
			else
			{
				alert("No RFI selected!");
					
			}
		}
		else
		{
			alert("No RFI selected!");
				
		}
	} 

});



 jQuery("#approvedList").jqGrid('filterToolbar',{stringResult: false,searchOnEnter : false, beforeSearch:function(){var grid = $("#approvedList");
 grid.jqGrid('setGridParam',{search:false});

 var postData = grid.jqGrid('getGridParam','postData');
 $.extend(postData,{filters:""});
 // for singe search you should replace the line with
 $.extend(postData,{searchField:"",searchString:"",searchOper:""});

// grid.trigger("reloadGrid",[{page:1}]);
 }});
//jQuery("#approvedList").jqGrid('filterToolbar');
// $(".collapsibleContainerContent").slideToggle(); 
}); 





$(function(){ 
	  var mygrid = $("#billedList").jqGrid({
	    url:'prepareRfiXml1.htm?billed=billed&billNumber=<%=request.getParameter("billNumber")%>',
	    datatype: 'xml',
	    mtype: 'POST',
	    colNames:['RFI Code','RFI No.', 'Issue date','BOQ No.', 'Activity', 'Item','From','To','Side', 'Layer','Status','Gr.','Insp Date','Created By', 'Updated by','Appr From','Appr To','Appr Side', 'Appr Layer','Appr Date','Bill No.','Quantity', 'Remarks'],
	    colModel :[ 
		  {name:'rfiCode', index:'rfi_code', width:'40', searchoptions:{sopt:['eq','ne']}}, 
	      {name:'rfiNumber', index:'rfi_number', width:'60', align:'right', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
	      {name:'issueDate', index:'issue_date', width:'100', search:true, searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
	      //{name:'issueDate', index:'issue_date', width:'5', align:'right', search:true, searchoptions:{dataInit:function(el){$(el).datepicker({dateFormat:'yy-mm-dd',onClose: function(dateText, inst) {$("#billedList")[0].triggerToolbar();}});} }}, 
	      {name:'boqNumber', index:'boq_number', width:'60', align:'left', searchoptions:{sopt:['eq','ne']}},
	      {name:'activity', index:'activity', width:'100', align:'left', searchoptions:{sopt:['eq','ne']}},
	      {name:'item', index:'item_description', width:'400', align:'left', searchoptions:{sopt:['eq','ne']}}, 
	      {name:'fromChainage', index:'from_chainage', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}, 
	      {name:'toChainage', index:'to_chainage', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
	      
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
	      {name:'quantity', index:'quantity', width:'60', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
	      {name:'billNumber', index:'bill_number', width:'80', align:'right', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
	      
	      {name:'remarks', index:'remarks', width:'500', searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}}
	    ],
	    pager: '#billedPager',
	    rowNum:20,
	    rowList:[20,100],
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
	    caption: 'Billed RFI List'
	  });

	  $(window).bind('resize', function() {
		  var width = jQuery("#container").attr('clientWidth');
		    $("#billedList").setGridWidth(width);
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
	        Math.abs(width - jQuery("#billedList").width()) > 5)
	    {
	        jQuery("#billedList").setGridWidth(width);
	    }

	}).trigger('resize');
	*/
	  jQuery("#billedList").jqGrid('navGrid','#billedPager',
			  {
			  	edit:false,add:false,del:false,search:true
			  },
			  {}, // edit options
			  {}, // add options
			  {}, //del options
			  {multipleSearch:true, multipleGroup:true, showQuery: true, closeAfterSearch:true, closeAfterReset:true, beforeShowSearch:function(){mygrid[0].clearToolbar();}} // search options
			  );

	jQuery("#billedList").jqGrid('navButtonAdd','#billedPager',{caption:"Remove from Bill",
		onClickButton:function(){
			
			var s;
			s = jQuery("#billedList").jqGrid('getGridParam','selarrrow');
			if(s)
			{
				if (s.length > 0)
				{
					var c = 0;
					var url = 'changeRfiBillNumber.htm?previousBill=remove&';
					for(c = 0; c < s.length; ++c)
					{
						url = url + "rfiNumber=" + jQuery("#billedList").getCell(s[c],'rfiNumber') +"&";
						
						//myWindow.document.write("Print RFI Number "+jQuery("#approvedList").getCell(s[c],'rfiNumber'));
						//myWindow.document.write("Print RFI Number "+jQuery("#approvedList").getCell(s[c],'rfiNumber'));
						    
					}
					
					$.post(url,
							  {  },
							  function(xml){
								  
								  //alert("reloading 2");
								  $("#approvedList").trigger("reloadGrid");
								  //alert("reloading 22");
								  $("#billedList").trigger("reloadGrid");
								  //alert("reloading 222");
							  });
					
				}
				else
				{
					alert("No RFI selected!");
						
				}
			}
			else
			{
				alert("No RFI selected!");
					
			}
		} 

	});




	 jQuery("#billedList").jqGrid('filterToolbar',{stringResult: false,searchOnEnter : false, beforeSearch:function(){var grid = $("#billedList");
	 grid.jqGrid('setGridParam',{search:false});

	 var postData = grid.jqGrid('getGridParam','postData');
	 $.extend(postData,{filters:""});
	 // for singe search you should replace the line with
	 $.extend(postData,{searchField:"",searchString:"",searchOper:""});

	// grid.trigger("reloadGrid",[{page:1}]);
	 }});
	//jQuery("#billedList").jqGrid('filterToolbar');
	// $(".collapsibleContainerContent").slideToggle(); 
	});










</script>



	
	
</head>

<body onload="doOnLoad();">
<table id="container" style="width:100%;height:100%" cellspacing="0" cellpadding="0">
	<tr style="height:40%;width:100%">
		<td valign="top" style="width:100%">
			<table id="approvedList" width="100%" height="80%"><tr style="width:100%"><td/></tr></table> 
			<div id="approvedPager" style="width:100%"></div> 
		</td>
	</tr>
	
	<tr style="height:60%;width:100%">
		<td valign="top" style="width:100%">
			<table id="billedList" width="100%" height="80%"><tr style="width:100%"><td/></tr></table> 
			<div id="billedPager" style="width:100%"></div> 
		</td>
	</tr>
</table>
    
     
</body>

