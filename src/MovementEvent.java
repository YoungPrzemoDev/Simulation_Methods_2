import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class MovementEvent extends BasicSimEvent<Fight, Object> {

    public  MovementEvent(Fight f, Object o, double period, int priority) throws SimControlException {
        super(f, o, period, priority);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Fight fight = getSimObj();

        fight.printState();

        if(fight.isVariant()){
            modifyVelocity1(fight);
        }else {
            modifyVelocity2(fight);
        }

        modifyDistance(fight);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }


    @Override
    public Object getEventParams() {
        return null;
    }

    public void modifyVelocity1 (Fight f){
        f.getFutureState().setV(f.getvMin() + ( f.getvMax() - f.getvMin() ) * f.getActualState().getD() / f.getD0()) ;
    }
    public void modifyVelocity2 (Fight f){
        double c = Math.sqrt(f.getActualState().getA()*f.getActualState().getB());
        if(c < 0) {
            c = 0;
        } else if (c > f.getcMax()) {
            c = f.getcMax();
        }
        f.getFutureState().setV(f.getvMax() - ( f.getvMax() - f.getvMin() )
                / f.getcMax() * c);

    }

    public void modifyDistance(Fight f){
        if (f.getActualState().getD() <= 0){
            f.getFutureState().setD(0);
            f.getFutureState().setV(0);
        }else{
            f.getFutureState().setD(f.getActualState().getD() - f.getActualState().getV() * f.getDeltaT()) ;
        }
    }
}
