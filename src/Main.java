import cliente.Cliente;
import fabrica.EstacaoProducao;
import fabrica.Fabrica;
import log.Log;
import loja.Loja;
import veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fabrica fabrica = new Fabrica();
        EstacaoProducao[] estacoes = new EstacaoProducao[4];

        for (int i = 0; i < 4; i++) {
            estacoes[i] = new EstacaoProducao(i + 1, fabrica);
            estacoes[i].iniciarFuncionarios();
        }

        Loja loja1 = new Loja("Loja 1");
        Loja loja2 = new Loja("Loja 2");
        List<Loja> lojas = new ArrayList<>();
        lojas.add(loja1);
        lojas.add(loja2);
        ExecutorService servicoLoja = Executors.newFixedThreadPool(2);

        for (Loja loja : lojas) {
            servicoLoja.submit(() -> {
                while (true) {
                    try {
                        Veiculo v = fabrica.obterVeiculo();
                        int pos = loja.receberVeiculo(v);
                        Log.registroVendaFabrica(v, loja.getNome(), pos);
                    } catch (InterruptedException e) {}
                }
            });
        }
        ExecutorService servicoCliente = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            servicoCliente.submit(new Cliente(i + 1, lojas));
        }
    }
}