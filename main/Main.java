package main;

import utilities.UserInterface;

public class Main {
	public static void main(String[] args) {
		final int FRAME_WIDTH = 600;
		final int FRAME_HEIGHT = 450;
		UserInterface userInterface = new UserInterface(FRAME_WIDTH, FRAME_HEIGHT);
		userInterface.init();
	}
}