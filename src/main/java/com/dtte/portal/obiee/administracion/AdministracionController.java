package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.persistencia.PortalbiConfigObieeMapper;

public class AdministracionController extends HttpServlet {

	  @Inject
	  PortalbiConfigObieeMapper userMapper;
	  
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    try (PrintWriter out = response.getWriter()) {
	      /* TODO output your page here. You may use following sample code. */
	      out.println("<!DOCTYPE html>");
	      out.println("<html>");
	      out.println("<head>");
	      out.println("<title>Servlet SampleServlet</title>");
	      out.println("</head>");
	      out.println("<body>");
	      out.println("<h1>User (Servlet): " + userMapper.getConfig(0) +"</h1>");
	      out.println("</body>");
	      out.println("</html>");
	    }
	  }

}
