package es.jota.gwt.shared;

import java.io.Serializable;

public class SecurityGwtUtils implements Serializable {
	Long idUsuario;
	String nombreUsuario;

	public SecurityGwtUtils() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void update( SecurityGwtUtils source ) {
		this.idUsuario = source.getIdUsuario();
		this.nombreUsuario = source.getNombreUsuario();
	}

	public void clear() {
		this.idUsuario = null;
		this.nombreUsuario = null;
	}
}