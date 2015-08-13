package es.jota.gwt.client.mappers;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;

public class TopActivityMapper implements ActivityMapper {
	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public TopActivityMapper() {
		super();
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		return miApp.INJECTOR.getTopMenuActivity().setPlace( place );
	}

}
