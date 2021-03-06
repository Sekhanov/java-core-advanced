package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileMessageLogger implements MessageLogger {
	
	private BufferedWriter bufferWriter;
	
	
	public FileMessageLogger() throws IOException  {
		this.bufferWriter = new BufferedWriter(new FileWriter("messageArchive.txt", true));
	}

	@Override
	public boolean archiveMessage(String message) {
		boolean result = false;
		try {
			bufferWriter.write(message + "\n");
			bufferWriter.flush();
			result = true;
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public List<String> restoreMessages(int amount) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("messageArchive.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		List<String> stringFromFile = new ArrayList<>();
		List<String> result = null;
		String string = null;
		try {
			while((string = bufferedReader.readLine()) != null) {
				stringFromFile.add(string);
			}
			if(stringFromFile.size() < amount) {
				result = stringFromFile;
			} else {
				result = new ArrayList<>();
				int startIndex = stringFromFile.size() - amount;
				for (int i = startIndex; i < stringFromFile.size(); i++) {
					result.add(stringFromFile.get(i));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
