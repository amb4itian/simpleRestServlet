package com.amb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Person p = new Person();
		p.setCity("Bangalore");
		p.setId(1);
		p.setName("Amb");
		String resPerson = new Gson().toJson(p, Person.class);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(resPerson);
		out.flush();
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {

			BufferedReader reader = request.getReader();

			while ((line = reader.readLine()) != null)
				jb.append(line);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(jb);
			JsonParser parser = new JsonParser();
			JsonObject o = parser.parse(jb.toString()).getAsJsonObject();
			Person personObject = new Gson().fromJson(o, Person.class);
			
			System.out.println("ID - "+personObject.getId());
			System.out.println("Name - "+personObject.getName());
			System.out.println("City - "+personObject.getCity());
			// Gson gson = new Gson().toJson(jb.toString(), String.class) ;
			// //HTTP.toJSONObject(jb.toString());

		} catch (Exception e) {
			throw new IOException("Error parsing JSON request string");
		}

		// Work with the data using methods like...

		// int someInt = jsonObject.getInt("intParamName");

		// String someString = jsonObject.getString("stringParamName");

		// JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");

		// JSONArray arr = jsonObject.getJSONArray("arrayParamName");

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jb);
		out.flush();
	}

}
