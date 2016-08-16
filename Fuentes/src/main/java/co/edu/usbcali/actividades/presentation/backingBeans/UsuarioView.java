package co.edu.usbcali.actividades.presentation.backingBeans;

import co.edu.usbcali.actividades.exceptions.*;
import co.edu.usbcali.actividades.modelo.*;
import co.edu.usbcali.actividades.modelo.dto.UsuarioDTO;
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
public class UsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
    private InputText txtEmail;
    private InputText txtNombreCompleto;
    private InputText txtNumDocumento;
    private InputText txtPassword;
    private InputText txtUsuaId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioDTO> data;
    private UsuarioDTO selectedUsuario;
    private Usuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuarioDTO usuarioDTO = (UsuarioDTO) e.getObject();

            if (txtEmail == null) {
                txtEmail = new InputText();
            }

            txtEmail.setValue(usuarioDTO.getEmail());

            if (txtNombreCompleto == null) {
                txtNombreCompleto = new InputText();
            }

            txtNombreCompleto.setValue(usuarioDTO.getNombreCompleto());

            if (txtNumDocumento == null) {
                txtNumDocumento = new InputText();
            }

            txtNumDocumento.setValue(usuarioDTO.getNumDocumento());

            if (txtPassword == null) {
                txtPassword = new InputText();
            }

            txtPassword.setValue(usuarioDTO.getPassword());

            if (txtUsuaId == null) {
                txtUsuaId = new InputText();
            }

            txtUsuaId.setValue(usuarioDTO.getUsuaId());

            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = businessDelegatorView.getUsuario(usuaId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuario = null;

        if (txtEmail != null) {
            txtEmail.setValue(null);
            txtEmail.setDisabled(true);
        }

        if (txtNombreCompleto != null) {
            txtNombreCompleto.setValue(null);
            txtNombreCompleto.setDisabled(true);
        }

        if (txtNumDocumento != null) {
            txtNumDocumento.setValue(null);
            txtNumDocumento.setDisabled(true);
        }

        if (txtPassword != null) {
            txtPassword.setValue(null);
            txtPassword.setDisabled(true);
        }

        if (txtUsuaId != null) {
            txtUsuaId.setValue(null);
            txtUsuaId.setDisabled(false);
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
            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = (usuaId != null)
                ? businessDelegatorView.getUsuario(usuaId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEmail.setDisabled(false);
            txtNombreCompleto.setDisabled(false);
            txtNumDocumento.setDisabled(false);
            txtPassword.setDisabled(false);
            txtUsuaId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEmail.setValue(entity.getEmail());
            txtEmail.setDisabled(false);
            txtNombreCompleto.setValue(entity.getNombreCompleto());
            txtNombreCompleto.setDisabled(false);
            txtNumDocumento.setValue(entity.getNumDocumento());
            txtNumDocumento.setDisabled(false);
            txtPassword.setValue(entity.getPassword());
            txtPassword.setDisabled(false);
            txtUsuaId.setValue(entity.getUsuaId());
            txtUsuaId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsuario"));
        txtEmail.setValue(selectedUsuario.getEmail());
        txtEmail.setDisabled(false);
        txtNombreCompleto.setValue(selectedUsuario.getNombreCompleto());
        txtNombreCompleto.setDisabled(false);
        txtNumDocumento.setValue(selectedUsuario.getNumDocumento());
        txtNumDocumento.setDisabled(false);
        txtPassword.setValue(selectedUsuario.getPassword());
        txtPassword.setDisabled(false);
        txtUsuaId.setValue(selectedUsuario.getUsuaId());
        txtUsuaId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuario == null) && (entity == null)) {
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
            entity = new Usuario();

            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setNombreCompleto(FacesUtils.checkString(txtNombreCompleto));
            entity.setNumDocumento(FacesUtils.checkString(txtNumDocumento));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setUsuaId(usuaId);
            businessDelegatorView.saveUsuario(entity);
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
                Integer usuaId = new Integer(selectedUsuario.getUsuaId());
                entity = businessDelegatorView.getUsuario(usuaId);
            }

            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setNombreCompleto(FacesUtils.checkString(txtNombreCompleto));
            entity.setNumDocumento(FacesUtils.checkString(txtNumDocumento));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Integer usuaId = new Integer(selectedUsuario.getUsuaId());
            entity = businessDelegatorView.getUsuario(usuaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = businessDelegatorView.getUsuario(usuaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuario(entity);
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
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Integer usuaId = new Integer(selectedUsuario.getUsuaId());
            entity = businessDelegatorView.getUsuario(usuaId);
            businessDelegatorView.deleteUsuario(entity);
            data.remove(selectedUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String email, String nombreCompleto,
        String numDocumento, String password, Integer usuaId)
        throws Exception {
        try {
            entity.setEmail(FacesUtils.checkString(email));
            entity.setNombreCompleto(FacesUtils.checkString(nombreCompleto));
            entity.setNumDocumento(FacesUtils.checkString(numDocumento));
            entity.setPassword(FacesUtils.checkString(password));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
    }

    public InputText getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public void setTxtNombreCompleto(InputText txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    public InputText getTxtNumDocumento() {
        return txtNumDocumento;
    }

    public void setTxtNumDocumento(InputText txtNumDocumento) {
        this.txtNumDocumento = txtNumDocumento;
    }

    public InputText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(InputText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public InputText getTxtUsuaId() {
        return txtUsuaId;
    }

    public void setTxtUsuaId(InputText txtUsuaId) {
        this.txtUsuaId = txtUsuaId;
    }

    public List<UsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioDTO> usuarioDTO) {
        this.data = usuarioDTO;
    }

    public UsuarioDTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioDTO usuario) {
        this.selectedUsuario = usuario;
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
