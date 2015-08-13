package es.jota.gwt.server.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.GrupoAlimentoDto;
import jota.server.exceptions.ServiceException;
import jota.server.service.GrupoAlimentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.gwt.client.my.MyRemoteServiceServlet;
import es.jota.gwt.client.service.GrupoAlimentoGwtService;

@Controller
@RequestMapping("/grupoAlimentoGwtService.gwt")
public class GrupoAlimentoGwtServiceImpl extends MyRemoteServiceServlet implements GrupoAlimentoGwtService {

	private static final long serialVersionUID = -948716413193872587L;

	@Autowired
	GrupoAlimentoService grupoAlimentoService;

	@Override
	public void dummy( FiltroWhere a ) {
	}

	@Override
	public GrupoAlimentoDto getGrupoAlimentoDtoById( Long id ) throws ServiceException {
		GrupoAlimentoDto dev = null;
		dev = grupoAlimentoService.getById( id );
		return dev;
	}

	@Override
	public void modificarGrupoAlimento( Long id, GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException {
		grupoAlimentoService.modificar( id, grupoAlimentoDto );
	}

	@Override
	public Long insertarGrupoAlimento( GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException {
		Long dev = null;
		dev = grupoAlimentoService.insertar( grupoAlimentoDto );
		return dev;
	}

	@Override
	public List<GrupoAlimentoDto> findGruposAlimentoByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		List<GrupoAlimentoDto> dev = null;
		dev = grupoAlimentoService.findByFiltro( start, size, filtro );
		return dev;
	}

	@Override
	public void borrarGrupoAlimentoById( Long id ) throws ServiceException {
		grupoAlimentoService.borrarById( id );
	}
}