package net.programaciondevideojuegos.game01.task;

import java.util.ArrayList;
import java.util.List;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.activities.tabs.adapter.TabAdapter;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class LoadScoreTask extends AsyncTask<Void, Void, String> {

	private String url = "";
	private Context context = null;
	private String parameters[] = { Assets.PARAM_LEVEL };
	private String values[] = new String[parameters.length];
	private ListView lstScores = null;
	private LinearLayout prgLoadingBar = null;

	public LoadScoreTask(Context context, String level, ListView lstScores,
			LinearLayout prgLoadingBar) {
		this.context = context;
		this.lstScores = lstScores;
		this.prgLoadingBar = prgLoadingBar;
		this.url = Assets.URL_SCORE + "getScore";
		values[0] = level;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String result = Util.httpPost(url, parameters, values);
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null) {
			JSONArray objects = null;
			try {
				objects = new JSONArray(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (objects != null) {
				List<String> scores = new ArrayList<String>();

				for (int i = 0; i < objects.length(); i++) {
					String player = "";
					String currentScore = "";
					try {
						player = objects.getJSONObject(i).getString("nickname");
						currentScore = objects.getJSONObject(i).getString(
								"score");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					scores.add(player + "###" + currentScore);
				}

				prgLoadingBar.setVisibility(View.GONE);
				TabAdapter adapter = new TabAdapter(context,
						R.layout.list_scores, scores);
				lstScores.setAdapter(adapter);
				lstScores.setVisibility(View.VISIBLE);

			} else {
				Main.makeToast(context,
						context.getResources().getString(R.string.noConnection));
			}
		} else {
			Main.makeToast(context,
					context.getResources().getString(R.string.noConnection));
		}
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

}
