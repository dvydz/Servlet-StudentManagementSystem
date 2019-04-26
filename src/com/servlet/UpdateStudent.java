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

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PreparedStatement pst;
	public void init(ServletConfig config) throws ServletException {
		try(Scanner scanner=new Scanner(System.in)) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "AlvaOklahoma");
			pst=con.prepareStatement("update student set course=?,fee=? where sId=?");
			}
			catch(ClassNotFoundException | SQLException ex)
			{
				ex.getStackTrace();
			}
	}


	public void destroy() {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId=request.getParameter("sId");
		String nCourse=request.getParameter("nCourse");
		String nFee=request.getParameter("nFee");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try {
			pst.setString(1, nCourse);
			pst.setString(2, nFee);
			pst.setString(3, sId);
			int result=pst.executeUpdate();
			if(result>0)
			{
				pw.print("<h3> records updated Successfully</h3>");
			}
				else 
					pw.print("<h3> Error </h3> <a href=newData.html>Try again...</a>");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
