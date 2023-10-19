import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TabelaDeSimbolos {

  private static TabelaDeSimbolos tabela;
  private HashSet<String> hashPalavrasReservadas;

  private TabelaDeSimbolos() {
    hashPalavrasReservadas = new HashSet<String>();
    AdicionarPalavrasReservadas();
    RetornaAlfabeto();
  }

  public static TabelaDeSimbolos gTabelaDeSimbolos() {
    if (tabela == null) {
      tabela = new TabelaDeSimbolos();
    }
    return tabela;

  }

  // Fornece o alfabeto da linguagem
  public static List<String> RetornaAlfabeto() {
    String simbolos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ{}.,;<>=!()/-*+0123456789 ";
    List<String> dicionarioSimbolos = new ArrayList<>();

    for (int i = 0; i < simbolos.length(); i++) {
      dicionarioSimbolos.add(String.valueOf(simbolos.charAt(i)));
    }

    return dicionarioSimbolos;
  }

  // Retorna as palavras reservadas da linguagem
  private HashSet<String> AdicionarPalavrasReservadas() {
    hashPalavrasReservadas.add("int");
    hashPalavrasReservadas.add("float");
    hashPalavrasReservadas.add("bool");
    hashPalavrasReservadas.add("true");
    hashPalavrasReservadas.add("false");
    hashPalavrasReservadas.add("while");
    hashPalavrasReservadas.add("if");
    hashPalavrasReservadas.add("break");

    return hashPalavrasReservadas;

  }

}
