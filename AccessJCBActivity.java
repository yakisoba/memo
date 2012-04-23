package test.access;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class AccessJCBActivity extends Activity {

	// final String Uid = ""; // au
	// final String Pass = ""; // au
	final String Uid = ""; // jcb
	final String Pass = ""; // jcb

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ReadDialog rd = new ReadDialog();
		rd.execute(Uid, Pass);

	}

	private class ReadDialog extends AsyncTask<String, Integer, Boolean> {
		private ProgressDialog pd;
		private boolean isStop = false;
		private String[] telList = null;
		private String[] infoList = null;
		private int resultCode = 1;

		@Override
		protected void onPreExecute() {
			// バックグラウンドの処理前にUIスレッドでダイアログ表示
			pd = new ProgressDialog(AccessJCBActivity.this);
			pd.setMessage("情報取得中...");
			pd.setCancelable(true);
			pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					isStop = true;
				}
			});
			pd.show();
		}

		@Override
		protected Boolean doInBackground(String... param) {
			Boolean result = false;
			result = getInfo(param[0], param[1]);

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			pd.dismiss();
		}

		@Override
		protected void onCancelled() {
			isStop = true;
		}

		private static final String REF_URL = "https://my.jcb.co.jp/";
		private static final String LOGIN_URL = "https://my.jcb.co.jp/iss-pc/member/user_manage/Login";
		private static final String LOGIN_URL2 = "https://my.jcb.co.jp/iss-pc/member/details_inquiry/detailMenu.html";
		private final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";

		private boolean getInfo(String id, String pass/* , String code */) {
			resultCode = 2;
			try {
				String r;

				HashMap<String, String> options = new HashMap<String, String>();
				// options.put("UserID", id);
				// options.put("Password", pass);
				// options.put("FormType", "LoginForm");
				options.put("userId", id);
				options.put("password", pass);
				options.put("screenId", "0102001");
				options.put("loginRouteId", "0102001");

				// options.put("x", "3");
				// options.put("y", "4");
				r = post(LOGIN_URL, options, REF_URL, "Shift_JIS", true);
				System.out.println(r);
				Log.d("http", r);
				System.out.println(cookie);
				System.out.println("--------------------");
				// if (isStop) return false;
				// r = get(r, REF_URL, "Shift_JIS");
				// System.out.println(r);
				// System.out.println(cookie);
				// System.out.println("--------------------");
				// if (isStop) return false;
				// r = get(r, REF_URL, "UTF-8");
				// System.out.println(r);
				// System.out.println(cookie);
				// System.out.println("--------------------");
				// int p;
				// ArrayList<String> telArray = new ArrayList<String>();
				// ArrayList<String> infoArray = new ArrayList<String>();
				// // get au list
				// p =
				// r.indexOf("<ul id=\"serviceNum_inner\" class=\"wordBreak\">");
				// resultCode = 3;
				// String l = r.substring(p, r.indexOf("</ul>", p) + 5);
				// int pp = 0, c = 0;
				// while ((pp = l.indexOf("javascript:selected_Au", pp)) != -1)
				// {
				// // get no & phone number
				// pp += 24;
				// String no = l.substring(pp, l.indexOf("'", pp));
				// pp = l.indexOf(">", pp);
				// String tel = l.substring(pp + 1, l.indexOf("</a>", pp));
				// System.out.println(no + ":" + tel);
				//
				// if (c > 0) {
				// options.clear();
				// options.put("topSelectedIndex", /* "0" */no);
				// options.put("detailsInfoStatus", "");
				// options.put("detailsInfoService", "au");
				// // get scr
				// p = r.indexOf("?scr=");
				// String scr = r.substring(p + 5, r.indexOf("\">", p));
				// System.out.println("scr:" + scr);
				// // get token
				// p = r.indexOf("org.apache.struts.taglib.html.TOKEN");
				// String token = r.substring(p + 44,
				// r.indexOf("\"></div>", p));
				// System.out.println("token:" + token);
				// System.out.println("--------------------");
				// options.put("org.apache.struts.taglib.html.TOKEN",
				// token);
				//
				// r = post(TOP_URL + "?scr=" + scr, options, TOP_URL,
				// "UTF-8", false);
				// }
				// c++;
				//
				// p = r.indexOf("<div class=\"serviceMenu fLeft wordBreak\">");
				// String d1 = r.substring(p, r.indexOf("</div>", p) + 6);
				// p =
				// r.indexOf("<div class=\"serviceMenu fRight wordBreak\">");
				// String d2 = r.substring(p, r.indexOf("</div>", p) + 6);
				// System.out.println(d1);
				// System.out.println("--------------------");
				// System.out.println(d2);
				// System.out.println("--------------------");
				// telArray.add(tel);
				// infoArray.add(d1 + /* "<hr>" + */d2);
				// // 1件づつtry,catchしたほうが良いかも？？
				// }
				// /*
				// * telArray.add("dummy"); infoArray.add("dummy data");
				// * telArray.add("dummy2"); infoArray.add("dummy data 2");
				// */
				// telList = telArray.toArray(new String[0]);
				// infoList = infoArray.toArray(new String[0]);

				// output消去
				options.clear();
				r = post(LOGIN_URL2, options, REF_URL, "Shift_JIS", true);
				System.out.println(r);
				Log.d("http2", r);
				System.out.println("--------------------");

				SpritString(r);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		private void SpritString(String str) {
			int start = str.indexOf("sectionM");
			int end = str.indexOf("sectionS");
			String test = str.substring(start, end);

			List<String> url = new ArrayList<String>();

			while (test.indexOf("href") > 0) {
				start = test.indexOf("href");
				end = test.indexOf("web");
				String link = test.substring(start + 6, end + 3);
				test = test.substring(end + 3);

				String http = "https://my.jcb.co.jp";
				url.add(http + link);
				Log.d("http3", link);
			}

			for (String out : url) {

				HttpPost httpPost = new HttpPost(url.get(url.indexOf(out)));
				// パラメータを設定
				// httpPost.setEntity(new UrlEncodedFormEntity(params,
				// "UTF-8"));
				DefaultHttpClient client = new DefaultHttpClient();
				HttpResponse httpResponse;
				try {
					httpResponse = client.execute(httpPost);
					// ステータスコードを取得
					int statusCode = httpResponse.getStatusLine()
							.getStatusCode();
					// レスポンスを取得
					HttpEntity entity = httpResponse.getEntity();
					String response = EntityUtils.toString(entity);

					Log.d("http4", response);

				} catch (ClientProtocolException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

			}

		}

		/*
		 * // HTTP GET private String get(String url, String ref, String enc) {
		 * System.out.println("get:" +url ); HttpURLConnection conn = null;
		 * String result = null; try { conn = ((HttpURLConnection) (new
		 * URL(url).openConnection())); if (cookie.compareTo("") != 0)
		 * conn.setRequestProperty("Cookie", cookie);
		 * conn.setRequestProperty("User-Agent", USER_AGENT);
		 * conn.setInstanceFollowRedirects(false); if (ref != null)
		 * conn.setRequestProperty("Referer", ref);
		 * 
		 * conn.setDoInput(true); conn.connect(); System.out.println("resp:" +
		 * conn.getResponseCode()); cookie += getCookie(conn); if
		 * (conn.getResponseCode() == 302) { return
		 * conn.getHeaderField("Location"); } if (conn.getResponseCode() != 200)
		 * return null;
		 * 
		 * InputStreamReader reader = new InputStreamReader(
		 * conn.getInputStream(), enc);
		 * 
		 * int nbytes = 1024; char[] buffer = new char[nbytes]; StringBuffer sb
		 * = new StringBuffer(); int nchars; while ((nchars =
		 * reader.read(buffer)) != -1) sb.append(String.valueOf(buffer, 0,
		 * nchars)); reader.close();
		 * 
		 * result = sb.toString(); } catch (IOException e) { result = null; }
		 * finally { if (conn != null) conn.disconnect(); } return result; }
		 */

		// HTTP POST
		private String post(String url, HashMap<String, String> params,
				String ref, String enc, boolean cookieUpdate) {
			System.out.println("post:"/* +url */);
			URL url_ = null;
			try {
				url_ = new URL(url);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
				return null;
			}
			String result = null;
			HttpURLConnection conn = null;
			// post送信データを１本の文字列にまとめる
			try {
				String postdata = "";
				if (params != null) {
					StringBuilder builder = new StringBuilder();
					Iterator<String> it = params.keySet().iterator();
					boolean firstLine = true;
					while (it.hasNext()) {
						String key_ = (String) it.next();
						String value = (String) params.get(key_);
						String value_encoded = URLEncoder.encode(value, enc);
						if (!firstLine)
							builder.append('&');
						else
							firstLine = false;
						builder.append(key_ + "=" + value_encoded);
					}
					postdata = builder.toString();
				}

				conn = (HttpURLConnection) url_.openConnection();
				// conn.setReadTimeout(40*1000 /* m秒 */);
				// conn.setConnectTimeout(20*1000 /* m秒 */);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				if (cookie.compareTo("") != 0)
					conn.setRequestProperty("Cookie", cookie);
				if (ref != null)
					conn.setRequestProperty("Referer", ref);
				conn.setRequestProperty("User-Agent", USER_AGENT);
				// conn.setRequestProperty("Accept-Language", "ja-JP");
				conn.setInstanceFollowRedirects(false);

				conn.setDoInput(true);
				conn.setDoOutput(true);

				// クエリーから処理結果を読み込む
				if (postdata.length() > 0) {
					OutputStreamWriter writer = new OutputStreamWriter(
							conn.getOutputStream(), enc);
					writer.write(postdata);
					writer.flush();
					writer.close();
				}

				// クエリーを開始
				conn.connect();
				System.out.println("resp:" + conn.getResponseCode());
				if (cookieUpdate)
					cookie = getCookie(conn);
				if (conn.getResponseCode() == 302) {
					return conn.getHeaderField("Location");
				}
				if (conn.getResponseCode() != 200)
					return null;

				InputStreamReader reader = new InputStreamReader(
						conn.getInputStream(), enc);

				int nbytes = 1024;
				char[] buffer = new char[nbytes];
				StringBuffer sb = new StringBuffer();
				int nchars;
				while ((nchars = reader.read(buffer)) != -1)
					sb.append(String.valueOf(buffer, 0, nchars));
				reader.close();

				result = sb.toString();
			} catch (IOException e) {
				result = null;
			} finally {
				if (conn != null)
					conn.disconnect();
			}
			return result;
		}

		private String cookie = "";

		private String getCookie(HttpURLConnection conn) {
//			int n = 1;
//			String ret = "";
//			while (conn.getHeaderFieldKey(n) != null) {
//				System.out.println(conn.getHeaderFieldKey(n) + "|"
//						+ conn.getHeaderField(n));
//				if (conn.getHeaderFieldKey(n).toLowerCase()
//						.compareTo("set-cookie") == 0) {
//					if (ret.indexOf("JSESSIONID") >= 0
//							&& conn.getHeaderField(n).indexOf("JSESSIONID") >= 0) {
//						n++;
//						continue;
//					}
//					ret += conn.getHeaderField(n).substring(0,
//							conn.getHeaderField(n).indexOf(';') + 2);
//				}
//				n++;
//			}
			
			String ret = conn.getHeaderField("Set-Cookie"); 
			System.out.println(ret);
			return ret;
		}
	}
}
