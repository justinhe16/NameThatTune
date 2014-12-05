
public class MusicLib {
	static boolean delayyesno = false;
/**
 * Returns a array of double variable types, which essentially builds a sine curve that when passed
 * through StdAudio.play() plays the sound. 
 * 
 * 
 * 
 * 
 * 
 * 
 */
    public static double[] sum(double[] a, double[] b, double awt, double bwt) {
        assert (a.length == b.length);

        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]*awt + b[i]*bwt;
        }
        return c;
    } 

    // create a pure pitch of the given frequency for the given duration
    public static double[] pitch(double hz, double duration) { 
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
        double[] a  = pitch(hz, t);
        double[] hi = pitch(2*hz, t);
        double[] lo = pitch(hz/2, t);
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
        double[] a  = pitch(hz, t);
        double[] a2 = pitch(hz2, t);
        double[] a3 = pitch(hz3, t);
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
        double[] a  = pitch(hz, t);
        double[] a2 = pitch(hz2, t);
        double[] a3 = pitch(hz3, t);
        double[] h  = sum(a2, a3, .5, .5);
        return sum(a, h, .5, .5);
    }
    
    public static double[] VolumeChanger(double numA, double[] A) {
		for (int i=0; i<A.length; i++){
			A[i]=A[i]*numA;
		}
    	return A;
    }
    
    public static int Delay(int intialnote){
    	int echonote = intialnote;
    	delayyesno = true;
		return echonote;
    	
    }
    
}
