package br.com.infoTech.modal.DAO;

import br.com.infoTech.modal.Usuario;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson F.F. Souza
 */
public class DAOUsuario {

    private final Connection connection;

    public DAOUsuario(Connection connection) {
        this.connection = connection;
    }

    public void Insert(Usuario usuario) throws SQLException {
        String sql = "insert into tb_usuarios(tipoUsuario,usuario, fone,login,senha) values (?,?,?,?,?);";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario.getTipoUsuario());
        pst.setString(2, usuario.getNome());
        pst.setString(3, usuario.getTelefone());
        pst.setString(4, usuario.getLogin());
        pst.setString(5, usuario.getSenha());
        pst.execute();
    }

    public void Update(Usuario usuario) throws SQLException {
        String sql = "update tb_usuarios set tipoUsuario=?,usuario=?,fone=?,login=?,senha=? where usuario=?";
        PreparedStatement pst = connection.prepareStatement(sql);
  
        pst.setString(1, usuario.getTipoUsuario());
        pst.setString(2, usuario.getNome());
        pst.setString(3, usuario.getTelefone());
        pst.setString(4, usuario.getLogin());
        pst.setString(5, usuario.getSenha());
        pst.setString(6, usuario.getNome());
        pst.execute();
    }

    public void InsertOrUpdate(Usuario usuario) throws SQLException {
       boolean existi = existiUsuarioCadastrado(usuario);
        if (existi) {
            Update(usuario);
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
        } else {
            Insert(usuario);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        }
    }

    public void Delete(Usuario usuario) throws SQLException {
        String sql = "delete from tb_usuarios where usuario=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario.getNome());
        pst.execute();
    }

    public ArrayList<Usuario> selectAll() throws SQLException {
        String sql = "select * from tb_usuarios";
        PreparedStatement pst = connection.prepareStatement(sql);
        return pesquisa(pst);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement pst) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        pst.execute();
        ResultSet rs = pst.getResultSet();
        
        while (rs.next()) {

            int id = rs.getInt("id_User");
            String tipoUsuario = rs.getString("tipoUsuario");
            String usuario = rs.getString("usuario");
            String fone = rs.getString("fone");
            String login = rs.getString("login");
            String senha = rs.getString("senha");

            Usuario usuarioDadosDoBanco = new Usuario();
            usuarioDadosDoBanco.setID(id);
            usuarioDadosDoBanco.setTipoUsuario(tipoUsuario);
            usuarioDadosDoBanco.setNome(usuario);
            usuarioDadosDoBanco.setTelefone(fone);
            usuarioDadosDoBanco.setLogin(login);
            usuarioDadosDoBanco.setSenha(senha);
            usuarios.add(usuarioDadosDoBanco);
        }
        return usuarios;
    }

    public Usuario selectNome(Usuario usuario) throws SQLException {
        String sql = "select * from tb_usuarios where usuario=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario.getNome());
        return pesquisa(pst).get(0);
    }
    
    public Usuario selectLogin(Usuario usuario) throws SQLException {
        String sql = "select * from tb_usuarios where login=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario.getLogin());
        return pesquisa(pst).get(0);
    }

    public boolean existiUsuario(Usuario usuario) throws SQLException {
        String sql = "select * from tb_usuarios where login=? and senha=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario.getLogin());
        pst.setString(2, usuario.getSenha());
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        return rs.next();
    }
    
    public boolean existiUsuarioCadastrado(Usuario usuario) throws SQLException {
        String sql = "select * from tb_usuarios where login=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario.getLogin());
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        return rs.next();
    }
}
