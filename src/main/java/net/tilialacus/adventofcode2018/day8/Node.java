package net.tilialacus.adventofcode2018.day8;

import java.util.Arrays;
import java.util.PrimitiveIterator.OfInt;

public class Node {
    private final Node[] children;
    private final int[] meta;

    public Node(Node[] children, int[] meta) {
        this.children = children;
        this.meta = meta;
    }

    public static Node parse(String[] data) {
        OfInt iterator = Arrays.stream(data).mapToInt(Integer::parseInt).iterator();
        return build(iterator);
    }

    private static Node build(OfInt data) {
        int childCount = data.nextInt();
        int metaCount = data.nextInt();
        return new Node(buildChildren(childCount, data), buildMeta(metaCount, data));
    }

    private static int[] buildMeta(int metaCount, OfInt data) {
        int[] meta = new int[metaCount];
        for (int i = 0; i < meta.length; i++) {
            meta[i] = data.nextInt();
        }
        return meta;
    }

    private static Node[] buildChildren(int count, OfInt data) {
        Node[] nodes = new Node[count];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = build(data);
        }
        return nodes;
    }

    public Node[] getChildren() {
        return children;
    }

    public int[] getMeta() {
        return meta;
    }

    public int getMetaSum() {
        return Arrays.stream(meta).sum() + Arrays.stream(children).mapToInt(Node::getMetaSum).sum();
    }

    public int getIndexedMetaSum() {
        if (children.length == 0) {
            return getMetaSum();
        } else {
            return Arrays.stream(meta).map(this::getChildMetaSum).sum();
        }
    }

    private int getChildMetaSum(int index) {
        if (index > children.length) {
            return 0;
        } else {
            return children[index - 1].getIndexedMetaSum();
        }
    }
}
