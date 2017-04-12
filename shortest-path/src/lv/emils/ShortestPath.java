package lv.emils;

/**
 * Created by Emils on 09.04.2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShortestPath {
    private static List<Node> nodes;
    private static List<Node> nodes2;
    private static List<Node> nodes3;

    private static int hopCount;
    private static List<Node> path;
    private static List<Node> queue;


    public static void main(String[] args) {
        nodes = new ArrayList<>();
        nodes2 = new ArrayList<>();
        nodes3 = new ArrayList<>();
        path = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            nodes.add(new Node(i));
        }
        nodes.get(0).setNodes(new LinkedList<>(Arrays.asList(nodes.get(1), nodes.get(2), nodes.get(4))));
        nodes.get(1).setNodes(new LinkedList<>(Arrays.asList(nodes.get(0), nodes.get(2), nodes.get(5))));
        nodes.get(2).setNodes(new LinkedList<>(Arrays.asList(nodes.get(0), nodes.get(1), nodes.get(3), nodes.get(5))));
        nodes.get(3).setNodes(new LinkedList<>(Arrays.asList(nodes.get(2), nodes.get(4), nodes.get(5))));
        nodes.get(4).setNodes(new LinkedList<>(Arrays.asList(nodes.get(0), nodes.get(3), nodes.get(5))));
        nodes.get(5).setNodes(new LinkedList<>(Arrays.asList(nodes.get(1), nodes.get(2), nodes.get(3), nodes.get(5))));

        for (int i = 0; i < 9; i++) {
            nodes2.add(new Node(i));
        }
        nodes2.get(0).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(1), nodes2.get(2), nodes2.get(3))));
        nodes2.get(1).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(0), nodes2.get(4))));
        nodes2.get(2).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(0), nodes2.get(4), nodes2.get(5))));
        nodes2.get(3).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(0), nodes2.get(5))));
        nodes2.get(4).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(1), nodes2.get(2), nodes2.get(8))));
        nodes2.get(5).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(2), nodes2.get(3), nodes2.get(6), nodes2.get(7))));
        nodes2.get(6).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(5), nodes2.get(7))));
        nodes2.get(7).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(5), nodes2.get(6))));
        nodes2.get(8).setNodes(new LinkedList<>(Arrays.asList(nodes2.get(8))));

        for (int i = 0; i < 8; i++) {
            nodes3.add(new Node(i));
        }
        nodes3.get(0).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(1), nodes3.get(3), nodes3.get(6))));
        nodes3.get(1).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(0), nodes3.get(4), nodes3.get(5))));
        nodes3.get(2).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(5), nodes3.get(7))));
        nodes3.get(3).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(0), nodes3.get(5))));
        nodes3.get(4).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(1), nodes3.get(6))));
        nodes3.get(5).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(1), nodes3.get(2), nodes3.get(3))));
        nodes3.get(6).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(0), nodes3.get(4))));
        nodes3.get(7).setNodes(new LinkedList<>(Arrays.asList(nodes3.get(2))));

        System.out.println("Graph 1");
        queue = new ArrayList<>(Arrays.asList(nodes.get(4)));
        findShortestPath(nodes.get(4), nodes.get(2));

        System.out.println("Graph 2");
        queue = new ArrayList<>(Arrays.asList(nodes2.get(0)));
        findShortestPath(nodes2.get(0), nodes2.get(6));

        System.out.println("Graph 3");
        queue = new ArrayList<>(Arrays.asList(nodes3.get(0)));
        findShortestPath(nodes3.get(0), nodes3.get(7));
    }

    public static void findShortestPath(Node node, Node target) {
        if (!queue.isEmpty()) {
            queue.remove(0);
            for (int adjNode = 0; adjNode < node.getNodes().size(); adjNode++) {
                Node adjNodeObj = node.getNodes().get(adjNode);
                if (adjNodeObj.isVisited())
                    continue;
                else {
                    node.setVisited(true);
                    queue.add(adjNodeObj);
                    adjNodeObj.setParent(node);
//                    System.out.print("Searching node " + node.getId() + ": " + adjNodeObj.getId() + " ");
                    if (adjNodeObj.equals(target)) {
                        queue.clear();
                        Node currentNode = adjNodeObj;
                        while (true) {
                            path.add(currentNode);
                            if (currentNode.getParent() != null) {
                                currentNode = currentNode.getParent();
                                hopCount++;
                            }
                            else {
                                break;
                            }
                        }
                        System.out.println("Shortest path (" + hopCount + " hops). Path: " + path);
                        hopCount = 0;
                        path.clear();
                        System.out.println();
                        break;
                    }
                }
            }
//            System.out.println();
            if (!queue.isEmpty())
                findShortestPath(queue.get(0), target);
        }
    }
}

class Node {
    private int id;
    private LinkedList<Node> nodes;
    private boolean visited;
    private Node parent;

    public Node(int id) {
        this.setId(id);
    }

    @Override
    public String toString() {
        return "V(" + id + ')';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
