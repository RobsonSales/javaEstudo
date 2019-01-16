package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		// usar variavel fileUpload para salvar no banco de dados

		try {
			String fileUpload = request.getParameter("fileUpload");

			// neste momento faz o insert no banco de dados

			response.getWriter().write("Upload realizado com sucesso");
		} catch (Exception e) {
			response.getWriter().write("Erro fatal ao realizar upload");
		}

	}
}
