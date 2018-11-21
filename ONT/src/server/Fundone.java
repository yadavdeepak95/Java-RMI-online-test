package server;

import java.rmi.RemoteException;

import common.AddO;
import pojo.ServerList;


public class Fundone implements AddO {

	
	@Override
	public Boolean add(String IP, String mainIP, int port, int mainPORT, String objName, String mainOBJ)
			throws RemoteException {
		
		return Server.serverList.add(new ServerList(IP, mainIP, port, mainPORT, objName, mainOBJ));
	}

	@Override
	public ServerList giveServer() throws RemoteException {
		// TODO Auto-generated method stub
		ServerList s = Server.getServer();
		return s;
	}

	@Override
	public Boolean status() {	
		return true;
	}

//	@Override
//	public String add(int x, int y) throws RemoteException {
//		File file = new File("//home//deepak//Desktop//binclassv2.txt"); 
//		String st,ret = null; 
//		  try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			  try {
//				while ((st = br.readLine()) != null) 
//				    ret = ret.concat(st);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			  } 
//		 catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ret; 
//	}


}
