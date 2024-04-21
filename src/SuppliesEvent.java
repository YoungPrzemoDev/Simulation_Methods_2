import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.text.DecimalFormat;

public class SuppliesEvent extends BasicSimEvent<Fight,Object> {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public SuppliesEvent(Fight f, Object o, double period, int priority) throws SimControlException {
        super(f, o, period, priority);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Fight f = getSimObj();
        supply(f);
        modifyActualState(f);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }


    public void supply(Fight f){
        if (f.getActualState().getuX() <= simTime()){
            double supplyValue = f.getSupplyPercent() * (f.getX0()-f.getActualState().getX());
            f.getFutureState().setX(f.getActualState().getX() + supplyValue);

            System.out.println("Supply for X! value = " + df.format(supplyValue));

            f.getFutureState().setuX(f.getActualState().getuX() + f.getGenerator().uniform(f.getE(),f.getF()));
        }
        if (f.getActualState().getuY() <= simTime()){
            double supplyValue = f.getSupplyPercent() * (f.getY0() - f.getActualState().getY());
            f.getFutureState().setY(f.getActualState().getY() + supplyValue);
            System.out.println("Supply for Y! value = " + df.format(supplyValue));
            f.getFutureState().setuY(f.getActualState().getuY() + f.getGenerator().uniform(f.getE(),f.getF()));
        }
    }
    public void modifyActualState(Fight f) {
        f.getActualState().setX(f.getFutureState().getX());
        f.getActualState().setY(f.getFutureState().getY());
        f.getActualState().setD(f.getFutureState().getD());
        f.getActualState().setA(f.getFutureState().getA());
        f.getActualState().setB(f.getFutureState().getB());
        f.getActualState().setV(f.getFutureState().getV());
        f.getActualState().setuX(f.getFutureState().getuX());
        f.getActualState().setuY(f.getFutureState().getuY());
    }

}
