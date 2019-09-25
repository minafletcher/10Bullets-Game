import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;

class NBullets extends World {
  ILoGamePiece bullets;
  ILoGamePiece ships;
  int bulletsLeftToFire;
  int shipsDestroyed;
  int ticks;
  Random rand;

  // standard constructor
  NBullets(ILoGamePiece bullets, ILoGamePiece ships, int bulletsLeftToFire, int shipsDestroyed,
      int ticks) {
    this(bullets, ships, bulletsLeftToFire, shipsDestroyed, ticks, new Random());
    this.bullets = bullets;
    this.ships = ships;
    this.bulletsLeftToFire = bulletsLeftToFire;
    this.shipsDestroyed = shipsDestroyed;
    this.ticks = ticks;
  }

  // constructor for grader to begin the game
  NBullets(int bulletsLeftToFire) {
    this(new MtLoGamePiece(), new MtLoGamePiece(), bulletsLeftToFire, 0, 0);
  }

  // constructor to be used for testing random ship generations
  NBullets(ILoGamePiece bullets, ILoGamePiece ships, int bulletsLeftToFire, int shipsDestroyed,
      int ticks, Random rand) {
    this.bullets = bullets;
    this.ships = ships;
    this.bulletsLeftToFire = bulletsLeftToFire;
    this.shipsDestroyed = shipsDestroyed;
    this.ticks = ticks;
    this.rand = rand;
  }

  /*
   * fields:
   * this.bullets ... ILoGamePiece
   * this.ships ... ILoGamePiece
   * this.bulletsLeftToFire ... int
   * this.shipsDestroyed ... int
   * this.ticks ... int
   * this.rand ... Random
   * methods:
   * this.onKeyEvent(String) ... NBullets
   * this.makeScene() ... WorldScene
   * this.spawnShips() ... NBullets
   * this.makeAFinalScene() ... WorldScene
   * this.worldEnds() ... worldEnd
   * this.bottomText() ... TextImage
   * this.onTick() ... NBullets
   * this.handleCollisions() ... NBullets
   * this.addTick() ... NBullets
   * this.movePieces() ... NBullets
   * this.removeOffScreenPieces(int, int) ... NBullets
   * methods for fields:
   * this.bullets.placeAll(WorldScene background) ... WorldScene
   * this.bullets.placeAllAcc(WorldScene acc) ... WorldScene
   * this.bullets.moveAll() ... ILoGamePiece
   * this.bullets.removeOffscreen() ... ILoGamePiece
   * this.bullets.isTouching(IGamePiece that) ... boolean
   * this.bullets.length() ... int
   * this.bullets.removeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.bullets.explodeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.bullets.anyLeft() ... boolean
   * this.bullets.append(ILoGamePiece that) ... ILoGamePiece
   * this.ships.placeAll(WorldScene background) ... WorldScene
   * this.ships.placeAllAcc(WorldScene acc) ... WorldScene
   * this.ships.moveAll() ... ILoGamePiece
   * this.ships.removeOffscreen() ... ILoGamePiece
   * this.ships.isTouching(IGamePiece that) ... boolean
   * this.ships.length() ... int
   * this.ships.removeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.ships.explodeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.ships.anyLeft() ... boolean
   * this.ships.append(ILoGamePiece that) ... ILoGamePiece
   */

  // handles the key event for this NBullets
  // only shoots a Bullet if the space bar is pressed and there are Bullet in the
  // bullets list in this NBullets left to fire
  public NBullets onKeyEvent(String key) {
    if (key.equals(" ") && bulletsLeftToFire > 0) {
      ILoGamePiece addBullet = new ConsLoGamePiece(new Bullet(), this.bullets);
      return new NBullets(addBullet, this.ships, this.bulletsLeftToFire - 1, this.shipsDestroyed,
          this.ticks);
    }
    else {
      return this;
    }
  }

  // renders this NBullets as an image
  public WorldScene makeScene() {
    return this.bullets.placeAll(this.ships.placeAll(new WorldScene(500, 300)))
        .placeImageXY(this.bottomText(), 130, 290);
  }

  // randomly spawns anywhere from 1 to 3 Ship, inclusive, every 1 second
  public NBullets spawnShips() {
    int spawnHelp = this.rand.nextInt(3);
    ILoGamePiece add1Ship = new ConsLoGamePiece(new Ship(this.rand), this.ships);
    ILoGamePiece add2Ship = new ConsLoGamePiece(new Ship(this.rand),
        new ConsLoGamePiece(new Ship(this.rand), this.ships));
    ILoGamePiece add3Ship = new ConsLoGamePiece(new Ship(this.rand), new ConsLoGamePiece(
        new Ship(this.rand), new ConsLoGamePiece(new Ship(this.rand), this.ships)));
    if (this.ticks % 24 != 0) {
      return this;
    }
    else if (spawnHelp == 0) {
      return new NBullets(this.bullets, add1Ship, this.bulletsLeftToFire, this.shipsDestroyed,
          this.ticks);
    }
    else if (spawnHelp == 1) {
      return new NBullets(this.bullets, add2Ship, this.bulletsLeftToFire, this.shipsDestroyed,
          this.ticks);
    }
    else {
      return new NBullets(this.bullets, add3Ship, this.bulletsLeftToFire, this.shipsDestroyed,
          this.ticks);
    }
  }

  // makes game-ending scene for this NBullets
  public WorldScene makeAFinalScene() {
    return new WorldScene(500, 300).placeImageXY(new TextImage(
        "Game over! You destroyed " + this.shipsDestroyed + " ships.", 25, Color.BLACK), 250, 150);
  }

  // ends the game when there are no Bullet in the bullets list left to fire in
  // this NBullets and no Bullet in the bullets list left on the screen in this
  // NBullets
  public WorldEnd worldEnds() {
    if (bulletsLeftToFire == 0 && !this.bullets.anyLeft()) {
      return new WorldEnd(true, this.makeAFinalScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  // renders how many Bullet in the bullets list of this NBullets you have left
  // and how many Ship in the ships list of this NBullets destroyed as an image
  public WorldImage bottomText() {
    return new TextImage(
        "Bullets left: " + this.bulletsLeftToFire + ", ships destroyed: " + this.shipsDestroyed, 15,
        Color.BLACK);
  }

  // performs all necessary game functions that need to happen in a tick in this
  // NBullets
  public NBullets onTick() {
    return this.addTick().spawnShips().movePieces().removeOffScreenPieces(500, 300)
        .handleCollisions();
  }

  // performs all necessary game functions that need to happen in a tick in this
  // NBullets
  // does the same as the function above but used for the sole purpose of testing
  public NBullets onTickTester() {
    return this.addTickTester().spawnShips().movePieces().removeOffScreenPieces(500, 300)
        .handleCollisions();
  }

  // Handles collisions of Ship and Bullet in the ships and bullets lists of this
  // NBullets
  public NBullets handleCollisions() {
    ILoGamePiece notDestroyed = this.ships.removeTouchers(this.bullets);
    int destroyees = this.ships.length() - notDestroyed.length();
    ILoGamePiece exploded = this.bullets.explodeTouchers(this.ships);
    return new NBullets(exploded, notDestroyed, this.bulletsLeftToFire,
        this.shipsDestroyed + destroyees, this.ticks);
  }

  // Adds a tick to this NBullets (How many ticks have gone by)
  public NBullets addTick() {
    return new NBullets(this.bullets, this.ships, this.bulletsLeftToFire, this.shipsDestroyed,
        this.ticks + 1);
  }

  // Adds a tick to this NBullets (How many ticks have gone by)
  // same function as above, but used for the sole purpose of testing (so we can
  // pass along a random)
  public NBullets addTickTester() {
    return new NBullets(this.bullets, this.ships, this.bulletsLeftToFire, this.shipsDestroyed,
        this.ticks + 1, this.rand);
  }

  // moves all game pieces in this NBullets
  public NBullets movePieces() {
    return new NBullets(this.bullets.moveAll(), this.ships.moveAll(), this.bulletsLeftToFire,
        this.shipsDestroyed, this.ticks);
  }

  // removes all off-screen Ship and Bullet in the ships and bullets lists of this
  // NBullets
  public NBullets removeOffScreenPieces(int width, int height) {
    return new NBullets(this.bullets.removeOffscreen(width, height),
        this.ships.removeOffscreen(width, height), this.bulletsLeftToFire, this.shipsDestroyed,
        this.ticks);
  }

}