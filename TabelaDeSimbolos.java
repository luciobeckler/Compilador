import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.List;

public class TabelaDeSimbolos {

  private static TabelaDeSimbolos tabela;
  private static HashMap<String, Token> hashPalavrasReservadas = new HashMap<>();

  private TabelaDeSimbolos() {
    hashPalavrasReservadas = new HashMap<String, Token>();
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
  public static HashMap<String, Token> InicializaPalavrasReservadas() {

    hashPalavrasReservadas.put("int", new Token("palavraReservada", "int"));
    hashPalavrasReservadas.put("float", new Token("palavraReservada", "float"));
    hashPalavrasReservadas.put("bool", new Token("palavraReservada", "bool"));
    hashPalavrasReservadas.put("true", new Token("palavraReservada", "true"));
    hashPalavrasReservadas.put("false", new Token("palavraReservada", "false"));
    hashPalavrasReservadas.put("while", new Token("palavraReservada", "while"));
    hashPalavrasReservadas.put("if", new Token("palavraReservada", "if"));
    hashPalavrasReservadas.put("break", new Token("palavraReservada", "break"));

    return hashPalavrasReservadas;
  }

  public static void setPalavraReservada(Token token) {
    hashPalavrasReservadas.put(token.getValor(), new Token(token.getNome(), token.getValor()));
  }

  public Token isToken(String key) {
    Token retornoIsToken = hashPalavrasReservadas.get(key);

    return retornoIsToken;
  }

}
