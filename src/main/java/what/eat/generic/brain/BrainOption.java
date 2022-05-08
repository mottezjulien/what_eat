package what.eat.generic.brain;

public class BrainOption {

    private int max;
    private boolean repeat;

    public BrainOption() {
        this.max = 5;
        this.repeat = true;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}

