package com.hellokoding.core.dynamic.LIS;

import java.util.Arrays;

public class BoxStacking {

    int findMaxHeight(Box[] boxes) {
        Box[] possibleBoxes = findAllPossibleBoxes(boxes);
        Arrays.sort(possibleBoxes);

        System.out.println("All sorted and rotated boxes: ");
        printBoxes(possibleBoxes);

        int[] resultLink = new int[possibleBoxes.length];
        int[] boxHeightSubsequences = new int[possibleBoxes.length];
        for (int i = 0; i < possibleBoxes.length; i++) {
            boxHeightSubsequences[i] = possibleBoxes[i].height;
            resultLink[i] = i;
        }

        for (int i = 1; i < boxHeightSubsequences.length; i++) {
            for (int j = 0; j < i; j++) {
                if (possibleBoxes[i].depth < possibleBoxes[j].depth && possibleBoxes[i].width < possibleBoxes[j].width) {
                    if (boxHeightSubsequences[i] < possibleBoxes[i].height + boxHeightSubsequences[j]) {
                        boxHeightSubsequences[i] = possibleBoxes[i].height + boxHeightSubsequences[j];
                        resultLink[i] = j;
                    }
                }
            }
        }

        int maxHeight = 0;
        int maxHeightIndex = 0;
        for (int i = 0; i < boxHeightSubsequences.length; i++) {
            if (maxHeight < boxHeightSubsequences[i]){
                maxHeight = boxHeightSubsequences[i];
                maxHeightIndex = i;
            }
        }


        System.out.println("Final result stacking boxes: ");
        printBoxes(possibleBoxes);

        return maxHeight;
    }

    Box[] findAllPossibleBoxes(Box[] boxes) {
        Box[] possibleBoxes = new Box[boxes.length*3];
        int pi = 0;
        for (int i = 0; i < boxes.length; i++) {
            possibleBoxes[pi++] = new Box(boxes[i].height, boxes[i].depth, boxes[i].width);
            possibleBoxes[pi++] = new Box(boxes[i].width, boxes[i].height, boxes[i].depth);
            possibleBoxes[pi++] = new Box(boxes[i].depth, boxes[i].width, boxes[i].height);
        }

        return possibleBoxes;
    }

    Box[] findResultBoxes(Box[] boxes, int[] stackIndexes, int topBoxIndex) {
        //int[] resultIndexes = new int[]
        return null;
    }

    void printBoxes(Box[] boxes) {
        for (int i = 0; i < boxes.length; i++) {
            System.out.println(boxes[i]);
        }
    }

    public static void main(String[] args) {
        BoxStacking boxStacking = new BoxStacking();
        Box[] boxes = {new Box(3, 2, 5), new Box(1, 2, 4)};
        int maxHeight = boxStacking.findMaxHeight(boxes);
        System.out.println("Max height which can be obtained: " + maxHeight);
    }
}

class Box implements Comparable<Box>{
    int height;
    int width;
    int depth;
    public Box(int a, int b, int c){
        this.height = a;
        if (b >= c) {
            this.width = b;
            this.depth = c;
        } else {
            this.width = c;
            this.depth = b;
        }
    }

    public int compareTo(Box o) {
        return o.depth*o.width - this.depth*this.width;
    }

    @Override
    public String toString() {
        return "Box [height=" + height + ", depth=" + depth
                + ", width=" + width + "]";
    }
}
