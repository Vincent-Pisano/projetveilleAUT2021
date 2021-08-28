package veille.renaud.exercices.games;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    private static final List<Game> GAMES = Arrays.asList(
            new Game("Cheese eater", 30.95d, 12),
            new Game("Rubicon 1", 5.99d, 30),
            new Game("Rubicon 2", 12.99d, 60),
            new Game("Cheese Stealer", 56.85d, 5),
            new Game("Fallout 2", 120.84d, 7),
            new Game("Skyrim", 80.00d, 25),
            new Game("Falling lies", 99.98d, 8)
    );

    public Observable<Game> gamesForSale() {
        return Observable.create(emitter -> {
            int i = 0;
            System.out.println("Start Sending Games");
            while (!emitter.isDisposed() && i < GAMES.size())
            {
                Game game = GAMES.get(i);
                if(game.getStorage() == 0)
                {
                    emitter.onError(new RuntimeException("Woops, The storage is empty for the game " + game.getName()));
                }
                emitter.onNext(game);
                i++;
            }
            System.out.println("Finish Sending games");
            emitter.onComplete();
        });
    }

}
