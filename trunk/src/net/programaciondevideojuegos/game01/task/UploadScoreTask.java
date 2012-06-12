package net.programaciondevideojuegos.game01.task;

import net.programaciondevideojuegos.game01.Main;
import net.programaciondevideojuegos.game01.R;
import net.programaciondevideojuegos.game01.utils.Assets;
import net.programaciondevideojuegos.game01.utils.Util;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class UploadScoreTask extends AsyncTask<Void, Void, String> {

	private Context context = null;
	private String url = "";
	private String parameters[] = { Assets.PARAM_PLAYER, Assets.PARAM_SCORE,
			Assets.PARAM_LEVEL };
	private String values[] = new String[parameters.length];

	public UploadScoreTask(Context context, String player, String level) {
		this.context = context;
		this.url = Assets.URL_SCORE + "uploadScore";
		values[0] = player;
		values[1] = Assets.SCORE + "";
		values[2] = level;
		Log.d("url: ", url);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		Main.makeToast(context,
				context.getResources().getString(R.string.uploadingScore));
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
		if (result != null)
			Main.makeToast(context,
					context.getResources().getString(R.string.scoreSubmitted));
		else
			Main.makeToast(context,
					context.getResources().getString(R.string.cancelScore));
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		Main.makeToast(context,
				context.getResources().getString(R.string.cancelScore));
	}

}
