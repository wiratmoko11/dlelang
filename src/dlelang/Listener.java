package dlelang;

import dlelang.controller.AdminBarangController;
import dlelang.controller.AdminUserController;
import dlelang.controller.UserController;
import dlelang.implement.BarangImplement;
import dlelang.model.Barang;
import dlelang.model.Message;
import dlelang.model.Transaksi;
import dlelang.model.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by Ultrabook on 4/27/2017.
 */
public class Listener implements Runnable {

    private Socket socket;
    public String hostname;
    public int port;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;
    private static AdminBarangController adminBarangController;
    private AdminUserController adminUserController;
    private UserController userController;


    public Listener(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public static void setControllerAdminBarang(AdminBarangController controller){
        adminBarangController = controller;
    }


    @Override
    public void run() {
        System.out.println("Listener Socket");
        BarangImplement crudBarang = new BarangImplement();
        try {
            socket = new Socket(hostname, port);

            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);

            is = socket.getInputStream();
            input = new ObjectInputStream(is);

            System.out.println(socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (socket.isConnected()){
                Message message = (Message) input.readObject();
                if(message != null){
                    switch (message.getMessageType()){
                        case 1:
                            if(message.getMessageAct() == 1){
                                crudBarang.sendBarang((Barang) message.getMessageContent());
                            }else if(message.getMessageAct() == 2){
                                crudBarang.updateBarang((Barang) message.getMessageContent(), 1);
                            }
                            break;
                        case 2:
                            if(message.getMessageAct() == 1){

                            }else if(message.getMessageAct() == 2){

                            }
                            break;
                        case 3:
                            if(message.getMessageAct() == 1){

                            }else if(message.getMessageAct() == 2){

                            }
                            break;
                    }
                }
            }
        }catch (Exception e){

        }

    }

    public static void sendMessage(Message msg) throws IOException {
        oos.writeObject(msg);
        oos.flush();
    }
    public void sendDataTransaksi(Transaksi transaksi) throws IOException {
        oos.writeObject(transaksi);
        oos.flush();
    }
    public void sendDataUser(User user) throws IOException{
        oos.writeObject(user);
        oos.flush();
    }
}
