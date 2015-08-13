package es.jota.gwt.client.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

public interface INewActivity extends IsWidget {
	public interface Listener {
		void guardarYListar();
		void guardarYNuevo();
		void guardar();
		void limpiar();
	}
}