import java.io.IOException;
import java.util.Random;

public class Utils {

    public static String percyToken = "882b707ebe8e86ab61c9d12726ae5619c9c0277b69fdb0d6e520a104cbee9f0f";
    public static String percyBuildUniqueID = generateRandomUniqueID();

    public Utils() {}

    public static void startPercy() {
        // Run Percy server command that depends on environment variable in the background
        String percyCommand = "set PERCY_TOKEN=" + percyToken + "&&set PERCY_PARALLEL_TOTAL=-1&&set PERCY_PARALLEL_NONCE=" + percyBuildUniqueID + "&& npx percy exec start -v";
        String processCommand = String.format("start /B cmd.exe /c \"%s\" >> percy-start.log 2>&1", percyCommand);
        try {
            ProcessBuilder processProcessBuilder = new ProcessBuilder("cmd.exe", "/c", processCommand);
            processProcessBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopPercy() {
        // Stop the Percy Server
        String percyCommand = "npx percy exec stop -v";
        String processCommand = String.format("start /B cmd.exe /c \"%s\" >> percy-stop.log 2>&1", percyCommand);
        try {
            ProcessBuilder processProcessBuilder = new ProcessBuilder("cmd.exe", "/c", processCommand);
            processProcessBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void finalizePercyBuild() {
        // Stop the Percy Server
        String percyCommand = "set PERCY_TOKEN=" + percyToken + "&&set PERCY_PARALLEL_TOTAL=-1&&set PERCY_PARALLEL_NONCE=" + percyBuildUniqueID + "&& npx percy build:finalize";
        String processCommand = String.format("start /B cmd.exe /c \"%s\" >> percy-build-finalize.log 2>&1", percyCommand);
        try {
            ProcessBuilder processProcessBuilder = new ProcessBuilder("cmd.exe", "/c", processCommand);
            processProcessBuilder.start();
        } catch (IOException e) {
            System.out.println("test");
            e.printStackTrace();
        }
    }

    public static String generateRandomUniqueID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
