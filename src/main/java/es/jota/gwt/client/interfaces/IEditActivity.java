package es.jota.gwt.client.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

public interface IEditActivity extends IsWidget {
	public interface Listener {
		void modificarYListar();
		void modificarYNuevo();
		void modificar();
	}

	void setListarUrl( String url );
	void setNuevoUrl( String url );
	void setVerUrl( String url);
}