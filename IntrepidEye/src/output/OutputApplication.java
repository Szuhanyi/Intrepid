package output;

import services.ApplicationConsoleService;

import javax.swing.*;

public class OutputApplication extends OutputDestination {

    @Override
    public void print(String s) {

        JTextArea out = ApplicationConsoleService.getInstance().getOutput();
        out.setText(s);
    }

}
