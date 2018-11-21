package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface AddI extends Remote {
	public int add(int x, int y) throws RemoteException;

	public Integer addQuestion(Object q) throws RemoteException;

	public List<Object> getPapers(ArrayList<Integer> subids) throws RemoteException;

	public ArrayList<Object> getQuestions(Object paperreg, Object user) throws RemoteException;

	public List<Object> getStudentSubjects(Integer sid) throws RemoteException;

	public List<Object> getSubjects(ArrayList<Integer> subids) throws RemoteException;

	public List<Object> getTeacherSubjects(Integer tid) throws RemoteException;

	public List<Object> getTeacherTA(ArrayList<Integer> subids) throws RemoteException;

	public Object loginStudent(String email, String pwd) throws RemoteException;

	public Integer regiserStudent(String sname, String semail, String spassword, Integer[] selectedSubjects)
			throws RemoteException;

	public Integer regiserTeacher(String sname, String semail, String spassword, Integer[] selectedSubjects)
			throws RemoteException;

	// public int sub(int x, int y) throws Exception;
	public Boolean status() throws RemoteException;

	public Object teacherLogin(String email, String pwd) throws RemoteException;

	public List<Object> getAllSubjects() throws RemoteException;

	public Integer regiserTA(String sname, String semail, String spassword, Integer selectedSubject)
			throws RemoteException;

	public List<Object> getQuestionforSubject(Integer s) throws RemoteException;

	public Integer registerPaper(Object paperreg) throws RemoteException;

	public Integer submitQuestion(Integer uid, int questionNo, String solution, Integer paperId, Integer subId)
			throws RemoteException;

	public Integer startExam(Object sol) throws RemoteException;

	public Object getSolutions(Integer sid, Integer pid) throws RemoteException;

	public Integer canStartExam(Object selectedpaper) throws RemoteException;

	public List<Object> getQuestionListFromQuids(ArrayList<Integer> qids) throws RemoteException;

	public String getUrlTeacher(Integer pid, Integer tid) throws RemoteException;

	public String getUrlStudent(Integer paperid, Integer subid,Integer userid) throws RemoteException;

	public List<Object> getPaperforResult(Integer tid)throws RemoteException;

}
