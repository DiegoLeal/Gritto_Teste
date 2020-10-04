package aplicacao;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import modelo.dao.DaoFactory;
import modelo.dao.UsuarioDao;
import modelo.entidades.CatServico;
import modelo.entidades.Usuario;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
				
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		
		System.out.println("=== TEST 1: usuario findById ===");
		Usuario usuario = usuarioDao.findById(5);		
		System.out.println(usuario);
		
		System.out.println("\n=== TEST 2: usuario findByCatServico ===");
		CatServico catServico = new CatServico(0, null);
		List<Usuario> list = usuarioDao.findByCatServico(catServico);
		for (Usuario obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: usuario findAll ===");		
		list = usuarioDao.findAll();
		for (Usuario obj : list) {
			System.out.println(obj);
		}	
		
		System.out.println("\n=== TEST 4: usuario insert ===");
		Usuario newUsuario = new Usuario(null, "Manuela Lima", "26.750.460-3", "553.172.095-52", new Date(1988/11/13), "(81) 99847-7128", "gya38NJDHs", "manuelalimas@outlook.com", "F", catServico);
		usuarioDao.insert(newUsuario);
		System.out.println("Inserido com Sucesso! Novo id = " + newUsuario.getId());
		
	    //System.out.println("\n=== TEST 5: usuario update ===");
		//usuario = usuarioDao.findById(5);
		//usuario.setEmail("franciscolopes@gmail.com");
		//usuarioDao.update(usuario);
		//System.out.println("Update efetuado com sucesso! ");
	
		
		//System.out.println("\n=== TEST 6: usuario delete ===");	
	    //System.out.println("Insira o ID a ser deletado");
	   // int id = sc.nextInt();
	    //usuarioDao.deleteById(id);
	   // System.out.println("ID Deletado com sucesso! ");
	   
	    sc.close();
	}

}
