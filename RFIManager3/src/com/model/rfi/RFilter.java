package com.model.rfi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class RFilter implements Filter {
	
	static private Pattern macPattern=Pattern.compile(".*(([0-9a-f]{2}[-:]){5}[0-9a-f]{2}).*",Pattern.CASE_INSENSITIVE);

	static final String[] windowsCommand={"ipconfig","/all"};

	static final String[] linuxCommand={"/sbin/ifconfig","-a"};
	
	public final static List getMacAddresses() throws IOException{
		final List macAddressList=new ArrayList();

		// Extract the MAC addresses from stdout
		BufferedReader reader=getMacAddressesReader();
		for(String line=null;(line=reader.readLine())!=null;){
		Matcher matcher=macPattern.matcher(line);
		if(matcher.matches()){
		macAddressList.add(matcher.group(1).replaceAll("[-:]",""));
		}
		}
		reader.close();

		return macAddressList;
		}
	public final static String getMacAddress() throws IOException{
		return getMacAddress(0);
		}
	
	public final static String getMacAddress(int nicIndex) throws IOException{
		// Extract the MAC addresses from stdout
		BufferedReader reader=getMacAddressesReader();
		int nicCount=0;
		for(String line=null;(line=reader.readLine())!=null;){
		Matcher matcher=macPattern.matcher(line);
		if(matcher.matches()){
		if(nicCount==nicIndex){
		reader.close();
		return matcher.group(1).replaceAll("[-:]","");
		}
		nicCount++;
		}
		}
		reader.close();
		return null;
		}
	
	
	private static BufferedReader getMacAddressesReader() throws IOException{
		final String[] command;
		final String os=System.getProperty("os.name");

		if(os.startsWith("Windows")){
		command=windowsCommand;
		}else if(os.startsWith("Linux")){
		command=linuxCommand;
		}else{
		throw new IOException("Unknown operating system: "+os);
		}

		final Process process=Runtime.getRuntime().exec(command);

		// Discard the stderr
		new Thread(){
		public void run(){
		try{
		InputStream errorStream=process.getErrorStream();
		while(errorStream.read()!=-1){};
		errorStream.close();
		}catch(IOException e){
		e.printStackTrace();
		}
		}

		}.start();

		// Extract the MAC addresses from stdout
		return new BufferedReader(new InputStreamReader(process.getInputStream()));
		}
	 
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
    	
    	final Logger logger = Logger.getLogger(getClass());
    	
    	
    	List addressList = getMacAddresses();
    	ListIterator li = addressList.listIterator();
    	while (li.hasNext()){
    	String a = li.next().toString();
    	if("00210064A578".equals(a) || "B8AC6FDCE6C8".equals(a) || "38607755C0B8".equals(a) )
    	{
    		chain.doFilter(req, res);
    		return;
    	}
    	
    	}
    	
    
    res.setContentType("html");
    PrintWriter out = res.getWriter();
    out.println("<html><body>Please contact administrator!!</body></html>");
    out.close();
    	
    	/*
        HttpServletRequest request = (HttpServletRequest) req;
 
        if("00-21-00-64-A5-78".equals(k()))
        {
        	chain.doFilter(req, res);
        }
        else
        {
        	logger.error("sss"+"00-21-00-64-A5-78"+"sss");
        	logger.error("sss"+k()+"sss");
        	res.setContentType("html");
		    PrintWriter out = res.getWriter();
		    out.println("<html><body>Please contact administrator!!</body></html>");
		    out.close();
        }
        */
    }
    
    private String k()
	{
    	String s = "";
		try {
		InetAddress address = InetAddress.getLocalHost();
		//InetAddress address = InetAddress.getByName("192.168.46.53");
		 
		/*
		* Get NetworkInterface for the current host and then read the
		* hardware address.
		*/
		NetworkInterface ni = NetworkInterface.getByInetAddress(address);
		if (ni != null) {
		byte[] mac = ni.getHardwareAddress();
		if (mac != null) {
		/*
		* Extract each array of mac address and convert it to hexa with the
		* following format 08-00-27-DC-4A-9E.
		*/
			
		for (int i = 0; i < mac.length; i++) {
		String m  =String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
		s = s+m;
		}
		
		} else {
		System.out.println("Address doesn't exist or is not accessible.");
		}
		} else {
		System.out.println("Network Interface for the specified address is not found.");
		}
		return s;
		} catch (UnknownHostException e) {
		e.printStackTrace();
		return null;
		} catch (SocketException e) {
		e.printStackTrace();
		return null;
		}
	}
    
    public void init(FilterConfig config) throws ServletException {
 
        //Get init parameter
        String testParam = config.getInitParameter("test-param");
 
        //Print the init parameter
        System.out.println("Test Param: " + testParam);
    }
    public void destroy() {
        //add code to release any resource
    }
}
