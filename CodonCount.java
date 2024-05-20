
/**
 * Write a description of CodonCount here.
 * 
 * @author Vlad Ragulin
 * @version 20-May-2024
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    // instance variables
    private HashMap<String, Integer> DNAmap;
    
    // Constructor for objects of class CodonCount
    public CodonCount(){
        DNAmap = new HashMap<String, Integer>();
    }
    
    /**
     * The method builds a new map of codons mapped to tehir counts
     * from the string dna with the reasing frame with the positive start
     * @param start
     * @param dna
     * @return None
     */
    private void buildCodonMap(int start, String dna){
        DNAmap.clear();
        String current;
        for(int i=0; i<(dna.length() - start)/3; i++){
            current = dna.substring(start+i*3, start+i*3+3);
            if (!DNAmap.containsKey(current)) DNAmap.put(current,1);
            else DNAmap.put(current, DNAmap.get(current)+1);
        }
    }
    
    /**
     *  This method returns a String, the codon in a reading frame that has the largest count
     *  @param Nothing
     *  @return String
     */
    private String getMostCommonCodon(){
        int value = 0;
        int maxCount = 0;
        String maxKey = null;
        for (String key : DNAmap.keySet()) {
            value = DNAmap.get(key);
            if (maxCount < value) {
                maxCount = value;
                maxKey = key;
            }
        }
        return maxKey;    
    }
    
    /**
     * This method prints all the codons in the HashMap along with their counts
     * if their cont is between start and end inclusive
     */
    private void printCodonCounts(int start, int end){
        int value = 0;
        for(String key : DNAmap.keySet()) {
            value = DNAmap.get(key);
            if (value >=start && value <=end)
                System.out.println( key + ": " + value + "\t");
        }
    }
    
    // Tester methods
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna = dna.toUpperCase();
        int start = 7;
        int end = 7;
        
        for (int k=0; k<3; k++){
            buildCodonMap(k, dna);
            System.out.println("\nReading frame starting with "+k+"results in "
                +DNAmap.size()+" unique codons"+"\t");
            String largest = getMostCommonCodon();
             System.out.println("Most common codon is "+largest+" with count "+DNAmap.get(largest)+"\t"); 
             System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
             printCodonCounts(start, end);            
        }
    }
}
