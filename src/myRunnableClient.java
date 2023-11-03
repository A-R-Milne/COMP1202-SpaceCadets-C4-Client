import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class myRunnableClient implements Runnable {
    private final Socket socket;
    final int END_OF_SENTENCE = 10;
    private Boolean exitCommand;
    
    public myRunnableClient(Socket socket, Boolean exitCommand) {
        this.socket = socket;
        this.exitCommand = exitCommand;
    }
    
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            int input;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            while (!exitCommand) {
                input = inputStream.read();

                if (input != END_OF_SENTENCE) {
                    bytes.write((byte) input);

                } else if (bytes.size()>0) {
                    System.out.println(bytes.toString());
                    bytes.reset();
                }
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
