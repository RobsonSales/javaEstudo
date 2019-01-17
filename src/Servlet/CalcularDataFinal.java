package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;

@WebServlet("/pages/calcularDataFinal")
public class CalcularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DaoCalculaDataFinal calculaDataFinal = new DaoCalculaDataFinal();
       
    public CalcularDataFinal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		/*08:00 - 12:00 e 13:00 - 18:00*/
		/*1 dia é igual a 8 horas */
		
		try {
			
			int horaDia = 8;
			Date dataCalculada = null;
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			Double totalDeDias = 0.0;
			
			if(tempo <= horaDia) {//mesmo dia
				
				Date dataInformada = new SimpleDateFormat("dd/mm/yyyy").parse(data);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInformada);
				calendar.add(Calendar.DATE, 2);
				
				dataCalculada = calendar.getTime();
				totalDeDias = 1.0;
				
			}else {
				
				totalDeDias = (double) (tempo / horaDia);
				
				if(totalDeDias <= 1) {
					dataCalculada = new SimpleDateFormat("dd/mm/yyyy").parse(data);
				}else {
					Date dataInformada = new SimpleDateFormat("dd/mm/yyyy").parse(data);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dataInformada);
					calendar.add(Calendar.DATE, totalDeDias.intValue());
					
					dataCalculada = calendar.getTime();
				}
				
			}
			
			calculaDataFinal.gravaDataFinal(new SimpleDateFormat("dd/mm/yyy").format(dataCalculada));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/datas.jsp");
			request.setAttribute("dataFinal", new SimpleDateFormat("dd/mm/yyy").format(dataCalculada));
			request.setAttribute("dias", totalDeDias);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
