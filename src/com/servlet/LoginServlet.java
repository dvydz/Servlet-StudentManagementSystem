package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PreparedStatement pst;

	public void init(ServletConfig config) throws ServletException {
		try(Scanner scanner=new Scanner(System.in)) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "AlvaOklahoma");
			pst=con.prepareStatement("select emailId from userid where emailId=? and password=?");
			}
			catch(ClassNotFoundException | SQLException ex)
			{
				ex.getStackTrace();
			}
	}

	public void destroy() {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String email=request.getParameter("email");
	String password=request.getParameter("password");
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	try {
	pst.setString(1, email);
	pst.setString(2, password);
	ResultSet rs=pst.executeQuery();
	if(rs.next())
	{
		HttpSession session=request.getSession();
		session.setAttribute("emailId", email);
		response.sendRedirect("Operations.html");
	}
	else
		pw.print("<h3>Invalid email/password</h3> <a href=Login.html>Try again....</a>");
	
	}
	catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	}

}
