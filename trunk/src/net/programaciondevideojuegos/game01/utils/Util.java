package net.programaciondevideojuegos.game01.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Util {

	public static Bitmap decodeBitmap(Resources resource, int resourceID) {
		Bitmap bmp = null;
		Options options = new Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		bmp = BitmapFactory.decodeResource(resource, resourceID, options);
		return bmp;
	}

	public static Bitmap decodeBitmap(Context context, String resource) {
		AssetManager am = context.getAssets();
		Bitmap bmp = null;
		Options options = new Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		try {
			bmp = BitmapFactory.decodeStream(am.open(resource), null, options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bmp;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInformation = connectivityManager
				.getActiveNetworkInfo();
		return networkInformation != null;
	}

	public static float getRandomNumber(float start, float end) {
		Random random = new Random();
		float value = (random.nextFloat() * (end - start + 1) + start);
		return value;
	}

	public static String httpPost(String url, String campos[], String valores[]) {
		String response = null;
		if (campos.length > 0 && valores.length > 0
				&& campos.length == valores.length) {
			HttpClient http_client = new DefaultHttpClient();
			HttpPost http_post = new HttpPost(url);
			try {
				List<NameValuePair> parametros = new ArrayList<NameValuePair>(
						campos.length);
				for (int i = 0; i < campos.length; i++) {
					parametros
							.add(new BasicNameValuePair(campos[i], valores[i]));
				}
				http_post.setEntity(new UrlEncodedFormEntity(parametros));
				HttpResponse http_response = http_client.execute(http_post);
				if (http_response.getStatusLine().getStatusCode() == 200) {
					response = EntityUtils.toString(http_response.getEntity())
							.trim();
					Log.d("response", response);
				}
			} catch (ClientProtocolException e) {
				Log.d("ClientProtocolException", e.getMessage());

			} catch (IOException e) {
				Log.d("IOException", e.getMessage());

			}
		}
		return response;
	}

	public static JSONArray getJSONArray(String url, String campos[],
			String valores[]) {
		JSONArray arResp = null;
		String response = Util.httpPost(url, campos, valores);
		if (response != null) {
			try {
				arResp = new JSONArray(response);
			} catch (JSONException e) {

			}
		}
		return arResp;
	}

	public static JSONObject getJSONObject(String url, String campos[],
			String valores[]) {
		JSONObject arResp = null;
		String response = Util.httpPost(url, campos, valores);
		if (response != null) {
			try {
				arResp = new JSONObject(response);
			} catch (JSONException e) {

			}
		}
		return arResp;
	}

}
