
import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.view.BasePageBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
@ManagedBean(name = "insertUsersBean")
@ApplicationScoped
public class IniciativasUsuariosBean extends BasePageBean {
    
    private int documento;
    
    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa; 
    
    public void registrarIniciativa (int id,int estado, String nombre, String descripcion, ArrayList<String> palabrasClave ){
         
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	//Date date = new Date();
        
        
        //Calendar today = Calendar.getInstance();
    	//today.set(Calendar.HOUR_OF_DAY, 0);
        
        
        //Usuario usuario = new Usuario(documento);
        //Iniciativa  iniciativa = new Iniciativa(id,estado,nombre,descripcion,java.time.LocalDate.now(),palabrasClave);
        
    }
}
