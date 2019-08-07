package services;

import javax.swing.*;

public class ApplicationConsoleService {

    private static ApplicationConsoleService instance;
    private JTextArea out;

    private ApplicationConsoleService() {
    }

    public static ApplicationConsoleService getInstance() {
        if (instance == null) {
            instance = new ApplicationConsoleService();
        }
        return instance;
    }

    public JTextArea getOutput() {
        if (out == null) {
            //throw some exception
        }
        return out;
    }

    public void setOutput(JTextArea output) {
        out = output;
    }

}
