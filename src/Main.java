import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SimControlException {
        double x0 = 2000;
        double y0 = 15000;
        double d0 = 8000;
        double aMax = 0.1;
        double bMax = 0.08;
        double vMax = 20;
        double vMin = 2;
        double e = 1;
        double f = 3;
        // wariant obliczania odleglosci

        boolean variant = true;

        // procent uzupelnienia
        double supplyPercent = 0.1;

        // krytyczny poziom strat
        double surrenderPercent = 0.2;

        // zmiana stanu w sekundach - odstep czasowy miedzy krokami
        double deltaT = 1;

        SimManager sm = SimManager.getInstance();
        sm.setEndSimTime(100000);

        Fight fight = new Fight(aMax,bMax,vMin,vMax,x0,y0,d0,e,f,deltaT,surrenderPercent,variant,supplyPercent);
        double step = 1;

        new MovementEvent(fight, null, step,3);
        new DamageEvent(fight, null, step,2);
        new SuppliesEvent(fight, null, step,1);

        sm.startSimulation();
    }

}