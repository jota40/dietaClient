package es.jota.gwt.server.service;
import jota.server.utils.SecurityUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.gwt.client.my.MyRemoteServiceServlet;
import es.jota.gwt.client.service.LoginGwtService;
import es.jota.gwt.shared.SecurityGwtUtils;

@Controller
@RequestMapping("/loginGwtService.gwt")
public class LoginGwtServiceImpl extends MyRemoteServiceServlet implements LoginGwtService {

	private static final long serialVersionUID = 2828541109842868140L;

	@Override
	public SecurityGwtUtils loadSecurityGwtUtils() {
		SecurityGwtUtils securityGwtUtils = new SecurityGwtUtils();
		securityGwtUtils.setIdUsuario( SecurityUtils.getIdUsuario() );
		securityGwtUtils.setNombreUsuario( SecurityUtils.getNombreUsuario() );
		return securityGwtUtils;
	}
}