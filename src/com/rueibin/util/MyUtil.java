package com.rueibin.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MyUtil {
	public static void showError(Component parent, String msg) {
		JOptionPane.showMessageDialog(parent, msg, "發現錯誤", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showSuccess(Component parent, String msg) {
		JOptionPane.showMessageDialog(parent, msg, "提示", JOptionPane.PLAIN_MESSAGE);
	}
}
