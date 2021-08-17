package br.com.gotasdetecnologia.conexao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateGerarTabela implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent evento) {
		HibernateUtil.getConexaoBaseLocal().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent evento) {
		HibernateUtil.getConexaoBaseLocal();
	}

}