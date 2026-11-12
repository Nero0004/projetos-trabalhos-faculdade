package conexao;


import java.sql.*;

public class Conexao {
    public Connection getConexao () {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/BDaula?useTimezone=true&serverTimezone=UTC",
                "root", "laboratorio");
            System.out.println("Conexao realizada com sucesso!");
            return conn;
        }
        catch (Exception e) {
            System.out.println("Erro ao conectar no BD" + e.getMessage());
            return null;
        }
    }
}
