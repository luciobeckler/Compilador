import java.util.ArrayList;
import java.util.HashSet;

public class AnalisadorSemantico {
  private ArrayList<Token> listaToken;

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
    Token tokenAtual;
    TabelaDeSimbolos.InicializaPalavrasReservadas();
    HashSet<Token> tabelaSimbolos = TabelaDeSimbolos.getTabelaSimpolos();

    for (int i = 0; i < listaTokens.size(); i++) {
      tokenAtual = listaTokens.get(i);

      // *Verificação de dupla declaração */
      if (tokenAtual.getValor().equals("int") || tokenAtual.getValor().equals("float")
          || tokenAtual.getValor().equals("bool")) {
        System.out.println("Declaração de variável");
        if (!tabelaSimbolos.contains(tokenAtual)) {
          System.out.println("Setando palavra reservada");
          TabelaDeSimbolos.setPalavraReservada(listaTokens.get(i + 1));
          // !PALAVRA ESTÁ SENDO ADICIONADA, CONTINUAR LÓGICA DAQUI
        } else {
          throw new ErrosCompilador("Variável " + tokenAtual.getValor() + " declarada mais de uma vez");
        }
      }
    }
  }

}
