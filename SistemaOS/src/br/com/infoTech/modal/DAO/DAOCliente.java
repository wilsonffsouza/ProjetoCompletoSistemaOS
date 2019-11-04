package br.com.infoTech.modal.DAO;

import br.com.infoTech.modal.Cliente;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson F.F. Souza
 */
public class DAOCliente {

    private final Connection connection;

    public DAOCliente(Connection connection) {
        this.connection = connection;
    }

    public void Insert(Cliente cliente ) throws SQLException {
        String sql = "insert into tb_Clientes(nome_Cliente,end_Cliente,nun_end_Cliente,bairro_Cliente,cid_Cliente,estado_Cliente,fone_Cliente,cel_Cliente,email) values (?,?,?,?,?,?,?,?,?);";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getEnd_Cliente());
        pst.setString(3, cliente.getNun_end_Cliente());
        pst.setString(4, cliente.getBairro_Cliente());
        pst.setString(5, cliente.getCid_Cliente());
        pst.setString(6, cliente.getEstado_Cliente());
        pst.setString(7, cliente.getTelefone());
        pst.setString(8, cliente.getCel_Cliente());
        pst.setString(9, cliente.getEmail());
        pst.execute();
    }

    public void Update(Cliente cliente) throws SQLException {
        String sql = "update tb_Clientes set nome_Cliente=?,end_Cliente=?,nun_end_Cliente=?,bairro_Cliente=?,cid_Cliente=?,estado_Cliente=?,fone_Cliente=?,cel_Cliente=?,email=? where nome_Cliente=?";
        PreparedStatement pst = connection.prepareStatement(sql);
  
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getEnd_Cliente());
        pst.setString(3, cliente.getNun_end_Cliente());
        pst.setString(4, cliente.getBairro_Cliente());
        pst.setString(5, cliente.getCid_Cliente());
        pst.setString(6, cliente.getEstado_Cliente());
        pst.setString(7, cliente.getTelefone());
        pst.setString(8, cliente.getCel_Cliente());
        pst.setString(9, cliente.getEmail());
        pst.setString(10, cliente.getNome());
        pst.execute();
    }

    public void InsertOrUpdate(Cliente cliente) throws SQLException {
       boolean existe = existiClienteCadastrado(cliente);
        if (existe) {
            Update(cliente);
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
        } else {
            Insert(cliente);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        }
    }

    public void Delete(Cliente cliente) throws SQLException {
        String sql = "delete from tb_Clientes where id_cliente=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, cliente.getID());
        pst.execute();
    }

    public ArrayList<Cliente> selectAll() throws SQLException {
        String sql = "select * from tb_Clientes";
        PreparedStatement pst = connection.prepareStatement(sql);
        return pesquisa(pst);
    }

    private ArrayList<Cliente> pesquisa(PreparedStatement pst) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        pst.execute();
        ResultSet rs = pst.getResultSet();
        
        while (rs.next()) {

            int id = rs.getInt("id_Cliente");
            String nome_Cliente = rs.getString("nome_Cliente");
            String end_Cliente = rs.getString("end_Cliente");
            String nun_end_Cliente = rs.getString("nun_end_Cliente");
            String bairro_Cliente = rs.getString("bairro_Cliente");
            String cid_Cliente = rs.getString("cid_Cliente");
            String estado_Cliente = rs.getString("estado_Cliente");
            String fone = rs.getString("fone_Cliente");
            String cel = rs.getString("cel_Cliente");
            String email = rs.getString("email");

            Cliente clienteDadosDoBanco = new Cliente(id, nome_Cliente, end_Cliente, nun_end_Cliente, bairro_Cliente, cid_Cliente, estado_Cliente, fone,cel, email);
            clientes.add(clienteDadosDoBanco);
        }
        return clientes;
    }

    public Cliente selectNome(Cliente cliente) throws SQLException {
        String sql = "select * from tb_Clientes where nome_Cliente=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, cliente.getNome());
        return pesquisa(pst).get(0);
    }
    public Cliente selectID(Cliente cliente) throws SQLException{
        String sql ="select * from tb_CLientes where id_Cliente=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, cliente.getID());
        return pesquisa(pst).get(0);
    }

    public boolean existiClienteCadastrado(Cliente cliente) throws SQLException {
        String sql = "select * from tb_Clientes where nome_Cliente=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, cliente.getNome());
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        return rs.next();
    }
    
    public Cliente SelectDinamica(Cliente cliente) throws SQLException{
        String sql= "select * from tb_Clientes where nome_Cliente like ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, cliente.getNome()+"%");
        return pesquisa(pst).get(0);
    }
}
