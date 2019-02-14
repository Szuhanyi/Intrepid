package output;

import javax.swing.JTextArea;

import services.ApplicationConsoleService;

public class OutputApplication extends OutputDestination {

	@Override
	public void print(String s) {
		
		JTextArea out = ApplicationConsoleService.getInstance().getOutput();
		out.setText(s);		
	}

}
