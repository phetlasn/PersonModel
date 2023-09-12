import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> persons = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        String id = "";
        String fName = "";
        String lName = "";
        String title = "";
        String record = "";
        int yob = 0;

        boolean done = false;

        do {
            id = SafeInput.getNonZeroLenString(in, "Enter your ID [6 DIGITS]");
            fName = SafeInput.getNonZeroLenString(in, "Enter your First name");
            lName = SafeInput.getNonZeroLenString(in, "Enter your Last name");
            title = SafeInput.getNonZeroLenString(in, "Enter your Title");
            yob = SafeInput.getRangedInt(in, "Enter your Year of Birth", 1000, 3000);

            record = id + ", " + fName + ", " + lName + ", " + title + ", " + yob;
            persons.add(record);

            done = SafeInput.getYNConfirm(in, "Are there any more record you would like to add?");

        }while(done);

        for (String p: persons)
            System.out.println(p);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file,CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec: persons){
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data successfully written!");

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
