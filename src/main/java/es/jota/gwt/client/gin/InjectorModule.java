package es.jota.gwt.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import es.jota.gwt.client.activity.fotos.FotosActivity;
import es.jota.gwt.client.activity.fotos.FotosView;
import es.jota.gwt.client.activity.grupoAlimento.GrupoAlimentoActivityEdit;
import es.jota.gwt.client.activity.grupoAlimento.GrupoAlimentoActivityList;
import es.jota.gwt.client.activity.grupoAlimento.GrupoAlimentoActivityNew;
import es.jota.gwt.client.activity.grupoAlimento.GrupoAlimentoActivityView;
import es.jota.gwt.client.activity.ingrediente.IngredienteActivityEdit;
import es.jota.gwt.client.activity.ingrediente.IngredienteActivityList;
import es.jota.gwt.client.activity.ingrediente.IngredienteActivityNew;
import es.jota.gwt.client.activity.ingrediente.IngredienteActivityView;
import es.jota.gwt.client.activity.inicio.InicioActivity;
import es.jota.gwt.client.activity.logs.LogsActivity;
import es.jota.gwt.client.activity.menu.TopActivity;
import es.jota.gwt.client.activity.presupuesto.PresupuestoActivityNew;
import es.jota.gwt.client.activity.receta.RecetaActivityEdit;
import es.jota.gwt.client.activity.receta.RecetaActivityList;
import es.jota.gwt.client.activity.receta.RecetaActivityNew;
import es.jota.gwt.client.activity.receta.RecetaActivityView;
import es.jota.gwt.client.activity.unidadDeMedida.UnidadDeMedidaActivityEdit;
import es.jota.gwt.client.activity.unidadDeMedida.UnidadDeMedidaActivityList;
import es.jota.gwt.client.activity.unidadDeMedida.UnidadDeMedidaActivityNew;
import es.jota.gwt.client.activity.unidadDeMedida.UnidadDeMedidaActivityView;
import es.jota.gwt.client.display.fotos.FotosViewImpl;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayEdit;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayEditImpl;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayList;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayListImpl;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayNew;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayNewImpl;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayView;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayViewImpl;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayEdit;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayEditImpl;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayList;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayListImpl;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayNew;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayNewImpl;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayView;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayViewImpl;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayEdit;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayEditImpl;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayView;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayViewImpl;
import es.jota.gwt.client.display.inicio.InicioView;
import es.jota.gwt.client.display.inicio.InicioViewImpl;
import es.jota.gwt.client.display.logs.LogsView;
import es.jota.gwt.client.display.logs.LogsViewImpl;
import es.jota.gwt.client.display.menu.TopView;
import es.jota.gwt.client.display.menu.TopViewImpl;
import es.jota.gwt.client.display.presupuesto.PresupuestoDisplayNewView;
import es.jota.gwt.client.display.presupuesto.PresupuestoDisplayNewViewImpl;
import es.jota.gwt.client.display.receta.RecetaDisplayEdit;
import es.jota.gwt.client.display.receta.RecetaDisplayEditImpl;
import es.jota.gwt.client.display.receta.RecetaDisplayList;
import es.jota.gwt.client.display.receta.RecetaDisplayListImpl;
import es.jota.gwt.client.display.receta.RecetaDisplayNew;
import es.jota.gwt.client.display.receta.RecetaDisplayNewImpl;
import es.jota.gwt.client.display.receta.RecetaDisplayView;
import es.jota.gwt.client.display.receta.RecetaDisplayViewImpl;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayEdit;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayEditImpl;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayList;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayListImpl;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayNew;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayNewImpl;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayView;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayViewImpl;
import es.jota.gwt.shared.SecurityGwtUtils;

public class InjectorModule extends AbstractGinModule {
	
	@Override
	protected void configure() {
		bind(SecurityGwtUtils.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceProvider.class).in(Singleton.class);

		bind(LogsActivity.class).in(Singleton.class);
		bind(TopActivity.class).in(Singleton.class);
		bind(InicioActivity.class).in(Singleton.class);
		bind(FotosActivity.class);

		bind( IngredienteActivityEdit.class).in(Singleton.class);
		bind( IngredienteActivityList.class).in(Singleton.class);
		bind( IngredienteActivityNew.class).in(Singleton.class);
		bind( IngredienteActivityView.class).in(Singleton.class);

		bind( UnidadDeMedidaActivityEdit.class).in(Singleton.class);
		bind( UnidadDeMedidaActivityList.class).in(Singleton.class);
		bind( UnidadDeMedidaActivityNew.class).in(Singleton.class);
		bind( UnidadDeMedidaActivityView.class).in(Singleton.class);

		bind( RecetaActivityEdit.class).in(Singleton.class);
		bind( RecetaActivityList.class).in(Singleton.class);
		bind( RecetaActivityNew.class).in(Singleton.class);
		bind( RecetaActivityView.class).in(Singleton.class);

		bind( GrupoAlimentoActivityEdit.class).in(Singleton.class);
		bind( GrupoAlimentoActivityList.class).in(Singleton.class);
		bind( GrupoAlimentoActivityNew.class).in(Singleton.class);
		bind( GrupoAlimentoActivityView.class).in(Singleton.class);

		bind( PresupuestoActivityNew.class).in(Singleton.class);

		bind(LogsView.class).to(LogsViewImpl.class).in(Singleton.class);
		bind(TopView.class).to(TopViewImpl.class).in(Singleton.class);
		bind(InicioView.class).to(InicioViewImpl.class).in(Singleton.class);
		bind(FotosView.class).to(FotosViewImpl.class).in(Singleton.class);

		bind( IngredienteDisplayEdit.class ).to( IngredienteDisplayEditImpl.class ).in( Singleton.class );
		bind( IngredienteDisplayList.class ).to( IngredienteDisplayListImpl.class ).in( Singleton.class );
		bind( IngredienteDisplayNew.class ).to( IngredienteDisplayNewImpl.class ).in( Singleton.class );
		bind( IngredienteDisplayView.class ).to( IngredienteDisplayViewImpl.class ).in( Singleton.class );

		bind( UnidadDeMedidaDisplayEdit.class ).to( UnidadDeMedidaDisplayEditImpl.class ).in( Singleton.class );
		bind( UnidadDeMedidaDisplayList.class ).to( UnidadDeMedidaDisplayListImpl.class ).in( Singleton.class );
		bind( UnidadDeMedidaDisplayNew.class ).to( UnidadDeMedidaDisplayNewImpl.class ).in( Singleton.class );
		bind( UnidadDeMedidaDisplayView.class ).to( UnidadDeMedidaDisplayViewImpl.class ).in( Singleton.class );

		bind( RecetaDisplayEdit.class ).to( RecetaDisplayEditImpl.class ).in( Singleton.class );
		bind( RecetaDisplayList.class ).to( RecetaDisplayListImpl.class ).in( Singleton.class );
		bind( RecetaDisplayNew.class ).to( RecetaDisplayNewImpl.class ).in( Singleton.class );
		bind( RecetaDisplayView.class ).to( RecetaDisplayViewImpl.class ).in( Singleton.class );

		bind( IngredienteDeRecetaDisplayEdit.class ).to( IngredienteDeRecetaDisplayEditImpl.class );
		bind( IngredienteDeRecetaDisplayView.class ).to( IngredienteDeRecetaDisplayViewImpl.class );

		bind( GrupoAlimentoDisplayEdit.class ).to( GrupoAlimentoDisplayEditImpl.class ).in( Singleton.class );
		bind( GrupoAlimentoDisplayList.class ).to( GrupoAlimentoDisplayListImpl.class ).in( Singleton.class );
		bind( GrupoAlimentoDisplayNew.class ).to( GrupoAlimentoDisplayNewImpl.class ).in( Singleton.class );
		bind( GrupoAlimentoDisplayView.class ).to( GrupoAlimentoDisplayViewImpl.class ).in( Singleton.class );

		bind( PresupuestoDisplayNewView.class ).to( PresupuestoDisplayNewViewImpl.class ).in( Singleton.class );
	}

	static class PlaceProvider implements Provider<PlaceController> {
		@Inject
		EventBus eventBus;
		@Override
		public PlaceController get() {
			return new PlaceController(eventBus);
		}
	}
	/*
	@Provides
	@Singleton
	Validator createValidator() {
	    return Validation.buildDefaultValidatorFactory().getValidator();
	}*/
}