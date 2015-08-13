package es.jota.gwt.client.place.receta;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;

public class RecetaPlace extends Place {
	protected RecetaPlace() {
	}

	public Activity getActivity( Place place ) {
		if ( place instanceof RecetaPlaceEdit ) 
			return miApp.INJECTOR.getRecetaActivityEdit().setPlace( (RecetaPlaceEdit)place );
		if ( place instanceof RecetaPlaceList ) 
			return miApp.INJECTOR.getRecetaActivityList().setPlace( (RecetaPlaceList)place );
		if ( place instanceof RecetaPlaceNew ) 
			return miApp.INJECTOR.getRecetaActivityNew().setPlace( (RecetaPlaceNew)place );
		if ( place instanceof RecetaPlaceView ) 
			return miApp.INJECTOR.getRecetaActivityView().setPlace( (RecetaPlaceView)place );
		return null;
	}
}