package logic;

public class Shelter {
	private String shelter_no;
	private String shelter_name;
	private String shelter_address;
	private String shelter_tel;
	private String hap;
	
	public String getHap() {
		return hap;
	}
	public void setHap(String hap) {
		this.hap = hap;
	}
	public String getShelter_no() {
		return shelter_no;
	}
	public void setShelter_no(String shelter_no) {
		this.shelter_no = shelter_no;
	}
	public String getShelter_name() {
		return shelter_name;
	}
	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}
	public String getShelter_address() {
		return shelter_address;
	}
	public void setShelter_address(String shelter_address) {
		this.shelter_address = shelter_address;
	}
	public String getShelter_tel() {
		return shelter_tel;
	}
	public void setShelter_tel(String shelter_tel) {
		this.shelter_tel = shelter_tel;
	}
	
	@Override
	public String toString() {
		return "Shelter [shelter_no=" + shelter_no + ", shelter_name=" + shelter_name + ", shelter_address="
				+ shelter_address + ", shelter_tel=" + shelter_tel + ", hap=" + hap + "]";
	}
}