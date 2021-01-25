import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class W-Add implements Runnable {

    private Socket connection;
    String name;
    String mail;
    String surname;
     ArrayList<String> users =new ArrayList<String>() ;
    String user;



    public W-Add(Socket clientConnection,String name,String mail,String surname) {
        this.connection = clientConnection;
        this.name=name;
        this.mail=mail;
        this.surname=surname;
    }

    public void run() {
        try {
            File file = new File("users.txt");
            try (FileReader files = new FileReader(file)) {
                StringBuffer sb = new StringBuffer();
                while (files.ready()) {
                    char car = (char) f.read();
                    if (car == '\n') {
                        users.add(sb.toString());
                        sb = new StringBuffer();
                    } else {
                        sb.append(car);
                    }
                }
                if (sb.length() > 0) {
                    users.add(sb.toString());
                }
            }
            System.out.println(users);

            FileWriter writer = new FileWriter(file);

            Scanner sc = new Scanner(System.in);
            user=(name+";"+mail+";"+surname);
            System.out.println(user);
            users.add(user);
            System.out.println(users.get(0));
            for (int i = 0; i < users.size(); i++) {
                writer.write(users.get(i)+'\n');
            }

            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
