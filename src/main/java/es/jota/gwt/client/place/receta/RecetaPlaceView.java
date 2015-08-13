package es.jota.gwt.client.place.receta;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class RecetaPlaceView extends RecetaPlace {
	private final static String NAME = "receta";
	private Long id;
	
	private RecetaPlaceView( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<RecetaPlaceView> {
		@Override
		public String getToken( RecetaPlaceView place ) {
			return place.getId().toString();
		}

		@Override
		public RecetaPlaceView getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static RecetaPlaceView instance( Long id ) {
		return new RecetaPlaceView( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}