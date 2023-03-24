/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */
public class Reminder {

    Timer timer;

    public Reminder(int seconds, String tarefa) {
        timer = new Timer();
        timer.schedule(new RemindTask(tarefa), seconds * 1000);
    }

    class RemindTask extends TimerTask {

        private String msg;

        public RemindTask(String msg) {
            this.msg = msg;
        }

        public void run() {
            System.out.println(msg + " finalizou " + new Date());
            timer.cancel(); //Terminate the timer thread
        }
    }

    public static void main(String args[]) {
        new Reminder(5, "T1");
        System.out.println("T1 iniciou " + new Date());
        new Reminder(10, "T2");
        System.out.println("T2 iniciou " + new Date());
    }
}
