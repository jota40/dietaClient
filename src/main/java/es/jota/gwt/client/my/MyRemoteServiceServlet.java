package es.jota.gwt.client.my;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public abstract class MyRemoteServiceServlet extends RemoteServiceServlet implements ServletContextAware, Controller, RemoteService {
	private static final long serialVersionUID = 7420008554025683977L;
	
	static final Logger LOG = LoggerFactory.getLogger(MyRemoteServiceServlet.class);
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			doPost(request, response);
		} catch (Exception ex) {
			LOG.error("handleRequest error " + ex);
		}
		return null;
	}
}
