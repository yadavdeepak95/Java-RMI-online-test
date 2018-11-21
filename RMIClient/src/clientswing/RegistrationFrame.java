package clientswing;

import java.awt.Frame;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.AddI;
import common.AddO;
import pojo.ServerList;
import pojo.Subject;

public class RegistrationFrame extends javax.swing.JFrame {

	private static final String[] Integer = null;

	private AddI serverObj;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnRegister;
	private javax.swing.ButtonGroup chooseroll;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JRadioButton rbstudent;
	private javax.swing.JRadioButton rbteacher;
	private javax.swing.JPasswordField txtConfirmPassword;
	private javax.swing.JTextField txtFirstName;
	private javax.swing.JTextField txtLastName;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtUserId;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JFrame parent;
	/**
	 * Creates new form RegistrationFrame
	 * 
	 * @param server2
	 * @param server
	 */
	private AddO server1;
	private AddO server2;

	private java.util.List<Object> subs;
	private Integer[] selectedSubjects = new Integer[4];
	private String[] subjects;

	public RegistrationFrame(AddI serverObj, AddO server1, AddO server2) {
		this.serverObj = serverObj;
		this.server1 = server1;
		this.server2 = server2;

		boolean flag = true;
		while (flag) {

			try {
				/// *****Add code only here////

				subs = this.serverObj.getAllSubjects();
				subjects = new String[subs.size() + 1];
				this.subjects[0] = "----------";
				int count = 1;
				for (Object sub : subs) {
					subjects[count] = ((Subject) sub).getSubname();
					System.out.println(subjects[count]);
					count++;
				}
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

		initComponents();
		   setTitle("WELCOME TO ONLINE QUIZ SYSTEM ");
		rbstudent.setSelected(true);

	}

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		parent.setVisible(true);
		this.dispose();
	}// GEN-LAST:event_btnCancelActionPerformed

	private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRegisterActionPerformed
		String spassword = new String(txtPassword.getPassword());
		String s2 = new String(txtConfirmPassword.getPassword());
		if (spassword.equals(s2) == false) {
			JOptionPane.showMessageDialog(this, "Password do not MATCH");
			return;
		}
		String sname = txtFirstName.getText() + txtLastName.getText();
		String semail = txtUserId.getText();
		if (this.jComboBox1.getSelectedIndex() > 0) {
			selectedSubjects[0] = ((Subject) subs.get((this.jComboBox1.getSelectedIndex()) - 1)).getSubid();
		} else {
			selectedSubjects[0] = -1;
		}
		if (this.jComboBox2.getSelectedIndex() > 0) {
			selectedSubjects[1] = ((Subject) subs.get((this.jComboBox2.getSelectedIndex()) - 1)).getSubid();
		} else {
			selectedSubjects[1] = -1;
		}
		if (this.jComboBox3.getSelectedIndex() > 0) {
			selectedSubjects[2] = ((Subject) subs.get((this.jComboBox3.getSelectedIndex()) - 1)).getSubid();
		} else {
			selectedSubjects[2] = -1;
		}
		if (this.jComboBox4.getSelectedIndex() > 0) {
			selectedSubjects[3] = ((Subject) subs.get((this.jComboBox4.getSelectedIndex()) - 1)).getSubid();
		} else {
			selectedSubjects[3] = -1;
		}

		Integer id = null;
		boolean flag = true;
		if (rbteacher.isSelected()) {

			while (flag) {

				try {
					/// *****Add cone only here////

					id = this.serverObj.regiserTeacher(sname, semail, spassword, this.selectedSubjects);
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

		} else if (rbstudent.isSelected()) {

			try {
				/// *****Add cone only here////
				if (this.jComboBox1.getSelectedIndex() > 0) {
					selectedSubjects[0] = ((Subject) subs.get((this.jComboBox1.getSelectedIndex()) - 1)).getSubid();
				} else {
					selectedSubjects[0] = -1;
				}
				if (this.jComboBox2.getSelectedIndex() > 0) {
					selectedSubjects[1] = ((Subject) subs.get((this.jComboBox2.getSelectedIndex()) - 1)).getSubid();
				} else {
					selectedSubjects[1] = -1;
				}
				if (this.jComboBox3.getSelectedIndex() > 0) {
					selectedSubjects[2] = ((Subject) subs.get((this.jComboBox3.getSelectedIndex()) - 1)).getSubid();
				} else {
					selectedSubjects[2] = -1;
				}
				if (this.jComboBox4.getSelectedIndex() > 0) {
					selectedSubjects[3] = ((Subject) subs.get((this.jComboBox4.getSelectedIndex()) - 1)).getSubid();
				} else {
					selectedSubjects[3] = -1;
				}
				id = serverObj.regiserStudent(sname, semail, spassword, this.selectedSubjects);
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

		if (id == 0) {
			JOptionPane.showMessageDialog(this, "User Id already exists\nSelect a different User Id");
		} else if (id > 0) {
			JOptionPane.showMessageDialog(this, "Registration Successful");
			parent.setVisible(true);
			this.dispose();

		} else {
			JOptionPane.showMessageDialog(this, "Database Error");
		}
		/*
		 * **************for combo box********* String subject1 =
		 * jComboBox1.getSelectedItem().toString(); String subject2 =
		 * jComboBox2.getSelectedItem().toString(); String subject3 =
		 * jComboBox3.getSelectedItem().toString(); String subject4 =
		 * jComboBox4.getSelectedItem().toString();
		 */

	}// GEN-LAST:event_btnRegisterActionPerformed

	private void formWindowClosed(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosed
		// TODO add your handling code here:
	}// GEN-LAST:event_formWindowClosed

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
		chooseroll = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		txtFirstName = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		txtLastName = new javax.swing.JTextField();
		txtUserId = new javax.swing.JTextField();
		txtPassword = new javax.swing.JPasswordField();
		txtConfirmPassword = new javax.swing.JPasswordField();
		btnRegister = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		rbteacher = new javax.swing.JRadioButton();
		rbstudent = new javax.swing.JRadioButton();
		jLabel7 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jComboBox3 = new javax.swing.JComboBox();
		jComboBox4 = new javax.swing.JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}

			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel1.setText("User Id :");

		jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel2.setText("Password :");

		jLabel3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel3.setText("Confirm Password :");

		jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
		jLabel4.setText("Registration Form");

		jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel5.setText("First Name :");

		jLabel6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel6.setText("Last Name :");

		btnRegister.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		btnRegister.setText("Register");
		btnRegister.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegisterActionPerformed(evt);
			}
		});

		btnCancel.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});

		chooseroll.add(rbteacher);
		rbteacher.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbteacher.setText("As Teacher");

		chooseroll.add(rbstudent);
		rbstudent.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		rbstudent.setText("As student");

		jLabel7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel7.setText("Choose roll:");

		jComboBox1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(subjects));
		jComboBox1.setSelectedIndex(0);
		jLabel8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel8.setText("Subject1:");

		jLabel9.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel9.setText("Subject2:");

		jLabel10.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel10.setText("Subject3:");

		jLabel11.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel11.setText("Subject4:");

		jComboBox2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(subjects));
		jComboBox2.setSelectedIndex(0);

		jComboBox3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(subjects));
		jComboBox3.setSelectedIndex(0);

		jComboBox4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(subjects));
		jComboBox4.setSelectedIndex(0);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel5).addComponent(jLabel6).addComponent(jLabel1).addComponent(jLabel2)
								.addComponent(jLabel3).addComponent(jLabel7).addComponent(jLabel8).addComponent(jLabel9)
								.addComponent(jLabel10).addComponent(jLabel11))
						.addGap(27, 27, 27)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(rbteacher)
														.addGap(27, 27, 27).addComponent(rbstudent))
												.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(txtUserId, javax.swing.GroupLayout.DEFAULT_SIZE, 321,
														Short.MAX_VALUE)
												.addComponent(
														txtLastName, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
												.addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 321,
														Short.MAX_VALUE)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		false)
																.addComponent(txtConfirmPassword,
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(txtPassword,
																		javax.swing.GroupLayout.Alignment.LEADING,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 141,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(0, 0, Short.MAX_VALUE)))
										.addGap(27, 27, 27))))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(108, 108, 108).addComponent(jLabel4))
								.addGroup(layout.createSequentialGroup().addGap(65, 65, 65).addComponent(btnRegister)
										.addGap(29, 29, 29).addComponent(btnCancel)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel4).addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(rbteacher).addComponent(rbstudent).addComponent(jLabel7))
						.addGap(25, 25, 25)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5)
								.addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel6).addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3)
								.addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel8))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel9))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel10))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel11))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCancel).addComponent(btnRegister))
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void setParentFrame(JFrame f) {
		parent = f;
	}

}