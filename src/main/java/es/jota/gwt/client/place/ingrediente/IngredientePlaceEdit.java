package es.jota.gwt.client.place.ingrediente;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class IngredientePlaceEdit extends IngredientePlace {
	private final static String NAME = "ingredienteEdit";
	private Long id;
	
	private IngredientePlaceEdit( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<IngredientePlaceEdit> {
		@Override
		public String getToken( IngredientePlaceEdit place ) {
			return place.getId().toString();
		}

		@Override
		public IngredientePlaceEdit getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static IngredientePlaceEdit instance( Long id ) {
		return new IngredientePlaceEdit( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}