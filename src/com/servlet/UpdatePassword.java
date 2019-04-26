package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PreparedStatement pst;
	public void init(ServletConfig config) throws ServletException {
		try(Scanner scanner=new Scanner(System.in)) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "AlvaOklahoma");
			pst=con.prepareStatement("update userid set password=? where emailId=?");
			}
			catch(ClassNotFoundException | SQLException ex)
			{
				ex.getStackTrace();
			}
	}
	public void destroy() {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String password=request.getParameter("pwd");
	String confirmPw=request.getParameter("cpwd");
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	HttpSession session=request.getSession();
	String emailId=(String)session.getAttribute("emailId");
	try {
		if(password.equals(confirmPw))
		{
	pst.setString(1, password);
	pst.setString(2, emailId);
	int result=pst.executeUpdate();
	if(result>0)
	{
		pw.print("<h3>Password updated Successfully</h3>");
	}
		}
		else 
			pw.print("<h3> Passwords do not match.</h3> <a href=ChangePassword.html>Try again...</a>");
	}
	catch(SQLException ex)
	{
		ex.getStackTrace();
	}
	
	}

}
