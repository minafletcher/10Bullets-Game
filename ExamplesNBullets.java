import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;

class ExamplesNBullets {
  MyPosn p1 = new MyPosn(50, 50);
  MyPosn p2 = new MyPosn(0, 150);
  MyPosn p3 = new MyPosn(50, 50);
  MyPosn p4 = new MyPosn(600, 600);
  MyPosn p5 = new MyPosn(250, 50);
  MyPosn p6 = new MyPosn(1, 0);
  MyPosn p7 = new MyPosn(-1, 0);

  WorldScene em = new WorldScene(500, 300);

  IGamePiece ship1 = new Ship(10, Color.CYAN, this.p2, this.p3);
  IGamePiece ship2 = new Ship(10, Color.CYAN, this.p1, this.p6);
  IGamePiece ship3 = new Ship(10, Color.CYAN, this.p3, this.p6);
  IGamePiece ship4 = new Ship(10, Color.CYAN, new MyPosn(300, 450), new MyPosn(3, 3));
  IGamePiece ship5 = new Ship(10, Color.CYAN, new MyPosn(310, 460), new MyPosn(3, 3));
  IGamePiece ship6 = new Ship(10, Color.CYAN, new MyPosn(312, 462), new MyPosn(3, 3));
  IGamePiece ship7 = new Ship(10, Color.CYAN, new MyPosn(290, 440), new MyPosn(3, 3));
  IGamePiece ship8 = new Ship(10, Color.CYAN, new MyPosn(250, 600), new MyPosn(3, 3));
  IGamePiece ship9 = new Ship(8, Color.CYAN, new MyPosn(214, 214), new MyPosn(3, 3));
  IGamePiece ship10 = new Ship(10, Color.CYAN, new MyPosn(300, 300), new MyPosn(-10, -30));
  Ship ship11 = new Ship(10, Color.CYAN, new MyPosn(300, 300), new MyPosn(-10, -30));
  Ship ship12 = new Ship(10, Color.CYAN, new MyPosn(300, 450), new MyPosn(3, 3));
  Ship ship13 = new Ship(10, Color.CYAN, new MyPosn(310, 460), new MyPosn(3, 3));
  Ship ship14 = new Ship(10, Color.CYAN, new MyPosn(312, 462), new MyPosn(3, 3));
  Ship ship15 = new Ship(10, Color.CYAN, new MyPosn(290, 440), new MyPosn(3, 3));
  Ship ship16 = new Ship(10, Color.CYAN, new MyPosn(250, 600), new MyPosn(3, 3));

  IGamePiece bullet1 = new Bullet();
  IGamePiece bullet2 = new Bullet(2, Color.PINK, this.p4, this.p3, 1);
  IGamePiece bullet3 = new Bullet(2, Color.PINK, new MyPosn(300, 450), new MyPosn(2, 2), 1);
  IGamePiece bullet4 = new Bullet(6, Color.PINK, new MyPosn(200, 200), new MyPosn(1, 1), 8);
  IGamePiece bullet5 = new Bullet(4, Color.PINK, new MyPosn(300, 200), new MyPosn(1, 1), 8);
  Bullet bullet6 = new Bullet(2, Color.PINK, new MyPosn(300, 450), new MyPosn(2, 2), 1);
  Bullet bullet7 = new Bullet();
  IGamePiece bullet8 = new Bullet(2, Color.PINK, this.p1, this.p6, 1);
  IGamePiece bullet9 = new Bullet(2, Color.PINK, this.p3, this.p6, 1);

  ILoGamePiece mt = new MtLoGamePiece();
  ILoGamePiece los1 = new ConsLoGamePiece(this.ship1, this.mt);
  ILoGamePiece los2 = new ConsLoGamePiece(this.ship2, this.los1);
  ILoGamePiece los3 = new ConsLoGamePiece(this.ship3, this.los2);
  ILoGamePiece los4 = new ConsLoGamePiece(this.ship4, this.los3);

  ILoGamePiece lob1 = new ConsLoGamePiece(this.bullet1, this.mt);
  ILoGamePiece lob2 = new ConsLoGamePiece(this.bullet8, lob1);
  ILoGamePiece lob3 = new ConsLoGamePiece(this.bullet9, lob2);
  ILoGamePiece lob4 = new ConsLoGamePiece(this.bullet3, lob2);
  ILoGamePiece lob5 = new ConsLoGamePiece(this.bullet3, new ConsLoGamePiece(this.bullet2, this.mt));

  boolean testBigBang(Tester t) {
    NBullets world = new NBullets(10);
    int worldWidth = 500;
    int worldHeight = 300;
    double tickRate = 0.04;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }

  // MYPOSN TESTERS
  // ----------------------------------------------------------------------------
  // test isOffScreen for MyPosn class
  boolean testOffScreenPosn(Tester t) {
    return t.checkExpect(this.p1.isOffScreen(200, 200), false)
        && t.checkExpect(this.p4.isOffScreen(200, 200), true)
        && t.checkExpect(this.p5.isOffScreen(200, 200), true)
        && t.checkExpect(new MyPosn(-10, 40).isOffScreen(200, 200), true)
        && t.checkExpect(new MyPosn(10, -40).isOffScreen(200, 200), true)
        && t.checkExpect(new MyPosn(200, 200).isOffScreen(200, 200), false);
  }

  // test add method for MyPosn class
  boolean testAdd(Tester t) {
    return t.checkExpect(this.p5.add(this.p7), new MyPosn(249, 50))
        && t.checkExpect(this.p6.add(this.p1), new MyPosn(51, 50));
  }

  // test touching method for MyPosn class
  boolean testTouching(Tester t) {
    return t.checkExpect(this.p5.touching(p6, 8), false)
        && t.checkExpect(new MyPosn(13, 6).touching(new MyPosn(6, 6), 7), true)
        && t.checkExpect(new MyPosn(6, 13).touching(new MyPosn(6, 6), 7), true)
        && t.checkExpect(new MyPosn(5, 6).touching(new MyPosn(6, 6), 5), true)
        && t.checkExpect(new MyPosn(6, 3).touching(new MyPosn(6, 6), 8), true)
        && t.checkExpect(new MyPosn(6, 6).touching(new MyPosn(13, 6), 7), true)
        && t.checkExpect(new MyPosn(6, 6).touching(new MyPosn(6, 13), 7), true);
  }

  // test xCoord and yCoord method for MyPosn class
  boolean testCoords(Tester t) {
    return t.checkExpect(this.p3.xCoord(), 50) && t.checkExpect(this.p4.yCoord(), 600);
  }

  // test magnitude() method for MyPosn class
  boolean testMag(Tester t) {
    return t.checkExpect(this.p6.magnitude(), 1) && t.checkExpect(this.p7.magnitude(), 1)
        && t.checkExpect(this.p2.magnitude(), 150);
  }

  // SHIP TESTERS
  // -------------------------------------------------------------------------

  // test move method for Ship class
  boolean testMoveShip(Tester t) {
    return t.checkExpect(ship1.move(),
        new Ship(10, Color.CYAN, new MyPosn(50, 200), new MyPosn(50, 50)))
        && t.checkExpect(ship10.move(),
            new Ship(10, Color.CYAN, new MyPosn(290, 270), new MyPosn(-10, -30)));
  }

  // test draw method for Ship class
  boolean testDrawShip(Tester t) {
    return t.checkExpect(ship3.draw(), new CircleImage(10, OutlineMode.SOLID, Color.CYAN))
        && t.checkExpect(ship1.draw(), new CircleImage(10, OutlineMode.SOLID, Color.CYAN));
  }

  // test place method for Ship class
  boolean testPlaceShip(Tester t) {
    return t.checkExpect(ship3.place(this.em),
        em.placeImageXY(new CircleImage(10, OutlineMode.SOLID, Color.CYAN), 50, 50))
        && t.checkExpect(ship1.place(this.em),
            em.placeImageXY(new CircleImage(10, OutlineMode.SOLID, Color.CYAN), 0, 150));
  }

  // test isOffScreen for Ship class
  boolean testOffscreenShip(Tester t) {
    return t.checkExpect(this.ship1.isOffScreen(100, 100), true)
        && t.checkExpect(this.ship1.isOffScreen(200, 200), false)
        && t.checkExpect(this.ship3.isOffScreen(50, 50), false);
  }

  // test isTouching for Ship class
  boolean testIsTouchingShip(Tester t) {
    return t.checkExpect(
        this.ship1.isTouching(new Bullet(7, Color.PINK, new MyPosn(1, 150), this.p7, 2)), true)
        && t.checkExpect(ship4.isTouching(bullet3), true)
        && t.checkExpect(ship5.isTouching(bullet3), true)
        && t.checkExpect(ship6.isTouching(bullet3), true)
        && t.checkExpect(ship7.isTouching(bullet3), true)
        && t.checkExpect(ship7.isTouching(bullet1), false)
        && t.checkExpect(ship8.isTouching(bullet1), false)
        && t.checkExpect(ship9.isTouching(bullet4), true)
        && t.checkExpect(ship9.isTouching(bullet5), false)
        && t.checkExpect(ship9.isTouching(ship9), false);
  }

  // test isTouchingShip for Ship class
  boolean testIsTouchingShipShip(Tester t) {
    return t.checkExpect(ship1.isTouchingShip(ship11), false)
        && t.checkExpect(ship11.isTouchingShip(ship11), false);
  }

  // test isTouchingBullet for Ship class
  boolean testIsTouchingBulletShip(Tester t) {
    return t.checkExpect(ship4.isTouchingBullet(bullet6), true)
        && t.checkExpect(ship5.isTouchingBullet(bullet6), true)
        && t.checkExpect(ship6.isTouchingBullet(bullet6), true)
        && t.checkExpect(ship7.isTouchingBullet(bullet6), true)
        && t.checkExpect(ship7.isTouchingBullet(bullet7), false)
        && t.checkExpect(ship8.isTouchingBullet(bullet7), false);
  }

  // test explode for Ship class
  boolean testShipExplode(Tester t) {
    return t.checkExpect(this.ship1.explode(), this.mt)
        && t.checkExpect(this.ship3.explode(), this.mt);
  }

  // test explodeAcc for Ship class
  boolean testShipExplodeAcc(Tester t) {
    return t.checkExpect(this.ship1.explodeAcc(0, 0), this.mt)
        && t.checkExpect(this.ship12.explodeAcc(10, 20), this.mt);
  }

  // test grow for Ship class
  boolean shipGrow(Tester t) {
    return t.checkExpect(this.ship1.grow(), 10) && t.checkExpect(this.ship9.grow(), 8);
  }

  // test isTouching(ILoGamePiece that) for Ship class
  boolean testIsTouchingILoGamePieceShip(Tester t) {
    return t.checkExpect(ship2.isTouching(los2), false)
        && t.checkExpect(ship2.isTouching(lob2), true)
        && t.checkExpect(ship3.isTouching(lob3), true)
        && t.checkExpect(ship3.isTouching(mt), false);
  }

  // BULLET CLASS
  // -----------------------------------------------------------------------

  // test draw for Bullet class
  boolean testDrawBullet(Tester t) {
    return t.checkExpect(bullet1.draw(), new CircleImage(2, OutlineMode.SOLID, Color.pink))
        && t.checkExpect(bullet2.draw(), new CircleImage(2, OutlineMode.SOLID, Color.pink));
  }

  // test move method for Bullet class
  boolean testMoveBullet(Tester t) {
    return t.checkExpect(bullet1.move(),
        new Bullet(2, Color.PINK, new MyPosn(250, 292), new MyPosn(0, -8), 1))
        && t.checkExpect(bullet2.move(),
            new Bullet(2, Color.PINK, new MyPosn(650, 650), new MyPosn(50, 50), 1));
  }

  // test place method for Bullet class
  boolean testPlaceBullet(Tester t) {
    return t.checkExpect(bullet1.place(this.em),
        em.placeImageXY(new CircleImage(2, OutlineMode.SOLID, Color.PINK), 250, 300))
        && t.checkExpect(bullet2.place(this.em),
            em.placeImageXY(new CircleImage(2, OutlineMode.SOLID, Color.PINK), 600, 600));
  }

  // test isOffScreen method for Bullet class
  boolean testIsOffScreenBullet(Tester t) {
    return t.checkExpect(bullet1.isOffScreen(0, 0), true)
        && t.checkExpect(bullet2.isOffScreen(1000, 1000), false)
        && t.checkExpect(bullet1.isOffScreen(250, 300), false);
  }

  // test isTouching(IGamePiece that) for Bullet class
  boolean testIsTouchingBullet(Tester t) {
    return t.checkExpect(
        this.bullet9.isTouching(new Bullet(7, Color.PINK, new MyPosn(1, 150), this.p7, 2)), false)
        && t.checkExpect(bullet3.isTouching(ship4), true)
        && t.checkExpect(bullet3.isTouching(ship5), true)
        && t.checkExpect(bullet3.isTouching(ship6), true)
        && t.checkExpect(bullet3.isTouching(ship7), true)
        && t.checkExpect(bullet1.isTouching(ship7), false)
        && t.checkExpect(bullet1.isTouching(ship8), false)
        && t.checkExpect(bullet4.isTouching(ship9), true)
        && t.checkExpect(bullet5.isTouching(ship9), false);
  }

  // test isTouchingBullet for Bullet class
  boolean testIsTouchingBulletBullet(Tester t) {
    return t.checkExpect(bullet3.isTouchingBullet(bullet6), false)
        && t.checkExpect(bullet6.isTouchingBullet(bullet6), false);
  }

  // test isTouchingShip method for Bullet class
  boolean testIsTouchingShipBullet(Tester t) {
    return t.checkExpect(bullet3.isTouchingShip(ship12), true)
        && t.checkExpect(bullet3.isTouchingShip(ship13), true)
        && t.checkExpect(bullet3.isTouchingShip(ship14), true)
        && t.checkExpect(bullet3.isTouchingShip(ship15), true)
        && t.checkExpect(bullet1.isTouchingShip(ship15), false)
        && t.checkExpect(bullet1.isTouchingShip(ship16), false);
  }

  // test explode for Bullet class
  boolean testBulletExplode(Tester t) {
    return t.checkExpect(
        new Bullet(2, Color.PINK, new MyPosn(50, 50), new MyPosn(0, -8), 1).explode(),
        new ConsLoGamePiece(new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(8, 0), 2),
            new ConsLoGamePiece(new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(-8, 0), 2),
                this.mt)))
        && t.checkExpect(
            new Bullet(4, Color.PINK, new MyPosn(100, 100), new MyPosn(0, 4), 2).explode(),
            new ConsLoGamePiece(
                new Bullet(6, Color.PINK, new MyPosn(100, 100), new MyPosn(4, 0), 3),
                new ConsLoGamePiece(
                    new Bullet(6, Color.PINK, new MyPosn(100, 100), new MyPosn(-2, 3), 3),
                    new ConsLoGamePiece(
                        new Bullet(6, Color.PINK, new MyPosn(100, 100), new MyPosn(-2, -3), 3),
                        this.mt))))
        && t.checkExpect(
            new Bullet(2, Color.PINK, new MyPosn(50, 50), new MyPosn(0, -8), -1).explode(),
            new MtLoGamePiece());
  }

  // test explodeAcc(int, int) for Bullet class
  boolean testBulletExplodeAcc(Tester t) {
    return t.checkExpect(
        new Bullet(2, Color.PINK, new MyPosn(50, 50), new MyPosn(0, -8), 1).explodeAcc(1, 0),
        new ConsLoGamePiece(new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(8, 0), 2),
            new ConsLoGamePiece(new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(-8, 0), 2),
                this.mt)))
        && t.checkExpect(
            new Bullet(2, Color.PINK, new MyPosn(50, 50), new MyPosn(0, -8), 1).explodeAcc(1, 1),
            new ConsLoGamePiece(new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(-8, 0), 2),
                this.mt))
        && t.checkExpect(new Bullet().explodeAcc(3, 8), new MtLoGamePiece());
  }

  // test grow for Bullet class
  boolean bulletGrow(Tester t) {
    return t.checkExpect(this.bullet2.grow(), 4) && t.checkExpect(this.bullet4.grow(), 8);
  }

  // test isTouching(ILoGamePiece that) for Bullet class
  boolean testIsTouchingILoGamePieceBullet(Tester t) {
    return t.checkExpect(bullet9.isTouching(los3), true)
        && t.checkExpect(bullet1.isTouching(los3), false)
        && t.checkExpect(bullet9.isTouching(lob3), false);
  }

  // ILOGAMEPIECE TESTS ---------------------------------------------------------

  // test placeAll method for ILoGamePiece (with Ship)
  boolean testPlaceAllShips(Tester t) {
    return t.checkExpect(this.los3.placeAll(this.em),
        em.placeImageXY(ship1.draw(), 0, 150).placeImageXY(ship2.draw(), 50, 50)
            .placeImageXY(ship3.draw(), 50, 50))
        && t.checkExpect(this.mt.placeAll(this.em), this.em);
  }

  // test placeAllAcc method for ILoGamePiece (with Ship)
  boolean testPlaceAllShipsAcc(Tester t) {
    return t.checkExpect(this.los3.placeAllAcc(this.em),
        em.placeImageXY(ship1.draw(), 0, 150).placeImageXY(ship2.draw(), 50, 50)
            .placeImageXY(ship3.draw(), 50, 50))
        && t.checkExpect(
            this.los1.placeAllAcc(
                this.em.placeImageXY(ship2.draw(), 50, 50).placeImageXY(ship3.draw(), 50, 50)),
            em.placeImageXY(ship1.draw(), 0, 150).placeImageXY(ship2.draw(), 50, 50)
                .placeImageXY(ship3.draw(), 50, 50))
        && t.checkExpect(this.mt.placeAllAcc(new WorldScene(300, 700)), new WorldScene(300, 700));
  }

  // test moveAll method for ILoGamePiece (with Ship)
  boolean testMoveAllShips(Tester t) {
    return t.checkExpect(this.los3.moveAll(), new ConsLoGamePiece(
        new Ship(10, Color.CYAN, new MyPosn(51, 50), this.p6),
        new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(51, 50), this.p6),
            new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(50, 200), this.p3), this.mt))))
        && t.checkExpect(this.mt.moveAll(), this.mt);
  }

  // test removeOffscreen for ILoGamePiece (with Ship)
  boolean removeOffScreenShips(Tester t) {
    return t.checkExpect(this.los3.removeOffscreen(100, 100),
        new ConsLoGamePiece(this.ship3, new ConsLoGamePiece(this.ship1, this.mt)))
        && t.checkExpect(this.los3.removeOffscreen(200, 200), this.los3)
        && t.checkExpect(this.mt.removeOffscreen(10, 10), this.mt);
  }

  // test isTouching(IGamePiece that) for ILoGamePiece
  boolean testIsTouchingIGamePiece(Tester t) {
    return t.checkExpect(los2.isTouching(ship2), false)
        && t.checkExpect(los3.isTouching(bullet8), true)
        && t.checkExpect(lob2.isTouching(ship2), true)
        && t.checkExpect(lob3.isTouching(ship11), false)
        && t.checkExpect(lob3.isTouching(ship3), true)
        && t.checkExpect(mt.isTouching(ship6), false);
  }

  // test placeAll for a ILoGamePiece (with Bullet)
  boolean testPlaceAllBullets(Tester t) {
    return t.checkExpect(this.lob3.placeAll(this.em),
        em.placeImageXY(bullet1.draw(), 250, 300).placeImageXY(bullet8.draw(), 50, 50)
            .placeImageXY(bullet9.draw(), 50, 50))
        && t.checkExpect(this.mt.placeAll(this.em), this.em);
  }

  // test placeAllAcc for ILoGamePiece (with Bullet)
  boolean testPlaceAllAccBullets(Tester t) {
    return t.checkExpect(this.lob3.placeAllAcc(this.em),
        em.placeImageXY(bullet1.draw(), 250, 300).placeImageXY(bullet8.draw(), 50, 50)
            .placeImageXY(bullet9.draw(), 50, 50))
        && t.checkExpect(
            this.lob1.placeAllAcc(
                this.em.placeImageXY(bullet8.draw(), 50, 50).placeImageXY(bullet9.draw(), 50, 50)),
            em.placeImageXY(bullet1.draw(), 250, 300).placeImageXY(bullet8.draw(), 50, 50)
                .placeImageXY(bullet9.draw(), 50, 50))
        && t.checkExpect(this.mt.placeAllAcc(new WorldScene(400, 800)), new WorldScene(400, 800));
  }

  // test moveAll for ILoGamePiece (with Bullet)
  boolean testMoveAll(Tester t) {
    return t.checkExpect(this.lob3.moveAll(), new ConsLoGamePiece(
        new Bullet(2, Color.PINK, new MyPosn(51, 50), this.p6, 1),
        new ConsLoGamePiece(new Bullet(2, Color.PINK, new MyPosn(51, 50), this.p6, 1),
            new ConsLoGamePiece(
                new Bullet(2, Color.PINK, new MyPosn(250, 292), new MyPosn(0, -8), 1), this.mt))))
        && t.checkExpect(this.mt.moveAll(), this.mt);
  }

  // test removeOffscreen for ILoGamePiece (with Bullet)
  boolean testRemoveOffscreenBullets(Tester t) {
    return t.checkExpect(lob3.removeOffscreen(100, 100),
        new ConsLoGamePiece(bullet9, new ConsLoGamePiece(bullet8, mt)))
        && t.checkExpect(lob3.removeOffscreen(300, 300), lob3)
        && t.checkExpect(lob3.removeOffscreen(500, 500), lob3)
        && t.checkExpect(mt.removeOffscreen(0, 0), mt);
  }

  // test length for ILoGamePiece
  boolean testLength(Tester t) {
    return t.checkExpect(lob3.length(), 3) && t.checkExpect(los3.length(), 3)
        && t.checkExpect(los1.length(), 1) && t.checkExpect(new MtLoGamePiece().length(), 0);
  }

  // test removeTouchers(ILoGamePiece) for ILoGamePiece
  boolean testRemoveTouchers(Tester t) {
    return t.checkExpect(this.los3.removeTouchers(this.los2), this.los3)
        && t.checkExpect(this.mt.removeTouchers(this.los2), this.mt)
        && t.checkExpect(this.los3.removeTouchers(this.mt), this.los3)
        && t.checkExpect(this.lob3.removeTouchers(this.lob2), this.lob3)
        && t.checkExpect(this.los4.removeTouchers(this.lob4),
            new ConsLoGamePiece(this.ship1, this.mt))
        && t.checkExpect(this.lob4.removeTouchers(this.los4),
            new ConsLoGamePiece(this.bullet1, this.mt));
  }

  // test explodeTouchers for ILoGamePiece
  boolean testExplodeTouchers(Tester t) {
    return t.checkExpect(new MtLoGamePiece().explodeTouchers(lob3), new MtLoGamePiece())
        && t.checkExpect(lob3.explodeTouchers(lob2), new ConsLoGamePiece(
            new Bullet(2, Color.PINK, new MyPosn(50, 50), new MyPosn(1, 0), 1),
            new ConsLoGamePiece(new Bullet(2, Color.PINK, new MyPosn(50, 50), new MyPosn(1, 0), 1),
                new ConsLoGamePiece(
                    new Bullet(2, Color.PINK, new MyPosn(250, 300), new MyPosn(0, -8), 1),
                    new MtLoGamePiece()))))
        && t.checkExpect(los3.explodeTouchers(los2),
            new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(50, 50), new MyPosn(1, 0)),
                new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(50, 50), new MyPosn(1, 0)),
                    new ConsLoGamePiece(
                        new Ship(10, Color.CYAN, new MyPosn(0, 150), new MyPosn(50, 50)),
                        new MtLoGamePiece()))))
        && t.checkExpect(los3.explodeTouchers(lob3),
            new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(0, 150), new MyPosn(50, 50)),
                new MtLoGamePiece()))
        && t.checkExpect(lob3.explodeTouchers(los2), new ConsLoGamePiece(
            new Bullet(2, Color.PINK, new MyPosn(250, 300), new MyPosn(0, -8), 1),
            new ConsLoGamePiece(new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(1, 0), 2),
                new ConsLoGamePiece(new Bullet(4, Color.PINK, this.p1, new MyPosn(-1, 0), 2),
                    new ConsLoGamePiece(
                        new Bullet(4, Color.PINK, new MyPosn(50, 50), new MyPosn(1, 0), 2),
                        new ConsLoGamePiece(
                            new Bullet(4, Color.PINK, this.p1, new MyPosn(-1, 0), 2),
                            new MtLoGamePiece()))))));
  }

  // test anyLeft() method for ILoGamePiece
  boolean testAnyLeft(Tester t) {
    return t.checkExpect(this.mt.anyLeft(), false) && t.checkExpect(this.los3.anyLeft(), true)
        && t.checkExpect(this.lob1.anyLeft(), true);
  }

  // test append method for ILoGamePiece
  boolean testAppend(Tester t) {
    return t.checkExpect(this.mt.append(los3), los3)
        && t.checkExpect(this.mt.append(this.mt), this.mt)
        && t.checkExpect(this.lob3.append(los3),
            new ConsLoGamePiece(this.bullet9,
                new ConsLoGamePiece(this.bullet8,
                    new ConsLoGamePiece(this.bullet1,
                        new ConsLoGamePiece(this.ship3,
                            new ConsLoGamePiece(this.ship2,
                                new ConsLoGamePiece(this.ship1, new MtLoGamePiece())))))))
        && t.checkExpect(this.lob3.append(this.mt), this.lob3);
  }

  // TEST NBULLETS CLASS
  // ------------------------------------------------------------------------

  // test the onKeyEvent for the NBullets class
  boolean testOnKeyEventString(Tester t) {
    return t.checkExpect(new NBullets(20).onKeyEvent(" "),
        new NBullets(new ConsLoGamePiece(new Bullet(), new MtLoGamePiece()), new MtLoGamePiece(),
            19, 0, 0))
        && t.checkExpect(new NBullets(0).onKeyEvent(" "), new NBullets(0))
        && t.checkExpect(new NBullets(10).onKeyEvent("hi"), new NBullets(10))
        && t.checkExpect(new NBullets(0).onKeyEvent("hi"), new NBullets(0));
  }

  // makeScene() for NBullets class
  boolean testMakeScene(Tester t) {
    return t.checkExpect(new NBullets(10).makeScene(),
        new WorldScene(500, 300).placeImageXY(
            new TextImage("Bullets left: 10, ships destroyed: 0", 15, Color.BLACK), 130, 290))
        && t.checkExpect(new NBullets(this.lob1, this.los1, 9, 2, 0).makeScene(),
            this.bullet1.place(this.ship1.place(new WorldScene(500, 300))).placeImageXY(
                new TextImage("Bullets left: 9, ships destroyed: 2", 15, Color.BLACK), 130, 290));
  }

  // test spawnShips method for the NBullets class
  boolean testSpawnShips(Tester t) {
    return t.checkExpect(new NBullets(lob3, los3, 8, 12, 23, new Random(10)).spawnShips(),
        new NBullets(lob3, los3, 8, 12, 23))
        && t.checkExpect(new NBullets(lob3, los3, 8, 12, 24, new Random(10)).spawnShips(),
            new NBullets(lob3,
                new ConsLoGamePiece(new Ship(10, Color.cyan, new MyPosn(0, 127), new MyPosn(4, 0)),
                    new ConsLoGamePiece(new Ship(10, Color.cyan, this.p3, this.p6),
                        new ConsLoGamePiece(new Ship(10, Color.cyan, this.p1, this.p6),
                            new ConsLoGamePiece(
                                new Ship(10, Color.cyan, new MyPosn(0, 150), this.p3),
                                new MtLoGamePiece())))),
                8, 12, 24));
  }

  // test makeAFinalScene() for NBullets class
  boolean testFinalScene(Tester t) {
    return t.checkExpect(new NBullets(10).makeAFinalScene(),
        new WorldScene(500, 300).placeImageXY(
            new TextImage("Game over! You destroyed 0 ships.", 25, Color.BLACK), 250, 150))
        && t.checkExpect(new NBullets(this.mt, this.los1, 0, 42, 0).makeAFinalScene(),
            new WorldScene(500, 300).placeImageXY(
                new TextImage("Game over! You destroyed 42 ships.", 25, Color.BLACK), 250, 150));
  }

  // test worldEnds for NBullets class
  boolean testWorldEnds(Tester t) {
    return t.checkExpect(new NBullets(0).worldEnds(),
        new WorldEnd(true, new NBullets(0).makeAFinalScene()))
        && t.checkExpect(
            new NBullets(new ConsLoGamePiece(bullet9, new MtLoGamePiece()), new MtLoGamePiece(), 0,
                0, 0).worldEnds(),
            new WorldEnd(false,
                new NBullets(new ConsLoGamePiece(bullet9, new MtLoGamePiece()), new MtLoGamePiece(),
                    0, 0, 0).makeScene()))
        && t.checkExpect(
            new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 0).worldEnds(),
            new WorldEnd(false,
                new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 0).makeScene()))
        && t.checkExpect(
            new NBullets(new ConsLoGamePiece(bullet9, new MtLoGamePiece()), new MtLoGamePiece(), 15,
                0, 0).worldEnds(),
            new WorldEnd(false, new NBullets(new ConsLoGamePiece(bullet9, new MtLoGamePiece()),
                new MtLoGamePiece(), 15, 0, 0).makeScene()));
  }

  // test bottomText() for NBullets class
  boolean testBottomText(Tester t) {
    return t.checkExpect(new NBullets(10).bottomText(),
        new TextImage("Bullets left: 10, ships destroyed: 0", 15, Color.BLACK))
        && t.checkExpect(new NBullets(this.mt, this.los1, 5, 42, 0).bottomText(),
            new TextImage("Bullets left: 5, ships destroyed: 42", 15, Color.BLACK));
  }

  // test OnTick (using the onTickTester method) for NBullets class
  boolean testOnTick(Tester t) {
    return t
        .checkExpect(new NBullets(0).onTickTester(), new NBullets(new MtLoGamePiece(),
            new MtLoGamePiece(), 0, 0, 1))
        && t.checkExpect(new NBullets(lob2, los3, 8, 12, 23, new Random(2)).onTickTester(),
            new NBullets(
                new ConsLoGamePiece(
                    new Bullet(2, Color.PINK, new MyPosn(250, 292), new MyPosn(0, -8), 1),
                    new ConsLoGamePiece(
                        new Bullet(4, Color.PINK, new MyPosn(51, 50), new MyPosn(1, 0), 2),
                        new ConsLoGamePiece(
                            new Bullet(4, Color.PINK, new MyPosn(51, 50), new MyPosn(-1, 0), 2),
                            new MtLoGamePiece()))),
                new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(4, 239), new MyPosn(4, 0)),
                    new ConsLoGamePiece(
                        new Ship(10, Color.CYAN, new MyPosn(496, 248), new MyPosn(-4, 0)),
                        new ConsLoGamePiece(
                            new Ship(10, Color.CYAN, new MyPosn(50, 200), new MyPosn(50, 50)),
                            new MtLoGamePiece()))),
                8, 14, 24))
        && t.checkExpect(new NBullets(lob5, los3, 8, 12, 23, new Random(2)).onTickTester(),
            new NBullets(new MtLoGamePiece(), new ConsLoGamePiece(
                new Ship(10, Color.CYAN, new MyPosn(4, 239), new MyPosn(4, 0)),
                new ConsLoGamePiece(
                    new Ship(10, Color.CYAN, new MyPosn(496, 248), new MyPosn(-4, 0)),
                    new ConsLoGamePiece(
                        new Ship(10, Color.CYAN, new MyPosn(51, 50), new MyPosn(1, 0)),
                        new ConsLoGamePiece(
                            new Ship(10, Color.CYAN, new MyPosn(51, 50), new MyPosn(1, 0)),
                            new ConsLoGamePiece(
                                new Ship(10, Color.CYAN, new MyPosn(50, 200), new MyPosn(50, 50)),
                                new MtLoGamePiece()))))),
                8, 12, 24));
  }

  // test handleCollisions() for NBullets class
  boolean testHandleCollisions(Tester t) {
    return t.checkExpect(new NBullets(10).handleCollisions(), new NBullets(10))
        && t.checkExpect(new NBullets(this.mt, this.los3, 3, 2, 40).handleCollisions(),
            new NBullets(this.mt, this.los3, 3, 2, 40))
        && t.checkExpect(new NBullets(this.lob3, this.mt, 3, 2, 40).handleCollisions(),
            new NBullets(this.lob3, this.mt, 3, 2, 40))
        && t.checkExpect(new NBullets(this.lob4, this.los4, 5, 5, 0).handleCollisions(),
            new NBullets(this.lob4.explodeTouchers(this.los4),
                new ConsLoGamePiece(this.ship1, this.mt), 5, 8, 0));
  }

  // test addTick for NBullets class
  boolean testAddTick(Tester t) {
    return t.checkExpect(new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 0).addTick(),
        new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 1))
        && t.checkExpect(new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 3).addTick(),
            new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 4));
  }

  // test removeOffScreenPieces() for the NBullets class
  boolean testRemoveOffScreenPieces(Tester t) {
    return t.checkExpect(new NBullets(10).removeOffScreenPieces(500, 300), new NBullets(10))
        && t.checkExpect(
            new NBullets(this.lob3, this.los3, 5, 5, 0).removeOffScreenPieces(100, 100),
            new NBullets(
                new ConsLoGamePiece(this.bullet9, new ConsLoGamePiece(this.bullet8, this.mt)),
                new ConsLoGamePiece(this.ship3, new ConsLoGamePiece(this.ship2, this.mt)), 5, 5, 0))
        && t.checkExpect(new NBullets(this.mt, this.los3, 5, 5, 0).removeOffScreenPieces(100, 100),
            new NBullets(this.mt,
                new ConsLoGamePiece(this.ship3, new ConsLoGamePiece(this.ship2, this.mt)), 5, 5, 0))
        && t.checkExpect(new NBullets(this.lob3, this.mt, 5, 5, 0).removeOffScreenPieces(100, 100),
            new NBullets(
                new ConsLoGamePiece(this.bullet9, new ConsLoGamePiece(this.bullet8, this.mt)),
                this.mt, 5, 5, 0));
  }

  // test movePieces for NBullets class
  boolean testMovePieces(Tester t) {
    return t
        .checkExpect(new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 0)
            .movePieces(), new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 0))
        && t.checkExpect(new NBullets(lob3, los3, 15, 0, 0).movePieces(),
            new NBullets(
                new ConsLoGamePiece(new Bullet(2, Color.PINK, new MyPosn(51, 50), this.p6, 1),
                    new ConsLoGamePiece(
                        new Bullet(2, Color.PINK, new MyPosn(51, 50), this.p6, 1),
                        new ConsLoGamePiece(
                            new Bullet(2, Color.PINK, new MyPosn(250, 292), new MyPosn(0, -8), 1),
                            this.mt))),
                new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(51, 50), this.p6),
                    new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(51, 50), this.p6),
                        new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(50, 200), this.p3),
                            this.mt))),
                15, 0, 0));
  }

  // tests addTickTester method for NBullets class
  boolean testAddTickTester(Tester t) {
    return t.checkExpect(
        new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 0).addTickTester(),
        new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 1, new Random()))
        && t.checkExpect(new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 3).addTick(),
            new NBullets(new MtLoGamePiece(), new MtLoGamePiece(), 15, 0, 4, new Random()));
  }

  // tests onTickTester method for NBullets class
  boolean testOnTickTester(Tester t) {
    return t
        .checkExpect(new NBullets(0).onTickTester(), new NBullets(new MtLoGamePiece(),
            new MtLoGamePiece(), 0, 0, 1, new Random()))
        && t.checkExpect(new NBullets(lob2, los3, 8, 12, 23, new Random(2)).onTickTester(),
            new NBullets(
                new ConsLoGamePiece(
                    new Bullet(2, Color.PINK, new MyPosn(250, 292), new MyPosn(0, -8), 1),
                    new ConsLoGamePiece(
                        new Bullet(4, Color.PINK, new MyPosn(51, 50), new MyPosn(1, 0), 2),
                        new ConsLoGamePiece(
                            new Bullet(4, Color.PINK, new MyPosn(51, 50), new MyPosn(-1, 0), 2),
                            new MtLoGamePiece()))),
                new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(4, 239), new MyPosn(4, 0)),
                    new ConsLoGamePiece(
                        new Ship(10, Color.CYAN, new MyPosn(496, 248), new MyPosn(-4, 0)),
                        new ConsLoGamePiece(
                            new Ship(10, Color.CYAN, new MyPosn(50, 200), new MyPosn(50, 50)),
                            new MtLoGamePiece()))),
                8, 14, 24, new Random()))
        && t.checkExpect(new NBullets(lob5, los3, 8, 12, 23, new Random(2)).onTickTester(),
            new NBullets(new MtLoGamePiece(),
                new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(4, 239), new MyPosn(4, 0)),
                    new ConsLoGamePiece(
                        new Ship(10, Color.CYAN, new MyPosn(496, 248), new MyPosn(-4, 0)),
                        new ConsLoGamePiece(
                            new Ship(10, Color.CYAN, new MyPosn(51, 50), new MyPosn(1, 0)),
                            new ConsLoGamePiece(
                                new Ship(10, Color.CYAN, new MyPosn(51, 50), new MyPosn(1, 0)),
                                new ConsLoGamePiece(new Ship(10, Color.CYAN, new MyPosn(50, 200),
                                    new MyPosn(50, 50)), new MtLoGamePiece()))))),
                8, 12, 24, new Random()));
  }
}