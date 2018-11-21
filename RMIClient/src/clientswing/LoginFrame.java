package clientswing;

import java.awt.Frame;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.AddI;
import common.AddO;
import pojo.ServerList;
import pojo.Studentreg;
import pojo.Substudent;
import pojo.Teacherreg;

public class LoginFrame extends javax.swing.JFrame {

	// private static final String User = null;
	private static ArrayList<Integer> subids = new ArrayList<>();
	String action;

	private AddI serverObj;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancel;

	private javax.swing.JButton btnLogin;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JPasswordField txtPassword;

	private javax.swing.JTextField txtUserId;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JFrame parent;
	private AddO server1;
	private AddO server2;

	/**
	 * Creates new form LoginFrame
	 * 
	 * @param serverObj
	 * @param server
	 * @param server2
	 */
	public LoginFrame(String action, AddI serverObj, AddO server1, AddO server2) {
		this.serverObj = serverObj;
		this.server1 = server1;
		this.server2 = server2;
		this.action = action;
		initComponents();
		   setTitle("WELCOME TO ONLINE QUIZ SYSTEM ");

	}

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		parent.setVisible(true);
		this.dispose();
	}// GEN-LAST:event_btnCancelActionPerformed

	private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException, NotBoundException {// GEN-FIRST:event_btnLoginActionPerformed
		String userId = txtUserId.getText();
		String passwd = new String(txtPassword.getPassword());// d
		Boolean flag = true;

		if (action.equals("student")) {
			Studentreg User = null;
			while (flag) {

				try {
					/// *****Add cone only here////

					User = (Studentreg) this.serverObj.loginStudent(userId, passwd);
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
			if (User != null) {

				Boolean flag3 = true;

				List<Object> subs = null;

				while (flag3) {

					try {
						/// *****Add cone only here////

						subs = this.serverObj.getStudentSubjects(User.getSid());

						System.out.println("making call");
						flag3 = false;

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

				if (subs != null) {
					for (Object i : subs) {
						subids.add(((Substudent) i).getId().getSubid());

						System.out.println("subs le aya");
					}
					Boolean flag2 = true;
					List<Object> papers = null;

					while (flag2) {

						try {
							/// *****Add cone only here////

							papers = this.serverObj.getPapers(subids);

							System.out.println("making call");
							flag2 = false;

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
								Registry registrymain = LocateRegistry.getRegistry(serving.getMainIP(),
										serving.getMainPORT());
								this.serverObj = (AddI) registrymain.lookup(serving.getMainOBJ());
							} catch (Exception exxs) {
								// TODO: handle exception
							}
						}
					}

					StudentTests f = new StudentTests(serverObj, User, papers, this.server1, this.server2);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					f.setExtendedState(Frame.MAXIMIZED_BOTH);
					this.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(this, "User Id or Password is Incorrect");
				txtUserId.requestFocus();
			}

		} else if (action.equals("admin")) {
			Teacherreg TUser = null;

			while (flag) {

				try {
					/// *****Add cone only here////

					TUser = (Teacherreg) this.serverObj.teacherLogin(userId, passwd);
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
			if (TUser != null) {

				TeacherMain f = new TeacherMain(TUser, serverObj, server1, server2);
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				f.setExtendedState(Frame.MAXIMIZED_BOTH);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "User Id or Password is Incorrect");
				txtUserId.requestFocus();
			}

		}
	}

	// GEN-LAST:event_btnLoginActionPerformed
	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
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

		setExtendedState(Frame.MAXIMIZED_BOTH);
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		txtUserId = new javax.swing.JTextField();
		btnLogin = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		txtPassword = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
		jLabel1.setText("Login Form");

		jLabel2.setText("User Id :");

		jLabel3.setText("Password : ");

		btnLogin.setText("Login");
		btnLogin.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnLoginActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel3).addComponent(jLabel2))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txtUserId, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))))
						.addGroup(layout.createSequentialGroup().addGap(70, 70, 70).addComponent(btnLogin)
								.addGap(18, 18, 18).addComponent(btnCancel)))
				.addContainerGap(24, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel2).addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel3).addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnLogin).addComponent(btnCancel))
						.addContainerGap(24, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void setParentFrame(JFrame f) {
		parent = f;
	}
}
