import javalib.funworld.WorldScene;

public class ConsLoGamePiece implements ILoGamePiece {
  IGamePiece first;
  ILoGamePiece rest;

  // standard constructor
  ConsLoGamePiece(IGamePiece first, ILoGamePiece rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * fields:
   * this.first ... IGamePiece
   * this.rest ... ILoGamePiece
   * methods:
   * this.placeAll(WorldScene background) ... WorldScene
   * this.placeAllAcc(WorldScene acc) ... WorldScene
   * this.moveAll() ... ILoGamePiece
   * this.removeOffscreen() ... ILoGamePiece
   * this.isTouching(IGamePiece that) ... boolean
   * this.length() ... int
   * this.removeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.explodeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.anyLeft() ... boolean
   * this.append(ILoGamePiece that) ... ILoGamePiece
   * methods of fields:
   * this.first.draw() ... WorldImage
   * this.first.move() ... IGamePiece
   * this.first.place(WorldScene background) ... WorldScene
   * this.first.isOffScreen(int width, int height) ... boolean
   * this.first.isTouchingShip(Ship that) ... boolean
   * this.first.isTouchingBullet(Bullet that) ... boolean
   * this.first.isTouching(ILoGamePiece that) ... boolean
   * this.first.isTouching(IGamePiece that) ... boolean
   * this.first.explode() ... ILoGamePiece
   * this.first.explodeAcc(int, int) ... ILoGamePiece
   * this.first.grow() ... int
   * this.rest.placeAll(WorldScene background) ... WorldScene
   * this.rest.placeAllAcc(WorldScene acc) ... WorldScene
   * this.rest.moveAll() ... ILoGamePiece
   * this.rest.removeOffscreen() ... ILoGamePiece
   * this.rest.isTouching(IGamePiece that) ... boolean
   * this.rest.length() ... int
   * this.rest.removeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.rest.explodeTouchers(ILoGamePiece that) ... ILoGamePiece
   * this.rest.anyLeft() ... boolean
   * this.rest.append(ILoGamePiece that) ... ILoGamePiece
   */

  // place everything in this ConsLoGamePiece on the background
  public WorldScene placeAll(WorldScene background) {
    return this.placeAllAcc(background);
  }

  // place everything in this ConsLoGamePiece on background, accumulating
  // background so far
  public WorldScene placeAllAcc(WorldScene acc) {
    return this.rest.placeAllAcc(this.first.place(acc));
  }

  // move all IGamePiece in this ConsLoGamePiece
  public ILoGamePiece moveAll() {
    return new ConsLoGamePiece(this.first.move(), this.rest.moveAll());
  }

  // remove any off-screen IGamePiece from this ConsLoGamePiece
  public ILoGamePiece removeOffscreen(int width, int height) {
    if (this.first.isOffScreen(width, height)) {
      return this.rest.removeOffscreen(width, height);
    }
    else {
      return new ConsLoGamePiece(this.first, this.rest.removeOffscreen(width, height));
    }
  }

  // is anything in this ConsLoGamePiece touching that IGamePiece?
  public boolean isTouching(IGamePiece that) {
    return this.first.isTouching(that) || this.rest.isTouching(that);
  }

  // returns the length of this ConsLoGamePiece
  public int length() {
    return 1 + this.rest.length();
  }

  // removes IGamePiece that are touching an IGamePiece in the given ILoGamePiece
  public ILoGamePiece removeTouchers(ILoGamePiece that) {
    if (this.first.isTouching(that)) {
      return this.rest.removeTouchers(that);
    }
    else {
      return new ConsLoGamePiece(this.first, this.rest.removeTouchers(that));
    }
  }

  // explode anything in this ConsLoGamePiece that is touching anything in that
  // ILoGamePiece
  public ILoGamePiece explodeTouchers(ILoGamePiece that) {
    if (this.first.isTouching(that)) {
      return this.rest.explodeTouchers(that).append(this.first.explode());
    }
    else {
      return new ConsLoGamePiece(this.first, this.rest.explodeTouchers(that));
    }
  }

  // is there anything left in this ConsLoGamePiece?
  public boolean anyLeft() {
    return true;
  }

  // appends everything in the given list onto this list
  public ILoGamePiece append(ILoGamePiece that) {
    return new ConsLoGamePiece(this.first, this.rest.append(that));
  }

}