package es.jota.gwt.client.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IngredienteGwtServiceAsync {
	void getIngredienteDtoById( Long id, AsyncCallback<IngredienteDto> asyncCallback );
	void modificarIngrediente( Long id, IngredienteDto ingredienteDto, Long idUnidadDeMedida, Long idGrupoAlimento, AsyncCallback<Void> asyncCallback );
	void insertarIngrediente( IngredienteDto ingredienteDto, Long idUnidadDeMedida, Long idGrupoAlimento , AsyncCallback<Long> asyncCallback );
	void findIngredientesByFiltro( int start, int size, FiltroServer filtro, AsyncCallback<List<IngredienteDto>> asyncCallback );
	void borrarIngredienteById( Long id, AsyncCallback<Void> asyncCallback );
}