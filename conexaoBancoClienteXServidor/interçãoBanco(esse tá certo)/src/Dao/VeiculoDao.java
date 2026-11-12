/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Beans.Pessoa;
import conexao.Conexao;
import java.sql.*;
import Beans.Veiculo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author laboratorio
 */
public class VeiculoDao {

        private Conexao conexao;
    private Connection conn;
    
    public VeiculoDao(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    public void incerir (Veiculo veiculo){
        String sql = "INSERT INTO veiculo (modelo, placa, id_pessoa) VALUES (?,?,?);";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2,veiculo.getPlaca());
            stmt.setString(3, veiculo.getPessoaid());
            stmt.execute();
         
        } catch (SQLException e) {
            System.out.println("Erro ao inserir veiculo: " + e.getMessage());
        }
    }
    public void editar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET modelo=?, placa=?, id_pessoa=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2,veiculo.getPlaca());
            stmt.setString(3, veiculo.getPessoaid());
            stmt.setInt(4, veiculo.getId() );
            stmt.execute();
         
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar veiculo: " + ex.getMessage());
        }
    }
    
        public void excluir(int id) {
        String sql = "DELETE FROM veiculo WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir veiculo: " + ex.getMessage());
        }
    }
        public List<Veiculo> getVeiculos(){
        String sql = "SELECT * FROM pessoa";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Veiculo> listaVeiculos = new ArrayList();
            while (rs.next()){
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                PessoaDao pDAO = new PessoaDao();
                
                Pessoa p = pDAO.getPessoa(rs.getInt("id_pessoa"));
                v.setPessoaid(p);
                listaVeiculos.add(v);
            }
            return listaVeiculos;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar todas as pessoas: "+ex.getMessage());
            return null;
        }
    }
}
