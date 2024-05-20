
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Vlad Ragulin
 * @version v1
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordmap;
    
    public WordsInFiles(){
        wordmap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word :fr.words()){
            if(!wordmap.containsKey(word)){
                ArrayList<String> wordlist = new ArrayList<String>();
                wordlist.add(f.getName());
                wordmap.put(word, wordlist);
            } else {
                if(!wordmap.get(word).contains(f.getName()))
                    wordmap.get(word).add(f.getName());
            }
        }
    }
    
    private void buildWordFileMap(){
        wordmap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        ArrayList<Integer> size = new ArrayList<Integer>();
        for(ArrayList list : wordmap.values()){
            size.add(list.size());
        }
        Collections.sort(size);
        return size.get(size.size()-1);
    }
    
    public ArrayList wordsInNumFiles(int number){
        ArrayList<String> w = new ArrayList<String>();
        for(String word : wordmap.keySet()){
            if (wordmap.get(word).size() == number) w.add(word);
        }
        return w;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> f= wordmap.get(word);
        for (String s : f) 
            System.out.println(s);
    }
    
    // Test methods
    public void tester(){
        buildWordFileMap();
        System.out.println("laid: " + wordmap.get("laid"));
        System.out.println("tree: " + wordmap.get("tree"));

        int myNum = 4;
        System.out.println("\nWords that occur max "+myNum+" times");
        ArrayList<String> al = wordsInNumFiles(myNum);
        System.out.println("# " + al.size());
        
        int maxNum = maxNumber();
        System.out.println("\nWords that occur max "+maxNum+" times");
        al.clear();
        al = wordsInNumFiles(maxNum);
        //System.out.println(al);
        System.out.println("# " + al.size());
        
        //System.out.println("\nFull Map");
        //System.out.println(wordmap);
    }
}
