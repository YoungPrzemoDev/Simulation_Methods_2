import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class DamageEvent extends BasicSimEvent<Fight,Object> {


    public DamageEvent(Fight f, Object o, double period, int priority) throws SimControlException {
        super(f, o, period, priority);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Fight f = getSimObj();

        // modyfikuje wspolczynniki scierania wojsk
        modifyAttrictionCoefficient(f);

        // modyfikuje sily stron
        modifyMilitaryStrength(f);
        if (checkForces(f)) {
            stopSimulation();
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }



    public void modifyAttrictionCoefficient (Fight f){
        f.getFutureState().setA(f.getaMax() - ( f.getaMax() / f.getD0() ) * f.getActualState().getD());
        f.getFutureState().setB(f.getbMax() - ( f.getbMax() / f.getD0() ) * f.getActualState().getD());
    }

    public void modifyMilitaryStrength ( Fight f ){
        f.getFutureState().setX(f.getActualState().getX() - f.getActualState().getA() * f.getActualState().getY() * f.getDeltaT());
        f.getFutureState().setY(f.getActualState().getY() - f.getActualState().getB() * f.getActualState().getX() * f.getDeltaT());
    }

    public boolean checkForces (Fight f){

        // warunek unicestwienia (opcja 1)
        if(f.getActualState().getX() <= 0) {
            System.out.println("X całkowicie zniszczone!");
            return true;
        }if(f.getActualState().getY() <= 0) {
            System.out.println("Y całkowicie zniszczone!");
            return true;
        }else if (f.getActualState().getX() <= (f.getX0() * f.getSupplyPercent())) {
            System.out.println("Krytyczny poziom strat X wynoszący " + f.getSurrenderPercent()*100 + "%");
            return true;
        }else if (f.getActualState().getY() <= (f.getY0() * f.getSurrenderPercent())) {
            System.out.println("Krytyczny poziom strat Y wynoszący " + f.getSurrenderPercent()*100 + "%");
            return true;
        }
        return false;
    }
}
