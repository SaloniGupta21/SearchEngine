package search;

import java.util.ArrayList;
import java.util.Scanner;

import sorting.Ranking;
import textprocessing.SpellChecker;
import textprocessing.Suggestions;

public class WebSearchEngine {
	public static void main(String[] args) throws Exception{
		
		PreProcessor.preProcessor();
		
		String option = "";
		Scanner in = new Scanner(System.in);
		String keyword;
		
		while(true) {
			
			switch (option) {
			case "1":
				System.out.println("Search with our Surfer:");
				keyword = in.next();
				Ranking.search(keyword, 5);
				break;
		
			case "2":
				System.out.println("Enter a keyword to get suggestions :");
				keyword = in.next();
				ArrayList<String> suggestions= Suggestions.getSuggestions(keyword);
				for (String string : suggestions) {
					System.out.println(string);
				}
				break;
			
			case "3":
				System.out.println("Enter a keyword to check spelling:");
				keyword = in.next();
				SpellChecker.spellCheck(keyword);
				break;
			
			case "4":
				System.out.println("Exiting");
				System.out.println("Thank you for using our application!");
				in.close();
				System.exit(0);
				break;
			
			default:
				System.out.println("Invalid input!! Please try again.");
				break;
			}
			System.out.println("Select one option from below:");
			System.out.println("1. For Search \n2. To Get Suggestions \n3. To run Spell Checker \n4. Exit\n");
			option = in.next();
		}
		
		
	}
	
}
