package org.daemon.utils;

import org.daemon.model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class UserInterface extends PasswordManager {
	private JFrame mainFrame, savedPasswordFrame, passwordsLoginFrame;
	private JButton generateButton, copyButton, saveButton, viewSavedPasswordButton, dialogButton, backButton, plfLoginButton;
	private JLabel passwordLabel, spinnerLabel, dialogLabel, comboBoxLabel, textFieldLabel;
	private SpinnerNumberModel spinnerNumberModel;
	private JSpinner spinner;
	private JDialog dialog;
    private JTextField mfUsernameTextField, plfUsernameTextField, plfPasswordTextField;
	private JTable passwordTable;
    private Component dialogLayoutLineBreak, mainFrameLayoutLineBreak1, mainFrameLayoutLineBreak2, mainFrameLayoutLineBreak3, mainFrameLayoutLineBreak4, mainFrameLayoutLineBreak5, mainFrameLayoutLineBreak6;
	private int generateButtonWidth,generateButtonHeight, copyButtonWidth, copyButtonHeight, saveButtonWidth, saveButtonHeight, numberModelInitial, numberModelMin, numberModelMax, numberModelStep, passwordLabelFontSize, spinnerLabelFontSize, frameWidth, frameHeight, mfUsernameTextFieldWidth, mfUsernameTextFieldHeight, accountListComboBoxWidth, accountListComboBoxHeight;
	private String mainFrameTitle, generateButtonText, copyButtonText, saveButtonText, passwordLabelText, spinnerLabelText, passwordLabelFontType, spinnerLabelFontType, comboBoxLabelText, usernameLabelText, viewSavedPasswordButtonText, savedPasswordFrameTitle, dialogButtonText, backButtonText, passwordsLoginFrameTitle, plfLoginButtonText;
	private String generatedPassword, accountSelected, textFieldUsername;
	private ImageIcon icon;
	private String iconUrl;
	private JScrollPane scrollpane;

	private String[] accountList = {"-", "Facebook", "Gmail", "Instagram", "X", "Outlook", "LinkedIn", "Github", "Tiktok", "Snapchat"};
	private JComboBox<String> accountListComboBox = new JComboBox<>(accountList);

	public DefaultTableModel model;
	
	public UserInterface() {
		super();
		frameWidth = 600;
		frameHeight = 450;
		generateButtonWidth = 100;
		generateButtonHeight = 40;
		copyButtonWidth = 100;
		copyButtonHeight = 40;
		saveButtonWidth = 300;
		saveButtonHeight = 40;
		passwordLabelFontSize = 35;
		spinnerLabelFontSize = 16;
		numberModelInitial = 10;
		numberModelMin = 8;
		numberModelMax = 25;
		numberModelStep = 1;
		mfUsernameTextFieldWidth = 200;
		mfUsernameTextFieldHeight = 30;
		accountListComboBoxWidth = 200;
		accountListComboBoxHeight = 30;
		
		mainFrameTitle = "Daemon Password Manager";
		savedPasswordFrameTitle = "Saved Passwords";
		passwordsLoginFrameTitle = "User Authentication";
		generateButtonText = "Generate";
		copyButtonText = "Copy";
		saveButtonText = "Save";
		dialogButtonText = "OK";
		backButtonText = "< Back";
		plfLoginButtonText = "Authenticate";
		passwordLabelText = "";
		spinnerLabelText = "Password Length: ";
		comboBoxLabelText = "Account:";
		usernameLabelText = "Username:";
		viewSavedPasswordButtonText = "<html><u>View Saved Passwords ><u/><html>";
		passwordLabelFontType = "Roboto";
		spinnerLabelFontType = "Arial";
		iconUrl = "/img/app_icon.png";
		
		mainFrame = new JFrame(mainFrameTitle);
		savedPasswordFrame = new JFrame(savedPasswordFrameTitle);
		passwordsLoginFrame = new JFrame(passwordsLoginFrameTitle);
		generateButton = new JButton(generateButtonText);
		copyButton = new JButton(copyButtonText);
		saveButton = new JButton(saveButtonText);
		dialogButton = new JButton(dialogButtonText);
		plfLoginButton = new JButton(plfLoginButtonText);
		viewSavedPasswordButton = new JButton(viewSavedPasswordButtonText);
		backButton = new JButton(backButtonText);
		passwordLabel = new JLabel(passwordLabelText);
		spinnerLabel = new JLabel(spinnerLabelText);
		comboBoxLabel = new JLabel(comboBoxLabelText);
		textFieldLabel = new JLabel(usernameLabelText);
		spinnerNumberModel = new SpinnerNumberModel(numberModelInitial, numberModelMin, numberModelMax, numberModelStep);
		spinner = new JSpinner(spinnerNumberModel);
		mfUsernameTextField = new JTextField();

		mainFrameLayoutLineBreak1 = Box.createHorizontalStrut(frameWidth);
		mainFrameLayoutLineBreak2 = Box.createHorizontalStrut(frameWidth);
		mainFrameLayoutLineBreak3 = Box.createHorizontalStrut(frameWidth);
		mainFrameLayoutLineBreak4 = Box.createHorizontalStrut(frameWidth);
		mainFrameLayoutLineBreak5 = Box.createHorizontalStrut(frameWidth);
		mainFrameLayoutLineBreak6 = Box.createHorizontalStrut(frameWidth);

		icon = new ImageIcon(UserInterface.class.getResource(iconUrl));
	}
	
	class Dialog {
		static final int COPY = 0;
		static final int SAVE = 1;
		static final int ERROR = 2;
	}
	
	public void init() {
		setMainFrame();
		update();
		setEventHandler();
	}

	void setMainFrame() {
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setIconImage(icon.getImage());
		mainFrame.setSize(frameWidth, frameHeight);
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		generateButton.setPreferredSize(new Dimension(generateButtonWidth, generateButtonHeight));
		copyButton.setPreferredSize(new Dimension(copyButtonWidth, copyButtonHeight));
		accountListComboBox.setPreferredSize(new Dimension(accountListComboBoxWidth, accountListComboBoxHeight));
		mfUsernameTextField.setPreferredSize(new Dimension(mfUsernameTextFieldWidth, mfUsernameTextFieldHeight));
		saveButton.setPreferredSize(new Dimension(saveButtonWidth, saveButtonHeight));
		passwordLabel.setFont(new Font(passwordLabelFontType, Font.BOLD, passwordLabelFontSize));
		spinnerLabel.setFont(new Font(spinnerLabelFontType, Font.BOLD, spinnerLabelFontSize));
		passwordLabel.setForeground(Color.BLACK);
		spinnerLabel.setForeground(Color.BLACK);
		comboBoxLabel.setForeground(Color.BLACK);
		textFieldLabel.setForeground(Color.BLACK);
		generateButton.setFocusPainted(false);
		copyButton.setFocusPainted(false);
		saveButton.setFocusPainted(false);
		dialogButton.setFocusPainted(false);
		viewSavedPasswordButton.setContentAreaFilled(false);
		viewSavedPasswordButton.setBorderPainted(false);
		viewSavedPasswordButton.setFocusPainted(false);
		viewSavedPasswordButton.setOpaque(false);
		viewSavedPasswordButton.setForeground(Color.BLUE);
		viewSavedPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainFrame.add(passwordLabel);
		mainFrame.add(mainFrameLayoutLineBreak1);
		mainFrame.add(spinnerLabel);
		mainFrame.add(spinner);
		mainFrame.add(mainFrameLayoutLineBreak4);
		mainFrame.add(generateButton);
		mainFrame.add(copyButton);
		mainFrame.add(mainFrameLayoutLineBreak2);
		mainFrame.add(comboBoxLabel);
		mainFrame.add(accountListComboBox);
		mainFrame.add(mainFrameLayoutLineBreak3);
		mainFrame.add(textFieldLabel);
		mainFrame.add(mfUsernameTextField);
		mainFrame.add(mainFrameLayoutLineBreak5);
		mainFrame.add(saveButton);
		mainFrame.add(mainFrameLayoutLineBreak6);
		mainFrame.add(viewSavedPasswordButton);
		mainFrame.setVisible(true);
	}
	
	void update() {
		int passwordLength = (Integer) spinner.getValue();
		this.generatedPassword = generate(passwordLength);
		passwordLabel.setText(generatedPassword);
	}
	
	UserInterface getClassInstance() {
		return this;
	}
	
	Boolean isCredentialsValid(String account, String username) {
		if(account != "-" && !username.isEmpty() && !username.isBlank()) {
			return true;
		} else {
			return false;
		}
	}
	
	void setEventHandler() {
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswordManager.copy(generatedPassword);
				showDialog(Dialog.COPY);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInterface ui = getClassInstance();
				ui.accountSelected = (String) accountListComboBox.getSelectedItem();
				ui.textFieldUsername = mfUsernameTextField.getText();
				System.out.println(ui.accountSelected + "  " + ui.textFieldUsername);
				if(isCredentialsValid(ui.accountSelected, ui.textFieldUsername)) {
					PasswordManager.save(ui.generatedPassword, ui.accountSelected, ui.textFieldUsername);
					showDialog(Dialog.SAVE);
				} else {
					showDialog(Dialog.ERROR);
				}
			}
		});
		
		dialogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		viewSavedPasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordsLoginFrame();
				/*
				authenticate input
				username
				password
				if valid 
				retrieve collection
				show password
				else repeat
				 */
				new Model().getEntries();
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(true);
				savedPasswordFrame.dispose();
			}
		});
		
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				update();
			}
		});
	}

	public void setViewPasswordFrame() {
		passwordTable = new JTable(model);
		scrollpane = new JScrollPane(passwordTable);

		mainFrame.setVisible(false);
		passwordTable.setBackground(Color.WHITE);
		scrollpane.setPreferredSize(new Dimension(585, 200));
		scrollpane.getViewport().setBackground(Color.WHITE);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		savedPasswordFrame.add(scrollpane);
		savedPasswordFrame.add(backButton);
		savedPasswordFrame.getContentPane().setBackground(Color.WHITE);
		savedPasswordFrame.setIconImage(icon.getImage());
		savedPasswordFrame.setSize(frameWidth, frameHeight);
		savedPasswordFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		savedPasswordFrame.setResizable(false);
		savedPasswordFrame.setLocationRelativeTo(null);
		savedPasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		savedPasswordFrame.setVisible(true);
	}

	void passwordsLoginFrame() {
		mainFrame.setVisible(false);

	}

	void userLoginFrame() {
		mainFrame.setVisible(false);

	}
	
	void showDialog(int type) {
		String dialogMessage, dialogTitle;
		
		dialogTitle = "ALERT";

		switch(type) {
		case 0:
			dialogMessage = "Password Copied Successfully!";
			break;
		case 1:
			dialogMessage = "Password Saved Successfully!";
			break;
		case 2:
			dialogMessage = "Username/Account Field Cannot Be Empty!";
			break;
		default:
			dialogMessage = null;
		}
		
		dialog = new JDialog(mainFrame, dialogTitle, true);
		dialogLabel = new JLabel(dialogMessage);
		dialogLayoutLineBreak = Box.createHorizontalStrut(300);
		
		dialog.setSize(300, 150);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		dialog.setLocationRelativeTo(mainFrame);
		dialog.add(dialogLabel);
		dialog.add(dialogLayoutLineBreak);
		dialog.add(dialogButton);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}



