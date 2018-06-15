/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourThings;

/**
 *
 * @author franc
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Receiver implements Runnable{
    Cliente1a cliente;
    
    public Receiver(Cliente1a cliente){
        this.cliente = cliente;
        Thread t = new Thread(this);
        t.start();
    }

    /*
    * Da Lista de Mensagens recebidas, função mostra a lista em formato de pilha na tela.
    * */

    @Override
    public void run() {

        try {
            while (true) {
                /*
                * Thread para cada usuário ficar recebendo mensagem em qualquer momento.
                * */
                ObjectInputStream entrada = new ObjectInputStream(this.cliente.getCliente_socket().getInputStream());
                Mensagem msg = (Mensagem) entrada.readObject();
                /*
                * Adiciona as mensagens nem uma lista.
                * */
                cliente.msgList.add(msg);
                JOptionPane.showMessageDialog(cliente, "Nova Mensagem!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

