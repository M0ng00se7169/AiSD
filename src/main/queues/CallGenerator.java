package main.queues;

public class CallGenerator {

    /** Macierzyste centrum obslugi */
    private final CallCenter callCenter;

    /** Ogólna liczba zgloszen do wygenerowania */
    private final int numberOfCalls;

    /** Maksymalny czas obslugi zgloszenia */
    private final int maxCallDuration;

    /** Maksymalny odstep czasowy między kolejnymi zgloszeniami */
    private final int maxCallInterval;

    /**
     * Konstruktor
     * @param callCenter = Macierzyste centrum obslugi
     * @param numberOfCalls = Ogólna liczba zgloszen do wygenerowania
     * @param maxCallDuration = Maksymalny czas obslugi zgloszenia
     * @param maxCallInterval = Maksymalny odstep czasowy między kolejnymi zgloszeniami
     */
    public CallGenerator(CallCenter callCenter, int numberOfCalls, int maxCallDuration, int maxCallInterval) {
        assert callCenter != null : "nie okreslono macierzystego centrum obslugi";
        assert numberOfCalls > 0 : "liczba zgloszen msui byc dodatnia";
        assert maxCallDuration > 0 : "czas obslugi zgloszenia musi byc dodatni";
        assert maxCallInterval > 0 : "czas między zgloszeniami musi byc dodatni";

        this.callCenter = callCenter;
        this.numberOfCalls = numberOfCalls;
        this.maxCallDuration = maxCallDuration;
        this.maxCallInterval = maxCallInterval;
    }

    public void generateCalls() {
        for (int i = 0; i < numberOfCalls; ++i) {
            sleep();
            callCenter.accept(new Call(i, (int) (Math.random() * maxCallDuration)));
        }
    }

    private void sleep() {
        try {
            Thread.sleep((int)(Math.random() * maxCallInterval));
        } catch (InterruptedException e) {
            // Ignoruj
        }
    }
}
