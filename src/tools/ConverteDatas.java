package tools;

import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author radames
 *
 */
public class ConverteDatas {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ConverteDatas() {

        simpleDateFormat.setLenient(false);

    }

    public String converteDeDateParaString(Date data) {

        try {

            return simpleDateFormat.format(data);

        } catch (Exception e) {

            return null;
        }

    }

    public boolean verificaData(String data) {

        boolean booleano = true;
        String sdf;
        Date data_f;
        try {
            data_f = converteDeStringParaDate(data);
            sdf = simpleDateFormat.format(data_f);
        } catch (Exception e) {
            booleano = false;
        }
        return booleano;

    }

    public Date converteDeStringParaDate(String s) {

        try {

            return simpleDateFormat.parse(s);//converte

        } catch (Exception e) {

            return null;// se algo estiver errado, retorne null
        }
    }

    public int getDiaDoAno(Date data) {
        String ano = simpleDateFormat.format(data).substring(6);
        ano = "31/12/"+ano;
        Date data1 = converteDeStringParaDate(ano);
        SimpleDateFormat SDF_YearDay = new SimpleDateFormat("D");
        String data2 = SDF_YearDay.format(data1);
        int dia = Integer.valueOf(data2);
        return dia;
    }

}

