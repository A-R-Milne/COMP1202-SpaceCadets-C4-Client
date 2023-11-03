import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final int END_OF_SENTENCE = 10;
        Boolean exitCommand = false;
        
        try(Socket clientSocket = new Socket("localhost",8080);) {
            myRunnableClient threadJob = new myRunnableClient(clientSocket,exitCommand);
            new Thread(threadJob).start();
            OutputStream outputStream = clientSocket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("What is your name?");
            String userInput = scanner.nextLine();
            outputStream.write(userInput.getBytes());
            outputStream.write(END_OF_SENTENCE);
            
            System.out.println("NOTE: Include '|goodbye|' in your message to exit.");
            
            while(!exitCommand) {
                userInput = scanner.nextLine();
                outputStream.write(userInput.getBytes());
                outputStream.write(END_OF_SENTENCE);
                
                if(userInput.contains("|goodbye|")) {
                    exitCommand = true;
                }
            }
        }
    }
}
