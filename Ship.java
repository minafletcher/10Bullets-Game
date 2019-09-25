import java.awt.Color;
import java.util.Random;

public class Ship extends AGamePiece {

  // standard constructor
  Ship(int radius, Color c, MyPosn position, MyPosn velocity) {
    super(radius, c, position, velocity);
  }

  // constructor used for random spawning of Ship and constant features of Ship
  Ship(Random rand) {
    this(10, Color.cyan, new MyPosn(0, 0), new MyPosn(0, 0));
    int placeHolder = rand.nextInt(2);
    if (placeHolder == 0) {
      this.position = new MyPosn(0, rand.nextInt(251) + 20);
      this.velocity = new MyPosn(4, 0);
    }
    else {
      this.position = new MyPosn(500, rand.nextInt(251) + 20);
      this.velocity = new MyPosn(-4, 0);
    }
  }

  /*
   * fields:
   * this.radius ... int
   * this.c ... Color
   * this.position ... MyPosn
   * this.velocity ... MyPosn
   * this.rand ... Random
   * this.SHIP_RADIUS ... 10
   * methods:
   * this.draw() ... WorldImage
   * this.move() ... IGamePiece
   * this.place(WorldScene background) ... WorldScene
   * this.isOffScreen(int width, int height) ... boolean
   * this.isTouchingShip(Ship that) ... boolean
   * this.isTouchingBullet(Bullet that) ... boolean
   * this.isTouching(ILoGamePiece that) ... boolean
   * this.isTouching(IGamePiece that) ... boolean
   * this.explode() ... ILoGamePiece
   * this.explodeAcc(int gen, int bullNum) ... ILoGamePiece
   * this.grow() ... int
   * methods of fields:
   * this.position.add(MyPosn p) ... MyPosn
   * this.position.isOffScreen(int width, int height) ... boolean
   * this.position.touching(MyPosn otherPosition, int radius) ... boolean
   * this.position.xCoord() ... int
   * this.position.yCoord() ... int
   * this.position.magnitude() ... int
   * this.velocity.add(MyPosn p) ... MyPosn
   * this.velocity.isOffScreen(int width, int height) ... boolean
   * this.velocity.touching(MyPosn otherPosition, int radius) ... boolean
   * this.velocity.xCoord() ... int
   * this.velocity.yCoord() ... int
   * this.velocity.magnitude() ... int
   */

  // outputs new Ship in a new position
  public IGamePiece move() {
    return new Ship(this.radius, this.c, this.position.add(this.velocity), this.velocity);
  }

  // is this Ship touching that IGamePiece?
  public boolean isTouching(IGamePiece that) {
    return that.isTouchingShip(this);
  }

  // Is this Ship touching the given Ship?
  // although a Ship can touch another Ship, we don't want to account for it
  // because nothing changes when that happens
  public boolean isTouchingShip(Ship that) {
    return false;
  }

  // explode this Ship
  public ILoGamePiece explode() {
    return new MtLoGamePiece();
  }

  // explode this Ship
  // keeping track of original generation and current bullet number
  public ILoGamePiece explodeAcc(int gen, int bullNum) {
    return new MtLoGamePiece();
  }

  // A Ship should not grow during game play
  public int grow() {
    return this.radius;
  }
}