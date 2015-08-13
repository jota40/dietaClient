package es.jota.gwt.client.place.unidadDeMedida;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class UnidadDeMedidaPlaceNew extends UnidadDeMedidaPlace {
	private final static String NAME = "unidadDeMedidaNew";
	
	private UnidadDeMedidaPlaceNew() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<UnidadDeMedidaPlaceNew> {
		@Override
		public String getToken(UnidadDeMedidaPlaceNew place) {
			return "";
		}

		@Override
		public UnidadDeMedidaPlaceNew getPlace(String token) {
			return instance();
		}
	}

	public static UnidadDeMedidaPlaceNew instance() {
		return new UnidadDeMedidaPlaceNew();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}