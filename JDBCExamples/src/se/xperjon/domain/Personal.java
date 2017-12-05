package se.xperjon.domain;

public class Personal {
	
	private int id;
	private String namn;
	private String befattning;
	private Avdelning avdelning;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Personal [id=" + id + ", namn=" + namn + ", befattning=" + befattning + ", avdelning=" + avdelning
				+ "]";
	}		


}
