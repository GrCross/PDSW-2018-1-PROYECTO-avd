
package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.view.BasePageBean;
import java.io.File;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.Hashtable;



import java.util.LinkedHashMap;
import java.util.Map;

import java.util.Set;


import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


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

import java.net.URLDecoder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;




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
        private BarChartModel modeloMasLikes;
	

	

	@Inject
	private ServiciosBancoIniciativas serviciosBancoIniciativa;

	public void consultarIniciativas() throws ExcepcionBancoIniciativas {
		iniciativas = serviciosBancoIniciativa.consultarIniciativas();

	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String red() throws ExcepcionBancoIniciativas {
		consultarIniciativas();
		createModeloAreas();
		createPieModel2();
		createModeloMasLikes();
		return " ";
	}

	private void createModeloAreas() {
		modeloAreas = new BarChartModel();
		ChartSeries areas = new ChartSeries();
		areas.setLabel("Numero de Proyectos");
		Hashtable<String, Integer> tablaAreas = valoresTabla1();
		int cont=0;
		Set<String> keys = tablaAreas.keySet();
		for (String key : keys) areas.set(key, tablaAreas.get(key));
			
		modeloAreas.addSeries(areas);
		modeloAreas.setTitle("");
		modeloAreas.setLegendPosition("e");
		modeloAreas.setShadow(true);
	}
	

	private void createPieModel2() {

		pieModel2 = new PieChartModel();
		Hashtable<String, Integer> tablaEstados = valoresTabla2();

		Set<String> keys = tablaEstados.keySet();
		int cont =0;
		for (String key : keys) {
                    if(cont<=10) pieModel2.set(key, tablaEstados.get(key));
		}
		pieModel2.setTitle("");
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(300);
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
                    String estado = in.getEstado().toString();
                    if (!tablaEstados.containsKey(estado)) {

                            tablaEstados.put(estado, 1);
                    } else {
                            tablaEstados.put(estado, tablaEstados.get(estado) + 1);
                    }
		}
		return tablaEstados;
	}

	

	public void imagenChart2() {

		String path1 = this.getClass().getClassLoader().getResource("").getPath();

		try {
			path = URLDecoder.decode(path1, "UTF-8");

			path = path.substring(0, path.length() - 15);
			path = path.concat("src/main/webapp/resources/images/Creadas");
			//path = path.concat("src/main/webapp/resources/images");

			DefaultPieDataset chart2 = new DefaultPieDataset();
			Hashtable<String, Integer> tablaEstados = valoresTabla2();

			Set<String> keys = tablaEstados.keySet();
			for (String key : keys) {
				chart2.setValue(key, tablaEstados.get(key));
			}

			JFreeChart chartEstados = ChartFactory.createPieChart("Estados", chart2);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(path + "/chartEstados.png");
			ChartUtilities.saveChartAsPNG(file1, chartEstados, 400, 300, info);

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

	}

	public void imagenChart1() {

		String path1 = this.getClass().getClassLoader().getResource("").getPath();

		try {
			path = URLDecoder.decode(path1, "UTF-8");
			

			path = path.substring(0, path.length() - 15);
			path = path.concat("src/main/webapp/resources/images/Creadas");
			//path = path.concat("src/main/webapp/resources/images");

			DefaultCategoryDataset chart1 = new DefaultCategoryDataset();
			Hashtable<String, Integer> tablaEstados = valoresTabla1();

			Set<String> keys = tablaEstados.keySet();
			for (String key : keys) {

				chart1.addValue(tablaEstados.get(key), "Cantidad de proyectos", key);
			}

			JFreeChart chartAreas = ChartFactory.createBarChart("Cantidad de iniciativas por area", "Areas",
					"Cantidad de proyectos", chart1);

			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

			final File file1 = new File(path + "/chartAreas.png");

			ChartUtilities.saveChartAsPNG(file1, chartAreas, 550, 300, info);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void aPDF() {
		imagenChart2();
		imagenChart1();
		Document document = new Document();
		Document document2 = new Document();
		try {
			String home = System.getProperty("user.home");
			
			PdfWriter.getInstance(document, new FileOutputStream(home+"/Downloads/Estadisticas banco de iniciativas.pdf"));
			
			document.open();

			document.addTitle("Resumen estadisticas banco de iniciativas");
			document.leftMargin();

			Image img = Image.getInstance(path + "/chartEstados.png");
			Image img2 = Image.getInstance(path + "/chartAreas.png");
			document.addHeader("Estadisticas banco de iniciativas", "");
			document.add(new Paragraph(
					"La siguiente grafica muestra la distribucion de las iniciativas en base al estado en el que se encuentran"));
			document.add(img);
			document.add(new Paragraph(
					"La siguiente grafica muestra la distribucion de las iniciativas en base a la cantidad de iniciativas por area"));
			document.add(img2);
			document.close();
			FileUtils.delete(path + "/chartEstados.png");


			FileUtils.delete(path + "/chartAreas.png");
			
			  FacesMessage msg;
              msg = new FacesMessage("El archivo pdf se ha guardado en su carpeta de descargas bajo el nombre 'Estadisticas banco de iniciativas'");
              FacesContext.getCurrentInstance().addMessage(null, msg);
              
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  

	}

    
    private void createModeloMasLikes() {
        modeloMasLikes = new BarChartModel();
        ChartSeries masLiked = new ChartSeries();
        masLiked.setLabel("Numero de Likes");
        Map<String, Integer> tablaLikes = new LinkedHashMap<>();
        Map<String, Integer> tablaLikesOrd = new LinkedHashMap<>();
        //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (Iniciativa in : iniciativas) {
            tablaLikes.put(in.getNombre(),in.getVotos());
        }
        
        //se esta ordenando de mayor a menor los votos de cada uno
        tablaLikes.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> tablaLikesOrd.put(x.getKey(), x.getValue()));

//        Set<String> keys = tablalikes.keySet();
//        for (String key : keys) {
//            areas.set(key, tablalikes.get(key));
//        }
        Set<String> keys = tablaLikesOrd.keySet();
        int cont = 0;
        for (String key : keys){
            masLiked.set(key, tablaLikesOrd.get(key));
            cont+=1;
            if (cont==7){break;} 
        }
        modeloMasLikes.addSeries(masLiked);
        modeloMasLikes.setTitle("");
        modeloMasLikes.setLegendPosition("e");
        modeloMasLikes.setShadow(true);
        
    }




        public BarChartModel getModeloMasLikes() {
            return modeloMasLikes;
        }

        public void setModeloMasLikes(BarChartModel modeloMasLikes) {
            this.modeloMasLikes = modeloMasLikes;
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
	
	
   

}
