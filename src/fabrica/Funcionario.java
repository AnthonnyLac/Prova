package fabrica;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Funcionario implements Runnable {
    private int idEstacao;
    private int idFuncionario;
    private Fabrica fabrica;
    private Semaphore[] ferramentas;
    private int indice;
    private Random rand = new Random();
    public Funcionario(int idEstacao, int idFuncionario, Fabrica fabrica, Semaphore[] ferramentas, int indice) {
        this.idEstacao = idEstacao;
        this.idFuncionario = idFuncionario;
        this.fabrica = fabrica;
        this.ferramentas = ferramentas;
        this.indice = indice;
    }
    public void run() {
        while (true) {
            try {
                if (idFuncionario % 2 == 0) {
                    ferramentas[(indice + 1) % 5].acquire();
                    ferramentas[indice].acquire();
                } else {
                    ferramentas[indice].acquire();
                    ferramentas[(indice + 1) % 5].acquire();
                }
                fabrica.produzirVeiculo(idEstacao, idFuncionario);
                ferramentas[indice].release();
                ferramentas[(indice + 1) % 5].release();

                System.out.println("[" + Thread.currentThread().getName() + " - " + System.currentTimeMillis() +
                        "] Funcionário " + idFuncionario + " da estação " + idEstacao + " iniciou uma produção.");

                Thread.sleep(5000);
            } catch (InterruptedException e) {}
        }
    }
}