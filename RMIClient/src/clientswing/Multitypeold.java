package clientswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import common.AddI;
import common.AddO;
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
public class Multitypeold extends javax.swing.JFrame implements ActionListener {
	Timer tr;
	Teacherreg user;
	String selectedSubject;
	AddI serverObj;
	AddO server1;
	AddO server2;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel answer;

	private javax.swing.JLabel autograded;
	private javax.swing.ButtonGroup bgauto;

	private javax.swing.ButtonGroup bglevel;

	private javax.swing.ButtonGroup bgmarks;

	private javax.swing.ButtonGroup bgtime;
	private javax.swing.JComboBox cbans;
	private javax.swing.JLabel estimatedtime;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JLabel level;
	private javax.swing.JLabel marks;
	private javax.swing.JLabel option1;
	private javax.swing.JLabel option2;
	private javax.swing.JLabel option3;
	private javax.swing.JLabel option4;
	private javax.swing.JLabel question;
	private javax.swing.JRadioButton rb10marks;
	private javax.swing.JRadioButton rb2marks;
	private javax.swing.JRadioButton rb2minute;
	private javax.swing.JRadioButton rb3marks;
	private javax.swing.JRadioButton rb5marks;
	private javax.swing.JRadioButton rb5minute;
	private javax.swing.JRadioButton rb7minute;
	private javax.swing.JRadioButton rbbeginner;
	private javax.swing.JRadioButton rbdisable;
	private javax.swing.JRadioButton rbenable;
	private javax.swing.JRadioButton rbexpert;
	private javax.swing.JRadioButton rbintermediate;
	private javax.swing.JButton submit;
	private javax.swing.JLabel timer;
	private javax.swing.JLabel username;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JFrame parent;

	public Multitypeold(Teacherreg user, String selectedSubject, AddI serverObj, AddO server1, AddO server2) {
		this.user = user;
		this.selectedSubject = selectedSubject;
		this.serverObj = serverObj;
		this.server1 = server1;
		this.server2 = server2;
		initComponents();
		submit.addActionListener(this);
		/*
		 * cblevel.addItemListener(this); cbtime.addItemListener(this);
		 * cbauto.addItemListener(this); cbmarks.addItemListener(this);
		 * cbans.addItemListener(this);
		 */
		showTime();
		tr = new Timer(1000, this);
		tr.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int level1 = 0;
		int marks1 = 0;
		boolean autograded1 = false;
		int estitime1 = 0;
		if (e.getSource() == tr) {// this is for timer
			showTime();
		}
		String str1 = "string of student name  ";
		username.setText(str1);

		if (e.getSource() == submit && (cbans.getSelectedIndex() > 0)) {
			String question = "";// this for save question
			question = jTextArea1.getText();

			/*
			 * level1 =cblevel.getSelectedItem().toString();
			 * 
			 * 
			 * marks1 =cbmarks.getSelectedItem().toString();
			 * 
			 * 
			 * estitime1 =cbtime.getSelectedItem().toString();
			 * 
			 * 
			 * autograded1 =cbauto.getSelectedItem().toString();
			 * 
			 * JOptionPane.showMessageDialog(this, ans);
			 */

			// JOptionPane.showMessageDialog(this, str);
			if (rbbeginner.isSelected()) {// this is for level
				rbintermediate.setSelected(false);
				rbexpert.setSelected(false);
				level1 = 1;
			} else if (rbintermediate.isSelected()) {
				rbexpert.setSelected(false);
				rbbeginner.setSelected(false);
				level1 = 2;
			} else if (rbexpert.isSelected()) {
				rbintermediate.setSelected(false);
				rbbeginner.setSelected(false);
				level1 = 3;
			}
			// for marks
			if (rb2marks.isSelected()) {
				rb5marks.setSelected(false);
				rb3marks.setSelected(false);
				rb10marks.setSelected(false);
				marks1 = 1;
			} else if (rb3marks.isSelected()) {
				rb2marks.setSelected(false);
				rb5marks.setSelected(false);
				rb10marks.setSelected(false);
				marks1 = 2;
			} else if (rb5marks.isSelected()) {
				rb2marks.setSelected(false);
				rb3marks.setSelected(false);
				rb10marks.setSelected(false);
				marks1 = 3;
			} else if (rb10marks.isSelected()) {
				rb2marks.setSelected(false);
				rb3marks.setSelected(false);
				rb5marks.setSelected(false);
				marks1 = 4;
			}
			// //this is for estimated time

			if (rb2minute.isSelected()) {
				rb5minute.setSelected(false);
				rb7minute.setSelected(false);
				estitime1 = 2;
			} else if (rb5minute.isSelected()) {
				rb2minute.setSelected(false);
				rb7minute.setSelected(false);
				estitime1 = 5;
			} else if (rb7minute.isSelected()) {
				rb5minute.setSelected(false);
				rb2minute.setSelected(false);
				estitime1 = 7;
			}

			// this is for autograded option

			if (rbenable.isSelected()) {
				rbdisable.setSelected(false);
				autograded1 = true;
			} else if (rbdisable.isSelected()) {
				rbenable.setSelected(false);
				autograded1 = false;
			}
			Question ques = new Question();
			ques.setMarks(marks1);
			ques.setQgraded(autograded1);
			ques.setQcreationdate(new Date());
			ques.setQlevel(level1);
			ques.setQoption(jTextField1.getText() + "~" + jTextField3.getText() + "~" + jTextField2.getText() + "~"
					+ jTextField4.getText() + "~");
			ques.setQquestion(question);
			// TODO option update
			ques.setQsolution(Integer.toString(cbans.getSelectedIndex()));
			ques.setSubid(Integer.parseInt(selectedSubject));
			ques.setQtype(1);
			ques.setTid(user.getTid());
			boolean flag = true;

			int result = -1;
			// RMI CALL
			while (flag) {

				try {
					/// *****Add cone only here////
					result = this.serverObj.addQuestion(ques);
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
						Registry registrymain = LocateRegistry.getRegistry(serving.getMainIP(), serving.getMainPORT());
						this.serverObj = (AddI) registrymain.lookup(serving.getMainOBJ());
					} catch (Exception exxs) {
						// TODO: handle exception
					}
				}
			}

			JOptionPane.showMessageDialog(this, result);

		}

	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		// TODO add your handling code here:
		parent.setVisible(true);
		this.dispose();

	}// GEN-LAST:event_formWindowClosing

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		bglevel = new javax.swing.ButtonGroup();
		bgmarks = new javax.swing.ButtonGroup();
		bgtime = new javax.swing.ButtonGroup();
		bgauto = new javax.swing.ButtonGroup();
		question = new javax.swing.JLabel();
		option1 = new javax.swing.JLabel();
		option2 = new javax.swing.JLabel();
		option3 = new javax.swing.JLabel();
		option4 = new javax.swing.JLabel();
		answer = new javax.swing.JLabel();
		submit = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		cbans = new javax.swing.JComboBox();
		timer = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		username = new javax.swing.JLabel();
		level = new javax.swing.JLabel();
		marks = new javax.swing.JLabel();
		estimatedtime = new javax.swing.JLabel();
		autograded = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		rb7minute = new javax.swing.JRadioButton();
		rbdisable = new javax.swing.JRadioButton();
		rbenable = new javax.swing.JRadioButton();
		rbbeginner = new javax.swing.JRadioButton();
		rbintermediate = new javax.swing.JRadioButton();
		rbexpert = new javax.swing.JRadioButton();
		rb2marks = new javax.swing.JRadioButton();
		rb3marks = new javax.swing.JRadioButton();
		rb5marks = new javax.swing.JRadioButton();
		rb10marks = new javax.swing.JRadioButton();
		rb2minute = new javax.swing.JRadioButton();
		rb5minute = new javax.swing.JRadioButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		question.setText("Question:");

		option1.setText("Option1:");

		option2.setText("Option2:");

		option3.setText("Option3:");

		option4.setText("Option4:");

		answer.setText("Answer:");

		submit.setText("Submit");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jScrollPane2.setViewportView(jScrollPane1);

		cbans.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "-", "option1", "option2", "option3", "option4" }));
		cbans.setSelectedIndex(-1);

		timer.setText("timer");

		jLabel8.setText("Welcome:");

		username.setText("username");

		level.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		level.setText("Level:");

		marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		marks.setText("Marks:");

		estimatedtime.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		estimatedtime.setText("Estimated time:");

		autograded.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		autograded.setText("Autograded:");

		jLabel1.setText("Multitype:");

		bgtime.add(rb7minute);
		rb7minute.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb7minute.setText("7minute");

		bgauto.add(rbdisable);
		rbdisable.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbdisable.setText("Disable");

		bgauto.add(rbenable);
		rbenable.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbenable.setText("Enable");

		bglevel.add(rbbeginner);
		rbbeginner.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbbeginner.setText("Beginner");

		bglevel.add(rbintermediate);
		rbintermediate.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbintermediate.setText("Intermediate");

		bglevel.add(rbexpert);
		rbexpert.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbexpert.setText("Expert");

		bgmarks.add(rb2marks);
		rb2marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb2marks.setText("2");

		bgmarks.add(rb3marks);
		rb3marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb3marks.setText("3");

		bgmarks.add(rb5marks);
		rb5marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb5marks.setText("5");

		bgmarks.add(rb10marks);
		rb10marks.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb10marks.setText("10");

		bgtime.add(rb2minute);
		rb2minute.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb2minute.setText("2minutes");

		bgtime.add(rb5minute);
		rb5minute.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rb5minute.setText("5minutes");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addComponent(submit))
										.addGroup(layout.createSequentialGroup().addComponent(jLabel8)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(username)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(timer)))
								.addGap(27, 27, 27))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(question)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(option4).addComponent(option3).addComponent(option2)
												.addComponent(option1))
										.addComponent(answer))
								.addGap(37, 37, 37)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
												.addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
										.addGroup(layout.createSequentialGroup()
												.addComponent(cbans, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGap(396, 396, 396)))
								.addGap(136, 137, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
												.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(level).addComponent(
																marks)
														.addComponent(estimatedtime).addComponent(autograded))
												.addGap(0, 0, 0)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(rbbeginner).addGap(18, 18, 18)
																.addComponent(rbintermediate))
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		false)
																.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
																		layout.createSequentialGroup()
																				.addComponent(rb2marks)
																				.addGap(38, 38, 38)
																				.addComponent(rb3marks)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(rb5marks))
																.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
																		layout.createSequentialGroup()
																				.addGroup(layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(rb2minute)
																						.addComponent(rbenable))
																				.addGap(18, 18, 18)
																				.addGroup(layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(rbdisable)
																						.addComponent(rb5minute)))))
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup().addGap(11, 11, 11)
																.addComponent(rb7minute))
														.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(rb10marks)
																		.addComponent(rbexpert)))))
										.addComponent(jLabel1))
								.addGap(0, 0, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(timer)
						.addComponent(jLabel8).addComponent(username))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel1)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(question)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(27, 27, 27)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(option1))
				.addGap(27, 27, 27)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(option2)
						.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(25, 25, 25)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(option3)
						.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(option4)
						.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(36, 36, 36)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(answer)
						.addComponent(cbans, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(level).addGap(27, 27, 27).addComponent(marks).addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(estimatedtime).addComponent(rb2minute).addComponent(rb5minute)
										.addComponent(rb7minute))
								.addGap(10, 10, 10)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(autograded).addComponent(rbenable).addComponent(rbdisable)))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(rbbeginner).addComponent(rbintermediate).addComponent(rbexpert))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(rb2marks).addComponent(rb5marks).addComponent(rb10marks)
										.addComponent(rb3marks))
								.addGap(83, 83, 83)))
				.addGap(6, 6, 6).addComponent(submit).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Creates new form Multitype
	 */
	public void setParentFrame(JFrame f) {
		parent = f;
	}

	void showTime() {
		Date d = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss a");
		String str = fmt.format(d);
		timer.setText(str);
	}

}
