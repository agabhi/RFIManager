function swapBreakRfi() 
{
	if(document.getElementById("breakIndicator").checked == true)
	{
		document.getElementById("breakSeperator").style.display = '';
		document.getElementById("breakRfiString").style.display = '';
	}
	else
	{
		document.getElementById("breakSeperator").style.display = 'none';
		document.getElementById("breakRfiString").style.display = 'none';
	}
}

String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
};

function dblclick(rId,cInd){
		
		myCalendar2.clearSensitiveRange();
		removeAllApprovals();
		removeAllBills();
		document.getElementById("breakIndicator").checked = false;
		swapBreakRfi();
		/*
		document.getElementById("breakRfiString").value = "";
		document.getElementById("rfiCode").value = mygrid.cellById(rId, 0).getValue();
		document.getElementById("rfiNumber").value = mygrid.cellById(rId, 1).getValue();
		document.getElementById("rfiHeader").innerText="Edit RFI Number: "+document.getElementById("rfiNumber").value;
		document.getElementById("issueDate").value = mygrid.cellById(rId, 2).getValue();
		myCalendar1.setDate(document.getElementById("issueDate").value);
		document.getElementById("item").value = mygrid.cellById(rId, 3).getValue();
		document.getElementById("fromChainage").value = mygrid.cellById(rId, 4).getValue();
		document.getElementById("toChainage").value = mygrid.cellById(rId, 5).getValue();
		document.getElementById("side").value = mygrid.cellById(rId, 6).getValue();
		document.getElementById("layer").value = mygrid.cellById(rId, 7).getValue();
		document.getElementById("status").value = mygrid.cellById(rId, 8).getValue();
		document.getElementById("grade").value = mygrid.cellById(rId, 9).getValue();
		document.getElementById("inspectionDate").value = mygrid.cellById(rId, 10).getValue();
		myCalendar2.setDate(document.getElementById("inspectionDate").value);
		document.getElementById("billNumber").value = mygrid.cellById(rId, 18).getValue();
		document.getElementById("remarks").value = mygrid.cellById(rId, 19).getValue();
		*/
		//alert("Check0");
		//alert("check");
		//alert(mygrid.cellById(rId, 1).getValue());
		var ids=mygrid.findCell(mygrid.cellById(rId, 1).getValue(),1); //find row which contains "value" in 3rd column
		//alert(ids);
		var len = ids.length;
		//alert("length = "+len);
		var i = 1;
		
		//alert("checkafterremove");
		var approvalArray=new Array();
		var billArray=new Array();
		var approvalCounter = 1;
		var billCounter = 1;
		while (i <= len) 
		{
			//alert("Check0.5");	
			var approvalFrom = mygrid.cellById(ids[i-1][0], 13).getValue();
			var approvalTo = mygrid.cellById(ids[i-1][0], 14).getValue();
			var approvalSide = mygrid.cellById(ids[i-1][0], 15).getValue();
			var approvalLayer = mygrid.cellById(ids[i-1][0], 16).getValue();
			
			
			//var billNumber = mygrid.cellById(ids[i-1][0], 17).getValue();;
			//var billFrom = mygrid.cellById(ids[i-1][0], 18).getValue();
			//var billTo = mygrid.cellById(ids[i-1][0], 19).getValue();
			//var billSide = mygrid.cellById(ids[i-1][0], 20).getValue();
			//var billLayer = mygrid.cellById(ids[i-1][0], 21).getValue();
			//alert("Check1");
			if( mygrid.cellById(rId, 1).getValue() == mygrid.cellById(ids[i-1][0], 1).getValue())
			{
				//alert("CheckingEmpty1");
					
						//alert("CheckingEmpty2");
						//alert("Without if - approvalFrom"+i+" = "+mygrid.cellById(ids[i-1][0], 14).getValue());
						 if(!approvalArray.contains(approvalFrom+"::"+approvalTo+"::"+approvalSide+"::"+approvalLayer)) 
						 {
							 if(!isEmpty(approvalFrom))
								{
								 createApproval();
								 //alert("Inside if - approvalFrom"+i+" = "+mygrid.cellById(ids[i-1][0], 14).getValue());
								 document.getElementById("approvalFrom"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 13).getValue();
								 document.getElementById("approvalTo"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 14).getValue();
								 document.getElementById("approvalSide"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 15).getValue();
								 document.getElementById("approvalLayer"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 16).getValue();
								 document.getElementById("approvalDate"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 17).getValue();
								 approvalDate[approvalCounter-1].setDate(document.getElementById("approvalDate"+approvalCounter).value);
		 						 approvalCounter=approvalCounter+1;
								}
							 	else
							 	{
							 	createApproval();
								 document.getElementById("approvalFrom"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 13).getValue();
								 document.getElementById("approvalTo"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 14).getValue();
								 document.getElementById("approvalSide"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 15).getValue();
								 document.getElementById("approvalLayer"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 16).getValue();
								 //document.getElementById("approvalDate"+approvalCounter).value = mygrid.cellById(ids[i-1][0], 17).getValue();
								 //approvalDate[approvalCounter-1].setDate(document.getElementById("approvalDate"+approvalCounter).value);
		 						 approvalCounter=approvalCounter+1;
							 	}
								 
							 
						 }
						 
						 
			}
					//alert("CheckingEmpty3");
					 /*
					if(!billArray.contains(billNumber+"::"+billFrom+"::"+billTo+"::"+billSide+"::"+billLayer)) {
											
						createBill();
						document.getElementById("billNumber"+billCounter).value = mygrid.cellById(ids[i-1][0], 18).getValue();
						document.getElementById("billFrom"+billCounter).value = mygrid.cellById(ids[i-1][0], 19).getValue();
						document.getElementById("billTo"+billCounter).value = mygrid.cellById(ids[i-1][0], 20).getValue();
						document.getElementById("billSide"+billCounter).value = mygrid.cellById(ids[i-1][0], 21).getValue();
						document.getElementById("billLayer"+billCounter).value = mygrid.cellById(ids[i-1][0], 22).getValue();
						billCounter = billCounter + 1;
					}
					 */
	
		approvalArray[i] = approvalFrom+"::"+approvalTo+"::"+approvalSide+"::"+approvalLayer;
		//billArray[i] = billNumber+"::"+billFrom+"::"+billTo+"::"+billSide+"::"+billLayer;
		i=i+1;
		}

		statusChange();
		enable("rfiDetailsTable");
		row_clicked_number = rId;
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
		//alert(cellObj.getValue());
		
		
	}



function disable(table_id)
{
    var inputs=document.getElementById(table_id).getElementsByTagName('input');
    for(var i=0; i<inputs.length; ++i)
    {
        inputs[i].disabled=true;
        if(inputs[i].type != "submit")
        	inputs[i].value="";
    	}
    inputs=document.getElementById(table_id).getElementsByTagName('select');
    for(var i=0; i<inputs.length; ++i)
    	{
        inputs[i].disabled=true;
    inputs[i].value="";
    	}
    inputs=document.getElementById(table_id).getElementsByTagName('textarea');
    for(var i=0; i<inputs.length; ++i)
    {
        inputs[i].disabled=true;
    inputs[i].value="";
    	}
    $(".collapsibleContainerContent").slideUp(); 
}

function enable(table_id)
{
	//   alert("Enable");
    var inputs=document.getElementById(table_id).getElementsByTagName('input');
    for(var i=0; i<inputs.length; ++i)
        inputs[i].disabled=false;
    inputs=document.getElementById(table_id).getElementsByTagName('select');
    for(var i=0; i<inputs.length; ++i) {
    	if(inputs[i].name != null) {
    		//alert(inputs[i].name);
    		if(!inputs[i].name.startsWith("approvalLayer")) {
    			inputs[i].disabled=false;
    		}
    	}
    }
    inputs=document.getElementById(table_id).getElementsByTagName('textarea');
    for(var i=0; i<inputs.length; ++i)
        inputs[i].disabled=false;
}

function statusChange() {
	var status = document.getElementById("status");
	 if(status != null){
		 if(status.value == "Approved") {
			 document.getElementById("approvalBox").style.display='';
			 //document.getElementById("billNumber").style.display='';
			 if(approval_row_counter == 0)
			 {
				 createApproval();
			 }
			 if(isEmpty(document.getElementById("approvalFrom1").value) && isEmpty(document.getElementById("approvalTo1").value)) {
				 document.getElementById("approvalFrom1").value = document.getElementById("fromChainage").value;
				 document.getElementById("approvalTo1").value = document.getElementById("toChainage").value;
				 document.getElementById("approvalSide1").value = document.getElementById("side").value;
				 document.getElementById("approvalLayer1").value = document.getElementById("layer").value;
				 //alert("Approval Date 1 - "+document.getElementById("approvalDate1").value);
				 //alert("Approval Date 2 - "+document.getElementById("approvalDate2").value);
			 }
			 
		 }
		 else {
			 document.getElementById("approvalBox").style.display='none';
			 //document.getElementById("billNumber").style.display='none';
		 }
	 }
	 //alert("statusChange2");
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
	   //alert("chainage number");
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
/*
function isNumeric(strString)
//  check for valid numeric strings	
{
var strValidChars = "0123456789.-";
var strChar;
var blnResult = true;

if (strString.length == 0) return false;

//  test strString consists of valid characters listed above
for (i = 0; i < strString.length && blnResult == true; i++)
   {
   strChar = strString.charAt(i);
   if (strValidChars.indexOf(strChar) == -1)
      {
      blnResult = false;
      }
   }
return blnResult;
}
*/

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


	function clickSubmit(successMsg, rfiNum) {
		
		if (!isInteger(document.getElementById("fromChainage").value) ) {
			alert("From Chainage should be a non-negative integer!");
			return false;
		}
		if (!isInteger(document.getElementById("toChainage").value) ) {
			alert("To Chainage should be a non-negative integer!");
			return false;
		}
		//alert("check4");
		if (!chainageCheck(document.getElementById("fromChainage"),document.getElementById("toChainage"),"From Chainage should be a less than or equal to To Chainage!")) {
			//alert("check5");
			return false;
		}
		
		if(isEmpty(document.getElementById("item").value))
		{
			alert("Please select an Item!");
			return false;
		}
		
		if(isEmpty(document.getElementById("activity").value))
		{
			alert("Please select an Activity!");
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
		
		if(isEmpty(document.getElementById("boqNumber").value))
		{
			alert("Please select a BOQ Number!");
			return false;
		}
		
		if(compareDates(myCalendar1.getDate(), myCalendar2.getDate()) == 1) {
			
			alert("Inspection date should be later than Issue date");
			return false;
		}
		
		//alert("check6");
		i=1;
		while (i <= approval_row_counter) {
			if(!(isEmpty(document.getElementById("approvalFrom"+i).value) && isEmpty(document.getElementById("approvalTo"+i).value))) {
				
				if (!isInteger(document.getElementById("approvalFrom"+i).value) ) {
					alert("Approval From ("+i+") Chainage should be a non-negative integer!");
					return false;
				}
				if (!isInteger(document.getElementById("approvalTo"+i).value) ) {
					alert("Approval To ("+i+") Chainage should be a non-negative integer!");
					return false;
				}
				if (!chainageCheck(document.getElementById("approvalFrom"+i),document.getElementById("approvalTo"+i),"Approval From ("+i+") Chainage should be a less than To ("+i+") Chainage!")) {
					return false;
				}
				
				if (!chainageCheck(document.getElementById("approvalFrom"+i),document.getElementById("toChainage"),"Approval From ("+i+") is not in range!")) {
					//alert("check5");
					return false;
				}
				
				if (!chainageCheck(document.getElementById("fromChainage"),document.getElementById("approvalFrom"+i),"Approval From ("+i+") is not in range!")) {
					//alert("check5");
					return false;
				}
				if (!chainageCheck(document.getElementById("approvalTo"+i),document.getElementById("toChainage"),"Approval To ("+i+") is not in range!")) {
					//alert("check5");
					return false;
				}
				
				if (!chainageCheck(document.getElementById("fromChainage"),document.getElementById("approvalTo"+i),"Approval To ("+i+") is not in range!")) {
					//alert("check5");
					return false;
				}
				/*
				if (!chainageNumber(document.getElementById("billFrom"+i).value,"Bill From ("+i+") Chainage should be a number!")) {
					return false;
				}
				if (!chainageNumber(document.getElementById("billTo"+i).value,"Bill To ("+i+") Chainage should be a number!")) {
					return false;
				}
				if (!chainageCheck(document.getElementById("billFrom"+i),document.getElementById("approvalTo"+i),"Bill From ("+i+") Chainage should be a less than To ("+i+") Chainage!")) {
					return false;
				}
				*/
				
				if(isEmpty(document.getElementById("approvalSide"+i).value))
				{
					alert("Please select a Approval Side (+"+i+")!");
					return false;
				}
				
				if(isEmpty(document.getElementById("approvalLayer"+i).value))
				{
					alert("Please select a Approval Layer (+"+i+")!");
					return false;
				}
				
				if(compareOnlyDates(myCalendar2.getDate(), approvalDate[i-1].getDate()) == 1) {
					
					alert("Approval date ("+i+") should be later than or same as Inspection date");
					return false;
				}
				
				if(!isEmpty(document.getElementById("quantity"+i).value) && !isNumeric(document.getElementById("quantity"+i).value))
				{
					alert("Quantity ("+i+") should either be blank or numeric.");
					return false;
				}
				
			}
			
			i=i+1;
			
		}
		
		
		var queryString = $('#editForm').serialize();
		//alert(queryString);
		
		var url1 ="rfiUpdate.htm?"+queryString;
		/*
		$.get(url1, function(data) {
			alert("RFI Edited successfully!");
			});
			*/
		
		if (successMsg == "print")
		{
			
			$.get(url1,
					  {  },
					  function(xml){disable("rfiDetailsTable");document.getElementById("rfiHeader").innerText="Edit RFI Number: ";$("#list").trigger("reloadGrid"); var url = 'rfiPrint.htm?';
						url = url + "rfiNumber=" + rfiNum +"&";
						var myWindow = window.open(url,'','width=0,height=0');
						myWindow.print();});
		}
		else
		{

			$.get(url1,
					  {  },
					  function(xml){disable("rfiDetailsTable");document.getElementById("rfiHeader").innerText="Edit RFI Number: ";$("#list").trigger("reloadGrid");alert("RFI Edited successfully"); });
			
		}
		//alert("RFI Edited successfully!");
		/*
		$('#editForm').ajaxForm(function() { 
            alert("RFI Edited successfully!"); 
        }); 
			*/
		
//document.getElementById("issueDate").value = document.getElementById("issueDate").value + " 00:00";	
return true;
}
	

	function clickClearChanges() {
		loadRFIDetails(false);
		return false;
	}
	
	function createApproval() {
		if(approval_row_counter == 10) {
			alert("You have reached the limit of 10 approvals per RFI!");
			return;
		}
		if(approval_row_counter == 0) {
		var captionRow = document.getElementById("captionRow");
		var approvalRow1 =  document.getElementById("approvalRow1");
		captionRow.style.display = '';
		approvalRow1.style.display = '';
		approval_row_counter = approval_row_counter+1;
		
		}
		else {
			var approvalRow =  document.getElementById("approvalRow"+(approval_row_counter+1));
			approvalRow.style.display = '';
			document.getElementById("approvalSide"+(approval_row_counter+1)).value = document.getElementById("side").value;
			document.getElementById("approvalLayer"+(approval_row_counter+1)).value = document.getElementById("layer").value;
			approval_row_counter = approval_row_counter+1;
			
		}
	}
	
	function removeApproval() {
		if(approval_row_counter == 0) {
			alert("There are no approvals to remove!");
			return;
		}
		if(approval_row_counter == 1) {
		var captionRow = document.getElementById("captionRow");
		var approvalRow1 =  document.getElementById("approvalRow1");
		document.getElementById("approvalFrom1").value = "";
		document.getElementById("approvalTo1").value = "";
		document.getElementById("approvalSide1").value = "";
		document.getElementById("approvalLayer1").value = "";
		document.getElementById("approvalDate1").value = "";
		document.getElementById("quantity1").value = "";
		captionRow.style.display = 'none';
		approvalRow1.style.display = 'none';
		approval_row_counter = approval_row_counter-1;
		
		}
		else {
			var approvalRow =  document.getElementById("approvalRow"+(approval_row_counter));
			approvalRow.style.display = 'none';
			document.getElementById("approvalFrom"+approval_row_counter).value = "";
			document.getElementById("approvalTo"+approval_row_counter).value = "";
			document.getElementById("approvalSide"+approval_row_counter).value = "";
			document.getElementById("approvalLayer"+approval_row_counter).value = "";
			document.getElementById("approvalDate"+approval_row_counter).value = "";
			document.getElementById("quantity"+approval_row_counter).value = "";
			//document.getElementById("approvalSide"+(approval_row_counter+1)).value = document.getElementById("side").value;
			//document.getElementById("approvalLayer"+(approval_row_counter+1)).value = document.getElementById("layer").value;
			approval_row_counter = approval_row_counter-1;
			
		}
	}
	
	function createBill() {
		//alert("check");
		if(bill_row_counter == 10) {
			alert("You have reached the limit of 10 billings per RFI!");
			return;
		}
		if(bill_row_counter == 0) {
		var captionRow = document.getElementById("captionBillRow");
		var billRow1 =  document.getElementById("billRow1");
		captionRow.style.display = '';
		billRow1.style.display = '';
		bill_row_counter = bill_row_counter+1;
		}
		else {
			var billRow =  document.getElementById("billRow"+(bill_row_counter+1));
			billRow.style.display = '';
			bill_row_counter = bill_row_counter+1;
		}
	}
	
	function removeAllApprovals() {
		//alert("removestart");
		approval_row_counter = 0;
		i=1;
		while(i<=10){
			approvalDate[i-1].setDate(new Date());
			approvalDate[i-1].clearSensitiveRange();
			document.getElementById("approvalDate"+i).value= approvalDate[i-1].getFormatedDate();
			var approvalRow =  document.getElementById("approvalRow"+i);
			approvalRow.style.display = 'none';
			
			i=i+1;
		}
		//alert("removeend");
	}
	
	function removeAllBills() {
		//alert("removestart");
		bill_row_counter = 0;
		i=1;
		while(i<=10){
			var billRow =  document.getElementById("billRow"+i);
			billRow.style.display = 'none';
			i=i+1;
		}
		//alert("removeend");
	}

	
	
	function doOnLoad() {
		//document.getElementById("issueDate").value = co
		myCalendar1 = new dhtmlXCalendarObject(["issueDate"]);
		//myCalendar1.show();
		myCalendar1.setDateFormat("%d-%m-%Y");
		myCalendar1.hideTime();
		myCalendar1.setDate(new Date());
		document.getElementById("issueDate").value= myCalendar1.getFormatedDate();
		//myCalendar1.se
		//myCalendar1.hide();
		//alert((new Date()).getTime());
		myCalendar2 = new dhtmlXCalendarObject(["inspectionDate"]);
		//myCalendar2.show();
		//myCalendar2.setSensitiveRange(myCalendar1.getFormatedDate(), null);
		//myCalendar2.setSensitiveRange("15-08-2011 09:00", null);
		myCalendar2.setDateFormat("%d-%m-%Y %H:%i");
		var d = new Date((new Date()).getTime()+86400000);
		d.setHours(9);
		d.setMinutes(0,0,0);
		myCalendar2.setDate(d);
		//myCalendar2.setSensitiveRange("06-06-2011 09:00", null);
		//myCalendar2.setDate(new Date((new Date()).getTime()+86400000));
		document.getElementById("inspectionDate").value= myCalendar2.getFormatedDate();
		
		
		approvalDate[0] = new dhtmlXCalendarObject(["approvalDate1"]);
		//myCalendar1.show();
		approvalDate[0].setDateFormat("%d-%m-%Y");
		approvalDate[0].hideTime();
		approvalDate[0].setDate(new Date());
		document.getElementById("approvalDate1").value= approvalDate[0].getFormatedDate();
		
		approvalDate[1] = new dhtmlXCalendarObject(["approvalDate2"]);
		//myCalendar1.show();
		approvalDate[1].setDateFormat("%d-%m-%Y");
		approvalDate[1].hideTime();
		approvalDate[1].setDate(new Date());
		document.getElementById("approvalDate2").value= approvalDate[1].getFormatedDate();
		
		approvalDate[2] = new dhtmlXCalendarObject(["approvalDate3"]);
		//myCalendar1.show();
		approvalDate[2].setDateFormat("%d-%m-%Y");
		approvalDate[2].hideTime();
		approvalDate[2].setDate(new Date());
		document.getElementById("approvalDate3").value= approvalDate[2].getFormatedDate();
		
		approvalDate[3] = new dhtmlXCalendarObject(["approvalDate4"]);
		//myCalendar1.show();
		approvalDate[3].setDateFormat("%d-%m-%Y");
		approvalDate[3].hideTime();
		approvalDate[3].setDate(new Date());
		document.getElementById("approvalDate4").value= approvalDate[3].getFormatedDate();
		
		approvalDate[4] = new dhtmlXCalendarObject(["approvalDate5"]);
		//myCalendar1.show();
		approvalDate[4].setDateFormat("%d-%m-%Y");
		approvalDate[4].hideTime();
		approvalDate[4].setDate(new Date());
		document.getElementById("approvalDate5").value= approvalDate[4].getFormatedDate();
		
		approvalDate[5] = new dhtmlXCalendarObject(["approvalDate6"]);
		//myCalendar1.show();
		approvalDate[5].setDateFormat("%d-%m-%Y");
		approvalDate[5].hideTime();
		approvalDate[5].setDate(new Date());
		document.getElementById("approvalDate6").value= approvalDate[5].getFormatedDate();
		
		approvalDate[6] = new dhtmlXCalendarObject(["approvalDate7"]);
		//myCalendar1.show();
		approvalDate[6].setDateFormat("%d-%m-%Y");
		approvalDate[6].hideTime();
		approvalDate[6].setDate(new Date());
		document.getElementById("approvalDate7").value= approvalDate[6].getFormatedDate();
		
		approvalDate[7] = new dhtmlXCalendarObject(["approvalDate8"]);
		//myCalendar1.show();
		approvalDate[7].setDateFormat("%d-%m-%Y");
		approvalDate[7].hideTime();
		approvalDate[7].setDate(new Date());
		document.getElementById("approvalDate8").value= approvalDate[7].getFormatedDate();
		
		approvalDate[8] = new dhtmlXCalendarObject(["approvalDate9"]);
		//myCalendar1.show();
		approvalDate[8].setDateFormat("%d-%m-%Y");
		approvalDate[8].hideTime();
		approvalDate[8].setDate(new Date());
		document.getElementById("approvalDate9").value= approvalDate[8].getFormatedDate();
		
		approvalDate[9] = new dhtmlXCalendarObject(["approvalDate10"]);
		//myCalendar1.show();
		approvalDate[9].setDateFormat("%d-%m-%Y");
		approvalDate[9].hideTime();
		approvalDate[9].setDate(new Date());
		document.getElementById("approvalDate10").value= approvalDate[9].getFormatedDate();
		
		
		var myEvent = myCalendar1.attachEvent("onClick", function (){
			var dat = new Date(myCalendar1.getDate().getTime()+86400000);
			dat.setHours(myCalendar2.getDate().getHours());
			dat.setMinutes(myCalendar2.getDate().getMinutes());
			myCalendar2.setSensitiveRange(myCalendar1.getFormatedDate()+" 00:00", null);
			myCalendar2.setDate(dat);
			document.getElementById("inspectionDate").value= myCalendar2.getFormatedDate();
			//alert(myCalendar2.getFormatedDate());
			
			//myCalendar2.setSensitiveRange("2011-07-08", null);
			
			var formatedInspectionDate = dat.getDate()+"-"+(+dat.getMonth()+1)+"-"+dat.getFullYear();
			//approvalDate[0].setSensitiveRange(formatedInspectionDate, null);
			//alert(formatedInspectionDate);
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
		var myEvent1 = myCalendar2.attachEvent("onClick", function (){
			var dat = myCalendar2.getDate();
			var formatedInspectionDate = dat.getDate()+"-"+(+dat.getMonth()+1)+"-"+dat.getFullYear();
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
		
		//alert("ApprovalDate 1 - "+document.getElementById("approvalDate1").value);
		 //alert("ApprovalDate 2 - "+document.getElementById("approvalDate2").value);
		
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