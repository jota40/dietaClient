package es.jota.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.shared.SecurityGwtUtils;

public interface LoginGwtServiceAsync {
	void loadSecurityGwtUtils( AsyncCallback<SecurityGwtUtils> asyncCallback );
}