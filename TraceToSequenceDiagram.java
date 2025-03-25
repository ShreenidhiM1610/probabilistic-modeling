import java.io.*;

public class TraceToSequenceDiagram {
    public static void main(String[] args) {
        String inputFile = "trace.txt";
        String outputFile = "sequenceDiagram.puml";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {

            pw.println("@startuml");
            pw.println("actor User");
            pw.println("participant Service");

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("->")) {
                    // Extract the state transitions
                    String[] parts = line.split("->");
                    String source = parts[0].trim();
                    String target = parts[1].trim().split(" ")[0]; // Ignore probabilities
                    
                    if (source.equals("0") && target.equals("1")) {
                        pw.println("User -> Service : Request Sent");
                    } else if (source.equals("1") && target.equals("2")) {
                        pw.println("Service -> User : Response Received");
                    } else if (source.equals("2") && target.equals("3")) {
                        pw.println("User -> Service : Session Ended");
                    }
                }
            }

            pw.println("@enduml");
            System.out.println("Sequence diagram definition generated in " + outputFile);

        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}
