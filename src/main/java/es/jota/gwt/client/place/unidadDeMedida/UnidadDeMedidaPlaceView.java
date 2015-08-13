package es.jota.gwt.client.place.unidadDeMedida;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class UnidadDeMedidaPlaceView extends UnidadDeMedidaPlace {
	private final static String NAME = "unidadDeMedida";
	private Long id;
	
	private UnidadDeMedidaPlaceView( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<UnidadDeMedidaPlaceView> {
		@Override
		public String getToken( UnidadDeMedidaPlaceView place ) {
			return place.getId().toString();
		}

		@Override
		public UnidadDeMedidaPlaceView getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static UnidadDeMedidaPlaceView instance( Long id ) {
		return new UnidadDeMedidaPlaceView( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}