package fabrica;

import log.Log;
import veiculo.Veiculo;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Fabrica {
    private int estacaoProducaoNum = 4;
    private AtomicInteger pecasEstoque = new AtomicInteger(500);
    private Semaphore esteiraMontagem = new Semaphore(5);
    private BlockingQueue<Veiculo> esteiraProducao = new ArrayBlockingQueue<>(40);
    private AtomicInteger contadorPosEsteira = new AtomicInteger(0);
    private AtomicInteger contadorVeiculos = new AtomicInteger(0);
    private EstacaoProducao [] estacoes = new EstacaoProducao[estacaoProducaoNum];

    public void adquirirPecas() throws InterruptedException {
        while (true) {
            int atual = pecasEstoque.get();
            if (atual <= 0) {
                throw new InterruptedException("Sem peças");
            }

            if (pecasEstoque.compareAndSet(atual, atual - 1)) {
                break;
            }
        }
    }

    public void colocarNaEsteira(Veiculo v) throws InterruptedException {
        esteiraProducao.put(v);
    }

    public Veiculo retirarDaEsteira() throws InterruptedException {
        return esteiraProducao.take();
    }

    public void  IniciarEstacoes(){

        for (int i = 0; i < estacaoProducaoNum; i++) {
            estacoes[i] = new EstacaoProducao(i + 1, this);
            estacoes[i].iniciarFuncionarios();
        }

        for (EstacaoProducao estacao : estacoes) {
            estacao.iniciarFuncionarios();
        }
    }

    public Veiculo produzirVeiculo(int idEstacao, int idFuncionario) throws InterruptedException {
        adquirirPecas();

        int pos = contadorPosEsteira.getAndIncrement() % 40;
        int id = contadorVeiculos.incrementAndGet();
        String cor = (id % 3 == 1) ? "Vermelho" : (id % 3 == 2) ? "Verde" : "Azul";
        String tipo = (id % 2 == 0) ? "Sedan" : "SUV";
        Veiculo v = new Veiculo(id, cor, tipo, idEstacao, idFuncionario, pos);

        colocarNaEsteira(v);

        Log.registroProducaoFabrica(v);

        return v;
    }
    public Veiculo obterVeiculo() throws InterruptedException {
        return retirarDaEsteira();
    }
}