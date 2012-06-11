package net.programaciondevideojuegos.game01.activities;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuScene extends Main {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scene_menu);
		_init();
	}

	private void _init() {

		Button btnGame = (Button) findViewById(R.id.btnGame);
		btnGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuScene.this, GameScene.class);
				startActivity(intent);
			}
		});

		Button btnScore = (Button) findViewById(R.id.btnScore);
		btnScore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuScene.this, ScoreScene.class);
				startActivity(intent);
			}
		});

		Button btnSettings = (Button) findViewById(R.id.btnSettings);
		btnSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuScene.this,
						PreferenceScene.class);
				startActivity(intent);
			}
		});

		Button btnAboutMe = (Button) findViewById(R.id.btnAboutMe);
		btnAboutMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuScene.this, AboutMeScene.class);
				startActivity(intent);
			}
		});

	}

}