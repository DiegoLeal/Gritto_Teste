package aplicacao;

import java.util.List;
import java.util.Scanner;

import modelo.dao.CatServicoDao;
import modelo.dao.DaoFactory;
import modelo.entidades.CatServico;

public class Programa2 {

	public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
		
		CatServicoDao catServicoDao = DaoFactory.createCatServicoDao();

		System.out.println("=== TEST 1: findById =======");
		CatServico prof = catServicoDao.findById(1);
		System.out.println(prof);
		
		System.out.println("\n=== TEST 2: findAll =======");
		List<CatServico> list = catServicoDao.findAll();
		for (CatServico d : list) {
			System.out.println(d);
		}

		//System.out.println("\n=== TEST 3: insert =======");
		//CatServico newProfissao = new CatServico(2, "Eletricista");
		//catServicoDao.insert(newProfissao);
		//System.out.println("Inserido com Sucesso! Novo ID = " + newProfissao.getId());

		//System.out.println("\n=== TEST 4: update =======");
		//CatServico prof2 = catServicoDao.findById(2);
		//prof2.setNome("Eletricista");
		//catServicoDao.update(prof2);
		//System.out.println("Update efetuado com sucesso! ");
				
		
		//System.out.println("\n=== TEST 5: delete =======");
		//System.out.print("Insira o ID a ser excluido: ");
		//int id = sc.nextInt();
		//catServicoDao.deleteById(id);
		//System.out.println("ID Deletado com sucesso! ");

		sc.close();

	}

}
