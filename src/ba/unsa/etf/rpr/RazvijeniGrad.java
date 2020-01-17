package ba.unsa.etf.rpr;

public class RazvijeniGrad extends Grad {
    public RazvijeniGrad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        super(id, naziv, brojStanovnika, drzava);
    }

    public RazvijeniGrad() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int brojBolnica() {
        int broj = getBrojStanovnika() / 10000;
        if (getBrojStanovnika() % 10000 == 0) return broj;
        else return (broj + 1);
    }
}
