package com.example.acessoaoservidor;

import android.app.Activity;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.conexao.clsConexao;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class Logar extends Activity {
	EditText txtLogin, txtSenha;
	Button btnLogar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logar);
		txtLogin = (EditText) findViewById(R.id.txtLogin);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		btnLogar = (Button) findViewById(R.id.btnLogar);
		
		btnLogar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Logar","Entrou no evento");
				String url = "http://192.168.0.101:90/Android/logar.php";
				String urlGet = "http://192.168.0.101:90/Android/logar.php?usuario=" + txtLogin.getText().toString() + "&senha=" + txtSenha.getText().toString();
				ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
				parametrosPost.add(new BasicNameValuePair("usuario", txtLogin.toString()));
				parametrosPost.add(new BasicNameValuePair("senha", txtSenha.toString()));
				String retorno = null;
				try{
					retorno = clsConexao.executaHttpPost(url,parametrosPost);
					String resposta = retorno;
					resposta = resposta.replaceAll("\\s+", "");
					if(resposta.equals(""))
					{
						Toast.makeText(Logar.this, "Validado com sucesso!", Toast.LENGTH_LONG).show();
						setContentView(R.layout.acesso);
					}
					else
						Toast.makeText(Logar.this, "Login e/ou senha inválidos!", Toast.LENGTH_LONG).show();
				}
				catch(Exception e){
					Log.i("Erro","Erro " + e);
					Toast.makeText(Logar.this, "Erro.:" + e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
