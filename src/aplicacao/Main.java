package aplicacao;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

public class Main {
	
	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
			server.createContext("/usuarios", new UsuarioHttpHandler());
			server.setExecutor(threadPoolExecutor);
			server.start();
			Logger logger = Logger.getLogger(Main.class.getName());
			logger.info("Server started on port 8080");
			
		} catch (IOException ex) {
			Logger logger = Logger.getLogger(Main.class.getName());
			logger.info(ex.getMessage());
			
		}
	}

}