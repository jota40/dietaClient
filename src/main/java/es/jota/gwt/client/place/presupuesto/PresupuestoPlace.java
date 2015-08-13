package es.jota.gwt.client.place.presupuesto;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;

public class PresupuestoPlace extends Place {
	protected PresupuestoPlace() {
	}

	public Activity getActivity( Place place ) {
		if ( place instanceof PresupuestoPlaceNew ) 
			return miApp.INJECTOR.getPresupuestoActivityNew().setPlace( (PresupuestoPlaceNew)place );
		return null;
	}
}