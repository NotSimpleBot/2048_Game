package View;

import Controller.Controller;
import Model.Tile;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private Controller controller;
    boolean gameLoose = false;
    boolean gameWon = false;


    public View(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    /**
     * Для отрисовки каждой плитки на игровом поле
     */
    private void drawTile(Graphics g2, Tile tile, int x, int y) {

    }

    /**
     * Установка цвета
     */
    private static int offsetCoors(int arg) {
        // TODO: 07.02.2022  
        return 0;
    }

    public boolean isGameLoose() {
        return gameLoose;
    }

    public void setGameLoose(boolean gameLoose) {
        this.gameLoose = gameLoose;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
