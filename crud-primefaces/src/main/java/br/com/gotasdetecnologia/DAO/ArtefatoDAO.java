package br.com.gotasdetecnologia.DAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gotasdetecnologia.conexao.HibernateUtil;

public class ArtefatoDAO<Entidade> {
	
		private Class<Entidade> classe;
		
		private Entidade retornaId;
		
		public Entidade getRetornaId() {
			return retornaId;
		}

		@SuppressWarnings("unchecked")
		public ArtefatoDAO() {
			this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}

		/** Método de SALVAR, recebendo a PESSOA como parametro (<entidade>)*/
		
		public void salvar(Entidade entidade) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction();
				sessao.save(entidade);
				transacao.commit();
				
			} catch (RuntimeException erro) {
				if (transacao != null) {
					transacao.rollback();
				}
				
				throw erro;
			} finally {
				sessao.close();
			}
		}

		/** Método de LISTAR */
		
		@SuppressWarnings("unchecked")
		public List<Entidade> listar() {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			try {
				Criteria consulta = sessao.createCriteria(classe);
				List<Entidade> resultado = consulta.list();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}

		/** Método de EXCLUIR  */
		public void excluir(Entidade entidade) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction();
				sessao.delete(entidade);
				transacao.commit();
			} catch (RuntimeException erro) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close();
			}
		}

		/** Método EDITAR */
		
		public void editar(Entidade entidade) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;

			try {
				transacao = sessao.beginTransaction();
				sessao.update(entidade);
				transacao.commit();
			} catch (RuntimeException erro) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close();
			}
		}

		/** Método INSERIR/ATUALIZAR >> Este método é especial, pois se o formulario for carregado sem dado, ele insere. 
		 * Mas se o formulario for carregado já populado com dados, ele faz update * */
		
		
		@SuppressWarnings("unchecked")
		public Entidade merge(Entidade entidade) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;

			try {
				transacao = sessao.beginTransaction();
				Entidade retorno = (Entidade) sessao.merge(entidade);
				transacao.commit();
				retornaId = retorno;
				return retorno;
			} catch (RuntimeException erro) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close();
			}
		}

		public Class<Entidade> getClasse() {
			return classe;
		}
		public void setClasse(Class<Entidade> classe) {
			this.classe = classe;
		}
		
		
	}
	