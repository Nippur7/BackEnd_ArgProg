package ArgProg4.CV_Dynamic1.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ArgProg4.CV_Dynamic1.Model.ProyectoModel;
import ArgProg4.CV_Dynamic1.Repository.ProyectoRepository;

/**
 *
 * @author tonym
 */
@Service
public class ProyectoService {
    
    @Autowired
    ProyectoRepository proyectoRepository;
    public ArrayList<ProyectoModel> obtenerProyecto(){
        return (ArrayList<ProyectoModel>) proyectoRepository.findAll();
    }
    
    public ProyectoModel guardarProyecto(ProyectoModel proyecto){
        return proyectoRepository.save(proyecto);
        
    }

    public ProyectoModel encontrarProyecto(Integer id){
        ProyectoModel proy = proyectoRepository.findById(id).orElse(null);
        return proy;
    }

    public void borrarProyecto(Integer id){
        proyectoRepository.deleteById(id);
    }

}
