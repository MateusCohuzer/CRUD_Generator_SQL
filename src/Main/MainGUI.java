package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.CopiarArquivos;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Batichotti Silva | 02/10/2022 - 14:20:13
 */
public class MainGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnNorte1 = new JPanel();
    JPanel pnNorte2 = new JPanel();
    JPanel pnNorte3 = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btEscolher = new JButton("Escolher");
    JButton btEstrutura = new JButton("Gerar Estrutura");
    JButton btDAO = new JButton("Gerar DAOs");
    JButton btGUI = new JButton("Gerar GUI");
    JButton btAbrirEntidade = new JButton("Abrir Entidades");
    JButton btSalvarEntidade = new JButton("Salvar Entidades");
    JButton btGerar = new JButton("Gerar Todos");
    JButton btMain = new JButton("Gerar Main");

    JButton btImg = new JButton("Img");

    JButton btByte = new JButton("byte");
    JButton btShort = new JButton("short");
    JButton btInt = new JButton("int");
    JButton btLong = new JButton("long");
    JButton btFloat = new JButton("float");
    JButton btDouble = new JButton("double");
    JButton btChar = new JButton("char");
    JButton btBoolean = new JButton("boolean");
    JButton btString = new JButton("String");
    JButton btDate = new JButton("Date");

////////////////////////////////// - MUTÁVEL - //////////////////////////////////
    JLabel lbNome = new JLabel("Nome da Classe de Entidade:");
    JLabel lbPath = new JLabel("Caminho:");
    JTextField tfNome = new JTextField(30);
    JTextField tfPath = new JTextField(30);
    JTextArea taCg = new JTextArea(50, 50);

    private JFileChooser caixaDeDialogo = new JFileChooser();
    private String caminho = "";
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    List<String> texto = new ArrayList();

    public MainGUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pnCentro.setBackground(Color.WHITE);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.setLayout(new BorderLayout());
        setTitle("CRUD - Main");

        cp.add(pnNorte, BorderLayout.NORTH);
        pnNorte.add(pnNorte1, BorderLayout.NORTH);
        pnNorte.add(pnNorte2, BorderLayout.CENTER);
        pnNorte.add(pnNorte3, BorderLayout.SOUTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnNorte1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnNorte2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnNorte3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnNorte2.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnNorte3.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnSul.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnNorte1.add(lbPath);
        pnNorte1.add(tfPath);
        pnNorte1.add(btEscolher);
        pnNorte1.add(btEstrutura);

        pnNorte2.add(lbNome);
        pnNorte2.add(tfNome);
        pnNorte2.add(btAbrirEntidade);
        pnNorte2.add(btSalvarEntidade);

        pnNorte3.add(btBoolean);
        pnNorte3.add(btByte);
        pnNorte3.add(btChar);
        pnNorte3.add(btDate);
        pnNorte3.add(btDouble);
        pnNorte3.add(btFloat);
        pnNorte3.add(btImg);
        pnNorte3.add(btInt);
        pnNorte3.add(btLong);
        pnNorte3.add(btShort);
        pnNorte3.add(btString);

        pnCentro.add(taCg);
        taCg.setEnabled(true);
        taCg.setVisible(true);
        taCg.setEditable(true);
        taCg.setLineWrap(true);
        taCg.setFont(new Font("Arial", Font.BOLD, 24));

        pnSul.add(btDAO);
        pnSul.add(btGUI);
        pnSul.add(btMain);
        pnSul.add(btGerar);

        texto = manipulaArquivo.abrirArquivo(".\\ProjetoEscolhido.txt");
        if (texto.size() > 0) {
            caminho = texto.get(0);
            tfPath.setText(caminho);
        }

        //COLORS
        pnNorte1.setBackground(Color.GRAY);
        pnNorte2.setBackground(Color.GRAY);
        pnNorte3.setBackground(Color.GRAY);
        pnSul.setBackground(Color.GRAY);

        btBoolean.setBackground(Color.WHITE);
        btByte.setBackground(Color.WHITE);
        btChar.setBackground(Color.WHITE);
        btDAO.setBackground(Color.WHITE);
        btDate.setBackground(Color.WHITE);
        btDouble.setBackground(Color.WHITE);
        btEscolher.setBackground(Color.WHITE);
        btEstrutura.setBackground(Color.WHITE);
        btFloat.setBackground(Color.WHITE);
        btGUI.setBackground(Color.WHITE);
        btGerar.setBackground(Color.WHITE);
        btImg.setBackground(Color.WHITE);
        btInt.setBackground(Color.WHITE);
        btLong.setBackground(Color.WHITE);
        btShort.setBackground(Color.WHITE);
        btString.setBackground(Color.WHITE);

        btMain.setBackground(Color.WHITE);
        btAbrirEntidade.setBackground(Color.WHITE);
        btSalvarEntidade.setBackground(Color.WHITE);

        //LISTENERS
        btEscolher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("DIRETÓRIO", "..", "..");
                caixaDeDialogo.setFileFilter(filter);
                caixaDeDialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file = new File(caminho);
                if (file.exists()) {
                    caixaDeDialogo.setCurrentDirectory(file);
                } else {
                    file = new File("D:\\Mateus CohuzEr\\Documents\\NetBeansProjects\\GeradorRaposasB");
                    if (file.exists()) {
                        caixaDeDialogo.setCurrentDirectory(file);
                    } else {
                        caixaDeDialogo.setCurrentDirectory(null);
                    }
                }
                if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    caminho = caixaDeDialogo.getSelectedFile().getAbsolutePath();
                    tfPath.setText(caminho);
                    texto.clear();
                    texto.add(caminho);
                    manipulaArquivo.salvarArquivo("ProjetoEscolhido.txt", texto);
                }
            }
        });

        btEstrutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = tfPath.getText();
                List<String> listaString = new ArrayList<>();
                listaString.clear();
                listaString.add("/src/" + "Entidades");
                listaString.add("/src/" + "GUIs");
                listaString.add("/src/" + "tools");
                listaString.add("/src/" + "Main");
                listaString.add("/src/" + "DAOs");
                for (String pacote : listaString) {
                    File pac = new File(src + pacote);
                    if (!pac.exists()) {
                        new File(src + pacote).mkdirs();
                    }
                }


                //copia as tools
                File listaFerramentas = new File("D:\\Mateus Cohuzer\\Documents\\NetBeansProjects\\GeradorSQL\\src\\tools");
                if (listaFerramentas.exists()) {
                    File[] arqs = listaFerramentas.listFiles();
                    CopiarArquivos copiarArquivos = new CopiarArquivos();
                    for (int i = 0; i < arqs.length; i++) {
                        copiarArquivos.copiar(arqs[i].getAbsolutePath(), caminho + "/src" + "/tools/" + arqs[i].getName());
                    }
                }
            }
        });

        btSalvarEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = taCg.getText();
                StringTokenizer st = new StringTokenizer(s);
                texto.clear();
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    line = line.trim();
                    if (!line.equals("")) {
                        texto.add(line + System.lineSeparator());
                    }
                }
                manipulaArquivo.salvarArquivo("src/txts/" + tfNome.getText() + ".txt", texto);
            }
        });

        btAbrirEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("src/txts/" + tfNome.getText() + ".txt");
                texto.clear();
                taCg.setText("");
                if (file.exists()) {
                    texto = manipulaArquivo.abrirArquivo("src/txts/" + tfNome.getText() + ".txt");
                    for (String string : texto) {
                        taCg.append(string + System.lineSeparator());
                    }
                }
            }
        });

        tfNome.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String s = tfNome.getText();
                String nomeAjustado = s;
                if (!s.trim().equals("")) {
                    nomeAjustado = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
                }
                tfNome.setText(nomeAjustado);
                tfNome.setBackground(Color.WHITE);
            }
        });

        btDAO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNome.getText();
                btSalvarEntidade.doClick();
                caminho = tfPath.getText();
                String oArquivo = "src/txts/" + tfNome.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarDAOs gerarDAOs = new GerarDAOs(nomeDaClasse, atributo, caminho);
                }
            }
        });

        btGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNome.getText();
                btSalvarEntidade.doClick();
                caminho = tfPath.getText();
                String oArquivo = "src/txts/" + tfNome.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseGui gerarClasseGUI = new GerarClasseGui(nomeDaClasse, atributo, caminho);
                }
            }
        });

        btMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNome.getText();
                btSalvarEntidade.doClick();
                caminho = tfPath.getText();
                String oArquivo = "src/txts/" + tfNome.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseMain gerarClasseMain = new GerarClasseMain(nomeDaClasse, atributo, caminho);
                }
            }
        });

        btGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btEstrutura.doClick();
                btSalvarEntidade.doClick();
                btDAO.doClick();
                btGUI.doClick();
                btMain.doClick();
            }
        });

        taCg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                taCg.setText(removerAcentos(taCg.getText()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("");
            }
        });

        taCg.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String s = taCg.getText().replaceAll("[^a-zA-Z0-9-;\\r\\n]", "_");
                taCg.setText(s);
                taCg.setText(removerAcentos(taCg.getText()));
            }

            @Override
            public void focusLost(FocusEvent e) {
                String s = taCg.getText().replaceAll("[^a-zA-Z0-9-;\\r\\n]", "_");
                taCg.setText(s);
                taCg.setText(removerAcentos(taCg.getText()));
            }
        });

        setModal(true);
        setSize(1080, 720);
        setResizable(false);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }//fim do construtor de GUI

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}//fim da classe
