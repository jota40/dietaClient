package es.jota.gwt.client.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.GrupoAlimentoDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GrupoAlimentoGwtServiceAsync {
	void dummy( FiltroWhere a, AsyncCallback<Void> asyncCallback );
	
	void getGrupoAlimentoDtoById( Long id, AsyncCallback<GrupoAlimentoDto> asyncCallback );
	void modificarGrupoAlimento( Long id, GrupoAlimentoDto grupoAlimentoDto, AsyncCallback<Void> asyncCallback );
	void insertarGrupoAlimento( GrupoAlimentoDto grupoAlimentoDto, AsyncCallback<Long> asyncCallback );
	void findGruposAlimentoByFiltro( int start, int size, FiltroServer filtros, AsyncCallback<List<GrupoAlimentoDto>> asyncCallback );
	void borrarGrupoAlimentoById( Long id, AsyncCallback<Void> asyncCallback );
}