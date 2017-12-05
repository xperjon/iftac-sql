package se.xperjon.hibernate_demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Avdelning {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int avdelningID;
	private String namn;
	public int getAvdelningId() {
		return avdelningID;
	}
	public void setAvdelningId(int avdelningID) {
		this.avdelningID = avdelningID;
	}
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}
	@Override
	public String toString() {
		return "Avdelning [avdelningID=" + avdelningID + ", namn=" + namn + "]";
	}
}
