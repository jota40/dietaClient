package es.jota.gwt.client.place.grupoAlimento;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GrupoAlimentoPlaceNew extends GrupoAlimentoPlace {
	private final static String NAME = "grupoAlimentoNew";
	
	private GrupoAlimentoPlaceNew() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<GrupoAlimentoPlaceNew> {
		@Override
		public String getToken(GrupoAlimentoPlaceNew place) {
			return "";
		}

		@Override
		public GrupoAlimentoPlaceNew getPlace(String token) {
			return instance();
		}
	}

	public static GrupoAlimentoPlaceNew instance() {
		return new GrupoAlimentoPlaceNew();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}