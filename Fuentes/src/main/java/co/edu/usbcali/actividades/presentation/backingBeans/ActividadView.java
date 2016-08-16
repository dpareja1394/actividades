package co.edu.usbcali.actividades.presentation.backingBeans;

import co.edu.usbcali.actividades.exceptions.*;
import co.edu.usbcali.actividades.modelo.*;
import co.edu.usbcali.actividades.modelo.dto.ActividadDTO;
import co.edu.usbcali.actividades.presentation.businessDelegate.*;
import co.edu.usbcali.actividades.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ActividadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ActividadView.class);
    private InputText txtDescripcion;
    private InputText txtNombreActividad;
    private InputText txtActiId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ActividadDTO> data;
    private ActividadDTO selectedActividad;
    private Actividad entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ActividadView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ActividadDTO actividadDTO = (ActividadDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(actividadDTO.getDescripcion());

            if (txtNombreActividad == null) {
                txtNombreActividad = new InputText();
            }

            txtNombreActividad.setValue(actividadDTO.getNombreActividad());

            if (txtActiId == null) {
                txtActiId = new InputText();
            }

            txtActiId.setValue(actividadDTO.getActiId());

            Integer actiId = FacesUtils.checkInteger(txtActiId);
            entity = businessDelegatorView.getActividad(actiId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedActividad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedActividad = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtNombreActividad != null) {
            txtNombreActividad.setValue(null);
            txtNombreActividad.setDisabled(true);
        }

        if (txtActiId != null) {
            txtActiId.setValue(null);
            txtActiId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer actiId = FacesUtils.checkInteger(txtActiId);
            entity = (actiId != null)
                ? businessDelegatorView.getActividad(actiId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtNombreActividad.setDisabled(false);
            txtActiId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtNombreActividad.setValue(entity.getNombreActividad());
            txtNombreActividad.setDisabled(false);
            txtActiId.setValue(entity.getActiId());
            txtActiId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedActividad = (ActividadDTO) (evt.getComponent().getAttributes()
                                               .get("selectedActividad"));
        txtDescripcion.setValue(selectedActividad.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtNombreActividad.setValue(selectedActividad.getNombreActividad());
        txtNombreActividad.setDisabled(false);
        txtActiId.setValue(selectedActividad.getActiId());
        txtActiId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedActividad == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Actividad();

            Integer actiId = FacesUtils.checkInteger(txtActiId);

            entity.setActiId(actiId);
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setNombreActividad(FacesUtils.checkString(txtNombreActividad));
            businessDelegatorView.saveActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer actiId = new Integer(selectedActividad.getActiId());
                entity = businessDelegatorView.getActividad(actiId);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setNombreActividad(FacesUtils.checkString(txtNombreActividad));
            businessDelegatorView.updateActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedActividad = (ActividadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedActividad"));

            Integer actiId = new Integer(selectedActividad.getActiId());
            entity = businessDelegatorView.getActividad(actiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer actiId = FacesUtils.checkInteger(txtActiId);
            entity = businessDelegatorView.getActividad(actiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedActividad = (ActividadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedActividad"));

            Integer actiId = new Integer(selectedActividad.getActiId());
            entity = businessDelegatorView.getActividad(actiId);
            businessDelegatorView.deleteActividad(entity);
            data.remove(selectedActividad);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer actiId, String descripcion,
        String nombreActividad) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setNombreActividad(FacesUtils.checkString(nombreActividad));
            businessDelegatorView.updateActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ActividadView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtNombreActividad() {
        return txtNombreActividad;
    }

    public void setTxtNombreActividad(InputText txtNombreActividad) {
        this.txtNombreActividad = txtNombreActividad;
    }

    public InputText getTxtActiId() {
        return txtActiId;
    }

    public void setTxtActiId(InputText txtActiId) {
        this.txtActiId = txtActiId;
    }

    public List<ActividadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataActividad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ActividadDTO> actividadDTO) {
        this.data = actividadDTO;
    }

    public ActividadDTO getSelectedActividad() {
        return selectedActividad;
    }

    public void setSelectedActividad(ActividadDTO actividad) {
        this.selectedActividad = actividad;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
