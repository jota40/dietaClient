package es.jota.gwt.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

import es.jota.gwt.shared.SecurityGwtUtils;

@GinModules(InjectorModule.class)
public interface Injector extends Ginjector {
	public final static Injector INSTANCE = GWT.create(Injector.class);

	public SecurityGwtUtils getSecurityGwtUtils();
    public EventBus getEventBus();
    public PlaceController getPlaceController();
}