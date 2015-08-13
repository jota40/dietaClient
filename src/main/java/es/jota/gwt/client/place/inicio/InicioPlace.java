package es.jota.gwt.client.place.inicio;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class InicioPlace extends Place {
	private final static String NAME = "inicio";
	
	private InicioPlace() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<Place> {
		@Override
		public String getToken(Place place) {
			return "";
		}

		@Override
		public Place getPlace(String token) {
			return instance();
		}
	}

	public static InicioPlace instance() {
		return new InicioPlace();
	}

	public static String getUrl() {
		return NAME + ":";
	}
}