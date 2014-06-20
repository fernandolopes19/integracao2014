package br.ufg.inf.fs.es.integracao.sna;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.ufg.inf.fs.es.integracao.sna.util.Constantes;
import br.ufg.inf.fs.es.integracao.sna.util.Notificacao;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	/**
	 * 
	 */
	@Override
	protected void onRegistered(Context context, String regId) {
		Log.i(Constantes.TAG, Constantes.SNA_ATIVO);
		String mensagemLog = "ID de registro no SNA: " + regId;
		Log.i(Constantes.TAG, mensagemLog);
	}

	/**
	 * 
	 */
	@Override
	protected void onError(Context context, String errorMessage) {
		Log.e(Constantes.TAG, "Erro: " + errorMessage);
	}

	/**
	 * 
	 */
	@Override
	protected void onMessage(Context context, Intent intent) {
		String mensagem = intent.getExtras().getString("mensagem");
		Log.i(Constantes.TAG, Constantes.MENSAGEM_RECEBIDA + ": " + mensagem);
		
		if (mensagem != null && !"".equals(mensagem))
			Notificacao.mostraNotificacao(Constantes.MENSAGEM_RECEBIDA, mensagem, context);
		
	}

	/**
	 * 
	 */
	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.i(Constantes.TAG, Constantes.SNA_DESATIVADO);
	}
	
}
