import com.sun.security.ntlm.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Livraria {

    List<Cliente> clientes = new ArrayList<>();
    List<Livro> livrosNaoAlugados = new ArrayList<>();

    Scanner scan = new Scanner(System.in);

    public void criarListaDeClientes() {
        clientes.add(new Cliente("Daniele", "123"));
        clientes.add(new Cliente("Felipe", "124"));
        clientes.add(new Cliente("Julia", "125"));
    }

    public void criarListDeLivros() {
        livrosNaoAlugados.add(new Livro("A Culpa É das Estrelas", 1, "John Green"));
        livrosNaoAlugados.add(new Livro("Chapeuzinho Vermelho", 2, "Charles Perrault"));
        livrosNaoAlugados.add(new Livro("Harry Potter", 3, "J. K. Rowling"));
        livrosNaoAlugados.add(new Livro("O Pequeno Príncipe", 4, "Antoine de Saint-Exupéry"));
        livrosNaoAlugados.add(new Livro("Jogos Vorazes", 5, "Suzanne Collins"));
    }

    public Cliente buscaCliente(String matricula) {
        for (Cliente cliente : clientes) {
            if (cliente.matricula == matricula) {
                return cliente;
            }
        }
        return null;
    }

    public Livro buscaLivro(int matricula) {
        for (Livro l : livrosNaoAlugados) {
            if (l.matricula == matricula) {
                return l;
            }
        }
        return null;
    }

    public Livro buscaClientePelaMatriculaDoLivro(String matriculaDoCliente, int matriculaLivro) {
        Cliente clientesDevolvendoLivro = buscaCliente(matriculaDoCliente);

        for (Livro l : clientesDevolvendoLivro.livros) {
            if (l.matricula == matriculaLivro) {
                return l;
            }
        }
        clientesDevolvendoLivro.devolverLivro(matriculaLivro);
        return null;
    }

    public void validaClienteParaAlugar() {
        System.out.println("Entre com a matricula do cliente");
        String matriculaDoCliente = scan.next();

        Cliente client = buscaCliente(matriculaDoCliente);

        if (client != null) {
            validaLivro(client);
        } else {
            Cliente novoCliente = adicionaNovoCliente();
            validaLivro(novoCliente);
        }
    }

    public void validaLivro(Cliente cliente) {
        System.out.println("Entre com a matricula do livro a ser alugado");
        int matriculaDoLivro = scan.nextInt();

        Livro l = buscaLivro(matriculaDoLivro);

        if (l != null) {
            cliente.adicionarNovoLivro(l);
            this.livrosNaoAlugados.remove(l);
        } else {
            System.out.println("Livro não existe");
        }
    }

    public void devolverLivro() {
        System.out.println("Entre com a matricula do cliente");
        String matriculaDoCliente = scan.next();

        System.out.println("Entre com a matricula do livro a ser devolvido: ");
        int matriculaDoLivro = scan.nextInt();

        Livro livro = buscaClientePelaMatriculaDoLivro(matriculaDoCliente, matriculaDoLivro);
        this.livrosNaoAlugados.add(livro);
    }

    public Cliente adicionaNovoCliente() {
        System.out.println("Deseja incluir um novo cliente? S - 1 / N - 2");
        int aceite = scan.nextInt();

        if (aceite == 1) {
            System.out.println("Entre com o nome: ");
            String nome = scan.next();
            return new Cliente(nome, geradaNovaMatricula());
        } else {
            scan.close();
            Runtime.getRuntime().exit(1);
            return null;
        }
    }

    public String geradaNovaMatricula() {
        return UUID.randomUUID().toString();
    }

    public void listarTodosnaoAlugados() {
        for (Livro l : livrosNaoAlugados) {
            System.out.println("----------------------------------------");
            System.out.println("matricula: " + l.matricula);
            System.out.println("nome: " + l.nome);
            System.out.println("autor: " + l.autor);
            System.out.println("----------------------------------------");
        }
    }

}

//crie um metodo para ordenar os livros por nome
//crie um metodo para lista os livros alugados
//Um cliente não pode estar com mais de 2 livros
// não pode haver dois alugueis