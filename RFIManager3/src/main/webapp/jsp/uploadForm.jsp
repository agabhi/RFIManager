<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload</title>
    </head>
    
    <style>
		
		table.curvedEdges { border:1px solid lightgray;-webkit-border-radius:8px;-moz-border-radius:8px;border-radius:8px; }
		table.curvedEdges td table.curvedEdges th { border-bottom:1px dotted black;padding:5px; }
		
		table.curvedEdges1 { border:1px solid lightgray;-webkit-border-top-left-radius:8px;-moz-border-top-left-radius:8px;border-top-left-radius:8px;-webkit-border-top-right-radius:8px;-moz-border-top-right-radius:8px;border-top-right-radius:8px; }
		table.curvedEdges1 td table.curvedEdges1 th { border-bottom:1px dotted black;padding:5px; }

	</style>
    
    
    <body>
    <table class="curvedEdges" style="width:98%;" cellspacing="0" cellpadding="5"  bgcolor="#F2F2F2" id="rfiDetailsTable"> 
			<tr>
				<td style="border:0;padding-left:0px;padding-top:0px;padding-bottom:0px;padding-right:0px;">
					<table class="curvedEdges1" style="width:100%;" cellspacing="0" cellpadding="5">
						<tr><td style="background-image:url(css/ui-lightness/images/ui-bg_gloss-wave_35_f6a828_500x100.png); background-repeat: repeat-x;">
		        			<label id="rfiHeader" style="font-family:Comic Sans MS;font-size:medium;color:#FFFFFF;">Upload RFI File</label>
		        		</td></tr>
		        	</table>
		        	
		        </td>
		     </tr> 
		 
		
			<tr>
				<td style="padding-top:12;padding-bottom:0;font-size:large">
					<form:form commandName="fileUpload" action="fileUpload.htm" enctype="multipart/form-data" cssStyle="margin: 0px; padding: 0px;">
					<p style="font-family: Verdana, Geneva, sans-serif;">
						Please specify a file:<br><br>
					<input type="file" name="file" style="font-size:large;font-family: Verdana,Geneva,sans-serif;"/>
					</p>
					<br>
					<input type="submit" value="Import" style="font-size:large;"/>
						
					</form:form>
				</td>
			</tr>
    </body>
</html>