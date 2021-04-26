import java.time.LocalDateTime;

public class Cliente {

    public String nome;
    public String matricula;
    public Livro[] livros = new Livro[2];

    public Cliente() {
    }

    public Cliente(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public void adicionarNovoLivro(Livro livros) {
        if (this.livros[0] == null) {
            this.livros[0] = livros;
            this.livros[0].dataLocacao = LocalDateTime.now();
        } else {
            System.out.println("O cliente deve devolver um livro");
        }
    }

    public void devolverLivro(int matricula) {
        if (this.livros[0].matricula == matricula) {
            validarDataDevolucao(this.livros[0]);
            this.livros[0] = null;
        } else if (this.livros[1].matricula == matricula) {
            validarDataDevolucao(this.livros[1]);
            this.livros[1] = null;
        } else {
            System.out.println("Este livro não foi alugado pelo cliente");
        }
    }

    public void validarDataDevolucao(Livro livros) {
        LocalDateTime devolucao = livros.dataLocacao.plusDays(2);
        if (devolucao.isBefore(LocalDateTime.now())) {
            System.out.println("Devolução sem multa");
        } else {
            System.out.println("Devolução com multa");
        }
    }
}
