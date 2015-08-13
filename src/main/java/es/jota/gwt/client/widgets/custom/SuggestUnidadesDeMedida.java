package es.jota.gwt.client.widgets.custom;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.UnidadDeMedidaDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.service.UnidadDeMedidaGwtService;
import es.jota.gwt.client.widgets.presenter.SelectValuePresenter;
import es.jota.gwt.client.widgets.presenter.interfaces.IAcceptableValuesProvider;
import es.jota.gwt.client.widgets.presenter.interfaces.ISelectValue;

public class SuggestUnidadesDeMedida extends SelectValuePresenter<UnidadDeMedidaDto> {

	public SuggestUnidadesDeMedida( ISelectValue.IDisplay display ) {
		super( display );
		setItemTextProvider( new IItemProvider<UnidadDeMedidaDto>() {
			@Override
			public String get(UnidadDeMedidaDto item) {
				return item.getNombre();
			}
		});
		FiltroServer filtro = new FiltroServer();
		UnidadDeMedidaGwtService.Util.getInstance().findUnidadesDeMedidaByFiltro( 0, 0, filtro, new AsyncCallback<List<UnidadDeMedidaDto>>() {
			@Override
			public void onFailure( Throwable caught ) {
			}
			@Override
			public void onSuccess( final List<UnidadDeMedidaDto> result ) {
				setAcceptableValuesProvider( new IAcceptableValuesProvider() {
					@Override
					public void load( int start, int size, String query, final long timestamp ) {
						List<UnidadDeMedidaDto> list = match( result, query );
						int end = Math.min( list.size(), start + size );
						setAcceptableValues( list.subList( start, end ), timestamp );
					}
				});
			}
		});
	}
	
	public Long getId() {
		return getValue() == null ? null : getValue().getId();
	}
}