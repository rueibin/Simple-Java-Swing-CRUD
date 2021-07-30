package com.rueibin.view.employee;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.EmployeeDAOImpl;
import com.rueibin.entity.Employee;
import com.rueibin.exception.MyException;
import com.rueibin.util.MyUtil;
import com.rueibin.view.ManagerFrame;

public class EmployeePanel extends JPanel {

	private JPanel topJPanel, footerJPanel;
	private JLabel searchNameJLabel;
	private JTable empJTable;
	private DefaultTableModel empModel;
	private JScrollPane empJScrollPane;
	private JButton addJButton, deleteJButton, updateJButton, searchJButton;
	private JTextField searchNameField;
	private ManagerFrame jf;
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private AddDialog addDialog;
	private UpdateDialog updateDialog;

	public EmployeePanel(ManagerFrame jf) {
		init(jf);
	}

	private void init(ManagerFrame jf) {
		setLayout(new BorderLayout());
		this.jf = jf;
		addDialog = new AddDialog();
		updateDialog = new UpdateDialog();
		initTop();
		initContent();
		initFooter();
	}

	private void initTop() {
		topJPanel = new JPanel();
		footerJPanel = new JPanel();
		searchNameJLabel = new JLabel("輸入篩選姓名");
		addJButton = new JButton("新增員工");

		searchJButton = new JButton("查詢");
		searchNameField = new JTextField(10);

		addJButton.addActionListener(new EmpManagerClick());
		searchJButton.addActionListener(new EmpManagerClick());

		topJPanel.add(searchNameJLabel);
		topJPanel.add(searchNameField);
		topJPanel.add(searchJButton);
		topJPanel.add(addJButton);

		add(topJPanel, BorderLayout.NORTH);
	}

	private void initContent() {
		Object[] header = { "員工id", "員工姓名", "性別", "email", "部門" };
		empModel = new DefaultTableModel(header, 0);
		empJTable = new JTable(empModel);
		empJScrollPane = new JScrollPane(empJTable);
		initRowData();
		add(empJScrollPane);
	}

	private void initFooter() {
		deleteJButton = new JButton("刪除員工");
		updateJButton = new JButton("修改員工");

		deleteJButton.addActionListener(new EmpManagerClick());
		updateJButton.addActionListener(new EmpManagerClick());

		footerJPanel.add(deleteJButton);
		footerJPanel.add(updateJButton);
		add(footerJPanel, BorderLayout.SOUTH);
	}

	public void initRowData() {
		empModel.getDataVector().clear();
		List<Employee> employeeList = employeeDAO.getEmployees();
		for (Employee emp : employeeList) {
			String gender= emp.getGender()==1?"男":"女";
			Object[] rowData = { emp.getId(), emp.getName(), gender, emp.getEmail(), emp.getDept().getName() };
			empModel.addRow(rowData);
		}
	}

	private class EmpManagerClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addJButton) {
				addDialog.setVisible(true);
				refreshData();
			} else if (e.getSource() == deleteJButton) {
				deleteEmp();
			} else if (e.getSource() == updateJButton) {
				updateEmp();
				refreshData();
			} else if (e.getSource() == searchJButton) {
				searchEmp();
			}
		}
	}

	private void deleteEmp() {
		try {
			int index = empJTable.getSelectedRow();
			if (index < 0) {
				MyUtil.showError(topJPanel, "必須選擇要刪除的員工");
				return;
			}

			int empId = (Integer) empJTable.getValueAt(index, 0);
			int confirm = JOptionPane.showConfirmDialog(jf, "確定刪除嗎");
			if (confirm == JOptionPane.YES_OPTION) {
				if (employeeDAO.delete(empId) > 0) {
					MyUtil.showSuccess(topJPanel, "刪除成功");
					refreshData();
				}
			}
		} catch (MyException e) {
			MyUtil.showError(jf, e.getMessage());
		}
	}

	private void updateEmp() {
		try {
			int index = empJTable.getSelectedRow();
			if (index < 0) {
				MyUtil.showError(topJPanel, "必須選擇要更新的員工");
				return;
			}
			int empId = (Integer) empJTable.getValueAt(index, 0);
			Employee emp = employeeDAO.getEmployeeById(empId);
			updateDialog.show(emp);
		} catch (MyException e) {
			MyUtil.showError(jf, e.getMessage());
		}
	}

	private void searchEmp() {
		String name = searchNameField.getText();
		empModel.getDataVector().clear();
		List<Employee> employeeList = employeeDAO.getEmployeeByName(name);
		for (Employee emp : employeeList) {
			Object[] rowData = { emp.getId(), emp.getName(), emp.getGender(), emp.getEmail(), emp.getDept().getName() };
			empModel.addRow(rowData);
		}
	}

	private void refreshData() {
		initRowData();
		empJTable.updateUI();
	}

}
