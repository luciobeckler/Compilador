import java.util.ArrayList;
import java.util.List;

public class AnaliseLexa {

  public static String LimpaEspacoBrancoEComentario(String linha) {
    String codigoFinal = "";

    // Substitui espaços duplos por um único espaço repetidamente
    while (linha.contains("  ")) {

      linha = linha.replace("  ", " ");
    }
    // Retira comentários
    if (linha.contains("//")) {
      String[] partes = linha.split("//", 2);
      codigoFinal = codigoFinal.concat(partes[0]);
    } else
      codigoFinal = codigoFinal.concat(linha);
    return codigoFinal;
  }

  public static Character[] ValidaChars(String entrada) {
    List<Character> tokens = new ArrayList<>();
    List<String> alfabeto = TabelaDeSimbolos.RetornaAlfabeto();

    for (int i = 0; i < entrada.length(); i++) {
      char caractere = entrada.charAt(i);
      String caractereComoString = String.valueOf(caractere);
      if (!alfabeto.contains(caractereComoString)) {
        return null; // Se qualquer caractere não estiver no alfabeto, retorne null
      }
      tokens.add(caractere);
    }

    // Converter a lista para um array de caracteres
    Character[] tokensArray = tokens.toArray(new Character[0]);

    return tokensArray; // Se todos os caracteres forem válidos, retorne o array de caracteres
  }

  public static String ValidaECriaTokens(String codigoLimpo) throws Exception {
    ArrayList<Token> listaTokens = new ArrayList<>(); // !Deve retornar essa estrutura

    // !Lança exceção de falta de chaves
    // Ou adiciona a { na tabela de símbolos e exclui ela do código
    if (codigoLimpo.charAt(0) != '{') {
      throw new Exception();
    } else {
      listaTokens.add(new Token("simbolo", codigoLimpo.charAt(0))); // TODO: Trocar num futuro distante o nome para
                                                                    // nomes expecíficos
      codigoLimpo = codigoLimpo.substring(1);
    }

    // Passa por todo o array codigoLimpo REMOVER ENQUANTO NÃO FOR VAZIO
    int index = 0;
    while (!codigoLimpo.isEmpty()) {

      switch (codigoLimpo.charAt(index)) {
        case ' ':
          codigoLimpo = codigoLimpo.substring(1);
          break;

        case 'b':
          funcaoDoB(codigoLimpo);
          break;
        case 'f':
          funcaoDoF(codigoLimpo);
          break;
        case 'w':
          funcaoDoW(codigoLimpo);
          break;

        default:
          break;
      }
    }

    return "falso";
  }

  private static void funcaoDoB(String codigoLimpo) {

  }

  private static void funcaoDoF(String codigoLimpo) {

  }

  private static void funcaoDoW(String codigoLimpo) {
  }

}
