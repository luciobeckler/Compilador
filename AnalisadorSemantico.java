import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AnalisadorSemantico {
  private ArrayList<Token> listaToken;
  private int contexto = 0;

  public AnalisadorSemantico(ArrayList<Token> listaToken) {
    this.listaToken = listaToken;

  }

  public void ExibeTokens() {
    listaToken.forEach(item -> {
      System.err.println("<" + item.getNome() + ">" + "<" + item.getValor() + ">");
    });
  }

  public void AnaliseSemantica(ArrayList<Token> listaTokens) throws ErrosCompilador {
    System.err.println("Passei por aqui");
    TabelaDeSimbolos.InicializaPalavrasReservadas();
    TabelaDeSimbolos tabelaSimbolos;
    tabelaSimbolos = TabelaDeSimbolos.gTabelaDeSimbolos();

    // !Lembrar que no token final não haverá i+1
    for (int i = 0; i < listaTokens.size() - 1; i++) { // !Cadastrar o contexto ao cadastrar uma variável, caso ela já
                                                       // exista, teste o contexto dela
      if (Arrays.asList("int", "bool", "float").contains(listaTokens.get(i).getValor())) {
        verificaDeclaracaoDuplaDeVariavel(listaTokens, tabelaSimbolos, i);
      } else if (Arrays.asList("if", "while").contains(listaTokens.get(i).getValor())) {
        verificaCondicionalBooleano(listaTokens, tabelaSimbolos, i);
      } else if (listaTokens.get(i).getValor().equals("{")) {
        contexto++;
      } else if (listaTokens.get(i).getValor().equals("}")) {
        contexto--;
      } else if (listaTokens.get(i).getNome() == "id") {
        verificaContexto(listaTokens.get(i));
        verificaUsoAntesDaDeclaracao(listaTokens, tabelaSimbolos, i);

        if (listaTokens.get(i + 1).getValor().equals("=") && !listaTokens.get(i + 2).getValor().equals("=")) {
          verificaTiposDasOperacoes(listaTokens, tabelaSimbolos, i);
        }
      }

      // !Concertar possíveis erros que podem ser gerados por conta do "i+2"

    }
  }

  // * Método que recebe um toke e verifica se o contexto atual bate com o
  // contexto de seu token cadastrado na tabela de símbolos */
  private void verificaContexto(Token token) throws ErrosCompilador {
    if (token.getContexto() > contexto) {
      throw new ErrosCompilador("Variável declarada fora do contexto");
    }
  }

  // *Método responsável por verificar se as operações dentro do if/while retornam
  // um tipo boolean */
  private void verificaCondicionalBooleano(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i)
      throws ErrosCompilador {

    while (!listaTokens.get(i).getValor().equals(")")) {
      if (listaTokens.get(i).getNome() == "id") {
        Token variavel = tabelaDeSimbolos.isToken(listaTokens.get(i).getValor());
        if (!variavel.getTipoVariavel().equals("bool")) // !usar a lista de token antes
          // // adicionar a variável na
          // tabela de símbolos
          throw new ErrosCompilador("Tipo de variável diferente do tipo booleano");
      }
      i++;
    }
  }

  // *Método responsável por verificar se as operações são realizadas com o mesmo
  // tipo */
  private void verificaTiposDasOperacoes(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i)
      throws ErrosCompilador {
    switch (tabelaDeSimbolos.isToken(listaTokens.get(i).getValor()).getTipoVariavel()) {
      case "bool":
        verificaOperacaoBool(listaTokens, tabelaDeSimbolos, i);
        break;
      case "int":
        verificaOperacaoInt(listaTokens, tabelaDeSimbolos, i);
        break;
      case "float":
        verificaOperacaoFloat(listaTokens, tabelaDeSimbolos, i);
        break;
      default:
        break;
    }
  }

  private void verificaOperacaoBool(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i)
      throws ErrosCompilador {
    if (listaTokens.get(i + 2).getNome().equals("id")) {
      if (!tabelaDeSimbolos.isToken(listaTokens.get(i + 2).getValor()).getTipoVariavel().equals("bool")) {
        throw new ErrosCompilador("A variável " + listaTokens.get(i).getValor()
            + " está sofrendo uma atribuição de um tipo diferente da que foi declarada");
      }
    } else if (listaTokens.get(i + 2).getNome().equals("palavraReservada")) {
      if (!Arrays.asList("true", "false").contains(listaTokens.get(i + 2).getValor())) {
        throw new ErrosCompilador("A variável " + listaTokens.get(i).getValor()
            + " está sofrendo uma atribuição de um tipo diferente da que foi declarada");
      }
    } else if (Arrays.asList("numeroInteiro", "numeroDecimal").contains(listaTokens.get(i + 2).getNome())) {
      throw new ErrosCompilador("A variável " + listaTokens.get(i).getValor()
          + " está sofrendo uma atribuição de um tipo diferente da que foi declarada");
    }

  }

  private void verificaOperacaoInt(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i) {
    System.err.println(listaTokens.get(i).getValor());
  }

  private void verificaOperacaoFloat(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i) {
    System.err.println(listaTokens.get(i).getValor());
  }

  // *Método responsável por verificar se a variável foi declarada antes de ser
  // utilizada */
  private void verificaUsoAntesDaDeclaracao(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i)
      throws ErrosCompilador {
    if (tabelaDeSimbolos.isToken(listaTokens.get(i).getValor()) == null) {
      throw new ErrosCompilador("Variável " + listaTokens.get(i).getValor() + " sendo utilizada antes da declaração");
    }
  }

  // *Método responsável por verificar se a variável foi declarada mais de uma vez
  // */
  private void verificaDeclaracaoDuplaDeVariavel(ArrayList<Token> listaTokens, TabelaDeSimbolos tabelaDeSimbolos, int i)
      throws ErrosCompilador {
    // *Atualiza tabela de símbolos */
    tabelaDeSimbolos = TabelaDeSimbolos.gTabelaDeSimbolos();

    // *Verificação de dupla declaração */
    if (listaTokens.get(i).getValor().equals("int") || listaTokens.get(i).getValor().equals("float")
        || listaTokens.get(i).getValor().equals("bool")) {
      System.out.println("Declaração de variável");
      if (tabelaDeSimbolos.isToken(listaTokens.get(i + 1).getValor()) != null) {
        throw new ErrosCompilador("Variável " + listaTokens.get(i + 1).getValor() + " declarada mais de uma vez");
      } else {
        System.out.println("Setando palavra reservada");
        listaTokens.get(i + 1).setContexto(contexto);
        listaTokens.get(i + 1).setTipoVariavel(listaTokens.get(i).getValor());
        listaTokens.get(i + 1).setTipoVariavel(listaTokens.get(i).getValor());
        TabelaDeSimbolos.setPalavraReservada(listaTokens.get(i + 1));
      }
    }
  }

}
