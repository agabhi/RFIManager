package com.model.rfi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class DashboardController extends AbstractController{
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg0.getRequestDispatcher("/jsp/home.jsp").forward(arg0, arg1);
		//arg0.getRequestDispatcher("/jsp/statusBarChartForm.jsp").forward(arg0, arg1);
		return null;
	}

}
