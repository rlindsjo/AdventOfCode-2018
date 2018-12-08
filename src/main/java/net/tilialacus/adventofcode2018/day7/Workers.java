package net.tilialacus.adventofcode2018.day7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Workers {

    private final int baseTime;
    private final List<Worker> workers = new ArrayList<>();
    private int time = 0;

    public Workers(int size, int baseTime) {
        this.baseTime = baseTime;
        while (workers.size() < size) {
            workers.add(new Worker());
        }
    }

    public List<Assembler.Step> process(Assembler assembler) {
        List<Assembler.Step> completed = new ArrayList<>();
        while (!assembler.getSteps().isEmpty()) {
            List<Worker> available = getAvailableWorkers();
            List<Assembler.Step> next = assembler.getNext(available.size());
            for (int i = 0; i < next.size(); i++) {
                available.get(i).assign(next.get(i));
            }
            time = nextStepCompletedAt();
            completeAll(assembler, completed);
        }
        return completed;
    }

    private void completeAll(Assembler assembler, List<Assembler.Step> completed) {
        workers.stream().filter(it -> it.doneAt == time && !it.available())
                .sorted(Comparator.comparing(Worker::getName))
                .forEach(it -> completed.add(assembler.process(it.complete())));
    }

    private int nextStepCompletedAt() {
        return workers.stream().filter(it -> it.step != null)
                .sorted(Comparator.comparing(Worker::getDoneAt))
                .mapToInt(Worker::getDoneAt).findFirst().orElse(time);
    }

    private List<Worker> getAvailableWorkers() {
        return workers.stream().filter(Worker::available).collect(Collectors.toList());
    }

    public int getTime() {
        return time;
    }

    private class Worker {
        int doneAt;
        Assembler.Step step;

        public int getDoneAt() {
            return doneAt;
        }

        public String getName() {
            return step.getName();
        }

        public boolean available() {
            return  step == null;
        }

        public void assign(Assembler.Step step) {
            this.step = step;
            step.setProcessing();
            doneAt = time + baseTime + step.getName().charAt(0)-'A' + 1;
        }

        public Assembler.Step complete() {
            Assembler.Step current = step;
            step = null;
            return current;
        }
    }

}
