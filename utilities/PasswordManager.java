package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.JobAttributes.DialogType;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.IOException;
import java.util.List;

class PasswordManager {
	final ArrayList<String> charList = new ArrayList<String>();
	static String dirname, filename, deviceUser;
	
	PasswordManager() {
		deviceUser = System.getProperty("user.name");
		dirname = "C:\\Users\\" + deviceUser + "\\Documents\\Daemon";
                filename = "db.txt";
		Collections.addAll(charList, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", 
						"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", 
						"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", 
						"Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "@", "#", "$", "%", "^", "&", 
						"*", "(", ")", "-", "+", "=", "_", "{", "}", "[", "]", "|", ":", ";", "'", "?", ">", 
                        "<");
	}
	
	String generate(int passwordLength) {
		int randomNumber;
		final int CHAR_LIST_LENGTH = charList.size();
		String password = "";
		
		Random random = new Random();
		
		for(int i = 0; i < passwordLength; i++) {
			randomNumber = random.nextInt(CHAR_LIST_LENGTH);
			password += charList.get(randomNumber);
		}
		
		return password;
	}
	
	static void copy(String password) {
		StringSelection stringSelection = new StringSelection(password);
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(stringSelection, null);
	}
	
	static void save(String password, String account, String username) {
		String format = account + "," + username + "," + password + "\r\n";
		byte[] b = format.getBytes(StandardCharsets.UTF_8);
                Path path = Paths.get(dirname, filename);
		
		try {
                        if(!Files.isDirectory(path.getParent())) {
                            Files.createDirectories(path.getParent());
                        }
                        
                        if(!Files.exists(path)) {
                            File file = new File(dirname + "\\" + filename);
                            
                            file.createNewFile();
                         }
			
			Files.write(path, b, StandardOpenOption.APPEND);
                        
		} catch(IOException e) {
			System.out.println("An error occurred.." + e.getMessage());
		}
	}

	static List<String> extract() {
		String file = dirname + "\\" + filename;
		List<String> content = new ArrayList<>();
		try {
			content = Files.readAllLines(Path.of(file));

		} catch(IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}