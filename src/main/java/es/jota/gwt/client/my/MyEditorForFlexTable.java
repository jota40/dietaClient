package es.jota.gwt.client.my;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;

public interface MyEditorForFlexTable<T> extends MyEditorBase<T> {
	Collection<Widget> getWidgets();
}
