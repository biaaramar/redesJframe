/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ítalo_2
 */
public class recThread implements Runnable{
    private Cliente1a client;

    public recThread(Cliente1a client) {
        this.client = client;
        Thread t = new Thread(this);
        t.start();
    }
    
    

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("to aqui");
                ObjectInputStream entrada = new ObjectInputStream(client.getCliente_socket().getInputStream());
                Mensagem msg = (Mensagem) entrada.readObject();
                System.out.println("Chegou!");
                client.msgList.add(msg);
                JOptionPane.showMessageDialog(client, "Nova Mensagem!");
            } catch (IOException ex) {
                Logger.getLogger(recThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(recThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
