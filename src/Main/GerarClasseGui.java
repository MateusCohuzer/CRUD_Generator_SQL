package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ConverteDatas;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Cohuzer
 */
public class GerarClasseGui {

    public GerarClasseGui(String nomeDaClasse, List<String> atributo, String caminho) {
        Ferramentas f = new Ferramentas();
        ConverteDatas converteDatas = new ConverteDatas();
        List<String> codigo = new ArrayList();
        String[] aux;
        st st = new st();
        String nomeDaClasseMin = st.plMinus(nomeDaClasse);
        codigo.add("package GUIs;\n\n\n");

        codigo.add("import Entidades." + nomeDaClasse + ";\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.util.List;\n"
                + "import javax.swing.BorderFactory;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextField;\n"
                + "import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.CardLayout;\n"
                + "import java.awt.FlowLayout;\n"
                + "import tools.ConverteDatas;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import DAOs.DAO" + nomeDaClasse + ";\n");

        int erro_count = 0;

        codigo.add("\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + "* @author Mateus Batichotti Silva | " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + "*/");

        codigo.add("public class " + nomeDaClasse + "GUI extends JDialog{");

        codigo.add("    Container cp;\n"
                + "    JPanel pnNorte = new JPanel();\n"
                + "    JPanel pnCentro = new JPanel();\n"
                + "    JPanel pnSul = new JPanel();");

        codigo.add("ConverteDatas converteDatas = new ConverteDatas();");

        codigo.add("    JButton btBuscar = new JButton(\"Buscar\");\n"
                + "    JButton btAdicionar = new JButton(\"Adicionar\");\n"
                + "    JButton btSalvar = new JButton(\"Salvar\");\n"
                + "    JButton btAlterar = new JButton(\"Alterar\");\n"
                + "    JButton btExcluir = new JButton(\"Excluir\");\n"
                + "    JButton btListar = new JButton(\"Listar\");\n"
                + "    JButton btCancelar = new JButton(\"Cancelar\");");

        codigo.add("String acao = \"\";");
        codigo.add("    private JScrollPane scrollTabela = new JScrollPane();\n"
                + "\n"
                + "    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));\n"
                + "    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));\n"
                + "    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));\n"
                + "\n"
                + "" + nomeDaClasse + " " + nomeDaClasseMin + " = new " + nomeDaClasse + "();\n"
                + "\n"
                + "    private CardLayout cardLayout;");
        codigo.add("////////////////////////////////// - MUTÁVEL - //////////////////////////////////");
        codigo.add("\n//pk\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            codigo.add("JLabel lb" + st.plMaiusc(aux[1]) + " = new JLabel(\"" + st.plMaiusc(aux[1]) + "\");\n");
            if ("Date".equals(aux[0])) {
                codigo.add("\nJTextField tf" + st.plMaiusc(aux[1]) + " = new DateTextField();\n");
            } else if ("img".equals(aux[0])) {
                codigo.add("JLabel lbPath" + st.plMaiusc(aux[1]) + " = new JLabel(\"Path\");\n");
                codigo.add("ImagemAjustada imagemAjustada" + st.plMaiusc(aux[1]) + " = new ImagemAjustada();\n"
                        + "    DiretorioDaAplicacao diretorioDaAplicacao" + st.plMaiusc(aux[1]) + " = new DiretorioDaAplicacao();\n"
                        + "    String dirApp" + st.plMaiusc(aux[1]) + " = diretorioDaAplicacao" + st.plMaiusc(aux[1]) + ".getDiretorioDaAplicacao();\n"
                        + "    String origem" + st.plMaiusc(aux[1]) + " = dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\";\n"
                        + "    int tamX" + st.plMaiusc(aux[1]) + " = 300;\n"
                        + "    int tamY" + st.plMaiusc(aux[1]) + " = 300;\n"
                        + "    String temFoto" + st.plMaiusc(aux[1]) + ";\n"
                        + "    CopiarArquivos copiarArquivos" + st.plMaiusc(aux[1]) + " = new CopiarArquivos();\n");
            } else if ("boolean".equals(aux[0])) {
                codigo.add("\nJCheckBox check" + st.plMaiusc(aux[1]) + " = new JCheckBox();\n");
            } else {
                codigo.add("JTextField tf" + st.plMaiusc(aux[1]) + " = new JTextField(" + aux[2] + ");\n");
            }
        }
        
        codigo.add("DAO" + nomeDaClasse + " dao" + nomeDaClasse + " = new DAO" + nomeDaClasse + "();");

        String s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s += "\"" + aux[1] + "\",";
        }
        s = s.substring(0, s.length() - 1);
        codigo.add("String[] colunas = new String[]{" + s + "};\n"
                + "String[][] dados = new String[0][colunas.length];\n");

        codigo.add("DefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "JTable tabela = new JTable(model);\n");
        codigo.add("");

        codigo.add("    public " + nomeDaClasse + "GUI(){\n");
        codigo.add("setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "        cp = getContentPane();\n"
                + "        cp.setLayout(new BorderLayout());\n"
                + "        setTitle(\"CRUD - " + nomeDaClasse + "\");\n"
                + "\n"
                + "        cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "        cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "        cp.add(pnSul, BorderLayout.SOUTH);\n"
                + "\n"
                + "        pnNorte.setBackground(Color.GRAY);\n"
                + "        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));\n"
                + "\n"
                + "        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));\n");
        aux = atributo.get(0).split(";");
        codigo.add("pnNorte.add(lb" + st.plMaiusc(aux[1]) + ");\n"
                + "        pnNorte.add(tf" + st.plMaiusc(aux[1]) + ");\n"
                + "        pnNorte.add(btBuscar);\n"
                + "        pnNorte.add(btAdicionar);\n"
                + "        pnNorte.add(btAlterar);\n"
                + "        pnNorte.add(btExcluir);\n"
                + "        pnNorte.add(btListar);\n"
                + "        pnNorte.add(btSalvar);\n"
                + "        pnNorte.add(btCancelar);\n"
                + "        \n"
                + "        lb" + st.plMaiusc(aux[1]) + ".setVisible(true);\n"
                + "        tf" + st.plMaiusc(aux[1]) + ".setVisible(true);\n"
                + "        btSalvar.setVisible(false);\n"
                + "        btAdicionar.setVisible(false);\n"
                + "        btAlterar.setVisible(false);\n"
                + "        btExcluir.setVisible(false);\n"
                + "        btCancelar.setVisible(false);"
                + "        btSalvar.setVisible(false);\n"
                + "        "
                + "        btSalvar.setBackground(Color.WHITE);\n"
                + "        btBuscar.setBackground(Color.WHITE);\n"
                + "        btListar.setBackground(Color.WHITE);\n"
                + "        btAdicionar.setBackground(Color.WHITE);\n"
                + "        btAlterar.setBackground(Color.WHITE);\n"
                + "        btExcluir.setBackground(Color.WHITE);\n"
                + "        btCancelar.setBackground(Color.WHITE);\n");
        codigo.add("pnCentro.setLayout(new GridLayout(colunas.length-1, 2));\n");

        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if ("img".equals(aux[0])) {
                codigo.add("pnCentro.add(lbPath" + st.plMaiusc(aux[1]) + ");\n"
                        + "pnCentro.add(lb" + st.plMaiusc(aux[1]) + ");\n");
            } else if ("boolean".equals(aux[0])) {
                codigo.add("pnCentro.add(lb" + st.plMaiusc(aux[1]) + ");\n");
                codigo.add("pnCentro.add(check" + st.plMaiusc(aux[1]) + ");\n");
            } else {
                codigo.add("pnCentro.add(lb" + st.plMaiusc(aux[1]) + ");\n");
                codigo.add("pnCentro.add(tf" + st.plMaiusc(aux[1]) + ");\n");
            }
        }

        codigo.add("cardLayout = new CardLayout();\n"
                + "pnSul.setLayout(cardLayout);");
        codigo.add("for (int i = 0; i < 5; i++) {\n"
                + "            pnVazio.add(new JLabel(\" \"));\n"
                + "        }\n"
                + "        pnSul.add(pnVazio, \"vazio\");\n"
                + "        pnSul.add(pnAvisos, \"avisos\");\n"
                + "        pnSul.add(pnListagem, \"listagem\");\n"
                + "        tabela.setEnabled(false);\n"
                + "\n"
                + "        pnAvisos.add(new JLabel(\"Avisos\"));");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (i == 0) {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
            } else if ("img".equals(aux[0])) {
                codigo.add("");
            } else if ("boolean".equals(aux[0])) {
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setSelected(false);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n");
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
            }
        }

        codigo.add("\n//listeners\n");
        codigo.add("btBuscar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "            try {"
                + "                cardLayout.show(pnSul, \"avisos\");");

        aux = atributo.get(0).split(";");

        switch (aux[0]) {
            case "String":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(tf" + st.plMaiusc(aux[1]) + ".getText());");
                break;
            case "int":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Integer.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "byte":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Byte.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "short":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Short.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "long":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Long.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "float":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Float.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "double":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Double.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "char":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(String.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()).charAt(0));");
                break;
            case "boolean":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(Boolean.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            case "Date":
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(converteDatas.converteDeStringParaDate(tf" + st.plMaiusc(aux[1]) + ".getText()));");
                break;
            default:
                codigo.add("" + nomeDaClasseMin + " = dao" + nomeDaClasse + ".obter(DESCONHECIDO.valueOf(tf" + st.plMaiusc(aux[1]) + ".getText()));");
        }

        codigo.add("if (" + nomeDaClasseMin + " != null) {//achou o " + nomeDaClasseMin + " na lista\n"
                + "                        //mostrar\n"
                + "                        btAdicionar.setVisible(false);\n"
                + "                        btAlterar.setVisible(true);\n"
                + "                        btExcluir.setVisible(true);\n"
                + "                        btCancelar.setVisible(true);\n");

        String abre = "";
        String fecha = ")";
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            switch (aux[0]) {
                case "String":
                    abre = "";
                    fecha = "";
                    break;
                case "Date":
                    abre = "converteDatas.converteDeDateParaString(";
                    fecha = ")";
                    break;
                case "img":
                    codigo.add("String c = dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/\" + " + nomeDaClasseMin + ".getId() + \".png\";\n"
                            + "                    \n"
                            + "                    if (!" + nomeDaClasseMin + ".get" + st.plMaiusc(aux[1]) + "().equals(dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\")) {\n"
                            + "                        ImageIcon ii = imagemAjustada" + st.plMaiusc(aux[1]) + ".getImagemAjustada(c, tamX" + st.plMaiusc(aux[1]) + ", tamY" + st.plMaiusc(aux[1]) + ");\n"
                            + "                        lb" + st.plMaiusc(aux[1]) + ".setIcon(ii);\n"
                            + "                    } else {\n"
                            + "                        c = dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\";\n"
                            + "                        ImageIcon ii = imagemAjustada" + st.plMaiusc(aux[1]) + ".getImagemAjustada(c, tamX" + st.plMaiusc(aux[1]) + ", tamY" + st.plMaiusc(aux[1]) + ");\n"
                            + "                        lb" + st.plMaiusc(aux[1]) + ".setIcon(ii);\n"
                            + "                    }\n"
                            + "                    lbPath" + st.plMaiusc(aux[1]) + ".setText(c);\n");
                    break;
                default:
                    abre = "String.valueOf(";
                    fecha = ")";
            }
            if ("boolean".equals(aux[0])) {
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setSelected(" + nomeDaClasseMin + ".get" + st.plMaiusc(aux[1]) + "()==1?true:false);\n");
            } else if (!"img".equals(aux[0])) {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(" + abre + ""
                        + nomeDaClasseMin + ".get" + st.plMaiusc(aux[1]) + "())" + fecha + ";\n"
                        + "tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
            }
        }

        codigo.add("} else {//não achou na lista\n"
                + "                        //mostrar botão incluir\n"
                + "\n"
                + "                        btAdicionar.setVisible(true);\n"
                + "                        btAlterar.setVisible(false);\n"
                + "                        btExcluir.setVisible(false);\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (i != 0 && !"boolean".equals(aux[0]) && !"img".equals(aux[0])) {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n");
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
            } else if ("img".equals(aux[0])) {
                codigo.add("lb" + st.plMaiusc(aux[1]) + ".setText(\"\");\n"
                        + "\n"
                        + "                    ImageIcon ii = imagemAjustada" + st.plMaiusc(aux[1]) + ".getImagemAjustada(dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\", tamX" + st.plMaiusc(aux[1]) + ", tamY" + st.plMaiusc(aux[1]) + ");\n"
                        + "                    lb" + st.plMaiusc(aux[1]) + ".setIcon(ii);\n"
                        + "                    lbPath" + st.plMaiusc(aux[1]) + ".setText(dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\");");
            } else if ("boolean".equals(aux[0])) {
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setSelected(false);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
            }
        }
        codigo.add("                }\n"
                + "} catch (Exception extru" + erro_count + ") {\n"
                + "                    JOptionPane.showMessageDialog(null, \"Algo deu errado\");\n"
                + "                }"
                + "            }\n"
                + "        });");

        erro_count++;

        codigo.add("\n\n//listener adicionar\n");
        codigo.add("btAdicionar.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "            public void actionPerformed(ActionEvent e) {");
        aux = atributo.get(0).split(";");
        codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEnabled(false);\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (i == 1) {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n");
            }
            if ("img".equals(aux[0])) {
                codigo.add("temFoto" + st.plMaiusc(aux[1]) + " = \"Não\";\n");
            } else if ("boolean".equals(aux[0])) {
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
            }
        }
        codigo.add("btAdicionar.setVisible(false);\n"
                + "                btSalvar.setVisible(true);\n"
                + "                btCancelar.setVisible(true);\n"
                + "                btBuscar.setVisible(false);\n"
                + "                btListar.setVisible(false);\n"
                + "                acao = \"adicionar\";\n"
                + "            }\n"
                + "        });");

        codigo.add("\n\n//listener Salvar\n");
        codigo.add("btSalvar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "try{");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if ("boolean".equals(aux[0])) {
                codigo.add("Short " + aux[1] + "_escopo;\n"
                        + "                    if (check" + st.plMaiusc(aux[1]) + ".isSelected()) {\n"
                        + "                        " + aux[1] + "_escopo = (short)1;\n"
                        + "                    } else {\n"
                        + "                        " + aux[1] + "_escopo = (short)0;\n"
                        + "                    }"
                        + "                if (acao.equals(\"alterar\")) {\n");
            }
        }

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            fecha = ")";
            switch (aux[0]) {
                case "String":
                    abre = "";
                    fecha = "";
                    break;
                case "int":
                    abre = "Integer.valueOf(";
                    break;
                case "double":
                    abre = "Double.valueOf(";
                    break;
                case "long":
                    abre = "Long.valueOf(";
                    break;
                case "byte":
                    abre = "Byte.valueOf(";
                    break;
                case "short":
                    abre = "Short.valueOf(";
                    break;
                case "float":
                    abre = "Float.valueOf(";
                    break;
                case "char":
                    abre = "String.valueOf(";
                    fecha = ".charAt(0))";
                    break;
                case "boolean":
                    abre = "(" + aux[1] + "_escopo";
                    fecha = ")";
                    break;
                case "Date":
                    abre = "converteDatas.converteDeStringParaDate(";
                    break;
                case "img":
                    codigo.add("" + nomeDaClasseMin + ".setFoto(lbPath" + st.plMaiusc(aux[1]) + ".getText());\n"
                            + "                \n"
                            + "                String destino = dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/\";\n"
                            + "                destino = destino + " + nomeDaClasseMin + ".getId() + \".png\";\n"
                            + "                copiarArquivos" + st.plMaiusc(aux[1]) + ".copiar(lbPath" + st.plMaiusc(aux[1]) + ".getText(), destino);");
                    break;
                default:
                    abre = "DESCONHECIDO.valueOf(";
            }
            if ("boolean".equals(aux[0])) {
                codigo.add("" + nomeDaClasseMin + ".set" + st.plMaiusc(aux[1]) + abre + fecha + ";");
            } else if (!"img".equals(aux[0])) {
                codigo.add("" + nomeDaClasseMin + ".set" + st.plMaiusc(aux[1]) + "(" + abre + "tf" + st.plMaiusc(aux[1]) + ".getText())" + fecha + ";");
            }
        }
        codigo.add("dao" + nomeDaClasse + ".atualizar(" + nomeDaClasseMin + ");");
        codigo.add("} else { //acao == adicionar\n" + "                " + nomeDaClasseMin + " = new " + nomeDaClasse + "();\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            fecha = ")";
            switch (aux[0]) {
                case "String":
                    abre = "";
                    fecha = "";
                    break;
                case "int":
                    abre = "Integer.valueOf(";
                    break;
                case "double":
                    abre = "Double.valueOf(";
                    break;
                case "long":
                    abre = "Long.valueOf(";
                    break;
                case "byte":
                    abre = "Byte.valueOf(";
                    break;
                case "short":
                    abre = "Short.valueOf(";
                    break;
                case "float":
                    abre = "Float.valueOf(";
                    break;
                case "char":
                    abre = "String.valueOf(";
                    fecha = ".charAt(0))";
                    break;
                case "boolean":
                    abre = "(" + aux[1] + "_escopo";
                    fecha = ")";
                    break;
                case "Date":
                    abre = "converteDatas.converteDeStringParaDate(";
                    break;
                case "img":
                    codigo.add("" + nomeDaClasseMin + ".setFoto(lbPath" + st.plMaiusc(aux[1]) + ".getText());\n"
                            + "                \n"
                            + "                String destino = dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/\";\n"
                            + "                destino = destino + " + nomeDaClasseMin + ".getId() + \".png\";\n"
                            + "                copiarArquivos" + st.plMaiusc(aux[1]) + ".copiar(lbPath" + st.plMaiusc(aux[1]) + ".getText(), destino);");
                    break;
                default:
                    abre = "DESCONHECIDO.valueOf(";
            }
            if ("boolean".equals(aux[0])) {
                codigo.add("" + nomeDaClasseMin + ".set" + st.plMaiusc(aux[1]) + abre + fecha+";");
            } else if (!"img".equals(aux[0])) {
                codigo.add("" + nomeDaClasseMin + ".set" + st.plMaiusc(aux[1]) + "(" + abre + "tf" + st.plMaiusc(aux[1]) + ".getText())" + fecha + ";");
            }
        }

        codigo.add("dao" + nomeDaClasse + ".inserir(" + nomeDaClasseMin + "); "
                + "}"
                + "                btSalvar.setVisible(false);\n"
                + "                btCancelar.setVisible(false);\n"
                + "                btBuscar.setVisible(true);\n"
                + "                btListar.setVisible(true);");
        aux = atributo.get(0).split(";");
        codigo.add("                tf" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n"
                + "                tf" + st.plMaiusc(aux[1]) + ".setText(\"\");");

        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if ("img".equals(aux[0])) {
                codigo.add("lb" + st.plMaiusc(aux[1]) + ".setText(\"\");\n"
                        + "                ImageIcon ii = imagemAjustada" + st.plMaiusc(aux[1]) + ".getImagemAjustada(dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\", tamX" + st.plMaiusc(aux[1]) + ", tamY" + st.plMaiusc(aux[1]) + ");\n"
                        + "                lb" + st.plMaiusc(aux[1]) + ".setIcon(ii);\n"
                        + "                lbPath" + st.plMaiusc(aux[1]) + ".setText(dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\");");
            } else if ("boolean".equals(aux[0])) {
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setSelected(false);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n"
                        + "tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
            }
        }
        codigo.add("} catch (Exception macau" + erro_count + ") {\n"
                + "                    JOptionPane.showMessageDialog(null, \"Algo deu errado\");\n"
                + "                }"
                + "            }\n"
                + "        });\n");
        erro_count++;

        codigo.add("\n//listener Alterar\n");
        codigo.add("btAlterar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                btBuscar.setVisible(false);\n"
                + "                btAlterar.setVisible(false);\n");

        aux = atributo.get(0).split(";");
        codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (i == 1) {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".requestFocus();");
            }
            if ("img".equals(aux[0])) {
                codigo.add("temFoto" + st.plMaiusc(aux[1]) + " = \"Não\";\n");
            } else if("boolean".equals(aux[0])){
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
            }
        }
        codigo.add("btSalvar.setVisible(true);\n"
                + "                btCancelar.setVisible(true);\n"
                + "                btListar.setVisible(false);\n"
                + "                btExcluir.setVisible(false);\n"
                + "                acao = \"alterar\";\n"
                + "\n"
                + "            }\n"
                + "        });");

        codigo.add("\n//listener excluir\n");
        codigo.add("        btExcluir.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "\n"
                + "                int response = JOptionPane.showConfirmDialog(cp, \"Confirme a exclusão?\", \"Confirm\",\n"
                + "                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);\n"
                + "\n"
                + "                btExcluir.setVisible(false);\n");

        aux = atributo.get(0).split(";");
        codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
        codigo.add("tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if ("img".equals(aux[0])) {
                codigo.add("if (response == JOptionPane.YES_OPTION) {\n"
                        + "                    controle.excluir(" + nomeDaClasseMin + ");\n"
                        + "                }\n"
                        + "                \n"
                        + "                String cc = dirApp" + st.plMaiusc(aux[1]) + ";\n"
                        + "                File oArquivo = new File(cc.trim());\n"
                        + "                if (oArquivo.exists()) {\n"
                        + "                    oArquivo.delete();\n"
                        + "                    origem" + st.plMaiusc(aux[1]) + " = dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\";\n"
                        + "                    ImageIcon ii = imagemAjustada" + st.plMaiusc(aux[1]) + ".getImagemAjustada(dirApp" + st.plMaiusc(aux[1]) + " + \"/src/icones/std.png\", tamX" + st.plMaiusc(aux[1]) + ", tamY" + st.plMaiusc(aux[1]) + ");\n"
                        + "                    lb" + st.plMaiusc(aux[1]) + ".setIcon(ii);\n"
                        + "                } else {\n"
                        + "                    System.out.println(\"hahahahah não achei\");\n"
                        + "                }");
            } else if("boolean".equals(aux[0])){
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n");
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(true);\n");
            }
        }
        codigo.add("btAlterar.setVisible(false);\n"
                + "                if (response == JOptionPane.YES_OPTION) {\n"
                + "                    dao"+nomeDaClasse+".remover(" + nomeDaClasseMin + ");\n"
                + "                }\n"
                + "            }\n"
                + "        });");

        codigo.add("\n//listener listar\n");
        codigo.add("btListar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "List<" + nomeDaClasse + "> lista" + nomeDaClasse + " = dao" + nomeDaClasse + ".listInOrderNome();");

        s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s += "\"" + aux[1] + "\",";
        }
        s = s.substring(0, s.length() - 1);
        codigo.add("String[] colunas = new String[]{" + s + "};\n"
                + "    String[][] dados = new String[lista" + nomeDaClasse + ".size()][colunas.length];\n");

        codigo.add("String aux[];\n"
                + "                for (int i = 0; i < lista" + nomeDaClasse + ".size(); i++) {\n"
                + "                    aux = lista" + nomeDaClasse + ".get(i).toString().split(\";\");\n"
                + "                    for (int j = 0; j < colunas.length; j++) {\n"
                + "                        dados[i][j] = aux[j];\n"
                + "                    }\n"
                + "                }");

        codigo.add("cardLayout.show(pnSul, \"listagem\");\n"
                + "                scrollTabela.setPreferredSize(tabela.getPreferredSize());\n"
                + "                pnListagem.add(scrollTabela);\n"
                + "                scrollTabela.setViewportView(tabela);\n"
                + "                model.setDataVector(dados, colunas);\n"
                + "\n"
                + "                btAlterar.setVisible(false);\n"
                + "                btExcluir.setVisible(false);\n"
                + "                btAdicionar.setVisible(false);\n"
                + "\n"
                + "            }\n"
                + "        });");

        codigo.add("\n//listener Cancelar\n");
        codigo.add("btCancelar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                btCancelar.setVisible(false);");

        aux = atributo.get(0).split(";");
        codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n"
                + "tf" + st.plMaiusc(aux[1]) + ".requestFocus();\n"
                + "tf" + st.plMaiusc(aux[1]) + ".setEnabled(true);\n"
                + "tf" + st.plMaiusc(aux[1]) + ".setEditable(true);");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if ("img".equals(aux[0])) {
                codigo.add("");
            } else if("boolean".equals(aux[0])){
                codigo.add("check" + st.plMaiusc(aux[1]) + ".setEnabled(false);\n");
            } else {
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setText(\"\");\n");
                codigo.add("tf" + st.plMaiusc(aux[1]) + ".setEditable(false);\n");
            }
        }
        codigo.add("btBuscar.setVisible(true);\n"
                + "                btListar.setVisible(true);\n"
                + "                btSalvar.setVisible(false);\n"
                + "                btCancelar.setVisible(false);\n"
                + "\n"
                + "            }\n"
                + "        });");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if ("img".equals(aux[0])) {
                codigo.add("lb" + st.plMaiusc(aux[1]) + ".addMouseListener(new MouseListener() {\n"
                        + "            @Override\n"
                        + "            public void mouseClicked(MouseEvent e) {\n"
                        + "                JFileChooser fc" + i + " = new JFileChooser();\n"
                        + "                fc" + i + ".setFileSelectionMode(JFileChooser.FILES_ONLY);\n"
                        + "                if (fc" + i + ".showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {\n"
                        + "                    File img" + i + " = fc" + i + ".getSelectedFile();\n"
                        + "                    origem" + st.plMaiusc(aux[1]) + " = fc" + i + ".getSelectedFile().getAbsolutePath();\n"
                        + "                    try {\n"
                        + "                        lb" + st.plMaiusc(aux[1]) + ".setIcon(imagemAjustada" + st.plMaiusc(aux[1]) + ".getImagemAjustada(origem" + st.plMaiusc(aux[1]) + ", tamX" + st.plMaiusc(aux[1]) + ", tamY" + st.plMaiusc(aux[1]) + "));\n"
                        + "                        lbPath" + st.plMaiusc(aux[1]) + ".setText(origem" + st.plMaiusc(aux[1]) + ");\n"
                        + "                        temFoto" + st.plMaiusc(aux[1]) + " = \"sim\";\n"
                        + "                    } catch (Exception ee" + i + ") {\n"
                        + "                        JOptionPane.showMessageDialog(cp, \"Erro ao carregar a imagem <3\");\n"
                        + "                    }\n"
                        + "                }\n"
                        + "            }\n"
                        + "\n"
                        + "            @Override\n"
                        + "            public void mousePressed(MouseEvent e) {\n"
                        + "                System.out.println(\"\");\n"
                        + "            }\n"
                        + "\n"
                        + "            @Override\n"
                        + "            public void mouseReleased(MouseEvent e) {\n"
                        + "                System.out.println(\"\");\n"
                        + "            }\n"
                        + "\n"
                        + "            @Override\n"
                        + "            public void mouseEntered(MouseEvent e) {\n"
                        + "                System.out.println(\"\");\n"
                        + "            }\n"
                        + "\n"
                        + "            @Override\n"
                        + "            public void mouseExited(MouseEvent e) {\n"
                        + "                System.out.println(\"\");\n"
                        + "            }\n"
                        + "        });");
            }
        }

        codigo.add("\n//windão FECHAR O PROGRAMA\n");
        codigo.add("addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                //antes de sair, salvar a lista em armazenamento permanente\n"
                + "                // Sai da classe\n"
                + "                dispose();\n"
                + "            }\n"
                + "        });\n"
                + "\n"
                + "        setModal(true);\n"
                + "        pack();\n"
                + "        setResizable(false);\n"
                + "        setLocationRelativeTo(null);//centraliza na tela\n"
                + "        setVisible(true);");
        codigo.add("");

        codigo.add("}//fim do construtor de GUI\n");

        codigo.add("}//fim da classe\n");

        codigo.add("");
        codigo.add("");
        codigo.add("");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/GUIs/" + nomeDaClasse + "GUI.java", codigo);
    }

}
