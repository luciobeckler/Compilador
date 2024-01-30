import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeracaoDeCodigo {
  private ArrayList<Token> listaToken;
  private HashMap<String, Token> tabelaDeSimbolos;
  private File arquivoASM;
  private FileWriter fileWriter;
  private BufferedWriter bufferWrite;

  public GeracaoDeCodigo(ArrayList<Token> listaToken, HashMap<String, Token> tabelaDeSimbolos) throws IOException {
    this.listaToken = listaToken;
    this.tabelaDeSimbolos = tabelaDeSimbolos;
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
    testeEscritaListaTokesn();
    testeEscritaTabelaSimbolos();
    setaRegistradores();

    // Feche o BufferedWriter após concluir a escrita
    bufferWrite.close();
  }

  private void testeEscritaTabelaSimbolos() throws Exception {
    bufferWrite.write("Tabela de símbolos:");
    bufferWrite.newLine();
    for (Map.Entry<String, Token> entry : tabelaDeSimbolos.entrySet()) {
      try {
        bufferWrite.write(entry.getValue().getValor());
        bufferWrite.newLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    bufferWrite.newLine();
  }

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

  private void setaRegistradores() {
    char registradorNome = 'A';
    int registradorNumero = 0;

    for (Map.Entry<String, Token> entry : tabelaDeSimbolos.entrySet()) {
      // Garantindo que apenas os ID's recebam o tratamento de registradores
      if (entry.getValue().getNome().equals("id")) {
        // Garantindo que o número de registradores seja até a letra Z
        if (registradorNumero <= 90) {
          entry.getValue().setRegistradorASM("A" + registradorNome);
          registradorNumero = (int) registradorNome;
          registradorNumero++;
          registradorNome = (char) registradorNumero;
        }
      }
    }
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
}
