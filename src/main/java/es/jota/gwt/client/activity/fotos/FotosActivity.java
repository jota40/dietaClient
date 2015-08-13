package es.jota.gwt.client.activity.fotos;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.place.fotos.FotoPlace;

public class FotosActivity extends MyAbstractActivity<FotoPlace> implements FotosView.Delegate {

	private FotosView display;

	public FotosActivity() {
		display = miApp.INJECTOR.getFotosView();
		display.setDelegate(this);
	}

	@Override
	protected void start() {
		display.reset();
		fillForm();
		containerWidget.setWidget(display.asWidget());
	}
	
	private void fillForm() {
	}
}