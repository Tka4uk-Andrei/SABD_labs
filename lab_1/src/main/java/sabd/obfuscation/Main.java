package sabd.obfuscation;

/*
Jackson XML parser articles
1) https://mincong.io/2019/03/19/jackson-xml-mapper/
2) https://stackabuse.com/serialize-and-deserialize-xml-in-java-with-jackson/
*/

public class Main {

    public static final String OBFUSCATION = "obfuscation";
    public static final String DEOBFUSCATION = "deobfuscation";

    public static void main(String[] args) throws IllegalStateException {
        String mode = System.getenv("mode");

        if (mode == null)
        {
            throw new IllegalStateException(
                    "Environment variable \"mode\" should be specified!"
            );
        }

        switch (mode) {
            case OBFUSCATION:
                Obfuscation obfuscation = new Obfuscation();
                obfuscation.obfuscation();
                System.out.println(OBFUSCATION + "  done");
                break;
            case DEOBFUSCATION:
                Deobfuscation deobfuscation = new Deobfuscation();
                deobfuscation.deobfuscation();
                System.out.println(DEOBFUSCATION + "  done");
                break;
            default:
                throw new IllegalStateException(
                        "Expect mode to be: " + OBFUSCATION + " or " + DEOBFUSCATION + ". Have: " + mode
                );
        }
    }
}
