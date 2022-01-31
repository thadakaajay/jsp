//product
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Product Details Portal</title>");
		out.println("</head>");
		out.println("<body>");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String color = request.getParameter("color");
			float price = Float.parseFloat(request.getParameter("price"));

			ProductClass product1 = new ProductClass(id, name, color, price);
//			product1 = new ProductClass(id, name, color, price));

			HttpSession session = request.getSession();
			session.setAttribute("data", product1);
			request.setAttribute("data", product1.getProductDetails());
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);

			out.println("</body>");
			out.println("</html>");

		} catch (NumberFormatException e) {
			out.println(e);
		} catch (Exception e) {
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}

//Productclass
package com;

import java.util.ArrayList;

public class ProductClass{
	private int id;
	private String name;
	private String color;
	private float price;
	
	public ProductClass(int id, String name, String color, float price){
		this.id=id;
		this.name=name;
		this.color=color;
		this.price=price;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public float getPrice() {
		return price;
	}
	
	public ArrayList<ProductClass> getProductDetails()
	{
		ArrayList<ProductClass> productList = new ArrayList<ProductClass>();
		 
		productList.add(new ProductClass(id, name, color, price));  
		 
		 for (ProductClass s : productList) 
			{
				System.out.print("ID, Name, Color, and Price of the product are : ");
				System.out.println(s.id + " " + s.name + " " + s.color + " " + s.price);
			}
		return productList;
	}
}

//index.jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
</head>
<body>
	<div align="center">
	<form method="post" action="Product">
	<h2>Product Details</h2>
		Id: <input type="number" name="id" required>
		<br/>
		<br/>
		Name: <input type="text" name="name" required>
		<br/>
		<br/>
		Color: <input type="text" name="color" required>
		<br/>
		<br/>
		Price: <input type="number" name="price" required>
		<br/>
		<br/>
		<input type="submit" value="Submit">
	</form>
	</div>
</body>
</html>

//Profile.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ProductClass"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Profile</title>

</head>
<body>

	<%
		ArrayList<ProductClass> p1 = (ArrayList) request.getAttribute("data");
	%>

	<div align="center">
		<table border="1">

			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Color</th>
				<th>Price</th>
			</tr>
			<%
				if (request.getAttribute("data") != null) {
					Iterator<ProductClass> iterator = p1.iterator();

					while (iterator.hasNext()) {
						ProductClass pc = iterator.next();
			%>
			<tr>
				<td><%=pc.getId()%></td>
				<td><%=pc.getName()%></td>
				<td><%=pc.getColor()%></td>
				<td><%=pc.getPrice()%></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>

</body>
</html>

//pom.xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>Product-Details-Portal</groupId>
	<artifactId>Product-Details-Portal</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>war</packaging>
	<build>
		<sourceDirectory>src</sourceDirectory>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.0</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.2.1</version>
				<configuration>
					<warSourceDirectory>WebContent</warSourceDirectory>
				</configuration>
			</plugin>
		</plugins>
	</build>
	<dependencies>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
</project>

//MANIFEST.MF
Manifest-Version: 1.0
Class-Path: 

