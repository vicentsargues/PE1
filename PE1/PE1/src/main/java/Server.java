import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int SERVER_PORT = 9876;

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ServerSocket server = new ServerSocket(SERVER_PORT);
        Socket clientConnection = null;
        while (true) {
            clientConnection = server.accept();
            System.out.println("El cliente se a conectado al puerto " + clientConnection.getPort());
            OutputStream outputStream = clientConnection.getOutputStream();
            InputStream input = clientConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream));


            ArrayList<String> commands = new ArrayList<String>();
            String add = "add";
            String remove = "remove";
            String notify = "notify";
            String block = "block";
            String unblock = "unblock";
            String unplug = "unplug";
            commands.add(add);
            commands.add(remove);
            commands.add(notify);
            commands.add(block);
            commands.add(unblock);
            commands.add(unplug);
            String line;
            String name;
            String mail;
            String surname;
            String action;
            String unplugC;
            String si="si";

            String codeBlock=null;
            Boolean blocked = false;
            ArrayList<String> users = new ArrayList<String>();

            printer.println("Bienvenido");

            while ((line = reader.readLine()) != null) {
                System.out.println("Introduce el comando deseado");
                if(blocked.equals(false)) {
                    if (line.equals(commands.get(5)) && blocked.equals(false)) {
                        System.out.println("Cliente connectado al WorkerUnplug");
                        System.out.println("Vas a ser desconectado , estas seguro?");
                        unplugC= reader.readLine();
                        if (unplugC.equals(si)){
                            System.out.println("El seevidor va a ser cerrado");
                            server.close();
                            System.exit(1);

                        }
                        if (line.equals(commands.get(3)) && blocked.equals(false)) {
                            System.out.println("Cliente connectado al WorkerBlock");

                            codeBlock = "";
                            while (codeBlock.length() != 6) {
                                System.out.println("Escribe El codigo de bloqueo (Solo puede tener 6 Caracteres) ");
                                codeBlock = reader.readLine();
                                if (codeBlock.length() == 6) {
                                    System.out.println("has introducido un codigo correcto");
                                    blocked = true;
                                } else {
                                    System.out.println("Debe tener 6 caracteres");

                                }
                            }


                        }
                    if (line.equals(commands.get(0)) && blocked.equals(false)) {

                        System.out.println("Cliente connectado al WorkerADD");
                        System.out.println("Escribe el Nombre");
                        name = reader.readLine();
                        System.out.println("Escribe el Email");
                        mail = reader.readLine();
                        System.out.println("Escribe el surname");
                        surname = reader.readLine();
                        executorService.execute(new W-Add(clientConnection, name, mail, surname));
                    }
                    if (line.equals(commands.get(1)) && blocked.equals(false)) {

                        System.out.println("Cliente connectado al WorkerRemove");


                        System.out.println("Escribe el Nombre ");
                        name = reader.readLine();
                        System.out.println("Escribe el Email");
                        mail = reader.readLine();
                        System.out.println("Escribe el surname");
                        surname = reader.readLine();

                        executorService.execute(new W-Remove(clientConnection, name, mail, surname));
                    }
                    if (line.equals(commands.get(2)) && blocked.equals(false)) {
                        System.out.println("Cliente connectado al WorkerNotify");
                        System.out.println("Escribe la accion ");
                        action = reader.readLine();
                        executorService.execute(new W-Notify(clientConnection, action));
                    }



                    }
                }else{
                    System.out.println("Servido bloqueado");
                }
                if (line.equals(commands.get(4))&&blocked.equals(true)) {
                    System.out.println("Cliente connectado al WorkerUnBlock");
                    System.out.println("Introduce el codigo de Desbloqueo");
                    while(blocked.equals(true)){
                        if(codeBlock.equals(reader.readLine())){
                            System.out.println("el Servidor se ha desbloqueado");
                            blocked=false;

                        }else{
                            System.out.println("el codigo introducido no es correcto");

                        }
                    }




                }


                 }



        }
    }

}


