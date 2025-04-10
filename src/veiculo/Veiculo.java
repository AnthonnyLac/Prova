package veiculo;

public class Veiculo {
    private int id;
    private String cor;
    private String tipo;
    private int idEstacao;
    private int idFuncionario;
    private int posicaoEsteira;
    public Veiculo(int id, String cor, String tipo, int idEstacao, int idFuncionario, int posicaoEsteira) {
        this.id = id;
        this.cor = cor;
        this.tipo = tipo;
        this.idEstacao = idEstacao;
        this.idFuncionario = idFuncionario;
        this.posicaoEsteira = posicaoEsteira;
    }
    public int getId() {
        return id;
    }
    public String getCor() {
        return cor;
    }
    public String getTipo() {
        return tipo;
    }
    public int getIdEstacao() {
        return idEstacao;
    }
    public int getIdFuncionario() {
        return idFuncionario;
    }
    public int getPosicaoEsteira() {
        return posicaoEsteira;
    }
    public String toString() {
        return "Veiculo[id=" + id + ", cor=" + cor + ", tipo=" + tipo + ", estacao=" + idEstacao + ", funcionario=" + idFuncionario + ", pos=" + posicaoEsteira + "]";
    }
}