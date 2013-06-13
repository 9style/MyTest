package swings;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class FindDialog extends JDialog implements ActionListener {
	// 引入np属性是为了通过构造器的参数传入NotePad的对象,
	// 从而方便访问其内部属性与方法
	NotePad np;
	JTextField tex;
	JLabel label;
	JButton find;
	JButton exit;

	public FindDialog(NotePad owener) {
		super(owener, " 查找 ", false);
		this.np = owener;
		label = new JLabel("   查找内容 ");
		tex = new JTextField(5);
		find = new JButton(" 查找下一个 ");
		exit = new JButton(" 取消 ");
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(2, 2));
		contentPane.add(label);
		contentPane.add(tex);
		contentPane.add(find);
		contentPane.add(exit);
		this.setSize(210, 80);
		this.setLocation(450, 350);
		this.setResizable(false);

		find.addActionListener(this);
		exit.addActionListener(this);
		tex.addKeyListener(new MyKeyAdapter());
		find.setEnabled(false);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		String str = np.text.getSelectedText();
		if (str == "" || str == null) {
			np.findIndex = 0;
		}
		if (e.getSource() == find) {
			np.find(tex.getText(), np.findIndex);
		} else if (e.getSource() == exit) {
			this.dispose();
		}
	}

	class MyKeyAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (np.text.getSelectedText() != "") {
				find.setEnabled(true);
			}
		}
	}
}

class ReplaceDialog extends JDialog implements ActionListener {
	NotePad np;
	JTextField ft;
	JTextField rt;
	JLabel b1;
	JLabel b2;
	JButton find;
	JButton rp;
	JButton rpa;
	JButton exit;

	public ReplaceDialog(NotePad owener) {
		super(owener, " 替换 ", false);
		this.np = owener;
		b1 = new JLabel(" 查找内容 ");
		b2 = new JLabel(" 替  换  为　 ");
		ft = new JTextField(8);
		// 初始状态将已查找到的字符串放在此文本框中
		ft.setText(owener.text.getSelectedText());
		rt = new JTextField(8);

		find = new JButton(" 查找下一个 ");
		rp = new JButton(" 替     换 ");
		rpa = new JButton(" 全部替换 ");
		exit = new JButton(" 取       消 ");
		Container cp = getContentPane();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		p1.setLayout(new GridLayout(2, 3, 5, 2));
		p1.add(b1);
		p1.add(ft);
		p1.add(find);
		p1.add(b2);
		p1.add(rt);
		p1.add(rp);
		cp.add(p1, BorderLayout.NORTH);

		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p2.add(rpa);
		p2.add(exit);
		cp.add(p2, BorderLayout.SOUTH);

		this.setSize(350, 120);
		this.setLocation(400, 350);
		this.setResizable(false);

		find.addActionListener(this);
		rp.addActionListener(this);
		rpa.addActionListener(this);
		exit.addActionListener(this);

		ft.addKeyListener(new MyKeyAdapter());
		rt.addKeyListener(new MyKeyAdapter());
		find.setEnabled(false);
		rp.setEnabled(false);
		rpa.setEnabled(false);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == find) {
			/**//*
				 * String str=np.text.getSelectedText(); if(str==""||str==null)
				 * { np.findIndex=0; }
				 */
			if (ft.getText().equals(np.findStr) == false) {
				np.findIndex = 0;
			}
			np.findStr = ft.getText();
			np.find(np.findStr, np.findIndex);
		}
		if (e.getSource() == rp) {
			String str = np.text.getSelectedText();
			if (str != "" && str != null) {
				np.text.replaceSelection(rt.getText());
			}
		}
		if (e.getSource() == rpa) {
			int n = np.text.getText().length();
			np.replaceAll(ft.getText(), rt.getText(), 0, n);
		} else if (e.getSource() == exit) {
			this.dispose();
		}
	}

	class MyKeyAdapter extends KeyAdapter { // 如果查找或替换框内没有内容则相应按钮不可用
		public void keyReleased(KeyEvent e) {
			if (ft.getText() != "") {
				find.setEnabled(true);
			}
			if (rt.getText() != "") {
				rp.setEnabled(true);
				rpa.setEnabled(true);
			}
		}
	}
}