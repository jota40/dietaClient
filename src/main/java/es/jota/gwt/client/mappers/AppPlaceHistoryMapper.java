package es.jota.gwt.client.mappers;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceEdit;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceList;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceNew;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceView;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceEdit;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceList;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceNew;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceView;
import es.jota.gwt.client.place.inicio.InicioPlace;
import es.jota.gwt.client.place.presupuesto.PresupuestoPlaceNew;
import es.jota.gwt.client.place.receta.RecetaPlaceEdit;
import es.jota.gwt.client.place.receta.RecetaPlaceList;
import es.jota.gwt.client.place.receta.RecetaPlaceNew;
import es.jota.gwt.client.place.receta.RecetaPlaceView;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceEdit;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceList;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceNew;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceView;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( { 
			InicioPlace.Tokenizer.class, 
			IngredientePlaceEdit.Tokenizer.class, IngredientePlaceList.Tokenizer.class, IngredientePlaceNew.Tokenizer.class, IngredientePlaceView.Tokenizer.class,
			UnidadDeMedidaPlaceEdit.Tokenizer.class, UnidadDeMedidaPlaceList.Tokenizer.class, UnidadDeMedidaPlaceNew.Tokenizer.class, UnidadDeMedidaPlaceView.Tokenizer.class,
			RecetaPlaceEdit.Tokenizer.class, RecetaPlaceList.Tokenizer.class, RecetaPlaceNew.Tokenizer.class, RecetaPlaceView.Tokenizer.class,
			GrupoAlimentoPlaceEdit.Tokenizer.class, GrupoAlimentoPlaceList.Tokenizer.class, GrupoAlimentoPlaceNew.Tokenizer.class, GrupoAlimentoPlaceView.Tokenizer.class,
			PresupuestoPlaceNew.Tokenizer.class
			} )
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
