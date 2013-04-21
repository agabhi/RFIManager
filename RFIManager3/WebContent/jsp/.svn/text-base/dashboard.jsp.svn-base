<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RFI Manager</title>

<style>
table.curvedEdges { border:1px solid lightgray;-webkit-border-radius:8px;-moz-border-radius:8px;border-radius:8px; }
table.curvedEdges td table.curvedEdges th { border-bottom:1px dotted black;padding:5px; }

table.curvedEdges1 { border:1px solid lightgray;-webkit-border-top-left-radius:8px;-moz-border-top-left-radius:8px;border-top-left-radius:8px;-webkit-border-top-right-radius:8px;-moz-border-top-right-radius:8px;border-top-right-radius:8px; }
table.curvedEdges1 td table.curvedEdges1 th { border-bottom:1px dotted black;padding:5px; }

</style>

<link rel="stylesheet" type="text/css" href="/RFIManager3/jqplot/jquery.jqplot.css" />
	<script src="/RFIManager3/js/jquery-1.5.2.min.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript" src="/RFIManager3/jqplot/jquery.jqplot.min.js"></script>
<script class="include" language="javascript" type="text/javascript" src="/RFIManager3/jqplot/plugins/jqplot.pieRenderer.js"></script>
  
  
<script class="code" type="text/javascript">
$(document).ready(function(){
	
	$.post("/RFIManager3/pieChartStatus.htm",
			  {pieChart: 'allStatus'},
			  function(array){
				  var i;
				  var data1 = new Array();
				  var j = 0;
				  var fldHeading = [];
				  fldHeading = array.split(',');
				  for(i = 0; i < fldHeading.length; i=i+2 )
				  {
					  var array1 = new Array();
					  array1[0] = fldHeading[i].trim();
					  array1[1] = parseInt(fldHeading[i+1]);
					  data1[j] = array1;
					  ++j;
				  }
				  
				             var plot1 = jQuery.jqplot ('chart1', [data1], 
				              { 
				            	title: {
				                    text: 'All RFI\'s status',
				                    show: true,
				                },
				                seriesDefaults: {
				                  // Make this a pie chart.
				                  renderer: jQuery.jqplot.PieRenderer, 
				                  rendererOptions: {
				                    // Put data labels on the pie slices.
				                    // By default, labels show the percentage of the slice.
				                    showDataLabels: true,
				                    dataLabels: 'percent',
				                    sliceMargin: 2
				                  }
				                }, 
				                grid:{background:'white',borderWidth: 0.0,borderColor: 'white',shadowWidth:1,shadowDepth:1},
				                legend: { show:true, location: 'e' }
				              }
				            );
				          });	
	
	$.post("/RFIManager3/pieChartStatus.htm",
			  {pieChart: 'unbilledStatus'},
			  function(array){
				  var i;
				  var data1 = new Array();
				  var j = 0;
				  var fldHeading = [];
				  fldHeading = array.split(',');
				  for(i = 0; i < fldHeading.length; i=i+2 )
				  {
					  var array1 = new Array();
					  array1[0] = fldHeading[i].trim();
					  array1[1] = parseInt(fldHeading[i+1]);
					  data1[j] = array1;
					  ++j;
				  }
				  
				             var plot2 = jQuery.jqplot ('chart2', [data1], 
				              { 
				            	title: {
				                    text: 'Unbilled RFI\'s status',
				                    show: true,
				                },
				                seriesDefaults: {
				                  // Make this a pie chart.
				                  renderer: jQuery.jqplot.PieRenderer, 
				                  rendererOptions: {
				                    // Put data labels on the pie slices.
				                    // By default, labels show the percentage of the slice.
				                    showDataLabels: true,
				                    dataLabels: 'percent',
				                    sliceMargin: 2
				                  }
				                }, 
				                grid:{background:'white',borderWidth: 0.0,borderColor: 'white',shadowWidth:1,shadowDepth:1},
				                legend: { show:true, location: 'e' }
				              }
				            );
				            //plot2.dataLabels = 'value';
				          });	
	
	
	
		});
		
		
		
  
</script>  
  
</head>
<body>
<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="4">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Project Dashboard</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr>
</table>
<table>
	<tr>
		<td><div id="chart1" style="height:300px; width:500px;"></div></td>
		<td><div id="chart2" style="height:300px; width:500px;"></div></td>
	</tr>
</table>
	
	
</body>
</html>