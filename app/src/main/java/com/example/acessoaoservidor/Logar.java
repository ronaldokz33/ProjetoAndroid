package com.example.acessoaoservidor;

import android.app.Activity;

import com.example.async.LoginRequest;

import android.os.Bundle;
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

	}

    public void submit(View v) {

        ((LoginRequest) new LoginRequest(Logar.this, txtLogin.getText().toString(), txtSenha.getText().toString())).execute();

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
