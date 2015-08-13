package es.jota.gwt.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

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
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayEdit;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayList;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayNew;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayView;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayEdit;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayList;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayNew;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayView;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayEdit;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayView;
import es.jota.gwt.client.display.inicio.InicioView;
import es.jota.gwt.client.display.logs.LogsView;
import es.jota.gwt.client.display.menu.TopView;
import es.jota.gwt.client.display.presupuesto.PresupuestoDisplayNewView;
import es.jota.gwt.client.display.receta.RecetaDisplayEdit;
import es.jota.gwt.client.display.receta.RecetaDisplayList;
import es.jota.gwt.client.display.receta.RecetaDisplayNew;
import es.jota.gwt.client.display.receta.RecetaDisplayView;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayEdit;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayList;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayNew;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayView;

@GinModules(InjectorModule.class)
public interface InjectorDisplay extends Ginjector {
	// ACTIVITY
	public FotosActivity getFotosActivity();
	public InicioActivity getInicioActivity();
	public LogsActivity getLogsActivity();
	public TopActivity getTopMenuActivity();

	public IngredienteActivityEdit getIngredienteActivityEdit();
	public IngredienteActivityList getIngredienteActivityList();
	public IngredienteActivityNew getIngredienteActivityNew();
	public IngredienteActivityView getIngredienteActivityView();

	public UnidadDeMedidaActivityEdit getUnidadDeMedidaActivityEdit();
	public UnidadDeMedidaActivityList getUnidadDeMedidaActivityList();
	public UnidadDeMedidaActivityNew getUnidadDeMedidaActivityNew();
	public UnidadDeMedidaActivityView getUnidadDeMedidaActivityView();

	public RecetaActivityEdit getRecetaActivityEdit();
	public RecetaActivityList getRecetaActivityList();
	public RecetaActivityNew getRecetaActivityNew();
	public RecetaActivityView getRecetaActivityView();

	public GrupoAlimentoActivityEdit getGrupoAlimentoActivityEdit();
	public GrupoAlimentoActivityList getGrupoAlimentoActivityList();
	public GrupoAlimentoActivityNew getGrupoAlimentoActivityNew();
	public GrupoAlimentoActivityView getGrupoAlimentoActivityView();

	public PresupuestoActivityNew getPresupuestoActivityNew();

	//VIEW
	public LogsView getLogsView();
	public TopView getTopPublicoView();
    public InicioView getInicioView();
	public FotosView getFotosView();

	public IngredienteDisplayEdit getIngredienteDisplayEdit();
	public IngredienteDisplayList getIngredienteDisplayList();
	public IngredienteDisplayNew getIngredienteDisplayNew();
	public IngredienteDisplayView getIngredienteDisplayView();

	public UnidadDeMedidaDisplayEdit getUnidadDeMedidaDisplayEdit();
	public UnidadDeMedidaDisplayList getUnidadDeMedidaDisplayList();
	public UnidadDeMedidaDisplayNew getUnidadDeMedidaDisplayNew();
	public UnidadDeMedidaDisplayView getUnidadDeMedidaDisplayView();

	public RecetaDisplayEdit getRecetaDisplayEdit();
	public RecetaDisplayList getRecetaDisplayList();
	public RecetaDisplayNew getRecetaDisplayNew();
	public RecetaDisplayView getRecetaDisplayView();

	public IngredienteDeRecetaDisplayEdit getIngredienteDeRecetaDisplayEdit();
	public IngredienteDeRecetaDisplayView getIngredienteDeRecetaDisplayView();

	public GrupoAlimentoDisplayEdit getGrupoAlimentoDisplayEdit();
	public GrupoAlimentoDisplayList getGrupoAlimentoDisplayList();
	public GrupoAlimentoDisplayNew getGrupoAlimentoDisplayNew();
	public GrupoAlimentoDisplayView getGrupoAlimentoDisplayView();

	public PresupuestoDisplayNewView getPresupuestoDisplayNewView();
}