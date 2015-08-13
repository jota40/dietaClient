package es.jota.gwt.client.activity.logs;

import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.logs.AlertView;
import es.jota.gwt.client.display.logs.AlertViewImpl;
import es.jota.gwt.client.display.logs.LogsView;
import es.jota.gwt.client.my.MyAbstractActivity;

public class LogsActivity extends MyAbstractActivity<Place> implements LogsView.Presenter {

	private LogsView display = miApp.INJECTOR.getLogsView();

	public LogsActivity() {
	}

	@Override
	protected void start() {
		containerWidget.setWidget(display.asWidget());
	}

	public AlertView crearAlert( String titulo, String texto ) {
		AlertView alert = new AlertViewImpl( titulo, texto );
		display.getPanel().add( alert );
		return alert;
	}
}