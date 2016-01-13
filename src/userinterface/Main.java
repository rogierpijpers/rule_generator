package userinterface;

import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
		
		
		new Thread() {
			public void run() {
				Application.launch(GeneratorUI.class);
			}
		}.start();
		
		
		
	}

}
