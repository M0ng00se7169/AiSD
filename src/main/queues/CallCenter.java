package main.queues;

import main.iteration.Iterator;
import main.lists.ArrayList;
import main.lists.List;

public class CallCenter {

    /** Kolejka zgloszen */
    private final Queue calls = new BlockingQueue(new ListFifoQueue());

    /** Liczba dzialajacych konsultantow */
    private final int numberOfAgents;

    /** Lista wątkow konsultantow
     * pusta, gdy centrum zamknięte i nie pusta gdy otwarte
     */
    private final List threads;

    /**
     * Konstruktor
     * @param numberOfAgents = liczba dzialajacych konsultantow
     */
    public CallCenter(int numberOfAgents) {
        this.threads = new ArrayList(numberOfAgents);
        this.numberOfAgents = numberOfAgents;
    }

    public void open() {
        assert threads.isEmpty() : "Centrum obslugi jest juz otwarte";

        System.out.println("Otwieranie centrum obslugi");

        for (int i = 0; i < numberOfAgents; i++) {
            Thread thread = new Thread(new CustomerServiceAgent(i, calls));

            thread.start();
            threads.add(thread);
        }

        System.out.println("Centrum obsługi otwarte");
    }

    public void accept(Call call) {
        assert !threads.isEmpty() : "Centrum obslugi jest zamknięte";

        calls.enqueue(call);

        System.out.println(call + " umieszczone w kolejce");
    }

    public void close() {
        assert !threads.isEmpty() : "Centrum obslugi jest juz zamkniete";

        System.out.println("Zamykanie centrum obslugi");

        // Wyslanie "zgloszenia konczacego" do każdego z agentow
        for (int i = 0; i < numberOfAgents; ++i) {
            accept(CustomerServiceAgent.GO_HOME);
        }

        Iterator iterator = threads.iterator();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            waitForTermination((Thread) iterator.current());
        }

        threads.clear();

        System.out.println("Centrum obslugi jest zamkniete");
    }

    private void waitForTermination(Thread current) {
        try {
            current.join();
        } catch (InterruptedException e) {
            // Ignoruj
        }
    }
}
