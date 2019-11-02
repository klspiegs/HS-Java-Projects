import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class splitString {

    Node head;

    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }

    void detectDuplicates()
    {
        int i = 0;
        /*Another reference to head*/
        Node curr = head;

        /* Traverse list till the last node */
        while (curr != null) {
            Node temp = curr;
            /*Compare current node with the next node and
            keep on deleting them until it matches the current
            node data */
            while(temp!=null && temp.data==curr.data) {
                temp = temp.next;
            }
            /*Set current node next to the next different
            element denoted by temp*/
            curr.next = temp;
            curr = curr.next;
        }
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static String isSpecial(String strSpec) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strSpec);
        while(m.find())
        {
            String s= m.group();
            strSpec=strSpec.replace(m.group(), "");
        }

        return strSpec.toLowerCase();
    }

    public static void main(String[] args) {

        LinkedList<String> unsortedwords = new LinkedList<String>();
        String filePath = "/Users/Katie/IdeaProjects/lljavaBook/src/text.txt";

        String contents = null;
        try {
            contents = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("##### Split string by multiple spaces");
        String[] words = contents.split(" ");
        for(String word : words) {
            if (word.equals("a") || word.equals("A") || word.equals("the") || word.equals("The") || word.equals("and") || word.equals("but") || word.equals("is") || word.equals("an")) {
                System.out.println("NOT ADDING: " + word);
            } else if (isNumeric(word)) {
                System.out.println("NOT ADDING: " + word);
            }
            else {
                unsortedwords.add(isSpecial(word));
            }

        }

        Collections.sort(unsortedwords);
        System.out.println("##### Sorted list");
        System.out.println(unsortedwords);

        for (String word:unsortedwords) {

        }

    }
}
