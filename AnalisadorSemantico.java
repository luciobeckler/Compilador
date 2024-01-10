import java.util.ArrayList;

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

}
