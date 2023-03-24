package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiarArquivos {

    public int copiar(String origem, String destino) {
       // System.out.println("\nOrigem >"+origem+ " \nDestino >"+destino);
        try {
            InputStream in;
            in = new FileInputStream(origem);
            OutputStream out;
            byte[] buf = new byte[1024];
            out = new FileOutputStream(destino);
            int len;
            try {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch (IOException ex) {
                System.out.println("Erro na cópia");
            }
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                System.out.println("Erro na cópia");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro na cópia - arquivo não encontrado");
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        //copiar icones
        String caminho = "/home/radames/ProjetosNetbeans/Exemplo";
        File listaIcones = new File("src/icones");
        if (listaIcones.exists()) {

            File[] arqs = listaIcones.listFiles();
            CopiarArquivos copiaImagem = new CopiarArquivos();
            for (int i = 0; i < arqs.length; i++) {
                //   System.out.print("origem [" + arqs[i].getAbsolutePath()+"]");
                //   System.out.println("  =>destino [" + caminho+"/src/icones/"+arqs[i].getName()+"]");
                copiaImagem.copiar(arqs[i].getAbsolutePath(), caminho + "/src/icones/" + arqs[i].getName());
            }

        }
    }
}
