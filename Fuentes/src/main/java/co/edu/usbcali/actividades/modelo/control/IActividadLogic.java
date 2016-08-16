package co.edu.usbcali.actividades.modelo.control;

import co.edu.usbcali.actividades.modelo.Actividad;
import co.edu.usbcali.actividades.modelo.dto.ActividadDTO;

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
public interface IActividadLogic {
    public List<Actividad> getActividad() throws Exception;

    /**
         * Save an new Actividad entity
         */
    public void saveActividad(Actividad entity) throws Exception;

    /**
         * Delete an existing Actividad entity
         *
         */
    public void deleteActividad(Actividad entity) throws Exception;

    /**
        * Update an existing Actividad entity
        *
        */
    public void updateActividad(Actividad entity) throws Exception;

    /**
         * Load an existing Actividad entity
         *
         */
    public Actividad getActividad(Integer actiId) throws Exception;

    public List<Actividad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Actividad> findPageActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberActividad() throws Exception;

    public List<ActividadDTO> getDataActividad() throws Exception;
}
