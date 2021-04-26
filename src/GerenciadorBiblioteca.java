import java.util.Scanner;

public class GerenciadorBiblioteca {

    public static void main(String[] args) {

        Livraria livraria = new Livraria();

        livraria.criarListaDeClientes();
        livraria.criarListDeLivros();

        Scanner scan = new Scanner(System.in);

        System.out.println("Olá bem vindo a livraria da Daniele");
        System.out.println("Voce deseja (1)Alugar - (2)Devolver livro (3)Trazer todos não alugados");

        Integer escolha = scan.nextInt();

        if (escolha.equals(1)) {
            livraria.validaClienteParaAlugar();
        } else if (escolha.equals(2)) {
            livraria.devolverLivro();
        } else if (escolha.equals(3)) {
            livraria.listarTodosnaoAlugados();
        } else {
            System.out.println("Opção não disponível");
        }
    }
}
