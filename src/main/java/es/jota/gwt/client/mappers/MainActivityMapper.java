package es.jota.gwt.client.mappers;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlace;
import es.jota.gwt.client.place.ingrediente.IngredientePlace;
import es.jota.gwt.client.place.inicio.InicioPlace;
import es.jota.gwt.client.place.presupuesto.PresupuestoPlace;
import es.jota.gwt.client.place.receta.RecetaPlace;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlace;

public class MainActivityMapper implements ActivityMapper {

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public MainActivityMapper() {
		super();
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity( Place place ) {
		if (place instanceof InicioPlace) 
			return miApp.INJECTOR.getInicioActivity().setPlace( (InicioPlace)place );

		if ( place instanceof IngredientePlace ) 
			return ( (IngredientePlace)place ).getActivity( place );

		if ( place instanceof UnidadDeMedidaPlace ) 
			return ( (UnidadDeMedidaPlace)place ).getActivity( place );

		if ( place instanceof RecetaPlace ) 
			return ( (RecetaPlace)place ).getActivity( place );

		if ( place instanceof GrupoAlimentoPlace ) 
			return ( (GrupoAlimentoPlace)place ).getActivity( place );

		if ( place instanceof PresupuestoPlace ) 
			return ( (PresupuestoPlace)place ).getActivity( place );

		return null;
	}
}