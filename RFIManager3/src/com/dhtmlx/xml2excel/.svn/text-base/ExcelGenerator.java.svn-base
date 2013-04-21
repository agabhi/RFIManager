package com.dhtmlx.xml2excel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class ExcelGenerator extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String sdh ="";
		try
	    {
			File file = new File("C:\\Users\\Abhishek\\Desktop\\check.txt");
			FileOutputStream fos = new FileOutputStream(file);
	        InputStream inputStream = req.getInputStream();
	        StringWriter strWrite = new StringWriter();
	        //strWrite.write(
	        
	        char buf[]=new char[1024];
	        int len;
	        Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        while((len=reader.read(buf))>0)
	        strWrite.write(buf,0,len);
	        strWrite.close();
	        sdh = strWrite.toString();
	        inputStream.close();
	        
	        System.out.println(sdh);
	        //FileWriter fos = new FileWriter(file);
	        //FileOutputStream fos = new FileOutputStream(file);
	        //fos.write(str);
	        //fos.close();
	        sdh = sdh.substring(sdh.indexOf("<rows"),sdh.indexOf("</rows>"));
	        sdh = sdh+"</rows>";
	        System.out.println(sdh);
	    }
		
	    catch (Exception e)
	    {
	        System.out.println("Error writing request");
	    }
		String xml = req.getParameter("grid_xml");
		if (xml == null){
			System.out.println(" It is null \n\n\n;");
		}
		System.out.println(" It is not null \n\n\n;");
		//xml = URLDecoder.decode(xml, "UTF-8");
		(new ExcelWriter()).generate(sdh, resp);
	}

}