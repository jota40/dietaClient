package es.jota.gwt.client.widgets.presenter.interfaces;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import es.jota.gwt.client.widgets.presenter.events.ValueAddHandler;

public interface HasValueAddHandlers<T> extends HasHandlers {
	HandlerRegistration addValueAddHandler( ValueAddHandler<T> handler );
}