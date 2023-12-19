import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProjetoCompiladores {
    public static void main(String[] args) throws Exception {

        String nomeArquivo = "./texto.txt"; // Substitua pelo caminho do seu arquivo de texto
        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            String codigoLimpo = "";

            // Retira espaços duplos e comentários
            while ((linha = bufferedReader.readLine()) != null) {
                codigoLimpo = codigoLimpo.concat(AnaliseLexa.LimpaEspacoBrancoEComentario(linha));
            }

            System.out.println(TabelaDeSimbolos.RetornaAlfabeto());

            // Testa se os chars são válidos
            if (AnaliseLexa.ValidaChars(codigoLimpo) != null) {
                System.out.println("ANÁLISE LEXA OK");

                for (int i = 0; i < AnaliseLexa.ValidaChars(codigoLimpo).length; i++) {
                    System.out.println(AnaliseLexa.ValidaChars(codigoLimpo)[i]); // ! EXIBE A LISTA DE TOKENS
                }

                // !Análise Lexa
                ArrayList<Token> tokensSeparados = AnaliseLexa.ValidaECriaTokens(codigoLimpo);

                // !Exibição dos tokens
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(tokensSeparados);
                analisadorSintatico.AnaliseSintatica();

            } else
                System.out.println("ERRO: LEXO");

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Chama função separaTokens

    }

    // Função que separa o código em tokens

}
