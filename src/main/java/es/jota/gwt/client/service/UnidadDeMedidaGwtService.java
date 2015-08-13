package es.jota.gwt.client.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.UnidadDeMedidaDto;
import jota.server.exceptions.ServiceException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../unidadDeMedidaGwtService.gwt")
public interface UnidadDeMedidaGwtService extends RemoteService {

	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static UnidadDeMedidaGwtServiceAsync instance;

		public static UnidadDeMedidaGwtServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(UnidadDeMedidaGwtService.class);
			}
			return instance;
		}
	}
	void dummy( FiltroWhere a );

	UnidadDeMedidaDto getUnidadDeMedidaDtoById( Long id ) throws ServiceException;
	void modificarUnidadDeMedida( Long id, UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException;
	Long insertarUnidadDeMedida( UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException;
	List<UnidadDeMedidaDto> findUnidadesDeMedidaByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
	void borrarUnidadDeMedidaById( Long id ) throws ServiceException;
}