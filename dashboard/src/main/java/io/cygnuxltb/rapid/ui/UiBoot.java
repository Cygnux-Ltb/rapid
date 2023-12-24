package io.cygnuxltb.rapid.ui;

import io.mercury.swing.LoginDialog;

import javax.swing.SwingUtilities;

public final class UiBoot {

    private LoginDialog loginDialog;

    private UiBoot() {
        this.loginDialog = new LoginDialog("Rapid Login", (username, password) ->
        {
            SwingUtilities.invokeLater(SwingApp::new);
            loginDialog.setVisible(false);
        });
    }

    private void start() {
        loginDialog.showDialog();
    }

    public static void main(String[] args) {
        new UiBoot().start();
    }

}
