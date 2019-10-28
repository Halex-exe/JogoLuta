package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jogador implements Runnable {

    public int x = 0, y = 0, soco = 0;
    public int vida = 100;

    public String msg;
    public String dados;

    Socket s;
    Thread thRecebeMsg;
    BufferedReader entrada;
    PrintWriter saida;
    int speed = 15;

    public Jogador(Socket s) {
        configurarJogador(s);
    }

    public Jogador(Socket s, int x) {
        configurarJogador(s, x);
    }


    public void configurarJogador(Socket s) {
        this.s = s;
        try {
            entrada = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(s.getOutputStream());
            thRecebeMsg = new Thread(this);
            thRecebeMsg.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void configurarJogador(Socket s, int x) {
        this.s = s;
        try {
            entrada = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(s.getOutputStream());
            this.x = x;
            thRecebeMsg = new Thread(this);
            thRecebeMsg.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGame() {

        if (this.msg != null) {

            if (this.msg.equals("r")) {
                if (this.x < 705) {
                    this.x += speed;
                    this.msg = null;
                }
            } else {

                if (this.msg.equals("l")) {
                    if (this.x > 0) {
                        this.x -= speed;
                        this.msg = null;
                    }
                } else {

                    if (this.msg.equals("u")) {
                        if (this.y > 0) {
                            this.y -= speed;
                            this.msg = null;
                        }
                    } else {

                        if (this.msg.equals("d")) {
                            if (this.y <= 445) {
                                this.y += speed;
                                this.msg = null;
                            }
                        } else {
                            if (msg.equals("s")) {
                                //Jogador.setIconSpace(); //troca para soco.
                                this.soco = 1;
                                this.msg = null;
                            }
                        }
                    }
                }
            }

        }
        this.atualizadClientes(); // enviar para o cliente as novas coordenadas
    }

    public void enviarMensagem() {
        //dados = x + ":" + y + ":" + vida;
        if (MultiJogador.jogador.size() >= 2) {
            Jogador jogador1 = MultiJogador.jogador.get(0);
            if (jogador1.soco == 1) {
                dados = jogador1.x + ":" + jogador1.y + ":" + jogador1.vida + ":" + jogador1.soco;
                soco = 0;
            } else {
                dados = jogador1.x + ":" + jogador1.y + ":" + jogador1.vida + ":" + jogador1.soco;
            }
            String dados2;

            Jogador jogador2 = MultiJogador.jogador.get(1);
            if (jogador2.soco == 1) {
                dados2 = jogador2.x + ":" + jogador2.y + ":" + jogador2.vida + ":" + jogador2.soco;
                soco = 0;
            } else {
                dados2 = jogador2.x + ":" + jogador2.y + ":" + jogador2.vida + ":" + jogador2.soco;
            }

            dados = dados + "-" + dados2;
        } else { // 1 Player
            Jogador jogador = MultiJogador.jogador.get(0);
            if (soco == 1) {
                dados = jogador.x + ":" + jogador.y + ":" + jogador.vida + ":" + jogador.soco;
                soco = 0;
            } else {
                dados = jogador.x + ":" + jogador.y + ":" + jogador.vida + ":" + jogador.soco;
            }
        }
        //System.out.println(dados);
        saida.println(dados);
        saida.flush();

    }

    public void atualizadClientes() {
        for (Jogador cliente : MultiJogador.jogador) {
            cliente.enviarMensagem();
        }
    }

    public void receberMensagens() {
        new Thread(() -> {
            try {
                String msg;
                while ((msg = entrada.readLine()) != null) {
                    System.out.println(msg);
                    this.msg = msg;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    @Override
    public void run() {
        receberMensagens();
        while (true) {
            try {
                updateGame();
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
