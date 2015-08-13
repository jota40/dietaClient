package es.jota.gwt.client.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDto;
import jota.server.exceptions.ServiceException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../ingredienteGwtService.gwt")
public interface IngredienteGwtService extends RemoteService {

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static IngredienteGwtServiceAsync instance;

		public static IngredienteGwtServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(IngredienteGwtService.class);
			}
			return instance;
		}
	}

	IngredienteDto getIngredienteDtoById( Long id ) throws ServiceException;
	void modificarIngrediente( Long id, IngredienteDto ingredienteDto, Long idUnidadDeMedida, Long idGrupoAlimento ) throws ServiceException;
	Long insertarIngrediente( IngredienteDto ingredienteDto, Long idUnidadDeMedida, Long idGrupoAlimento ) throws ServiceException;
	List<IngredienteDto> findIngredientesByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
	void borrarIngredienteById( Long id ) throws ServiceException;
}