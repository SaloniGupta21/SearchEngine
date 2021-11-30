package crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtility {
	
	public static ArrayList<String> searchURLS(File file) throws IOException{
		ArrayList<String> urls = new ArrayList<String>();
		Pattern pat = Pattern.compile("href=\"/[\\w]+(/[.\\w]+)*");
		String t = new String(Files.readAllBytes(Paths.get(file.getPath())));
		Matcher  match = pat.matcher(t);
		while(match.find()) {
			urls.add(match.group());
		}
		File url = new File("url.txt");
		url.createNewFile();
		Files.write(Paths.get(url.getName()),urls,StandardOpenOption.APPEND);
		return urls;
	}
	
	
	public static ArrayList<String> searchphonenums(File file) throws IOException{
		ArrayList<String> phoneNums = new ArrayList<String>();
		String ccode = "(\\+[\\d]{1,2}-)?";
		String pat1 = "(\\(?[\\d]{3}\\)?)";
		String c1 = "([- ])+";
		String pat2 = "([\\d]{3})";
		String c2 = "([- ])+";
		String pat3 = "([\\d]{4})";
		String p = ccode.concat(pat1).concat(c1).concat(pat2).concat(c2).concat(pat3);
		Pattern pat = Pattern.compile(p);
		String t = new String(Files.readAllBytes(Paths.get(file.getPath())));
		Matcher  m = pat.matcher(t);
		while(m.find()) {
			phoneNums.add(m.group());
		}
		File phone = new File("phone.txt");
		phone.createNewFile();
		Files.write(Paths.get(phone.getName()),phoneNums,StandardOpenOption.APPEND);
		return phoneNums;
	}
	
	
	/**
	 * @param file
	 * @return list of email addresses
	 * @throws IOException
	 */
	public static ArrayList<String> searchEmailIDs(File file) throws IOException{
		ArrayList<String> emailIds = new ArrayList<String>();
		Pattern pat = Pattern.compile("[-\\w.]+@([\\w]+\\.[-\\w]+)+");
		String t = new String(Files.readAllBytes(Paths.get(file.getPath())));
		Matcher  m = pat.matcher(t);
		while(m.find()) {
			
			emailIds.add(m.group());
		}
		File email = new File("email.txt");
		email.createNewFile();
		Files.write(Paths.get(email.getName()),emailIds,StandardOpenOption.APPEND);
		return emailIds;
	}
	
	public static void searchphonenums() throws IOException {
		File phone = new File("phone.txt");
		phone.delete();
		File[] files = new File("D:\\uWindsor\\ACC\\ACC-Surfer\\src\\uwindsor\\acc\\Udemy\\Text").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				if(searchphonenums((files[i])).size()!=0) {
					System.out.println(files[i].getName()+" "+searchphonenums(files[i]));
				}
			}
		}
	}
	
	public static void searchEmailIDs() throws IOException {
		File email = new File("email.txt");
		email.delete();
		File[] files = new File("D:\\uWindsor\\ACC\\ACC-Surfer\\src\\uwindsor\\acc\\Udemy\\Text").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				if(searchEmailIDs((files[i])).size()!=0) {
					System.out.println(files[i].getName()+" "+searchEmailIDs(files[i]));
				}
				
			}
			
		}
	}
	
	public static void searchURLs() throws IOException {
		File url = new File("url.txt");
		url.delete();
		File[] files = new File("D:\\uWindsor\\ACC\\ACC-Surfer\\src\\uwindsor\\acc\\Udemy\\Text").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				if(searchURLS((files[i])).size()!=0) {
					System.out.println(files[i].getName()+" "+searchURLS(files[i]));
				}
			}
		}
	}
	
}