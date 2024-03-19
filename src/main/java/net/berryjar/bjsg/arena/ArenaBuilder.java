package net.berryjar.bjsg.arena;

public class ArenaBuilder {

    String world;
    String arenaID;
    int x;
    int y;
    int z;
    float pitch;
    float yaw;


    public String getWorld() {
        return world;
    }
    public void setWorld(String world) {
        this.world = world;
    }

    public void setArenaID(String regionID) {
        this.arenaID = regionID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public String getArenaID() {
        return this.arenaID;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public double getPitch() {
        return this.pitch;
    }
    public double getYaw() {
        return this.yaw;
    }
}
