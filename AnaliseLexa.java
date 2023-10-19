import java.util.ArrayList;
import java.util.Arrays;
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

}
