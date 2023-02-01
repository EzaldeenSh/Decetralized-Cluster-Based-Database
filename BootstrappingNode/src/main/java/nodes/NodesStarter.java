package nodes;

import java.io.IOException;

public class NodesStarter {
    public void startAllNodes() throws IOException {

        ProcessBuilder builder1 = new ProcessBuilder("cmd.exe" , "/c" , "cd C:\\Users\\User\\Desktop\\Newfolder & start \"Node1\" java -jar Node1.jar");
        builder1.redirectErrorStream(true);
        builder1.start();
        ProcessBuilder builder2 = new ProcessBuilder("cmd.exe" , "/c" , "cd C:\\Users\\User\\Desktop\\Newfolder & start \"Node2\" java -jar Node2.jar");
        builder2.redirectErrorStream(true);
        builder2.start();
        ProcessBuilder builder3 = new ProcessBuilder("cmd.exe" , "/c" , "cd C:\\Users\\User\\Desktop\\Newfolder & start \"Node3\" java -jar Node3.jar");
        builder3.redirectErrorStream(true);
        builder3.start();
        ProcessBuilder builder4 = new ProcessBuilder("cmd.exe" , "/c" , "cd C:\\Users\\User\\Desktop\\Newfolder & start \"Node4\" java -jar Node4.jar");
        builder4.redirectErrorStream(true);
        builder4.start();

    }
}
