package com.vacinasja.model;

import com.sun.istack.NotNull;
import com.vacinasja.model.statecidadao.Estado;
import com.vacinasja.model.statecidadao.NaoHabilitado;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
public class Cidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String endereco;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Column(unique = true)
    private String cartaoSus;

	@NotNull
    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private String telefone;
    private String profissao;

    @ElementCollection
    @CollectionTable(name = "cidadao_comorbidade", joinColumns = @JoinColumn(name = "cidadao_id"))
    @JoinColumn(name = "cidadao_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<String> comorbidade;

    @OneToOne(cascade = CascadeType.ALL)
    private Estado estagioVacinacao;

    @Temporal(TemporalType.DATE)
    private Date diaTomouDose;
    private Integer intervaloDoses;
    private String fabricanteVacina;

    public Cidadao() {

    }

    public Cidadao(String nome, String endereco, String cpf, String cartaoSus, String email, Date dataNascimento, String telefone, String profissao, List<String> comorbidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.cartaoSus = cartaoSus;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.profissao = profissao;
        this.comorbidade = comorbidade;
        this.estagioVacinacao = new NaoHabilitado();
        this.diaTomouDose = null;
        this.intervaloDoses = 0;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setComorbidade(List<String> comorbidade) {
        this.comorbidade = comorbidade;
    }

    public List<String> getComorbidade() {
        return comorbidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public int calculaIdade() {
        return Period.between(dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }

    public void setDiaTomouDose(Date dia) {
        this.diaTomouDose = dia;
    }

    public Date pegaDiaTomouDose() {
        return diaTomouDose;
    }

    public void setIntervaloDoses(Integer intervaloDoses) {
        this.intervaloDoses = intervaloDoses;
    }

    public Integer pegaIntervaloDoses() {
        return intervaloDoses;
    }

    public int pegaIntEstado() {
        return estagioVacinacao.getIntEstado();
    }

    public void setFabricanteVacina(String fabricante) {
        this.fabricanteVacina = fabricante;
    }

    public String pegaFabricanteVacina() {
        return fabricanteVacina;
    }

    public Boolean passarEstagioByIdade(Integer idade) {
        return estagioVacinacao.tentaAlterarByIdade(this, idade);
    }
    
    public Boolean passarEstagioByProfissao(String profissao) {
        return estagioVacinacao.tentaAlterarByProfissao(this, profissao);
    }
    
    public Boolean passarEstagioByComorbidade(String comorbidade) {
        return estagioVacinacao.tentaAlterarByComorbidade(this, comorbidade);
    }
 
    public Boolean passarEstagio(LocalDate data) {
        return estagioVacinacao.tentaAlterar(this, data);
    }

    public Boolean vacinar(Vacina vacina) {
        return estagioVacinacao.tentaAlterar(this, vacina);
    }

    public void setNewEstagioVacinacao(Estado newEstagioVacinacao) {
        this.estagioVacinacao = newEstagioVacinacao;
    }

    public String getEstagioVacinacao() {
        return estagioVacinacao.toString();
    }

    public String getEmail() {
	    	return email;
  	}
    
    public String getNome() {
		    return nome;
  	}

	  public String getCartaoSus() {
        return cartaoSus;
    }
	
  	public Estado getEstadoVacinacao() {
	  	return estagioVacinacao;
  	}
	
}
