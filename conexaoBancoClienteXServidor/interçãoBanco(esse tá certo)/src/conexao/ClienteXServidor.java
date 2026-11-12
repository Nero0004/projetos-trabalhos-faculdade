/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;


import Threads.TreadServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author laboratorio
 */
public class ClienteXServidor {
    public static void main(String[] args) {
    int porta = 12345; // Use uma constante para a porta
    try (ServerSocket servidorSocket = new ServerSocket(porta)) {
        System.out.println("Servidor aguardando conexoes na porta " + porta);
        while (true) {
            try {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Conexao aceita de " + clienteSocket.getInetAddress());
                
                                // Criar uma nova thread para lidar com o cliente
                Thread threadCliente = new TreadServer(clienteSocket);
                threadCliente.start();
            } catch (IOException ex) {
                System.out.println("Erro ao aceitar conexão do cliente");
            }
        }
    } catch (IOException ex) {
        System.out.println("Erro ao criar o ServerSocket");
        }
    }
}
