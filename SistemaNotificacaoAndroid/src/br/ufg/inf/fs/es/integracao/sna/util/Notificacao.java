package br.ufg.inf.fs.es.integracao.sna.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import br.ufg.inf.fs.es.integracao.sna.R;
import br.ufg.inf.fs.es.integracao.sna.activity.MostraMensagemActivity;

public class Notificacao {

	/**
	 * 
	 * @param titulo
	 * @param mensagem
	 * @param context
	 */
	public static void mostraNotificacao(String titulo, String mensagem,
			Context context) {

		long tempoDefinido = System.currentTimeMillis();

		Notification notification = new Notification(R.drawable.ic_launcher,
				titulo, tempoDefinido);

		Intent intent = new Intent(context, MostraMensagemActivity.class);
		intent.putExtra("mensagem_recebida", mensagem);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(context, titulo, mensagem,
				pendingIntent);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		notification.defaults = Notification.DEFAULT_ALL;

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}

}
