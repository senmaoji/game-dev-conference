package net.programaciondevideojuegos.game01.activities;

import net.programaciondevideojuegos.game01.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * 
 * @author Carlos Piñan Levels: 0 = Easy; 1 = Normal; 2 = Hard
 */
public class PreferenceScene extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

}