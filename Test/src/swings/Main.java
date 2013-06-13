package swings;

/**
 * Java Swing小例子，让你明白
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Main {
	private static String labelPrefix = "Number of button clicks: ";

	private int numClicks = 0; // 计数器，计算点击次数

	public Component createComponents() {
		final JLabel label = new JLabel(labelPrefix + "0 ");

		JButton button = new JButton("I'm a Swing button!");
		button.setMnemonic(KeyEvent.VK_I); // 设置按钮的热键为'I'
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numClicks++;
				label.setText(labelPrefix + numClicks);// 显示按钮被点击的次数
			}
		});
		label.setLabelFor(button);

		/* 在顶层容器及其内容之间放置空间的常用办法是把内容添加到Jpanel上，而Jpanel本身没有边框的。 */

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(30, // top
				30, // left
				10, // bottom
				30) // right
		);
		pane.setLayout(new GridLayout(0, 1)); // 单列多行
		pane.add(button);
		pane.add(label);
		return pane;
	}

	public static void main(String[] args) {
		try {
			// 设置窗口风格
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}

		// 创建顶层容器并添加内容.
		JFrame frame = new JFrame("让你明白");
		Main app = new Main();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		// 窗口设置结束，开始显示
		frame.addWindowListener(new WindowAdapter() {
			// 匿名类用于注册监听器
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
