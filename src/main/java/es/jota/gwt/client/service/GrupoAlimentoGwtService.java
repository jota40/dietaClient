package es.jota.gwt.client.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.GrupoAlimentoDto;
import jota.server.exceptions.ServiceException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../grupoAlimentoGwtService.gwt")
public interface GrupoAlimentoGwtService extends RemoteService {

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static GrupoAlimentoGwtServiceAsync instance;

		public static GrupoAlimentoGwtServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(GrupoAlimentoGwtService.class);
			}
			return instance;
		}
	}
	void dummy( FiltroWhere a );

	GrupoAlimentoDto getGrupoAlimentoDtoById( Long id ) throws ServiceException;
	void modificarGrupoAlimento( Long id, GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException;
	Long insertarGrupoAlimento( GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException;
	List<GrupoAlimentoDto> findGruposAlimentoByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
	void borrarGrupoAlimentoById( Long id ) throws ServiceException;
}