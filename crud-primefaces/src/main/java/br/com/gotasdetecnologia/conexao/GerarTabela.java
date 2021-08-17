package br.com.gotasdetecnologia.conexao;

public class GerarTabela {
	public static void main (String[]args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
		
		
				
	}

}
