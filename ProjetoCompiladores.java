import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProjetoCompiladores {
    public static void main(String[] args) {
        String nomeArquivo = "./texto.txt"; // Substitua pelo caminho do seu arquivo de texto
        String codigoFinal = "";

        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;

            while ((linha = bufferedReader.readLine()) != null) {                
                // Substitui espaços duplos por um único espaço repetidamente
                while (linha.contains("  ")) {
                    linha = linha.replace("  ", " ");
                }
                // Retira comentários
                if(linha.contains("//")){
                    String[] partes = linha.split("//", 2);
                    codigoFinal = codigoFinal.concat(partes[0]);
                }else codigoFinal = codigoFinal.concat(linha);
                
            }
            // Exibe cada linha do arquivo
            System.out.println(codigoFinal); 


            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        //Chama função separaTokens
        separaTokens(codigoFinal);
    }
    
    //Função que separa o código em tokens
    public static void separaTokens(String entrada){
        String[] palavrasReservadas = {"int","float", "bool", "true", "false", "while", "if", "break"} ;
        String simbolos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ{}.,;<>=!()/-*+0123456789 ";
        String[] dicionarioSimbolos = new String[simbolos.length()];

        //Transforma o dicionario em uma Lista de Simbolos chamado dicionario simbolos
        for (int i = 0; i < simbolos.length(); i++) {
            dicionarioSimbolos[i] = String.valueOf(simbolos.charAt(i));
        }

        // Verifica se o caractere não está no dicionário de símbolos
        for (int i = 0; i < entrada.length(); i++) {
            char caractere = entrada.charAt(i);
            String caractereComoString = String.valueOf(caractere);
            //System.out.println(entrada.charAt(i));
            if (!Arrays.asList(dicionarioSimbolos).contains(caractereComoString)) {
                System.out.println("Símbolo não reconhecido: " );
                System.out.println(caractereComoString);
            }
            else System.out.println("Passou");
        }
    }
}
