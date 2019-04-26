package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/abc")
public class StudentServletCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Initialized");
	}

	public void destroy() {
		System.out.println("Destroyed...");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get Method");			//response to client(httpResponse)
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("<html>");
		pw.print("<body bgcolor=yellow> <h2>Student Management System </h2></body>");
		pw.print("</html>");
	}

}
