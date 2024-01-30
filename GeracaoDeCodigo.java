import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeracaoDeCodigo {
  private ArrayList<Token> listaToken;
  private TabelaDeSimbolos tabelaDeSimbolos;
  private File arquivoASM;
  private FileWriter fileWriter;
  private BufferedWriter bufferWrite;

  public GeracaoDeCodigo(ArrayList<Token> listaToken) throws IOException {
    this.listaToken = listaToken;
    this.tabelaDeSimbolos = TabelaDeSimbolos.gTabelaDeSimbolos();
    this.arquivoASM = criaArquivoAssembly();
    this.fileWriter = new FileWriter(arquivoASM);
    this.bufferWrite = new BufferedWriter(fileWriter);
  }

  private File criaArquivoAssembly() {
    File arquivoASM = new File("/home/andre/Desktop/Estudo/IFMG/Compiladores/ProjetoOficial/codigo.asm");
    try {
      arquivoASM.createNewFile();
      System.out.print("Arquivo criado com sucesso!");

    } catch (IOException e) {
      e.printStackTrace();
    }
    return arquivoASM;
  }

  public void geraCodigoAssembly() throws Exception {
    /* testeEscritaListaTokesn(); */
    /* testeEscritaTabelaSimbolos(); */
    bufferWrite.write("section .text");
    bufferWrite.newLine();
    bufferWrite.write("    global _start");
    bufferWrite.newLine();
    bufferWrite.write("_start:");
    bufferWrite.newLine();
    setaRegistradores();

    for (int i = 0; i < listaToken.size(); i++) {
      if (listaToken.get(i).getValor().equals("=")) {
        String registradorTokenAnterior = tabelaDeSimbolos.isToken(listaToken.get(i - 1).getValor())
            .getRegistradorASM();
        bufferWrite.write("MOV " + registradorTokenAnterior + ", " + listaToken.get(i + 1).getValor());
      }
    }

    // Feche o BufferedWriter após concluir a escrita
    bufferWrite.close();
  }

  /*
   * private void testeEscritaTabelaSimbolos() throws Exception {
   * bufferWrite.write("Tabela de símbolos:");
   * bufferWrite.newLine();
   * for (Map.Entry<String, Token> entry :
   * tabelaDeSimbolos.gTabelaDeSimbolos().entrySet()) {
   * try {
   * bufferWrite.write(entry.getValue().getValor());
   * bufferWrite.newLine();
   * } catch (IOException e) {
   * e.printStackTrace();
   * }
   * }
   * bufferWrite.newLine();
   * }
   */

  private void testeEscritaListaTokesn() throws IOException {
    bufferWrite.write("Lista de tokens:");
    bufferWrite.newLine();
    listaToken.forEach(item -> {
      try {
        if (item.getValor().equals(";")) {
          bufferWrite.write(item.getValor());
          bufferWrite.newLine();
        } else {
          bufferWrite.write(item.getValor());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    bufferWrite.newLine();
  }

  private void setaRegistradores() throws IOException {
    char registradorNome = 'A';
    int registradorNumero = 0;
    ArrayList<Token> listaTokensID = TabelaDeSimbolos.retornaListaTokensID();

    for (Token item : listaTokensID) {
      if (registradorNumero <= 90) {
        item.setRegistradorASM(registradorNome + "X");
        bufferWrite.write("MOV " + registradorNome + "X, 0h");
        bufferWrite.newLine();
      }

      registradorNumero = (int) registradorNome; // Talvez você quis fazer isso dentro do if
      registradorNumero++;
      registradorNome = (char) registradorNumero;
    }
  }

  public ArrayList<Token> getListaToken() {
    return listaToken;
  }

  public void setListaToken(ArrayList<Token> listaToken) {
    this.listaToken = listaToken;
  }

  public TabelaDeSimbolos getTabelaDeSimbolos() {
    return tabelaDeSimbolos;
  }

  public void setTabelaDeSimbolos(TabelaDeSimbolos tabelaDeSimbolos) {
    this.tabelaDeSimbolos = tabelaDeSimbolos;
  }
}
