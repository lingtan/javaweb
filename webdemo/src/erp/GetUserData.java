package erp;

public class GetUserData {
	
	private int unitId;
	private String name;
	private String note;
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
	
	

	@Override
	public String toString() {
		
		return "User [unitId="+unitId+",name="+name+",note="+note+",classType="+classType+",requestType="+requestType+",serverIp="+serverIp+",servlet="+servlet+"]";
	}

	
	
	
	

}
