
package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ContatoDAO {

    private Connection con;

    public ContatoDAO() {
        con = new Conexao().conectar();
    }

    public ArrayList<Contato> listar() {
        String sql = "SELECT * FROM cadastro";
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setValor(rs.getString("valor"));
                c.setTamanho(rs.getString("tamanho"));
                contatos.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return contatos;
    }

    public ArrayList<Contato> lista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void inserir(Contato c) {
        String sql = "INSERT INTO cadastro (nome, valor, tamanho) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getValor());
            ps.setString(3, c.getTamanho());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  
    }
     public void atualizar(Contato c) {
        String sql = "UPDATE cadastro SET nome=?,valor=?,tamanho=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getValor());
            ps.setInt(4, c.getId());
            ps.setString(3,c.getTamanho());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
      public void excluir(Contato c) {
        String sql = "DELETE FROM cadastro WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    }
