<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>

<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>

<script type="text/javascript" src="js/ddaccordion.js">

/***********************************************
* Accordion Content script- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
***********************************************/

</script>


<script type="text/javascript">


ddaccordion.init({ //top level headers initialization
	headerclass: "expandable", //Shared CSS class name of headers group that are expandable
	contentclass: "categoryitems", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [0], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", "openheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

ddaccordion.init({ //2nd level headers initialization
	headerclass: "subexpandable", //Shared CSS class name of sub headers group that are expandable
	contentclass: "subcategoryitems", //Shared CSS class name of sub contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click" or "mouseover
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["opensubheader", "closedsubheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})


</script>




<style type="text/css">

.arrowlistmenu{
width: 95%; /*width of accordion menu*/
}

.arrowlistmenu .menuheader{ /*CSS class for menu headers in general (expanding or not!)*/
font: 18px Arial;
font-family:Comic Sans MS;
/*color: #FF9C05;*/
color: white;
//background: lightgray url(titlebar.png) repeat-x center left;
background: url(<%=request.getContextPath()%>/css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png) no-repeat center left; /*custom bullet list image*/
margin-bottom: 10px; /*bottom spacing between header and rest of content*/
text-transform: uppercase;
padding: 15px 0 15px 10px; /*header text is indented 10px*/
cursor: hand;
cursor: pointer;
}

.arrowlistmenu .openheader{ /*CSS class to apply to expandable header when it's expanded*/
background-image: lightgray url(titlebar-active.png);
}

.arrowlistmenu ul{ /*CSS for UL of each sub menu*/
list-style-type: none;
margin: 0;
padding: 0;
margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
color:black;
}

.arrowlistmenu ul li{
padding-bottom: 2px; /*bottom spacing between menu items*/
}

.arrowlistmenu ul li .opensubheader{ /*Open state CSS for sub menu header*/
background: lightblue !important;
}

.arrowlistmenu ul li .closedsubheader{ /*Closed state CSS for sub menu header*/
background: lightgreen !important;
}

.arrowlistmenu ul li a{
/*color: #A70303;*/
background-color: lightgray;
//background: url(<%=request.getContextPath()%>/css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png) no-repeat center left; /*custom bullet list image*/
display: block;
padding: 0px 0;
padding-left: 19px; /*link text is indented 19px*/
text-decoration: none;
//font-weight: bold;
font-family:Comic Sans MS;
border-bottom: 1px solid #dadada;
font-size: 15;
//height: 30

}

.arrowlistmenu ul li a:visited{
color: #A70303;
}

.arrowlistmenu ul li a:hover{ /*hover state CSS*/
background-color: #F3F3F3;
}

.arrowlistmenu ul li a.subexpandable:hover{ /*hover state CSS for sub menu header*/
background: lightblue;
}

</style>

</head>
	<div class="arrowlistmenu">
	
	
	<h3 class="menuheader expandable" >Status Bar Charts</h3>
	<ul class="categoryitems" style="width:100%">
	<li style="width:100%"><a style="color:black" href="savedStatusBarChart.htm">Saved Bar Charts</a></li>
	<li style="width:100%"><a style="color:black" href="statusBarChart.htm">Generate New Bar Chart</a></li>
	</ul>
	<!-- 
	<h3 class="menuheader expandable" >Reports</h3>
	<ul class="categoryitems" style="width:100%">
	<li style="width:100%"><a style="color:black" href="gaps.htm">Gaps</a></li>
	<li style="width:100%"><a style="color:black" href="statusBarChart.htm">Partly Approved Chainages</a></li>
	</ul>
	 -->
	
	
	<h3 class="menuheader expandable">RFI Workflow</h3>
	<ul class="categoryitems" style="width:100%">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li style="width:100%"><a style="color:black" href="fileUpload.htm" >Import</a></li>
		</sec:authorize>
		<li style="width:100%"><a style="color:black" href="addRFI.htm">Add</a></li>
		<li style="width:100%"><a style="color:black" href="editRFI.htm">Edit</a></li>
	</ul>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h3 class="menuheader expandable">OTHER COMPONENTS</h3>
		<ul class="categoryitems" style="width:100%">
			<li style="width:100%"><a style="color:black" href="boq.htm">BOQ</a></li>
			<li style="width:100%"><a style="color:black" href="activity.htm">Activity</a></li>
			<li style="width:100%"><a style="color:black" href="workItem.htm">Work Item</a></li>
			<li style="width:100%"><a style="color:black" href="layer.htm" >Layer</a></li>
			<li style="width:100%"><a style="color:black" href="layerExceptions.htm" >Layer Exceptions</a></li>
			<li style="width:100%"><a style="color:black" href="side.htm">Side</a></li>
			<li style="width:100%"><a style="color:black" href="status.htm">Status</a></li>
			<li style="width:100%"><a style="color:black" href="grade.htm">Grade</a></li>
			<li style="width:100%"><a style="color:black" href="rfiCode.htm">RFI Code</a></li>
			<li style="width:100%"><a style="color:black" href="quantity.htm">Area</a></li>
		</ul>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h3 class="menuheader expandable">CLIENT BILLS</h3>
		<ul class="categoryitems" style="width:100%">
			<li style="width:100%"><a style="color:black" href="prepareBill.htm">Prepare New Bill</a></li>
			<!-- <li style="width:100%"><a style="color:black" href="checkPreparedBill.htm">Check Prepared Bill</a></li> -->
			<li style="width:100%"><a style="color:black" href="billInbox.htm">Bill Inbox</a></li>
		</ul>
	</sec:authorize>
	
	
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">	
		<h3 class="menuheader expandable" >Admin Area</h3>
		<ul class="categoryitems" style="width:100%">
		<li style="width:100%"><a style="color:black" href="userRegisterIntermediate.htm">Register User</a></li>
		</ul>
	</sec:authorize>
		
	</div>
