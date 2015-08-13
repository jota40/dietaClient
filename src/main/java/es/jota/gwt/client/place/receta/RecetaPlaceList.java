package es.jota.gwt.client.place.receta;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class RecetaPlaceList extends RecetaPlace {
	private final static String NAME = "recetas";
	
	private RecetaPlaceList() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<RecetaPlaceList> {
		@Override
		public String getToken(RecetaPlaceList place) {
			return "";
		}

		@Override
		public RecetaPlaceList getPlace(String token) {
			return instance();
		}
	}

	public static RecetaPlaceList instance() {
		return new RecetaPlaceList();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}
}