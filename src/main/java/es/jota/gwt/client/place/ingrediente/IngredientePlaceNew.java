package es.jota.gwt.client.place.ingrediente;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class IngredientePlaceNew extends IngredientePlace {
	private final static String NAME = "ingredienteNew";
	
	private IngredientePlaceNew() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<IngredientePlaceNew> {
		@Override
		public String getToken(IngredientePlaceNew place) {
			return "";
		}

		@Override
		public IngredientePlaceNew getPlace(String token) {
			return instance();
		}
	}

	public static IngredientePlaceNew instance() {
		return new IngredientePlaceNew();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}