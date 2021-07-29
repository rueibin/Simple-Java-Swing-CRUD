package com.rueibin.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rueibin.view.employee.EmployeePanel;

public class ManagerFrame extends JFrame{
	
	private JPanel jp;
	private EmployeePanel employeePanel;

	public ManagerFrame() {
		setSize(650, 400);
		setLocationRelativeTo(null);
		setTitle("歡迎使用系統");
	
		jp = new JPanel(new BorderLayout());
		employeePanel=new EmployeePanel(this);
		jp.add(employeePanel);
		
		add(jp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
