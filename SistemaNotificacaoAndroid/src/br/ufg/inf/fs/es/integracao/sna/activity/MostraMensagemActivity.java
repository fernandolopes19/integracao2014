package br.ufg.inf.fs.es.integracao.sna.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MostraMensagemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView texto = new TextView(getApplicationContext());
		texto.setText(getIntent().getStringExtra("mensagem_recebida"));
		texto.setTextSize(25.0F);
		texto.setTextColor(Color.BLACK);
		setContentView(texto);
	}
}
