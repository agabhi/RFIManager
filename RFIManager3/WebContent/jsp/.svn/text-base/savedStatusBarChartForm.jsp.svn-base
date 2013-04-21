<%@page import="java.text.SimpleDateFormat, com.model.rfi.RFI, com.model.rfi.ExpandedRFI" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<script src="/RFIManager3/js/jquery-1.5.2.min.js" type="text/javascript"></script>

<script type="text/javascript">

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


function printBarChart()
{
	var printWin = window.open("","",'width=0,height=0'); 
	printWin.document.open();
	printWin.document.write(document.getElementById('barchart1').innerHTML);
	printWin.print();
	//printWin.close();
}

function validations ()
{
	
	if (!isInteger(document.getElementById("fromChainage").value))
	{
		alert("From chainage should be a non-negative integer!");
		return false;
	}
	
	if (!isInteger(document.getElementById("toChainage").value))
	{
		alert("To chainage should be a non-negative integer!");
		return false;
	}
	
	if (+document.getElementById("fromChainage").value > +document.getElementById("toChainage").value)
	{
		alert("From Chainage should be a less than To Chainage!");
		return false;
	}
	
	if (+document.getElementById("toChainage").value - +document.getElementById("fromChainage").value > 4000)
	{
		alert("You are generating barchart for length more than 4000 metres. Please reduce the length!");
		return false;
	}
	
	if(isEmpty(document.getElementById("barChartName").value))
	{
		alert("Please select a Bar chart name!");
		return false;
	}
	
	return true;
}

function nextBarChart()
{
	if(!validations())
	{
		return false;
	}
	
	var length = +document.getElementById("toChainage").value - +document.getElementById("fromChainage").value;
	document.getElementById("fromChainage").value = document.getElementById("toChainage").value;
	document.getElementById("toChainage").value = +document.getElementById("toChainage").value + length;
	
	generateBarChart();
	
}

function previousBarChart()
{
	if(!validations())
	{
		return false;
	}
	
	var length = +document.getElementById("toChainage").value - +document.getElementById("fromChainage").value;
	document.getElementById("toChainage").value = +document.getElementById("fromChainage").value;
	document.getElementById("fromChainage").value = +document.getElementById("fromChainage").value - length;
	
	if(+document.getElementById("fromChainage").value < 0)
	{
		document.getElementById("fromChainage").value = 0;	
	}
	
	generateBarChart();
	
}

function generateBarChart()
{
	if (!isInteger(document.getElementById("fromChainage").value))
	{
		alert("From chainage should be a non-negative integer!");
		return false;
	}
	
	if (!isInteger(document.getElementById("toChainage").value))
	{
		alert("To chainage should be a non-negative integer!");
		return false;
	}
	
	if (+document.getElementById("fromChainage").value > +document.getElementById("toChainage").value)
	{
		alert("From Chainage should be a less than To Chainage!");
		return false;
	}
	
	if (+document.getElementById("toChainage").value - +document.getElementById("fromChainage").value > 4000)
	{
		alert("You are generating barchart for length more than 4000 metres. Please reduce the length!");
		return false;
	}
	
	if(isEmpty(document.getElementById("barChartName").value))
	{
		alert("Please select a Bar chart name!");
		return false;
	}
	
	var toChainage = document.getElementById("toChainage").value;
	var toChainageRemainder = toChainage%10;
	
	if (+toChainageRemainder > 0)
	{
		toChainage = toChaiange + (10-toChainageRemainder);
	}
	var length = toChainage - document.getElementById("fromChainage").value;
	var remainder = length%1000;
	var loopCount = parseInt(length/1000);
	if (remainder > 0)
	{
		++loopCount;
	}
	
	var queryString = $('#drawStatusBarChartform').serialize();
	//alert(queryString);
	
	document.getElementById('barchart1').innerHTML = "";
	var i;
	for(i=0;i<loopCount;++i)
	{
		var from =  +document.getElementById("fromChainage").value + (i*1000);
		var to = from + 1000;
		
		if(+to > +toChainage)
		{
			to = toChainage;
		}
		var url1 ="/RFIManager3/drawStatusbarChart.htm?"+queryString;
		$.ajaxSetup({async:false});	
		$.post(url1,
				  {savedBarChartName: document.getElementById("barChartName").value, from: from, to: to},
				  function(barChartHtml){
					  //alert("Inside");
					  //alert(barChartHtml);
					  document.getElementById('barchart1').innerHTML += barChartHtml;
					  
				  });
	}
	
	document.getElementById("printDiv").style.display = "";
	
	
		
}
				  

</script>
  
    
</head>
<body>
<form:form id="drawStatusBarChartform" method="POST" commandName="drawStatusBarChart" cssStyle="margin: 0px; padding: 0px;">
<table class="curvedEdges" style="width:100%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
				<tr>
					<td style="width:100%;border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;" colspan="6">
						<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
							<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
			        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Status Barchart</label>
			        		</td></tr>
			        	</table>
			        	
			        </td>
			     </tr>
			     
			     <tr>
			     	<td style="border:0">
		        		<label><font size="4">Select Bar Chart</font></label>
		        	</td>
		        	<td style="border:0">
						<form:select id="barChartName" path="barChartName"> 
							<form:options items="${savedStatusBarChartList}" /> 
						</form:select> 
					</td>
		       		<td style="border:0"><label><font size="4"><font color="#000066"></font>From Chainage</font></font></label></td><td style="border:0"><form:input id="fromChainage" path="fromChainage" /></td>
			   		<td style="border:0"><label><font size="4">To Chainage</font></label></td><td style="border:0"><form:input id="toChainage" path="toChainage" /></td>
				</tr>
			      
				<tr>
					<td>
						<input id="generateButton" type="submit" value="Generate Barchart" onclick="javascript: generateBarChart(); return false;">
						<input id="generateButton" type="submit" value="<< Previous" onclick="javascript: previousBarChart(); return false;">
						<input id="generateButton" type="submit" value="Next >>" onclick="javascript: nextBarChart(); return false;">
					</td>
				</tr>
				
				<tr>
					<td>
						<div id="printDiv" style="display:none">
							<input id="printButton" type="button" value="Print Barchart" onclick="javascript: printBarChart(); return false;">
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						
					</td>
				</tr>
			     
			     
</table>
</form:form>
<table>
	<tr>
		<td><div id="barchart1" style="width:100%"></div></td>
	</tr>
</table>
	
	
</body>
</html>