import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

            // Testa se os chars são válidos
            if (AnaliseLexa.ValidaChars(codigoLimpo) != null) {
                System.out.println("ANÁLISE LEXA OK");

                // !Análise Lexa
                ArrayList<Token> tokensSintaticos = AnaliseLexa.ValidaECriaTokens(codigoLimpo);
                ArrayList<Token> tokensSemantico = (ArrayList<Token>) tokensSintaticos.clone();

                // !Exibição dos tokens
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(tokensSintaticos);
                analisadorSintatico.AnaliseSintatica();
                System.out.println("ANÁLISE SINTÁTICA OK");

                // ! Análise Semântica
                AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico(tokensSemantico);
                analisadorSemantico.ExibeTokens();
                analisadorSemantico.AnaliseSemantica(tokensSemantico);

                HashMap<String, Token> tabelaDeSimbolos = TabelaDeSimbolos.getHashPalavrasReservadas();

                GeracaoDeCodigo geradorDeCodigo = new GeracaoDeCodigo(tokensSemantico, tabelaDeSimbolos);
                geradorDeCodigo.geraCodigoAssembly();

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
