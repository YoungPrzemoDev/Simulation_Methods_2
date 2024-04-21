public class FightStatus {
    private double d;
    private double a,b;
    private double x, y;
    private double v; //predkosc zblizania
    private double uX,uY;

    public FightStatus(double armyDistance, double aDamageFactor, double bDamageFactor, double assetsA, double assetsB,
                       double v, double aTimeLeftToRefillAssets, double bTimeLeftToRefillAssets) {
        this.d = armyDistance;
        this.a = aDamageFactor;
        this.b = bDamageFactor;
        this.x = assetsA;
        this.y = assetsB;
        this.v = v;
        this.uX = aTimeLeftToRefillAssets;
        this.uY = bTimeLeftToRefillAssets;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getuX() {
        return uX;
    }

    public void setuX(double uX) {
        this.uX = uX;
    }

    public double getuY() {
        return uY;
    }

    public void setuY(double uY) {
        this.uY = uY;
    }
}
