package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

import Constants.DataContants;
import common.AddO;
import pojo.ServerList;

public class Server {
static ArrayList<ServerList> serverList = new ArrayList<>();
	public static ServerList getServer() {
		Collections.shuffle(Server.serverList);
		
		return Server.serverList.get(0);
	}
	public static void main(String[] args) {
		
		System.out.println("I am live");
		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		System.setSecurityManager(cSM);
		System.setProperty("java.rmi.server.hostname",DataContants.server1);
		System.setProperty("java.rmi.server.useCodebaseOnly","false");		
		try {
			
			AddO serverobj =  new Fundone();
			AddO stub = 
	                (AddO) UnicastRemoteObject.exportObject(serverobj, 0);
			
			LocateRegistry.createRegistry(DataContants.server1Port).rebind(DataContants.server1stub, stub);
			
			java.util.Timer t = new java.util.Timer();
			t.schedule(new TimerTask() {

			         
						@Override
			            public void run() {
							//System.out.println("polling");
							java.util.Iterator<ServerList> itr = serverList.iterator();
							
							while (itr.hasNext()) {
							ServerList l = itr.next();
							Registry registry = null;
							try {
								//System.out.println(l.getIP()+l.getPort());
								registry = LocateRegistry.getRegistry(l.getIP(), l.getPort());
							} catch (NullPointerException r) 
							{
								System.out.println("remove");
							}catch (RemoteException e) 
							{
								System.out.println("remove me-1-"+ l.getIP());
							}
							
							AddO serverObj = null;
							try {
								serverObj = (AddO) registry.lookup(l.getObjName());
							} catch (NullPointerException r) 
							{
								System.out.println("remove");
							}catch (RemoteException | NotBoundException e) {
								System.out.println("remove me-2-"+ l.getIP());
								itr.remove();
								continue;
							}
							try {
								serverObj.status();
							} catch (NullPointerException r) 
							{
								System.out.println("remove");
							}catch(RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
			            }
			        }, 500, 500);
			
		}catch(Exception e) {
			System.out.println("error"+ e);
		}
			
	System.out.println("I am live");

}
	public boolean add(ServerList server) {
		System.out.println(Server.serverList.size());
		return Server.serverList.add(server);
	}

}
