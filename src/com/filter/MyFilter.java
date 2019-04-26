package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/LoginPage")
public class MyFilter implements Filter {

		public void destroy() {
			System.out.println("In destroy...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long startTime=9*60*60;
		long endTime=15*60*60;
		Date dt=new Date();
		int hours=dt.getHours();
		int minutes=dt.getMinutes();
		int seconds=dt.getSeconds();
		long curTime=(long)(hours*60*60)+(minutes*60)+(seconds);
		if(curTime>=startTime && curTime<=endTime)
		{			
			chain.doFilter(request, response);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.print("<html><body><h2>Application will be accessible between 9 AM and 3 PM</h2></body></html>");
		}
	
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("In INIT...");
	}

}
