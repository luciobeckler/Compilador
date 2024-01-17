public class Token {
  private String nome; // id, palavraReservada, numeroInteiro, numeroDecimal e simbolo
  private String valor;
  private int contexto;
  private String tipoVariavel;
  private int linha; // Adicionar a linha para indicar ao usuário onde se encontra o erro

  // Construtor
  public Token(String nome, String valor, int contexto) { // pode receber uma string
    this.nome = nome;
    this.valor = valor;
    this.contexto = contexto;
  }

  public String getTipoVariavel() {
    return tipoVariavel;
  }

  public void setTipoVariavel(String tipoVariavel) {
    this.tipoVariavel = tipoVariavel;
  }

  public Token(String nome, String valor) { // pode receber uma string
    this.nome = nome;
    this.valor = valor;
    this.contexto = -1;
  }

  public Token(String nome, String valor, int contexto, String tipoVariavel) {
    this.nome = nome;
    this.valor = valor;
    this.contexto = contexto;
    this.tipoVariavel = tipoVariavel;
  }

  public int getContexto() {
    return contexto;
  }

  public void setContexto(int contexto) {
    this.contexto = contexto;
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