package Produto.Produto.sql.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.wls.shaded.org.apache.bcel.generic.Type;

import Produto.Produto.sql.model.Produto;



@Repository
public class DaoProduto {
	@Autowired
	GenericDao gdao;
	public void Inserir(Produto p) throws ClassNotFoundException, SQLException {
		OPT(p,"I");
	}
	public void Atualizar(Produto p) throws ClassNotFoundException, SQLException {
		OPT(p,"U");
	}
	public void Deletar(Produto p) throws ClassNotFoundException, SQLException {
		OPT(p,"D");
	}
	public void OPT(Produto p,String opt) throws ClassNotFoundException, SQLException {
		Connection c = gdao.getC();
		String sql ="{CALL P (?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1,opt);
		cs.setInt(2, p.getCodigo());
		cs.setString(3, p.getNome());
		cs.setDouble(4, p.getVu());
		cs.setInt(5, p.getQtd());
		cs.execute();
		c.close();
	}
	public Produto Buscar(int val) throws ClassNotFoundException, SQLException {
		Connection c = gdao.getC();
		String sql ="SELECT CODIGO, NOME, VALOR_UNITARIO, QTD_ESTOQUE  FROM PRODUTO WHERE PRODUTO.CODIGO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, val);
		ResultSet rs = ps.executeQuery();
		Produto p = new Produto();
		if(rs.next()) {
			p.setCodigo(val);
			p.setNome(rs.getString("NOME"));
			p.setQtd(rs.getInt("QTD_ESTOQUE"));
			p.setVu(rs.getDouble("VALOR_UNITARIO"));
		}
		rs.close();
		c.close();
		return p;
	}
	public List<Produto> Listar() throws ClassNotFoundException, SQLException {
		Connection c = gdao.getC();
		String sql ="SELECT CODIGO, NOME, VALOR_UNITARIO, QTD_ESTOQUE  FROM PRODUTO";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Produto> lp = new ArrayList<>();
		while(rs.next()) {
			Produto p1 = new Produto();
			p1.setCodigo(rs.getInt("CODIGO"));
			p1.setNome(rs.getString("NOME"));
			p1.setQtd(rs.getInt("QTD_ESTOQUE"));
			p1.setVu(rs.getDouble("VALOR_UNITARIO"));
			lp.add(p1);
		}
		rs.close();
		c.close();
		return lp;
	}
	public int getNum(int val) throws SQLException, ClassNotFoundException {
		Connection c = gdao.getC();
		String sql="{call P_SCL (?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1,val);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		int res = cs.getInt(2);
		c.close();
		return res;
	}
	public List<Produto> getProduto(int val) throws ClassNotFoundException, SQLException {
		Connection c = gdao.getC();
		String sql ="SELECT CODIGO, NOME, QTD FROM dbo.FC (?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, val);
		ResultSet rs = ps.executeQuery();
		List<Produto> lp = new ArrayList<>();
		while(rs.next()) {
			Produto p1 = new Produto();
			p1.setCodigo(rs.getInt("CODIGO"));
			p1.setNome(rs.getString("NOME"));
			p1.setQtd(rs.getInt("QTD"));
			lp.add(p1);
		}
		rs.close();
		c.close();
		return lp;
	}
	
}
