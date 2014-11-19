// This is mutant program.
// Author : ysma


public class MatchBox extends Box1
{

    double depth;

    double weight;

    static double result = 0;

    public MatchBox()
    {
    }

    public MatchBox( double w, double h, double d, double m )
    {
        super( w, h, d );
        weight = m;
    }

}
