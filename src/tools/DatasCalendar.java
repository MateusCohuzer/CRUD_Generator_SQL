package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author radames
 */
public class DatasCalendar {

    public DatasCalendar() {
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Hoje = " + sd.format(d));
        String dataAuxiliar = sd.format(d);
        String[] vetorDiaMesAno = dataAuxiliar.split("/");

        int dia = Integer.parseInt(vetorDiaMesAno[0]);
        int mes = Integer.parseInt(vetorDiaMesAno[1]);
        int ano = Integer.parseInt(vetorDiaMesAno[2]);

        Calendar c = new GregorianCalendar(ano, mes - 1, dia);

        SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm:ss");

        Calendar h = new GregorianCalendar(2013, Calendar.JANUARY, 1, 0, 0, 0);
        h.add(java.util.Calendar.MINUTE, 121);
        h.add(java.util.Calendar.SECOND, 10);

        System.out.println("hora " + formataHora.format(h.getTime()));

        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.out.println(" teste data do sistema " + date_format.format(creationDate));

    }

}
