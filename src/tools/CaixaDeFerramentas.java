package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author radames
 */
public class CaixaDeFerramentas {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public CaixaDeFerramentas() {
        simpleDateFormat.setLenient(false);//faz com que datas erradas sejam detectadas
    }

    public String converteDeDateParaString(Date data) {
        try {
            return simpleDateFormat.format(data); //converte a data para string
        } catch (Exception e) {
            return null;//se algo estiver errado na data, retorne null
            //tem que tratar o erro na classe que chamou
        }
    }

    public Date converteDeStringParaDate(String s) {
        try {
            return simpleDateFormat.parse(s);//converte
        } catch (Exception e) {
            return null;// se algo estiver errado, retorne null
        }
    }

    /* métodos para Persistência  
     
         
     é melhor colocar somente o nome do arquivo no parâmetro caminho (ao chamar o método)
     pois o arquivo .csv será criado dentro da pasta do projeto por não ter
     sido informado nenhum caminho adicional (pasta)
         
     para que os métodos abrir e salvar funcionem corretamente devem existir:
     um método toString na classe modelo, parecido com o método abaixo.
        
     @Override
     public String toString() {
     return id + ";" + nome + ";" + Main.sdf.format(dataDeNascimento) + ";" + altura;
     }
    
     e na classe de controle, tem que haver um método que retorna uma
     lista de String (List<String> listaCSV)
    
    
     public List<String> getListaCSV() {
     List<String> listaCSV = new ArrayList<>();
     for (int i = 0; i < lista.size(); i++) {
        listaCSV.add(lista.get(i).toString());
     }
     return listaCSV;
     }
     */
    public List<String> abrirArquivo(String caminho) {
        List<String> texto = new ArrayList<>();
        File arq = new File(caminho);
        if (arq.exists()) {
            try {
                FileReader arquivo = new FileReader(caminho);
                BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);
                String linha = conteudoDoArquivo.readLine();
                while (linha != null) {
                    texto.add(linha);
                    linha = conteudoDoArquivo.readLine();
                }
                conteudoDoArquivo.close();
            } catch (Exception e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            texto = null;
        }

        return texto;
    }

    public int salvarArquivo(String caminho, List<String> texto) {

        try {
            FileWriter arquivo = new FileWriter(caminho);
            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
            for (int i = 0; i < texto.size(); i++) {
                conteudoDoArquivo.write(texto.get(i) + System.getProperty("line.separator"));
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            //  System.err.println("Error: " + e.getMessage());
            return -1; //houve erro
        }
        return 1;
    }
}
