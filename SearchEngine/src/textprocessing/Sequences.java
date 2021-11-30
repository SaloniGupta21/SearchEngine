package textprocessing;

import java.util.Random;

public class Sequences {

	public static int editDistance(String word1, String word2) {
		int firstLength = word1.length();
		int secondLength = word2.length();
	 
		// firstLength+1, secondLength+1, because finally return dp[firstLength][secondLength]
		int[][] dp = new int[firstLength + 1][secondLength + 1];
	 
		for (int i = 0; i <= firstLength; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= secondLength; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < firstLength; i++) {
			char char1 = word1.charAt(i);
			for (int j = 0; j < secondLength; j++) {
				char char2 = word2.charAt(j);
	 
				//if last two chars equal
				if (char1 == char2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replaceChar = dp[i][j] + 1;
					int insertChar = dp[i][j + 1] + 1;
					int deleteChar = dp[i + 1][j] + 1;
	 
					int min = replaceChar > insertChar ? insertChar : replaceChar;
					min = deleteChar > min ? min : deleteChar;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[firstLength][secondLength];
	}
	
	public static void main(String[] args) {
		// two arbitrary strings
		String string1 = "intranet torrain";
		String string2 = "internet domain";
		int d = editDistance(string1, string2);
		long totaltime=0;
		System.out.println("Dist between '" + string1 + "' and '" + string2 + "' = " + d);
		Random rand = new Random();
		int length = 9;//
		String str = new String();
		//generate 1000 words
		String[] lst= new String[1000]; 
		for (int i=0; i < 1000; i++)
		{	
		for( int j=0; j < length; j++ )
           str  += (char) ( 'a' + rand.nextInt( 26 ) );
		
		lst[i] = str;
		}
		// calculate distance between each pair  in lst
		// random strings
		// Random r = new Random( );
		String randomString1 = "";
		String randomString2 = "";
        //int length = 10;

		for ( int c=0; c < 1000; c++){
	        randomString1="";
	        randomString2="";
	      
	        for( int j = 0; j < length; j++ ){
	            randomString1 += (char) ( 'a' + rand.nextInt(26));
	            randomString2 += (char) ( 'a' + rand.nextInt(26));
	        }
	        long time1= System.currentTimeMillis();
			@SuppressWarnings("unused")
			int dr = editDistance(randomString1, randomString2);
	        long time2 = System.currentTimeMillis();
	        totaltime += time2-time1;  // totaltime = totaltime + (time2-time1);
	        //System.out.println("Dist between '" + randomString1 + "' and '" + randomString2 + "' = " + dr);
	    }//1000 loop
		System.out.println("avg dist for 1000 random pairs of strings " + totaltime/1000);
	}
}