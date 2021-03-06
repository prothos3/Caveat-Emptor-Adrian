package beans.user.register;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import constants.Forms;
import user.activation.ActivationService;
import utils.FacesContextMessage;
import exceptions.RegistrationException;
import exceptions.UserException;

@ManagedBean(name = "activation")
@RequestScoped
public class ActivateBean {

	@EJB
	private ActivationService activationService;

	private String activationKey;

	public void activate() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			activationService.activate(activationKey);
		} catch (UserException | RegistrationException e) {
			FacesContextMessage.addMessage(facesContext, Forms.ACTIVATION_CONTAINER.getName(), e.getMessage());
		}
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

}
