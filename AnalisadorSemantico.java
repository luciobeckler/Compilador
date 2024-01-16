import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    TabelaDeSimbolos.InicializaPalavrasReservadas();
    TabelaDeSimbolos tabelaSimbolos;
    tabelaSimbolos = TabelaDeSimbolos.gTabelaDeSimbolos();

    for (int i = 0; i < listaTokens.size(); i++) {
      if (Arrays.asList("int", "bool", "float").contains(listaTokens.get(i).getValor())) {
        verificaDeclaracaoDuplaDeVariavel(listaTokens, tabelaSimbolos, i);
      }
      verificaUsoAntesDaDeclaracao(listaTokens, tabelaSimbolos, i);

    }
  }

  private void verificaUsoAntesDaDeclaracao(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i) {

  }

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
        TabelaDeSimbolos.setPalavraReservada(listaTokens.get(i + 1));
        // !PALAVRA ESTÁ SENDO ADICIONADA, CONTINUAR LÓGICA DAQUI
      }
    }
  }

}
