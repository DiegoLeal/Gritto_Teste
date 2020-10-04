package aplicacao;

import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import controller.UsuarioController;
import modelo.dao.CatServicoDao;
import modelo.dao.DaoFactory;
import modelo.dao.UsuarioDao;
import modelo.entidades.CatServico;
import modelo.entidades.Usuario;

public class UsuarioTeste {
	static Scanner scan = new Scanner(System.in).useDelimiter("\r");
	static Scanner scanInt = new Scanner(System.in);

	static UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
	static Usuario usuario = new Usuario();

	static CatServicoDao catServicoDao = DaoFactory.createCatServicoDao();
	static CatServico catServico = new CatServico();
	
	static UsuarioController controller = new UsuarioController();

	static JSONObject json = new JSONObject();

	public static void main(String[] args) {

		System.out.println("[Operações...]");
		System.out.println("[1 = Insert..]");
		System.out.println("[2 = Delete..]");
		System.out.println("[3 = FindById]");
		System.out.println("[4 = FindAll.]");
		System.out.println("[5 = Update..]");
		System.out.print("Insira o opção desejada: ");
		int e = scanInt.nextInt();

		Escolha(e);

	}

	private static void Insert(int i) {

		switch (i) {

		case 1:
			
			try {

				System.out.print("Digite o Usuario: ");
				String newUsuario = scan.next();

				usuario.setNome(newUsuario);
				usuarioDao.insert(usuario);

				System.out.println("Cadastro efetuado");
				break;

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}	
		
	private static void FindById(int i) {
		switch (i) {
		case 1:
			
			try {

				System.out.println("Digite o id do Usuario: ");
				int id = scanInt.nextInt();

				usuario = usuarioDao.findById(id);
				System.out.println(usuario);
				break;

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}	
		
	private static void Delete(int i) {

		switch (i) {
		case 1:
			
			try {
				System.out.println("Digite o id do Usuario: ");
				int id = scanInt.nextInt();

				usuarioDao.deleteById(id);
				System.out.println("Deletado com sucesso!");
				break;

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}	

	private static void FindAll(int i) {

		switch (i) {
		case 1:
		
			try {

				List<Usuario> list = usuarioDao.findAll();
				System.out.println(list);
				break;

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private static void Update(int i) {

		switch (i) {
		case 1:
			
			
			try {

				System.out.println("Digite o id do Usuario: ");
				int id = scanInt.nextInt();

				System.out.println("Digite o Usuario atualizado: ");
				String newUsuario = scan.next();

				usuario.setId(id);
				usuario.setNome(newUsuario);
				usuarioDao.update(usuario);

				System.out.println("Usuario atualizado com sucesso!");
				break;

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private static void Escolha(int e) {

		switch (e) {

		case 1:
			System.out.println("[Inserir...]");
			System.out.println("[1 Usuario.]");			
			int i = scanInt.nextInt();

			if (i == 1) {
				Insert(1);
				break;
			} else {
				System.out.println("Nenhuma opção selecionada!");
			}
			break;
		case 2:
			System.out.println("[Deletar...]");
			System.out.println("[1 Usuario.]");			
			int i1 = scanInt.nextInt();

			if (i1 == 1) {
				Delete(1);
				break;
			} else {
				System.out.println("Nenhuma opção selecionada!");
			}
			break;
		case 3:
			System.out.println("[FindById...]");
			System.out.println("[1 Usuario..]");			
			int i2 = scanInt.nextInt();

			if (i2 == 1) {
				FindById(1);
				break;
			} else {
				System.out.println("Nenhuma opção selecionada!");
			}
			break;
		case 4:
			System.out.println("[FindAll...]");
			System.out.println("[1 Usuario.]");			
			int i3 = scanInt.nextInt();

			if (i3 == 1) {
				FindAll(1);
				break;
			} else {
				System.out.println("Nenhuma opção selecionada!");
			}
			break;
		case 5:
			System.out.println("[Update....]");
			System.out.println("[1 Usuario.]");			
			int i4 = scanInt.nextInt();

			if (i4 == 1) {
				Update(1);
				break;
			} else {
				System.out.println("Nenhuma opção selecionada!");
			}
			break;
		default:
			break;
		}
	}
}