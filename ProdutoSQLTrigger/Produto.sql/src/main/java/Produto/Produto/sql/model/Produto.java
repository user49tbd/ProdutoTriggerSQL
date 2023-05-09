package Produto.Produto.sql.model;

public class Produto {
	private int codigo;
	private String nome;
	private double vu;
	private int qtd;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getVu() {
		return vu;
	}
	public void setVu(double vu) {
		this.vu = vu;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", vu=" + vu + ", qtd=" + qtd + "]";
	}
}
