package es.jota.gwt.client.widgets.presenter.events;

import com.google.gwt.event.shared.EventHandler;

public interface ValueAddHandler<T> extends EventHandler {
  void onValueAdd(ValueAddEvent<T> event);
}
