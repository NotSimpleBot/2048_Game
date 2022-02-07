package Controller;

import Model.Model;
import View.View;

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
        // TODO: 07.02.2022
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            model.resetGameTiles();
        }
        if (!model.canMove()) {
            // TODO: 07.02.2022
        }
        if (// TODO: 07.02.2022  ){
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
            // TODO: 07.02.2022
        } else {
            resetGame();
        }
        // TODO: 07.02.2022  
    }


}
}
