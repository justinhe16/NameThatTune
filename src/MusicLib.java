
public class MusicLib {
	// take weighted sum of two arrays
    public static double[] sum(double[] a, double[] b, double awt, double bwt) {
    //test
        // precondition: arrays have the same length
        assert (a.length == b.length);

        // compute the weighted sum
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]*awt + b[i]*bwt;
        }
        return c;
    } 

    // create a pure tone of the given frequency for the given duration
    public static double[] tone(double hz, double duration) { 
        int N = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[N+1];
        for (int i = 0; i <= N; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }
        return a; 
    } 

    // create a note with harmonics of of the given pitch, where 0 = concert A
    public static double[] note(int pitch, double t) {
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double[] a  = tone(hz, t);
        double[] hi = tone(2*hz, t);
        double[] lo = tone(hz/2, t);
        double[] h  = sum(hi, lo, .5, .5);
        return sum(a, h, .5, .5);
    }
    
    // create a major chord with a stem note (pitch)
    public static double[] createMajorChord(int pitch, double t) {
    	int pitch2 = pitch + 4;
    	int pitch3 = pitch + 7;
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double hz2 = 440.0 * Math.pow(2, pitch2 / 12.0);
        double hz3 = 440.0 * Math.pow(2, pitch3 / 12.0);
        double[] a  = tone(hz, t);
        double[] a2 = tone(hz2, t);
        double[] a3 = tone(hz3, t);
        double[] h  = sum(a2, a3, .5, .5);
        return sum(a, h, .5, .5);
    }
    
    // creates a minor chord with a stem note (pitch)
    public static double[] createMinorChord(int pitch, double t) {
    	int pitch2 = pitch + 3;
    	int pitch3 = pitch + 7;
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double hz2 = 440.0 * Math.pow(2, pitch2 / 12.0);
        double hz3 = 440.0 * Math.pow(2, pitch3 / 12.0);
        double[] a  = tone(hz, t);
        double[] a2 = tone(hz2, t);
        double[] a3 = tone(hz3, t);
        double[] h  = sum(a2, a3, .5, .5);
        return sum(a, h, .5, .5);
    }
    
}
