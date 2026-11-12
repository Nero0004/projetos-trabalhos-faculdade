/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import conexao.Conexao;
import java.sql.*;
import Beans.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class PessoaDao {
    private Conexao conexao;
    private Connection conn;
    
    public PessoaDao(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void incerir (Pessoa pessoa){
        String sql = "INSERT INTO pessoa (nome, sexo, idioma) VALUES (?,?,?);";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, pessoa.getIdioma());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }
    
    public Pessoa getPessoa(int idPessoa) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                p.setIdioma(rs.getString("idioma"));
                return p;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa: " + e.getMessage());
            return null;
        }
    }
    
    public void editar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome=?, sexo=?, idioma=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, pessoa.getIdioma());
            stmt.setInt(4, pessoa.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
        }
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM pessoa WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir pessoa: " + ex.getMessage());
        }
    }
    
    public List<Pessoa> getPessoas(){
        String sql = "SELECT * FROM pessoa";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Pessoa> listaPessoas = new ArrayList<>();
            while (rs.next()){
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                p.setIdioma(rs.getString("idioma"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar todas as pessoas: "+ex.getMessage());
            return null;
        }
    }
    
    // Método adicionado para consultar filtrando por nome (conforme os slides)
    public List<Pessoa> getPessoasNome(String nome){
        String sql = "SELECT * FROM pessoa WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Pessoa> listaPessoas = new ArrayList<>();
            while (rs.next()){
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                p.setIdioma(rs.getString("idioma"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar pessoas por nome: "+ex.getMessage());
            return null;
        }
    }
}