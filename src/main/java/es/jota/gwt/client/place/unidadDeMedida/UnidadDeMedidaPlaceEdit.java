package es.jota.gwt.client.place.unidadDeMedida;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class UnidadDeMedidaPlaceEdit extends UnidadDeMedidaPlace {
	private final static String NAME = "unidadDeMedidaEdit";
	private Long id;
	
	private UnidadDeMedidaPlaceEdit( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<UnidadDeMedidaPlaceEdit> {
		@Override
		public String getToken( UnidadDeMedidaPlaceEdit place ) {
			return place.getId().toString();
		}

		@Override
		public UnidadDeMedidaPlaceEdit getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static UnidadDeMedidaPlaceEdit instance( Long id ) {
		return new UnidadDeMedidaPlaceEdit( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}