package es.jota.gwt.client.activity.inicio;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.inicio.InicioView;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.place.inicio.InicioPlace;

public class InicioActivity extends MyAbstractActivity<InicioPlace> implements InicioView.Listener {
	
	private InicioView display;

	public InicioActivity() {
		display = miApp.INJECTOR.getInicioView();
		display.setListener( this );
	}

	@Override
	protected void start() {
		containerWidget.setWidget(display.asWidget());
	}
}