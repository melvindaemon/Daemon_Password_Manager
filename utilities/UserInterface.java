package utilities;

import utilities.PasswordManager;
import java.io.*;
import java.awt.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.net.URL;
import javax.swing.table.DefaultTableModel;

public class UserInterface extends PasswordManager {
	private JFrame mainFrame, viewSavedPasswordFrame;
	private JButton generateButton, copyButton, saveButton, viewSavedPasswordButton, dialogButton, backButton;
	private JLabel passwordLabel, spinnerLabel, dialogLabel, comboBoxLabel, textFieldLabel;
	private SpinnerNumberModel spinnerNumberModel;
	private JSpinner spinner;
	private JDialog dialog;
    private JPanel dialogPanel, mainFramePanel;
    private JTextField usernameTextField;
	private JTable passwordTable;
    private BufferedImage blankIcon;
    private Component dialogLayoutLineBreak, mainFrameLayoutLineBreak1, mainFrameLayoutLineBreak2, mainFrameLayoutLineBreak3, mainFrameLayoutLineBreak4, mainFrameLayoutLineBreak5, mainFrameLayoutLineBreak6;
	private int passwordLabelWidth, passwordLabelHeight, spinnerLabelWidth, spinnerLabelHeight, generateButtonWidth,generateButtonHeight, copyButtonWidth, copyButtonHeight, saveButtonWidth, saveButtonHeight, numberModelInitial, numberModelMin, numberModelMax, numberModelStep, spinnerWidth, spinnerHeight, passwordLabelFontSize, spinnerLabelFontSize, frameWidth, frameHeight, comboBoxLabelWidth, comboBoxLabelHeight, usernameTextFieldWidth, usernameTextFieldHeight, textFieldLabelWidth, textFieldLabelHeight, viewSavedPasswordButtonWidth, viewSavedPasswordButtonHeight, accountListComboBoxWidth, accountListComboBoxHeight;
	private String mainFrameTitle, generateButtonText, copyButtonText, saveButtonText, passwordLabelText, spinnerLabelText, passwordLabelFontType, spinnerLabelFontType, comboBoxLabelText, usernameLabelText, viewSavedPasswordButtonText, viewSavedPasswordFrameTitle, dialogButtonText, backButtonText;
	private String generatedPassword, accountSelected, textFieldUsername;
	private String[] accountList = {"-", "Facebook", "Gmail", "Instagram", "X", "Outlook", "LinkedIn", "Github", "Tiktok", "Snapchat"};
	private JComboBox<String> accountListComboBox = new JComboBox<>(accountList);
	private ImageIcon icon;
	private String iconUrl;
	private DefaultTableModel model;
	private JScrollPane scrollpane;
	
	public UserInterface(int width, int height) {
		super();
		frameWidth = width;
		frameHeight = height;
		passwordLabelWidth = 400;
		passwordLabelHeight = 50;
		spinnerLabelWidth = 200;
		spinnerLabelHeight = 50;
		generateButtonWidth = 100;
		generateButtonHeight = 40;
		copyButtonWidth = 100;
		copyButtonHeight = 40;
		saveButtonWidth = 300;
		saveButtonHeight = 40;
		spinnerWidth = 50;
		spinnerHeight = 30;
		passwordLabelFontSize = 35;
		spinnerLabelFontSize = 16;
		numberModelInitial = 10;
		numberModelMin = 8;
		numberModelMax = 25;
		numberModelStep = 1;
		usernameTextFieldWidth = 200;
		usernameTextFieldHeight = 30;
		textFieldLabelWidth = 100;
		textFieldLabelHeight = 30;
		viewSavedPasswordButtonWidth = 250;
		viewSavedPasswordButtonHeight = 30;
		comboBoxLabelWidth = 100;
		comboBoxLabelHeight = 10;
		accountListComboBoxWidth = 200;
		accountListComboBoxHeight = 30;
		
		mainFrameTitle = "Daemon Password Manager";
		viewSavedPasswordFrameTitle = "Saved Passwords";
		generateButtonText = "Generate";
		copyButtonText = "Copy";
		saveButtonText = "Save";
		dialogButtonText = "OK";
		backButtonText = "< Back";
		passwordLabelText = "";
		spinnerLabelText = "Password Length: ";
		passwordLabelFontType = "Roboto";
		spinnerLabelFontType = "Arial";
		comboBoxLabelText = "Account:";
		usernameLabelText = "Username:";
		viewSavedPasswordButtonText = "<html><u>View Saved Passwords ><u/><html>";
		iconUrl = "/resources/app_icon.png";
		
		mainFrame = new JFrame(mainFrameTitle);
		viewSavedPasswordFrame = new JFrame(viewSavedPasswordFrameTitle);
		generateButton = new JButton(generateButtonText);
		copyButton = new JButton(copyButtonText);
		saveButton = new JButton(saveButtonText);
		dialogButton = new JButton(dialogButtonText);
		viewSavedPasswordButton = new JButton(viewSavedPasswordButtonText);
		backButton = new JButton(backButtonText);
		passwordLabel = new JLabel(passwordLabelText);
		spinnerLabel = new JLabel(spinnerLabelText);
		comboBoxLabel = new JLabel(comboBoxLabelText);
		textFieldLabel = new JLabel(usernameLabelText);
		spinnerNumberModel = new SpinnerNumberModel(numberModelInitial, numberModelMin, numberModelMax, numberModelStep);
		spinner = new JSpinner(spinnerNumberModel);
		usernameTextField = new JTextField();
		blankIcon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		mainFrameLayoutLineBreak1 = Box.createHorizontalStrut(width);
		mainFrameLayoutLineBreak2 = Box.createHorizontalStrut(width);
		mainFrameLayoutLineBreak3 = Box.createHorizontalStrut(width);
		mainFrameLayoutLineBreak4 = Box.createHorizontalStrut(width);
		mainFrameLayoutLineBreak5 = Box.createHorizontalStrut(width);
		mainFrameLayoutLineBreak6 = Box.createHorizontalStrut(width);
		icon = new ImageIcon(PasswordManager.class.getResource(iconUrl));
	}
	
	class Dialog {
		static final int COPY = 0;
		static final int SAVE = 1;
		static final int ERROR = 2;
	}
	
	public void init() {
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
		usernameTextField.setPreferredSize(new Dimension(usernameTextFieldWidth, usernameTextFieldHeight));
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
		mainFrame.add(usernameTextField);
		mainFrame.add(mainFrameLayoutLineBreak5);
		mainFrame.add(saveButton);
		mainFrame.add(mainFrameLayoutLineBreak6);
		mainFrame.add(viewSavedPasswordButton);
		
		
		mainFrame.setVisible(true);
		
		update();
		setEventHandler();
	}
	
	void update() {
		int passwordLength = (Integer) spinner.getValue();
		this.generatedPassword = super.generate(passwordLength);
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
				ui.textFieldUsername = usernameTextField.getText();
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
				mainFrame.setVisible(false);

				displayPasswords();

				viewSavedPasswordFrame.add(backButton);
				viewSavedPasswordFrame.getContentPane().setBackground(Color.WHITE);
				viewSavedPasswordFrame.setIconImage(icon.getImage());
				viewSavedPasswordFrame.setSize(frameWidth, frameHeight);
				viewSavedPasswordFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
				viewSavedPasswordFrame.setResizable(false);
				viewSavedPasswordFrame.setLocationRelativeTo(null);
				viewSavedPasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				viewSavedPasswordFrame.setVisible(true);
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewSavedPasswordFrame.setVisible(false);
				viewSavedPasswordFrame.getContentPane().removeAll();
				viewSavedPasswordFrame.revalidate();
				viewSavedPasswordFrame.repaint();
				mainFrame.setVisible(true);
			}
		});
		
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				update();
			}
		});
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

	void displayPasswords() {
		String[] columns = {"S/N", "Account", "Username", "Password"};
		model = new DefaultTableModel(columns, 0);
		int index = 1;
		String[] cd;

		for(String c : super.extract()) {
			cd = c.split(",");
			model.addRow(new Object[]{index, cd[0], cd[1], cd[2]});
			index += 1;
		}

		passwordTable = new JTable(model);

		passwordTable.setBackground(Color.WHITE);

		scrollpane = new JScrollPane(passwordTable);

		scrollpane.setPreferredSize(new Dimension(585, 200));
		scrollpane.getViewport().setBackground(Color.WHITE);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		viewSavedPasswordFrame.add(scrollpane);
	}
        
}



