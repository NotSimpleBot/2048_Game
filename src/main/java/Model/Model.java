package Model;

public interface Model {
    /**
     * Попытка переместить плитки влево либо смерджить.
     * <p>
     * Это основной метод, логикой которого пользуются остальные методы перемещения:
     * (вправо, вверх, вниз)
     */
    void left();

    /**
     * Попытка переместить плитки вправо либо смерджить.
     * <p>
     * Использует логику метода left(), но перед этим разворачиваем игровой массив с плитками
     */
    void right();

    /**
     * Попытка переместить плитки вверх либо смерджить.
     * <p>
     * Использует логику метода left(), но перед этим разворачиваем игровой массив с плитками
     */
    void up();

    /**
     * Попытка переместить плитки вниз либо смерджить.
     * <p>
     * Использует логику метода left(), но перед этим разворачиваем игровой массив с плитками
     */
    void down();

    /**
     * Сбрасывает игровое поля и формирует 2 рандомные плитки со значением 2 или 4
     */
    void resetGameTiles();

    /**
     * Возвращает игровой массив с актуальными плитками
     */
    Tile[][] getGameTiles();

    /**
     * Проверка возможности сделать ход (смерджить/пепеместить плитки)
     */
    boolean canMove();
}
