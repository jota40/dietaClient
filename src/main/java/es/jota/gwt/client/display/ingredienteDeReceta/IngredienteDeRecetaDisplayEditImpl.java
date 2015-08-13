package es.jota.gwt.client.display.ingredienteDeReceta;

import java.util.Arrays;
import java.util.List;

import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.IngredienteDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.widgets.custom.SuggestIngredientes;
import es.jota.gwt.client.widgets.display.Suggest;
import es.jota.gwt.client.widgets.display.TrWidget;

public class IngredienteDeRecetaDisplayEditImpl extends TrWidget implements IngredienteDeRecetaDisplayEdit {
	
	private static IngredienteDeRecetaDisplayEditUiBinder uiBinder = GWT.create(IngredienteDeRecetaDisplayEditUiBinder.class);
	interface IngredienteDeRecetaDisplayEditUiBinder extends UiBinder<Widget, IngredienteDeRecetaDisplayEditImpl>{}

	@UiField TableRowElement tr;
	@UiField Suggest ingrediente;
	@UiField InputElement cantidad;

	IngredienteDeRecetaDto ingredienteDeRecetaDto;
	private IngredienteDeRecetaDisplayEdit.Listener listener;
	private SuggestIngredientes suggestIngrediente;

	public IngredienteDeRecetaDisplayEditImpl() {
		ingredienteDeRecetaDto = new IngredienteDeRecetaDto();
		initWidget(uiBinder.createAndBindUi(this));
		suggestIngrediente = new SuggestIngredientes( ingrediente );
		suggestIngrediente.addValueChangeHandler( new ValueChangeHandler<List<IngredienteDto>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<IngredienteDto>> event) {
				tr.setClassName( suggestIngrediente.getValue() == null ? "" : suggestIngrediente.getValue().getGrupoAlimento().getColor() );
			}
		});
	}

	@UiHandler("borrar")
	public void borrarOnClick(ClickEvent event) {
		removeFromParent();
	}

	@UiHandler("up")
	public void upOnClick(ClickEvent event) {
		moveUp();
	}

	@UiHandler("down")
	public void downOnClick(ClickEvent event) {
		moveDown();
	}

	@Override
	public IngredienteDeRecetaDisplayEdit setListener( IngredienteDeRecetaDisplayEdit.Listener listener ) {
		this.listener = listener;
		return this;
	}

	@Override
	public List<TableRowElement> getTr() {
		return Arrays.asList( tr );
	}

	@Override
	public void set( IngredienteDeRecetaDto ingredienteDeRecetaDto ) {
		this.ingredienteDeRecetaDto = ingredienteDeRecetaDto;
		tr.setClassName( ingredienteDeRecetaDto.getIngredienteDto().getGrupoAlimento().getColor() );
		suggestIngrediente.setValue( ingredienteDeRecetaDto.getIngredienteDto() );
		cantidad.setValue( UtilClient.Numeros.number2String( ingredienteDeRecetaDto.calcularCantidad() ) );
	}

	@Override
	public IngredienteDeRecetaDto get() {
		ingredienteDeRecetaDto.setIdIngrediente( suggestIngrediente.getId() );
		ingredienteDeRecetaDto.setCantidad( UtilClient.Numeros.string2BigDecimal( cantidad.getValue() ) );
		return ingredienteDeRecetaDto;
	}
}