import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountIslands {

    public static void main(String[] args) {
        char[][] map0 = {
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        };  // 0 islands
        char[][] map1 = {
                {'0', '0', '0'},
                {'0', '1', '0'},
                {'0', '0', '0'}
        };  // 1 island
        char[][] map11 = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };  // 1 island
        char[][] map111 = {
                {'1', '1', '0'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };  // 1 island
        char[][] map2 = {
                {'1', '0', '0'},
                {'0', '0', '0'},
                {'1', '1', '1'}
        };  // 2 islands
        char[][] map3 = {
                {'1', '1', '0', '0', '0'},
                {'1', '0', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };  // 3 islands
        char[][] map4 = {
                {'1', '1', '0', '0', '1'},
                {'1', '0', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };  // 4 islands
        System.out.println("The map0 contains " + countIslands(map0) + " islands.");
        System.out.println("The map1 contains " + countIslands(map1) + " islands.");
        System.out.println("The map11 contains " + countIslands(map11) + " islands.");
        System.out.println("The map111 contains " + countIslands(map111) + " islands.");
        System.out.println("The map2 contains " + countIslands(map2) + " islands.");
        System.out.println("The map3 contains " + countIslands(map3) + " islands.");
        System.out.println("The map4 contains " + countIslands(map4) + " islands.");
    }


    public static int countIslands(char[][] map) {
        int result = 0;
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            for (int colIndex = 0; colIndex < map[rowIndex].length; colIndex++) {
                if (map[rowIndex][colIndex] == '1') {
                    resetLand(map, rowIndex, colIndex);
                    result++;
                }
            }
        }
        return result;
    }

    public static void resetLand(char[][] map, int row, int col) {
        map[row][col] = '0';
        if(row > 0 && map[row - 1][col] == '1') {
            resetLand(map, row - 1, col);
        }
        if(map.length > row + 1 && map[row + 1][col] == '1') {
            resetLand(map, row + 1, col);
        }
        if(col > 0 && map[row][col - 1] == '1') {
            resetLand(map, row, col - 1);
        }
        if(map[row].length > col + 1 && map[row][col + 1] == '1') {
            resetLand(map, row, col + 1);
        }
    }


    public static int countIslandsOOP(char[][] map) {
        int result = 0;
        MapGraph mapGraph = new MapGraph(map);
        System.out.println(mapGraph);
        return result;
    }

    static class MapGraph {

        private Cell seed;
        private Integer islandsAmount = null;

        public MapGraph(char[][] map) {
            if(map[0][0] == '1') {
                seed = new Land(map, new Point(0,0));
            } else {
                seed = new Water(map, new Point(0,0));
            }
        }

        @Override
        public String toString() {
            return seed.getColumnAfterWith().stream().map(cell -> cell.getRowAfterWith().toString()).collect(Collectors.joining("\n"));
        }

    }

    static abstract class Cell {

        private final Point location;
        private Cell rightNeighbour;
        private Cell bottomNeighbour;

        public Cell(char[][] map, Point location) {
            this.location = location;
            if (map[location.y].length > location.x + 1) {
                if (map[location.y][location.x + 1] == '1') {
                    rightNeighbour = new Land(map, new Point(location.x + 1, location.y));
                } else {
                    rightNeighbour = new Water(map, new Point(location.x + 1, location.y));
                }
            }
            if (map.length > location.y + 1) {
                if (map[location.y + 1][location.x] == '1') {
                    bottomNeighbour = new Land(map, new Point(location.x, location.y + 1));
                } else {
                    bottomNeighbour = new Water(map, new Point(location.x, location.y + 1));
                }
            }
        }

        public Point getLocation() {
            return location;
        }

        public Cell getRightNeighbour() {
            return rightNeighbour;
        }

        public Cell getBottomNeighbour() {
            return bottomNeighbour;
        }
        public List<Cell> getRowAfterWith() {
            List<Cell> rowAfterWith = new ArrayList<>();
            rowAfterWith.add(this);
            Cell currentCell = this;
            while(currentCell.getRightNeighbour() != null) {
                rowAfterWith.add(currentCell.getRightNeighbour());
                currentCell = currentCell.getRightNeighbour();
            }
            return rowAfterWith;
        }

        public List<Cell> getColumnAfterWith() {
            List<Cell> columnAfterWith = new ArrayList<>();
            columnAfterWith.add(this);
            Cell currentCell = this;
            while(currentCell.getBottomNeighbour() != null) {
                columnAfterWith.add(currentCell.getBottomNeighbour());
                currentCell = currentCell.getBottomNeighbour();
            }
            return columnAfterWith;
        }

    }

    static class Land extends Cell {

        public Land(char[][] map, Point location) {
            super(map, location);
        }

        @Override
        public String toString() {
            return "1";
        }

    }

    static class Water extends Cell {

        public Water(char[][] map, Point location) {
            super(map, location);
        }

        @Override
        public String toString() {
            return "0";
        }
    }
}
