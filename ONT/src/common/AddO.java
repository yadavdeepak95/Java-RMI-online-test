package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pojo.ServerList;



public interface AddO extends Remote {
		public Boolean add(String IP, String mainIP, int port , int mainPORT, String objName, String mainOBJ) throws RemoteException;
		public ServerList giveServer() throws RemoteException;
		public Boolean status() throws RemoteException;

}
