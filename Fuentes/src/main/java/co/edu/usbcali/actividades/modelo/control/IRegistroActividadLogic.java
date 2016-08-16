package co.edu.usbcali.actividades.modelo.control;

import co.edu.usbcali.actividades.modelo.RegistroActividad;
import co.edu.usbcali.actividades.modelo.dto.RegistroActividadDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IRegistroActividadLogic {
    public List<RegistroActividad> getRegistroActividad()
        throws Exception;

    /**
         * Save an new RegistroActividad entity
         */
    public void saveRegistroActividad(RegistroActividad entity)
        throws Exception;

    /**
         * Delete an existing RegistroActividad entity
         *
         */
    public void deleteRegistroActividad(RegistroActividad entity)
        throws Exception;

    /**
        * Update an existing RegistroActividad entity
        *
        */
    public void updateRegistroActividad(RegistroActividad entity)
        throws Exception;

    /**
         * Load an existing RegistroActividad entity
         *
         */
    public RegistroActividad getRegistroActividad(Integer regiId)
        throws Exception;

    public List<RegistroActividad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<RegistroActividad> findPageRegistroActividad(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberRegistroActividad() throws Exception;

    public List<RegistroActividadDTO> getDataRegistroActividad()
        throws Exception;
}
