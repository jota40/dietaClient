package es.jota.gwt.server.service;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;
import jota.server.exceptions.ServiceException;
import jota.server.service.RecetaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.gwt.client.my.MyRemoteServiceServlet;
import es.jota.gwt.client.service.RecetaGwtService;

@Controller
@RequestMapping("/recetaGwtService.gwt")
public class RecetaGwtServiceImpl extends MyRemoteServiceServlet implements RecetaGwtService {

	 private final Logger LOG = LoggerFactory.getLogger(RecetaGwtServiceImpl.class);
	 
	 private static final long serialVersionUID = -948716413193872587L;

	@Autowired
	RecetaService recetaService;

	@Override
	public RecetaDto getRecetaDtoById( Long id ) throws ServiceException {
		RecetaDto dev = null;
		dev = recetaService.getById( id );
		return dev;
	}

	@Override
	public void modificarReceta( Long idReceta, RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException {
		try {
			recetaService.modificar( idReceta, recetaDto, ingredientesDeRecetaDto );
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Long insertarReceta( RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException {
		Long dev = null;
		dev = recetaService.insertar( recetaDto, ingredientesDeRecetaDto );
		return dev;
	}

	@Override
	public List<RecetaDto> findRecetasByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		List<RecetaDto> dev = null;
		dev = recetaService.findByFiltro( start, size, filtro );
		return dev;
	}

	@Override
	public void borrarRecetaById( Long id ) throws ServiceException {
		recetaService.borrarById( id );
	}
}