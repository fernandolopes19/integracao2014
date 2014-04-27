package br.ufg.inf.fs.es.integracao.sna.servidor;

import java.util.ArrayList;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class SNAServidor {
	
	private static final String API_KEY = "AIzaSyBBtaDqKKDZMyohPLboAU0polwE3j93VlY";

	public static void main(String args[]) {

		new Thread() {

			public void run() {

				try {

					Sender sender = new Sender(API_KEY); //API Key

					// use this to send message with payload data
					Message messagem = new Message.Builder()
							.collapseKey("message")
							.addData("message", "Welcome to Push Notifications") // you
																					// can
																					// get
																					// this
																					// message
																					// on
																					// client
																					// side
																					// app
							.build();

					System.setProperty("https.proxyHost", "192.168.1.1"); // write
																			// your
																			// own
																			// proxyHost
					System.setProperty("https.proxyPort", "8080"); // write your
																	// own
																	// proxyHost

					
					// Use this code to send notification message to multiple
					// devices
					ArrayList<String> listaRegistrationID = new ArrayList<String>();

					// add your devices RegisterationID, one for each device
					listaRegistrationID
							.add("APA91bEC0ZwWeWgtaWIIarY8M_1lVN1GGyQuwwWikkuTmtRyibUHuAsiMb4ctfHyQZlkM018hI-BgbPGeZbbbQmDfLScPUdEZp2pEwJPh9LNDS-knetjQHCkgXFNKY5hOKAcRdSdPOR2");
					listaRegistrationID
							.add("APA91bE2w5kK_LTmbm0vUL9VvaXfT5mqdIo9a719K_U18M1bbK2cTbbnQVhMsogxczRpoPEjeyExCkyPI19L1bJz2fBln-k_5yJA3T9-XRBceMyjai9cPYbEKVwBRbEuurpR0ki1LJfP");

					// Use this code for multicast messages
					MulticastResult multicastResult = sender.send(messagem,
							listaRegistrationID, 0);
					sender.send(messagem, listaRegistrationID, 0);
					System.out.println("Message Result: "
							+ multicastResult.toString());// Print multicast
															// message result on
															// console

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
