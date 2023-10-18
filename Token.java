public class Token{
        private String nome;
        private String valor;

        //Construtor
        public Token(String nome, String valor) {
          this.nome = nome;
          this.valor = valor;
        }

        //Métodos setters e getters
        public String getNome() {
          return nome;
        }

        public void setNome(String nome) {
          this.nome = nome;
        }

        public String getValor() {
          return valor;
        }

        public void setValor(String valor) {
          this.valor = valor;
        }
        
    }