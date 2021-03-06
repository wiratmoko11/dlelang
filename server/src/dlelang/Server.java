package dlelang;

import dlelang.implement.BarangImplement;
import dlelang.implement.TransaksiImplement;
import dlelang.implement.UserImplement;
import dlelang.model.Barang;
import dlelang.model.Message;
import dlelang.model.Transaksi;
import dlelang.model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Created by Ultrabook on 4/23/2017.
 */
public class Server {
    private static final int PORT = 9100;
    private static HashSet<ObjectOutputStream> listeners = new HashSet<>();

        public static void main(String[] args) throws Exception {
            System.out.println("Socket dlelang.Server Running");
            ServerSocket listener = new ServerSocket(PORT);

            try {
                while (true) {
                    new Handler(listener.accept()).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                listener.close();
            }
        }

    private static class Handler extends Thread{
        private Socket socket;
        private BarangImplement crudBarang;
        private TransaksiImplement crudTransaksi;
        private UserImplement crudUser;
        public Handler(Socket socket) throws IOException {
            this.socket = socket;
        }

        @Override
        public void run() {
            try(
                    InputStream is = socket.getInputStream();
                    ObjectInputStream input = new ObjectInputStream(is);
                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream output = new ObjectOutputStream(os);
                    ){
                    listeners.add(output);
                    while (socket.isConnected()){
                        Message message = (Message) input.readObject();
                        if(message != null){
                            System.out.println("Ada Pesan");
                            switch (message.getMessageType()){
                                case 1:
                                    crudBarang = new BarangImplement();
                                    // Aksi Tambah Barang
                                    if(message.getMessageAct() == 1){
                                        Barang barang = (Barang) message.getMessageContent();
                                        System.out.println(barang.getNamaBarang());
                                        int idBarang = crudBarang.insertBarang(barang);
                                        ((Barang) message.getMessageContent()).setIdBarang(idBarang);
                                        send(message);
                                    //Aksi Update Barang
                                    }else if(message.getMessageAct() == 2){
                                        Barang barang = (Barang) message.getMessageContent();
                                        crudBarang.updateBarang(barang, 1);
                                        send(message);
                                    }else if(message.getMessageAct() == 3){
                                        crudBarang.deleteBarang(message.getObjectID());
                                        send(message);
                                    }
                                    break;
                                //Transaksi
                                case 2:
                                    System.out.println("Pesan Type = "+message.getMessageType());
                                    System.out.println("Aksi Pesan = "+message.getMessageAct());
                                    crudTransaksi = new TransaksiImplement();
                                    switch (message.getMessageAct()){
                                        //Insert Transaksi
                                        case 1:
                                            System.out.println("Insert Data Transaksi");
                                            Transaksi transaksi = (Transaksi) message.getMessageContent();
                                            int idTransaksi = crudTransaksi.insert(transaksi);
                                            ((Transaksi)message.getMessageContent()).setIdTransaksi(idTransaksi);
                                            send(message);
                                            break;
                                    }
                                    break;
                                //User
                                case 3:
                                    switch (message.getMessageType()){
                                        case 1:
                                            User user = (User) message.getMessageContent();
                                            crudUser.insertUser(user);
                                            send(message);
                                            break;
                                    }
                                    break;
                            }

                        }else {
                            closeConnections();
                        }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally{
                closeConnections();
            }
        }



        private void write(Message message){
            for (ObjectOutputStream listener: listeners){
                try {
                    listener.writeObject(message);
                    listener.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private Message send(Message message){
            write(message);
            return message;
        }

        private Barang sendBarang(Barang barang){
            Barang newBarang = barang;
//            write(newBarang);
            return newBarang;
        }

        private synchronized void closeConnections(){
            System.out.println("Koneksi Tutup");
        }

    }
}


