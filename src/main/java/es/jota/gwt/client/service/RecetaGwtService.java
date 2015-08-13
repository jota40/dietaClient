package es.jota.gwt.client.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;
import jota.server.exceptions.ServiceException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../recetaGwtService.gwt")
public interface RecetaGwtService extends RemoteService {

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static RecetaGwtServiceAsync instance;

		public static RecetaGwtServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(RecetaGwtService.class);
			}
			return instance;
		}
	}

	RecetaDto getRecetaDtoById( Long id ) throws ServiceException;
	void modificarReceta( Long id, RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException;
	Long insertarReceta( RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException;
	List<RecetaDto> findRecetasByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
	void borrarRecetaById( Long id ) throws ServiceException;
}