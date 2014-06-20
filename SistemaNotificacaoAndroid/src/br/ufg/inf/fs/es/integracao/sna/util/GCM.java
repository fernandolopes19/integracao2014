package br.ufg.inf.fs.es.integracao.sna.util;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class GCM {
	
	/**
	 * 
	 * @param context
	 */
	public static void ativaSNA(Context context) {
		GCMRegistrar.checkDevice(context);
		GCMRegistrar.checkManifest(context);
		final String regId = GCMRegistrar
				.getRegistrationId(context);
		if (regId.equals("")) {
			GCMRegistrar.register(context, Constantes.SENDER_ID);
			Log.i(Constantes.TAG, Constantes.SNA_ATIVO);
		} else {
			Log.i(Constantes.TAG, Constantes.SNA_ATIVO + "ID: " + regId);
		}
	}
	
	/**
	 * 
	 * @param context
	 */
	public static void desativaSNA(Context context) {
			GCMRegistrar.unregister(context);
			Log.i(Constantes.TAG, Constantes.SNA_DESATIVADO);
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static boolean statusGCM(Context context) {
		return GCMRegistrar.isRegistered(context);
	}

}
