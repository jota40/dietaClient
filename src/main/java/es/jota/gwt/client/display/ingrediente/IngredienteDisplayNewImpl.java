package es.jota.gwt.client.display.ingrediente;

import java.math.BigDecimal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.validator.Bootstrap3Action;
import es.jota.gwt.client.validator.Validator;
import es.jota.gwt.client.validator.isDecimal;
import es.jota.gwt.client.validator.notEmpty;
import es.jota.gwt.client.widgets.custom.SuggestGruposAlimento;
import es.jota.gwt.client.widgets.custom.SuggestUnidadesDeMedida;
import es.jota.gwt.client.widgets.display.MenuItem;
import es.jota.gwt.client.widgets.display.Suggest;

public class IngredienteDisplayNewImpl extends Composite implements IngredienteDisplayNew {
	
	private static IngredienteDisplayNewUiBinder uiBinder = GWT.create(IngredienteDisplayNewUiBinder.class);
	interface IngredienteDisplayNewUiBinder extends UiBinder<Widget, IngredienteDisplayNewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;

	@UiField Element nombreFormGroup;
	@UiField InputElement nombre;
	@UiField Element nombreError;

	@UiField TextAreaElement descripcion;
	
	@UiField Element pesoPorRacionFormGroup;
	@UiField InputElement pesoPorRacion;
	@UiField Element pesoPorRacionError;

	@UiField InputElement coste;
	@UiField Suggest unidadDeMedida;

	@UiField Element grupoAlimentoFormGroup;
	@UiField Suggest grupoAlimento;
	@UiField Element grupoAlimentoError;

	private IngredienteDisplayNew.Listener listener;
	private SuggestUnidadesDeMedida suggestUnidadesDeMedida;
	private SuggestGruposAlimento suggestGruposAlimento;

	@UiHandler("guardarYListar")
	public void guardarYListarOnClick(ClickEvent event) {
		listener.guardarYListar();
	}

	@UiHandler("guardarYNuevo")
	public void guardarYNuevoOnClick(ClickEvent event) {
		listener.guardarYNuevo();
	}

	@UiHandler("guardar")
	public void guardarOnClick(ClickEvent event) {
		listener.guardar();
	}

	@UiHandler("limpiar")
	public void limpiarOnClick(ClickEvent event) {
		listener.limpiar();
	}

	@Override
	public void setListener( IngredienteDisplayNew.Listener listener ) {
		this.listener = listener;
	}

	public IngredienteDisplayNewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		suggestUnidadesDeMedida = new SuggestUnidadesDeMedida( unidadDeMedida );
		suggestGruposAlimento = new SuggestGruposAlimento( grupoAlimento );
	}

	// Menu
	@Override
	public HTMLPanel getMenu() {
		return menu;
	}

	@Override
	public void setListarUrl( String url ) {
		listar.setHref( url );
	}

	// fillForm
	@Override
	public void setClean() {
		this.nombre.setValue( "" );
		this.descripcion.setValue( "" );
		this.pesoPorRacion.setValue( "" );
		this.coste.setValue( "" );
		this.unidadDeMedida.clear();
		this.grupoAlimento.clear();
	}

	//fillObject
	@Override
	public String getNombre() {
		return nombre.getValue();
	}

	@Override
	public String getDescripcion() {
		return descripcion.getValue();
	}

	@Override
	public BigDecimal getPesoPorRacion() {
		return UtilClient.Numeros.string2BigDecimal( pesoPorRacion.getValue() );
	}

	@Override
	public BigDecimal getCoste() {
		return UtilClient.Numeros.string2BigDecimal( coste.getValue() );
	}

	@Override
	public Long getIdUnidadDeMedida() {
		return suggestUnidadesDeMedida.getId();
	}

	@Override
	public Long getIdGrupoAlimento() {
		return suggestGruposAlimento.getId();
	}

	@Override
	public boolean validate() {
		boolean dev = true;
		dev &= Validator.validate( new Bootstrap3Action( nombreFormGroup, nombreError ), new notEmpty( nombre.getValue() ) );
		dev &= Validator.validate( new Bootstrap3Action( pesoPorRacionFormGroup, pesoPorRacionError ), new notEmpty( pesoPorRacion.getValue() ), new isDecimal( pesoPorRacion.getValue() ) );
//		dev &= Validator.validate( new Bootstrap3Action( grupoAlimentoFormGroup, grupoAlimentoError ), new notEmpty( grupoAlimento.getValue() ) );
		return dev;
	}
}