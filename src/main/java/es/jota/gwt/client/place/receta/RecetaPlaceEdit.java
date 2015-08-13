package es.jota.gwt.client.place.receta;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class RecetaPlaceEdit extends RecetaPlace {
	private final static String NAME = "recetaEdit";
	private Long id;
	
	private RecetaPlaceEdit( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<RecetaPlaceEdit> {
		@Override
		public String getToken( RecetaPlaceEdit place ) {
			return place.getId().toString();
		}

		@Override
		public RecetaPlaceEdit getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static RecetaPlaceEdit instance( Long id ) {
		return new RecetaPlaceEdit( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}