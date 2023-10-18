import java.util.Arrays;

public class AnaliseLexa {
  public boolean separaTokens(String entrada) {
    String simbolos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ{}.,;<>=!()/-*+0123456789 ";
    String[] dicionarioSimbolos = new String[simbolos.length()];

    // Transforma o dicionario em uma Lista de Simbolos chamado dicionario simbolos
    for (int i = 0; i < simbolos.length(); i++) {
      dicionarioSimbolos[i] = String.valueOf(simbolos.charAt(i));
    }

    // Verifica se o caractere não está no dicionário de símbolos
    for (int i = 0; i < entrada.length(); i++) {
      char caractere = entrada.charAt(i);
      String caractereComoString = String.valueOf(caractere);
      // System.out.println(entrada.charAt(i));
      if (!Arrays.asList(dicionarioSimbolos).contains(caractereComoString)) {
        return false;
      } else
        return true;

    }
  }

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
}
