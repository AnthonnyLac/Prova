import cliente.Cliente;
import fabrica.Fabrica;
import log.Log;
import loja.Loja;
import veiculo.Veiculo;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fabrica fabrica = new Fabrica();
        fabrica.IniciarEstacoes();


        List<Loja> lojas = List.of(
                new Loja("Loja 1"),
                new Loja("Loja 2"),
                new Loja("Loja 3")
        );
        
        ExecutorService servicoLoja = Executors.newFixedThreadPool(3);

        for (Loja loja : lojas) {
            servicoLoja.submit(() -> {
                while (true) {
                    try {
                        Veiculo v = fabrica.obterVeiculo();
                        int pos = loja.receberVeiculo(v);
                        Log.registroVendaFabrica(v, loja.getNome(), pos);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ExecutorService servicoCliente = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            servicoCliente.submit(new Cliente(i + 1, lojas));
        }

    }
}