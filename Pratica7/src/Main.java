import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Arvore arvore = new Arvore();
        Scanner ler = new Scanner(System.in);

        arvore.inserir(7);
        arvore.inserir(8);
        arvore.inserir(2);
        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(10);
        arvore.inserir(4);
        arvore.inserir(11);
        arvore.inserir(12);

        System.out.println("Arvore AVL Balanceada:");
        arvore.imprimir();

        System.out.println("O menor elemento da arvore: "+ arvore.buscarMenorValor());

        System.out.println("O menor elemento da arvore: "+ arvore.buscarMaiorValor());

        Arvore arvore2 = new Arvore();

        arvore2.inserir(7);
        arvore2.inserir(8);
        arvore2.inserir(2);
        arvore2.inserir(5);
        arvore2.inserir(3);
        arvore2.inserir(10);
        arvore2.inserir(4);
        arvore2.inserir(11);
        arvore2.inserir(12);

        System.out.println("Arvore 2 AVL Balanceada:");
        arvore2.imprimir();

        if(arvore.isIdenticas(arvore2)){
            System.out.println("As arvores são identicas");
        }else{
            System.out.println("As arvores não são identicas");
        }

        System.out.println("Altura da arvore: " + arvore.getAltura());
    }
}
