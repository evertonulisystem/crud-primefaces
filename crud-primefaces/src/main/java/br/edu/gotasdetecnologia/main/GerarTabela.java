package br.edu.gotasdetecnologia.main;

import br.com.gotasdetecnologia.conexao.HibernateUtil;

public class GerarTabela {

	public static void main(String[] args) {
	
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
		

	}

}
