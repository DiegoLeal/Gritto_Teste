package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// Classe para conectar e desconectar com o Banco de Dados

public class DB {

	//Conexão com banco de dados do jdbc 	
	private static Connection conn = null;
	
	//Método para conectar com banco de dados instanciando um obj do tipo Connection
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {  //Tratamento de mensagem de exceção personalizada criada dentro de package DbException
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	// Método para fechar a conexão
	public static void closeConnection() {
		if (conn != null) {  //Testando se a conexão esta instanciada
			try {
				conn.close();
			} catch (SQLException e) {  //Tratamento de mensagem de exceção personalizada criada dentro de package DbException
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	// Lendo os dados da pasta db.properties
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs); //load faz a leitura do props apontado pelo imputstreame fs e guarda os dados dentro do obj props 
			return props;
		}
		catch (IOException e) { //Tratamento de mensagem de exceção personalizada criada dentro de package DbException
			throw new DbException(e.getMessage());
		}
	}
	
	//Método para fechar Statement st
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Método para fechar ResultSet rs
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}