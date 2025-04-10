package cliente;

import log.Log;
import loja.Loja;
import veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente implements Runnable {
    private int idCliente;
    private List<Loja> lojas;
    private Random rand = new Random();
    private List<Veiculo> garagem = new ArrayList<>();

    public Cliente(int idCliente, List<Loja> lojas) {
        this.idCliente = idCliente;
        this.lojas = lojas;
    }

    public void run() {
        while (true) {
            try {
                Loja loja = lojas.get(rand.nextInt(lojas.size()));
                Veiculo v = loja.venderParaCliente(idCliente);

                int posicaoGaragem = garagem.size();
                garagem.add(v);


                Log.registroGaragemCliente(v, idCliente, posicaoGaragem);
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}