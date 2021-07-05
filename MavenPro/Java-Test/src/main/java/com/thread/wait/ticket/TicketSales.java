package com.thread.wait.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketSales {
    public static final Integer MAX = 100;
    private static AtomicInteger count = new AtomicInteger(0);
    public static int intCount = 0;

    public static void main(String[] args) {
        Tickets tickets = new Tickets(new ArrayList<>());
        new Thread(new Produce(tickets)).start();
        new Thread(new Produce(tickets)).start();

        new Thread(new Sale(tickets)).start();
        new Thread(new Sale(tickets)).start();
    }
}

class Produce implements Runnable {
    private Tickets tickets;
    Produce(Tickets tickets){
        this.tickets = tickets;
    }
    @Override
    public void run() {
        synchronized (this.tickets) {
            while (true) {
                if(TicketSales.intCount >= TicketSales.MAX)
                    break;

                if (this.tickets.getContainer().size() > 5) {
                    try {
                        tickets.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    int ticketNum = TicketSales.intCount++;
                    System.out.println("Produce Ticket "+ticketNum);
                    tickets.getContainer().add(ticketNum);
                    tickets.notifyAll();
                }
            }
        }
    }
}

class Sale implements Runnable {
    private Tickets tickets;
    Sale(Tickets tickets){
        this.tickets = tickets;
    }
    @Override
    public void run() {
        synchronized (this.tickets) {
            while (true) {
                if (this.tickets.getContainer().size() <= 0) {
                    try {
                        tickets.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    int ticketNum = this.tickets.getContainer().remove(this.tickets.getContainer().size()-1);
                    System.out.println("Sale Ticket "+ticketNum);
                    tickets.notifyAll();
                }
            }
        }
    }
}

class Tickets{
    private List<Integer> container;

    Tickets(List<Integer> container){
        this.container = container;
    }

    public List<Integer> getContainer() {
        return container;
    }
}
