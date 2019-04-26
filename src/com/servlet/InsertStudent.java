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

@WebServlet("/InsertStudent")
public class InsertStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PreparedStatement pst;
	public void init(ServletConfig config) throws ServletException {
		try(Scanner scanner=new Scanner(System.in)) {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "AlvaOklahoma");
		pst=con.prepareStatement("insert into student values(?,?,?,?)");
		}
		catch(ClassNotFoundException | SQLException ex)
		{
			ex.getStackTrace();
		}
	}
	public void destroy() {
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String sId=request.getParameter("sId");		//requesting the data from client(httpRequest)
	String sName=request.getParameter("sName");
	String course=request.getParameter("course");
	String fee=request.getParameter("fee");
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	try {
	pst.setInt(1, Integer.parseInt(sId));
	pst.setString(2, sName);
	pst.setString(3, course);
	pst.setFloat(4, Float.parseFloat(fee));
	int result=pst.executeUpdate();
	if(result>0)
	{
	pw.println("<h3 align=center>Record inserted Successfully !");	
	}
	}
	catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	}

}
