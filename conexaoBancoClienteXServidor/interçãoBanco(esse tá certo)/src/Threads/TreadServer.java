/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import Beans.Pessoa;
import Dao.PessoaDao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author laboratorio
 */
public class TreadServer extends Thread{
    private Socket clienteSocket;
    
    public TreadServer(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }   
    
    @Override
    public void run()   {
try {
            ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());
            
            int id = in.readInt();
            
            System.out.println("ID recebido: " + id);
            
            // Simule a obtenção de uma Pessoa a partir do ID
            PessoaDao pdao = new PessoaDao();
            Pessoa p = pdao.getPessoa(id);
            
            out.writeObject(p);
            
        } catch (IOException ex) {
            System.out.println("Erro ao lidar com o cliente");
        }
    }
    
}
