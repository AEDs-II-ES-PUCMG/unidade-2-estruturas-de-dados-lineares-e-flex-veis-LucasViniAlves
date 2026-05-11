import java.util.NoSuchElementException;

public class Fila<E> {

    private Celula<E> frente;
    private Celula<E> tras;

    public Fila() {
        Celula<E> sentinela = new Celula<E>();
        frente = sentinela;
        tras = sentinela;
    }

    public boolean vazia() {
        return frente == tras;
    }

    public void enfileirar(E item) {
        Celula<E> nova = new Celula<E>(item);

        tras.setProximo(nova);
        tras = nova;
    }

    public E desenfileirar() {
        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na fila!");
        }

        Celula<E> primeira = frente.getProximo();
        E item = primeira.getItem();

        frente.setProximo(primeira.getProximo());

        if (primeira == tras) {
            tras = frente;
        }

        return item;
    }

    public E consultarPrimeiro() {
        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na fila!");
        }

        return frente.getProximo().getItem();
    }

    public int contarOcorrencias(E itemProcurado) {
        int contador = 0;
        Celula<E> atual = frente.getProximo();

        while (atual != null) {
            if (itemProcurado == null) {
                if (atual.getItem() == null) {
                    contador++;
                }
            } else if (itemProcurado.equals(atual.getItem())) {
                contador++;
            }

            atual = atual.getProximo();
        }

        return contador;
    }

    public Fila<E> extrairLote(int numItens) {
        if (numItens < 0) {
            throw new IllegalArgumentException("A quantidade de itens não pode ser negativa.");
        }

        Fila<E> lote = new Fila<E>();
        int contador = 0;

        while (!vazia() && contador < numItens) {
            lote.enfileirar(desenfileirar());
            contador++;
        }

        return lote;
    }

    @Override
    public String toString() {
        if (vazia()) {
            return "Fila vazia.";
        }

        StringBuilder sb = new StringBuilder();
        Celula<E> atual = frente.getProximo();

        while (atual != null) {
            sb.append(atual.getItem()).append("\n");
            atual = atual.getProximo();
        }

        return sb.toString();
    }
}