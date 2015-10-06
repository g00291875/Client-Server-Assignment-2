package A1V18;

import java.io.*;
import java.util.*;


/**
 * Author : Thomas Flynn
 * Student ID: G00291875
 * Subject: Client Server Programming
 * Assignment 1&2: Streams & Thread Safe Swing
 * */


public class Rearrange {
    Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
    String[] arrayOfLines;
    File textfile;

    /*********************************
     * Constructors
     *****************************************/
    public Rearrange() throws IOException {}

    public Rearrange(String filePath) throws IOException {
        try {
            textfile = new File(filePath);
            whatItKnows();
            extractWords();
        }//try
        catch (Exception cons) {
            cons.printStackTrace();
        }
    }// constructor

    /*********************************
     * Constructor methods
     *****************************************/
    public void whatItKnows() {
        try {
            // LineNumberReader determines number of lines in file
            int noOfLines;
            LineNumberReader lnr = new LineNumberReader(new FileReader(textfile));//create line number reader object and pass it filereader of the text
            lnr.skip(Long.MAX_VALUE);//skip is passed The number of characters to skip, returns The number of characters actually skipped
            noOfLines = lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
            lnr.close();// LineNumberReader object closed to prevent resource leak
            arrayOfLines = new String[noOfLines];// allocate memory for array depending on how many lines are in the text
        }
        catch (Exception reverse) {
            reverse.printStackTrace();
        }
    }//what it knows()

    //performs IO operation of reading the text file into "arrayOfLines"
    public void extractWords() {
        try {
            FileReader fr = new FileReader(textfile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            for (int i = 0; (line = br.readLine())!= null; i++)
            	arrayOfLines[i] = line;

            br.close();//close buffered reader
        }//try
        catch (Exception reverse) {
            reverse.printStackTrace();
        }
    }//extractWords()

    /***********************************
     * Useful methods
     ******************************/

    //takes a String array and returns a String
    public String elementsToString(String[] append) {
        StringBuilder sb = new StringBuilder();

        for (String s1 : append)
            sb.append(s1 + " ");

        String returnString = sb.toString();

        return returnString;
    }

    //takes a String and returns a String array
    public String[] stringToElements(String line) {
        String[] localWordsOnLineArray = new String[countSpaces(line)];

        Scanner s = new Scanner(line).useDelimiter("\\s");
        for (int j = 0; j < localWordsOnLineArray.length; j++) {
            if (s.hasNext())
                localWordsOnLineArray[j] = s.next();

        }
        return localWordsOnLineArray;
    }

    public int countSpaces(String s) {
        String trim = s.trim();
        if (trim.isEmpty())
            return 0;
        return trim.split("\\s").length; // '\s' = separate spaces
    }
    /********************************
     * Assignment objective methods
     *******************************/

    public void reverseContents() {
        try {
            for (int i = 0; i < arrayOfLines.length; i++) {
                String line = arrayOfLines[i];
                String[] localWordsOnLineArray = stringToElements(line);
                String[] localSwapArray = new String[localWordsOnLineArray.length];

                int cntUp = 0;// index for swap array
                for (int j = localWordsOnLineArray.length; j > 0; j--, cntUp++)
                    localSwapArray[cntUp] = localWordsOnLineArray[j - 1];// put the last element into the first element of the swap array

                arrayOfLines[i] = elementsToString(localSwapArray);// get string representation of swapped elements
            }//for i
        }//try
        catch (Exception reverse) {
            reverse.printStackTrace();
        }//catch
    }//reverse content

    public void reversePairs() {
    	SpellChecker checker = new SpellChecker();
        String firstWord;
        String secondWord;

        for (int i = 0; i < arrayOfLines.length; i++) {
            String line = arrayOfLines[i];
            String[] localWordsOnLineArray = stringToElements(line);

            for (int j = 0; j < localWordsOnLineArray.length - 1; j++) {
                firstWord = localWordsOnLineArray[j];
                secondWord = localWordsOnLineArray[j+1];

                //if both words are in the dictionary then swap them
                if (checker.checkWord(firstWord) && checker.checkWord(secondWord)) {
                    localWordsOnLineArray[j+1] = firstWord;
                    localWordsOnLineArray[j] = secondWord;
                    j++;//increment to prevent accidental swapping of pairs
                }
            }
            arrayOfLines[i] = elementsToString(localWordsOnLineArray);
        }//for i
    }//reverse pairs()

    public void countWordsFunc() {
    	SpellChecker checker = new SpellChecker();
        for (int i = 0; i < arrayOfLines.length; i++) {
            String line = arrayOfLines[i];
            String[] localWordsOnLineArray = stringToElements(line);

            for (String word : localWordsOnLineArray) {
                if (checker.checkWord(word)) {// if it's a word
                    //increase the count of the word found in the wordCountMap
                    Integer count = wordCountMap.get(word);
                    //if it's the first time the word is found make it 1, else count++
                    wordCountMap.put(word, (count == null) ? 1 : count + 1);
                }
            }//for words on the line
        }//for amount of lines
    }//countWordsFunc()

    /************************************
     * getters
     *************************/
    public Map<String, Integer> getWordCount() {
        return wordCountMap;
    }

    public String[] getArrayOfLines() {
        return arrayOfLines;
    }
}
