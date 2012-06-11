package net.programaciondevideojuegos.game01.activities;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.utils.Assets;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScene extends Main {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scene_splash);
		_init();
	}

	private void _init() {

		Assets.SCALEX = (float) display.getWidth()
				/ (float) Assets.DEFAULT_WIDTH;

		Assets.SCALEY = (float) display.getHeight()
				/ (float) Assets.DEFAULT_HEIGHT;

		ImageView imgSplash = (ImageView) findViewById(R.id.imgSplash);
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fade_out);
		imgSplash.setAnimation(anim);

		anim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashScene.this, MenuScene.class);
				startActivity(intent);
				SplashScene.this.finish();
			}
		});

	}

}
