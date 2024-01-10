public class Token {
  private String nome; // id, palavraReservada, numero e simbolo
  private String valor;
  private int linha; // Adicionar a linha para indicar ao usuário onde se encontra o erro

  // Construtor
  public Token(String nomeValor[]) { // pode receber uma string
    this.nome = nomeValor[0];
    this.valor = nomeValor[1];
  }

  public Token(String string, String string2) {
  }

  // Métodos setters e getters
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

}