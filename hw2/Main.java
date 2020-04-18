package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static HashMap<Lab,HashMap<StudyGroup,Integer>> tally = new HashMap<>();

    public static void log(final String string) {
        synchronized (System.out) {
            System.out.println(string);
        }
    }

    public static void logInit(final int id, final StudyGroup studyGroup) {
        log("Student " + id + ": with group " + studyGroup.getName() + " in room " + studyGroup.getLab().getName()
                + " with capacity " + studyGroup.getLab().getCapacity());

        synchronized (tally) {
            tally.putIfAbsent(studyGroup.getLab(), new HashMap<>());
            tally.get(studyGroup.getLab()).putIfAbsent(studyGroup, 0);
        }
    }

    public static void logStudy(final int id, final StudyGroup studyGroup) {
        final StringBuilder logstr = new StringBuilder("Student " + id + " studying: ");

        synchronized (tally) {

            final HashMap<StudyGroup, Integer> roomTally = tally.get(studyGroup.getLab());
            roomTally.put(studyGroup, roomTally.get(studyGroup) + 1);

            for (final Map.Entry<Lab, HashMap<StudyGroup, Integer>> entry : tally.entrySet()) {

                logstr.append(entry.getKey().getName() + ": ");

                int total = 0;
                boolean occupied = false;

                for (final int count : entry.getValue().values()) {
                    logstr.append(count + " ");

                    total += count;
                    if (count != 0 && !occupied) {
                        occupied = true;
                    } else if (count != 0 && occupied) {
                        log(logstr.toString());
                        throw new Error("Implementation Error");
                    }
                }

                if (total > entry.getKey().getCapacity() || total < 0) {
                    log(logstr.toString());
                    throw new Error("Implementation Error");
                }
            }

        }
        log(logstr.toString());
    }

    public static void logDone(final int id, final StudyGroup studyGroup) {
        final StringBuilder logstr = new StringBuilder("Student " + id + " finished: ");

        synchronized (tally) {

            final HashMap<StudyGroup, Integer> roomTally = tally.get(studyGroup.getLab());
            roomTally.put(studyGroup, roomTally.get(studyGroup) - 1);

            for (final Map.Entry<Lab, HashMap<StudyGroup, Integer>> entry : tally.entrySet()) {

                logstr.append(entry.getKey().getName() + ": ");

                int total = 0;
                boolean occupied = false;

                for (final int count : entry.getValue().values()) {
                    logstr.append(count + " ");

                    total += count;
                    if (count != 0 && !occupied) {
                        occupied = true;
                    } else if (count != 0 && occupied) {
                        log(logstr.toString());
                        throw new Error("Implementation Error");
                    }
                }

                if (total > entry.getKey().getCapacity() || total < 0) {
                    log(logstr.toString());
                    throw new Error("Implementation Error");
                }
            }
        }
        log(logstr.toString());
    }

    public static void main(final String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(20);

        final Lab ismailAbi = new Lab("ismailAbi", 3);
        final Lab dijital = new Lab("dijital", 2);

        final StudyGroup quietOnes = new StudyGroup("quietOnes", dijital);
        final StudyGroup ruleBreakers = new StudyGroup("ruleBreakers", dijital);
        final StudyGroup seniorProjectProgrammers = new StudyGroup("seniorProjectProgrammers", ismailAbi);
        final StudyGroup slackers = new StudyGroup("slackers", ismailAbi);

        final StudyGroup[] studyGroups = { quietOnes, ruleBreakers, seniorProjectProgrammers, slackers };

        final Random random = new Random(1837837);

        for (int i = 0; i < 300; i++) {
            final int id = i;

            final StudyGroup studyGroup = studyGroups[random.nextInt(studyGroups.length)];
            final Runnable student = new Runnable() {
                @Override
                public void run() {

                    Main.logInit(id, studyGroup);

                    studyGroup.startStudyingWith();

                    Main.logStudy(id, studyGroup);

                    try {
                        Thread.sleep(50 + random.nextInt(20));
                    } catch (final InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }

                        Main.logDone(id, studyGroup);
                    studyGroup.stopStudyingWith();
                }
            };
            executor.execute(student);
        }
        executor.shutdown();

    }
}
