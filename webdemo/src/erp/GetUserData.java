package erp;

public class GetUserData {
	
	private int unitId;
	private int selectImage;
	private String name;
	private String note;
	private  String original;
	private  String requestType;
	private  String serverIp;
    private  String servlet;
    
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
    public int getSelectImage() {
        return selectImage;
    }

    public void setSelectImage(int selectImage) {
        this.selectImage = selectImage;
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
	
	public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
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
		
		return "User [unitId="+unitId+",selectImage="+selectImage+",name="+name+",password="+note+",requestType="+requestType+",serverIp="+serverIp+",servlet="+servlet+"]";
	}

	
	
	
	

}
