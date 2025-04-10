package log;

import veiculo.Veiculo;

public class Log {
    public static synchronized void registroProducaoFabrica(Veiculo v) {
        System.out.println("Log Producao Fabrica: " + v);
    }
    public static synchronized void registroVendaFabrica(Veiculo v, String loja, int posLoja) {
        System.out.println("Log Venda Fabrica: " + v + " -> " + loja + " (pos=" + posLoja + ")");
    }
    public static synchronized void registroRecebimentoLoja(Veiculo v, String loja, int posLoja) {
        System.out.println("Log Recebimento Loja: " + v + " -> " + loja + " (pos=" + posLoja + ")");
    }
    public static synchronized void registroVendaLoja(Veiculo v, String loja, int cliente) {
        System.out.println("Log Venda Loja: " + v + " -> Cliente " + cliente + " (" + loja + ")");
    }
    public static synchronized void registroGaragemCliente(Veiculo v, int cliente, int posGaragem) {
        System.out.println("Log Garagem Cliente " + cliente + ": " + v + " (pos=" + posGaragem + ")");
    }
}