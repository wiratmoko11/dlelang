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
    private AdminBarangController adminBarangController;
    private AdminUserController adminUserController;
    private UserController userController;


    public Listener(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public Listener(String hostname, int port, AdminBarangController controller){
        this.hostname = hostname;
        this.port = port;
        this.adminBarangController = controller;
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
            System.out.println("Socket is Connected ? ="+socket.isConnected());
            while (socket.isConnected()){
                Message message = (Message) input.readObject();
                System.out.println("Any message ? = "+message != null);
                if(message != null){
                    System.out.println("Found Message");
                    switch (message.getMessageType()){
                        case 1:
                            if(message.getMessageAct() == 1){
                                crudBarang.insertBarang((Barang) message.getMessageContent());
                            }else if(message.getMessageAct() == 2){
                                crudBarang.updateBarang((Barang) message.getMessageContent(), ((Barang) message.getMessageContent()).getIdBarang());
                            }else if(message.getMessageAct() == 3){
                                crudBarang.deleteBarang(message.getObjectID());
                            }
                            System.out.println(adminBarangController);

                            adminBarangController.showData(crudBarang.get());
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
