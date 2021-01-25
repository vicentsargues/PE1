import com.sun.deploy.util.ArrayUtil;

import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;

public class W-Notify implements Runnable {
    String action;
    ArrayList<String> users = new ArrayList<String>();
    String[] mails;

    String user;
    String arrayUser;
    private Socket connection;

    public W-Notify(Socket clientConnection, String name) {
        this.connection = clientConnection;
        this.action = name;
    }

    @Override
    public void run() {
        System.out.println("Se va a notificar a todos los coreos");
        try {
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
                for (int i = 0; i < users.size(); i++) {
                    mails = users.get(i).split(";");
                    System.out.println("Mail " + mails[1] + " notificado de la accion de la accion" + action);
                    mails = null;


                }
            }

        } catch (Exception e) {

        }

    }
}
