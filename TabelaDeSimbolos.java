import java.util.HashSet;

public class TabelaDeSimbolos {

  private static TabelaDeSimbolos tabela;
  private HashSet<String> hashPalavrasReservadas;
  private HashSet<String> hashAlfabeto;

  private TabelaDeSimbolos() {
    hashPalavrasReservadas = new HashSet<String>();
    hashAlfabeto = new HashSet<String>();
    AdicionarPalavrasReservadas();
    AdicionarAlfabeto();
  }

  public static TabelaDeSimbolos gTabelaDeSimbolos() {
    if (tabela == null) {
      tabela = new TabelaDeSimbolos();
    }
    return tabela;

  }

  private void AdicionarAlfabeto() {
    String simbolos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ{}.,;<>=!()/-*+0123456789 ";
  }

  private void AdicionarPalavrasReservadas() {
    hashPalavrasReservadas.add("int");
    hashPalavrasReservadas.add("float");
    hashPalavrasReservadas.add("bool");
    hashPalavrasReservadas.add("true");
    hashPalavrasReservadas.add("false");
    hashPalavrasReservadas.add("while");
    hashPalavrasReservadas.add("if");
    hashPalavrasReservadas.add("break");
  }

}
