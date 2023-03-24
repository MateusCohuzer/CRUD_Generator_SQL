package tools;

// @author Radames
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JanelaPesquisarEmbutida extends JPanel {

    private String valorRetornado = "";
    private List<String> lista;

    public JanelaPesquisarEmbutida(List<String> lista) {//esse construtor é importante para receber dados 
        this.lista = lista;

    }

    public String getValorRetornado() {

        return valorRetornado;
    }

    public JPanel getThis() {
        //this.setBackground(Color.blue);
        Container containerLista = new JPanel();

        this.setBackground(Color.red);

        this.setLayout(new BorderLayout());
        // populate list
        final FilteredJList list = new FilteredJList(containerLista);
        for (int i = 0; i < lista.size(); i++) {
            list.addItem(lista.get(i));
        }
        containerLista.setLayout(new GridLayout(1, 1));
        containerLista.add(list);
        // add to gui
        JPanel painelDireita = new JPanel();
        painelDireita.setLayout(new GridLayout(3, 1));

        painelDireita.add(new JLabel());
        JScrollPane pane = new JScrollPane(containerLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BorderLayout());
        painelCentro.add(list.getFilterField(), BorderLayout.NORTH);
        painelCentro.add(pane, BorderLayout.CENTER);
        this.add(painelCentro, BorderLayout.CENTER);
        this.add(painelDireita, BorderLayout.EAST);
        this.add(new JLabel("<Clic duplo = seleciona>"), BorderLayout.SOUTH);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { //clic duplo
                    valorRetornado = (String) list.getSelectedValue();
                }
            }
        });

        return this;

    }

}
