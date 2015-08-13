package es.jota.gwt.client.my;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import es.jota.gwt.client.gin.Injector;

public abstract class MyAbstractActivity<P> extends AbstractActivity {
	protected EventBus globalEventBus = Injector.INSTANCE.getEventBus();
	private PlaceController placeController = Injector.INSTANCE.getPlaceController();
	protected P place;
	protected AcceptsOneWidget containerWidget;

	protected void goTo( Place place ){
		placeController.goTo( place );
	}
	public MyAbstractActivity<P> setPlace(P place) {
		this.place = place;
		return this;
	}

	@Override
	public void start( AcceptsOneWidget containerWidget, EventBus eventBus ) {
		this.containerWidget = containerWidget;
		start();
	}

	protected abstract void start();
}
