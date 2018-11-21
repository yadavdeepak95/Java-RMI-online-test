package server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.AddO;

public class fun {

	public static void main(String[] args) throws RemoteException {
//		 if (System.getSecurityManager() == null) {
//	            System.setSecurityManager(new SecurityManager());     
//	        }
		System.setProperty("java.rmi.server.hostname","172.23.48.211");
		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		   System.setSecurityManager(cSM);
		try {
			Registry registry = LocateRegistry.getRegistry("172.23.48.211", 1099);
			AddO serverObj = (AddO) registry.lookup("ONT");
			//MainFrame f = new MainFrame(serverObj);
		//	System.out.println(serverObj.add("172.23.48.211", 10997,"TNO"));
	        //f.setLocationRelativeTo(null);
	        //f.setVisible(true);
			
			
			System.setProperty("java.rmi.server.useCodebaseOnly","false");		
			AddO serverobj =  new Fundone();
			AddO stub = 
	                (AddO) UnicastRemoteObject.exportObject(serverobj, 0);
			LocateRegistry.createRegistry(10997).rebind("TNO", stub);
			
	       
		}catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
		
	//	AddI serverObj = new Fundone();
		
      //f.setResizable(false);
//        System.out.println("after call");
	}
//
//	public void studentLogin() throws RemoteException {
//		AddI f = new Fundone();
//		Studentreg user = (Studentreg) f.loginStudent("d@gmail.com", "deepak");
//		if (user != null) {
//			System.out.println(user.getScreationdate());
//
//			List<Object> subs = f.getStudentSubjects(user.getSid());
//			if (subs != null) {
//				for (Object i : subs) {
//					subids.add((Integer) ((Substudent) i).getId().getSubid());
//					System.out.println("subs le aya");
//				}
//
//				List<Object> papers = f.getPapers(subids);
//				System.out.println(((Paperreg) papers.get(0)).getPasskey());
//				System.out.println(((Paperreg) papers.get(0)).getPid());
//				ArrayList<Object> questionList =  f.getQuestions(papers.get(0),user);
//				for(Object q : questionList)
//					System.out.println(((Question) q).getQquestion());
//
//			}
//
//		}
//
//	}
//
//	public void teaherLogin(SessionFactory factory) {
//		Fundone f = new Fundone();
//		Teacherreg user = f.teacherLogin("dteacher@gmail.com", "deepak", factory);
//		if (user != null) {
//			System.out.println(user.getTname());
//
//			List<Subteacher> subs = f.getTeacherSubjects(user.getTid(), factory);
//			
//			if (subs != null) {
//				for (Subteacher i : subs) {
//					subids.add((Integer) ((Subteacher) i).getId().getSubid());
//					System.out.println("subs le aya");
//				}
//				List<Subject> subjects = f.getSubjects(subids, factory);
//				System.out.println(subjects.get(0).getSubname());
//				System.out.println(subjects);
//				List<Paperreg> papers = f.getTeacherTA(subids, factory);
//				System.out.println(papers.get(0).getPasskey());
//
//			}
//
//		}

	
	
}
