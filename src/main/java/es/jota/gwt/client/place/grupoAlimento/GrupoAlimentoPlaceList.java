package es.jota.gwt.client.place.grupoAlimento;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GrupoAlimentoPlaceList extends GrupoAlimentoPlace {
	private final static String NAME = "gruposAlimento";
	
	private GrupoAlimentoPlaceList() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<GrupoAlimentoPlaceList> {
		@Override
		public String getToken(GrupoAlimentoPlaceList place) {
			return "";
		}

		@Override
		public GrupoAlimentoPlaceList getPlace(String token) {
			return instance();
		}
	}

	public static GrupoAlimentoPlaceList instance() {
		return new GrupoAlimentoPlaceList();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}