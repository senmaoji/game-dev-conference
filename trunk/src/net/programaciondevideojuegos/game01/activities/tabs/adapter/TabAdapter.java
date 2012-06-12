package net.programaciondevideojuegos.game01.activities.tabs.adapter;

import java.util.List;

import net.programaciondevideojuegos.game01.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TabAdapter extends ArrayAdapter<String> {

	private Context context;
	private List<String> objects;
	private int layout;

	public TabAdapter(Context context, int layout, List<String> objects) {
		super(context, layout, objects);
		this.context = context;
		this.layout = layout;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View item = null;
		if (objects != null) {

			LayoutInflater inflate = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			item = inflate.inflate(this.layout, null);
			String values[] = objects.get(position).split("###");
			String player = values[0];
			String score = values[1];
			TextView tvPlayer = (TextView) item.findViewById(R.id.tvPlayer);
			TextView tvScore = (TextView) item.findViewById(R.id.tvScore);

			tvPlayer.setText(player);
			tvScore.setText(score);

		}
		return (item);
	}

}
