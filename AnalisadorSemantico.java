import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AnalisadorSemantico {
  private ArrayList<Token> listaToken;
  private int contexto = 0;

  public AnalisadorSemantico(ArrayList<Token> listaToken) {
    this.listaToken = listaToken;

  }

  public void ExibeTokens() {
    listaToken.forEach(item -> {
      System.err.println("<" + item.getNome() + ">" + "<" + item.getValor() + ">");
    });
  }

  public void AnaliseSemantica(ArrayList<Token> listaTokens) throws ErrosCompilador {
    System.err.println("Passei por aqui");
    TabelaDeSimbolos.InicializaPalavrasReservadas();
    TabelaDeSimbolos tabelaSimbolos;
    tabelaSimbolos = TabelaDeSimbolos.gTabelaDeSimbolos();

    // !Lembrar que no token final não haverá i+1
    for (int i = 0; i < listaTokens.size() - 1; i++) { // !Cadastrar o contexto ao cadastrar uma variável, caso ela já
                                                       // exista, teste o contexto dela
      if (Arrays.asList("int", "bool", "float").contains(listaTokens.get(i).getValor())) {
        verificaDeclaracaoDuplaDeVariavel(listaTokens, tabelaSimbolos, i);
      } else if (listaTokens.get(i).getNome() == "id" && listaTokens.get(i + 1).getValor() == "="
          && listaTokens.get(i + 2).getValor() != "=") { // !Avaliar o mesmo problema do começo do for
        verificaUsoAntesDaDeclaracao(listaTokens, tabelaSimbolos, i);
        verificaTiposDasOperacoes(listaTokens, tabelaSimbolos, i);
      } else if (Arrays.asList("if", "while").contains(listaTokens.get(i).getValor())) {
        verificaCondicionalBooleano(listaTokens, tabelaSimbolos, i);
      } else if (listaTokens.get(i).getValor().equals("{")) {
        contexto++;
      } else if (listaTokens.get(i).getValor().equals("}")) {
        contexto--;
      } else if (listaTokens.get(i).getNome() == "id") {
        verificaContexto(listaTokens.get(i));

        if (listaTokens.get(i + 1).getValor() == "=" && listaTokens.get(i + 2).getNome() != "=") {
          verificaUsoAntesDaDeclaracao(listaTokens, tabelaSimbolos, i);
        }
      }

      // !Concertar possíveis erros que podem ser gerados por conta do "i+2"

    }
  }

  // * Método que recebe um toke e verifica se o contexto atual bate com o
  // contexto de seu token cadastrado na tabela de símbolos */
  private void verificaContexto(Token token) throws ErrosCompilador {
    if (token.getContexto() > contexto) {
      throw new ErrosCompilador("Variável declarada fora do contexto");
    }
  }

  // *Método responsável por verificar se as operações dentro do if/while retornam
  // um tipo boolean */
  private void verificaCondicionalBooleano(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i) {

  }

  // *Método responsável por verificar se as operações são realizadas com o mesmo
  // tipo */
  private void verificaTiposDasOperacoes(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i) {

  }

  // *Método responsável por verificar se a variável foi declarada antes de ser
  // utilizada */
  private void verificaUsoAntesDaDeclaracao(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i) {

  }

  // *Método responsável por verificar se a variável foi declarada mais de uma vez
  // */
  private void verificaDeclaracaoDuplaDeVariavel(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i)
      throws ErrosCompilador {
    // *Atualiza tabela de símbolos */
    tabelaDeSimbolos = TabelaDeSimbolos.gTabelaDeSimbolos();

    // *Verificação de dupla declaração */
    if (listaTokens.get(i).getValor().equals("int") || listaTokens.get(i).getValor().equals("float")
        || listaTokens.get(i).getValor().equals("bool")) {
      System.out.println("Declaração de variável");
      if (tabelaDeSimbolos.isToken(listaTokens.get(i + 1).getValor()) != null) {
        throw new ErrosCompilador("Variável " + listaTokens.get(i + 1).getValor() + " declarada mais de uma vez");
      } else {
        System.out.println("Setando palavra reservada");
        listaTokens.get(i + 1).setContexto(contexto);
        TabelaDeSimbolos.setPalavraReservada(listaTokens.get(i + 1));
      }
    }
  }

}
