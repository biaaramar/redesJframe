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
import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Monitor implements Runnable {
    private gerenteServidor servidor;
    private Socket cliente;
    private Servidor server;

    public Monitor(gerenteServidor servidor, Socket cliente, Servidor server) {
        this.server = server;
        this.servidor = servidor;
        this.cliente = cliente;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Servidor2 server2 = new Servidor2();
        ObjectInputStream entrada;
        boolean flag = true;
        while(flag){
            try {
                /*
                * Recebe os dados do cliente como nome e salva em clienteArrayList.
                * */
                entrada = new ObjectInputStream(this.cliente.getInputStream());
                Cliente cliente = (Cliente) entrada.readObject();
               // servidor.clienteArrayList.add(cliente);
                flag = cliente.flag;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        while (flag) try {
            /*
            * Imprime no servidor a mensagem recebida do usuário.
            * */
            entrada = new ObjectInputStream(this.cliente.getInputStream());
            Mensagem msg = (Mensagem) entrada.readObject();
            flag = msg.flag;
            System.out.println();
            System.out.println("Remeente: " + msg.getRemetente());
            System.out.println("Destinatario: " + msg.getDestinatario());
            System.out.println("Data: " + msg.getDate());
            System.out.println("Assunto: " + msg.getAssunto());
            System.out.println("Texto: " + msg.getTexto());
            System.out.println();
            if(servidor.clienteArrayList.isEmpty()){
                System.out.println("Sem clientes!");
            }else{
                /*
                * Procura o destinatário da mensagem e a envia pra ele procurando na lista de Sockets.
                * */          
                for (int i = 0; i < servidor.clienteArrayList.size(); i++) {
                    if (servidor.clienteArrayList.get(i).getName().intern() ==
                            msg.getDestinatario().intern()) {
                        ObjectOutputStream saida = new ObjectOutputStream(servidor.ipArrayList.get(i).getOutputStream());
                        saida.writeObject(msg);
                        saida.flush();
                    }
                    /*
                    * Caso o usuário tenha se desconectado(flag acionada), Excluiremos das listas.
                    **/                
                    if(!flag && servidor.clienteArrayList.get(i).getName().intern()== msg.getRemetente().intern()){
                        servidor.clienteArrayList.remove(i);
                        servidor.ipArrayList.remove(i);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}


