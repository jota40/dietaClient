package es.jota.gwt.client.widgets.presenter.interfaces;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import es.jota.gwt.client.widgets.presenter.events.ValueRemoveHandler;

public interface HasValueRemoveHandlers<T> extends HasHandlers {
	HandlerRegistration addValueRemoveHandler( ValueRemoveHandler<T> handler );
}