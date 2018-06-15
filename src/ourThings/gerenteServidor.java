/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourThings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franc
 */
public class gerenteServidor implements Runnable{
    static public  ArrayList<Cliente> clienteArrayList = new <Cliente>ArrayList();
    static  ArrayList<Socket> ipArrayList = new <Socket>ArrayList();
    ServerSocket socketServer;
    Socket socket;
    Servidor server;
    
    public gerenteServidor(Servidor server) throws IOException{
        this.server = server;
        socketServer = new ServerSocket(2130);
        Thread t = new Thread(this);
        t.start();
    }
    

    @Override
    public void run() {
        while(true){
            Socket user;
            try {
                user = socketServer.accept();
                JOptionPane.showMessageDialog(server, "Conectado com "+user.getInetAddress().getHostAddress());    
                System.out.println();
                //this.ipArrayList.add(user);
                Monitor t = new Monitor(this, user, server);
            } catch (IOException ex) {
                Logger.getLogger(gerenteServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
