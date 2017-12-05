package se.xperjon.model;

public class Personal {
	
	private int personalId;
	private String namn;
	private String befattning;
	private Avdelning avdelning;


	public int getPersonalId() {
		return personalId;
	}
	public void setPersonalId(int personalId) {
		this.personalId = personalId;
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
	@Override
	public String toString() {
		return "Personal [personalId=" + personalId + ", namn=" + namn + ", befattning=" + befattning + ", avdelning="
				+ avdelning + "]";
	}
		


}
