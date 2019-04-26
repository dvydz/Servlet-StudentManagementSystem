package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PreparedStatement pst;
	public void init(ServletConfig config) throws ServletException {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","AlvaOklahoma");
		pst=con.prepareStatement("delete from student where sId=?");
		}
		catch(ClassNotFoundException | SQLException ex)
		{
			ex.printStackTrace();
		}
	
	}


	public void destroy() {
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	try {
	String sId=request.getParameter("sId");
	pst.setInt(1, Integer.parseInt(sId));
	int result=pst.executeUpdate();
	if(result>0)
	{
		pw.print("<h1 align=center> Record deleted successully !</h3>");
	}
	else
		pw.print("<h1 align=center style='background-color:red'> Record Not Found !</h3>");
	}
	catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	}

}
