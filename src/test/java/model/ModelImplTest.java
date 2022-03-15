package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class ModelImplTest {
    private Tile[] lineTilesTest;
    private Tile[][] gameTiles;
    private Tile t1;
    private Tile t2;
    private Tile t3;
    private Tile t4;

    @Before
    public void setUp() throws Exception {
        t1 = new Tile(0);
        t2 = new Tile(0);
        t3 = new Tile(2);
        t4 = new Tile(2);
        lineTilesTest = new Tile[]{t1, t2, t3, t4};
        gameTiles = new Tile[][]{{new Tile(), new Tile(2), new Tile(2), new Tile()},
                {new Tile(4), new Tile(4), new Tile(), new Tile()},
                {new Tile(), new Tile(), new Tile(), new Tile()},
                {new Tile(), new Tile(), new Tile(), new Tile()}};
//        gameTiles = new Tile[][]{{new Tile(4), new Tile(256), new Tile(4), new Tile(2)},
//                                {new Tile(8), new Tile(2), new Tile(16), new Tile(32)},
//                                {new Tile(2), new Tile(4), new Tile(2), new Tile(16)},
//                                {new Tile(8), new Tile(256), new Tile(32), new Tile(128)}};

    }


    @Test
    public void moveTilesWithZeroValueRightTest() {
        Tile[] expected = new Tile[]{t3, t4, t1, t2};
        moveTilesWithZeroValueRight(lineTilesTest);

        assertArrayEquals(expected, lineTilesTest);
    }

    @Test
    public void mergeTilesWithEqualsValueTest() {
        int[] expected = new int[]{4, 0, 0, 0};
        mergeTilesWithEqualsValue(lineTilesTest);
        int[] actual = new int[4];
        actual[0] = lineTilesTest[0].getValue();
        actual[1] = lineTilesTest[1].getValue();
        actual[2] = lineTilesTest[2].getValue();
        actual[3] = lineTilesTest[3].getValue();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void rotateTo90_gameTilesTest() {
        int[][] expected = new int[][]{{0, 0, 4, 0},
                {0, 0, 4, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0}};
        Tile[][] testArr = rotateTo90_gameTiles(1);
        int[][] actual = new int[testArr.length][testArr.length];
        for (int i = 0; i < testArr.length; i++) {
            for (int j = 0; j < testArr.length; j++) {
                System.out.print(testArr[i][j]);
                actual[i][j] = testArr[i][j].getValue();
            }
            System.out.println();
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    public void canMoveTest(){
        boolean actual = canMove();
        assertFalse(actual);
    }


    /**
     * Суммирует значения 2ух рядом стоящих плиток в относительно левую, правую обнуляет значение.
     * Если слияние удается (хотя бы 1), то вызывается метод moveTilesWithZeroValueRight.
     *
     * @return true если удалось хотя бы 1 слияние
     */
    private boolean mergeTilesWithEqualsValue(Tile[] tilesLine) {
        boolean flag = false;
        Objects.requireNonNull(tilesLine, "tilesLine is null");

        for (int i = 0; i < 4 - 1; i++) {
            if (!tilesLine[i].isEmpty()
                    && (tilesLine[i].getValue() == tilesLine[i + 1].getValue())) {
                tilesLine[i].setValue(tilesLine[i].getValue() + tilesLine[i + 1].getValue());
                tilesLine[i + 1].setValue(0);

                flag = true;
                moveTilesWithZeroValueRight(tilesLine);
            }
        }
        return flag;
    }


    /**
     * Сдвигает все нули из левой части массива в правую,
     * нули местами не меняет.
     *
     * @return true если был хотя бы один swap.
     */
    private boolean moveTilesWithZeroValueRight(Tile[] tilesLine) {
        boolean swap = false;
        boolean flag = true;
        Objects.requireNonNull(tilesLine, "tilesLine is null");

        if (tilesLine.length > 0) {
            while (flag) {
                flag = false;
                for (int i = 0; i < 4 - 1; i++) {
                    if (Objects.nonNull(tilesLine[i]) && tilesLine[i].isEmpty()) {
                        if (Objects.nonNull(tilesLine[i + 1])
                                && (tilesLine[i + 1].getValue() != tilesLine[i].getValue())) {
                            swapTwoTiles(tilesLine, i, i + 1);
                            flag = true;
                            swap = true;
                        }
                    }
                }
            }
        }
        return swap;
    }

    /**
     * Меняет значения двух плиток местами
     */
    private void swapTwoTiles(Tile[] tilesLine, int x, int y) {
        Objects.requireNonNull(tilesLine[x], "t[x] is null");
        Objects.requireNonNull(tilesLine[y], "t[y] is null");
        Tile tileTmp = tilesLine[x];
        tilesLine[x] = tilesLine[y];
        tilesLine[y] = tileTmp;
    }


    /**
     * Разворачивает массив gameTiles на 90градусов, нужно для упрощения
     * реализации методов up, right, down --> теперь можно реализовать
     * только left и далее вертеть массив данным методом и выполнять left.
     *
     * @param count сколько раз повернуть массив на 90градусов
     * @return Tile[][] повернутый массив.
     */
    private Tile[][] rotateTo90_gameTiles(int count) {
        Tile[][] gameTilesCopy = gameTiles;
        for (int iter = 0; iter < count; iter++) {
            Tile[][] tempTiles = new Tile[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    tempTiles[i][j] = gameTilesCopy[4 - 1 - j][i];
                }
            }
            gameTilesCopy = tempTiles;
        }
        return gameTilesCopy;
    }

    /**
     * Проверка возможности сделать ход на игровом поле (смерджить или сместить)
     */
    public boolean canMove() {
        for (int i = 0; i < 4 - 1; i++) {
            for (int j = 0; j < 4 - 1; j++) {
                if (!getEmptyTilesFrom_gameTiles().isEmpty()) {
                    return true;
                }
                if (gameTiles[i][j].getValue() == gameTiles[i][j + 1].getValue()
                        || gameTiles[i][j].getValue() == gameTiles[i + 1][j].getValue()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Поиск плиток в массиве с незаданным значением value (=0).
     *
     * @return list с пустыми плитками
     */
    private List<Tile> getEmptyTilesFrom_gameTiles() {
        List<Tile> emptyTilesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tileTmp = gameTiles[i][j];
                if (Objects.nonNull(tileTmp) && tileTmp.isEmpty()) {
                    emptyTilesList.add(tileTmp);
                }
            }
        }
        return emptyTilesList;
    }
}