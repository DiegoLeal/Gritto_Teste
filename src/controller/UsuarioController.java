package controller;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modelo.dao.DaoFactory;
import modelo.dao.UsuarioDao;
import modelo.entidades.Usuario;

public class UsuarioController {
	
	
	
	Usuario Usuario = new Usuario();
	UsuarioDao dao = DaoFactory.createUsuarioDao();
	
	

	private Usuario jsonToUsuario(JSONObject json) {
		
		Usuario usuario = new Usuario();
		

		try {
			
			usuario.setId(json.getInt("id"));			
			usuario.setNome(json.getString("usuario"));	
			
									
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	private JSONObject usuarioToJson(Usuario usuario) {
		
		JSONObject json = new JSONObject();

		try {
			
			json.put("id", usuario.getId());
			json.put("nome", usuario.getNome());
			json.put("rg", usuario.getRg());
			json.put("cpf", usuario.getCpf());
			json.put("dataNascimento", usuario.getDataNascimento());
			json.put("telefone", usuario.getTelefone());
			json.put("senha", usuario.getSenha());
			json.put("email", usuario.getEmail());
			json.put("sexo", usuario.getSexo());			
			//json.put("catServico", usuario.getCatServico());
						
			return json;
			
		} catch (JSONException e) {

			json.put("erro", e.getMessage());
			return json;
		}
	}

	public JSONObject Create(JSONObject json) throws SQLException {
		
		Usuario = jsonToUsuario(json);
		dao.insert(Usuario);

		return usuarioToJson(Usuario);
	}

	public JSONObject Show(Integer id) {

		try {

			Usuario = dao.findById(id);
			return usuarioToJson(Usuario);

		} catch (JSONException e) {
			JSONObject json = new JSONObject();

			json.put("erro", e.getMessage());
			return json;

		}
	}

	public JSONObject Edit(JSONObject json) throws SQLException {
		
		Usuario = jsonToUsuario(json);
		dao.update(Usuario);

		return usuarioToJson(Usuario);
	}

	public JSONArray Index() {
		try {

            List<Usuario> usuarios = dao.findAll();
            JSONArray json = new JSONArray();

            usuarios = dao.findAll();
            usuarios.forEach(usuario -> {
                json.put(usuarioToJson(usuario));
            });

            return json;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
	}

	public JSONObject Delete(Integer id) {

		try {
			
			dao.deleteById(id);
			return usuarioToJson(Usuario);

		} catch (Exception e) {
			JSONObject json = new JSONObject();
			json.put("Erro", e.getMessage());
			return json;
		}
	}

}
