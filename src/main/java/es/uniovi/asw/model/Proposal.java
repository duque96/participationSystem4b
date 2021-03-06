package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TPROPUESTA")
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String content;

	@OneToMany(mappedBy = "proposal", fetch = FetchType.EAGER)
	private Set<Commentary> comments = new HashSet<Commentary>();

	@NotNull
	private int valoration;

	@NotNull
	private int minVotes;

	@Enumerated(EnumType.STRING)
	private EstadosPropuesta status;
	
	@OneToMany(mappedBy = "proposal", fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<Vote>();

	public Proposal() {	}

	public Proposal(String name, String content, int minVotes) {
		this.name = name;
		this.content = content;
		this.minVotes = minVotes;
		this.status = EstadosPropuesta.EnTramite;
		this.valoration = 0;
	}

	public EstadosPropuesta getStatus() {
		return status;
	}

	public void setStatus(EstadosPropuesta status) {
		this.status = status;
	}

	public void restoreEstadoPropuesta() {
		status = EstadosPropuesta.EnTramite;
	}

	public void refuseProposal() {
		status = EstadosPropuesta.Rechazada;
	}

	public void acceptProposal() {
		status = EstadosPropuesta.Aceptada;
	}

	public void cancelProposal() {
		status = EstadosPropuesta.Anulada;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Set<Commentary> getComments() {
		return new HashSet<Commentary>(comments);
	}

	public Set<Commentary> _getComments() {
		return comments;
	}

	public void setComments(Set<Commentary> comments) {
		this.comments = comments;
	}

	public int getValoration() {
		return valoration;
	}

	public int getMinVotes() {
		return minVotes;
	}

	public void setMinVotes(int minVotes) {
		this.minVotes = minVotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void positiveVote() {
		this.valoration++;
	}

	public void negativeVote() {
		this.valoration--;
	}

	public void insertComment(Commentary comment) {
		this.comments.add(comment);
	}

	public Set<Vote> getVotes() {
		return new HashSet<Vote>(votes);
	}
	
	public Set<Vote> _getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		String cadena = "La propuesta: '" + name + "' tiene un total de "
				+ valoration + " votos y " + comments.size() + " comments.\n";
		return cadena;
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
		Proposal other = (Proposal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

}
