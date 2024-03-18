package net.berryjar.bjsg.cuboid;

public class CuboidBuilder {

    String world;
    String regionID;
    int x1;
    int y1;
    int z1;
    int x2;
    int y2;
    int z2;

    public void setWorld(String world) {

        this.world = world;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setZ1(int z1) {
        this.z1 = z1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setZ2(int z2) {
        this.z2 = z2;
    }

    public String getWorld() {
        return this.world;
    }

    public String getRegionID() {
        return this.regionID;
    }

    public int getX1() {
        return this.x1;
    }

    public int getY1() {
        return this.y1;
    }

    public int getZ1() {
        return this.z1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY2() {
        return this.y2;
    }

    public int getZ2() {
        return this.z2;
    }
}
