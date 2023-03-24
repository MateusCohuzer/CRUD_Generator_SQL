/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Timer;

class Ping {

    public Ping() {
        ActionListener ativar = (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("passou....");
            }
        });
        Timer time = new Timer(1000, ativar);
        time.start();

    }
}

public class Temporizador {

    public static void main(String args[]) {
        Ping p = new Ping();
        System.out.println("T1 iniciou " + new Date());
    }
}
