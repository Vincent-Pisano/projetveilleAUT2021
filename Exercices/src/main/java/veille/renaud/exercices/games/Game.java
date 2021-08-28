package veille.renaud.exercices.games;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {

    private String name;
    private double price;
    private int storage;

}
