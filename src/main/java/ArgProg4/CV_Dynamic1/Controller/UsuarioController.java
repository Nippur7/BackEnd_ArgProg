/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArgProg4.CV_Dynamic1.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ArgProg4.CV_Dynamic1.Model.UsuarioModel;
import ArgProg4.CV_Dynamic1.Service.UsuarioService;
//import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author tonym
 */
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
    
    @PostMapping(path = "/agregar")
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
        
    }

    @GetMapping("/buscar/{id}")
    public UsuarioModel buscarUsuario(@PathVariable Integer id){
        return usuarioService.encontrarUsuario(id);
    } 

    @GetMapping("/buscare/{email}")
    public int encontrarEmail(@PathVariable String email){
        return usuarioService.encontrarEmail(email);
    } 

    @DeleteMapping("/borrar/{id}")
    public String borrarUsuario(@PathVariable Integer id){
        usuarioService.borrarUsuario(id);
        return "Eliminación exitosa";
    }

    
    @PostMapping(path = "/editar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editarUsuario(
        //@RequestBody String userJson, 
        //MultipartFile foto
        @RequestParam("userJson") String userJson,
        @RequestParam("foto") MultipartFile foto,
        HttpServletRequest request
         
        ) throws JsonMappingException, JsonProcessingException
    {
            UsuarioModel user =new ObjectMapper().readValue(userJson, UsuarioModel.class);
            if(!foto.isEmpty()){
                //String ruta = "src//main//resources//static//images/uploads";            
                Path directorioImagenes = Paths.get("src//main//resources//static//images/uploads");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            
                try{
                    byte[] bytes = foto.getBytes();
                    //Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" +foto.getOriginalFilename());
                    Files.write(rutaCompleta, bytes);
               //UsuarioModel user =new ObjectMapper().readValue(userJson, UsuarioModel.class); 
                    user.setPicture(foto.getOriginalFilename());
                    System.out.println(rutaAbsoluta);
                    // usuarioService.actualizarUsuario(user);
               //return "actualizado con exito";
                }catch(Exception e){
                    System.out.println("Error while writing file: " + e.getMessage());
                }
            }
           
        usuarioService.actualizarUsuario(user);
        return "actualizado con exito";
    }

   @GetMapping("/imagen/{filename:.+}")
   public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException{ //byte[]
    // Resource file = 
    try{
        Path directorioImagenes = Paths.get("src\\main\\resources\\static\\images\\uploads");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        Path rutaCompleta = Paths.get(rutaAbsoluta + "\\");
        Path fileP = rutaCompleta.resolve(filename);
        // byte[] archivoBytes = Files.readAllBytes(rutaCompleta);
        // HttpHeaders headers = new HttpHeaders();
        Resource file = new UrlResource((fileP.toUri()));
        if (file.exists() || file.isReadable()){
            String contentType = Files.probeContentType(file.getFile().toPath());
            return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
        }else{
            throw new RuntimeException("No se puede leer el archivo " + fileP.toUri());
        }
        // headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // headers.setContentDispositionFormData(filename, filename);
        // headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        // ResponseEntity<byte[]> response = new ResponseEntity<>(archivoBytes, headers, HttpStatus.OK);
        // return response;
    }catch (MalformedURLException e){
        throw new RuntimeException("Error de path "+filename);

    }
   }

}
