import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    List<String> expect;
    @Before
    public void setUp(){
        expect = List.of("https://something.com", "some-page.html");
    }
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("/Users/xiuyuan/Documents/GitHub/markdownparse/test-file.md"));
        
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    
}
