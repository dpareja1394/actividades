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
import co.edu.usbcali.actividades.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IActividadLogic actividadLogic;
    @Autowired
    private IRegistroActividadLogic registroActividadLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;

    public List<Actividad> getActividad() throws Exception {
        return actividadLogic.getActividad();
    }

    public void saveActividad(Actividad entity) throws Exception {
        actividadLogic.saveActividad(entity);
    }

    public void deleteActividad(Actividad entity) throws Exception {
        actividadLogic.deleteActividad(entity);
    }

    public void updateActividad(Actividad entity) throws Exception {
        actividadLogic.updateActividad(entity);
    }

    public Actividad getActividad(Integer actiId) throws Exception {
        Actividad actividad = null;

        try {
            actividad = actividadLogic.getActividad(actiId);
        } catch (Exception e) {
            throw e;
        }

        return actividad;
    }

    public List<Actividad> findByCriteriaInActividad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return actividadLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Actividad> findPageActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return actividadLogic.findPageActividad(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberActividad() throws Exception {
        return actividadLogic.findTotalNumberActividad();
    }

    public List<ActividadDTO> getDataActividad() throws Exception {
        return actividadLogic.getDataActividad();
    }

    public List<RegistroActividad> getRegistroActividad()
        throws Exception {
        return registroActividadLogic.getRegistroActividad();
    }

    public void saveRegistroActividad(RegistroActividad entity)
        throws Exception {
        registroActividadLogic.saveRegistroActividad(entity);
    }

    public void deleteRegistroActividad(RegistroActividad entity)
        throws Exception {
        registroActividadLogic.deleteRegistroActividad(entity);
    }

    public void updateRegistroActividad(RegistroActividad entity)
        throws Exception {
        registroActividadLogic.updateRegistroActividad(entity);
    }

    public RegistroActividad getRegistroActividad(Integer regiId)
        throws Exception {
        RegistroActividad registroActividad = null;

        try {
            registroActividad = registroActividadLogic.getRegistroActividad(regiId);
        } catch (Exception e) {
            throw e;
        }

        return registroActividad;
    }

    public List<RegistroActividad> findByCriteriaInRegistroActividad(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return registroActividadLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<RegistroActividad> findPageRegistroActividad(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return registroActividadLogic.findPageRegistroActividad(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberRegistroActividad() throws Exception {
        return registroActividadLogic.findTotalNumberRegistroActividad();
    }

    public List<RegistroActividadDTO> getDataRegistroActividad()
        throws Exception {
        return registroActividadLogic.getDataRegistroActividad();
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Integer usuaId) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(usuaId);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }
}
