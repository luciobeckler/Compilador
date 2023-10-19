import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProjetoCompiladores {
    public static void main(String[] args) {

        TabelaDeSimbolos tabela = TabelaDeSimbolos.gTabelaDeSimbolos();

        String nomeArquivo = "./texto.txt"; // Substitua pelo caminho do seu arquivo de texto
        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            String codigoLimpo = "";

            // Retira espaços duplos e comentários
            while ((linha = bufferedReader.readLine()) != null) {
                // System.out.println(AnaliseLexa.LimpaEspacoBrancoEComentario(linha));
                codigoLimpo = codigoLimpo.concat(AnaliseLexa.LimpaEspacoBrancoEComentario(linha));
            }

            /*
             * for(int i=0; i < codigoLimpo.length(); i++){
             * if(codigoLimpo.charAt(i).TabelaDeSimbolos.AdicionarAlfabeto());
             * }
             */

            // Exibe o resultado final
            System.out.println(codigoLimpo);

            System.out.println(TabelaDeSimbolos.RetornaAlfabeto());
            AnaliseLexa.separaTokens(codigoLimpo);

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        // Chama função separaTokens

    }

    // Função que separa o código em tokens

}
