package main.queues;

public class Call {
    /** Identyfikator zgloszenia */
    private final int id;

    /** Czas trwania obslugi zgloszenia */
    private final int duration;

    /** Czas wygenerowania zgloszenia */
    private final long startTime;

    /**
     * Konstruktor
     * @param id = identyfikator zgloszenia
     * @param duration = czas trwania obslugi zgloszenia
     */
    public Call(int id, int duration) {
        assert duration >= 0 : "czas obslugi nie moze byc ujemny";

        this.id = id;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
    }

    public void answer() {
        System.out.println(this + " rozpoczęcie obsługi po oczekiwaniu "
                                + (System.currentTimeMillis() - startTime)
                                + " milisekund");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            // Ignoruj
        }
    }

    public String toString() {
        return "Zgloszenie " + id;
    }
}
