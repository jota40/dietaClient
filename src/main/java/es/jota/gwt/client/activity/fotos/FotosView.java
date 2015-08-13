package es.jota.gwt.client.activity.fotos;

import com.google.gwt.user.client.ui.IsWidget;

public interface FotosView extends IsWidget  {
	void setDelegate(Delegate listener);
	public interface Delegate {
	}
	void reset();
}