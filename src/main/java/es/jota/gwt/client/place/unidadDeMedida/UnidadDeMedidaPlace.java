package es.jota.gwt.client.place.unidadDeMedida;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;

public class UnidadDeMedidaPlace extends Place {
	protected UnidadDeMedidaPlace() {
	}

	public Activity getActivity( Place place ) {
		if ( place instanceof UnidadDeMedidaPlaceEdit ) 
			return miApp.INJECTOR.getUnidadDeMedidaActivityEdit().setPlace( (UnidadDeMedidaPlaceEdit)place );
		if ( place instanceof UnidadDeMedidaPlaceList ) 
			return miApp.INJECTOR.getUnidadDeMedidaActivityList().setPlace( (UnidadDeMedidaPlaceList)place );
		if ( place instanceof UnidadDeMedidaPlaceNew ) 
			return miApp.INJECTOR.getUnidadDeMedidaActivityNew().setPlace( (UnidadDeMedidaPlaceNew)place );
		if ( place instanceof UnidadDeMedidaPlaceView ) 
			return miApp.INJECTOR.getUnidadDeMedidaActivityView().setPlace( (UnidadDeMedidaPlaceView)place );
		return null;
	}
}