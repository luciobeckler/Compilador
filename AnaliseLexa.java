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

  public static ArrayList<Token> ValidaECriaTokens(String codigoLimpo) throws Exception {
    ArrayList<Token> listaTokens = new ArrayList<>(); // !Deve retornar essa estrutura

    // Passa por todo o array codigoLimpo REMOVER ENQUANTO NÃO FOR VAZIO
    while (!codigoLimpo.isEmpty()) {
      String[] stringAux;
      switch (codigoLimpo.charAt(0)) {
        case ' ':
          while (codigoLimpo.charAt(0) == ' ') {
            codigoLimpo = codigoLimpo.substring(1);
          }
          break;
        case '{':
        case '}':
        case '(':
        case ')':
        case ';':
        case '.':
        case '<':
        case '>':
        case '=':
        case '!':
        case '+':
        case '-':
        case '*':
        case '/':
          stringAux = funcaoDosSimbolos(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1)); // Descobrir como receber o retorno
          break;
        case 'b':
          stringAux = funcaoDoB(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1)); // Descobrir como receber o retorno
          break;
        case 'f':
          stringAux = funcaoDoF(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1)); // Descobrir como receber o retorno
          break;
        case 'w':
          stringAux = funcaoDoW(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1)); // Descobrir como receber o retorno
          break;
        case 'i':
          stringAux = funcaoDoI(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1)); // Descobrir como receber o retorno
          break;
        case 't':
          stringAux = funcaoDoT(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1)); // Descobrir como receber o retorno
          break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          stringAux = funcaoDoNumero(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1));
          break;
        default:
          stringAux = funcaoDaVariavel(codigoLimpo);
          codigoLimpo = stringAux[2];
          listaTokens.add(new Token(stringAux[0], stringAux[1], -1));
          break;
      }
    }
    for (int index = 0; index < listaTokens.size(); index++) {
      Token token = listaTokens.get(index);
      System.err.println();
    }
    return listaTokens;
  }

  // *Funções de identificação de token
  private static String[] funcaoDoB(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };
    // Testa se o b é de bool
    if (codigoLimpo.charAt(1) == 'o' && codigoLimpo.charAt(2) == 'o' && codigoLimpo.charAt(3) == 'l'
        && codigoLimpo.charAt(4) == ' ') {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 4);
      retornoToken[2] = codigoLimpo.substring(4); // Limpa o código
      return retornoToken;
    }
    if (codigoLimpo.charAt(1) == 'r' && codigoLimpo.charAt(2) == 'e' && codigoLimpo.charAt(3) == 'a'
        && codigoLimpo.charAt(4) == 'k'
        && (codigoLimpo.charAt(5) == ' ' || codigoLimpo.charAt(5) == ';')) {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 5);
      if (codigoLimpo.charAt(5) == ' ')
        retornoToken[2] = codigoLimpo.substring(5); // Limpa o código para o espaço
      else if (codigoLimpo.charAt(5) == ';')
        retornoToken[2] = codigoLimpo.substring(5); // Limpa o código mas mantem o ;
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
    // Testa se o f é de float
    if (codigoLimpo.charAt(1) == 'l' && codigoLimpo.charAt(2) == 'o' && codigoLimpo.charAt(3) == 'a'
        && codigoLimpo.charAt(4) == 't'
        && (codigoLimpo.charAt(5) == ' ' || codigoLimpo.charAt(5) == ';')) {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 5);
      if (codigoLimpo.charAt(5) == ';')
        retornoToken[2] = codigoLimpo.substring(4);
      else
        retornoToken[2] = codigoLimpo.substring(5); // Limpa o código
      return retornoToken;
    } else if (codigoLimpo.charAt(1) == 'a' && codigoLimpo.charAt(2) == 'l' && codigoLimpo.charAt(3) == 's'
        && codigoLimpo.charAt(4) == 'e'
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

  private static String[] funcaoDoW(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };
    // Testa se o w é de while
    if (codigoLimpo.charAt(1) == 'h' && codigoLimpo.charAt(2) == 'i' && codigoLimpo.charAt(3) == 'l'
        && codigoLimpo.charAt(4) == 'e'
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

  private static String[] funcaoDoI(String codigoLimpo) {

    String retornoToken[] = { "", "", "" };
    // Testa se o i é de int
    if (codigoLimpo.charAt(1) == 'n' && codigoLimpo.charAt(2) == 't'
        && (codigoLimpo.charAt(3) == ' ' || codigoLimpo.charAt(3) == ';')) {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 3);
      if (codigoLimpo.charAt(3) == ' ')
        codigoLimpo = codigoLimpo.substring(3); // Limpa o código
      else
        codigoLimpo = codigoLimpo.substring(2);
      retornoToken[2] = codigoLimpo;
      return retornoToken;
    } else if (codigoLimpo.charAt(1) == 'f'
        && codigoLimpo.charAt(2) == ' ') {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 2);
      retornoToken[2] = codigoLimpo.substring(2);
      return retornoToken;
    } else { // Caso não for, caminha pelo código e adiciona no retorno e vai retirando de
             // código limpo até encontrar um espaço
      while (codigoLimpo.charAt(0) != ';' && codigoLimpo.charAt(0) != ' ') {
        retornoToken[1] += codigoLimpo.charAt(0);
        codigoLimpo = codigoLimpo.substring(1); // Limpa o código
      }
      retornoToken[0] = "id";
      retornoToken[2] = codigoLimpo;

      return retornoToken;
    }
  }

  private static String[] funcaoDoT(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };
    // Testa se o t é de true
    if (codigoLimpo.charAt(1) == 'r' && codigoLimpo.charAt(2) == 'u' && codigoLimpo.charAt(3) == 'e'
        && (codigoLimpo.charAt(4) == ' ' || codigoLimpo.charAt(4) == ';')) {
      retornoToken[0] = "palavraReservada";
      retornoToken[1] = codigoLimpo.substring(0, 4);

      if (codigoLimpo.charAt(4) == ' ')
        retornoToken[2] = codigoLimpo.substring(4); // Limpa o código para o espaço
      else if (codigoLimpo.charAt(4) == ';')
        retornoToken[2] = codigoLimpo.substring(4); // Limpa o código mas mantem o ;

      return retornoToken;
    } else { // Caso não for, caminha pelo código e adiciona no retorno e vai retirando de
      // código limpo até encontrar um espaço
      while (codigoLimpo.charAt(0) != ' ' || codigoLimpo.charAt(0) == ';') {
        retornoToken[1] += codigoLimpo.charAt(0);
        codigoLimpo = codigoLimpo.substring(1); // Limpa o código
      }
      retornoToken[0] = "id";
      retornoToken[2] = codigoLimpo;

      return retornoToken;
    }
  }

  private static String[] funcaoDoNumero(String codigoLimpo) throws Exception {
    String retornoToken[] = { "", "", "" };
    boolean isDecimal = false;

    while (codigoLimpo.length() > 0 && (Character.isDigit(codigoLimpo.charAt(0)) || codigoLimpo.charAt(0) == '.')) {
      if (codigoLimpo.charAt(0) == '.') {
        if (isDecimal) {
          // Se já encontramos um ponto decimal anteriormente, isso é um erro
          System.out.println("Erro: Número decimal inválido");
          throw new Exception();
        }
        isDecimal = true;
      }

      retornoToken[1] += codigoLimpo.charAt(0);
      codigoLimpo = codigoLimpo.substring(1); // Limpa o código
    }

    if (isDecimal) {
      retornoToken[0] = "numeroDecimal";
    } else {
      retornoToken[0] = "numeroInteiro";
    }

    retornoToken[2] = codigoLimpo;
    return retornoToken;
  }

  private static String[] funcaoDosSimbolos(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };

    retornoToken[0] = "simbolo";
    retornoToken[1] = String.valueOf(codigoLimpo.charAt(0));
    retornoToken[2] = codigoLimpo.substring(1);

    return retornoToken;
  }

  private static String[] funcaoDaVariavel(String codigoLimpo) {
    String retornoToken[] = { "", "", "" };
    retornoToken[0] = "id";
    while (codigoLimpo.charAt(0) != ' ' && codigoLimpo.charAt(0) != ';' && codigoLimpo.charAt(0) != ')'
        && codigoLimpo.charAt(0) != '}'
        && codigoLimpo.charAt(0) != '=' && codigoLimpo.charAt(0) != '!' && codigoLimpo.charAt(0) != '+'
        && codigoLimpo.charAt(0) != '-'
        && codigoLimpo.charAt(0) != '*' && codigoLimpo.charAt(0) != '/' && codigoLimpo.charAt(0) != '>'
        && codigoLimpo.charAt(0) != '<') {
      retornoToken[1] += codigoLimpo.charAt(0);
      codigoLimpo = codigoLimpo.substring(1);
    }
    retornoToken[2] = codigoLimpo;
    return retornoToken;
  }

}
