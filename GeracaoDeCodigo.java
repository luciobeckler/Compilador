import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GeracaoDeCodigo {
  private ArrayList<Token> listaToken;
  private HashMap<String, Token> tabelaDeSimbolos;

  public GeracaoDeCodigo(ArrayList<Token> listaToken, HashMap<String, Token> tabelaDeSimbolos) {
    this.listaToken = listaToken;
    this.tabelaDeSimbolos = tabelaDeSimbolos;
  }

  public ArrayList<Token> getListaToken() {
    return listaToken;
  }

  public void setListaToken(ArrayList<Token> listaToken) {
    this.listaToken = listaToken;
  }

  public HashMap<String, Token> getTabelaDeSimbolos() {
    return tabelaDeSimbolos;
  }

  public void setTabelaDeSimbolos(HashMap<String, Token> tabelaDeSimbolos) {
    this.tabelaDeSimbolos = tabelaDeSimbolos;
  }

  public void geraCodigoAssembly() throws IOException {
    File arquivoASM = criaArquivoAssembly();
    FileWriter fileWriter = new FileWriter(arquivoASM);
    BufferedWriter bufferWrite = new BufferedWriter(fileWriter);

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
    bufferWrite.close();
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
}
