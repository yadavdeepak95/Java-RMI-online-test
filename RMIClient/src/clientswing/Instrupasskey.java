package clientswing;
//latest
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.bind.ParseConversionEvent;

import clientExamSwing.Condexmmulans;
import clientExamSwing.Condexmmultype;
import clientExamSwing.Condexmsub;
import common.AddI;
import common.AddO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import pojo.Paperreg;
import pojo.Question;
import pojo.ServerList;
import pojo.Solution;
import pojo.SolutionId;
import pojo.Studentreg;
import pojo.Subject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author naruto
 */
public class Instrupasskey extends javax.swing.JFrame implements ActionListener {
    
	// Variables declaration - do not modify//GEN-BEGIN:variables
//		private javax.swing.JLabel jLabel1;
//
//		private javax.swing.JLabel jLabel2;
//
//		private javax.swing.JLabel jLabel3;
//
//		private javax.swing.JButton next;
//		private javax.swing.JPasswordField passkey;
//		private javax.swing.JLabel username;
		/**
		 * Creates new form instrupasskey
		 * 
		 * @param server2
		 * @param server
		 * @param selectedpaper
		 * @param user
		 * @param serverObj
		 */
		AddI serverObj;
		AddO server1;
		AddO server2;
		Studentreg user;
		ArrayList<Object> questionList = null;
		private Paperreg selectedpaper;

		private Solution solution;

		private JFrame parent;

		public Instrupasskey(AddI serverObj, Object user, Paperreg selectedpaper, AddO server1, AddO server2) {
			this.user = (Studentreg) user;
			this.selectedpaper = selectedpaper;
			this.serverObj = serverObj;
			this.server1 = server1;
			this.server2 = server2;
			System.out.println(selectedpaper.getPid());
			initComponents();
			   setTitle("WELCOME TO ONLINE QUIZ SYSTEM ");
			showTime();
	        tr = new Timer(1000,this);
	        tr.start(); 
			next.addActionListener(this);
		}
     Timer tr; 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton next;
    private javax.swing.JPasswordField passkey;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel User;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
	@Override
	public void actionPerformed(ActionEvent e) {
               if(e.getSource()==tr){
                      showTime();
        }
		String passwd = "";
		if (e.getSource() == next) {
			passwd = new String(passkey.getPassword());
			if (passwd.equals(selectedpaper.getPasskey())) {
				//JOptionPane.showMessageDialog(this, "Passed");

				// try to get solution if seceed do something else

				Integer canStart = -1;
				boolean flag2 = true;
				while (flag2) {

					try {
						/// *****Add cone only here////
						canStart = this.serverObj.canStartExam(this.selectedpaper);

						if (canStart == 1) {
							System.out.println("Passingsid+pid" + user.getSid() + selectedpaper.getPid());
							try {
								this.solution = (Solution) this.serverObj.getSolutions(user.getSid(),
										selectedpaper.getPid());
								if (this.solution == null) {
									System.out.println("in null in getting sultion");
								}
							} catch (Exception nul) {
								System.out.println("IN NULL");
							} // System.out.println("making call");
						} else if (canStart == 2) {
							JOptionPane.showMessageDialog(this, "Exam is not Started");
							parent.setVisible(true);
							this.dispose();
							break;
						} else if (canStart == 3) {
							JOptionPane.showMessageDialog(this, "Exam Ended. You are late");
							parent.setVisible(true);
							this.dispose();
							break;
						}
						System.out.println("making call");
						flag2 = false;

					} catch (Exception ee) {
						// System.out.println("will get new serverobject and the recall the function
						// again");
						ServerList serving = null;
						try {
							// System.out.println("Calling server");
							serving = server1.giveServer();
							// System.out.println("After call server");

						} catch (Exception ex) {
							// System.err.println("Server1 failed to give server calling server2");
							try {
								serving = server2.giveServer();
							} catch (Exception ex2) {
								// TODO EXIT POPUP
								JOptionPane.showMessageDialog(this,"No server to serve at this moment try again Later");
								System.err.println("NO SERVER TO SERVE");
								// TODO ADD OPOP
								System.exit(1);
							}
						}
						//
						// System.out.println("Getting new server obj");
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
				// if(solution!=null) {
				// System.exit(1);
				// }
				if (canStart == 1) {
					if (this.solution == null) {
						System.out.println("NULL");
						// System.exit(1);
						boolean flag = true;

						// int result = -1;
						// RMI CALL

						while (flag) {

							try {
								try {
									this.questionList = this.serverObj.getQuestions(this.selectedpaper, user);
								} catch (Exception eq) {
									System.out.println("In Null getting question");
									//System.exit(1);
									// TODO: handle exception
								}
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
										JOptionPane.showMessageDialog(this,"No server to serve at this moment try again Later");
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
						if (this.questionList == null) {
							JOptionPane.showMessageDialog(this, "No questions in Question Paper");
						}

						else {
							Integer result = makeEntrySolution(this.questionList);
							if (result == -1) {
								JOptionPane.showMessageDialog(this, "Some Unknown DB Error try again");
							}  else if (result == 1) {
								showQuestion(this.questionList, 0);
							}

						}

					} else {

						showQuestion(this.questionList, 1);
						// TODO CALL START EXAM WITH 1;
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Wrong Pass key Try Again");
				
			}
		}
	}

	private Integer makeEntrySolution(ArrayList<Object> questionList) {
		String questions = "";
		Solution sol = new Solution();
		for (Object o : questionList) {
			questions = questions + ((Question) o).getQid() + "#&#";
		}
		sol.setQuesid(questions);
		sol.setSid(this.selectedpaper.getSubid());
		SolutionId id = new SolutionId();
		id.setPid(this.selectedpaper.getPid());
		id.setUid(this.user.getSid());
		sol.setId(id);

		Integer result = -4;
		boolean flag = true;
		int count = 1;
		while (flag) {
			count = 0;
			try {
				/// *****Add code only here////
				System.out.println("Registering solution");
				result = this.serverObj.startExam(sol);
				System.out.println("Make entry");
				// System.exit(1);
				System.out.println("making call");
				flag = false;

			} catch (Exception e) {
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
						JOptionPane.showMessageDialog(this,"No server to serve at this moment try again Later");
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
		return result;

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        passkey = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        User = new javax.swing.JLabel();
        timer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        username.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        username.setText("Username:");

        jLabel3.setText("Check the exam timetable carefully. \nMake sure you know  the time and locations of your exams . \n Check whether you should go  directly to an exam hall or a waiting room.\nDo not bring any unauthorised material (e.g. written notes, notes in dictionaries, paper,");

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText(" Exam Instructions:");

        passkey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passkeyKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel2.setText("PASSKEY:");

        next.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        next.setText("Next");

        jLabel4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel4.setText("Start Time");

        jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel5.setText("Subject Id:");

        back.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel6.setText((this.selectedpaper.getPstarttime()).toString());

        jLabel7.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel7.setText(Integer.toString(this.selectedpaper.getSubid()));

        User.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        User.setText(user.getSname());

        timer.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        timer.setText("Timecounter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(User)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back)
                                .addGap(155, 155, 155)
                                .addComponent(next))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(passkey, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {back, next});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username)
                    .addComponent(User)
                    .addComponent(timer))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(back))
                .addGap(58, 58, 58))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {back, next});

        pack();
    }// </editor-fold>//GEN-END:initComponents
        /*showTime();
        tr = new Timer(1000,this);
        tr.start();     */  
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        
    }//GEN-LAST:event_backActionPerformed
 void showTime(){
        Date d = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss a");
        String str = fmt.format(d);
        timer.setText(str);
    }
	private void passkeyKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_passkeyKeyPressed
		// TODO add your handling code here:
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

			String passwd = new String(passkey.getPassword());
			// here put also logic which use in next button
		}

	}// GEN-LAST:event_passkeyKeyPressed

	private void showQuestion(ArrayList<Object> questionList, int i) {

		if (i == 1) {
			ArrayList<Integer> qids = new ArrayList<>();
			for (String s : Arrays.asList((this.solution.getQuesid()).split("\\s*#&#\\s*"))) {
				qids.add(Integer.parseInt(s));
			}
				
			
				boolean flag = true;

				// int result = -1;
				// RMI CALL

				while (flag) {

					try {
						try {
							this.questionList = (ArrayList<Object>) this.serverObj.getQuestionListFromQuids(qids);
						} catch (Exception eq) {
							System.out.println("In Null getting question");
							System.exit(1);
							// TODO: handle exception
						}
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
								JOptionPane.showMessageDialog(this,"No server to serve at this moment try again Later");
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

			
		//	System.out.println("quessize " +this.questionList.size());
			System.out.println(this.solution.getSolution());
			//System.out.println(Arrays.asList((this.solution.getSolution()).split("\\s*#&#\\s*")).size());
			if (this.solution.getSolution()!=null) {
				System.out.println(this.solution.getSolution());
				if (Arrays.asList((this.solution.getSolution()).split("\\s*#&#\\s*")).size() == this.questionList.size()) {
					JOptionPane.showMessageDialog(this,
					
							"YOU HAVE ATTEMPTED ALL QUESTIONS");
							parent.setVisible(true);
							this.dispose();
				} else {
					
				//	JOptionPane.showMessageDialog(this, "Showing Question", "S", JOptionPane.PLAIN_MESSAGE);
					long duration = (this.selectedpaper.getEndtime().getTime()
							- this.solution.getLastsubmit().getTime()) / 1000;
					
					
					int questionNo = (Arrays.asList((solution.getSolution()).split("\\s*#&#\\s*")).size()+1);
					int frametype = ((Question) this.questionList.get(questionNo)).getQtype();
					Integer paperId = this.selectedpaper.getPid();
					Integer subId = this.selectedpaper.getSubid();
					if (frametype == 1) {
						Condexmmultype f = new Condexmmultype(duration, user, serverObj, server1, server2, questionNo,
								this.questionList, paperId, subId);
						f.setParentFrame(parent);
						f.setLocationRelativeTo(null);
						f.setVisible(true);
						f.setExtendedState(Frame.MAXIMIZED_BOTH);
						this.setVisible(false);
					} else if (frametype == 2) {
						Condexmmulans f = new Condexmmulans(duration, user, serverObj, server1, server2, questionNo,
								this.questionList, paperId, subId);
						f.setParentFrame(parent);
						f.setLocationRelativeTo(null);
						f.setVisible(true);
						f.setExtendedState(Frame.MAXIMIZED_BOTH);
						this.setVisible(false);
					} else if (frametype == 3) {
						Condexmsub f = new Condexmsub(duration, user, serverObj, server1, server2, questionNo,
								this.questionList, paperId, subId);
						f.setParentFrame(parent);
						f.setLocationRelativeTo(null);
						f.setVisible(true);
						f.setExtendedState(Frame.MAXIMIZED_BOTH);
						this.setVisible(false);
					}

				}
			} else {

				JOptionPane.showMessageDialog(this, "Starting exam :Showing Question");

				long duration = (this.selectedpaper.getEndtime().getTime() - this.solution.getLastsubmit().getTime())
						/ 1000;
				int frametype = ((Question) this.questionList.get(0)).getQtype();
				int questionNo = 1;
				Integer paperId = this.selectedpaper.getPid();
				Integer subId = this.selectedpaper.getSubid();
				if (frametype == 1) {
					Condexmmultype f = new Condexmmultype(duration, user, serverObj, server1, server2, questionNo,
							this.questionList, paperId, subId);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					f.setExtendedState(Frame.MAXIMIZED_BOTH);
					this.setVisible(false);
				} else if (frametype == 2) {
					Condexmmulans f = new Condexmmulans(duration, user, serverObj, server1, server2, questionNo,
							this.questionList, paperId, subId);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					f.setExtendedState(Frame.MAXIMIZED_BOTH);
					this.setVisible(false);
				} else if (frametype == 3) {
					Condexmsub f = new Condexmsub(duration, user, serverObj, server1, server2, questionNo,
							this.questionList, paperId, subId);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					f.setExtendedState(Frame.MAXIMIZED_BOTH);
					this.setVisible(false);
				}
			}
		} else {

			JOptionPane.showMessageDialog(this, "Starting exam :Showing Question");
			long duration = (this.selectedpaper.getEndtime().getTime() - this.selectedpaper.getPstarttime().getTime() )
					/ 1000;
			int frametype = ((Question) questionList.get(0)).getQtype();
			int questionNo = 1;
			Integer paperId = this.selectedpaper.getPid();
			Integer subId = this.selectedpaper.getSubid();
			if (frametype == 1) {
				Condexmmultype f = new Condexmmultype(duration, user, serverObj, server1, server2, questionNo,
						this.questionList, paperId, subId);
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				f.setExtendedState(Frame.MAXIMIZED_BOTH);
				this.setVisible(false);
			} else if (frametype == 2) {
				Condexmmulans f = new Condexmmulans(duration, user, serverObj, server1, server2, questionNo,
						this.questionList, paperId, subId);
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				f.setExtendedState(Frame.MAXIMIZED_BOTH);
				this.setVisible(false);
			} else if (frametype == 3) {
				Condexmsub f = new Condexmsub(duration, user, serverObj, server1, server2, questionNo,
						this.questionList, paperId, subId);
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				f.setExtendedState(Frame.MAXIMIZED_BOTH);
				this.setVisible(false);
			}
		}
	}

	public void setParentFrame(JFrame f) {
		parent = f;
		// TODO Auto-generated method stub
		
	}
}
