package co.edu.usbcali.actividades.presentation.businessDelegate;

import co.edu.usbcali.actividades.modelo.Actividad;
import co.edu.usbcali.actividades.modelo.RegistroActividad;
import co.edu.usbcali.actividades.modelo.Usuario;
import co.edu.usbcali.actividades.modelo.control.ActividadLogic;
import co.edu.usbcali.actividades.modelo.control.IActividadLogic;
import co.edu.usbcali.actividades.modelo.control.IRegistroActividadLogic;
import co.edu.usbcali.actividades.modelo.control.IUsuarioLogic;
import co.edu.usbcali.actividades.modelo.control.RegistroActividadLogic;
import co.edu.usbcali.actividades.modelo.control.UsuarioLogic;
import co.edu.usbcali.actividades.modelo.dto.ActividadDTO;
import co.edu.usbcali.actividades.modelo.dto.RegistroActividadDTO;
import co.edu.usbcali.actividades.modelo.dto.UsuarioDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Actividad> getActividad() throws Exception;

    public void saveActividad(Actividad entity) throws Exception;

    public void deleteActividad(Actividad entity) throws Exception;

    public void updateActividad(Actividad entity) throws Exception;

    public Actividad getActividad(Integer actiId) throws Exception;

    public List<Actividad> findByCriteriaInActividad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Actividad> findPageActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberActividad() throws Exception;

    public List<ActividadDTO> getDataActividad() throws Exception;

    public List<RegistroActividad> getRegistroActividad()
        throws Exception;

    public void saveRegistroActividad(RegistroActividad entity)
        throws Exception;

    public void deleteRegistroActividad(RegistroActividad entity)
        throws Exception;

    public void updateRegistroActividad(RegistroActividad entity)
        throws Exception;

    public RegistroActividad getRegistroActividad(Integer regiId)
        throws Exception;

    public List<RegistroActividad> findByCriteriaInRegistroActividad(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<RegistroActividad> findPageRegistroActividad(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberRegistroActividad() throws Exception;

    public List<RegistroActividadDTO> getDataRegistroActividad()
        throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(Integer usuaId) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;
}
