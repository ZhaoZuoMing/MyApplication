package f.sky.flight.model;

import java.io.Serializable;

public class SortModel implements Serializable{

	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private String AirPortCode;
	private String CityCode;
	private String EngName;
	private String Field1;
	private String Field2;//是否热门
	private String Field3;
	private String Remark;
	private String no;
	private String jp;
	private String py;
	private String hEnterT;
	private String hOutT;

	private int id;
	private String code;
	private int isHot;
	private String eName;
	private int country;
	private int province;
	private String airport;
	private int status;
	private int sortIndex;

	public String gethEnterT() {
		return hEnterT;
	}

	public void sethEnterT(String hEnterT) {
		this.hEnterT = hEnterT;
	}

	public String gethOutT() {
		return hOutT;
	}

	public void sethOutT(String hOutT) {
		this.hOutT = hOutT;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getAirPortCode() {
		return AirPortCode;
	}

	public void setAirPortCode(String airPortCode) {
		AirPortCode = airPortCode;
	}

	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}

	public String getEngName() {
		return EngName;
	}

	public void setEngName(String engName) {
		EngName = engName;
	}

	public String getField1() {
		return Field1;
	}

	public void setField1(String field1) {
		Field1 = field1;
	}

	public String getField2() {
		return Field2;
	}

	public void setField2(String field2) {
		Field2 = field2;
	}

	public String getField3() {
		return Field3;
	}

	public void setField3(String field3) {
		Field3 = field3;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}


}
