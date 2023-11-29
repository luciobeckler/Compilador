import java.util.ArrayList;

public class AnaliseSintatica {
  public static void ExibeTokens(ArrayList<Token> token) {

    token.forEach(item -> {
      System.err.println("<" + item.getNome() + ">" + "<" + item.getValor() + ">");
    });

  }
}
