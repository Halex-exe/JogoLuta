package Server;

import java.io.Console;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiJogador {

    static List<Jogador> jogador = new ArrayList<>();
    ServerSocket ss;
    Socket socketNovoJogador;

    public void configurarServidor() {
        try {
            ss = new ServerSocket(8020);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aguardarClientes() {
        try {
            while (true) {
                System.out.println("Started");
                socketNovoJogador = ss.accept();
                Jogador novoCliente = new Jogador(socketNovoJogador);
                jogador.add(novoCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
