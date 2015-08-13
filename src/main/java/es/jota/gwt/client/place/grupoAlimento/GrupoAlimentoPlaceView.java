package es.jota.gwt.client.place.grupoAlimento;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GrupoAlimentoPlaceView extends GrupoAlimentoPlace {
	private final static String NAME = "grupoAlimento";
	private Long id;
	
	private GrupoAlimentoPlaceView( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<GrupoAlimentoPlaceView> {
		@Override
		public String getToken( GrupoAlimentoPlaceView place ) {
			return place.getId().toString();
		}

		@Override
		public GrupoAlimentoPlaceView getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static GrupoAlimentoPlaceView instance( Long id ) {
		return new GrupoAlimentoPlaceView( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}