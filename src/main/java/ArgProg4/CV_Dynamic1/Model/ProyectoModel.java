package ArgProg4.CV_Dynamic1.Model;

import jakarta.persistence.*;

/**
 *
 * @author tonym
 */
@Entity
@Table(name = "proyecto")
public class ProyectoModel {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idproyecto;
    private String titulo;
    private String descripcion;
    private String link;
    private int iduser;
    private int idexperiencia;


    public int getIdproyecto() {
        return this.idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIduser() {
        return this.iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdexperiencia() {
        return this.idexperiencia;
    }

    public void setIdexperiencia(int idexperiencia) {
        this.idexperiencia = idexperiencia;
    }

}
