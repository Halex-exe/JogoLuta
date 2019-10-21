package Server;

public class Servidor {

    public static void main(String[] args) {
        MultiJogador multi = new MultiJogador();
        multi.configurarServidor();
        multi.aguardarClientes();
    }
}
