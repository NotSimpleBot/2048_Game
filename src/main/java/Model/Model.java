package Model;

public interface Model {
    void left();
    void right();
    void up();
    void down();

    void resetGameTiles();
    Tile[][] getGameTiles();

    boolean canMove();
}
