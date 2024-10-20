package ru.Vladimir;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import static ru.Vladimir.states.VectorState.*;

public class Parser {
    public static Float[] parseVector(String rawVector){
        var rawArray = rawVector.replaceAll("\\{", "").replaceAll("}", "");
        return Arrays.stream(rawArray.split(",")).map(str -> str.replaceFirst("\\s++$", "")).map(Float::parseFloat).toArray(Float[]::new);
    }
    public static void parseCommand(String rawCommand) {
        var split = rawCommand.split(" ");
        if (split.length == 3) {
            if (split[1].toLowerCase(Locale.ROOT).startsWith("перп")) {
                if (split[0].matches("век.*") && split[2].matches("век.*")) {
                    var first = split[0].substring(3).replaceAll("[^0-9.]", "");
                    var last = split[2].substring(3).replaceAll("[^0-9.]", "");
                    if (!first.isEmpty() && !last.isEmpty()) {
                        int firstIndex = Integer.parseInt(first) - 1;
                        int lastIndex = Integer.parseInt(last) - 1;
                        if (vecArr.get(firstIndex) == null || vecArr.get(lastIndex) == null) {
                            System.out.println("Error, no such vectors");
                            return;
                        }

                        System.out.println(scalarProd(vecArr.get(firstIndex), vecArr.get(lastIndex)) == 0F);
                    }
                }
            } else if (split[1].toLowerCase(Locale.ROOT).startsWith("коллин")) {
                if (split[0].matches("век.*") && split[2].matches("век.*")) {
                    var first = split[0].substring(3).replaceAll("[^0-9.]", "");
                    var last = split[2].substring(3).replaceAll("[^0-9.]", "");
                    if (!first.isEmpty() && !last.isEmpty()) {
                        int firstIndex = Integer.parseInt(first) - 1;
                        int lastIndex = Integer.parseInt(last) - 1;
                        if (vecArr.get(firstIndex) == null || vecArr.get(lastIndex) == null) {
                            System.out.println("Error, no such vectors");
                            return;
                        }
                        System.out.println(isCollin(firstIndex, lastIndex));
                    }
                }
            } else if (split[1].toLowerCase(Locale.ROOT).startsWith("уг")) {
                if (split[0].matches("век.*") && split[2].matches("век.*")) {
                    var first = split[0].substring(3).replaceAll("[^0-9.]", "");
                    var last = split[2].substring(3).replaceAll("[^0-9.]", "");
                    if (!first.isEmpty() && !last.isEmpty()) {
                        int firstIndex = Integer.parseInt(first) - 1;
                        int lastIndex = Integer.parseInt(last) - 1;
                        if (vecArr.get(firstIndex) == null || vecArr.get(lastIndex) == null) {
                            System.out.println("Error, no such vectors");
                            return;
                        }
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        nf.setMaximumFractionDigits(4);
                        var retval = Double.parseDouble(nf.format(Math.acos(cosBetweenVectors(vecArr.get(firstIndex), vecArr.get(lastIndex))))) * 180 / Math.PI;
                        System.out.println(retval);
                    }
                }
            }
        } else if (split.length == 4) {
            if (split[0].toLowerCase(Locale.ROOT).startsWith("смеш")) {
                if (split[1].matches("век.*") && split[2].matches("век.*") && split[3].matches("век.*")) {
                    var first = split[1].substring(3).replaceAll("[^0-9.]", "");
                    var mid = split[2].substring(3).replaceAll("[^0-9.]", "");
                    var last = split[3].substring(3).replaceAll("[^0-9.]", "");
                    if (!first.isEmpty() && !last.isEmpty() && !mid.isEmpty()) {
                        int firstIndex = Integer.parseInt(first) - 1;
                        int midIndex = Integer.parseInt(first) - 1;
                        int lastIndex = Integer.parseInt(last) - 1;
                        if (vecArr.get(firstIndex) == null || vecArr.get(lastIndex) == null || vecArr.get(midIndex) == null) {
                            System.out.println("Error, no such vectors");
                            return;
                        }
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        nf.setMaximumFractionDigits(4);
                        var degree = Double.parseDouble(nf.format(Math.acos(cosBetweenVectors(vecArr.get(firstIndex), vecArr.get(lastIndex))))) * 180 / Math.PI;
                        var sin = Math.sin(degree);

                    }

                }
            }
        }
    }
    private static boolean isCollin(int firstIndex, int lastIndex) {
        var a = vecArr.get(firstIndex);
        var b = vecArr.get(lastIndex);
        var kx = (a.getEnd().x - a.getStart().x) / (b.getEnd().x - b.getStart().x);
        var ky = (a.getEnd().y - a.getStart().y) / (b.getEnd().y - b.getStart().y);
        var kz = (a.getEnd().z - a.getStart().z) / (b.getEnd().z - b.getStart().z);
        var retval = false;
        if(kx == ky && ky == kz){
            retval = true;
        }
        return retval;
    }
}
