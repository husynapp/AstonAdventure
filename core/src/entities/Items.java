package entities;

import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Items {

    public Texture backpack;
    public Texture book;
    public Texture coffee;
    public Texture shoes;

    public float xBackpack = 200, yBackpack = 350;
    public float xBook = 550, yBook = 150;
    public float xCoffee = 300, yCoffee = 0;
    public float xShoes = 50, yShoes = 100;

    public boolean backpackPick = false;
    public boolean bookPick = false;
    public boolean coffeePick = false;
    public boolean shoesPick = false;

    Soundmanager Sm = new Soundmanager();

    public Items() {

        backpack = new Texture("Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        shoes = new Texture("Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        book = new Texture("Sprites/Objects/Book/Book_object_spritesheet_resized.png");
        coffee = new Texture("Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");

    }

    public void hasPlayerPickedBackpack(float xPlayer, float yPlayer) {
        if (((xBackpack - 10) < xPlayer && xPlayer < (xBackpack + 10)) || ((yBackpack - 10) < yPlayer && yPlayer < (yBackpack + 10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                backpackPick = true;
                Sm.soundFx();

            }
        }
    }

    public void hasPlayerPickedBook(float xPlayer, float yPlayer) {
        if (((xBook - 20) < xPlayer && xPlayer < (xBook + 20)) || ((yBook - 20) < yPlayer && yPlayer < (yBook + 20))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                bookPick = true;
                Sm.soundFx();
            }
        }
    }

    public void hasPlayerPickedCoffee(float xPlayer, float yPlayer) {
        if (((xCoffee - 10) < xPlayer && xPlayer < (xCoffee + 10)) || ((yCoffee - 10) < yPlayer && yPlayer < (yCoffee + 10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                coffeePick = true;
                Sm.soundFx();
            }
        }
    }

    public void hasPlayerPickedShoes(float xPlayer, float yPlayer) {
        if (((xShoes - 10) < xPlayer && xPlayer < (xShoes + 15)) || ((yShoes - 10) < yPlayer && yPlayer < (yShoes + 15))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                GameScreen.SPEED = 200;
                shoesPick = true;
                Sm.soundFx();
            }
        }
    }
}
