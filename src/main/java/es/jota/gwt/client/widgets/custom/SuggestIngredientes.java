package es.jota.gwt.client.widgets.custom;

import java.util.ArrayList;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.IngredienteDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.service.IngredienteGwtService;
import es.jota.gwt.client.widgets.presenter.SelectValuePresenter;
import es.jota.gwt.client.widgets.presenter.interfaces.IAcceptableValuesProvider;
import es.jota.gwt.client.widgets.presenter.interfaces.ISelectValue;

public class SuggestIngredientes extends SelectValuePresenter<IngredienteDto> {

	public SuggestIngredientes( ISelectValue.IDisplay display ) {
		super( display );
		setItemTextProvider( new IItemProvider<IngredienteDto>() {
			@Override
			public String get(IngredienteDto item) {
				return item.getNombre();
			}
		});

		setAcceptableValuesProvider( new IAcceptableValuesProvider() {
			@Override
			public void load( int start, int size, String query, final long timestamp ) {
				FiltroServer filtro = new FiltroServer();
				filtro.add( new FiltroWhere( "and nombre like :nombre", "nombre", "%" + query + "%") );
				IngredienteGwtService.Util.getInstance().findIngredientesByFiltro( start, size, filtro, new AsyncCallback<List<IngredienteDto>>() {
					@Override
					public void onFailure( Throwable caught ) {
						setAcceptableValues( new ArrayList<IngredienteDto>(), timestamp );
					}
					@Override
					public void onSuccess( List<IngredienteDto> result ) {
						setAcceptableValues( result, timestamp );
					}
				});
			}
		});
	}
	
	public Long getId() {
		return getValue() == null ? null : getValue().getId();
	}
}