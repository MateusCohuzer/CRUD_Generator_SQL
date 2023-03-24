package tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * Na classe que chamará a classe GUI_Login deve-se usar esse código:
 * 
 *      GUI_Login login = new GUI_Login(this, "Login");
        if (!login.isVisible()) {
            login.setVisible(true);
        }


        if (!login.isLoginValido()) {
            JOptionPane.showMessageDialog(this, "Login inválido. Bye...");            
            System.exit(0);
        }
 */
public class GUI_Login extends JDialog {

    JLabel lbNome = new JLabel("Nome");
    JLabel lbSenha = new JLabel("Senha");
    JTextField tfNome = new JTextField(50);
    JPasswordField tfSenha = new JPasswordField(6);
    JButton btOK = new JButton("Ok");
    JButton btCancel = new JButton("Cancelar");
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();

    JPanel pnCentroL1 = new JPanel();
    JPanel pnCentroL2 = new JPanel();
    JPanel pnCentroL3 = new JPanel();
    String[] nomes = {"Daniela", "Giovanna", "Ruth"};
    JComboBox cb = new JComboBox(nomes);
    private boolean loginValido = false;

    public GUI_Login(JFrame frame, String title) {
        super(frame, title, false);
        setModal(true);
        setSize(250, 150);
        setTitle(title);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        pnCentro.setLayout(new GridLayout(3, 1));
        pnCentroL1.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnCentroL2.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnCentroL3.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnCentroL1.add(lbNome);
        pnCentroL1.add(cb);
        pnCentroL2.add(lbSenha);
        pnCentroL2.add(tfSenha);
        pnCentroL3.add(btOK);
        pnCentroL3.add(btCancel);
        pnCentro.add(pnCentroL1);
        pnCentro.add(pnCentroL2);
        pnCentro.add(pnCentroL3);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setLocationRelativeTo(frame);
        btOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Login liberado....");
                //buscar no db a senha
                loginValido = true;
                setVisible(false);
            }
        });
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //buscar no db a senha
                loginValido = false;
                setVisible(false);
            }
        });

    }

    public boolean isLoginValido() {
        return loginValido;
    }
}
