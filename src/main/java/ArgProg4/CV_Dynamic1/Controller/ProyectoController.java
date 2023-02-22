package ArgProg4.CV_Dynamic1.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ArgProg4.CV_Dynamic1.Model.ProyectoModel;
import ArgProg4.CV_Dynamic1.Service.ProyectoService;

/**
 *
 * @author tonym
 */
@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    ProyectoService proyectoService;
    
    @GetMapping()
    public ArrayList<ProyectoModel> obtenerProyecto(){
        return proyectoService.obtenerProyecto();
    }
    
    @PostMapping(path = "/agregar")
    public ProyectoModel guardarProyecto(@RequestBody ProyectoModel proyecto){
        return this.proyectoService.guardarProyecto(proyecto);
        
    }
    
    @GetMapping("/buscar/{id}")
    public ProyectoModel buscarProy(@PathVariable Integer id){
        return proyectoService.encontrarProyecto(id);
    } 

    @DeleteMapping("/borrar/{id}")
    public String borrarProy(@PathVariable Integer id){
        proyectoService.borrarProyecto(id);
        return "Eliminación exitosa";
    }

    @GetMapping("/usuario/{id}")
    public ArrayList<ProyectoModel> obtenerProyUser(@PathVariable Integer id){
        ArrayList<ProyectoModel> byUser = new ArrayList<ProyectoModel>();
        ArrayList<ProyectoModel> aux = proyectoService.obtenerProyecto();
        //Integer ii = 0;
        for (int i=0;i<aux.size();i++) {      
            //System.out.println(lista.get(i));
            if(aux.get(i).getIduser() == id){
                byUser.add(aux.get(i));

            }
          }
        return byUser;
    }

}
