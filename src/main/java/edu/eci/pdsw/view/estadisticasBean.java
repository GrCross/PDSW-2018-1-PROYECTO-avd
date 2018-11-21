///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
//package edu.eci.pdsw.view;
//
//import com.google.inject.Inject;
//import edu.eci.pdsw.samples.entities.Comentario;
//import edu.eci.pdsw.samples.entities.Estado;
//import edu.eci.pdsw.samples.entities.Iniciativa;
//import edu.eci.pdsw.samples.entities.Rol;
//import edu.eci.pdsw.samples.entities.Usuario;
//import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
//import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import org.primefaces.event.ItemSelectEvent;
//import org.primefaces.model.chart.PieChartModel;
//
///**
// *
// * @author estudiante
// */
//@SuppressWarnings("deprecation")
//@ManagedBean(name = "StaticsBean")
//@SessionScoped
///**
// *
// * @author 2130104
// */
//public class StaticsBean extends BasePageBean implements Serializable {
//
//    private PieChartModel modeloAreas;
//    private PieChartModel pieModel2;
//    ArrayList<Iniciativa> iniciativas;
//
//    @Inject
//    private ServiciosBancoIniciativas serviciosBancoIniciativa;
//
//    @PostConstruct
//    public void init() {
//
//        createPieModels();
//        //iniciativas = serviciosBancoIniciativa.consultarIniciativas();
//
//    }
//
//    public void itemSelect(ItemSelectEvent event) {
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
//                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    private void createPieModels() {
//        createModeloAreas();
//        createPieModel2();
//    }
//
//    public PieChartModel getPieModel1() {
//        return modeloAreas;
//    }
//
//    public void setPieModel1(PieChartModel modeloAreas) {
//        this.modeloAreas = modeloAreas;
//    }
//
//    public PieChartModel getPieModel2() {
//        return pieModel2;
//    }
//
//    public void setPieModel2(PieChartModel pieModel2) {
//        this.pieModel2 = pieModel2;
//    }
//
//    private void createModeloAreas() {
//        modeloAreas = new PieChartModel();
//        Hashtable<String, Integer> tablaAreas = new Hashtable<String, Integer>();
//        
//        for (Iniciativa in:iniciativas){
//            if(!tablaAreas.contains(in.getArea())){
//                //tablaAreas. put(in.getArea(), tablaAreas[in.getArea()]+1);
//            }
//        }
//
//        modeloAreas.set("Brand 1", 540);
//        modeloAreas.set("Brand 2", 325);
//        modeloAreas.set("Brand 3", 702);
//        modeloAreas.set("Brand 4", 421);
//
//        modeloAreas.setTitle("Iniciativas por area");
//        modeloAreas.setLegendPosition("e");
//        modeloAreas.setShadow(true);
//    }
//
//    private void createPieModel2() {
//        pieModel2 = new PieChartModel();
//
//        pieModel2.set("Brand 1", 100);
//        pieModel2.set("Brand 2", 1);
//        pieModel2.set("Brand 3", 1);
//        pieModel2.set("Brand 4", 1);
//
//        pieModel2.setTitle("Custom Pie");
//        pieModel2.setLegendPosition("e");
//        pieModel2.setFill(false);
//        pieModel2.setShowDataLabels(true);
//        pieModel2.setDiameter(150);
//        pieModel2.setShadow(false);
//    }
//
//}
package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.view.BasePageBean;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

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
    //<h:graphicImage library="images" name="cabecera.jpg" style =" text-align : center;"> </h:graphicImage> 
    // no borrar lo de arriba puede ser util en el xhtml

    private BarChartModel modeloAreas;
    private PieChartModel pieModel2;
    private ArrayList<Iniciativa> iniciativas = new ArrayList<Iniciativa>();

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa;

    public void consultarIniciativas() {
        iniciativas = serviciosBancoIniciativa.consultarIniciativas();
        System.out.println(iniciativas.size());

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
        Hashtable<String, Integer> tablaAreas = new Hashtable<String, Integer>();

        for (Iniciativa in : iniciativas) {
            if (!tablaAreas.containsKey(in.getArea())) {

                tablaAreas.put(in.getArea(), 1);
            } else {

                tablaAreas.put(in.getArea(), tablaAreas.get(in.getArea()) + 1);
            }
        }
        System.out.println(tablaAreas.toString());

        Set<String> keys = tablaAreas.keySet();
        for (String key : keys) {
            areas.set(key, tablaAreas.get(key));
        }
        modeloAreas.addSeries(areas);
        modeloAreas.setTitle("Iniciativas por area");
        modeloAreas.setLegendPosition("e");
        modeloAreas.setShadow(true);
    }

    private void createPieModel2() {

        pieModel2 = new PieChartModel();
        Hashtable<String, Integer> tablaEstados = new Hashtable<String, Integer>();

        for (Iniciativa in : iniciativas) {
            if (!tablaEstados.containsKey(in.getEstado().toString())) {

                tablaEstados.put(in.getEstado().toString(), 1);
            } else {

                tablaEstados.put(in.getEstado().toString(), tablaEstados.get(in.getEstado().toString()) + 1);
            }
        }
        System.out.println(tablaEstados.toString());

        Set<String> keys = tablaEstados.keySet();
        for (String key : keys) {
            pieModel2.set(key, tablaEstados.get(key));
        }

        pieModel2.setTitle("Estados");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
        pieModel2.setShadow(false);
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
