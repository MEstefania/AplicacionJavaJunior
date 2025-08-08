package org.sofkajavajunior.model;

import javax.persistence.*;

@Entity
@Table(
        name = "persona",
        schema = "sofka")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_per", nullable = false)
    private Long id;
    @Column(name = "nombre_per", nullable = false)
    private String nombre;
    @Column(name = "genero_per", length = 1)
    private String genero;
    @Column(name = "edad_per")
    private Integer edad;
    @Column(name = "identificacion_per", length = 30)
    private String identificacion;
    @Column(name = "direccion_per", nullable = false)
    private String direccion;
    @Column(name = "telefono_per", nullable = false, length = 10)
    private String telefono;

    public Persona() {
    }

    public Persona(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
