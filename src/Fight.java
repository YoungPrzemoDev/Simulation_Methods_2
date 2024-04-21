import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.text.DecimalFormat;

public class Fight extends BasicSimObj {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private RNGenerator generator;
    private double aMax,bMax,cMax;
    private double vMin, vMax;
    private double x0, y0; // ilości sił
    private double d0;
    private double e, f;   // wartosci przedzialu czasu uzupelniania zasobow wojsk
    private FightStatus actualState;
    private FightStatus futureState;
    private long seed;
    private double deltaT;
    private boolean variant;
    private double supplyPercent;
    private double surrenderPercent;

    public Fight(double aMax, double bMax, double vMin, double vMax, double x0,
                 double y0, double d0, double e, double f,double deltaT,double surrenderPercent,boolean variant,double supplyPercent) {
        this.x0 = x0;
        this.y0 = y0;
        this.d0 =d0;
        this.aMax = aMax;
        this.bMax = bMax;
        this.cMax = Math.sqrt(aMax * bMax);
        this.vMin = vMin;
        this.vMax = vMax;
        this.e = e;
        this.f = f;
        this.generator = new RNGenerator(seed);
        double uX = generator.uniform(this.e,this.f);
        double uY = generator.uniform(this.e,this.f);
        this.actualState = new FightStatus(d0,0,0,x0,y0,0,uX,uY);
        this.futureState = new FightStatus(d0,0,0,x0,y0,0,uX,uY);
        this.deltaT = deltaT;
        this.variant = variant;
        this.supplyPercent = supplyPercent;
        this.surrenderPercent = surrenderPercent;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }


    public void printState (){
        String separator = "-----------------------------------------------------------------------------------";
        String headerFormat = "%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n";
        String header = String.format(headerFormat,
                "t [s]", "d [m]", "v [m/s]", "x", "y", "ux [s]", "uy [s]", "a [1/s]", "b [1/s]");

        String rowFormat = "%-10d %-15s %-10s %-10d %-10d %-10d %-10d %-10f %-10f\n";
        String row = String.format(rowFormat,
                (int)(simTime()*deltaT),
                df.format(actualState.getD()),
                df.format(actualState.getV()),
                (int)(actualState.getX()),
                (int)(actualState.getY()),
                (int)(actualState.getuX()),
                (int)(actualState.getuY()),
                actualState.getA(),
                actualState.getB());

        System.out.println(header);
        System.out.println(row);
        System.out.println(separator);
    }

    public double getSurrenderPercent() {
        return surrenderPercent;
    }

    public void setSurrenderPercent(double surrenderPercent) {
        this.surrenderPercent = surrenderPercent;
    }

    public RNGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(RNGenerator generator) {
        this.generator = generator;
    }

    public double getaMax() {
        return aMax;
    }

    public void setaMax(double aMax) {
        this.aMax = aMax;
    }

    public double getbMax() {
        return bMax;
    }

    public void setbMax(double bMax) {
        this.bMax = bMax;
    }

    public double getcMax() {
        return cMax;
    }

    public void setcMax(double cMax) {
        this.cMax = cMax;
    }

    public double getvMin() {
        return vMin;
    }

    public void setvMin(double vMin) {
        this.vMin = vMin;
    }

    public double getvMax() {
        return vMax;
    }

    public void setvMax(double vMax) {
        this.vMax = vMax;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getD0() {
        return d0;
    }

    public void setD0(double d0) {
        this.d0 = d0;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public FightStatus getActualState() {
        return actualState;
    }

    public void setActualState(FightStatus actualState) {
        this.actualState = actualState;
    }

    public FightStatus getFutureState() {
        return futureState;
    }

    public void setFutureState(FightStatus futureState) {
        this.futureState = futureState;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    public boolean isVariant() {
        return variant;
    }

    public void setVariant(boolean variant) {
        this.variant = variant;
    }

    public double getSupplyPercent() {
        return supplyPercent;
    }

    public void setSupplyPercent(double supplyPercent) {
        this.supplyPercent = supplyPercent;
    }
}
