package net.programaciondevideojuegos.game01.activities.tabs;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.task.LoadScoreTask;
import net.programaciondevideojuegos.game01.utils.Util;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NormalTab extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_score);
		ListView lstScores = (ListView) findViewById(R.id.lstScores);
		LinearLayout prgLoadingBar = (LinearLayout) findViewById(R.id.prgLoading);
		if (Util.isNetworkAvailable(getApplicationContext())) {
			LoadScoreTask loadScore = new LoadScoreTask(
					getApplicationContext(), "1", lstScores, prgLoadingBar);
			loadScore.execute();
		} else {
			Main.makeToast(getApplicationContext(),
					getResources().getString(R.string.noConnection));
		}
	}

}
