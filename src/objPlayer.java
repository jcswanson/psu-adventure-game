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

public class objPlayer {
    objPlayer player;
    
    String playerName;
    
    int character,
        gameTheme;
    
    boolean playerCharacterSelected,
            gameThemeSelected;
    
    String currentMapTile;
    
    int gameTime,
        game1Time,
        game2Time,
        game3Time,
        game4Time;
    
    int gameScore,
        game1Score,
        game2Score,
        game3Score,
        game4Score;
    
    boolean gameStarted,
            gameTimerPaused,
            gameCampusOnePlayed,
            gameCampusTwoPlayed,
            gameCampusThreePlayed,
            gameCampusWCPlayed,
            gameOver;
    
        public objPlayer(String a, int b, int c, boolean d, boolean e, String f, int g, int h, int i, int j, int k, int l, int m, int n, int o, int p, boolean q, boolean r, boolean s, boolean t, boolean u, boolean v, boolean w){
            playerName = a;
            character = b;
            gameTheme = c;
            playerCharacterSelected = d;     
            gameThemeSelected = e;
            currentMapTile = f;
            gameTime = g;
            game1Time = h;
            game2Time = i;
            game3Time = j;
            game4Time = k;      
            gameScore = l;
            game1Score = m;
            game2Score = n;
            game3Score = o;
            game4Score = p;
            gameStarted = q;
            gameTimerPaused = r;
            gameCampusOnePlayed = s;
            gameCampusTwoPlayed = t;   
            gameCampusThreePlayed = u;       
            gameCampusWCPlayed = v;
            gameOver = w;
        }
}