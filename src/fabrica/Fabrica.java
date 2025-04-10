package fabrica;

import log.Log;
import veiculo.Veiculo;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Fabrica {
    private Semaphore pecasEstoque = new Semaphore(500);
    private Semaphore esteiraMontagem = new Semaphore(5);
    private BlockingQueue<Veiculo> esteiraProducao = new ArrayBlockingQueue<>(40);
    private AtomicInteger contadorPosEsteira = new AtomicInteger(0);
    private AtomicInteger contadorVeiculos = new AtomicInteger(0);
    public void adquirirPecas() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            esteiraMontagem.acquire();
            pecasEstoque.acquire();
            esteiraMontagem.release();
        }
    }
    public void colocarNaEsteira(Veiculo v) throws InterruptedException {
        esteiraProducao.put(v);
    }
    public Veiculo retirarDaEsteira() throws InterruptedException {
        return esteiraProducao.take();
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