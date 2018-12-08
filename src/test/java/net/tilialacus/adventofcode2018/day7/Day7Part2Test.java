package net.tilialacus.adventofcode2018.day7;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Part2Test {

    private static List<String> sampleInput = Arrays.asList(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
    );

    private Assembler assembler = new Assembler();

    @Test
    void sampleInputOrder() {
        for (String sample : sampleInput) {
            assembler.parse(sample);
        }

        int time = 0;
        int basetime = 0;

        List<Assembler.Step> completed = new ArrayList<>();
        List<Worker> workers = Arrays.asList(new Worker(), new Worker());
        while (completed.size() < 6) {
            List<Worker> available = workers.stream().filter(it -> it.step == null).collect(Collectors.toList());
            List<Assembler.Step> next = assembler.getNext(available.size());
            for (int i = 0; i < next.size(); i++) {
                Worker worker = available.get(i);
                worker.step = next.get(i);
                worker.step.setProcessing();
                worker.doneAt = time + basetime + worker.step.getName().charAt(0)-'A' + 1;
            }
            time = workers.stream().filter(it -> it.step != null)
                    .sorted(Comparator.comparing(Worker::getDoneAt))
                    .mapToInt(Worker::getDoneAt).findFirst().orElse(time);
            final int ct = time;
            workers.stream().filter(it -> it.doneAt == ct && it.step != null)
                    .sorted(Comparator.comparing(Worker::getName))
                    .forEach(it -> {
                        completed.add(assembler.process(it.step));
                        it.step = null;
                    });
        }

        String order = completed.stream().map(Assembler.Step::getName).collect(Collectors.joining());
        assertEquals("CABFDE", order);
        assertEquals(15, time);
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
    }

    @Test
    void result() {
        for (String sample : Input.inputFor(assembler)) {
            assembler.parse(sample);
        }

        int time = 0;
        int basetime = 60;

        List<Assembler.Step> completed = new ArrayList<>();
        List<Worker> workers = Arrays.asList(new Worker(), new Worker(), new Worker(), new Worker(), new Worker());
        while (completed.size() < 26) {
            List<Worker> available = workers.stream().filter(it -> it.step == null).collect(Collectors.toList());
            List<Assembler.Step> next = assembler.getNext(available.size());
            for (int i = 0; i < next.size(); i++) {
                Worker worker = available.get(i);
                worker.step = next.get(i);
                worker.step.setProcessing();
                worker.doneAt = time + basetime + worker.step.getName().charAt(0)-'A' + 1;
            }
            time = workers.stream().filter(it -> it.step != null)
                    .sorted(Comparator.comparing(Worker::getDoneAt))
                    .mapToInt(Worker::getDoneAt).findFirst().orElse(time);

            final int ct = time;
            workers.stream().filter(it -> it.doneAt <= ct && it.step != null)
                    .sorted(Comparator.comparing(Worker::getName))
                    .forEach(it -> {
                        completed.add(assembler.process(it.step));
                        it.step = null;
                    });
        }

        String order = completed.stream().map(Assembler.Step::getName).collect(Collectors.joining());
        assertEquals("BQRGMKJSZDCNWYETHULPAFIVXO", order);
        assertEquals(941, time);
    }
}
