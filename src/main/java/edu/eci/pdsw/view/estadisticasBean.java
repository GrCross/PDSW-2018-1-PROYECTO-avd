
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
        Hashtable<String, Integer> tablaEstados = new Hashtable<String, Integer>();

        for (Iniciativa in : iniciativas) {
            if (!tablaEstados.containsKey(in.getEstado().toString())) {

                tablaEstados.put(in.getEstado().toString(), 1);
            } else {

                tablaEstados.put(in.getEstado().toString(), tablaEstados.get(in.getEstado().toString()) + 1);
            }
        }
    

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
