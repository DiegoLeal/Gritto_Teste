package modelo.dao;

import java.util.List;

import modelo.entidades.CatServico;
import modelo.entidades.Usuario;

public interface UsuarioDao {
	
	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(Integer id);
	Usuario findById(Integer id);
	List<Usuario> findAll();	
	List<Usuario> findByCatServico(CatServico catServico);
	    
	
}
