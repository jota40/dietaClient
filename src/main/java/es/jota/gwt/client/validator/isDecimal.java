package es.jota.gwt.client.validator;

import java.math.BigDecimal;

public class isDecimal extends Validation<String> {

	public isDecimal( String value ) {
		super( value );
	}

	@Override
	public String validate() {
		String dev = null;
		try {
			new BigDecimal( value );
		} catch ( Exception e ) {
			dev = "Debe ser un valor decimal";
		}
		return dev;
	}
}