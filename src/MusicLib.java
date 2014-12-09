
/**
 * @author Justin He
 * @author Conor Yuen
 * @version version 1.0, December 5th, 2014
 */
public class MusicLib {
	static boolean delayyesno = false;
	static boolean echoyesno = false;
	/** Description of sum(double[] a, double[] b, double awt, double bwt)
	 * Returns an array of double data types, which essentially is a weighted sine curve 
	 * that is a combination of input sine curves, that when passed through StdAudio.play(),
	 * plays both sounds at the same time. It is similar to pressing two notes on the piano 
	 * at the same time. 
	 * @param a this is an array of double data types which represents a sine curve (a sound).
	 * @param b this is the 2nd array of double data types which also is a sine curve representing a sound.
	 * @param awt this is the wanted weight of sound 'a' - if it is < 0.5, it will have low volume; if
	 * it is > 0.5, it will have high volume. awt is proportional to the volume.
	 * @param bwt same thing as awt but for sound 'b'.
	 * @return an array of double data types that is a "weighted average" of the two
	 * inputed sounds.
	 */
	public static double[] sum(double[] a, double[] b, double awt, double bwt) {
		assert (a.length == b.length);

		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = a[i]*awt + b[i]*bwt;
		}
		return c;
	} 

	/** Description of pitch(double hz, double duration)
	 * Create a pure pitch of the given frequency for the given duration. This basically
	 * is the bread and butter of the overall program; it turns a given frequency of a note (i.e A)
	 * into a sound sine wave that can be interpreted by StdAudio.Play. 
	 * @param hz this is the inputed frequency that the user wants to create into a sine wave.
	 * @param duration this is how long the user wants the sound to last (aka how long the sine wave is).
	 * @return a "sine wave" that is in the form of an array containing double data types.
	 */ 
	public static double[] pitch(double hz, double duration) { 
		int N = (int) (StdAudio.SAMPLE_RATE * duration);
		double[] a = new double[N+1];
		for (int i = 0; i <= N; i++) {
			a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
		}
		return a; 
	} 

	/** Description of note(int pitch, double t)
	 * Creates a note with harmonies of of the given pitch, where 0 = concert A. Basically,
	 * this method returns an array of double data types that is the sound of a note, a note of 
	 * same type (i.e A) 1 octave lower, and a note of same type 1 octave higher.
	 * @param pitch this is a piano note value (i.e 0 = concert A, 1 = B, etc.) that the user wants to make harmonies with.
	 * @param t this is the duration that the user wants the sound to last.
	 * @return a "sine wave" that is in the form of an array containing double data types, which contains
	 * a note and its octave neighbors.
	 */ 
	public static double[] note(int pitch, double t) {
		double hz = 440.0 * Math.pow(2, pitch / 12.0);
		double[] a  = pitch(hz, t);
		double[] hi = pitch(2*hz, t);
		double[] lo = pitch(hz/2, t);
		double[] h  = sum(hi, lo, .5, .5);
		return sum(a, h, .5, .5);
	}

	/** Description of createMajorChord(int pitch, double t)
	 * Creates a major chord with a stem note (pitch), and the 4th major interval note
	 * and the 7th major interval note.
	 * @param pitch this is a piano note value (i.e 0 = concert A, 1 = B, etc.) that the user wants to make the stem note of a MajorChord with.
	 * @param t this is the duration that the user wants the chord to last.
	 * @return a "sine wave" that is in the form of an array containing double data types, which contains
	 * a stem note 'pitch' and its 4th major interval note and 7th major interval note to form a majorchord sound.
	 */ 
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

	/** Description of createMinorChord(int pitch, double t)
	 * Creates a minor chord with a stem note (pitch), and the 3rd minor interval note
	 * and the 7th major interval note.
	 * @param pitch this is a piano note value (i.e 0 = concert A, 1 = B, etc.) that the user wants to make the stem note of a MinorChord with.
	 * @param t this is the duration that the user wants the chord to last.
	 * @return a "sine wave" that is in the form of an array containing double data types, which contains
	 * a stem note 'pitch' and its 3rd minor interval note and 7th major interval note to form a minorchord sound.
	 */ 
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

	/** Description of VolumeChanger(double numA, double[] A)
	 * Basically changes the volume given a sound (in the form of a double[] array)
	 * @param numA the ratio of volume change; give it 1.2, 20% louder. give it 0.8, 20% quieter.
	 * @param A the sound that is to be volume changed.
	 * @return a double[] array that has changed volume!
	 */
	public static double[] VolumeChanger(double numA, double[] A) {
		for (int i=0; i<A.length; i++){
			A[i]=A[i]*numA;
		}
		return A;
	}

	/** Description of Delay(double[] a)
	 * Takes a double array and spits it back out to be played again, but silent.
	 * @param a the array of sound about to be silenced
	 * @return the sum of the original array and a counter array to create silence
	 */
	public static double[] Delay(double[] a){
		double[] b = new double[a.length];
		for (int r = 0; r<a.length; r++){
			b[r] = -1*a[r];
		}
		delayyesno = true;
		return sum(b,a,.5,.5);

	}
	
	/** Description of Delay(double[] a)
	 * Takes a double array and spits it back out to be played again, but silent.
	 * @param a the array of sound about to be silenced
	 * @return the sum of the original array and a counter array to create silence
	 */
	public static double[] ClickSound(double[] a){
		int x = 0;
		for (int i=0; i<1; i++){
			double hz = 440.0 * Math.pow(2, i / 12.0);
			double[] glissandopernote = pitch(hz, 0.1);
			for (int y=0; y<glissandopernote.length; y++){
				a[y] = glissandopernote[y]; //Array used to store array values for exporting to the .wav file
				System.out.println(glissandopernote.length);
				System.out.println(a.length);
			}
		}
		return a;
	}
}
