// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket <0 ) break;
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket < 0) break;
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen <0) break;
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen <0) break;
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
        System.out.println(getLinksRefined(contents));
    }
    public static ArrayList<String> getLinksRefined(String markdown){
        ArrayList<String> toReturn = new ArrayList<>();
        Stack<Integer> result = new Stack<>();
        for(int i = 0; i<markdown.length();i++){
            if(markdown.charAt(i) == '('){
                result.add(i);
            }
            if(markdown.charAt(i)==')'){
                int a = result.pop();
                toReturn.add(markdown.substring(a+1,i));
            }
        }
        return toReturn;
    }
}