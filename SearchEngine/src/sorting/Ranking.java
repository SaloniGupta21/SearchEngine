package sorting;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import textprocessing.InvertedIndexBuilder;
import textprocessing.Node;

public class Ranking {
	
	public static HashMap<String,BinaryHeap<Node>> index = new HashMap<String, BinaryHeap<Node>>();
	
	public static void reStructure() {
		for (Entry<String, HashMap<String, Integer>> entry : InvertedIndexBuilder.index.entrySet()) {
			BinaryHeap<Node> binaryHeap = new BinaryHeap<Node>();
			HashMap<String, Integer> hashMap = entry.getValue();
			for (Entry<String, Integer> i : hashMap.entrySet()) {
				binaryHeap.insert(new Node(i.getKey(),i.getValue()));
			}
			index.put(entry.getKey(), binaryHeap);
		}
	}
	
	public static void search(String keyword, int numberOfResults) {
		BinaryHeap<Node> binaryHeap = index.get(keyword);
		if(binaryHeap == (null)) {
			System.out.println("Match not found!!");
			return;
		}
		else
		{
			System.out.println("Retrieving first " + numberOfResults + " matches....");
		}
			
		for (int j = 0; j < numberOfResults; j++) {
			System.out.println((j+1)+" :"+ binaryHeap.deleteMin());
		}
	}
	
	public static void main(String[] args) throws Exception {
		File path = new File("C:\\Users\\LENOVO\\git\\repository\\SearchEngine\\src\\Udemy\\Text");
		File[] listOfFiles = path.listFiles();
		for (File file : listOfFiles) {
			InvertedIndexBuilder.invertedIndex(file);
		}
		reStructure();
		search("web", 10);
	}
}
