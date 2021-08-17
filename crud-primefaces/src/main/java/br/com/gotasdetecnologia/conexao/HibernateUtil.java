package br.com.gotasdetecnologia.conexao;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory conexaoBaseLocal;

	public static Connection getConexao() {
		Session sessao = conexaoBaseLocal.openSession();

		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
@Override
public Connection execute(Connection conn) throws SQLException {
return conn;
}
});
		return conexao;
	}

	static {
		try {
			Configuration configuration1 = new Configuration();
			configuration1.configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry1 = new StandardServiceRegistryBuilder()
					.applySettings(configuration1.getProperties()).build();

			conexaoBaseLocal = configuration1.buildSessionFactory(serviceRegistry1);

		} catch (Throwable ex) {
			System.err.println("Falha inicial na SessionFactory." + ex);
			throw new ExceptionInInitializerError("Erro gerado: " + ex.getMessage());
		}
	}

	public static SessionFactory getConexaoBaseLocal() {
		return conexaoBaseLocal;
	}

}

//public class HibernateUtil {
//
//	private static final SessionFactory sessionFactory = buildSessionFactory();
//
//	private static SessionFactory buildSessionFactory() {
//		try {
//			Configuration configuration = new Configuration();
//			configuration.configure();
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//					.applySettings(configuration.getProperties()).build();
//			
//			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//			
//			return sessionFactory;
//			
//		} catch (Throwable ex) {
//			System.err.print("Falha ao tentar criar o SessionFactory." + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//}
