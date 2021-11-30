package textprocessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;

public class HTMLToText {
	
	/**
	 * @param file HTML file that is to be converted to plain text
	 * @return Plain text of given HTML file
	 * Also Writes the plain text to a file in $currentdirectory/Text1 folder
	 * @throws IOException
	 */
	
	public static void createIndFiles(File file) throws IOException {
		
		String html="";
		String fileName = file.getName();
		String destPath = "C:\\Users\\LENOVO\\git\\repository\\SearchEngine\\src\\Udemy\\Text" + "\\" + fileName.substring(0, fileName.length() - 5) + ".txt";

		 Scanner scnr = new Scanner(file);
		 while(scnr.hasNextLine()){
			   String line = scnr.nextLine();
			   html+= line + "\n";
		 }
		 
		  String htmlText = Jsoup.parse(html).wholeText();
		  FileWriter writer = new FileWriter(destPath);
		  writer.write(htmlText);			
		  writer.close();
	}

}
