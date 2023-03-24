package tools;

// @author Radames
import java.text.DecimalFormat;

public class StringTools {

    private String s;
    private int largura;

    public StringTools(String s, int largura) {
        this.s = s;
        this.largura = largura;
    }

    public StringTools() {
    }

    public String plMaiusc(String s) {
        s = s.toUpperCase().charAt(0) + s.substring(1, s.length());
        return s;
    }

    public String plMinus(String s) {
        s = s.toLowerCase().charAt(0) + s.substring(1, s.length());
        return s;
    }

    public String plMinus3(String s) {
        s = s.toLowerCase().charAt(0) + s.toLowerCase().charAt(1) + s.toLowerCase().charAt(2) + s.substring(3, s.length());
        return s;
    }

    public String ajustaLargura(String s, int largura) {
        String aux = "";
        aux += s;
        for (int i = aux.length(); i < largura; i++) {
            aux += " ";
        }
        return aux;
    }

    public String centralizaString(String s, int largura) {
        int meio = (int) largura / 2;
        int inicio = meio - s.length() / 2;
        String aux = "";
        for (int i = 0; i < inicio; i++) {
            aux += " ";
        }
        aux += s;
        for (int i = aux.length(); i < largura; i++) {
            aux += " ";
        }
        return aux;
    }

    public String alinhaDireita(String s, int largura) {
        String aux = "";
        for (int i = 0; i < largura - s.length(); i++) {
            aux += " ";
        }
        aux += s;
        return aux;
    }

    public String converterDoubleString(double valorDouble) {
        /*Transformando um double em 2 casas decimais*/
        DecimalFormat fmt = new DecimalFormat("###,###,##0.00");    //limita o número de casas decimais     
        String string = fmt.format(valorDouble);
        String[] part = string.split("[,]");
        String valor = part[0] + "," + part[1];
        return valor;
    }
}
