package se.xperjon.hibernate_demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;

@Entity
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int personalID;
	@Column
	private String namn;
	private String befattning;
	private boolean isCEO;
	@OneToOne
	@JoinColumn(name = "avdelningID")
	private Avdelning avdelning;
	public int getPersonalId() {
		return personalID;
	}
	public void setPersonalId(int personalId) {
		this.personalID = personalId;
	}
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}
	public String getBefattning() {
		return befattning;
	}
	public void setBefattning(String befattning) {
		this.befattning = befattning;
	}
	public Avdelning getAvdelning() {
		return avdelning;
	}
	public void setAvdelning(Avdelning avdelning) {
		this.avdelning = avdelning;
	}
	
	public boolean isCEO() {
		return isCEO;
	}
	public void setCEO(boolean isCEO) {
		this.isCEO = isCEO;
	}
	@Override
	public String toString() {
		return "Personal [personalID=" + personalID + ", namn=" + namn + ", befattning=" + befattning + ", avdelning="
				+ avdelning + "]";
	}
	
}
