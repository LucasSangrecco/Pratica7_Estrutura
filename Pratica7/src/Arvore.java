import java.util.Stack;

public class Arvore {
    private No raiz;

    public Arvore() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }


    private No inserirRecursivo(No node, int valor) {
        if (node == null) {
            return new No(valor);
        }
        if (valor < node.valor) {
            node.esquerda = inserirRecursivo(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = inserirRecursivo(node.direita, valor);
        } else {
            return node;
        }

        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));

        int balance = getBalance(node);

        if (balance > 1 && valor < node.esquerda.valor) {
            return rotacaoDireita(node);
        }
        if (balance < -1 && valor > node.direita.valor) {
            return rotacaoEsquerda(node);
        }
        if (balance > 1 && valor > node.esquerda.valor) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }
        if (balance < -1 && valor < node.direita.valor) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }
        int altura = getBalance(node);
        return node;
    }


    public boolean pesquisar(int valor) {
        return pesquisarRecursivo(raiz, valor);
    }

    private boolean pesquisarRecursivo(No node, int valor) {
        if (node == null) {
            return false;
        }

        if (valor == node.valor) {
            return true;
        }

        if (valor < node.valor) {
            return pesquisarRecursivo(node.esquerda, valor);
        }

        return pesquisarRecursivo(node.direita, valor);
    }

    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(No node, int nivel) {
        if (node != null) {
            imprimirRecursivo(node.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(node.valor);
            imprimirRecursivo(node.esquerda, nivel + 1);
        }
    }

    public void excluir(int elemento) {
        raiz = excluirRecursivo(raiz, elemento);
    }

    private No excluirRecursivo(No node, int elemento){
        if (node == null) {
            return node;
        }
        if (elemento < node.valor) {
            node.esquerda = excluirRecursivo(node.esquerda, elemento);
        } else if (elemento > node.valor){
            node.direita = excluirRecursivo(node.direita, elemento);
        } else {
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return  node.esquerda;
            }
            node.valor = minValue(node.direita);
            node.direita = excluirRecursivo(node.direita, node.valor);
        }
        return node;
    }

    public int buscarMenorValor() {
        return minValue(raiz);
    }
    private int minValue(No node){
        int minValue = node.valor;
        while (node.esquerda != null) {
            minValue = node.esquerda.valor;
            node = node.esquerda;
        }
        return minValue;
    }
    public int buscarMaiorValor() {
        return maxValue(raiz);
    }

    private int maxValue(No node) {
        int maxValue = node.valor;
        while (node.direita != null) {
            maxValue = node.direita.valor;
            node = node.direita;
        }
        return maxValue;
    }

    public void inOrdem() {
        inOrdemRecursivo(raiz);
    }

    private void inOrdemRecursivo(No node) {
        if (node != null) {
            inOrdemRecursivo(node.esquerda);
            System.out.print(node.valor + " ");
            inOrdemRecursivo(node.direita);
        }
    }

    public void preOrdem(){
        preOrdemRecursivo(raiz);
    }

    private void preOrdemRecursivo(No node) {
        if (node == null) {
            return;
        }

        Stack<No> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            No current = stack.pop();
            System.out.print(current.valor + " ");

            if (current.direita != null) {
                stack.push(current.direita);
            }
            if (current.esquerda != null) {
                stack.push(current.esquerda);
            }
        }
    }

    public void posOrdem() {
        posOrdemRecursivo(raiz);
    }
    private void posOrdemRecursivo(No node) {
        if (node != null){
            posOrdemRecursivo(node.esquerda);
            posOrdemRecursivo(node.direita);
            System.out.print(node.valor + " ");
        }
    }

    private int altura(No node) {
        if (node == null) {
            return 0;
        }
        return node.altura;
    }

    public int getAltura() {
        return altura(raiz);
    }
    private int getBalance(No node) {
        if (node == null) {
            return 0;
        }
        return altura(node.esquerda) - altura(node.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));
        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));

        return x;
    }
    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));
        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));

        return y;
    }

    public boolean isIdenticas(Arvore outraArvore) {
        return isIdenticasRecursivo(this.raiz, outraArvore.raiz);
    }
    private boolean isIdenticasRecursivo(No node1, No node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 != null && node2 != null) {
            return (node1.valor == node2.valor) &&
                    isIdenticasRecursivo(node1.esquerda, node2.esquerda) &&
                    isIdenticasRecursivo(node1.direita, node2.direita);
        }
        return false;
    }

}
