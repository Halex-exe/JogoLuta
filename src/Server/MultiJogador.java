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

    public void aguardarClientes() { //pegar o codigo do conectado. e instanciar na tela
        try {
            while (true) {
                System.out.println("Servidor Iniciado!");
                socketNovoJogador = ss.accept();
                System.out.println("Cliente conectado: " + ss.toString());
                Jogador novoCliente = new Jogador(socketNovoJogador);
                jogador.add(novoCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
