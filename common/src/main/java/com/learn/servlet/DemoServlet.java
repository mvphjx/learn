package com.learn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DemoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String  name="" ;
	@Override
	protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		synchronized(this){
			doService(req, resp);
		}
	}

	@Override
	protected synchronized  void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected synchronized void doService(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String result = ""; 
		String param = request.getParameter("name");
		request.setCharacterEncoding("utf-8");
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			System.out.println(param+"*****"+request.getParameter("production"));
				name =request.getParameter("name");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				response.getWriter().println("你的名字是:" + name);	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			//outputResult(response, result);
		}	
	}

	/**
	 * 输出信息
	 * 
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	protected void outputResult(HttpServletResponse response, String result)
			throws IOException {
		System.out.println(result);
		PrintWriter writer = response.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
	}
}
