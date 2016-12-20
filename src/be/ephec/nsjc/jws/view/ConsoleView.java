package be.ephec.nsjc.jws.view;

import be.ephec.nsjc.jws.controller.JWSController;
import be.ephec.nsjc.jws.model.HTTPTrace;

import java.util.Observable;
import java.util.Scanner;

public class ConsoleView implements AbstractView{

    private Scanner sc;
    private boolean running = true;
    private HTTPTrace trace;
	private JWSController controller;

    public ConsoleView(HTTPTrace trace, JWSController controller) {
        this.sc = new Scanner(System.in);
        this.trace = trace;
        new Thread(new InputReader()).start();
        trace.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int count = (Integer) arg;
        if (this.trace.hasMadeResponse()) {
            System.out.println(this.trace.getResponseList().get(count));
        } else {
            System.out.println(this.trace.getRequestList().get(count));
        }
    }

    @Override
    public void log(String log) {
        System.out.println(log);
    }

    private class InputReader implements Runnable {

        @Override
        public void run() {
            while (running) {
                try {
                    ConsoleView.this.controller.handleInput(sc.nextLine().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
