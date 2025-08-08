package org.sofkajavajunior.model;

import javax.persistence.*;

@Entity
@Table(
        name = "cliente",
        schema = "sofka")
public class Cliente extends Persona{
    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cli", nullable = false)
    private Long id;
    @Column(name = "contrasenia_cli", nullable = false)
    private String contrasenia;
    @Column(name = "estado_cli",nullable = false)
    private Boolean estado;

    public Cliente() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
