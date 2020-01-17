package ba.unsa.etf.rpr;

public class SrednjeRazvijeniGrad extends Grad {
    public SrednjeRazvijeniGrad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        super(id, naziv, brojStanovnika, drzava);
    }

    public SrednjeRazvijeniGrad() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public int brojBolnica() {
        int broj=getBrojStanovnika()/25000;
        if(getBrojStanovnika()%25000==0) return broj;
        else return (broj+1);
    }
}
