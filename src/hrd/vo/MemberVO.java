package hrd.vo;

import java.sql.Date;

/**
 * @author myste
 *
 */
public class MemberVO {
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private Date joindate;
	private String sjoindate;	
	//ㄴ 문제에서 가입일자를 구분기호없이 년도4자리월2자리일2자리 20220805 형식 처리하기위해 문자열로 받고 
	//   insert 할때 to_date(문자열,'yyyyMMdd') 로 한다.
	private String grade;
	private String city;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(int custno, String custname, String phone, String address, 
			Date joindate, 
			String sjoindate, 
			String grade,
			String city) {
		this.custno = custno;
		this.custname = custname;
		this.phone = phone;
		this.address = address;
		this.joindate = joindate;
		this.sjoindate=sjoindate;
		this.grade = grade;
		this.city = city;
	}

	//getter 
	public int getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public Date getJoindate() {
		return joindate;
	}

	public String getGrade() {
		return grade;
	}

	public String getCity() {
		return city;
	}
	
	public String getSjoindate() {
		return sjoindate;
	}

	@Override
	public String toString() {
		return "MemberVO [custno=" + custno + ", custname=" + custname + ", phone=" + phone + ", address=" + address
				+ ", joindate=" + joindate + ", sjoindate=" + sjoindate + ", grade=" + grade + ", city=" + city + "]";
	}
	
	
	
}
