package tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
//autor:radames

public class MyTools {

    public String formatarDecimais(String x, int decimais) {
        Locale ptBR = new Locale("pt", "BR");
        String d = "";
        for (int i = 0; i < decimais; i++) {
            d += "0";
        }
        if (d.length() > 0) {
            d = "." + d;
        }
        try {
            DecimalFormat df = new DecimalFormat("###,###,###,##0" + d, new DecimalFormatSymbols(ptBR));
            x = df.format(Double.valueOf(x));
        } catch (Exception e) {
            x = null;
        }
        return x;
    }
}
