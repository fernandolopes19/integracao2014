package br.ufg.inf.fs.es.integracao.sna_servidor;

import java.sql.*;

public class ConexaoBD {

	private String nome_Tabela;

	Connection c = null;
	Statement stmt = null;

	public String getTabela() {
		return nome_Tabela;
	}

	public void setTabela(String nomeTabela) {
		this.nome_Tabela = nomeTabela;
	}

	public void criarConexao() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sna.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void criarTabela() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE " + getTabela() + " "
					+ "(API_KEY TEXT PRIMARY KEY     NOT NULL,"
					+ " ID_DISPOSITIVO_GCM            TEXT     NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserirDado(String apiKey, String idDispositivoGCM) {
		try {
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO " + getTabela()
					+ " (API_KEY,ID_DISPOSITIVO_GCM) "
					+ "VALUES (" + "'" + apiKey + "'" + ", " + "'"
					+ idDispositivoGCM + "'" + " );";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public String buscarIdDispositivo(){
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + getTabela());
			String idDispositivoGCM = "";
			while(rs.next()){
				idDispositivoGCM = rs.getString("ID_DISPOSITIVO_GCM");
			}
			rs.close();
			stmt.close();
			return idDispositivoGCM;
		} catch (SQLException e) {
			return null;
		}
	}

}
