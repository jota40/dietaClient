package es.jota.gwt.client.widgets.presenter.events;

import com.google.gwt.event.shared.EventHandler;

public interface ValueRemoveHandler<T> extends EventHandler {
  void onValueRemove(ValueRemoveEvent<T> event);
}
