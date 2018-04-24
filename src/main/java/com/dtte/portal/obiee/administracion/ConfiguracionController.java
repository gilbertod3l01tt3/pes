package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;

public class ConfiguracionController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override 
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws IOException,ServletException{
		
				String actionFromWeb = request.getServletPath();
				System.out.println("actionFromWeb: "+actionFromWeb);
				if (!actionFromWeb.equals("/configuracion"))
					throw new ServletException("Error en el llamado");
				try {
					//Obteniendo el parametro de accion
					String accion=request.getParameter("accion");
					System.out.println("accion: "+accion);
					
					switch (accion) {
					case "new":
						newForm(request, response);
						break;
					case "insert":
						insert(request, response);
						break;
					case "edit":
						update(request, response);
						break;
					case "delete":
						update(request, response);
						break;
					default:
						listarConfiguraciones(request, response);
						break;
					}
				//listarConfiguraciones(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
			
			
			private void insert(HttpServletRequest request, HttpServletResponse response) {
				String parametro = request.getParameter("parametro");
				String valor = request.getParameter("valor");
				PORTALBI_CONFIGOBIEE newConfig=new PORTALBI_CONFIGOBIEE();
				newConfig.setParametro(parametro);
				newConfig.setValor(valor);
				new BookServiceImpl().createBook(newBook);
				response.sendRedirect("/administracion/configuracionGeneral.jsp");
			}

			private void newForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/EditarConfiguracionGeneral.jsp");
				dispatcher.forward(request, response);
			}




			private void update(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
				int id = Integer.parseInt(request.getParameter("id"));
		
				}


			private void listarConfiguraciones(HttpServletRequest request, HttpServletResponse response)
					throws SQLException, IOException, ServletException {
				java.util.List<PORTALBI_CONFIGOBIEE> listaConf = new ConfigOBIIEImpl().listAllConfigs();
				request.setAttribute("listaConfiguraciones", listaConf);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/configuracionGeneral.jsp");
				dispatcher.forward(request, response);
			}
			
	
}
