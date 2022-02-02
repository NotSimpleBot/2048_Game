package Model;

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

    @Override
    public void left() {
        boolean flag = false;
    }

    @Override
    public void right() {

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

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
            for (int i = 0; i < FIELD_SIZE - 1; i++) {
                for (int j = 0; j < FIELD_SIZE - 1; j++) {
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

    }
}
