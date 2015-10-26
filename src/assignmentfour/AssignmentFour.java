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
    
    public void printResults() {
        System.out.println("Number of words found: " + wordsFound);
        System.out.println("Number of words not found: " + wordsNotFound);
        System.out.printf("Average number of comparisons for words found: %6.2f\n ",(double)compsFound/wordsFound);
        System.out.printf("Average number of comparisons for words not found: %6.2f\n ",(double)compsNotFound/wordsNotFound);
    }
}
