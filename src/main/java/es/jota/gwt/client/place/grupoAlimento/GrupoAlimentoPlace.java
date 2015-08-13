package es.jota.gwt.client.place.grupoAlimento;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;

public class GrupoAlimentoPlace extends Place {
	protected GrupoAlimentoPlace() {
	}

	public Activity getActivity( Place place ) {
		if ( place instanceof GrupoAlimentoPlaceEdit ) 
			return miApp.INJECTOR.getGrupoAlimentoActivityEdit().setPlace( (GrupoAlimentoPlaceEdit)place );
		if ( place instanceof GrupoAlimentoPlaceList ) 
			return miApp.INJECTOR.getGrupoAlimentoActivityList().setPlace( (GrupoAlimentoPlaceList)place );
		if ( place instanceof GrupoAlimentoPlaceNew ) 
			return miApp.INJECTOR.getGrupoAlimentoActivityNew().setPlace( (GrupoAlimentoPlaceNew)place );
		if ( place instanceof GrupoAlimentoPlaceView ) 
			return miApp.INJECTOR.getGrupoAlimentoActivityView().setPlace( (GrupoAlimentoPlaceView)place );
		return null;
	}
}