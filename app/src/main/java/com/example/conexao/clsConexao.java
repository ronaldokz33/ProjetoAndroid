package com.example.conexao;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;


import java.net.URLConnection;

//bibliotecas http
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import android.util.Log;

public class clsConexao{
	public static final int HTTP_TIMEOUT = 30 * 1000;
	private static HttpClient httpClient;
	
	private static HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient();
			final HttpParams httpParamns = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParamns, HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParamns, HTTP_TIMEOUT);
			ConnManagerParams.setTimeout(httpParamns, HTTP_TIMEOUT);
		}
		return httpClient;
	}
	//Videoaulas Neri Neitzke - mais de 4.000 videoaulas www.informaticon.com.br
	public static String executaHttpPost(String url, ArrayList<NameValuePair> parametrosPost) throws Exception {
		BufferedReader bufferedReader = null;
		try {
			HttpClient client = new DefaultHttpClient();//getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parametrosPost);
			httpPost.setEntity(formEntity);
			Log.i("Passo1", "to aqui");
			HttpResponse httpResponse = client.execute(httpPost);
			Log.i("Passo2", "Saporra de linha funcionou");
			bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + LS);
			}
			bufferedReader.close();

			String resultado = stringBuffer.toString();
			return resultado;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}			
	}
	
	//Videoaulas Neri Neitzke - mais de 4.000 videoaulas www.informaticon.com.br
	public static String executaHttpGet(String url) throws Exception {
		BufferedReader bufferedReader = null;
		try {
			HttpClient client = getHttpClient();
			HttpGet httpGet = new HttpGet(url);			
			httpGet.setURI(new URI(url));
			HttpResponse httpResponse = client.execute(httpGet);
			bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator"); // \s
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + LS);
			}
			bufferedReader.close();

			String resultado = stringBuffer.toString();
			return resultado;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}			
		//Videoaulas Neri Neitzke - mais de 4.000 videoaulas www.informaticon.com.br

	}
}