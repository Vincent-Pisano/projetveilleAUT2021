package veille.renaud.exercices.games;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameServiceTest {

    GameService gameService = new GameService();

    Observable<Game> gameObservable = gameService.gamesForSale();

    @Test
    public void gamesForSale1() {
         gameObservable.filter(game -> game.getName().startsWith("Cheese")).subscribe(
                data -> System.out.println("We got some Data : " + data),
                error -> System.out.println("Error occured : " + error),
                () -> System.out.println("We are done ... Thank you for the data ...")
        );
    }

    @Test
    public void gamesForSales2() {
        Observer<Game> observer = new Observer<Game>() {
            Disposable disposable;

            public void onUnSubscribe()
            {
                disposable.dispose();
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull Game game) {
                if (game.getName().startsWith("Fal"))
                    disposable.dispose();
                //change la condition emitter.isDisposed dans GameService
                //on ne va pas recevoir le OnComplete car on a dispose l'Observer
                System.out.println("OnNext = " + game);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Error observer : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed... ");
            }
        };

        //gameObservable.subscribe(observer);
        gameObservable.filter(game -> game.getStorage() > 10).subscribe(observer);
    }

    @BeforeEach
    public void separation()
    {
        System.out.println("\n-----------------------------------\n" +
                           "* Test Started" +
                         "\n-----------------------------------\n");
    }
}
