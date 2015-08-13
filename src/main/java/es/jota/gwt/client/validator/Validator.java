package es.jota.gwt.client.validator;

import java.util.ArrayList;
import java.util.List;

public class Validator<T> {

	public interface Action {
		public abstract void validate( List<String> errores );
	}

	public static boolean validate( Action action, Validation ... validations ) {
		List<String> errores = new ArrayList<String>();
		if ( validations != null ) {
			for ( Validation validation : validations ) {
				String error = validation.validate();
				if ( error != null ) {
					errores.add( error );
				}
			}
			if ( action != null ) {
				action.validate( errores );
			}
		}
		return errores.isEmpty();
	}
}