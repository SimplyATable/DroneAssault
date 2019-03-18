import edu.digipen.Game;

public class Main {

    public static void main(String[] args) {
        //game start
        Game.initialize(800, 600, 60, new MainMenu());

        //create game window

        // while game running, update game
        while(!Game.getQuit())
        {
            Game.update();
        }
        //clean game memory
        Game.destroy();
    }
}


// contols are wasd for movement
// arrow keys to shoot, and two keys next to eachother for a shotgun
// a couple more levels and maybe a machine gun powerup?
// it would do this for a couple seconds
