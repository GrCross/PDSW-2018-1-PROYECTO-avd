
package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.view.BasePageBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;

import org.h2.store.fs.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rosal
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "estadisticasBean")
@SessionScoped
public class estadisticasBean extends BasePageBean implements Serializable {
	// <h:graphicImage library="images" name="cabecera.jpg" style =" text-align :
	// center;"> </h:graphicImage>
	// no borrar lo de arriba puede ser util en el xhtml

	private BarChartModel modeloAreas;
	private PieChartModel pieModel2;
	private ArrayList<Iniciativa> iniciativas = new ArrayList<Iniciativa>();
	private String path;

	@Inject
	private ServiciosBancoIniciativas serviciosBancoIniciativa;

	public void consultarIniciativas() {
		iniciativas = serviciosBancoIniciativa.consultarIniciativas();

	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String red() {
		consultarIniciativas();
		createModeloAreas();
		createPieModel2();
		return " ";

	}

	private void createModeloAreas() {
		modeloAreas = new BarChartModel();
		ChartSeries areas = new ChartSeries();
		areas.setLabel("Numero de Proyectos");
		 Hashtable<String, Integer> tablaAreas = valoresTabla1();

		Set<String> keys = tablaAreas.keySet();
		for (String key : keys) {
			areas.set(key, tablaAreas.get(key));
		}
		modeloAreas.addSeries(areas);
		modeloAreas.setTitle("");
		modeloAreas.setLegendPosition("e");
		modeloAreas.setShadow(true);
	}

	private void createPieModel2() {

		pieModel2 = new PieChartModel();
		Hashtable<String, Integer> tablaEstados = valoresTabla2();

		Set<String> keys = tablaEstados.keySet();
		for (String key : keys) {
			pieModel2.set(key, tablaEstados.get(key));
		}

		pieModel2.setTitle("");
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
		pieModel2.setShadow(false);
	}
	
	
	
	
	private Hashtable<String, Integer> valoresTabla1() {

	Hashtable<String, Integer> tablaAreas = new Hashtable<String, Integer>();

	for (Iniciativa in : iniciativas) {
		if (!tablaAreas.containsKey(in.getArea())) {

			tablaAreas.put(in.getArea(), 1);
		} else {

			tablaAreas.put(in.getArea(), tablaAreas.get(in.getArea()) + 1);
		}
	}
	return tablaAreas;
	}
	
	
	
	
	
	private Hashtable<String, Integer> valoresTabla2() {

		Hashtable<String, Integer> tablaEstados = new Hashtable<String, Integer>();
		for (Iniciativa in : iniciativas) {
			if (!tablaEstados.containsKey(in.getEstado().toString())) {

				tablaEstados.put(in.getEstado().toString(), 1);
			} else {

				tablaEstados.put(in.getEstado().toString(), tablaEstados.get(in.getEstado().toString()) + 1);
			}
		}
		return tablaEstados;
	}

	public BarChartModel getModeloAreas() {
		return modeloAreas;
	}

	public void setModeloAreas(BarChartModel modeloAreas) {
		this.modeloAreas = modeloAreas;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public ArrayList<Iniciativa> getIniciativas() {
		return iniciativas;
	}

	public void setIniciativas(ArrayList<Iniciativa> iniciativas) {
		this.iniciativas = iniciativas;
	}

	public void imagenChart2() {
		
		
		String path1 = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath; 
		try {
			path = URLDecoder.decode(path1, "UTF-8");
			
			path=path.substring(0, path.length()-15); 
			path=path.concat("src/main/webapp/resources/images/Creadas");
			System.out.println(path);
			

			DefaultPieDataset chart2 = new DefaultPieDataset();
			Hashtable<String, Integer> tablaEstados = valoresTabla2();

			Set<String> keys = tablaEstados.keySet();
			for (String key : keys) {
				chart2.setValue(key, tablaEstados.get(key));
			}

			JFreeChart chartEstados = ChartFactory.createPieChart("Estados", chart2);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(path+"/chartEstados.png");
			ChartUtilities.saveChartAsPNG(file1, chartEstados, 600, 400, info);
			
			
		} catch (IOException e1) {
			System.out.println("                     Error");
			e1.printStackTrace();
		}

		

	}
	
	
public void imagenChart1() {
		
		
		String path1 = this.getClass().getClassLoader().getResource("").getPath();
	
		try {
			path = URLDecoder.decode(path1, "UTF-8");
			
			path=path.substring(0, path.length()-15); 
			path=path.concat("src/main/webapp/resources/images/Creadas");
			System.out.println(path);
			

			DefaultCategoryDataset chart1 = new DefaultCategoryDataset( );  
			Hashtable<String, Integer> tablaEstados = valoresTabla2();

			Set<String> keys = tablaEstados.keySet();
			for (String key : keys) {
				chart1.addValue(tablaEstados.get(key),key, "cantidad");
			}

			JFreeChart chartAreas = ChartFactory.createBarChart("Cantidad de iniciativas por area","area","cantidad", chart1);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(path+"/chartAreas.png");
			ChartUtilities.saveChartAsPNG(file1, chartAreas, 600, 400, info);
			
			
		} catch (IOException e1) {
			System.out.println("                     Error");
			e1.printStackTrace();
		}

		

	}
	
	
	
	
	
	public void aPDF() {
		System.out.println("ssssssss");
		imagenChart2();
		imagenChart1();
		Document document = new Document();
		Document document2 = new Document();
        try {
        	JFileChooser chooser = new JFileChooser();
        	chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Save Backup");
            chooser.setApproveButtonText("Save");
            //disables the all filesoptioning here
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
            chooser.setAcceptAllFileFilterUsed(false);
            
        			
			PdfWriter.getInstance(document, new FileOutputStream("Estadisticas banco de iniciativas.pdf"));
			PdfWriter.getInstance(document2, new FileOutputStream(new File(chooser.getSelectedFile(), "Esto de iniciativas.pdf")));
			
			
	        document.open();
	        Image img = Image.getInstance(path+"/chartEstados.png");
	        Image img2 = Image.getInstance(path+"/chartAreas.png");
	        document.addHeader("Estadisticas banco de iniciativas","");
	        document.add(new Paragraph("La siguiente grafica muestra la distribucion de las iniciativas en base al estado en el que se encuentran"));
	        document.add(img);
	        document.add(new Paragraph("La siguiente grafica muestra la distribucion de las iniciativas en base a la cantidad de iniciativas por area"));
	        document.add(img2);
	        document.close();
	        System.out.println("Done");
	        FileUtils.delete(path+"/chartEstados.png");

	        FileUtils.delete(path+"/chartAreas.png");
        } catch (DocumentException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
