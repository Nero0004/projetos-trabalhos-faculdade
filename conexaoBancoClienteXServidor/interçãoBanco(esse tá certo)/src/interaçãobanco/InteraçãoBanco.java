/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interaçãobanco;

import conexao.Conexao;

/**
 *
 * @author laboratorio
 */
public class InteraçãoBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao c = new Conexao();
        c.getConexao();
    }
    
}
