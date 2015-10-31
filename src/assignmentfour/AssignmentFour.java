/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentfour;
import java.io.*;
import java.util.*;

/**
 *
 * @author sjoec
 */
public class AssignmentFour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AssignmentFour obj = new AssignmentFour();
        MyLinkedList[] listArray = new MyLinkedList[26];
        for(int i = 0; i < listArray.length; i++) {
            listArray[i] = new MyLinkedList<String>();
        }
        obj.readDictionary(listArray);
        obj.readBook(listArray);
        obj.printResults();
    }
    
    
    public long wordsFound = 0;
    public long wordsNotFound = 0;
    public long compsFound = 0;
    public long compsNotFound = 0;
    
    /**
     * This method reads a dictionary file and loads each word into
     * a linked list in an array using an index based on the first letter of the
     * word
     * @param listArray array of linkedlists to be loaded
     * requires: random_dictionary exits and is in the proper location
     * ensures: that the listArray is properly loaded with words from the 
     * random dictionary
     */
    public void readDictionary(MyLinkedList[] listArray) {
        File f = new File("random_dictionary.txt");
        try {
            int i = 0;
            Scanner inf = new Scanner(f);
            while(inf.hasNext()) {
                String s = inf.nextLine().toLowerCase();
                listArray[s.charAt(0) - 97].add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method reads a text file, gets one word at a time, and then searches
     * an array of LinkedLists for that word to determine if the word is in the
     * dictionary.
     * @param listArray: array used to search for keys read from this method
     * requires: oliver.txt exists
     * ensures: that the LinkedList array is searched for words from oliver.txt
     */
    public void readBook(MyLinkedList[] listArray) {
        try {
            int[] i = new int[1];
            char let;
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            String str = "";
            int n = 0;
            while((n = inf.read()) != -1) {
                i[0] = 0;
                let = (char)n;
                if(Character.isLetter(let))
                    str += Character.toLowerCase(let);
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()){
                   if(listArray[str.charAt(0) - 97].contains(str,i)) {
                       wordsFound++;
                       compsFound += i[0];
                   } else {
                       wordsNotFound++;
                       compsNotFound += i[0];
                   }
                   str="";
                }
            }
            inf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method prints the results of the searches
     */
    public void printResults() {
        System.out.println("Number of words found: " + wordsFound);
        System.out.println("Number of words not found: " + wordsNotFound);
        System.out.printf("Average number of comparisons for words found: %6.2f\n ",(double)compsFound/wordsFound);
        System.out.printf("Average number of comparisons for words not found: %6.2f\n ",(double)compsNotFound/wordsNotFound);
    }
}
