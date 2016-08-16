package co.edu.usbcali.actividades.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class RegistroActividadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RegistroActividadDTO.class);
    private Date fechaCreacion;
    private Date fechaRegistro;
    private Integer regiId;
    private Date tiempoHoras;
    private Long tiempoMin;
    private Integer actiId_Actividad;
    private Integer usuaId_Usuario;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getRegiId() {
        return regiId;
    }

    public void setRegiId(Integer regiId) {
        this.regiId = regiId;
    }

    public Date getTiempoHoras() {
        return tiempoHoras;
    }

    public void setTiempoHoras(Date tiempoHoras) {
        this.tiempoHoras = tiempoHoras;
    }

    public Long getTiempoMin() {
        return tiempoMin;
    }

    public void setTiempoMin(Long tiempoMin) {
        this.tiempoMin = tiempoMin;
    }

    public Integer getActiId_Actividad() {
        return actiId_Actividad;
    }

    public void setActiId_Actividad(Integer actiId_Actividad) {
        this.actiId_Actividad = actiId_Actividad;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }
}
