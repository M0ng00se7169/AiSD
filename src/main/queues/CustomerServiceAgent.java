package main.queues;

public class CustomerServiceAgent implements Runnable {
    /**
     *  Zgloszenie sygnalizujace konsultantowi,
     *  że następnych zgloszeń już nie będzie
     */
    public static final Call GO_HOME = new Call(-1, 0);

    /** Identyfikator konsultanta */
    private final int id;

    /** Kolejka zgloszen */
    private final Queue calls;

    /**
     * Konstruktor
     * @param id = identyfikator konsultanta
     * @param calls = kolejka zgloszen
     */
    public CustomerServiceAgent(int id, Queue calls) {
        assert calls != null : "Nie okreslono kolejki zgloszen";

        this.id = id;
        this.calls = calls;
    }

    @Override
    public void run() {
        System.out.println(this + " obecny");

        while(true) {
            System.out.println(this + " oczekuje");

            Call call = (Call) calls.dequeue();
            System.out.println(this + " obsluguje " + call);

            if (call == GO_HOME) {
                break;
            }

            call.answer();
        }

        System.out.println(this + " zakonczyl prace");
    }

    public String toString() {
        return "Konsultant " + id;
    }
}
