package crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {

	public static Set<String> v = new HashSet<String>();
	public static Set<String> u = new HashSet<String>();
	public static String crawlURL = "https://www.javatpoint.com/";
	
	public static void visit(String url) throws IOException {
		try {
			v.add(url);
			Document document = Jsoup.connect(url).get();
			String path = "D:\\uWindsor\\ACC\\ACC-Surfer\\src\\uwindsor\\acc\\Udemy";
			new File(path).mkdirs();
			PrintWriter out = new PrintWriter(path + "\\" + document.title() + ".html");
			out.println(document.html());
			out.close();
			
			Elements l = document.select("a[href]");
			
			for (Element link : l) {
				u.add(link.absUrl("href"));
			}
		} catch (Exception e) {
			System.out.println(e);
			
		}
		finally {
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		visit(crawlURL);
		while(v.size()<=200) {
			String[] str = new String[u.size()];
			int i = 0;
			for (String string : u) {
				str[i++] = string;
			}
			for (int j = 0; j < str.length; j++) {
				if(v.size()>=1000)
				break;
				if(!v.contains(str[j]))
				visit(str[j]);
			}
			
		}
	}
	
	
}