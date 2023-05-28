package logic;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Item {
	private String item_no;
	@NotEmpty(message ="상품명을 입력하세요")
	private String item_name;
	@Min(value = 10,message = "10원 이상 가능합니다")
	@Max(value = 1000000,message = "1000000원 이하만 가능합니다")
	private int item_price;
	
	@NotEmpty(message = "상품 설명을 입력하세요")
	@Size(min = 10, max = 1000 ,message = "10자이상 1000자 이하만 가능합니다.")
	private String item_content;
	private String item_picture;
	private MultipartFile picture ; //업로드 된 파일의 내용 저장
	private String item_code;
	private int item_state;
	private int sellCnt;
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public String getItem_content() {
		return item_content;
	}
	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	public String getItem_picture() {
		return item_picture;
	}
	public void setItem_picture(String item_picture) {
		this.item_picture = item_picture;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public int getItem_state() {
		return item_state;
	}
	public void setItem_state(int item_state) {
		this.item_state = item_state;
	}
	public int getSellCnt() {
		return sellCnt;
	}
	public void setSellCnt(int sellCnt) {
		this.sellCnt = sellCnt;
	}
	@Override
	public String toString() {
		return "Item [item_no=" + item_no + ", item_name=" + item_name + ", item_price=" + item_price
				+ ", item_content=" + item_content + ", item_picture=" + item_picture + ", picture=" + picture
				+ ", item_code=" + item_code + ", item_state=" + item_state + ", sellCnt=" + sellCnt + "]";
	}
	
}
