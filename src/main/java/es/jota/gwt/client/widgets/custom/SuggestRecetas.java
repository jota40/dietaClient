package es.jota.gwt.client.widgets.custom;

import java.util.ArrayList;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.service.RecetaGwtService;
import es.jota.gwt.client.widgets.presenter.SelectValuePresenter;
import es.jota.gwt.client.widgets.presenter.interfaces.IAcceptableValuesProvider;
import es.jota.gwt.client.widgets.presenter.interfaces.ISelectValue;

public class SuggestRecetas extends SelectValuePresenter<RecetaDto> {

	public SuggestRecetas( ISelectValue.IDisplay display ) {
		super( display, true );
		setItemTextProvider( new IItemProvider<RecetaDto>() {
			@Override
			public String get(RecetaDto item) {
				return item.getNombre();
			}
		});

		setAcceptableValuesProvider( new IAcceptableValuesProvider() {
			@Override
			public void load( int start, int size, String query, final long timestamp ) {
				FiltroServer filtro = new FiltroServer();
				filtro.add( new FiltroWhere( "and nombre like :nombre", "nombre", "%" + query + "%") );
				RecetaGwtService.Util.getInstance().findRecetasByFiltro( start, size, filtro, new AsyncCallback<List<RecetaDto>>() {
					@Override
					public void onFailure( Throwable caught ) {
						setAcceptableValues( new ArrayList<RecetaDto>(), timestamp );
					}
					@Override
					public void onSuccess( List<RecetaDto> result ) {
						setAcceptableValues( result, timestamp );
					}
				});
			}
		});
	}
}