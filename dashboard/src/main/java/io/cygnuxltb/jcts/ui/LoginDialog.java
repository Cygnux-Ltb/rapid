package io.cygnuxltb.jcts.ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public LoginDialog() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onLogin());
        buttonCancel.addActionListener(e -> onClose());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        // call onLogin() on ENTER
        contentPane.registerKeyboardAction(e -> onLogin(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );

        // call onClose() on ESCAPE
        contentPane.registerKeyboardAction(e -> onClose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onLogin() {
        // add your code here
        System.out.println("onLogin");
        dispose();
    }

    private void onClose() {
        // add your code here if necessary
        System.out.println("onClose");
        dispose();
    }

    public static void main(String[] args) {
        LoginDialog dialog = new LoginDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
