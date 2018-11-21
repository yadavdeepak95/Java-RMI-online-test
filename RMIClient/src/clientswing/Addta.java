package clientswing;

import java.awt.Frame;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.AddI;
import common.AddO;
import pojo.ServerList;
import pojo.Subteacher;
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
public class Addta extends javax.swing.JFrame {

	/**
	 * Creates new form addta
	 */
	AddO server1, server2;
	List<Object> subs = null;
	String[] subjects = null;
	Teacherreg user = null;
	private String selectedSubject;
	private AddI serverObj;
	private Integer selectedSubjects;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnRegister;

	private javax.swing.JComboBox jComboBox1;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel12;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPasswordField txtConfirmPassword;
	private javax.swing.JTextField txtFirstName;
	private javax.swing.JTextField txtLastName;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtUserId;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JFrame parent;

	public Addta(List<Object> subs, Teacherreg user, AddI serverObj, AddO server1, AddO server2) {
		this.subs = subs;
		this.user = user;
		this.server1 = server1;
		this.server2 = server2;
		this.serverObj = serverObj;
		this.subjects = new String[this.subs.size()];
		// this.subjects[0] = "----------";
		for (int i = 0; i < this.subs.size(); i++) {
			subjects[i] = Integer.toString(((Subteacher) this.subs.get(i)).getId().getSubid());

		}

		initComponents();
		  setTitle("WELCOME TO ONLINE QUIZ SYSTEM ");
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
		selectedSubjects = ((Subteacher) subs.get((this.jComboBox1.getSelectedIndex()))).getId().getSubid();

		Integer id = null;
		boolean flag = true;

		while (flag) {

			try {
				/// *****Add cone only here////

				id = this.serverObj.regiserTA(sname, semail, spassword, this.selectedSubjects);
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
		setExtendedState(Frame.MAXIMIZED_BOTH);
		txtFirstName = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		txtLastName = new javax.swing.JTextField();
		txtUserId = new javax.swing.JTextField();
		txtPassword = new javax.swing.JPasswordField();
		txtConfirmPassword = new javax.swing.JPasswordField();
		jLabel1 = new javax.swing.JLabel();
		btnRegister = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		btnCancel = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jLabel7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel7.setText("Roll:");

		jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel5.setText("First Name :");

		jComboBox1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(subjects));

		jLabel6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel6.setText("Last Name :");

		jLabel8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel8.setText("Subject:");

		jLabel1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel1.setText("User Id :");

		btnRegister.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		btnRegister.setText("Register");
		btnRegister.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegisterActionPerformed(evt);
			}
		});

		jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel2.setText("Password :");

		btnCancel.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});

		jLabel3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel3.setText("Confirm Password :");

		jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
		jLabel4.setText("Registration Form");

		jLabel12.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
		jLabel12.setText("T.A");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5)
						.addComponent(jLabel6).addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3)
						.addComponent(jLabel7).addComponent(jLabel8))
				.addGap(27, 27, 27)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(txtUserId, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(txtConfirmPassword,
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 141,
														javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(0, 0, Short.MAX_VALUE)))
								.addGap(27, 27, 27))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel12))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(108, 108, 108).addComponent(jLabel4))
								.addGroup(layout.createSequentialGroup().addGap(69, 69, 69).addComponent(btnRegister)
										.addGap(29, 29, 29).addComponent(btnCancel)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(jLabel4).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7)
						.addComponent(jLabel12))
				.addGap(24, 24, 24)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5)
						.addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6)
						.addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
						.addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3)
						.addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel8))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnCancel)
						.addComponent(btnRegister))
				.addGap(34, 34, 34)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void setParentFrame(JFrame f) {
		parent = f;
	}

}
