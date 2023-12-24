package io.cygnuxltb.jcts.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingApp extends JFrame {

    public SwingApp() {
        // 设置窗口标题
        super("Swing App");

        // 设置窗口大小
        setSize(300, 200);

        // 设置窗口关闭按钮的默认行为
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建按钮
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton disconnectButton = new JButton("Disconnect");

        // 设置按钮的监听器
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在此处编写启动功能的代码
                JOptionPane.showMessageDialog(SwingApp.this, "系统已启动！");
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在此处编写停止功能的代码
                JOptionPane.showMessageDialog(SwingApp.this, "系统已停止！");
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在此处编写断开功能的代码
                JOptionPane.showMessageDialog(SwingApp.this, "已断开连接！");
            }
        });

        // 创建面板并添加按钮
        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(disconnectButton);

        // 将面板添加到窗口
        getContentPane().add(panel);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 显示窗口
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingApp();
            }
        });
    }
}
