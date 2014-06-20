package br.ufg.inf.fs.es.integracao.sna.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.ufg.inf.fs.es.integracao.sna.R;
import br.ufg.inf.fs.es.integracao.sna.util.Constantes;
import br.ufg.inf.fs.es.integracao.sna.util.GCM;

public class SNAActivity extends Activity {
	
	private Button botaoAtivarDesativar;
	private boolean statusGCM;

	/**
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sna);
		botaoAtivarDesativar = (Button) findViewById(R.id.botao_ativar_desativar);
		
		statusGCM = GCM.statusGCM(getApplicationContext());
		
		botaoNome();
	}
	
	/**
	 * 
	 * @param view
	 */
	public void ativaDesativaSNA(View view) {
		if (GCM.statusGCM(getApplicationContext())) {
			GCM.desativaSNA(getApplicationContext());
			statusGCM = false;
			Toast.makeText(getApplicationContext(), Constantes.SNA_DESATIVADO, Toast.LENGTH_LONG).show();
		} else {
			GCM.ativaSNA(getApplicationContext());
			statusGCM = true;
			Toast.makeText(getApplicationContext(), Constantes.SNA_ATIVO, Toast.LENGTH_LONG).show();
		}
		botaoNome();
	}
	
	/**
	 * 
	 */
	private void botaoNome() {
		if (statusGCM) {
			botaoAtivarDesativar.setText("Desativar SNA");
		} else {
			botaoAtivarDesativar.setText("Ativar SNA");
		}
	}

}
