package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FixedRouteSuggestion {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfFixedRoute = scanner.nextInt();
        List<Station> fixedStations = new ArrayList<>();
        List<Station> userSuggestedStations = new ArrayList<>();

        try {
            for (int i = 0; i < numberOfFixedRoute; i++) {
                int numberOfStations = scanner.nextInt();
                fixedStations.addAll(inputRoute(scanner, numberOfStations));
            }

            int numberOfUserSuggestedStations = scanner.nextInt();
            userSuggestedStations = inputRoute(scanner, numberOfUserSuggestedStations);
        } catch (Exception ex) {
            System.out.println();
            System.out.println("Please provide valid input");
        }

        constructRoute(fixedStations, userSuggestedStations);

    }

    private static void constructRoute(List<Station> fixedStations, List<Station> userSuggestionStations) {
        Map<Station, Integer> alreadyOpted = new HashMap<>();
        for (Station us : userSuggestionStations) {
            double minDistance = Integer.MAX_VALUE;
            Station selectedStation = null;

            for (Station fs : fixedStations) {
                if (alreadyOpted.containsKey(fs)) {
                    continue;
                }
                double distance = distance(us.getX(), us.getY(), fs.getX(), fs.getY());
                if (distance < minDistance) {
                    minDistance = distance;
                    selectedStation = fs;
                }
            }

            alreadyOpted.put(selectedStation, 1);
            System.out.println(us.getName() + " " + selectedStation.getName());
        }
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    private static List<Station> inputRoute(Scanner scanner, int numberOfStations) {
        List<Station> stations = new ArrayList<>();
        for (int j = 0; j < numberOfStations; j++) {
            String stationName = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            stations.add(new Station(stationName, x, y));
        }
        return stations;
    }

    private static class Station {
        private final String name;
        private final int x;
        private final int y;

        public Station(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return this.name + " " + x + " " + y;
        }

        public String getName() {
            return name;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
