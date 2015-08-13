package es.jota.gwt.client.validator;

import java.util.List;

import com.google.gwt.dom.client.Element;

import es.jota.gwt.client.validator.Validator.Action;

public class Bootstrap3Action implements Action {
	
	private Element formGroup;
	private Element error;

	public Bootstrap3Action( Element formGroup, Element error ) {
		this.formGroup = formGroup;
		this.error = error;
	}

	private void addValidation( String errores ) {
		formGroup.addClassName( "has-error" );
		error.removeClassName( "hide" );
		error.setInnerHTML( errores );
	}

	private void resetValidator() {
		formGroup.removeClassName( "has-error" );
		error.addClassName( "hide" );
		error.setInnerHTML( "" );
	}

	@Override
	public void validate( List<String> errores ) {
		resetValidator();
		if ( errores != null && !errores.isEmpty() ) {
			addValidation(errores.toString().substring(1, errores.toString().length()-1).replaceAll(",", "<br/>") );
		}
	}
}  