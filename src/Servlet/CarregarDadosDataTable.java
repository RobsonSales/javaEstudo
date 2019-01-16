package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import entidades.Usuario;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public CarregarDadosDataTable() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			List<Usuario> usuarios = daoUsuario.getUsuarios();

			if (!usuarios.isEmpty()) {
				
				String data = "";
				int totalUsuarios = usuarios.size();
				int index = 1;
				
				for (Usuario usuario : usuarios) {
					
					data += " ["+ 
							"\"" +usuario.getId()+"\"," + 
							"\""+usuario.getLogin()+"\"," + 
							"\""+usuario.getNome()+"\"," + 
							"\""+usuario.getTel()+"\"," + 
							"\""+usuario.getCep()+"\","+ 
							"\""+usuario.getRua()+"\"," +
							"\""+usuario.getBairro()+"\"," +
							"\""+usuario.getCidade()+"\"," +
							"\""+usuario.getUf()+"\"," +
							"\""+usuario.getIbge()+"\"" +
						"]"; 
					
					if(index < totalUsuarios) {
						data += ",";
						index++; 
					}
						
					
				}

				String json = "{" +

						"\"draw\": 1," + "\"recordsTotal\": " + usuarios.size() + "," + "\"recordsFiltered\":"+ usuarios.size() + "+," + 
						"\"data\": [" + 
						
						//INICIO - processa a lista de dados
						data + 
						
						//FIM - Processa a lista de dados

						"]" + //fechamento do Data
						"}"; //fechamento do JSON
						
						

				response.setStatus(200); // resposta compelta OK.
				response.getWriter().write(json); // JSON de resposta (Escreve a resposta HTTP)
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
