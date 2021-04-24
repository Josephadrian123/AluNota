package br.edu.ifpb.jaas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="aluno")
public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	
	@Column
	private Integer faltas;
	
	@Column
	private BigDecimal notaUm;
	
	@Column
	private BigDecimal notaDois;
	
	@Column
	private BigDecimal notaTres;
	
	@Column
	private BigDecimal notaFinal;
	
	@Column
	private Situacao situacao;
	
	
	
	
	
	public Aluno() {
		
	}
	
	public Aluno(String nome, Date dataNasc, Integer faltas, BigDecimal notaUm, BigDecimal notaDois,
			BigDecimal notaTres, BigDecimal notaFinal, Situacao situacao) {
		super();
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.faltas = faltas;
		this.notaUm = notaUm;
		this.notaDois = notaDois;
		this.notaTres = notaTres;
		this.notaFinal = notaFinal;
		this.situacao = situacao;
		
	}



	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public Integer getFaltas() {
		return faltas;
	}
	
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}
	
	public BigDecimal getNotaUm() {
		return notaUm;
	}
	
	public void setNotaUm(BigDecimal notaUm) {
		this.notaUm = notaUm;
	}
	
	public BigDecimal getNotaDois() {
		return notaDois;
	}
	
	public void setNotaDois(BigDecimal notaDois) {
		this.notaDois = notaDois;
	}
	
	public BigDecimal getNotaTres() {
		return notaTres;
	}
	
	public void setNotaTres(BigDecimal notaTres) {
		this.notaTres = notaTres;
	}
	
	public BigDecimal getNotaFinal() {
		return notaFinal;
	}
	
	public void setNotaFinal(BigDecimal notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	public Situacao getSituacao() {
		
		if(this.notaUm != null && this.notaDois != null && this.notaTres != null && this.faltas != null && this.notaFinal != null) {
			if((this.mediaNotas().multiply(new BigDecimal(60)).add(this.notaFinal.multiply(new BigDecimal(40))).divide(new BigDecimal(100))).compareTo(new BigDecimal(50)) >= 0) {
				if(this.faltas < 25) {
				this.situacao = Situacao.AP;
				}else this.situacao = Situacao.RF;
				if(this.mediaNotas().compareTo(new BigDecimal(40)) < 0) {
					this.situacao = Situacao.RP;
				}
			}else{
				this.situacao = Situacao.RP;
			}
		}else if(this.notaUm != null && this.notaDois != null && this.notaTres != null && this.faltas != null) {
			if(this.mediaNotas().compareTo(new BigDecimal(40)) < 0) {
				this.situacao = Situacao.RP;
			}else if(this.mediaNotas().compareTo(new BigDecimal(70)) < 0 && this.faltas < 25) {
				this.situacao = Situacao.FN;
			}else if(this.faltas < 25) {
				this.situacao = Situacao.AP;
			}else this.situacao = Situacao.RF;
		}else this.situacao = Situacao.MT;
		
		
		return this.situacao;
	}
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal mediaNotas() {

		List<BigDecimal> notas = new ArrayList<BigDecimal>();
		if(this.notaUm != null) {
			notas.add(notaUm);
		}
		if(this.notaDois != null) {
			notas.add(notaDois);
		}
		if(this.notaTres != null) {
			notas.add(notaTres);
		}
		
		
		if(notas.size() == 3) {
			BigDecimal sum = notas.stream()
		        .map(Objects::requireNonNull)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
		    return sum.divide(new BigDecimal(notas.size()), RoundingMode.CEILING);
		}
		
		return null;
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", dataNasc=" + dataNasc + ", faltas=" + faltas + ", notaUm=" + notaUm
				+ ", notaDois=" + notaDois + ", notaTres=" + notaTres + ", notaFinal=" + notaFinal + ", situacao="
				+ situacao + "]";
	}

	
	
	
	
}
