package erp;

public class GetUserData {
	
	private int unitId;
	private String name;
	private String note;
    private String address;
    private String phone;
	private String fax;
    private int category_id;
    private String category_name;
	private  int classType;
	private  String requestType;
	private  String serverIp;
    private  String servlet;
    
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	
	public String getNote()
	{
		return note;
	}
	
	public void setNote(String note)
	{
		this.note=note;
	}
	 public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getFax() {
	        return fax;
	    }

	    public void setFax(String fax) {
	        this.fax = fax;
	    }

	    public int getCategory_id() {
	        return category_id;
	    }

	    public void setCategory(int category_id) {
	        this.category_id = category_id;
	    }
	    
	    public String getCategory_name() {
	        return category_name;
	    }

	    public void setCategory_name(String category_name) {
	        this.category_name = category_name;
	    }
	
	public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }
	
	public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServlet() {
        return servlet;
    }

    public void setServlet(String servlet) {
        this.servlet = servlet;
    }
	
	

	
	
	

}
