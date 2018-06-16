/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsRedes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * Classe que ouve a mensagens dos usários caso tenha recebido alguma.
 * Se tiver recebido, armazena em uma lista estática.
 */
public class NewClass implements Runnable{
    private Cliente1a client;

    public NewClass(Cliente1a client) {
        this.client = client;
        Thread t = new Thread(this);
        t.start();
    }
    
    

    @Override
    public void run() {
        while(true){
            try {
                ObjectInputStream entrada = new ObjectInputStream(client.getCliente_socket().getInputStream());
                Mensagem msg = (Mensagem) entrada.readObject();
                client.msgList.add(msg);
                JOptionPane.showMessageDialog(client, "Nova Mensagem!");
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
