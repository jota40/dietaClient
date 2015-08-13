package es.jota.gwt.server.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.UnidadDeMedidaDto;
import jota.server.exceptions.ServiceException;
import jota.server.service.UnidadDeMedidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.gwt.client.my.MyRemoteServiceServlet;
import es.jota.gwt.client.service.UnidadDeMedidaGwtService;

@Controller
@RequestMapping("/unidadDeMedidaGwtService.gwt")
public class UnidadDeMedidaGwtServiceImpl extends MyRemoteServiceServlet implements UnidadDeMedidaGwtService {

	private static final long serialVersionUID = -948716413193872587L;

	@Autowired
	UnidadDeMedidaService unidadDeMedidaService;

	@Override
	public void dummy( FiltroWhere a ) {
	}

	@Override
	public UnidadDeMedidaDto getUnidadDeMedidaDtoById( Long id ) throws ServiceException {
		UnidadDeMedidaDto dev = null;
		dev = unidadDeMedidaService.getById( id );
		return dev;
	}

	@Override
	public void modificarUnidadDeMedida( Long id, UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException {
		unidadDeMedidaService.modificar( id, unidadDeMedidaDto );
	}

	@Override
	public Long insertarUnidadDeMedida( UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException {
		Long dev = null;
		dev = unidadDeMedidaService.insertar( unidadDeMedidaDto );
		return dev;
	}

	@Override
	public List<UnidadDeMedidaDto> findUnidadesDeMedidaByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		List<UnidadDeMedidaDto> dev = null;
		dev = unidadDeMedidaService.findByFiltro( start, size, filtro );
		return dev;
	}

	@Override
	public void borrarUnidadDeMedidaById( Long id ) throws ServiceException {
		unidadDeMedidaService.borrarById( id );
	}
}