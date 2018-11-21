package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Constants.DataContants;
import clientswing.MainFrame;
import common.AddI;
import common.AddO;
import pojo.ServerList;

public class Client {

	private static AddI serverObj;

	public static void main(String[] args) throws RemoteException {
		boolean serverflag = true;
		boolean server1flag = false; // false means no error true means error
		ServerList serving = null;

		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		System.setSecurityManager(cSM);
		AddO server1 = null;
		try {
			Registry registry = LocateRegistry.getRegistry(DataContants.server1, DataContants.server1Port);
			server1 = (AddO) registry.lookup(DataContants.server1stub);
			serving = server1.giveServer();
		} catch (Exception e) {
			System.err.println("ERROR : Not able to Connect to server 1");
			server1flag = true;
		}
		AddO server2 = null;
		try {
			Registry registry = LocateRegistry.getRegistry(DataContants.server2, DataContants.server2Port);
			server2 = (AddO) registry.lookup(DataContants.server2stub);
			if (server1flag) {
				serving = server2.giveServer();

			}
		} catch (Exception e) {
			System.err.println("ERROR : Not able to Connect to server 2");
			if (server1flag) {
				System.err.println("\nNOT Able to connect to any server");
				
				serverflag = false;
				
			}
		}

		System.out.println(serving.getMainIP());
		if(serverflag) {
		boolean flag = true;
		
		int count =10;
		while (flag ) {
			if(count<0) {
				break;
			}
			try {count--;
				Registry registrymain = LocateRegistry.getRegistry(serving.getMainIP(), serving.getMainPORT());
				serverObj = (AddI) registrymain.lookup(serving.getMainOBJ());
				flag = false;
			} catch (Exception e) {
				System.err.println("Not able to get Server to server the client looking for other one");
				
			}
		}
		}
		MainFrame f = new MainFrame(serverObj, server1, server2,serverflag);
		System.out.println(serverObj.add(2, 3));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		System.out.println("Deepak");
	}

}
