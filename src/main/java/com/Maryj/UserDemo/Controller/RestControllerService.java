package com.Maryj.UserDemo.Controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/api")

public class RestControllerService {

    File messageFile = new File("messages.txt");
    File logFile = new File("log.txt");

    @GetMapping("/message")
    public String getMessage(){
        StringBuilder builder = new StringBuilder();
        String read;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(messageFile))){
            while ((read=bufferedReader.readLine()) != null){
                builder.append(read);
            }

        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return builder.toString();
    }

@GetMapping("/messageCount")
    public String getMessageCount(){
        int messageCount = 0;
        try(BufferedReader bufferedReaderCounter = new BufferedReader(new FileReader(messageFile))) {
            while(bufferedReaderCounter.readLine() != null){
              messageCount++;
            }
        }catch (IOException exception){
            exception.getMessage();
        }
        return String.format("%s message has been posted" ,messageCount);

}

@PostMapping("/message")
    public  String postMessage(@RequestBody String message){
        try(BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(messageFile,true));BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(logFile,true))) {
            bufferedWriter1.write(message + "&");
            String logActivity = "New message created";
            bufferedWriter2.write(logActivity + "\n");

        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        return "New message posted successfully";
}
}



