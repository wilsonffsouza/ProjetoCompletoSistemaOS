package br.com.infoTech.modal.DAO;

import br.com.infoTech.modal.Cliente;
import br.com.infoTech.modal.Servico;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson F.F. Souza
 */
public class DAOServico {

    private final Connection connection;

    public DAOServico(Connection connection) {
        this.connection = connection;
    }

    public void Insert(Servico servico) throws SQLException {
        String sql = "insert into tb_os (status_os,situacao,equipamento,defeito,servico,tecnico,valor,id_cliente) values(?,?,?,?,?,?,?,?);";
        int id_cliente = servico.getCliente().getID();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, servico.getStatus());
        pst.setString(2, servico.getSituacao());
        pst.setString(3, servico.getEquipamento());
        pst.setString(4, servico.getDefeito());
        pst.setString(5, servico.getSerivco());
        pst.setString(6, servico.getTecnico());
        pst.setFloat(7, servico.getValor());
        pst.setInt(8, id_cliente);
        pst.execute();
    }

    private void Update(Servico servico) throws SQLException {
        String sql = "update tb_os set status_os=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=?,id_cliente=? where os=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, servico.getStatus());
        pst.setString(2, servico.getSituacao());
        pst.setString(3, servico.getEquipamento());
        pst.setString(4, servico.getDefeito());
        pst.setString(5, servico.getSerivco());
        pst.setString(6, servico.getTecnico());
        pst.setString(7, Float.toString(servico.getValor()));
        pst.setInt(8, servico.getCliente().getID());
        pst.setInt(9, servico.getOS());
        pst.execute();
    }

    public void InsertOrUpdate(Servico servico) throws SQLException {
    
        if (existeOS(servico)) {
            Update(servico);
            JOptionPane.showMessageDialog(null, "Ordem de Serviço alterado com sucesso!");
        } else {
            Insert(servico);
            JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrado com sucesso!");
        }
    }

    public boolean existeOS(Servico servico) throws SQLException {
        String sql = "select * from tb_os where os=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, servico.getOS());
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        return rs.next();
    }
    
    public void Delete(Servico servico) throws SQLException{
        String sql = "delete from tb_os where os=? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, servico.getOS());
        pst.execute();
    }
    
    private ArrayList<Servico> pesquisa(PreparedStatement pst) throws SQLException{
        ArrayList<Servico> servicos = new ArrayList<>();
        pst.execute();
        ResultSet rs = pst.getResultSet();
        
        while(rs.next()){
        
            int os = rs.getInt("os");
            Date data = rs.getDate("data_os");
            String status = rs.getString("status_os");
            String situacao = rs.getString("situacao");
            String equipamento = rs.getString("equipamento");
            String defeito = rs.getString("defeito");
            String OsServico = rs.getString("servico");
            String tecnico = rs.getString("tecnico");
            float valor = rs.getFloat("valor");
            int cliente = rs.getInt("id_cliente");
            Cliente c = new Cliente();
            c.setID(cliente);
            Servico servico = new Servico(data, os, c, status, situacao, equipamento, defeito, OsServico, tecnico, valor);
            servicos.add(servico);
    }
        return servicos;
    }
    
    public ResultSet pesquisarOS() throws SQLException{
        String sql = "select os,status_os as Status,equipamento,defeito,id_cliente as Cliente from tb_os";
        PreparedStatement pst= connection.prepareStatement(sql);
        pst.executeQuery();
     return pst.getResultSet();
    }
    
    public ArrayList<Servico> PesquisaOsAll() throws SQLException{
        String sql = "select * from tb_os";
        PreparedStatement pst = connection.prepareStatement(sql);
        return pesquisa(pst);
        
    }
    
    public Servico PesquisaServico(Servico servico) throws SQLException{
        String sql = "select * from tb_os where os=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, servico.getOS());
        return pesquisa(pst).get(0);
        
    }
    
}
