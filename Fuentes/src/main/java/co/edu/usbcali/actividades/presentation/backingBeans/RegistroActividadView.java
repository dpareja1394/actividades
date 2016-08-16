package co.edu.usbcali.actividades.presentation.backingBeans;

import co.edu.usbcali.actividades.exceptions.*;
import co.edu.usbcali.actividades.modelo.*;
import co.edu.usbcali.actividades.modelo.dto.RegistroActividadDTO;
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
public class RegistroActividadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RegistroActividadView.class);
    private InputText txtTiempoMin;
    private InputText txtActiId_Actividad;
    private InputText txtUsuaId_Usuario;
    private InputText txtRegiId;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaRegistro;
    private Calendar txtTiempoHoras;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RegistroActividadDTO> data;
    private RegistroActividadDTO selectedRegistroActividad;
    private RegistroActividad entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RegistroActividadView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RegistroActividadDTO registroActividadDTO = (RegistroActividadDTO) e.getObject();

            if (txtTiempoMin == null) {
                txtTiempoMin = new InputText();
            }

            txtTiempoMin.setValue(registroActividadDTO.getTiempoMin());

            if (txtActiId_Actividad == null) {
                txtActiId_Actividad = new InputText();
            }

            txtActiId_Actividad.setValue(registroActividadDTO.getActiId_Actividad());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(registroActividadDTO.getUsuaId_Usuario());

            if (txtRegiId == null) {
                txtRegiId = new InputText();
            }

            txtRegiId.setValue(registroActividadDTO.getRegiId());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(registroActividadDTO.getFechaCreacion());

            if (txtFechaRegistro == null) {
                txtFechaRegistro = new Calendar();
            }

            txtFechaRegistro.setValue(registroActividadDTO.getFechaRegistro());

            if (txtTiempoHoras == null) {
                txtTiempoHoras = new Calendar();
            }

            txtTiempoHoras.setValue(registroActividadDTO.getTiempoHoras());

            Integer regiId = FacesUtils.checkInteger(txtRegiId);
            entity = businessDelegatorView.getRegistroActividad(regiId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRegistroActividad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRegistroActividad = null;

        if (txtTiempoMin != null) {
            txtTiempoMin.setValue(null);
            txtTiempoMin.setDisabled(true);
        }

        if (txtActiId_Actividad != null) {
            txtActiId_Actividad.setValue(null);
            txtActiId_Actividad.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaRegistro != null) {
            txtFechaRegistro.setValue(null);
            txtFechaRegistro.setDisabled(true);
        }

        if (txtTiempoHoras != null) {
            txtTiempoHoras.setValue(null);
            txtTiempoHoras.setDisabled(true);
        }

        if (txtRegiId != null) {
            txtRegiId.setValue(null);
            txtRegiId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaRegistro() {
        Date inputDate = (Date) txtFechaRegistro.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtTiempoHoras() {
        Date inputDate = (Date) txtTiempoHoras.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer regiId = FacesUtils.checkInteger(txtRegiId);
            entity = (regiId != null)
                ? businessDelegatorView.getRegistroActividad(regiId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtTiempoMin.setDisabled(false);
            txtActiId_Actividad.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaRegistro.setDisabled(false);
            txtTiempoHoras.setDisabled(false);
            txtRegiId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaRegistro.setValue(entity.getFechaRegistro());
            txtFechaRegistro.setDisabled(false);
            txtTiempoHoras.setValue(entity.getTiempoHoras());
            txtTiempoHoras.setDisabled(false);
            txtTiempoMin.setValue(entity.getTiempoMin());
            txtTiempoMin.setDisabled(false);
            txtActiId_Actividad.setValue(entity.getActividad().getActiId());
            txtActiId_Actividad.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtRegiId.setValue(entity.getRegiId());
            txtRegiId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRegistroActividad = (RegistroActividadDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedRegistroActividad"));
        txtFechaCreacion.setValue(selectedRegistroActividad.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaRegistro.setValue(selectedRegistroActividad.getFechaRegistro());
        txtFechaRegistro.setDisabled(false);
        txtTiempoHoras.setValue(selectedRegistroActividad.getTiempoHoras());
        txtTiempoHoras.setDisabled(false);
        txtTiempoMin.setValue(selectedRegistroActividad.getTiempoMin());
        txtTiempoMin.setDisabled(false);
        txtActiId_Actividad.setValue(selectedRegistroActividad.getActiId_Actividad());
        txtActiId_Actividad.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedRegistroActividad.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtRegiId.setValue(selectedRegistroActividad.getRegiId());
        txtRegiId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRegistroActividad == null) && (entity == null)) {
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
            entity = new RegistroActividad();

            Integer regiId = FacesUtils.checkInteger(txtRegiId);

            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaRegistro(FacesUtils.checkDate(txtFechaRegistro));
            entity.setRegiId(regiId);
            entity.setTiempoHoras(FacesUtils.checkDate(txtTiempoHoras));
            entity.setTiempoMin(FacesUtils.checkLong(txtTiempoMin));
            entity.setActividad((FacesUtils.checkInteger(txtActiId_Actividad) != null)
                ? businessDelegatorView.getActividad(FacesUtils.checkInteger(
                        txtActiId_Actividad)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveRegistroActividad(entity);
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
                Integer regiId = new Integer(selectedRegistroActividad.getRegiId());
                entity = businessDelegatorView.getRegistroActividad(regiId);
            }

            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaRegistro(FacesUtils.checkDate(txtFechaRegistro));
            entity.setTiempoHoras(FacesUtils.checkDate(txtTiempoHoras));
            entity.setTiempoMin(FacesUtils.checkLong(txtTiempoMin));
            entity.setActividad((FacesUtils.checkInteger(txtActiId_Actividad) != null)
                ? businessDelegatorView.getActividad(FacesUtils.checkInteger(
                        txtActiId_Actividad)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateRegistroActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRegistroActividad = (RegistroActividadDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedRegistroActividad"));

            Integer regiId = new Integer(selectedRegistroActividad.getRegiId());
            entity = businessDelegatorView.getRegistroActividad(regiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer regiId = FacesUtils.checkInteger(txtRegiId);
            entity = businessDelegatorView.getRegistroActividad(regiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRegistroActividad(entity);
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
            selectedRegistroActividad = (RegistroActividadDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedRegistroActividad"));

            Integer regiId = new Integer(selectedRegistroActividad.getRegiId());
            entity = businessDelegatorView.getRegistroActividad(regiId);
            businessDelegatorView.deleteRegistroActividad(entity);
            data.remove(selectedRegistroActividad);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date fechaCreacion, Date fechaRegistro,
        Integer regiId, Date tiempoHoras, Long tiempoMin,
        Integer actiId_Actividad, Integer usuaId_Usuario)
        throws Exception {
        try {
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaRegistro(FacesUtils.checkDate(fechaRegistro));
            entity.setTiempoHoras(FacesUtils.checkDate(tiempoHoras));
            entity.setTiempoMin(FacesUtils.checkLong(tiempoMin));
            businessDelegatorView.updateRegistroActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RegistroActividadView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtTiempoMin() {
        return txtTiempoMin;
    }

    public void setTxtTiempoMin(InputText txtTiempoMin) {
        this.txtTiempoMin = txtTiempoMin;
    }

    public InputText getTxtActiId_Actividad() {
        return txtActiId_Actividad;
    }

    public void setTxtActiId_Actividad(InputText txtActiId_Actividad) {
        this.txtActiId_Actividad = txtActiId_Actividad;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaRegistro() {
        return txtFechaRegistro;
    }

    public void setTxtFechaRegistro(Calendar txtFechaRegistro) {
        this.txtFechaRegistro = txtFechaRegistro;
    }

    public Calendar getTxtTiempoHoras() {
        return txtTiempoHoras;
    }

    public void setTxtTiempoHoras(Calendar txtTiempoHoras) {
        this.txtTiempoHoras = txtTiempoHoras;
    }

    public InputText getTxtRegiId() {
        return txtRegiId;
    }

    public void setTxtRegiId(InputText txtRegiId) {
        this.txtRegiId = txtRegiId;
    }

    public List<RegistroActividadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRegistroActividad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RegistroActividadDTO> registroActividadDTO) {
        this.data = registroActividadDTO;
    }

    public RegistroActividadDTO getSelectedRegistroActividad() {
        return selectedRegistroActividad;
    }

    public void setSelectedRegistroActividad(
        RegistroActividadDTO registroActividad) {
        this.selectedRegistroActividad = registroActividad;
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
