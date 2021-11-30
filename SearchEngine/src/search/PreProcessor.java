package search;

import java.io.File;

import sorting.Ranking;
import textprocessing.HTMLToText;
import textprocessing.InvertedIndexBuilder;
import textprocessing.Suggestions;

public class PreProcessor {
	public static void preProcessor() throws Exception{
		System.out.println("Starting Pre-Processing...");
		System.out.println("Converting HTML files retrieved from Udemy to .txt files...");
		
		File folder = new File("C:\\Users\\LENOVO\\git\\repository\\SearchEngine\\src\\Udemy");
		File[] files = folder.listFiles();
		
		// converting HTML files to TEXT for processing
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile())
			HTMLToText.createIndFiles(files[i]);
		}
		
		// Build record-level-inverted index using hashMap.
		System.out.println("Reading .txt files and building Record Level Inverted Index...");
		File path = new File("C:\\Users\\LENOVO\\git\\repository\\SearchEngine\\src\\Udemy\\Text");
		File[] filesList = path.listFiles();
		for (File file : filesList) {
			InvertedIndexBuilder.invertedIndex(file);
		}
		
		// ReStructure inverted index to use binary heap.
		System.out.println("Restructuring the inverted index to use Binary Heaps...");
		Ranking.reStructure();
		
		//Constructs a TST for suggestions/autocomplete.
		System.out.println("Construsting a TST with all words");
		for (File file : filesList) {
			Suggestions.buildTST(file);
		}
		
		System.out.println("Pre-Processing finished...");
	}
}
