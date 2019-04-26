package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowStudent")
public class ShowStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PreparedStatement pst;
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","AlvaOklahoma");
			pst=con.prepareStatement("select * from student where sId=?");
			}
			catch(ClassNotFoundException | SQLException ex)
			{
				ex.printStackTrace();	
			}
	}
	public void destroy() {
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId=request.getParameter("sId");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try {
			pst.setString(1,sId);
			ResultSet rs=pst.executeQuery();
			ResultSetMetaData md=pst.getMetaData();
			pw.print("<table align=center border=2 cellspacing=0>");
			for(int i=1;i<=md.getColumnCount();i++)
			{
				pw.print("<th style='background-color:yellow' width=75>"+md.getColumnName(i).toUpperCase()+"</th>");
			}
			while(rs.next())
			{
				pw.print("<tr>");
				for(int i=1;i<=md.getColumnCount();i++)
				{
				pw.print("<td style='background-color:cyan' style='text-align:center'>"+rs.getString(i)+"</td>");
				}
				pw.print("</tr>");
			}
			pw.print("</table>");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
