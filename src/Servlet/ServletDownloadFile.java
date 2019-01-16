package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import entidades.Usuario;
import service.RelatorioService;

@WebServlet("/pages/ServletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RelatorioService relatorioService = new RelatorioService();
	private DaoUsuario daoUsuario = new DaoUsuario();

	public ServletDownloadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			
			ServletContext context = request.getServletContext();

			String tipoExportar = request.getParameter("tipoExportar");
			List<Usuario> usuarios = daoUsuario.getUsuarios();
			
			String fileUrl = relatorioService.gerarRelatorio(usuarios, new HashMap(), "rel_usuario", "rel_usuario", context, tipoExportar);
			
			/*Construir o caminho completo e absoluto do arquivo*/
			File downloadFile = new File(fileUrl);
			
			/*Ler o arquivo de download*/
			FileInputStream fileInputStream = new FileInputStream(downloadFile);
			
			/*Obter o tip MIME do arquivo */
			String mimeType = context.getMimeType(fileUrl);
			
			if(mimeType == null) {
				//define como tipo binario se o mapeamento mime n�o for encontrado.
				mimeType = "application/octet-stream";
			}
			
			//define atributos para resposta
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			
			//Definir cabe�alho para a resposta
			String headerKey = "content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			
			//Obter fluxo de saida da resposta
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			//Escrever bytes lidos a partir do fluxo de entrada para o fluxo de saida
			while((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,bytesRead);
			}
			fileInputStream.close();
			outputStream.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
