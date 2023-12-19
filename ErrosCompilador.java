public class ErrosCompilador extends Exception {
  public ErrosCompilador(String logExcessao) {
    super(logExcessao);
    System.err.println(">>>>>>>>>" + logExcessao);
  }
}
