package pojo;

public class ServerList implements java.io.Serializable {
	private String IP, mainIP;
	private int port, mainPORT;
	private String objName, mainOBJ;

	public ServerList(String iP, String mainIP, int port, int mainPORT, String objName, String mainOBJ) {
		super();
		this.IP = iP;
		this.mainIP = mainIP;
		this.port = port;
		this.mainPORT = mainPORT;
		this.objName = objName;
		this.mainOBJ = mainOBJ;
	}

	public String getIP() {
		return IP;
	}

	public String getMainIP() {
		return mainIP;
	}

	public String getMainOBJ() {
		return mainOBJ;
	}

	public int getMainPORT() {
		return mainPORT;
	}

	public String getObjName() {
		return objName;
	}

	public int getPort() {
		return port;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public void setMainIP(String mainIP) {
		this.mainIP = mainIP;
	}

	public void setMainOBJ(String mainOBJ) {
		this.mainOBJ = mainOBJ;
	}

	public void setMainPORT(int mainPORT) {
		this.mainPORT = mainPORT;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
