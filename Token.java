public class Token {
  private String nome; // id, palavraReservada, numero e simbolo
  private char valor;

  // Construtor
  public Token(String nome, char valor) {
    this.nome = nome;
    this.valor = valor;
  }

  // Métodos setters e getters
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public char getValor() {
    return valor;
  }

  public void setValor(char valor) {
    this.valor = valor;
  }

}