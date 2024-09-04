package aula09_tabela_hash_implementacao;

public class App {
    public static void main(String[] args) {
        //teste usando encadeamento separado
        TabelaHashEncadeamentoSeparado t1 = new TabelaHashEncadeamentoSeparado();
        t1.adicionar(499, "leonardo");
        t1.adicionar(567, "joao");
        t1.adicionar(400, "maria");
        t1.adicionar(300, "jose");
        t1.adicionar(871, "gustavo");
        t1.adicionar(956, "arthur");
        t1.adicionar(568, "gabriela");
        t1.adicionar(911, "salete");

        System.out.println(t1);

        System.out.println(t1.buscar(956));
        System.out.println(t1.buscar(200));
        System.out.println(t1.buscar(568));

        t1.remover(956);
        System.out.println(t1);
        t1.remover(300);
        System.out.println(t1);
        t1.remover(499);
        System.out.println(t1);
    }
}
