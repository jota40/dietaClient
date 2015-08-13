package es.jota.gwt.client.display.logs;

import com.google.gwt.user.client.ui.IsWidget;

public interface AlertView extends IsWidget {
	void turnToSuccess(String texto);
	void turnToFail(String texto);
	void close();
}
