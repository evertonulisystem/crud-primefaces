package br.com.gotasdetecnologia.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;

import br.com.gotasdetecnologia.DAO.PessoaDAO;
import br.com.gotasdetecnologia.domain.Pessoa;
import br.com.gotasdetecnologia.enuns.SituacaoEnum;

	@ManagedBean
	@ViewScoped
	public class PessoaController implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private SituacaoEnum situacaoEnum;
		private List<SituacaoEnum> listaSituacaoEnum = Arrays.asList(SituacaoEnum.values());
		
		private Pessoa pessoa;
		private List<Pessoa> listaPessoa;
		private PessoaDAO pessoaDAO = new PessoaDAO();
		
		@PostConstruct
		public void listarPessoa() {
			listaPessoa = pessoaDAO.listar();
		}

//		public void novaPessoa() {
//			pessoa = new Pessoa();
//			
//			listaContato = new ArrayList<>();
//		}

		public void salvarPessoa() {
			try {

				if (pessoa.getId() == null) {
					pessoaDAO.merge(pessoa);
					//for (PessoaContato contatoTemp : listaContato) {
					//	contatoTemp.setPessoa(pessoaDAO.getRetornaId());
					//	contatoDAO.merge(contatoTemp);
					//}
				} else {
					pessoaDAO.merge(pessoa);
				}

				this.listarPessoa();
				//MsgFeedBackUsuario.AdicionaMensagemSucesso("Pessoa Gravada com Sucesso!");
				
				PrimeFaces.current().executeScript("PF('dlgPessoa').hide();");

			} catch (RuntimeException erro) {
				//MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
				erro.printStackTrace();
			}
		}
}
