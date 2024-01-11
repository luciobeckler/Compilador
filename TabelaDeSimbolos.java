import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TabelaDeSimbolos {

  private static TabelaDeSimbolos tabela;
  private static HashSet<Token> hashPalavrasReservadas = new HashSet<>();

  public TabelaDeSimbolos() {
    hashPalavrasReservadas = new HashSet<Token>();
    InicializaPalavrasReservadas();
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
  public static HashSet<Token> InicializaPalavrasReservadas() {

    hashPalavrasReservadas.add(new Token("palavraReservada", "int"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "float"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "bool"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "true"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "false"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "while"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "if"));
    hashPalavrasReservadas.add(new Token("palavraReservada", "break"));

    return hashPalavrasReservadas;
  }

  public static void setPalavraReservada(Token token) {
    hashPalavrasReservadas.add(token);
  }

  public static HashSet<Token> getTabelaSimpolos() {
    return hashPalavrasReservadas;
  }

}
