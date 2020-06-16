package burp.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextPane;






public class CmdPanel extends JPanel  {

	private static final long serialVersionUID = 1L;

	
	
//	JLabel cmdJLabel = new JLabel("author wangmeng");
	public JTextArea textArea1;
	public JTextArea textArea2;
	

	public CmdPanel() {
		setSize(600, 460);
		textArea1 = new JTextArea();
		textArea2 = new JTextArea();
//		JScrollPane scrollPane1 = new JScrollPane(textArea1);
//		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE).addComponent(textArea2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))));
		
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGap(10).addComponent(textArea1, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE).addComponent(textArea2, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addContainerGap()));
		setLayout(groupLayout);
		setVisible(true);
	}


	

	
}