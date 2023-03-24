package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Cohuzer
 */
public class GerarClasseMain {

    public GerarClasseMain(String entidade, List<String> atributo, String caminho) {
        Ferramentas f = new Ferramentas();
        List<String> codigo = new ArrayList<>();

        codigo.add("package Main;\n"
                + "\n"
                + "import GUIs."+entidade+"GUI;"
                + "\n"
                + "/**\n"
                + " *\n"
                + "* @author Mateus Batichotti Silva\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + "*/"
                + "\n\npublic class Main {\n"
                + "\n"
                + "    /**\n"
                + "     * @param args the command line arguments\n"
                + "     */\n"
                + "    public static void main(String[] args) {\n"
                + "        "+entidade+"GUI mainGUI = new "+entidade+"GUI();\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/Main/Main.java", codigo);
    }

}
