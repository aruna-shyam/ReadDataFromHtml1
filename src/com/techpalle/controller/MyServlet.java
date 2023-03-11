package com.techpalle.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.*;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/My")
public class MyServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//fetch or read the data from html name control we use request variable
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbEmail");
		//every singe time checkbox ans ll be changeable
		String[] s=request.getParameterValues("chkSub");
		String g=request.getParameter("rdGender");
		String c=request.getParameter("ddlCity");
		
		
		/*//display it in browser so  we use response vari inside it have gw(),pw obj
		PrintWriter pw=response.getWriter();
		pw.append("<b> Name: </b>"+name);
		pw.append("<br/> <b> Email: </b>"+email);
		pw.append("<br/> <b> Sub: </b>"+Arrays.toString(sub));
		pw.append("<br/> <b> Gender: </b>"+gender);
		pw.append("<br/> <b> City: </b>"+city);*/
		
		//jdbc step to store above data into users(sqlworkbench we create) table:
		Connection con=null;
		PreparedStatement ps=null;
		
		try 
		{
			//loading the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","admin");
			
			//prepare statement
			String qry="insert into users (name,email,subject,gender,location) values(?,?,?,?,?)";
			ps=con.prepareStatement(qry);
			ps.setString(1, n);
			ps.setString(2, e);
		    ps.setString(3, Arrays.toString(s));
			ps.setString(4, g);
			ps.setString(5,c);
			
			//excecute query
			ps.executeUpdate();
			
		 } 
		catch (ClassNotFoundException e1) 
		{
			e1.printStackTrace();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				if(s!=null)
				{
				  ps.close();
				}
				if(con!=null)
				{
				  con.close();	
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}

}
