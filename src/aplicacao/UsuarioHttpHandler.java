package aplicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.UsuarioController;


public class UsuarioHttpHandler implements HttpHandler {
	
	Logger logger;

	@SuppressWarnings("unused")
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String requestParamValue = null;

		if (null == httpExchange.getRequestMethod()) {

		} else
			switch (httpExchange.getRequestMethod()) {
			case "GET":
				handleGetRequest(httpExchange);
			case "POST":
				handlePostRequest(httpExchange);
			case "PUT":
				handlePutRequest(httpExchange);
			case "DELETE":
				handleDeleteRequest(httpExchange);
			default:
			}
	}

	private void handleGetRequest(HttpExchange httpExchange) throws IOException {
		UsuarioController controller = new UsuarioController();

		String request_uri = httpExchange.getRequestURI().toString();
		OutputStream outStream = httpExchange.getResponseBody();

		JSONObject json;
		int id = 0;

		if (request_uri.split("/").length <= 2) {
			JSONArray json_array = null;
		
		try {
			    httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
		
			    if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
			    httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE");
			    httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
		        httpExchange.sendResponseHeaders(204, -1);		        
		        return;   
          }

			json_array = controller.Index();
			httpExchange.sendResponseHeaders(200, json_array.toString().getBytes().length);
			outStream.write(json_array.toString().getBytes("UTF-8"));

		} catch (IOException e) {
			logger = Logger.getLogger(UsuarioHttpHandler.class.getName());	
			logger.info(e.getMessage());
		}	
			
			outStream.flush();
			outStream.close();

		} else {

			try {

				id = Integer.valueOf(request_uri.split("/")[2]);
				json = controller.Show(id);
				httpExchange.sendResponseHeaders(200, json.toString().length());

			} catch (IOException e) {

				logger = Logger.getLogger(UsuarioHttpHandler.class.getName());	
				logger.info(e.getMessage());
				 //System.out.println(e.getMessage());
				json = new JSONObject();
				json.put("status", "server error");
				httpExchange.sendResponseHeaders(500, json.toString().length());
			}

			outStream.write(json.toString().getBytes());
			outStream.flush();
			outStream.close();
		}
	}

	private void handlePostRequest(HttpExchange httpExchange) throws IOException {
		OutputStream outStream = httpExchange.getResponseBody();
		httpExchange.sendResponseHeaders(200, "teste".length());
		outStream.write("teste".getBytes());

		InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuilder buf = new StringBuilder();

		int b;
		while ((b = br.read()) != -1) {
			buf.append((char) b);
		}
		br.close();
		isr.close();

		System.out.println(buf.toString());
		outStream.flush();
		outStream.close();
	}

	private void handlePutRequest(HttpExchange httpExchange) {
		// TODO implementar
	}

	private void handleDeleteRequest(HttpExchange httpExchange) throws IOException {
		UsuarioController controller = new UsuarioController();

		String request_uri = httpExchange.getRequestURI().toString();
		OutputStream outStream = httpExchange.getResponseBody();

		JSONObject json;
		int id = 0;

		if (request_uri.split("/").length >= 2) {

			try {

				id = Integer.valueOf(request_uri.split("/")[2]);
				controller.Delete(id);

				json = new JSONObject();
				json.put("status", "removido");
				httpExchange.sendResponseHeaders(200, json.toString().length());
				outStream.write(json.toString().getBytes());

			} catch (Exception e) {

				json = new JSONObject();
				json.put("status", "not found");
				outStream.write(json.toString().getBytes());
				httpExchange.sendResponseHeaders(404, json.toString().length());
				outStream.write(json.toString().getBytes());
				logger = Logger.getLogger(UsuarioHttpHandler.class.getName());	
				logger.info(e.getMessage());
			}

		} else {

			json = new JSONObject();
			json.put("status", "erro no servidor");
			outStream.write(json.toString().getBytes());
			httpExchange.sendResponseHeaders(500, json.toString().length());
			outStream.write(json.toString().getBytes());
		}

		outStream.flush();
		outStream.close();

	}
}