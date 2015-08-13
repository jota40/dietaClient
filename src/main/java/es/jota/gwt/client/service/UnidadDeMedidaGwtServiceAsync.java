package es.jota.gwt.client.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.UnidadDeMedidaDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UnidadDeMedidaGwtServiceAsync {
	void dummy( FiltroWhere a, AsyncCallback<Void> asyncCallback );
	
	void getUnidadDeMedidaDtoById( Long id, AsyncCallback<UnidadDeMedidaDto> asyncCallback );
	void modificarUnidadDeMedida( Long id, UnidadDeMedidaDto unidadDeMedidaDto, AsyncCallback<Void> asyncCallback );
	void insertarUnidadDeMedida( UnidadDeMedidaDto unidadDeMedidaDto, AsyncCallback<Long> asyncCallback );
	void findUnidadesDeMedidaByFiltro( int start, int size, FiltroServer filtros, AsyncCallback<List<UnidadDeMedidaDto>> asyncCallback );
	void borrarUnidadDeMedidaById( Long id, AsyncCallback<Void> asyncCallback );
}