package es.jota.gwt.client.place.grupoAlimento;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GrupoAlimentoPlaceEdit extends GrupoAlimentoPlace {
	private final static String NAME = "grupoAlimentoEdit";
	private Long id;
	
	private GrupoAlimentoPlaceEdit( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<GrupoAlimentoPlaceEdit> {
		@Override
		public String getToken( GrupoAlimentoPlaceEdit place ) {
			return place.getId().toString();
		}

		@Override
		public GrupoAlimentoPlaceEdit getPlace( String token ) {
			try {
				Long id = Long.parseLong( token );
				return instance( id );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static GrupoAlimentoPlaceEdit instance( Long id ) {
		return new GrupoAlimentoPlaceEdit( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}