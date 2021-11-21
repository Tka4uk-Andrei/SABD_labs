package sabd.obfuscation;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlgorithmTest {

    @Test
    public void obfuscateData() {
        String writeFile =  "src/test/resources/test_obfuscated_write.txt";
        Obfuscation obfuscation = new Obfuscation();
        obfuscation.obfuscation(
                "src/test/resources/test_initial.xml",
                writeFile
        );
        List<String> data = FileReader.readFromFile(writeFile);
        assertEquals(
                "dIsH6XirQ.EtcQHV\n" +
                        "SiH6XirQ.qvW6i\n" +
                        "St4i6ItU.jUBIi\n" +
                        "IB.ooo"
                , String.join("\n", data));
    }

    @Test
    public void deobfuscateData() {
        String writeFile =  "src/test/resources/test_deobfuscated.xml";
        Deobfuscation deobfuscation = new Deobfuscation();
        deobfuscation.deobfuscation(
                "src/test/resources/test_obfuscated_read.txt",
                writeFile
        );
        List<String> data = FileReader.readFromFile(writeFile);
        assertEquals(
                "<employees>\n" +
                        "  <employee id=\"333\">\n" +
                        "    <firstName>David</firstName>\n" +
                        "    <lastName>Feezor</lastName>\n" +
                        "    <location>USA</location>\n" +
                        "  </employee>\n" +
                        "</employees>"
                , String.join("\n", data));
    }

    @Test
    public void initialDataEqualsDeobfuscated() {
        String initialFile = "src/test/resources/test_initial.xml";
        String writeFileObfuscated =  "src/test/resources/test_obfuscated_write.txt";
        String writeFileDeobfuscated =  "src/test/resources/test_deobfuscated.xml";

        Obfuscation obfuscation = new Obfuscation();
        obfuscation.obfuscation(initialFile, writeFileObfuscated);

        Deobfuscation deobfuscation = new Deobfuscation();
        deobfuscation.deobfuscation(writeFileObfuscated, writeFileDeobfuscated);

        List<String> initialData = FileReader.readFromFile(initialFile);
        List<String> finalData = FileReader.readFromFile(writeFileDeobfuscated);

        assertEquals(initialData, finalData);
    }
}
