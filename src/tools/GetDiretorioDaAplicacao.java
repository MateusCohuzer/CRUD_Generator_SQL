package tools;

/**
 *
 * @author radames
 */
public class GetDiretorioDaAplicacao {

    private String diretorioDaAplicacao;

    public String getDiretorioDaAplicacao() {
        return System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        GetDiretorioDaAplicacao getDiretorioCorrente = new GetDiretorioDaAplicacao();
        System.out.println("Diretorio " + getDiretorioCorrente.getDiretorioDaAplicacao());
    }
}
