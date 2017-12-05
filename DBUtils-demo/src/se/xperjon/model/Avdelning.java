package se.xperjon.model;

public class Avdelning {
	
	private int id;
	private String namn;
	
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
	@Override
	public String toString() {
		return "Avdelning [id=" + id + ", namn=" + namn + "]";
	}		

}
