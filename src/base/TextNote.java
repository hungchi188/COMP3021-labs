package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note{

	private static final long serialVersionUID = 1L;
	
	public String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
		}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		// TODO
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(absolutePath);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader bf = new BufferedReader(isr);
			result = bf.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		}
	
	public void exportTextToFile(String pathFolder) {
		//TODO
		BufferedWriter output = null;
		String temp = "";
		
		String path = "";
		if (pathFolder!="") 
			path = pathFolder + File.separator;
	
		File file = new File( path + title.replaceAll(" ", "_") + ".txt");
		try {
			output = new BufferedWriter(new FileWriter(file));
			output.write(content);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO
	}
	public String getContent() {
		return content;
	}
}
