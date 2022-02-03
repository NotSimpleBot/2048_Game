package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class ModelImplTest {
    private Tile[] lineTilesTest;
    Tile t1;
    Tile t2;
    Tile t3;
    Tile t4;
    private ModelImpl model;

    @Before
    public void setUp() throws Exception {
        model = new ModelImpl();
        t1 = new Tile(0);
        t2 = new Tile(0);
        t3 = new Tile(2);
        t4 = new Tile(2);
        lineTilesTest = new Tile[]{t1, t2, t3, t4};
    }


    @Test
    public void moveTilesWithZeroValueRightTest() {
        Tile[] expected = new Tile[]{t3, t4, t1, t2};
        moveTilesWithZeroValueRight(lineTilesTest);

        assertArrayEquals(expected, lineTilesTest);
    }


    private boolean moveTilesWithZeroValueRight(Tile[] tilesLine) {
        boolean swap = false;
        boolean flag = true;
        Objects.requireNonNull(tilesLine, "tilesLine is null");

        if (tilesLine.length > 0) {
            while (flag) {
                flag = false;
                for (int i = 0; i < 4 - 1; i++) {
                    if (Objects.nonNull(tilesLine[i]) && tilesLine[i].isEmpty()) {
                        if (Objects.nonNull(tilesLine[i + 1]) && !tilesLine[i + 1].isEmpty()) {
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

    private void swapTwoTiles(Tile[] tilesLine, int x, int y) {
        Objects.requireNonNull(tilesLine[x], "t[x] is null");
        Objects.requireNonNull(tilesLine[y], "t[y] is null");
        Tile tileTmp = tilesLine[x];
        tilesLine[x] = tilesLine[y];
        tilesLine[y] = tileTmp;
    }
}