package es.jota.gwt.client.place.ingrediente;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.gin.Injector;

public class IngredientePlace extends Place {
	protected IngredientePlace() {
	}

	public Activity getActivity( Place place ) {
		if ( Injector.INSTANCE.getSecurityGwtUtils().getIdUsuario() != null ) {
			if ( place instanceof IngredientePlaceEdit ) 
				return miApp.INJECTOR.getIngredienteActivityEdit().setPlace( (IngredientePlaceEdit)place );
			if ( place instanceof IngredientePlaceNew ) 
				return miApp.INJECTOR.getIngredienteActivityNew().setPlace( (IngredientePlaceNew)place );
		}
		if ( place instanceof IngredientePlaceList ) 
			return miApp.INJECTOR.getIngredienteActivityList().setPlace( (IngredientePlaceList)place );
		if ( place instanceof IngredientePlaceView ) 
			return miApp.INJECTOR.getIngredienteActivityView().setPlace( (IngredientePlaceView)place );
		return null;
	}
}