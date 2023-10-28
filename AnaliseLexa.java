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
      System.out.println("Caractere incial errado");
      throw new Exception();
    } else {
      listaTokens.add(new Token(new String[] { "simbolo", "{" }));
      System.out.println("adiciona { na lista de tokens");
      // nomes expecíficos
      codigoLimpo = codigoLimpo.substring(1);
    }

    // Passa por todo o array codigoLimpo REMOVER ENQUANTO NÃO FOR VAZIO
    while (!codigoLimpo.isEmpty()) {
      String[] stringAux;
      switch (codigoLimpo.charAt(0)) {
        case ' ':
          while (codigoLimpo.charAt(0) == ' ') {
            codigoLimpo = codigoLimpo.substring(1);
          }
          break;
        case 'b':
          stringAux = funcaoDoB(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(new String[] { stringAux[0], stringAux[1] })); // Descobrir como receber o retorno
          System.out.println("Palavra adicionada na listaTokens pela funcaoDoB: " + codigoLimpo);
          break;
        case 'f':
          stringAux = funcaoDoF(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(new String[] { stringAux[0], stringAux[1] })); // Descobrir como receber o retorno
          System.out.println("Palavra adicionada na listaTokens pela funcaoDoB: " + codigoLimpo);
          break;
        case 'w':
          funcaoDoW(codigoLimpo);
          break;
        default:
          System.out.println("FIM");
          break;
      }
    }
    System.out.println(listaTokens.size());
    for (int i = 0; i < listaTokens.size(); i++) {
      System.out.println("ListaTokens no index " + i + ":" + listaTokens.get(i));
    }

    return "falso";
  }

  private static String[] funcaoDoB(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };
    // Testa se o b é de bool
    if (codigoLimpo.charAt(1) == 'o' && codigoLimpo.charAt(2) == 'o' && codigoLimpo.charAt(3) == 'l'
        && codigoLimpo.charAt(4) == ' ') {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 4);
      retornoToken[2] = codigoLimpo.substring(4); // Limpa o código
      return retornoToken;
    } else { // Caso não for, caminha pelo código e adiciona no retorno e vai retirando de
             // código limpo até encontrar um espaço
      while (codigoLimpo.charAt(0) != ' ') {
        retornoToken[1] += codigoLimpo.charAt(0);
        codigoLimpo = codigoLimpo.substring(1); // Limpa o código
      }
      retornoToken[0] = "id";
      retornoToken[2] = codigoLimpo;

      return retornoToken;
    }
  }

  private static String[] funcaoDoF(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };
    // Testa se o b é de bool
    if (codigoLimpo.charAt(1) == 'l' && codigoLimpo.charAt(2) == 'o' && codigoLimpo.charAt(3) == 'a'
        && codigoLimpo.charAt(4) == 't'
        && codigoLimpo.charAt(5) == ' ') {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 5);
      retornoToken[2] = codigoLimpo.substring(5); // Limpa o código
      return retornoToken;
    } else { // Caso não for, caminha pelo código e adiciona no retorno e vai retirando de
             // código limpo até encontrar um espaço
      while (codigoLimpo.charAt(0) != ' ') {
        retornoToken[1] += codigoLimpo.charAt(0);
        codigoLimpo = codigoLimpo.substring(1); // Limpa o código
      }
      retornoToken[0] = "id";
      retornoToken[2] = codigoLimpo;

      return retornoToken;
    }
  }

  private static void funcaoDoW(String codigoLimpo) {
  }

}
