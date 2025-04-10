package fabrica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class EstacaoProducao {
    private int idEstacao;
    private Fabrica fabrica;
    private Semaphore[] ferramentas = new Semaphore[5];
    private ExecutorService poolFuncionarios = Executors.newFixedThreadPool(5);
    public EstacaoProducao(int idEstacao, Fabrica fabrica) {
        this.idEstacao = idEstacao;
        this.fabrica = fabrica;
        for (int i = 0; i < 5; i++) {
            ferramentas[i] = new Semaphore(1, true);
        }
    }
    public void iniciarFuncionarios() {
        for (int i = 0; i < 5; i++) {
            int idFuncionario = i + 1;
            poolFuncionarios.submit(new Funcionario(idEstacao, idFuncionario, fabrica, ferramentas, i));
        }
    }
}