package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable{
	
	private static final long serialVeresionUID = 1L;
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;
	
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio, String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar) throws Exception {
		
		/* Cria a lista de Collection Data Source de Beans que carrega os dados do relatorio*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		/* Fornece o caminho fisico até a pasta que contem os relatorios .jasper*/
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jrxml");
		
		if(caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || (!file.exists())) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
			
		}
		
		/* Caminho para imagens */
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		/* Caminho completo do até o relatorio complidado indicado*/
		String caminhoRelatoriosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jrxml";
		
		/* Faz o carregamento do relatorio*/
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoRelatoriosJasper);
		
		/* Seta os parametros SUBREPORT_DIR com o caminho fisico para subreport*/
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		/* Carrega o arquivo*/
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio,jrbcds);
		
		if(tipoExportar.equalsIgnoreCase("pdf")) {
		/*Formato PDF*/
			exporter = new JRPdfExporter();
		}else if(tipoExportar.equalsIgnoreCase("xls")) {
			/*Formato XLS*/
			exporter = new JRXlsExporter();
		}
		
		/*Caminho do relatorio exportado*/
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + "." + tipoExportar;
		
		/*Criar novo arquivo exportado*/
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		/*Prepara a impressão*/
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		/*Nome do arquivo de saida*/
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		/*Executa a exportacao*/
		exporter.exportReport();
		
		/*Remove o arquivo do servidor após ser feito o Download*/
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}
	
}
