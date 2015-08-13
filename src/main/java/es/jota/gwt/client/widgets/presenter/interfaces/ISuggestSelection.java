package es.jota.gwt.client.widgets.presenter.interfaces;

public interface ISuggestSelection {
	public interface Delegate {
		void clickOnSelection(ISuggestSelection selection);
	}

	int getIndex();
}