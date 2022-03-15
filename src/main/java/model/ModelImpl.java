package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelImpl implements Model {
    private Tile[][] gameTiles;
    private static final int FIELD_SIZE = 4;

    private int score = 0;
    private int maxTailValue = 0;

    public ModelImpl() {
        resetGameTiles();
    }

    @Override
    public void resetGameTiles() {
        score = 0;
        maxTailValue = 0;
        gameTiles = new Tile[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addNewTile();
        addNewTile();
    }

    /**
     * Добавляем 1 новую плитку на поле со значением 2 или 4
     */
    private void addNewTile() {
        List<Tile> emptyTilesList = Objects.requireNonNull(getEmptyTilesFrom_gameTiles());
        if (emptyTilesList.size() > 0) {
            Tile tileTmp = emptyTilesList.get((int) (Math.random() * emptyTilesList.size()));
            tileTmp.setValue(Math.random() < 0.9 ? (2) : (4));
        }
    }

    /**
     * Поиск плиток в массиве с незаданным значением value (=0).
     *
     * @return list с пустыми плитками
     */
    private List<Tile> getEmptyTilesFrom_gameTiles() {
        List<Tile> emptyTilesList = new ArrayList<>();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                Tile tileTmp = gameTiles[i][j];
                if (Objects.nonNull(tileTmp) && tileTmp.isEmpty()) {
                    emptyTilesList.add(tileTmp);
                }
            }
        }
        return emptyTilesList;
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

        for (int i = 0; i < FIELD_SIZE - 1; i++) {
            if (!tilesLine[i].isEmpty()
                    && (tilesLine[i].getValue() == tilesLine[i + 1].getValue())) {
                tilesLine[i].setValue(tilesLine[i].getValue() + tilesLine[i + 1].getValue());
                tilesLine[i + 1].setValue(0);
                score += tilesLine[i].getValue();
                if (tilesLine[i].getValue() > maxTailValue) {
                    maxTailValue = tilesLine[i].getValue();
                }
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
                for (int i = 0; i < FIELD_SIZE - 1; i++) {
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

    @Override
    public void left() {
        boolean flag = false;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (moveTilesWithZeroValueRight(gameTiles[i]) |
                    mergeTilesWithEqualsValue(gameTiles[i])) {
                flag = true;
            }
        }
        if (flag) {
            addNewTile();
        }
    }

    @Override
    public void right() {
        gameTiles = rotateTo90_gameTiles(2);
        left();
        gameTiles = rotateTo90_gameTiles(2);
    }

    @Override
    public void up() {
        gameTiles = rotateTo90_gameTiles(3);
        left();
        gameTiles = rotateTo90_gameTiles(1);
    }

    @Override
    public void down() {
        gameTiles = rotateTo90_gameTiles(1);
        left();
        gameTiles = rotateTo90_gameTiles(3);
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
            Tile[][] tempTiles = new Tile[FIELD_SIZE][FIELD_SIZE];
            for (int i = 0; i < FIELD_SIZE; i++) {
                for (int j = 0; j < FIELD_SIZE; j++) {
                    tempTiles[i][j] = gameTilesCopy[FIELD_SIZE - 1 - j][i];
                }
            }
            gameTilesCopy = tempTiles;
        }
        return gameTilesCopy;
    }

    @Override
    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    @Override
    public boolean canMove() {
        for (int i = 0; i < FIELD_SIZE - 1; i++) {
            for (int j = 0; j < FIELD_SIZE - 1; j++) {
                if (!getEmptyTilesFrom_gameTiles().isEmpty()) {
                    return true;
                }
                if (gameTiles[i][j].getValue() == gameTiles[i][j + 1].getValue()
                        || gameTiles[i][j].getValue() == gameTiles[i + 1][j].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getGameScore() {
        return score;
    }

    @Override
    public int getMaxTileValue() {
        return maxTailValue;
    }

    @Override
    public void setMaxTileValue(int value) {
        maxTailValue = value;
    }
}
