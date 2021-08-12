/**
 * Group Project Final Deliverable
 * 12/8/2019
 * 
 * IST-240 Team 6
 * 
 * Jay Carnaghi
 * Hussein Ghaleb
 * David Logan
 * John Christian Swanson
 */

public class objMapTile {
    String id;  //ID OF THIS MAP TILE
    int xPos;   //X-POS TO PLACE PLAYER WHILE OCCUPYING THIS MAP TILE
    int yPos;   //Y-POS TO PLACE PLAYER WHILE OCCUPYING THIS MAP TILE
    int type;  //TILE TYPE - 1=OPEN TILE, 2=SCRANTON C, 3=UPARK C, 4=BEHREND C, 5=WORLDC, 6=GAMEOVER
    String tileAbove;   //THE ID OF THE TILE ABOVE THIS TILE, OR NONE IF EMPTY
    String tileBelow;   //THE ID OF THE TILE UNDER THIS TILE, OR NONE IF EMPTY
    String tileLeft;    //THE ID OF THE TILE TO THE LEFT, OR NONE IF EMPTY
    String tileRight;   //THE ID OF THE TILE TO THE RIGHT, OR NONE IF EMPTY
    boolean canMoveUp;      //CAN PLAY MOVE UP FROM THIS MAP TILE?
    boolean canMoveDown;    //CAN PLAYER MOVE DOWN FROM THIS MAP TILE?
    boolean canMoveLeft;    //CAN PLAYER MOVE LEFT FROM THIS MAP TILE?
    boolean canMoveRight;   //CAN PLAYER MOVE RIGHT FROM THIS MAP TILE?

    //DEFINE MAP TILE OBJECT
    public objMapTile(String a, int b, int c, int d, String e, String f, String g, String h, boolean i, boolean j, boolean k, boolean l){
        id = a;
        xPos = b;
        yPos = c;
        type = d;
        tileAbove = e;
        tileBelow = f;
        tileLeft = g;
        tileRight = h;
        canMoveUp = i;
        canMoveDown = j;
        canMoveLeft = k;
        canMoveRight = l;
    }

}