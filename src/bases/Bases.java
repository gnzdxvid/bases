package bases;

import Control.Control;
import GUI.GUI;

public class Bases {
    public static void main(String[] args) {
        Control control = new Control();
        GUI gui = new GUI(control);
        gui.mostrarMenu();
    }
}
