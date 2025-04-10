package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import veiculo.Veiculo;

public class Log {

    private static synchronized void writeLog(String file, String content) {
        try {
            FileWriter arquivoLog = new FileWriter(file, true);
            BufferedWriter escritorLog = new BufferedWriter(arquivoLog);
            escritorLog.append(content  + '\n');
            escritorLog.flush();
            escritorLog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static synchronized void registroProducaoFabrica(Veiculo v) {
        String content = "Log Producao Fabrica: " + v;
        System.out.println(content);
        writeLog("fabricaLog.txt", content);
    }

    public static synchronized void registroVendaFabrica(Veiculo v, String loja, int posLoja) {
        String content = "Log Venda Fabrica: " + v + " -> " + loja + " (pos=" + posLoja + ")";
        System.out.println(content);
        writeLog("fabricaLog.txt", content);
    }

    public static synchronized void registroRecebimentoLoja(Veiculo v, String loja, int posLoja) {
        String content = "Log Recebimento Loja: " + v + " -> " + loja + " (pos=" + posLoja + ")";
        System.out.println(content);
        writeLog("lojaLog.txt", content);
    }
    public static synchronized void registroVendaLoja(Veiculo v, String loja, int cliente) {
        String content = "Log Venda Loja: " + v + " -> Cliente " + cliente + " (" + loja + ")";
        System.out.println(content);
        writeLog("lojaLog.txt", content);
    }

    public static synchronized void registroGaragemCliente(Veiculo v, int cliente, int posGaragem) {
        String content = "Log Garagem Cliente " + cliente + ": " + v + " (pos=" + posGaragem + ")";
        System.out.println(content);
        writeLog("clienteLog.txt", content);
    }


}