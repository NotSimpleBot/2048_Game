package Controller;

import Model.Model;
import View.View;
import Model.Tile;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private final static int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    /**
     * Полный сброс игры
     */
    private void resetGame() {
        view.setGameWon(false);
        view.setGameLoose(false);
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            model.resetGameTiles();
        }
        if (!model.canMove()) {
            view.setGameLoose(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            model.left();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            model.right();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            model.up();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            model.down();
        }

        if (model.getMaxTileValue() == WINNING_TILE) {
            view.setGameWon(true);
        } else {
            resetGame();
        }
        view.repaint();
    }

    /**
     * Получить текущее положение плиток в виде массива
     */
    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    /**
     * Получить текущее кол-во очков
     */
    public int getScore() {
        return model.getGameScore();
    }

    /**
     * Геттер для view
     */
    public View getView() {
        return view;
    }
}

