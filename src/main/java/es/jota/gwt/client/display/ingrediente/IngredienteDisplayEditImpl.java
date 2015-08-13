package es.jota.gwt.client.display.ingrediente;

import java.math.BigDecimal;

import jota.server.dto.GrupoAlimentoDto;
import jota.server.dto.UnidadDeMedidaDto;

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

public class IngredienteDisplayEditImpl extends Composite implements IngredienteDisplayEdit {
	
	private static IngredienteDisplayEditUiBinder uiBinder = GWT.create(IngredienteDisplayEditUiBinder.class);
	interface IngredienteDisplayEditUiBinder extends UiBinder<Widget, IngredienteDisplayEditImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem ver;
	
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

	private IngredienteDisplayEdit.Listener listener;
	private SuggestUnidadesDeMedida suggestUnidadesDeMedida;
	private SuggestGruposAlimento suggestGruposAlimento;

	@UiHandler("modificarYListar")
	public void modificarYListarOnClick(ClickEvent event) {
		listener.modificarYListar();
	}

	@UiHandler("modificarYNuevo")
	public void modificarYNuevoOnClick(ClickEvent event) {
		listener.modificarYNuevo();
	}

	@UiHandler("modificar")
	public void modificarOnClick(ClickEvent event) {
		listener.modificar();
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public IngredienteDisplayEditImpl() {
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

	@Override
	public void setNuevoUrl( String url ) {
		nuevo.setHref( url );
	}

	@Override
	public void setVerUrl( String url ) {
		ver.setHref( url );
	}

	// fillForm
	@Override
	public void setNombre( String nombre ) {
		this.nombre.setValue( nombre );
	}

	@Override
	public void setDescripcion( String descripcion ) {
		this.descripcion.setValue( descripcion );
	}

	@Override
	public void setPesoPorRacion( BigDecimal pesoPorRacion ) {
		this.pesoPorRacion.setValue( UtilClient.Numeros.number2String( pesoPorRacion ) );
	}

	@Override
	public void setCoste( BigDecimal coste ) {
		this.coste.setValue( UtilClient.Numeros.number2String( coste ) );
	}

	@Override
	public void setUnidadDeMedida( UnidadDeMedidaDto unidadDeMedida ) {
		suggestUnidadesDeMedida.setValue( unidadDeMedida );
	}
	
	@Override
	public void setGrupoAlimento( GrupoAlimentoDto grupoAlimento ) {
		this.suggestGruposAlimento.setValue( grupoAlimento );
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

	//validate
	@Override
	public boolean validate() {
		boolean dev = true;
		dev &= Validator.validate( new Bootstrap3Action( nombreFormGroup, nombreError ), new notEmpty( nombre.getValue() ) );
		dev &= Validator.validate( new Bootstrap3Action( pesoPorRacionFormGroup, pesoPorRacionError ), new notEmpty( pesoPorRacion.getValue() ), new isDecimal( pesoPorRacion.getValue() ) );
//		dev &= Validator.validate( new Bootstrap3Action( grupoAlimentoFormGroup, grupoAlimentoError ), new notEmpty( grupoAlimento.getValue() ) );
		return dev;
	}
}