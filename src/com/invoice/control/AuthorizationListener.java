package com.invoice.control;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.invoice.beans.LoginBean;

@SuppressWarnings("unused")
public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		LoginBean loginUser = (LoginBean)event.getFacesContext().getExternalContext().getSessionMap().get("loginUser");
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}

