package loja;

import log.Log;
import veiculo.Veiculo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Loja {
    private String nome;
    private BlockingQueue<Veiculo> esteiraLoja = new ArrayBlockingQueue<>(40);
    private AtomicInteger contadorPosLoja = new AtomicInteger(0);

    public Loja(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public int receberVeiculo(Veiculo v) throws InterruptedException {

        esteiraLoja.put(v);
        int pos = contadorPosLoja.getAndIncrement() % 40;
        Log.registroRecebimentoLoja(v, nome, pos);

        return pos;
    }
    public Veiculo venderParaCliente(int idCliente) throws InterruptedException {
        Veiculo v = esteiraLoja.take();
        Log.registroVendaLoja(v, nome, idCliente);
        return v;
    }
}