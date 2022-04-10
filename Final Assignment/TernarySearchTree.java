/*
    This ternary search tree is adapted from the example given at javatpoint.com
    ( https://www.javatpoint.com/ternary-search-tree )
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TernarySearchTree {
    private Node root;
    public ArrayList<String> al;

    public TernarySearchTree(){
        root = null;
    }

    public void insert(String word, String stopInfo){
        root = insert(root, word.toCharArray(), 0, stopInfo);
    }

    public Node insert(Node r, char[] word, int ptr, String stopInfo)
    {
        if (r == null)
            r = new Node(word[ptr], null);
        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr, stopInfo);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr, stopInfo);
        else
        {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1, stopInfo);
            else
                r.stopInfo = stopInfo;
        }
        return r;
    }

    public String search(String word)
    {
        return search(root, word.toCharArray(), 0);
    }

    private String search(Node r, char[] word, int ptr)
    {
        if (r == null)
            return null;
        if (word[ptr] < r.data)
            return search(r.left, word, ptr);
        else if (word[ptr] > r.data)
            return search(r.right, word, ptr);
        else
        {
            if (r.stopInfo!=null && ptr == word.length - 1) {
                return r.stopInfo;
            }
            else if (ptr == word.length - 1) {
                al = new ArrayList<String>();
                traverse(r, "");
                return "-1";
            }
            else {
                return search(r.middle, word, ptr + 1);
            }
        }
    }

    private void traverse(Node r, String str)
    {
        if (r != null)
        {
            traverse(r.left, str);
            str = str + r.data;
            if (r.stopInfo != null)
                al.add(str);
            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);
            traverse(r.right, str);
        }
    }

    void populateTernarySearchTree(String stopsFile){
        try{
            TernarySearchTree tst = new TernarySearchTree();
            File stops = new File(stopsFile);
            Scanner scanner = new Scanner(stops);
            // Do nothing with the first line as it is just contains a header
            String stopInfo = scanner.nextLine();
            String[] stopInfoArray = new String[10];

            while(scanner.hasNext()){
                // Get the line as both a string and an array of strings
                stopInfo = scanner.nextLine();
                stopInfoArray = stopInfo.trim().split(",");
                String stopName = stopInfoArray[2];
                // Split the stop name into individual words to find keywords
                String[] splitName = stopName.split("\\s+");
                String keyword = splitName[0];
                if(keyword.equals("FLAGSTOP")){
                    // Move the two keywords to the end of the stop name as FLAGSTOP is always accompanied by another prefix
                    Collections.rotate(Arrays.asList(splitName), -2);
                }
                else if(keyword.equals("WB") || keyword.equals("NB") || keyword.equals("SB") || keyword.equals("EB")){
                    // Move the keyword to the end of the stop name
                    Collections.rotate(Arrays.asList(splitName), -1);
                }
                stopName = String.join(" ", splitName);
                tst.insert(stopName, stopInfo);
            }
            scanner.close();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
