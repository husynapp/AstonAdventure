package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import Entities.*;
import Game.AstonAdventure;

/**
 * The class LevelOne is used to play the first level (Enrolment) of the video game, with
 * the user learning basic controls and finding the university.
 */
public class LevelOne implements Screen {

    //Game
    private AstonAdventure game;

    //Sounds
    private Sounds Sm;

    //Map
    private Map map;

    //Player / Character
    private Player player;

    //Items / Pickups
    private Items items;

    //Inventory
    private Inventory inventory;

    //Text Boxes / Mentor
    private Text text;

    //Camera
    private Camera camera;

    //Character
    private float elapsedTime, elapsedTimeInventory, elapsedTimeText;

    /**
     * The Constructor for LevelOne initialises the map and player textures, camera,
     * items, inventory and sounds. Stores the game object.
     * @param game The game object.
     */
    LevelOne(AstonAdventure game) {

        //Store game object
        this.game = game;

        //Set up map
        map = new Map(1);

        //Set up camera
        camera = new Camera(map.getWidth(), map.getHeight(), game);

        //Set player / character
        player = new Player(game.getSelectedCharacter(), game);

        //Set items and their co-ordinates
        items = new Items(game);
        items.setBackpackCoordinates(500, 550);
        items.setCoffeeCoordinates(1100, 1100);
        items.setShoesCoordinates(1000, 1000);

        //Set Inventory and its position
        inventory = new Inventory(game);


        //Set Text Box and its position
        text = new Text(game);
        text.setTextPositiion(110, 230);
        text.setSylviaPosition(310, 230);

        //Set up sounds
        Sm = new Sounds();
        Sm.dispose();
    }

    /**
     * The render method is used to display the levels background and player, moving the camera such that
     * the player is centralised. It also allows for items to be displayed, picked up and stored.
     * @param delta Elapsed Time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Recalculate elapsed time
        elapsedTime += Gdx.graphics.getDeltaTime();

        //Background sounds
        if (!Sm.background.isPlaying()) {
            Sm.levelOne();
        }

        //Set view of camera
        camera.viewMap(map.getMapRenderer());

        //Begin sprite batch
        game.batch.begin();

        items.drawItems();

        //Draw text box relative to player position
        text.drawTextBox(items, camera);

        inventory.drawInventory(items, camera);

        //Draw character animation and calculate movement
        player.movement();

        //Update the camera position relative to player co-ordinates
        camera.update(player);


        player.drawCharacter(elapsedTime);

        //Check status of inventory
        inventory.checkHUDStatus(items);

        items.itemStatus(text, player);

        //Check if level has ended
        checkLevelProgress();

        //End sprite batch
        game.batch.end();
    }

    /**
     * Method used to determine if the level has finished.
     */
    private void checkLevelProgress() {
        if(text.currentSpeech == 6) {
            if (checkPlayerExit()) {
                game.setScreen(new ExitScreen(game));
            }
        }
    }

    /**
     * Method used to check if the player has exited to the main building.
     * @return True if the player has exited.
     */
    private boolean checkPlayerExit() {
        float xEntrance = 1369;
        float yEntrance = 1719;

        if (((xEntrance-20) < player.getX() && player.getX() < (xEntrance + 20)) && ((yEntrance-20) < player.getY() && player.getY() < (yEntrance + 20))) {
            return Gdx.input.isKeyPressed(Input.Keys.E);
        }

        return false;
    }

    //Unused
    @Override
    public void show() {

    }

    //Unused
    @Override
    public void resize(int width, int height) {
    }

    //Unused
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    //Unused
    @Override
    public void hide() {

    }

    //Unused
    @Override
    public void dispose() {

        game.batch.dispose();
    }
}
