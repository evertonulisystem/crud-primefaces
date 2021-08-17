package br.com.gotasdetecnologia.DAO;

import br.com.gotasdetecnologia.conexao.HibernateUtil;
import br.com.gotasdetecnologia.domain.Pessoa;
import br.com.gotasdetecnologia.enuns.SituacaoEnum;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
//import org.hibernate.Query;



public class PessoaDAO extends ArtefatoDAO<Pessoa> implements Serializable{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Pessoa> pesquisaPessoaPorStatus(SituacaoEnum situacaoEnum) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {

			String hql = "FROM Pessoa p WHERE p.situacao = :situacaoEnum order by p.nome asc";
			org.hibernate.Query query = sessao.createQuery(hql);
			query.setParameter("situacaoEnum", situacaoEnum);
			return query.list();
			
		} catch (RuntimeException erro) {
		throw erro;
		} finally {
			sessao.close();
		}
	
	}
	//@SuppressWarnings("unchecked")
//	public List<PessoaContato> listarContato(Long idPessoa) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//
//			String hql = "from PessoaContato pc where pc.pessoa.id = :idPessoa order by pc.id asc";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("idPessoa", idPessoa);
//
//			return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
}