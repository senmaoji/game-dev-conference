package net.programaciondevideojuegos.game01.task;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.os.AsyncTask;

public class UploadScoreTask extends AsyncTask<Void, Void, Integer> {

	private Context context = null;
	private String url = "";
	private String parameters[] = { Assets.PARAM_PLAYER, Assets.PARAM_SCORE };
	private String values[] = new String[parameters.length];

	public UploadScoreTask(Context context, String player) {
		this.context = context;
		this.url = Assets.URL_SCORE;
		values[0] = player;
		values[1] = Assets.SCORE + "";
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		Main.makeToast(context,
				context.getResources().getString(R.string.uploadingScore));
	}

	@Override
	protected Integer doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String result = Util.httpPost(url, parameters, values);
		return 0;
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		switch (result) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		default:
		}
		Main.makeToast(context,
				context.getResources().getString(R.string.scoreSubmitted));
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		Main.makeToast(context,
				context.getResources().getString(R.string.cancelScore));
	}

}
