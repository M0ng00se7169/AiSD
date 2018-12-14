package main.queues;

public class CallCenterSimulator {

    /** liczba spodziewanych parametrow wywolania */
    private static final int NUMBER_OF_ARGS = 4;

    /** indeks parametru oznaczającego liczbe konsultantow */
    private static final int NUMBER_OF_AGENTS_ARG = 0;

    /** indeks parametru oznaczającego liczbe generowanych zgloszen */
    private static final int NUMBER_OF_CALLS_ARG = 1;

    /** indeks parametru oznaczającego maksymalny czas obslugi zgloszenia */
    private static final int MAX_CALL_DURATION_ARG = 2;

    /** indeks parametru oznaczającego maksymalny odstęp czasowy
     *  między kolejnymi zgloszeniami */
    private static final int MAX_CALL_INTERVAL_ARG = 3;

    private CallCenterSimulator(){

    }

    public static void main(String[] args) {
        assert args != null : "nie podano argumentow";

        if (args.length != NUMBER_OF_ARGS) {
            System.out.println("Wywolanie: CallGenerator <konsultanci><zgloszenia>"
                                + "<czas obslugi><odstep czasowy>");
            System.exit(-1);
        }
        System.out.println(args.length);
        CallCenter callCenter = new CallCenter(Integer.parseInt(args[NUMBER_OF_AGENTS_ARG]));

        CallGenerator callGenerator = new CallGenerator(callCenter,
                                            Integer.parseInt(args[NUMBER_OF_CALLS_ARG]),
                                            Integer.parseInt(args[MAX_CALL_DURATION_ARG]),
                                            Integer.parseInt(args[MAX_CALL_INTERVAL_ARG]));
        callCenter.open();

        try {
            callGenerator.generateCalls();
        } finally {
            callCenter.close();
        }
    }
}
