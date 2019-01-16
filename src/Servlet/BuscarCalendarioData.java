package Servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoEventos;
import entidades.Eventos;

@WebServlet("/pages/buscarCalendarioData")
public class BuscarCalendarioData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoEventos daoEventos = new DaoEventos();

	public BuscarCalendarioData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			List<Eventos> eventos = daoEventos.getEventos();
			
			if(!eventos.isEmpty()) {
				
				int totalEventos = eventos.size();
				int index = 1;
				String datas = "[";
						
				
				for (Eventos event : eventos) {
					datas += "{\"title\": \""+event.getDescricao()+"\", \"start\": \""+event.getDataevento()+"\"}]";
					
					if(index < totalEventos) {
						datas += ",";
					}
					index++;
				}
				
				datas += "]";
				
				response.setStatus(200);
				response.getWriter().write(datas);
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
