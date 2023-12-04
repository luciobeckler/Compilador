import java.util.ArrayList;

public class AnalisadorSintatico {
  private ArrayList<Token> listaTokens;

  public AnalisadorSintatico(ArrayList<Token> listaTokens) {
    setListaTokens(listaTokens);
  }

  //////////// * Função de manipulação da fita de tokens
  //////////// *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
  public void removeTokenZero() {
    this.listaTokens.remove(0);
  }

  public String getValorTokenZero() {
    return this.listaTokens.get(0).getValor();
  }

  public String getNomeTokenZero() {
    return this.listaTokens.get(0).getNome();
  }

  public boolean isIndice() {
    return this.getNomeTokenZero() == "id";
  }

  public boolean isPalavraReservada() {
    return this.getNomeTokenZero() == "palavraReservada";
  }

  public boolean isNumero() {
    return this.getNomeTokenZero() == "numero";
  }

  public boolean isSimbolo() {
    return this.getNomeTokenZero() == "simbolo";
  }

  public boolean isValorZeroEqualsTo(String valorTeste) {
    return this.getValorTokenZero().equals(valorTeste);
  }

  public boolean isValorPosicaoEqualsTo(int posicao, String valorTeste) {
    return listaTokens.get(posicao).getValor().equals(valorTeste);
  }

  public ArrayList<Token> getListaTokens() {
    return listaTokens;
  }

  public void setListaTokens(ArrayList<Token> listaTokens) {
    this.listaTokens = listaTokens;
  }

  public static void ExibeTokens(ArrayList<Token> token) {
    token.forEach(item -> {
      System.err.println("<" + item.getNome() + ">" + "<" + item.getValor() + ">");
    });
  }

  ////////// * INÍCIO DA ANÁLISE LEXA
  ////////// *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
  public void AnaliseLexa() throws Exception {
    this.Inicio();
  }

  public void Inicio() throws Exception {
    if (this.isValorZeroEqualsTo("{")) {
      this.removeTokenZero();
      Declarador();
    } else {
      throw new Exception(); // !Retorna erro de falta de {
    }
    if (this.isValorZeroEqualsTo("}") && this.listaTokens.size() == 1) {
      // * PASSOU NA ANÁLISE LEXA *\\
    } else
      throw new Exception(); // ! Retorna erro de código fora do bloco principal
  }

  public void Declarador() throws Exception {

    // * Criação de variável da gramática */
    if (this.isValorZeroEqualsTo("int") ||
        this.isValorZeroEqualsTo("float") ||
        this.isValorZeroEqualsTo("bool")) {
      this.removeTokenZero();
      if (this.isIndice()) {
        this.removeTokenZero();

        if (this.isValorZeroEqualsTo(";")) {
          this.removeTokenZero();
          this.Atribuidor();
        } else
          throw new Exception(); // ! Erro de falta de ;
      } else
        throw new Exception(); // ! Erro de falta de indice
    }
    // * Chamando o início novamente */
    // * Retorna Y em últimmo casoo */

  }

  public void Atribuidor() throws Exception {
    // * Chama Indice Complementoo */
    if (this.isIndice()) {
      this.removeTokenZero();
      if (this.isValorZeroEqualsTo("=")) {
        this.removeTokenZero();
        this.IndiceCompleto();
      } else
        throw new Exception(); // !Falta de = na atribuição
    }
    // * Chama WhileOrIf */
    else if (this.isValorZeroEqualsTo("while") || isValorZeroEqualsTo("if")) {
      this.removeTokenZero();
      Condicional();
    }
    // * Chama declarador novamente */
    // * Retorna Y em último caso */
  }

  public void IndiceCompleto() throws Exception {
    if (this.isIndice()) {
      removeTokenZero();
      if (isValorZeroEqualsTo(";")) {
        removeTokenZero();
        Declarador();
      } else
        throw new Exception(); // ! Falta de ; no final
    }
    if (isValorZeroEqualsTo("true") || isValorZeroEqualsTo("false")) {
      removeTokenZero();
      if (isValorZeroEqualsTo(";")) {
        removeTokenZero();
        Declarador();
      } else
        throw new Exception(); // ! Falta de ; no final
    } else {
      ExpressaoMatematica();
      if (isValorZeroEqualsTo(";")) {
        removeTokenZero();
        Declarador();
      } else
        throw new Exception(); // ! Falta de ; no final
    }
  }

  // !Pendente resolução de ambiguidade
  public void Condicional() throws Exception {
    if (isIndice() || isValorZeroEqualsTo("false") || isValorZeroEqualsTo("true")) {
      removeTokenZero();
    }
  }

  public void ExpressaoMatematica() throws Exception {
    Termo();
    ExpressaoMatematicaLinha();
  }

  public void OperadorCondicional() throws Exception {
    if ((isValorZeroEqualsTo("<") || isValorZeroEqualsTo(">")) && !isValorPosicaoEqualsTo(1, "=")) {
      removeTokenZero();
      return;
    } else if ((isValorZeroEqualsTo("<") || isValorZeroEqualsTo(">") || isValorZeroEqualsTo("=")
        || isValorZeroEqualsTo("!")) && isValorPosicaoEqualsTo(1, "=")) {
      removeTokenZero();
      removeTokenZero();
      return;
    } else {
      throw new Exception(); // ! Erro de operador lógico inválido
    }
  }

  public void IgualOuDiferente() throws Exception {
    if ((isValorZeroEqualsTo("=") && isValorPosicaoEqualsTo(1, "="))
        || (isValorZeroEqualsTo("!") && isValorPosicaoEqualsTo(1, "="))) {
      removeTokenZero();
      removeTokenZero();
    } else {
      throw new Exception(); // ! Erro de operador de igualdade ou desigualdade inválido
    }
  }

  public void ExpressaoLogica() throws Exception {
    TermoLogico();
    ExpressaoLogicaLinha();
  }

  public void Termo() throws Exception {
    Fator();
    TermoLinha();
  }

  public void TrueOrFalse() throws Exception {
    if (isValorZeroEqualsTo("true") || isValorZeroEqualsTo("false")) {
      removeTokenZero();
      return;
    } else {
      throw new Exception(); // !Erro de valor true ou false inválido
    }
  }

  public void IdentificadorMatematico() throws Exception {
    if (isIndice() || isNumero()) {
      removeTokenZero();
    } else {
      throw new Exception(); // ! Erro de false índice ou número
    }
  }

  public void IdentificadorLogico() throws Exception {
    if (isValorZeroEqualsTo("true") || isValorZeroEqualsTo("false")) {
      removeTokenZero();
      return;
    } else if (isIndice()) {
      removeTokenZero();
      return;
    } else if (isNumero()) {
      removeTokenZero();
      return;
    } else {
      ExpressaoLogica();
    }
  }

  public void OperadorMatematico() throws Exception {
    if (isValorZeroEqualsTo("+") || isValorZeroEqualsTo("-") ||
        isValorZeroEqualsTo("*") || isValorZeroEqualsTo("/")) {
      removeTokenZero();
      return;
    }
  }

  public void ExpressaoMatematicaLinha() throws Exception {
    OperadorMatematico();
    Termo();
    ExpressaoLogicaLinha();
  }

  public void ExpressaoLogicaLinha() throws Exception {
    OperadorCondicional();
    TermoLogico();
    ExpressaoLogicaLinha();
  }

  public void TermoLinha() throws Exception {
    OperadorMatematico();
    Fator();
    TermoLinha();
  }

  public void Fator() throws Exception {
    if (isValorZeroEqualsTo("(")) {
      removeTokenZero();
      ExpressaoMatematica();
      if (isValorZeroEqualsTo(")")) {
        removeTokenZero();
        return;
      } else {
        throw new Exception(); // ! Erro de fechamento do ()
      }
    } else if (isIndice() || isNumero()) {
      removeTokenZero();
      return;
    } else
      throw new Exception(); // ! Erro de Fator inválido
  }

  public void ValorCondicionalSilmples() throws Exception {
    if (isIndice() || isValorZeroEqualsTo("true") || isValorZeroEqualsTo("false")) {
      removeTokenZero();
      return;
    } else {
      throw new Exception(); // ! Erro de valor condicional simples inválido
    }
  }

  public void TermoLogico() throws Exception {
    FatorLogico();
    TermoLogicoLinha();
  }

  public void TermoLogicoLinha() throws Exception {
    OperadorCondicional();
    Fator();
    TermoLogicoLinha();
  }

  public void FatorLogico() throws Exception {
    if (isValorZeroEqualsTo("(")) {
      removeTokenZero();
      ExpressaoLogica();
      if (isValorZeroEqualsTo(")")) {
        removeTokenZero();
        return;
      } else {
        throw new Exception(); // ! Erro de fechamento do ()
      }
    } else if (isValorZeroEqualsTo("true") || isValorZeroEqualsTo("false") || isIndice()) {
      removeTokenZero();
      return;
    } else {
      throw new Exception(); // ! Erro de fator lógico inválido
    }
  }

}
