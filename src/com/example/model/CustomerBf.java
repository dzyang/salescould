package com.example.model;

public class CustomerBf {
  
    public Integer getId() {
		return id;
	}
	public Integer getUserid() {
		return userid;
	}
	public Integer getCompanycode() {
		return companycode;
	}
	public String getCustomerid() {
		return customerid;
	}
	public String getLnglat() {
		return lnglat;
	}
	public String getPic() {
		return pic;
	}
	public String getBz() {
		return bz;
	}
	public String getAddDate() {
		return addDate;
	}
	private Integer id;
    private Integer userid;
    private Integer companycode;
    private String customerid;
    private String lnglat;
    private String pic;
    private String bz;
    private String addDate;

    
   
    public CustomerBf(Integer id, Integer userid, Integer companycode,
			String customerid, String lnglat, String pic, String bz,
			String addDate) {
		super();
		this.id = id;
		this.userid = userid;
		this.companycode = companycode;
		this.customerid = customerid;
		this.lnglat = lnglat;
		this.pic = pic;
		this.bz = bz;
		this.addDate = addDate;
	}

	
}