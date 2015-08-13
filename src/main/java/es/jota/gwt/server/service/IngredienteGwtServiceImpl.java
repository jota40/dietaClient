package es.jota.gwt.server.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDto;
import jota.server.exceptions.ServiceException;
import jota.server.service.IngredienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.gwt.client.my.MyRemoteServiceServlet;
import es.jota.gwt.client.service.IngredienteGwtService;

@Controller
@RequestMapping("/ingredienteGwtService.gwt")
public class IngredienteGwtServiceImpl extends MyRemoteServiceServlet implements IngredienteGwtService {

	 private final Logger LOG = LoggerFactory.getLogger(IngredienteGwtServiceImpl.class);
	 
	 private static final long serialVersionUID = -948716413193872587L;

	@Autowired
	IngredienteService ingredienteService;

	@Override
	public IngredienteDto getIngredienteDtoById( Long id ) throws ServiceException {
		IngredienteDto dev = null;
		dev = ingredienteService.getById( id );
		return dev;
	}

	@Override
	public void modificarIngrediente( Long idIngrediente, IngredienteDto ingredienteDto, Long idUnidadDeMedida, Long idGrupoAlimento  ) throws ServiceException {
		ingredienteService.modificar( idIngrediente, ingredienteDto, idUnidadDeMedida, idGrupoAlimento  );
	}

	@Override
	public Long insertarIngrediente( IngredienteDto ingredienteDto, Long idUnidadDeMedida, Long idGrupoAlimento ) throws ServiceException {
		Long dev = null;
		dev = ingredienteService.insertar( ingredienteDto, idUnidadDeMedida, idGrupoAlimento );
		return dev;
	}

	@Override
	public List<IngredienteDto> findIngredientesByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		List<IngredienteDto> dev = null;
		dev = ingredienteService.findByFiltro( start, size, filtro );
		return dev;
	}

	@Override
	public void borrarIngredienteById( Long id ) throws ServiceException {
		ingredienteService.borrarById( id );
	}
}