package br.ufg.inf.fs.es.integracao.sna_servidor;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.util.Scanner;

public class EnviaMensagem {

	private static final String ID_DISPOSITIVO_GCM = "APA91bHRucYH4KQOWp_h5t7nRtBbUxT5Z_lTCgFy6D9twUGWEy1A6ZPMvWFE94ANc-QrhW2bCqx0Xspc1Qa791JfciS7puUhzEw93d4TT2PZm2Z_cQXhlkGz0zyuoP2QOmxf7n-i708s4jbRZSUaecFf2b9sgbet1FwyY41LfGNlEvCPrE25nl0";
	private static final String API_KEY = "AIzaSyBBtaDqKKDZMyohPLboAU0polwE3j93VlY";

	static final int TIME_TO_LIVE = 86400; // 86.400 segundos = 1 dia
	static final int TAMANHO_MENSAGEM = 1024; // 1024 caracteres = 2Kb 

	public static void main(String[] args) {

		ConexaoBD conexaoBD = new ConexaoBD();
		conexaoBD.criarConexao();
		conexaoBD.setTabela("SNA");
		conexaoBD.criarTabela();
		conexaoBD.inserirDado(API_KEY, ID_DISPOSITIVO_GCM);

		Scanner scx = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);

		String idDispositivoGCM = ID_DISPOSITIVO_GCM;
		String apiKey = API_KEY;

		int opcaoMenu1;
		int opcaoMenu2;
		String mensagemParaEnviar;
		do {

			System.out.println("O que você deseja fazer:"
					+ "\n1:Enviar mensagem" + "\n2:Mudar configuração"
					+ "\n0:Sair");
			opcaoMenu1 = sc.nextInt();

			switch (opcaoMenu1) {
			case 1:
				System.out.println("Digite seu nome: ");
				mensagemParaEnviar = scx.nextLine();
				mensagemParaEnviar += "\n";
				System.out.println("Digite a mensagem: ");
				mensagemParaEnviar += scx.nextLine();

				if (mensagemParaEnviar.length() < TAMANHO_MENSAGEM) {
					Sender sender = new Sender(apiKey);

					Message messagemGCM = new Message.Builder()
							.collapseKey("1").timeToLive(TIME_TO_LIVE)
							.delayWhileIdle(true)
							.addData("mensagem", mensagemParaEnviar).build();

					Result resultado = null;

					// Envia a mensagem completa para o dispositivo
					try {
						resultado = sender.send(messagemGCM, idDispositivoGCM,
								1);
						System.out.println("Enviou");
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (resultado != null) {
						System.out.println(resultado.toString());
					}

					break;
				} else{
					System.out.println("Mensagem muito grande.");
					break;
				}

			case 2:
				System.out.println("O que deseja fazer:"
						+ "\n1:Inserir novos dados"
						+ "\n2:Buscar ID Dispositivo" + "\n0:Sair");
				opcaoMenu2 = sc.nextInt();

				if (opcaoMenu2 == 1) {
					System.out.println("Digite API KEY: ");
					apiKey = scx.nextLine();
					System.out.println("Digite ID Dispositivo: ");
					idDispositivoGCM = scx.nextLine();

					conexaoBD.inserirDado(apiKey, idDispositivoGCM);
					break;
				} else if (opcaoMenu2 == 2) {
					idDispositivoGCM = conexaoBD.buscarIdDispositivo();
					System.out.println(idDispositivoGCM);
					break;
				}
			default:
				System.out.println("Digite novamente.");
			}

		} while (opcaoMenu1 != 0);

	}
}
