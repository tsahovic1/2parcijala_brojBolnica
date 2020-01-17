package ba.unsa.etf.rpr;

public class NerazvijeniGrad extends Grad {
    public NerazvijeniGrad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        super(id, naziv, brojStanovnika, drzava);
    }

    public NerazvijeniGrad() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public int brojBolnica() {
        int broj=getBrojStanovnika()/100000;
        if(getBrojStanovnika()%100000==0) return broj;
        else return (broj+1);
    }
}
