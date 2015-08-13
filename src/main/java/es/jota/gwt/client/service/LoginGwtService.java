package es.jota.gwt.client.service;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.jota.gwt.shared.SecurityGwtUtils;

@RemoteServiceRelativePath("../loginGwtService.gwt")
public interface LoginGwtService extends RemoteService {

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static LoginGwtServiceAsync instance;

		public static LoginGwtServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(LoginGwtService.class);
			}
			return instance;
		}
	}

	SecurityGwtUtils loadSecurityGwtUtils();
}