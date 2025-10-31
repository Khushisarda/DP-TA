package smartparking.decorator;
public abstract class FeeDecorator implements Fee {
    protected Fee inner;
    public FeeDecorator(Fee inner){ this.inner = inner; }
}
