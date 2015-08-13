package es.jota.gwt.client.place.ingrediente;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class IngredientePlaceList extends IngredientePlace {
	private final static String NAME = "ingredientes";
	
	private IngredientePlaceList() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<IngredientePlaceList> {
		@Override
		public String getToken(IngredientePlaceList place) {
			return "";
		}

		@Override
		public IngredientePlaceList getPlace(String token) {
			return instance();
		}
	}

	public static IngredientePlaceList instance() {
		return new IngredientePlaceList();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}