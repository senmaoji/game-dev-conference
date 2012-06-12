package net.programaciondevideojuegos.game01.activities;

import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.activities.tabs.EasyTab;
import net.programaciondevideojuegos.game01.activities.tabs.HardTab;
import net.programaciondevideojuegos.game01.activities.tabs.NormalTab;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ScoreScene extends TabActivity {

	private TabHost tabHost = null;
	private Resources resources = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scene_score);
		tabHost = getTabHost();
		resources = getResources();
		addEasyTab();
		addNormalTab();
		addHardTab();
		tabHost.setCurrentTab(0);
	}

	private void addEasyTab() {
		Intent intent = new Intent(ScoreScene.this, EasyTab.class);
		TabSpec spec = tabHost.newTabSpec(resources.getString(R.string.easy));
		spec.setIndicator(resources.getString(R.string.easy),
				resources.getDrawable(android.R.drawable.ic_menu_info_details));
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

	private void addNormalTab() {
		Intent intent = new Intent(ScoreScene.this, NormalTab.class);
		TabSpec spec = tabHost.newTabSpec(resources.getString(R.string.normal));
		spec.setIndicator(resources.getString(R.string.normal),
				resources.getDrawable(android.R.drawable.ic_menu_info_details));
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

	private void addHardTab() {
		Intent intent = new Intent(ScoreScene.this, HardTab.class);
		TabSpec spec = tabHost.newTabSpec(resources.getString(R.string.hard));
		spec.setIndicator(resources.getString(R.string.hard),
				resources.getDrawable(android.R.drawable.ic_menu_info_details));
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

}
