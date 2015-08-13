package es.jota.gwt.client.widgets.custom;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.GrupoAlimentoDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.service.GrupoAlimentoGwtService;
import es.jota.gwt.client.widgets.presenter.SelectValuePresenter;
import es.jota.gwt.client.widgets.presenter.interfaces.IAcceptableValuesProvider;
import es.jota.gwt.client.widgets.presenter.interfaces.ISelectValue;

public class SuggestGruposAlimento extends SelectValuePresenter<GrupoAlimentoDto> {

	public SuggestGruposAlimento( ISelectValue.IDisplay display ) {
		super( display );
		setItemTextProvider( new IItemProvider<GrupoAlimentoDto>() {
			@Override
			public String get(GrupoAlimentoDto item) {
				return item.getNombre();
			}
		});
		FiltroServer filtro = new FiltroServer();
		GrupoAlimentoGwtService.Util.getInstance().findGruposAlimentoByFiltro( 0, 0, filtro, new AsyncCallback<List<GrupoAlimentoDto>>() {
			@Override
			public void onFailure( Throwable caught ) {
			}
			@Override
			public void onSuccess( final List<GrupoAlimentoDto> result ) {
				setAcceptableValuesProvider( new IAcceptableValuesProvider() {
					@Override
					public void load( int start, int size, String query, final long timestamp ) {
						List<GrupoAlimentoDto> list = match( result, query );
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