package server;

import java.rmi.RemoteException;

import common.AddO;
import pojo.ServerList;

public class ServerToServerImpl  implements AddO{

	
	@Override
	public Boolean add(String IP, String mainIP, int port, int mainPORT, String objName, String mainOBJ)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerList giveServer() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean status() throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

}
