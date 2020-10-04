package modelo.dao;

import db.DB;
import modelo.dao.impl.CatServicoDaoJDBC;
import modelo.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}

	public static CatServicoDao createCatServicoDao() {		
		return new CatServicoDaoJDBC(DB.getConnection());
	}
}
