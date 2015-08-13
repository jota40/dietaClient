package es.jota.gwt.client.place.receta;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class RecetaPlaceNew extends RecetaPlace {
	private final static String NAME = "recetaNew";
	
	private RecetaPlaceNew() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<RecetaPlaceNew> {
		@Override
		public String getToken(RecetaPlaceNew place) {
			return "";
		}

		@Override
		public RecetaPlaceNew getPlace(String token) {
			return instance();
		}
	}

	public static RecetaPlaceNew instance() {
		return new RecetaPlaceNew();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}