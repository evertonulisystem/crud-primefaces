package br.com.gotasdetecnologia.enuns;

public enum SituacaoEnum {
	ATIVO("ATIVO"),
	INATIVO ("INATIVO");
	
	private String descricao;
	
	SituacaoEnum (String descricao){
		this.descricao = descricao;
	}

	
	public String getDescricao() {
		return descricao;
	}
}
