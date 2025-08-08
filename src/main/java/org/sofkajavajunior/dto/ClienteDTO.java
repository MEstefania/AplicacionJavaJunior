package org.sofkajavajunior.dto;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteDTO {
    @NotNull(message = "El nombre no puede ser nulo.")
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("genero")
    @Size(max = 10)
    private String genero;
    @JsonProperty("edad")
    private int edad;
    @JsonProperty("identificacion")
    @Size(max = 30)
    private String identificacion;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("telefono")
    @Size(max = 10)
    private String telefono;
    @NotNull(message = "La contraseña no puede ser nulo.")
    @JsonProperty("contrasenia")
    private String contrasenia;
    @NotNull(message = "El estado no puede ser nulo.")
    @JsonProperty("estado")
    private String estado;

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
