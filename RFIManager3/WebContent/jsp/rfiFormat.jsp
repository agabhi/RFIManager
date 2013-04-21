<%@page import="com.model.rfi.ExpandedRFI" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
	<c:forEach var="rfi" items="${rfiList}">
	
	<div id="maincontainer" style="font-size:14;margin-top:0.1in;margin-bottom:0.2in;width:8.3in;height:11.4in;position:relative">
		<div id="rfiNumber"style="position:absolute;top:2.25in;left:0.8in;">${rfi.rfiCode}${rfi.rfiNumber}</div>
		<div id="issueDate" style="position:absolute;top:2.25in;left:7in;">${rfi.issueDateAsText}</div>
        <div id="chainage"style="position:absolute;top:4.49in;left:2in;">${rfi.fromChainage} to ${rfi.toChainage}&nbsp;${rfi.side}</div>
		<div id="boq" style="position:absolute;top:4.7in;left:2in;">${rfi.boqNumber}</div>
		<div id="item" style="position:absolute;top:5in;left:2in;">${rfi.itemDescription} (${rfi.wiRemarks})</div>
		<div id="layer" style="position:absolute;top:5.3in;left:2in;">${rfi.layer}</div>
		<div id="inspectionDate" style="position:absolute;top:6.1in;left:2in;">${rfi.inspectionDateAsText}</div>
	</div>
	
	</c:forEach>

</body>

</html>