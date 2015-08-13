package es.jota.gwt.client.display.ingredienteDeReceta;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import jota.server.dto.IngredienteDeRecetaDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.place.ingrediente.IngredientePlaceEdit;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceView;
import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.utils.enums.IconoEnum;
import es.jota.gwt.client.widgets.display.TrWidget;

public class IngredienteDeRecetaDisplayViewImpl extends TrWidget implements IngredienteDeRecetaDisplayView {
	
	private static IngredienteDeRecetaDisplayEditUiBinder uiBinder = GWT.create(IngredienteDeRecetaDisplayEditUiBinder.class);
	interface IngredienteDeRecetaDisplayEditUiBinder extends UiBinder<Widget, IngredienteDeRecetaDisplayViewImpl>{}

	@UiField TableRowElement tr;
	@UiField TableCellElement ingrediente;
	@UiField TableCellElement racionesPorComensal;
	@UiField TableCellElement cantidad;
	@UiField TableCellElement unidadDeMedida;
	@UiField TableCellElement coste;

	IngredienteDeRecetaDto ingredienteDeRecetaDto;
	private IngredienteDeRecetaDisplayView.Listener listener;

	public IngredienteDeRecetaDisplayViewImpl() {
		ingredienteDeRecetaDto = new IngredienteDeRecetaDto();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public IngredienteDeRecetaDisplayView setListener( IngredienteDeRecetaDisplayView.Listener listener ) {
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
		Long id = ingredienteDeRecetaDto.getIdIngrediente();
		String nombre = ingredienteDeRecetaDto.getIngredienteDto().getNombre();
		ingrediente.setInnerSafeHtml( UtilClient.TEMPLATES.mainLinkCell( IngredientePlaceEdit.getUrl( id ), IconoEnum.EDITAR.get(), IngredientePlaceView.getUrl( id ), nombre ) );
		unidadDeMedida.setInnerHTML( ingredienteDeRecetaDto.getIngredienteDto().getUnidadDeMedida() == null ? "" : ingredienteDeRecetaDto.getIngredienteDto().getUnidadDeMedida().getNombre() );
		setDatosCalculados();
	}

	@Override
	public IngredienteDeRecetaDto get() {
		return ingredienteDeRecetaDto;
	}

	@Override
	public void cambiarPax( BigDecimal pax ) {
		ingredienteDeRecetaDto.setPax( pax == null ? 0 : pax.intValue() );
		setDatosCalculados();
	}

	private void setDatosCalculados() {
		racionesPorComensal.setInnerHTML( UtilClient.Numeros.number2String( ingredienteDeRecetaDto.calcularRacionesPorComensal() ) );
		cantidad.setInnerHTML( UtilClient.Numeros.number2String( ingredienteDeRecetaDto.calcularCantidad() ) );
		coste.setInnerHTML( UtilClient.Numeros.number2String( ingredienteDeRecetaDto.calcularTotal(), "€" ) );
	}

	@Override
	public void addCantidadPorComensal( BigDecimal incremento ) {
		ingredienteDeRecetaDto.addCantidadPorComensal( incremento );
		setDatosCalculados();
	}
}