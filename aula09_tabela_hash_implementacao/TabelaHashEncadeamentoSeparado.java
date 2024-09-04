package aula09_tabela_hash_implementacao;

import java.util.Objects;

public class TabelaHashEncadeamentoSeparado {
    private final int CAPACIDADE_INICIAL = 5;
    private final double FATOR_DE_CARGA = 0.7;
    private int capacidade;
    private int tamanho;
    private Nodo[] tabela;

    public TabelaHashEncadeamentoSeparado() {
        capacidade = CAPACIDADE_INICIAL;
        tabela = new Nodo[capacidade];
    }

    private int funcaoHash(int chave) {
        return chave % capacidade;
    }

    public void adicionar(int chave, String valor) {
        //IMPLEMENTAR SOLUCAO PARA QUANDO O TAMANHO EXCEDER O FATOR DE CARGA * CAPACIDADE
        if(tamanho>=capacidade*FATOR_DE_CARGA) {
            duplicarTabelaHash();
        }

        Nodo n = new Nodo(chave, valor);
        int posicao = funcaoHash(n.getChave());

        if(tabela[posicao]==null) {
            tabela[posicao] = n;
        }
        else {
            n.setProximo(tabela[posicao]);
            tabela[posicao] = n;
        }
        tamanho++;
    }

    private void duplicarTabelaHash() {
        int novaCapacidade = capacidade*2+1;
        Nodo[] aux = tabela;
        tamanho = 0;
        capacidade = novaCapacidade;
        tabela = new Nodo[capacidade];
        for (int i = 0; i < aux.length; i++) {
            Nodo n = aux[i];
            while(Objects.nonNull(n)) {
                adicionar(n.getChave(), n.getValor());
                n = n.getProximo();
            }
        }
    }

    public String buscar(int chave) {
        for (int i = 0; i < capacidade; i++) {
            Nodo n = tabela[i];
            if (Objects.isNull(n)) {
                continue;
            }
            if (n.getChave() == chave) {
                return n.toString();
            }
            while(Objects.nonNull(n)) {
                if (n.getChave() == chave) {
                    return n.toString();
                }
                n = n.getProximo();
            }
        }
        return "Nao ha nodo com essa chave.";
    }
    public void remover(int chave) {
        for (int i = 0; i < capacidade; i++) {
            Nodo n = tabela[i];
            if (Objects.isNull(n)) {
                continue;
            }
            if (n.getChave() == chave) {
                if (Objects.nonNull(n.getProximo())) {
                    tabela[i] = n.getProximo();
                } else {
                    tabela[i] = null;
                }
                return;
            }
            while(Objects.nonNull(n.getProximo())) {
                if (n.getProximo().getChave() == chave) {
                    n.setProximo(n.getProximo().getProximo());
                    return;
                }
                n = n.getProximo();
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacidade; i++) {
            Nodo n = tabela[i];
            sb.append(System.lineSeparator()).append(i);
            while(n!=null) {
                sb.append(" -> ").append(n);
                n = n.getProximo();
            }
        }
        return sb.toString();
    }
}
