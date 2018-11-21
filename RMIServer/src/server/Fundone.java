package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import Constants.DataContants;
import common.AddI;
import pojo.Paperreg;
import pojo.Question;
import pojo.Solution;
import pojo.SolutionId;
import pojo.Studentreg;
import pojo.Subject;
import pojo.Substudent;
import pojo.SubstudentId;
import pojo.Subteacher;
import pojo.SubteacherId;
import pojo.Teacherreg;

@SuppressWarnings("deprecation")
public class Fundone implements AddI {
	private SessionFactory factory;

	public Fundone() {
		super();
		dbConnection db = new dbConnection();
		factory = db.getFactory();
		// Session session = factory.openSession();
		//
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(int x, int y) throws RemoteException {
		//
		return x + y;
	}

	// @Override
	@Override
	public Integer addQuestion(Object ques) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("addQuestion");
		Session session = factory.openSession();
		Transaction tx = null;
		Integer results = null;

		try {
			tx = session.beginTransaction();

			((Question) ques).setQcreationdate(new Date());
			// Save the employee in database
			results = (Integer) session.save(ques);

			// Commit the transaction
			tx.commit();
		} catch (HibernateException e) {
			if (e instanceof ConstraintViolationException) {
				results = 0;
			}
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println(e.getCause());
		} finally {
			session.close();

		}
		System.out.println("addQuestion");
		return results;
	}

	@Override
	public List<Object> getPapers(ArrayList<Integer> subids) {

		System.out.println("getPapers");
		//dbConnection db = new dbConnection();
		//factory = db.getFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List<Object> results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM paperreg WHERE subid IN (:subids)";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Paperreg.class);
			 query.setParameterList("subids", subids);

			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("getPapers");
		
		if (results.size()<1)
			return null;
		else
			
			return results;

	}

	@Override
	public ArrayList<Object> getQuestions(Object paperreg, Object user) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("getQuestions");
		
		Session session = factory.openSession();
		Transaction tx = null;
		List<Question> results = null;
		ArrayList<Object> questionListObject = null;

		
			try {
				tx = session.beginTransaction();
				String sql = null;
				sql = "SELECT * FROM question WHERE subid = :subid";

				SQLQuery query = session.createSQLQuery(sql);
				query.addEntity(Question.class);
				query.setParameter("subid", ((Paperreg) paperreg).getSubid());
				results = query.list();
				if(results.size()<1) {
					return null;
				}

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();

			}
			// Separating questions into different pool for random selection;
			ArrayList<Integer> l1m1 = new ArrayList();
			ArrayList<Integer> l1m2 = new ArrayList();
			ArrayList<Integer> l1m3 = new ArrayList();
			ArrayList<Integer> l1m4 = new ArrayList();
			ArrayList<Integer> l2m1 = new ArrayList();
			ArrayList<Integer> l2m2 = new ArrayList();
			ArrayList<Integer> l2m3 = new ArrayList();
			ArrayList<Integer> l2m4 = new ArrayList();
			ArrayList<Integer> l3m1 = new ArrayList();
			ArrayList<Integer> l3m2 = new ArrayList();
			ArrayList<Integer> l3m3 = new ArrayList();
			ArrayList<Integer> l3m4 = new ArrayList();
			System.out.println(results);
			
			for (Question q : results) {
				System.out.println(q.getMarks() + ":" + q.getQlevel());
				if (q.getMarks() == 1 && q.getQlevel() == 1)
					l1m1.add(q.getQid());
				if (q.getMarks() == 2 && q.getQlevel() == 1)
					l1m2.add(q.getQid());
				if (q.getMarks() == 3 && q.getQlevel() == 1)
					l1m3.add(q.getQid());
				if (q.getMarks() == 4 && q.getQlevel() == 1)
					l1m4.add(q.getQid());
				if (q.getMarks() == 1 && q.getQlevel() == 2)
					l2m1.add(q.getQid());
				if (q.getMarks() == 2 && q.getQlevel() == 2)
					l2m2.add(q.getQid());
				if (q.getMarks() == 3 && q.getQlevel() == 2)
					l2m3.add(q.getQid());
				if (q.getMarks() == 4 && q.getQlevel() == 2)
					l2m4.add(q.getQid());
				if (q.getMarks() == 1 && q.getQlevel() == 3)
					l3m1.add(q.getQid());
				if (q.getMarks() == 2 && q.getQlevel() == 3)
					l3m2.add(q.getQid());
				if (q.getMarks() == 3 && q.getQlevel() == 3)
					l3m3.add(q.getQid());
				if (q.getMarks() == 4 && q.getQlevel() == 3)
					l3m4.add(q.getQid());
			}
			System.out.println(l1m1);
			ArrayList<Integer> questionList = new ArrayList<>();
			// adding question to above list matching the createria set by teacher
			Collections.shuffle(l1m1);
			for (Integer i = 0; i < ((Paperreg) paperreg).getL1m1(); i++)
				questionList.add(l1m1.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL1m2(); i++)
				questionList.add(l1m2.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL1m3(); i++)
				questionList.add(l1m3.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL1m4(); i++)
				questionList.add(l1m4.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL2m1(); i++)
				questionList.add(l2m1.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL2m2(); i++)
				questionList.add(l2m2.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL2m3(); i++)
				questionList.add(l2m3.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL2m4(); i++)
				questionList.add(l2m4.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL3m1(); i++)
				questionList.add(l3m1.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL3m2(); i++)
				questionList.add(l3m2.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL3m3(); i++)
				questionList.add(l3m3.get(i));

			for (Integer i = 0; i < ((Paperreg) paperreg).getL3m4(); i++)
				questionList.add(l3m4.get(i));

			Collections.shuffle(questionList);

			questionListObject = new ArrayList<>();
			for (Integer qid : questionList) {

				for (Question question : results) {
					if (qid == question.getQid()) {
						questionListObject.add(question);

					}

				}

			}
		if(questionListObject.size()<1) {
			return null;
		}
		System.out.println("getQuestions");
		return questionListObject;
	}

	@Override
	public List<Object> getStudentSubjects(Integer sid) {
		System.out.println("getStudentSubjects");
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM substudent WHERE sid = :sid";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Substudent.class);
			query.setParameter("sid", sid);
			
			results = query.list();
			System.out.println(results.size());
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("getStudentSubjects");
		if (results.size()<1)
			return null;
		else
			return results;

	}

	@Override
	public List<Object> getSubjects(ArrayList<Integer> subids) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("getSubjects");
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM subject WHERE subid IN (:subids)";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Subject.class);
			query.setParameter("subids", subids);
			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		System.out.println("getSubjects");
		if (results.size() <1)
			return null;
		else
			return results;
	}

	@Override
	public List<Object> getTeacherSubjects(Integer tid) {

		System.out.println("getTeacherSubjects");
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM subteacher WHERE tid = :tid";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Subteacher.class);
			query.setParameter("tid", tid);

			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("getTeacherSubjects");
		if (results.size()<1)
			return null;
		else
			return results;
	}

	@Override
	public List<Object> getTeacherTA(ArrayList<Integer> subids) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("getTeacherTA");
		Session session = factory.openSession();
		Transaction tx = null;
		List results1 = null;
		List<Teacherreg> results2 = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM subteacher WHERE subid IN ( :subids)";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Subteacher.class);
			query.setParameter("subids", subids);
			results1 = query.list();
			ArrayList<Integer> tids = new ArrayList<Integer>();

			for (Object subteacher : results1)
				tids.add(((Subteacher) subteacher).getId().getTid());

			sql = "SELECT * FROM teacherreg WHERE  tstatus = 1 and tid IN ( :tids) ";
			query = session.createSQLQuery(sql);
			query.addEntity(Teacherreg.class);
			query.setParameter("tids", tids);
			results2 = query.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("wow");
		return null;
	}

	@Override
	public Studentreg loginStudent(String email, String pwd) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("loginStudent");
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM studentreg WHERE semail = :email and spassword = :password";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Studentreg.class);
			query.setParameter("email", email);
			query.setParameter("password", pwd);
			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("loginStudent");

		if (results.isEmpty())
			return null;
		else
			return (Studentreg) results.get(0);

			}

	@Override
	public Integer regiserStudent(String sname, String semail, String spassword, Integer[] selectedSubject) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("regiserStudent");

		Session session = factory.openSession();
		Transaction tx = null;
		Integer results = null;

		try {
			tx = session.beginTransaction();

			// Add new Employee object
			Studentreg user = new Studentreg();
			user.setSemail(semail);
			user.setSname(sname);
			// TODO HASHING OF PASSWORD
			user.setSpassword(spassword);
			user.setScreationdate(new Date());
			results = (Integer) session.save(user);
			Substudent substudent;
			SubstudentId subId;
			for (Integer sub : selectedSubject) {

				if (sub > 0) {
					subId = new SubstudentId();
					subId.setSubid(sub);
					subId.setSid(results);
					substudent = new Substudent();
					substudent.setId(subId);
					substudent.setSubstudentcreationdate(new Date());
					session.save(substudent);

				}

			}

			// Commit the transaction
			tx.commit();
		} catch (HibernateException e) {
			if (e instanceof ConstraintViolationException) {
				results = 0;
			}
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println(e.getCause());
		} finally {
			session.close();

		}
		System.out.println("regiserStudent");
		return results;
	}

	@Override
	public Integer regiserTeacher(String sname, String semail, String spassword, Integer[] selectedSubject)
			throws RemoteException {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("regiserTeacher");
		Session session = factory.openSession();
		Transaction tx = null;
		Integer results = null;

		try {
			tx = session.beginTransaction();

			Teacherreg user = new Teacherreg();
			user.setTemail(semail);
			user.setTname(sname);
			user.setTpassword(spassword);
			user.setTcreationdate(new Date());
			user.setTstatus(true);
			results = (Integer) session.save(user);

			Subteacher subteacher;
			SubteacherId subteacherId;
			for (Integer sub : selectedSubject) {

				if (sub > 0) {
					subteacher = new Subteacher();
					subteacherId = new SubteacherId();
					subteacherId.setSubid(sub);
					subteacherId.setTid(results);
					subteacher.setId(subteacherId);
					subteacher.setSubteachercreationdate(new Date());
					session.save(subteacher);
					// tx.commit();
				}

			}

			tx.commit();
		} catch (HibernateException e) {
			if (e instanceof ConstraintViolationException) {
				results = 0;
			}
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println(e.getCause());
		} finally {
			session.close();

		}
		System.out.println("Commited");
		return results;

	}

	@Override
	public Integer regiserTA(String sname, String semail, String spassword, Integer selectedSubject)
			throws RemoteException {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("regiserTA");
		Session session = factory.openSession();
		Transaction tx = null;
		Integer results = null;

		try {
			tx = session.beginTransaction();

			Teacherreg user = new Teacherreg();
			user.setTemail(semail);
			user.setTname(sname);
			user.setTpassword(spassword);
			user.setTcreationdate(new Date());
			results = (Integer) session.save(user);

			Subteacher subteacher = new Subteacher();
			SubteacherId subteacherId = new SubteacherId();
			subteacherId.setSubid(selectedSubject);
			subteacherId.setTid(results);
			subteacher.setId(subteacherId);
			subteacher.setSubteachercreationdate(new Date());
			session.save(subteacher);
			tx.commit();
		} catch (HibernateException e) {
			if (e instanceof ConstraintViolationException) {
				results = 0;
			}
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println(e.getCause());
		} finally {
			session.close();

		}
		System.out.println("Commited");
		return results;

	}

	@Override
	public Boolean status() throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object teacherLogin(String email, String pwd) {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("teacherLogin");
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM teacherreg WHERE temail = :email and tpassword = :password";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Teacherreg.class);
			query.setParameter("email", email);
			query.setParameter("password", pwd);
			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		Object Tuser = null;
		try {
			Tuser = results.get(0);
		} catch (Exception e) {
			System.err.println("No user");
			Tuser = null;
		}
		System.out.println("teacherLogin");
		return Tuser;

	}

	@Override
	public List<Object> getAllSubjects() throws RemoteException {
		// dbConnection db = new dbConnection();
		// factory = db.getFactory();
		System.out.println("getAllSubjects");
		Session session = factory.openSession();
		Transaction tx = null;
		List<Object> results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM subject WHERE substatus = 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Subject.class);
			results = query.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();

			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("getAllSubjects");
		return results;
	}

	@Override
	public List<Object> getQuestionforSubject(Integer s) throws RemoteException {
		Session session = factory.openSession();

		System.out.println("getQuestionforSubject");
		Transaction tx = null;
		List<Object> results = null;
		System.out.println("In getting Questions");
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM question WHERE subid = :subid";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Question.class);
			query.setParameter("subid", s);
			results = query.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		if(results.size()<1) {
			return null;
		}
		System.out.println("getQuestionforSubject");
		return results;
	}

	@Override
	public Integer registerPaper(Object paperreg) throws RemoteException {
		System.out.println("submitQuestion");
		Paperreg paper = (Paperreg) paperreg;
		Session session = factory.openSession();
		Transaction tx = null;
		Integer results = null;

		try {
			tx = session.beginTransaction();
			paper.setPcreationdate(new Date());
			results = (Integer) session.save((Paperreg) paper);
			tx.commit();
		} catch (HibernateException e) {
			if (e instanceof ConstraintViolationException) {
				results = 0;
			}
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println(e.getCause());
		} finally {
			session.close();

		}
		System.out.println("Commited");
		return results;
	}

	@Override
	public Integer submitQuestion(Integer uid, int questionNo, String solution, Integer paperId, Integer subId)
			throws RemoteException {
System.out.println("submitQuestion");
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		boolean flag = false;
		Object results2 = null;
		try {
			tx = session.beginTransaction();
			String sql = null;

			sql = "SELECT * FROM solution WHERE pid = :pid and uid = :uid";

			SQLQuery query = session.createSQLQuery(sql);

			query.addEntity(Solution.class);
			query.setParameter("pid", paperId);
			query.setParameter("uid", uid);

			results = query.list();
			tx.commit();
			tx = session.beginTransaction();
			System.out.println("got results of students");
			Solution sol = ((Solution) results.get(0));
			System.out.println(sol.getSid());
			if(questionNo !=-2) {
			
			if (questionNo == 1) {
				sol.setSolution(solution + "#&#");
			} else {
				sol.setSolution(sol.getSolution() + solution + "#&#");
			}}
			if (questionNo == -1) {
				sol.setStatus(true);
			}
			
			sol.setLastsubmit(new Date());
			session.update((Solution) sol);
			System.out.println("after res");
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			// e.printStackTrace();
			// System.out.println(e.getCause());
			//
			flag = true;
		} finally {
			session.close();

		}
		System.out.println("Commited");
		if (flag)
			return -1;
		return 1;
	}

	@Override
	public Integer startExam(Object sol) throws RemoteException {
		System.out.println("startExam");				
		Session session = factory.openSession();
		Transaction tx = null;
		List<Object> results = null;
		Integer toreturn = 1;

				try {
			
			tx = session.beginTransaction();
			Solution solution = new Solution();
			SolutionId solid = new SolutionId();
			solid.setPid(((Solution)sol).getId().getPid());
			solid.setUid(((Solution)sol).getId().getUid());
			solution.setId(solid);
			solution.setQuesid(((Solution)sol).getQuesid());
			solution.setSid(((Solution)sol).getSid());
			solution.setStarttime(new Date());
			solution.setLastsubmit(new Date());
			
			session.saveOrUpdate(solution);
			System.out.println("after res");
			tx.commit();
			System.out.println("agter commit");
			//toreturn = 1;
		} catch (Exception e) {
			System.out.println("inextion");
			toreturn = -1;
			if (tx != null) {
				tx.rollback();			}
		} finally {
			session.close();

		}
		//System.out.println(results.getClass());
		System.out.println("Commited");
		// return results;
		// TODO Auto-generated method stub
		System.out.println("startExam");				
		return toreturn;
	}

	@Override
	public Object getSolutions(Integer sid, Integer pid) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("getSolutions");
		Object solution = null;
		Session session = factory.openSession();
		Transaction tx = null;
		List<Object> results = null;
		//Integer toreturn = 1;
		try{
			tx = session.beginTransaction();
		String sql = null;
		sql = "SELECT * FROM solution WHERE pid = :pid and uid = :uid";

		SQLQuery query = session.createSQLQuery(sql);

		query.addEntity(Solution.class);
		query.setParameter("pid", pid);
		query.setParameter("uid", sid);

		results = query.list();
		if(results.size()>0) {
		solution = results.get(0);}
		else{solution = null;}
		System.out.println(results.size());
		
		}
		catch(Exception e){
			System.out.println("in Exception get solution");
			return null;
			
		}
		System.out.println("getSolutions");
		return solution;
	}

	@Override
	public Integer canStartExam(Object selectedpaper) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("In can start");
		((Paperreg)selectedpaper).getPstarttime();
		((Paperreg)selectedpaper).getEndtime();
		if(((Paperreg)selectedpaper).getPstarttime().getTime()<= (new Date()).getTime() && (new Date()).getTime() <= ((Paperreg)selectedpaper).getEndtime().getTime()){
			return 1;
		}
		else if(((Paperreg)selectedpaper).getPstarttime().getTime()> (new Date()).getTime()){
			return 2;
		}
		else if((new Date()).getTime() > ((Paperreg)selectedpaper).getEndtime().getTime()) {
			return  3;
		}
		
		else return -1;
		
	}

	@Override
	public List<Object> getQuestionListFromQuids(ArrayList<Integer> qids) throws RemoteException {
		System.out.println("getQuestionListFromQuids");
		dbConnection db = new dbConnection();
		factory = db.getFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM question WHERE qid IN (:qids)";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Question.class);
			 query.setParameterList("qids", qids);

			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		if (results.size()<1)
			return null;
		else
			System.out.println("getQuestionListFromQuids");
			return results;
		
	}

	@Override
	public String getUrlTeacher(Integer paperid, Integer subid) throws RemoteException {
		System.out.println("In teacher excel");
		   HSSFWorkbook workbook = new HSSFWorkbook();
		   HSSFSheet sheet = workbook.createSheet("FuSsA sheet");
		   //////////////////////////////////


		   /////////////////////////////////////////////////////////
		   String sql = null, sql2;
		   
		    dbConnection db = new dbConnection();
			SessionFactory factory = db.getFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			List results = null;
			tx = session.beginTransaction();
		   sql = "SELECT * FROM solution WHERE pid = :pid and status = 1";
		   SQLQuery query = session.createSQLQuery(sql);
		   query.addEntity(Solution.class);
		   query.setParameter("pid", paperid);
		   //query.setParameter("uid", userid);
		   List<Object> papers = query.list();
		   
		   sql2 = "SELECT * FROM question WHERE subid = :sid";
		   SQLQuery query2 = session.createSQLQuery(sql2);
		   query2.addEntity(Question.class);
		   query2.setParameter("sid", subid);
		   List<Object> questions = query2.list();
		   
		   //////////////////////////////////////
		   if(papers.size()<1) {
			   return null;
		   }else{
		   List<String> quesheader = Arrays.asList((((Solution)papers.get(0)).getQuesid()).split("\\s*#&#\\s*"));
		   //HSSFFont my_font=workbook.createFont();
		   //my_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		   Row header = sheet.createRow(0);
		   header.createCell(0).setCellValue("StudentId");
		   int i,cellcount=1;
		   for(i = 1; i <= quesheader.size(); i=i+1) {
			   
			   header.createCell(cellcount++).setCellValue("Question"+i);
			   header.createCell(cellcount++).setCellValue("Solution"+(i));
		   }
		    
		    
		    header.createCell(cellcount).setCellValue("Grading");
		    
		   ///////////////////////////////////////
		   for(i = 1; i <= papers.size(); i++) {
			   Row row = sheet.createRow(i);
			   Cell cell = row.createCell(0);
			   ///////Student name instead of id//////////////
			   cell.setCellValue(((Solution)papers.get(i-1)).getId().getUid());
			   List<String> solution = Arrays.asList((((Solution)papers.get(i-1)).getSolution()).split("\\s*#&#\\s*"));
			   List<String> ques = Arrays.asList((((Solution)papers.get(i-1)).getQuesid()).split("\\s*#&#\\s*"));
			   int k = 0,p,r,maxmarks = 0, totalmarks = 0, counter=0, colomncounter=1;
			   for(String q:ques) {
			   for(int j = 1; j <= questions.size(); j++) {
				   
				   if(((Question)questions.get(j-1)).getQid()==Integer.parseInt(q)) {
					   CellStyle style = workbook.createCellStyle();
					    maxmarks = maxmarks + ((Question)questions.get(j-1)).getMarks();
					    if(solution.get(k).equalsIgnoreCase(((Question)questions.get(j-1)).getQsolution())) {
					    	//System.out.println("getting into loop " + maxmarks+" hi there "+totalmarks);
					    	totalmarks = totalmarks + ((Question)questions.get(j-1)).getMarks();
					    	 style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
							    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					    }
					    else {
					    	style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
						    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					    }
					    
					    cell = row.createCell(colomncounter++);
					    
					   
					   
					    cell.setCellStyle(style);
					    cell.setCellValue(((Question)questions.get(j-1)).getQquestion());
					    cell = row.createCell(colomncounter++);
					    style.setBorderRight(BorderStyle.THIN);  
					    style.setRightBorderColor(IndexedColors.BLACK.getIndex());  
					    cell.setCellStyle(style);
					    cell.setCellValue(solution.get(counter));
					    k++;
				   }
			   }
			   counter++;
			   }
			   cell = row.createCell(colomncounter);
			   cell.setCellValue(totalmarks+"/"+maxmarks);
		   }
		   //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	       //String strDate = dateFormat.format(new Date());  
		   String papername = Integer.toString(paperid) + Integer.toString(subid)   ;
				   try {
			       FileOutputStream out = 
			               new FileOutputStream(new File("/var/www/html/"+papername+".xlsx"));
			       workbook.write(out);
			       out.close();
			       System.out.println("Excel written successfully..");
			
			   } catch (FileNotFoundException e) {
			       e.printStackTrace();
			   } catch (IOException e) {
			       e.printStackTrace();
			   }
		   tx.commit();
		   ////////////////////////////////////////////////////////////////////////////

		   
		   
		   ////////////////////////////////////////////////////////////////

		   ///////////////////////////////////////////////////////////////
		   System.out.println("in excel");
		   return "http://"+DataContants.serverServer+"/"+papername+".xlsx";
		   } }
	

	@Override
	public String getUrlStudent(Integer paperid, Integer subid,Integer userid) throws RemoteException {
		System.out.println("getUrlStudent");
		   HSSFWorkbook workbook = new HSSFWorkbook();
		   HSSFSheet sheet = workbook.createSheet("FuSsA sheet");
		   //////////////////////////////////


		   /////////////////////////////////////////////////////////
		   String sql = null, sql2;
		   
		    dbConnection db = new dbConnection();
			SessionFactory factory = db.getFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			List results = null;
			tx = session.beginTransaction();
		   sql = "SELECT * FROM solution WHERE pid = :pid and uid = :uid and status = 1";
		   SQLQuery query = session.createSQLQuery(sql);
		   query.addEntity(Solution.class);
		   query.setParameter("pid", paperid);
		   query.setParameter("uid", userid);
		   List<Object> papers = query.list();
		   
		   sql2 = "SELECT * FROM question WHERE subid = :sid";
		   SQLQuery query2 = session.createSQLQuery(sql2);
		   query2.addEntity(Question.class);
		   query2.setParameter("sid", subid);
		   List<Object> questions = query2.list();
		   
		   //////////////////////////////////////
		   if(papers.size()<1) {
			   return null;
		   }else{
		   List<String> quesheader = Arrays.asList((((Solution)papers.get(0)).getQuesid()).split("\\s*#&#\\s*"));
		   //HSSFFont my_font=workbook.createFont();
		   //my_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		   Row header = sheet.createRow(0);
		   header.createCell(0).setCellValue("StudentId");
		   int i,cellcount=1;
		   for(i = 1; i <= quesheader.size(); i=i+1) {
			   
			   header.createCell(cellcount++).setCellValue("Question"+i);
			   header.createCell(cellcount++).setCellValue("Solution"+(i));
		   }
		    
		    
		    header.createCell(cellcount).setCellValue("Grading");
		    
		   ///////////////////////////////////////
		   for(i = 1; i <= papers.size(); i++) {
			   Row row = sheet.createRow(i);
			   Cell cell = row.createCell(0);
			   
			   ///////Student name instead of id//////////////
			   cell.setCellValue(((Solution)papers.get(i-1)).getId().getUid());
			   
			   List<String> solution = Arrays.asList((((Solution)papers.get(i-1)).getSolution()).split("\\s*#&#\\s*"));
			   List<String> ques = Arrays.asList((((Solution)papers.get(i-1)).getQuesid()).split("\\s*#&#\\s*"));
			   int k = 0,p,r,maxmarks = 0, totalmarks = 0, counter=0, colomncounter=1;
			   for(String q:ques) {
			   for(int j = 1; j <= questions.size(); j++) {
				   
				   if(((Question)questions.get(j-1)).getQid()==Integer.parseInt(q)) {
					   CellStyle style = workbook.createCellStyle();
					    maxmarks = maxmarks + ((Question)questions.get(j-1)).getMarks();
					    if(solution.get(k).equalsIgnoreCase(((Question)questions.get(j-1)).getQsolution())) {
					    	//System.out.println("getting into loop " + maxmarks+" hi there "+totalmarks);
					    	totalmarks = totalmarks + ((Question)questions.get(j-1)).getMarks();
					    	 style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
							    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							    
					    }
					    else {
					    	style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
						    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					    }
					    
					    cell = row.createCell(colomncounter++);
					    
					   
					   
					    cell.setCellStyle(style);
					    cell.setCellValue(((Question)questions.get(j-1)).getQquestion());
					    cell = row.createCell(colomncounter++);
					    style.setBorderRight(BorderStyle.THIN);  
					    style.setRightBorderColor(IndexedColors.BLACK.getIndex());  
					    cell.setCellStyle(style);
					    cell.setCellValue(solution.get(counter));
					    k++;
				   }
			   }
			   counter++;
			   }
			   cell = row.createCell(colomncounter);
			   cell.setCellValue(totalmarks+"/"+maxmarks);
		   }
		   //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	       //String strDate = dateFormat.format(new Date());  
		   String papername =  Integer.toString(userid);
				   try {
			       FileOutputStream out = 
			               new FileOutputStream(new File("/var/www/html/"+papername+".xlsx"));
			       workbook.write(out);
			       out.close();
			       System.out.println("Excel written successfully..");
			
			   } catch (FileNotFoundException e) {
			       e.printStackTrace();
			   } catch (IOException e) {
			       e.printStackTrace();
			   }
		  // tx.commit();
		   ////////////////////////////////////////////////////////////////////////////

		   
		   
		   ////////////////////////////////////////////////////////////////

		   ///////////////////////////////////////////////////////////////
		   return "http://"+DataContants.serverServer+"/"+papername+".xlsx";}	
		   }

	@Override
	public List<Object> getPaperforResult(Integer tid) throws RemoteException {
		System.out.println("getPaperforResult");
		dbConnection db = new dbConnection();
		factory = db.getFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			sql = "SELECT * FROM paperreg WHERE tid = :tid";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Paperreg.class);
			 query.setParameter("tid", tid);

			results = query.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		System.out.println("getPaperforResult");				
		if (results.size()<1)
			return null;
		else
			return results;
	}
	
	
}