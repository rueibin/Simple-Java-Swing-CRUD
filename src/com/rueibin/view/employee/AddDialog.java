package com.rueibin.view.employee;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.rueibin.dao.DepartmentDAO;
import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.DepartmentDAOImpl;
import com.rueibin.dao.impl.EmployeeDAOImpl;
import com.rueibin.entity.Department;
import com.rueibin.entity.Employee;
import com.rueibin.exception.MyException;
import com.rueibin.util.MyUtil;

public class AddDialog extends JDialog {
	private JLabel nameJLabel, genderJLabel, emailJLabel, deptLabel;
	private JButton saveJButton, resetJButton;
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JTextField nameField, emailField;
	private JComboBox deptJComboBox;
	private JRadioButton manJRadioButton, womanJRadioButton;
	private ButtonGroup bg;
	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	public AddDialog() {		
		setSize(350, 240);
		setLocationRelativeTo(null);
		setTitle("新增員工");

		setModal(true);
		setLayout(new GridLayout(5, 1));

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();

		nameJLabel = new JLabel("員工名稱：");
		genderJLabel = new JLabel("員工性別：");
		emailJLabel = new JLabel("email：");
		deptLabel = new JLabel("所在部門：");

		nameField = new JTextField(10);
		emailField = new JTextField(10);

		// deptJComboBox = new JComboBox(departmentDAO.getDepartments().toArray());
		deptJComboBox = new JComboBox();
		initDept();

		manJRadioButton = new JRadioButton("男");
		manJRadioButton.setSelected(true);
		womanJRadioButton = new JRadioButton("女");
		bg = new ButtonGroup();

		bg.add(manJRadioButton);
		bg.add(womanJRadioButton);

		saveJButton = new JButton("新增員工");
		resetJButton = new JButton("重置資料");
		saveJButton.addActionListener(new addDialogClick());
		resetJButton.addActionListener(new addDialogClick());

		jp1.add(nameJLabel);
		jp1.add(nameField);

		jp2.add(genderJLabel);
		jp2.add(manJRadioButton);
		jp2.add(womanJRadioButton);

		jp3.add(emailJLabel);
		jp3.add(emailField);

		jp4.add(deptLabel);
		jp4.add(deptJComboBox);

		jp5.add(saveJButton);
		jp5.add(resetJButton);

		add(jp1);
		add(jp2);
		add(jp3);
		add(jp4);
		add(jp5);
	}

	private void initDept() {
		deptJComboBox.removeAllItems();
		List<Department> deptList = departmentDAO.getDepartments();
		for (Department dept : deptList) {
			this.deptJComboBox.addItem(dept);
		}
	}

	private class addDialogClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == saveJButton) {

					String name = nameField.getText();
					String nameRegex="[A-Za-z\u4E00-\u9FA5]*$";
					if (name == null || "".equals(name.trim())) {
						MyUtil.showError(jp1, "必須輸入員工名稱");
						return;
					}
					if(name.trim().length()>10) {
						MyUtil.showError(jp1, "員工名稱字串1-10字");
						return;
					}
					if (!name.matches(nameRegex)) {
						MyUtil.showError(jp1, "員工姓名只能是英文或中文字");
						return;
					}
					
					String email = emailField.getText();
					String mailRegex = "^(.+)@(.+)$";
					if (email == null || "".equals(email.trim())) {
						MyUtil.showError(jp1, "必須輸入email");
						return;
					}
					if (!email.matches(mailRegex)) {
						MyUtil.showError(jp1, "email格式錯誤");
						return;
					}
					int deptId = ((Department) deptJComboBox.getSelectedItem()).getId();

					int gender = 1;
					if (womanJRadioButton.isSelected()) {
						gender = 2;
					}

					Employee emp = new Employee();
					emp.setName(name);
					emp.setEmail(email);
					emp.setGender(gender);
					emp.setDeptId(deptId);
					if (employeeDAO.save(emp) == 1) {
						MyUtil.showSuccess(jp1, "新增成功");
						reset();
						hideDailog();
					}
				}else {
					reset();
				}
			} catch (MyException e1) {
				MyUtil.showError(jp1, e1.getMessage());
			}
		}

	}

	private void reset() {
		nameField.setText("");
		emailField.setText("");
		manJRadioButton.setSelected(true);
		deptJComboBox.setSelectedIndex(0);
	}

	private void hideDailog() {
		setVisible(false);
	}
}
