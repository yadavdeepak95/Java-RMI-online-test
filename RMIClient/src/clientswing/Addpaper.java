package clientswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Paper;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.AddI;
import common.AddO;
import pojo.Paperreg;
import pojo.Question;
import pojo.ServerList;
import pojo.Teacherreg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author naruto
 */
public class Addpaper extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private List substeacher;
	private String[] subjects;
	private List subObjects;
	private ArrayList<Integer> subIds;
	private List SubQuestion;
	AddO server1, server2;
	private AddI serverObj;
	private String selectedSubject;
	private Teacherreg user;
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
	String[] l1m1option;
	String[] l1m2option;
	String[] l1m3option;
	String[] l1m4option;
	String[] l2m1option;
	String[] l2m2option;
	String[] l2m3option;
	String[] l2m4option;
	String[] l3m1option;
	String[] l3m2option;
	String[] l3m3option;
	String[] l3m4option;

	/**
	 * Creates new form addpaper
	 * 
	 * @param server2
	 * @param server1
	 * @param serverObj
	 * @param user
	 * @param subs
	 */
	List<Object> QuestionList;

	@SuppressWarnings("unchecked")
	public Addpaper(Teacherreg user, String selectedSubject, AddI serverObj, AddO server1, AddO server2) {
		this.user = user;
		this.selectedSubject = selectedSubject;
		this.serverObj = serverObj;
		this.server1 = server1;
		this.server2 = server2;

		// for (int i = 0; i < subs.size(); i++) {
		// subIds.add(((Subteacher) this.substeacher.get(i)).getId().getSubid());
		// }
		//
		System.out.println(selectedSubject);
		int cto = 15;
		boolean flag = true;
		while (flag) {
//			cto--;
//			if(cto==0) {
//				JOptionPane.showMessageDialog(this, "No able to Connect at this time! Try again in some time.");
//				
//			}
			try {
				Integer subid = Integer.parseInt(selectedSubject);
				QuestionList = this.serverObj.getQuestionforSubject(subid);

				System.out.println("making call");
				flag = false;

			} catch (Exception e) {
				System.out.println("will get new serverobject and the recall the function again");
				ServerList serving = null;
				e.printStackTrace();
				try {
					System.out.println("Calling server");
					serving = server1.giveServer();
					System.out.println("After call server");

				} catch (Exception ex) {
					System.err.println("Server1 failed to give server calling server2");
					try {
						serving = server2.giveServer();
					} catch (Exception ex2) {
						// TODO EXIT POPUP
						System.err.println("NO SERVER TO SERVE");
						JOptionPane.showMessageDialog(this,"No server to serve at this moment try again Later");
						System.exit(1);
					}
				}
				//
				if(serving==null) {
					JOptionPane.showMessageDialog(this, "No server to Serve you please try again Later.");
					System.exit(1);
				}
				System.out.println("Getting new server obj");
				this.serverObj = null;

				try {
					System.out.println(serving.getMainIP());
					Registry registrymain = LocateRegistry.getRegistry(serving.getMainIP(), serving.getMainPORT());
					this.serverObj = (AddI) registrymain.lookup(serving.getMainOBJ());
					System.out.println("got server object"+this.serverObj);
				} catch (Exception exxs) {
					System.out.println("Error");
					
				}
			}
		}
		// System.out.println(((Question)this.SubQuestion.get(0)).getQquestion());
		if(QuestionList==null) {
			JOptionPane.showMessageDialog(this, "No Question to add to Paper. Please add questions first");
			parent.setVisible(true);
			this.dispose();
		}else {
		for (Object ques : QuestionList) {
			Question q = (Question) ques;
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

		if (l1m1.size() > 0) {
			l1m1option = new String[l1m1.size() + 1];

			l1m1option[0] = "0";
			for (int i = 1; i <= l1m1.size(); i++) {
				l1m1option[i] = Integer.toString(i);
			}
		} else {
			l1m1option = new String[1];
			l1m1option[0] = "0";
		}

		if (l1m2.size() > 0) {
			l1m2option = new String[l1m2.size() + 1];

			l1m2option[0] = "0";
			for (int i = 1; i <= l1m2.size(); i++) {
				l1m2option[i] = Integer.toString(i);
			}
		} else {
			l1m2option = new String[1];
			l1m2option[0] = "0";
		}

		if (l1m3.size() > 0) {
			l1m3option = new String[l1m3.size() + 1];

			l1m3option[0] = "0";
			for (int i = 1; i <= l1m3.size(); i++) {
				l1m3option[i] = Integer.toString(i);
			}
		} else {
			l1m3option = new String[1];
			l1m3option[0] = "0";
		}

		if (l1m4.size() > 0) {
			l1m4option = new String[l1m4.size() + 1];

			l1m4option[0] = "0";
			for (int i = 1; i <= l1m4.size(); i++) {
				l1m4option[i] = Integer.toString(i);
			}
		} else {
			l1m4option = new String[1];
			l1m4option[0] = "0";
		}

		if (l2m1.size() > 0) {
			l2m1option = new String[l2m1.size() + 1];

			l2m1option[0] = "0";
			for (int i = 1; i <= l2m1.size(); i++) {
				l2m1option[i] = Integer.toString(i);
			}
		} else {
			l2m1option = new String[1];
			l2m1option[0] = "0";
		}

		if (l2m2.size() > 0) {
			l2m2option = new String[l2m2.size() + 1];

			l2m2option[0] = "0";
			for (int i = 1; i <= l2m2.size(); i++) {
				l2m2option[i] = Integer.toString(i);
			}
		} else {
			l2m2option = new String[1];
			l2m2option[0] = "0";
		}

		if (l2m3.size() > 0) {
			l2m3option = new String[l2m3.size() + 1];

			l2m3option[0] = "0";
			for (int i = 1; i <= l2m3.size(); i++) {
				l2m3option[i] = Integer.toString(i);
			}
		} else {
			l2m3option = new String[1];
			l2m3option[0] = "0";
		}

		if (l2m4.size() > 0) {
			l2m4option = new String[l2m4.size() + 1];

			l2m4option[0] = "0";
			for (int i = 1; i <= l2m4.size(); i++) {
				l2m4option[i] = Integer.toString(i);
			}
		} else {
			l2m4option = new String[1];
			l2m4option[0] = "0";
		}

		if (l3m1.size() > 0) {
			l3m1option = new String[l3m1.size() + 1];

			l3m1option[0] = "0";
			for (int i = 1; i <= l3m1.size(); i++) {
				l3m1option[i] = Integer.toString(i);
			}
		} else {
			l3m1option = new String[1];
			l3m1option[0] = "0";
		}

		if (l3m2.size() > 0) {
			l3m2option = new String[l3m2.size() + 1];

			l3m2option[0] = "0";
			for (int i = 1; i <= l3m2.size(); i++) {
				l3m2option[i] = Integer.toString(i);
			}
		} else {
			l3m2option = new String[1];
			l3m2option[0] = "0";
		}

		if (l3m3.size() > 0) {
			l3m3option = new String[l3m3.size() + 1];

			l3m3option[0] = "0";
			for (int i = 1; i <= l3m3.size(); i++) {
				l3m3option[i] = Integer.toString(i);
			}
		} else {
			l3m3option = new String[1];
			l3m3option[0] = "0";
		}

		if (l3m4.size() > 0) {
			l3m4option = new String[l3m4.size() + 1];

			l3m4option[0] = "0";
			for (int i = 1; i <= l3m4.size(); i++) {
				l3m4option[i] = Integer.toString(i);
			}
		} else {
			l3m4option = new String[1];
			l3m4option[0] = "0";
		}

		initComponents();
		   setTitle("WELCOME TO ONLINE QUIZ SYSTEM ");
		/*
		 * Date date=new Date(); SpinnerDateModel em=new SpinnerDateModel(); jSpinner
		 */
	}}

	public void setParentFrame(JFrame f) {
		parent = f;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		setExtendedState(MAXIMIZED_BOTH);
		jLabel13 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jDateChooser1 = new com.toedter.calendar.JDateChooser();
		beg2mark = new javax.swing.JComboBox();
		beg3marks = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		beg5marks = new javax.swing.JComboBox();
		jLabel8 = new javax.swing.JLabel();
		beg10marks = new javax.swing.JComboBox();
		jLabel9 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		int5marks = new javax.swing.JComboBox();
		jLabel11 = new javax.swing.JLabel();
		int3marks = new javax.swing.JComboBox();
		int2marks = new javax.swing.JComboBox();
		jLabel12 = new javax.swing.JLabel();
		int10marks = new javax.swing.JComboBox();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		ex5marks = new javax.swing.JComboBox();
		jLabel17 = new javax.swing.JLabel();
		ex3marks = new javax.swing.JComboBox();
		ex2marks = new javax.swing.JComboBox();
		jLabel18 = new javax.swing.JLabel();
		ex10marks = new javax.swing.JComboBox();
		jLabel19 = new javax.swing.JLabel();
		back = new javax.swing.JButton();
		submit = new javax.swing.JButton();
		logout = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		jSpinner3 = new javax.swing.JSpinner(sm);
		Date date2 = new Date();
		SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.HOUR_OF_DAY);
		jSpinner4 = new javax.swing.JSpinner(sm2);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jLabel13.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
		jLabel13.setText("For set the question paper");

		jLabel1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel1.setText("Passkey");

		jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel2.setText("Exam date:");

		jLabel4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel4.setText("Level Beginner:");

		jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel5.setText("2 marks:");

		jDateChooser1.setDateFormatString("yyyy-MM-dd");

		beg2mark.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		beg2mark.setModel(new javax.swing.DefaultComboBoxModel(l1m1option));

		beg3marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		beg3marks.setModel(new javax.swing.DefaultComboBoxModel(l1m2option));

		jLabel7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel7.setText("3 marks:");

		beg5marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		beg5marks.setModel(new javax.swing.DefaultComboBoxModel(l1m3option));

		jLabel8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel8.setText("5 marks:");

		beg10marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		beg10marks.setModel(new javax.swing.DefaultComboBoxModel(l1m4option));

		jLabel9.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel9.setText("10 marks:");

		jLabel6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel6.setText("2 marks:");

		jLabel10.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel10.setText("Level Intermediate:");

		int5marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		int5marks.setModel(new javax.swing.DefaultComboBoxModel(l2m1option));

		jLabel11.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel11.setText("3 marks:");

		int3marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		int3marks.setModel(new javax.swing.DefaultComboBoxModel(l2m2option));

		int2marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		int2marks.setModel(new javax.swing.DefaultComboBoxModel(l2m3option));

		jLabel12.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel12.setText("10 marks:");

		int10marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		int10marks.setModel(new javax.swing.DefaultComboBoxModel(l2m4option));

		jLabel14.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel14.setText("5 marks:");

		jLabel15.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel15.setText("2 marks:");

		jLabel16.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel16.setText("Level Expert:");

		ex5marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		ex5marks.setModel(new javax.swing.DefaultComboBoxModel(l3m1option));

		jLabel17.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel17.setText("3 marks:");

		ex3marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		ex3marks.setModel(new javax.swing.DefaultComboBoxModel(l3m2option));

		ex2marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		ex2marks.setModel(new javax.swing.DefaultComboBoxModel(l3m3option));

		jLabel18.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel18.setText("10 marks:");

		ex10marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		ex10marks.setModel(new javax.swing.DefaultComboBoxModel(l3m4option));

		jLabel19.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel19.setText("5 marks:");

		back.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		back.setText("Back");
		back.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backActionPerformed(evt);
			}
		});

		submit.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		submit.setText("Submit");
		submit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitActionPerformed(evt);
			}
		});

		logout.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		logout.setText("Logout");
		logout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutActionPerformed(evt);
			}
		});

		jTextField1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N

		jLabel20.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel20.setText("Start time:");

		jLabel21.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel21.setText("End time:");

		JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner3, "HH:mm:ss");
		jSpinner3.setEditor(de);
		jSpinner3.setToolTipText("");
		jSpinner3.addChangeListener(this);
		JSpinner.DateEditor de2 = new JSpinner.DateEditor(jSpinner4, "HH:mm:ss");
		jSpinner4.setEditor(de2);
		jSpinner4.setToolTipText("");
		jSpinner4.addChangeListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(19, 19, 19).addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
												.createSequentialGroup().addComponent(jLabel13)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(logout))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel2).addComponent(jLabel1))
												.addGap(18, 18, 18)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jSpinner3)
														.addComponent(
																jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jTextField1))
												.addGap(0, 0, Short.MAX_VALUE))))
								.addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(jLabel15)
												.addGap(18, 18, 18)
												.addComponent(ex2marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel17)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(ex3marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel19)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(ex5marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel18)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(ex10marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addComponent(jLabel6)
												.addGap(18, 18, 18)
												.addComponent(int2marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel11)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(int3marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel14)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(int5marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel12)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(
														int10marks, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jLabel10).addComponent(jLabel16).addComponent(jLabel4)
										.addGroup(layout.createSequentialGroup().addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup().addComponent(jLabel5)
																.addGap(18, 18, 18)
																.addComponent(beg2mark,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jLabel20))
												.addGap(18, 18, 18).addComponent(jLabel7)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(beg3marks,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18).addComponent(jLabel8)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(beg5marks,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18).addComponent(jLabel9)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(beg10marks,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup().addComponent(jLabel21)
																.addGap(18, 18, 18).addComponent(jSpinner4,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 88,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95,
												Short.MAX_VALUE)))
						.addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(155, 155, 155).addComponent(back).addGap(126, 126, 126)
						.addComponent(submit).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(4, 4, 4).addComponent(jLabel13,
								javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(logout)))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(15, 15, 15)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2)
						.addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(26, 26, 26)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel20)
						.addComponent(jLabel21)
						.addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel4)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5)
						.addComponent(beg2mark, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel7)
						.addComponent(beg3marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel8)
						.addComponent(beg5marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(beg10marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel9))
				.addGap(18, 18, 18).addComponent(jLabel10).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6)
						.addComponent(int2marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel11)
						.addComponent(int3marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel14)
						.addComponent(int5marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(int10marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel12))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel16)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15)
						.addComponent(ex2marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel17)
						.addComponent(ex3marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel19)
						.addComponent(ex5marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(ex10marks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel18))
				.addGap(36, 36, 36).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(back).addComponent(submit))
				.addContainerGap(73, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void backActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backActionPerformed
		parent.setVisible(true);
		this.dispose();
	}// GEN-LAST:event_backActionPerformed

	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		parent.setVisible(true);
		this.dispose();
	}// GEN-LAST:event_formWindowClosing

	private void submitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_submitActionPerformed

		// System.out.println(((String)jSpinner3.getValue()).substring(11, 19));

		// SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		// String starttime = localDateFormat.format(jSpinner3.getValue());
		// System.out.println(starttime);
		//
		SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// System.out.println(endtime);

		// System.out.println(jSpinner3.getValue().getClass());
		// System.out.println( jDateChooser1.getDate());
		// System.out.println( jDateChooser1.getDate().getClass());
		//
		System.out.println(jDateChooser1.getDate());
		if (jTextField1.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Please specify pass key for exam");
		} else if (jDateChooser1.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Please specify Date for exam");
		} else if (flagJSpinner3 && flagJSpinner4) {

			if (((Date) jSpinner3.getValue()).compareTo((Date) jSpinner4.getValue()) > 0
					|| ((Date) jSpinner3.getValue()).compareTo((Date) jSpinner4.getValue()) == 0) {
				JOptionPane.showMessageDialog(this, "Please select end time greater the Start time");
			} else {

				Paperreg paper = new Paperreg();
				paper.setTid(user.getTid());
				paper.setSubid(new Integer(selectedSubject));
				paper.setPasskey(jTextField1.getText());
				paper.setL1m1(beg2mark.getSelectedIndex());
				paper.setL1m2(beg3marks.getSelectedIndex());
				paper.setL1m3(beg5marks.getSelectedIndex());
				paper.setL1m4(beg10marks.getSelectedIndex());

				paper.setL2m1(int2marks.getSelectedIndex());
				paper.setL2m2(int3marks.getSelectedIndex());
				paper.setL2m3(int5marks.getSelectedIndex());
				paper.setL2m4(int10marks.getSelectedIndex());

				paper.setL3m1(ex2marks.getSelectedIndex());
				paper.setL3m2(ex3marks.getSelectedIndex());
				paper.setL3m3(ex5marks.getSelectedIndex());
				paper.setL3m4(ex10marks.getSelectedIndex());

				String endtime = TimeFormat.format(jSpinner4.getValue());
				String starttime = TimeFormat.format(jSpinner3.getValue());
				String scheduledate = DateFormat.format(jDateChooser1.getDate());
				Date startdatetime = new Date();
				Date enddatetime = new Date();
				try {
					startdatetime = DateTimeFormat.parse(scheduledate + " " + starttime);
					enddatetime = DateTimeFormat.parse(scheduledate + " " + endtime);
					System.out.println(startdatetime);
					System.out.println(new Date());

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paper.setPstarttime(startdatetime);

				paper.setEndtime(enddatetime);

				boolean flag = true;

				int result = -1;
				// RMI CALL
				while (flag) {

					try {
						/// *****Add cone only here////
						result = this.serverObj.registerPaper(paper);
						System.out.println("making call");
						flag = false;

					} catch (Exception ee) {
						System.out.println("will get new serverobject and the recall the function again");
						ServerList serving = null;
						try {
							System.out.println("Calling server");
							serving = server1.giveServer();
							System.out.println("After call server");

						} catch (Exception ex) {
							System.err.println("Server1 failed to give server calling server2");
							try {
								serving = server2.giveServer();
							} catch (Exception ex2) {
								// TODO EXIT POPUP
								System.err.println("NO SERVER TO SERVE");
								// TODO ADD OPOP
								System.exit(1);
							}
						}
						//
						System.out.println("Getting new server obj");
						this.serverObj = null;

						try {
							Registry registrymain = LocateRegistry.getRegistry(serving.getMainIP(),
									serving.getMainPORT());
							this.serverObj = (AddI) registrymain.lookup(serving.getMainOBJ());
						} catch (Exception exxs) {
							// TODO: handle exception
						}
					}
				}
				JOptionPane.showMessageDialog(this, "Question Add sucessfully with Question id:"+ result);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Please specify exam schedule");
		}

	}// GEN-LAST:event_submitActionPerformed

	private void logoutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutActionPerformed
		JOptionPane.showMessageDialog(this, "logout");
	}// GEN-LAST:event_logoutActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton back;
	private javax.swing.JComboBox beg10marks;
	private javax.swing.JComboBox beg2mark;
	private javax.swing.JComboBox beg3marks;
	private javax.swing.JComboBox beg5marks;
	private javax.swing.JComboBox ex10marks;
	private javax.swing.JComboBox ex2marks;
	private javax.swing.JComboBox ex3marks;
	private javax.swing.JComboBox ex5marks;
	private javax.swing.JComboBox int10marks;
	private javax.swing.JComboBox int2marks;
	private javax.swing.JComboBox int3marks;
	private javax.swing.JComboBox int5marks;
	private com.toedter.calendar.JDateChooser jDateChooser1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JSpinner jSpinner3;
	private javax.swing.JSpinner jSpinner4;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JButton logout;
	private javax.swing.JButton submit;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JFrame parent;
	public boolean flagJSpinner3 = false;
	public boolean flagJSpinner4 = false;
	public boolean flagJDateChooser1 = false;

	@Override
	public void actionPerformed(ActionEvent e) {

		// String course = subject.getSelectedItem().toString();
		// JOptionPane.showMessageDialog(this, course);

		// System.out.println("Hello in action");
		// URL url = null;
		// try {
		// url = new URL("http://localhost/StudentData.html");
		// } catch (MalformedURLException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }
		// File f = new File("TA.html");
		// try {
		// org.apache.commons.io.FileUtils.copyURLToFile(url, f);
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		//

		// String d1 =
		// ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == jSpinner3) {
			flagJSpinner3 = true;
			// JOptionPane.showMessageDialog(this, "Please specify exam schedule");
		} else if (e.getSource() == jSpinner4) {
			flagJSpinner4 = true;
			// JOptionPane.showMessageDialog(this, "Please specify exam schedule");
		} else if (e.getSource() == jDateChooser1) {
			flagJDateChooser1 = true;
			// JOptionPane.showMessageDialog(this, "Please specify exam schedule");
		}

	}
}
