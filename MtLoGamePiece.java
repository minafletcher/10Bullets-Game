import javalib.funworld.WorldScene;

public class MtLoGamePiece implements ILoGamePiece {

  /*
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
   */

  // place all IGamePiece in this MtLoGamePiece on scene
  public WorldScene placeAll(WorldScene background) {
    return background;
  }

  // returns accumulator, as this is the end of a list
  public WorldScene placeAllAcc(WorldScene acc) {
    return acc;
  }

  // move all IGamePiece of this MtLoGamePiece
  public ILoGamePiece moveAll() {
    return this;
  }

  // remove all off-screen IGamePiece in this MtLoGamePiece
  public ILoGamePiece removeOffscreen(int width, int height) {
    return this;
  }

  // is anything in this MtLoGamePiece touching that IGamePiece?
  public boolean isTouching(IGamePiece that) {
    return false;
  }

  // how many elements in this MtLoGamePiece?
  public int length() {
    return 0;
  }

  // remove anything from this MtLoGamePiece touching something in that
  // ILoGamePiece
  public ILoGamePiece removeTouchers(ILoGamePiece that) {
    return this;
  }

  // explode elements from this MtLoGamePiece that touch elements from the given
  // ILoGamePiece
  public ILoGamePiece explodeTouchers(ILoGamePiece that) {
    return this;
  }

  // any pieces left in this MtLoGamePiece?
  public boolean anyLeft() {
    return false;
  }

  // append the given ILoGamePiece to this MtLoGamePiece
  public ILoGamePiece append(ILoGamePiece that) {
    return that;
  }

}