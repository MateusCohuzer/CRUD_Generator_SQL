/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

class AnnoyingBeep {

    Toolkit toolkit;
    Timer timer;

    public AnnoyingBeep() {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(),
                0, //initial delay
                5 * 1000);  //subsequent rate
    }

    class RemindTask extends TimerTask {

        int numWarningBeeps = 30;

        public void run() {
            if (numWarningBeeps > 0) {
                toolkit.beep();
                System.out.println(1000);
                numWarningBeeps--;
            } else {
                toolkit.beep();
                System.out.println("Time's up!");
                //timer.cancel(); // Not necessary because
                // we call System.exit
                System.exit(0);   // Stops the AWT thread 
                // (and everything else)
            }
        }
    }

}

public class RepetirTarefaDeTemposEmTempos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AnnoyingBeep();
    }
}
