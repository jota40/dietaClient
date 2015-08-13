package es.jota.gwt.client.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RecetaGwtServiceAsync {
	void getRecetaDtoById( Long id, AsyncCallback<RecetaDto> asyncCallback );
	void modificarReceta( Long id, RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto, AsyncCallback<Void> asyncCallback );
	void insertarReceta( RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto, AsyncCallback<Long> asyncCallback );
	void findRecetasByFiltro( int start, int size, FiltroServer filtro, AsyncCallback<List<RecetaDto>> asyncCallback );
	void borrarRecetaById( Long id, AsyncCallback<Void> asyncCallback );
}