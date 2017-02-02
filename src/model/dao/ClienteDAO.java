/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author daniel
 */
public class ClienteDAO {
    
    public void create(Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Cliente (Nome, Endereco, Telefone, Email) VALUES (?,?,?,?)");
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Cliente");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setEndereco(rs.getString("Endereco"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setEmail(rs.getString("Email"));
                clientes.add(cliente);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o banco de dados: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    
    public void update(Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Cliente SET Nome = ?, Endereco = ?, Telefone = ?, Email = ? WHERE id = ?");
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            stmt.setInt(5, c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            
            stmt.setInt(1, c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
}
