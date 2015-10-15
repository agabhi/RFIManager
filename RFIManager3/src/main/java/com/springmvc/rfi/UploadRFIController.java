package com.springmvc.rfi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.model.rfi.RFIService;
import com.model.rfi.UploadForm;

public class UploadRFIController extends SimpleFormController{
	
	private RFIService rfiService;
	
	
	public void setRfiService(RFIService rfiService) { 
		this.rfiService = rfiService; 
		}
		
		@Override
	    protected ModelAndView onSubmit(HttpServletRequest req, 
	                                    HttpServletResponse res, 
	                                    Object command, 
	                                    BindException errors) throws Exception {
//	        res.setContentType("text/plain");
	        if (!(req instanceof MultipartHttpServletRequest)) {
	            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected multipart request");
	            return null;
	        }
	        UploadForm bean = (UploadForm) command;
	        MultipartFile file = bean.getFile();
	        File destination = new File("C:\\temp\\" + file.getOriginalFilename());
	        file.transferTo(destination);
	        File errorsFile = rfiService.uploadRFIFile(destination);
//	        res.getWriter().write("Success, wrote to " + destination.getAbsolutePath());        
//	        res.flushBuffer();
	        //return new ModelAndView(getSuccessView(),"destination",destination.getAbsolutePath());
	        
	        
	        ServletOutputStream stream = null;
	        BufferedInputStream buf = null;
	        
	        stream = res.getOutputStream();

	        //set response headers
	        res.setContentType("application/txt");
	        res.addHeader("Content-Disposition", "attachment; filename=errors.txt");

	        

	        FileInputStream fis = new FileInputStream(errorsFile);
	        buf = new BufferedInputStream(fis);
	        int readBytes = 0;

	        //read from the file; write to the ServletOutputStream
	        while ((readBytes = buf.read()) != -1)
	          stream.write(readBytes);
	        
	        stream.close();
	        buf.close();
	        fis.close();
	        
	        return null;
	        
	        
	      } 
	    

}
