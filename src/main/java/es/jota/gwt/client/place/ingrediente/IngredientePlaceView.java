package es.jota.gwt.client.place.ingrediente;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class IngredientePlaceView extends IngredientePlace {
	private final static String NAME = "ingrediente";
	private Long id;
	
	private IngredientePlaceView( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<IngredientePlaceView> {
		@Override
		public String getToken( IngredientePlaceView place ) {
			return place.getId().toString();
		}

		@Override
		public IngredientePlaceView getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static IngredientePlaceView instance( Long id ) {
		return new IngredientePlaceView( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}