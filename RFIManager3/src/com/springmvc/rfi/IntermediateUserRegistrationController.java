package com.springmvc.rfi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.model.rfi.Bill;
import com.model.rfi.RFIService;

public class IntermediateUserRegistrationController extends AbstractController

{
	
private RFIService rfiService;

public void setRfiService(RFIService rfiService)
{ 
	this.rfiService = rfiService; 
}


@Override
 protected ModelAndView handleRequestInternal(HttpServletRequest request,
		 										HttpServletResponse response) throws Exception
{
	List<UserRegistrationForm> users = rfiService.getUsersList();
	request.setAttribute("users", users);
	request.getRequestDispatcher("/userRegister.htm").forward(request, response);
	return null;
}

}
