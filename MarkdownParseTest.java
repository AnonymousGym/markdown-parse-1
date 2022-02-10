// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class MarkdownParseTest {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket <0 ) break;
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket <0 ) break;
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen <0 ) break;
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen <0 ) break;
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            System.out.println("Value of currentIndex at end of loop: "+currentIndex);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);


    }
}