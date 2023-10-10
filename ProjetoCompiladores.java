import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProjetoCompiladores {
    public static void main(String[] args) {
        String nomeArquivo = "./texto.txt"; // Substitua pelo caminho do seu arquivo de texto

        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha.length()); // Exibe cada linha do arquivo
                System.out.println("======================================================");
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
