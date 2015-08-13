package es.jota.gwt.client.place.unidadDeMedida;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class UnidadDeMedidaPlaceList extends UnidadDeMedidaPlace {
	private final static String NAME = "unidadesDeMedida";
	
	private UnidadDeMedidaPlaceList() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<UnidadDeMedidaPlaceList> {
		@Override
		public String getToken(UnidadDeMedidaPlaceList place) {
			return "";
		}

		@Override
		public UnidadDeMedidaPlaceList getPlace(String token) {
			return instance();
		}
	}

	public static UnidadDeMedidaPlaceList instance() {
		return new UnidadDeMedidaPlaceList();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}