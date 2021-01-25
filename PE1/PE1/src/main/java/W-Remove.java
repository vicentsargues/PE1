import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.Socket;
import java.util.ArrayList;

public class W-Remove implements Runnable {
    private Socket connection;
    String mail;
    String name;
    String surname;
    ArrayList<String> users =new ArrayList<String>() ;
    String user;
    String arrayUser;

    public W-Remove(Socket clientConnection,String name,String mail,String surname) {
        this.connection = clientConnection;
        this.name=name;
        this.mail=mail;
        this.surname=surname;
    }

    @Override
    public void run() {
        try{
            File file = new File("users.txt");
            try (FileReader f = new FileReader(file)) {
                StringBuffer sb = new StringBuffer();
                while (f.ready()) {
                    char c = (char) f.read();
                    if (c == '\n') {
                        users.add(sb.toString());
                        sb = new StringBuffer();
                    } else {
                        sb.append(c);
                    }
                }
                if (sb.length() > 0) {
                    users.add(sb.toString());
                }
            }
            user=(name+";"+mail+";"+surname);
            System.out.println(users);
            for (int i = 0; i < users.size(); i++) {
                arrayUser=users.get(i);
                System.out.println(arrayUser=users.get(i));
                if (arrayUser.equals(user)){
                    users.remove(users.get(i));
                }

            }
            System.out.println(users);
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < users.size(); i++) {
                writer.write(users.get(i)+'\n');
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
