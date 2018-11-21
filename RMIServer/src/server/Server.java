package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import Constants.DataContants;
import common.AddI;
import common.AddO;
import pojo.Solution;
import pojo.SolutionId;



public class Server {

	public static void main(String[] args) {
		
		System.out.println("I am live");
		////VAR
		boolean server1flagReg = false;
	
		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
	
		System.setSecurityManager(cSM);
		System.setProperty("java.rmi.server.hostname",DataContants.serverServer);
		System.setProperty("java.rmi.server.useCodebaseOnly","false");
		try {
			//Registering on server1
			Registry registry1 = LocateRegistry.getRegistry(DataContants.server1, DataContants.server1Port);
			AddO serverObj1 = (AddO) registry1.lookup(DataContants.server1stub);
			System.out.println("Registered on server1:" + serverObj1.add(DataContants.serverServer,DataContants.serverServer, DataContants.serverServerPort ,DataContants.serverClientPort,"TNO","ONLINE"));
		}catch(Exception e) {
			System.err.println("ERROR : NOT ABLE TO REGISTER ON SERVER1");
			e.printStackTrace();
			server1flagReg = true;
		}
		
		try {
			//Registering on server2
			Registry registry2 = LocateRegistry.getRegistry(DataContants.server2, DataContants.server2Port);
			AddO serverObj2 = (AddO) registry2.lookup(DataContants.server2stub);
			System.out.println("Registered on server2:" + serverObj2.add(DataContants.serverServer,DataContants.serverServer, DataContants.serverServerPort ,DataContants.serverClientPort,"TNO","ONLINE"));
		}catch(Exception e) {
			System.err.println("ERROR : NOT ABLE TO REGISTER ON SERVER2");
			e.printStackTrace();
			if(server1flagReg) {
				System.err.println("NOT ABLE TO REGISTER ON EITHER SERVERS EXITING");
				System.exit(1);
			}
		}
		try {	
			//For server server1 ping
			AddO serverServerObj1 = new ServerToServerImpl();
			AddO stubserver1 = (AddO) UnicastRemoteObject.exportObject(serverServerObj1, 0);
			LocateRegistry.createRegistry(DataContants.serverServerPort).rebind("TNO", stubserver1);
		}catch(Exception e) {
			System.err.println("ERROR : Not able to BIND to server for status to main Server");
			e.printStackTrace();
			System.err.println("ERROR : Not able to BIND to server 2 for status");
			//TODO REMOVE THIS
			System.exit(1);
			}
			try {
			//for client hosting object
			AddI serverObj = new Fundone();
			AddI clientStub = (AddI) UnicastRemoteObject.exportObject(serverObj, 0);
			LocateRegistry.createRegistry(DataContants.serverClientPort).rebind("ONLINE", clientStub);
			}catch(Exception e){
				System.err.println("ERROR : not able to register for Client\nExiting");
				e.printStackTrace();
				//TODO REMOVE THIS
				System.exit(1);
				
			}	
			//new Server().Testing();

	}

	private void Testing() {
		
//		ArrayList<Integer> s = new ArrayList<>();
//		ArrayList<Object> SubQuestion = new ArrayList<>();
//		s.add(1);
		Fundone serverObj = new Fundone();
//		//List<Object> subObjects = serverObj.getSubjects(s);
//		try {
//			ArrayList<Integer> l1m1 = new ArrayList();
//		ArrayList<Integer> l1m2 = new ArrayList();
//		ArrayList<Integer> l1m3 = new ArrayList();
//		ArrayList<Integer> l1m4 = new ArrayList();
//		ArrayList<Integer> l2m1 = new ArrayList();
//		ArrayList<Integer> l2m2 = new ArrayList();
//		ArrayList<Integer> l2m3 = new ArrayList();
//		ArrayList<Integer> l2m4 = new ArrayList();
//		ArrayList<Integer> l3m1 = new ArrayList();
//		ArrayList<Integer> l3m2 = new ArrayList();
//		ArrayList<Integer> l3m3 = new ArrayList();
//		ArrayList<Integer> l3m4 = new ArrayList();
//		
//		
//				List QuestionList = (serverObj.getQuestionforSubject(1));
//			for (Object ques : QuestionList) {
//					Question q = (Question)ques;
//					System.out.println(q.getMarks() + ":" + q.getQlevel());
//					if (q.getMarks() == 2 && q.getQlevel() == 1)
//						l1m1.add(q.getQid());
//					if (q.getMarks() == 3 && q.getQlevel() == 1)
//						l1m2.add(q.getQid());
//					if (q.getMarks() == 5 && q.getQlevel() == 1)
//						l1m3.add(q.getQid());
//					if (q.getMarks() == 10 && q.getQlevel() == 1)
//						l1m4.add(q.getQid());
//					if (q.getMarks() == 2 && q.getQlevel() == 2)
//						l2m1.add(q.getQid());
//					if (q.getMarks() == 3 && q.getQlevel() == 2)
//						l2m2.add(q.getQid());
//					if (q.getMarks() == 5 && q.getQlevel() == 2)
//						l2m3.add(q.getQid());
//					if (q.getMarks() == 10 && q.getQlevel() == 2)
//						l2m4.add(q.getQid());
//					if (q.getMarks() == 2 && q.getQlevel() == 3)
//						l3m1.add(q.getQid());
//					if (q.getMarks() == 3 && q.getQlevel() == 3)
//						l3m2.add(q.getQid());
//					if (q.getMarks() == 5 && q.getQlevel() == 3)
//						l3m3.add(q.getQid());
//					if (q.getMarks() == 10 && q.getQlevel() == 3)
//						l3m4.add(q.getQid());
//				}
//			
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}
//		ArrayList<Integer> s = new ArrayList<>();
////		s.add(1);
//		List<Object> result;
//		try {
//			result = new Fundone().getQuestionforSubject(((Integer)1));
//			System.out.println(((Question)result.get(0)).getQid());
//			System.out.println(((Question)result.get(1)).getQid());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//System.out.println(((Question)result.get(0)).getQid());
		//System.out.println(((Question)result.get(1)).getQid());
		String s = new String("a~b~c~d~");
		
//		for(String a : quesheader) {
//			System.out.println(a+"\n");
//		}
		String solution="";if(solution.length()==0){
			System.out.println(1);
			
		}
		Solution sol = new Solution();
		
		sol.setQuesid("1#$#2#$#6#$#");
		sol.setSid(1);
		SolutionId id = new SolutionId();
		id.setPid(1);
		id.setUid(1);
		sol.setId(id);
		Object result = null;
		
		try {
			 result = serverObj.startExam(sol);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==null) {
			System.out.println("not cont");
		}
		
		//System.out.println(quesheader.size());
	}

}