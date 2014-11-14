import java.util.Random;



/*************************************************************************
 *  Compilation:  javac PlayThatTune.java
 *  Execution:    java PlayThatTune < input.txt
 *  Dependencies: StdAudio.java StdAudio.java
 *
 *  This is a data-driven program that plays pure tones from
 *  the notes on the chromatic scale, specified on standard input
 *  by their distance from concert A.
 *
 *  % java PlayThatTune < elise.txt
 *
 *
 *  Data files
 *  ----------
 *  http://www.cs.princeton.edu/introcs/21function/elise.txt
 *  http://www.cs.princeton.edu/introcs/21function/freebird.txt
 *  http://www.cs.princeton.edu/introcs/21function/Ascale.txt
 *  http://www.cs.princeton.edu/introcs/21function/National_Anthem.txt
 *  http://www.cs.princeton.edu/introcs/21function/looney.txt
 *  http://www.cs.princeton.edu/introcs/21function/StairwayToHeaven.txt
 *  http://www.cs.princeton.edu/introcs/21function/entertainer.txt
 *  http://www.cs.princeton.edu/introcs/21function/old-nassau.txt
 *  http://www.cs.princeton.edu/introcs/21function/arabesque.txt
 *  http://www.cs.princeton.edu/introcs/21function/firstcut.txt 
 *  http://www.cs.princeton.edu/introcs/21function/tomsdiner.txt
 *
 *************************************************************************/

public class PlayThatTune {
	static Random rand = new Random();
	static Random rand2 = new Random();
	static Random rand3 = new Random();

	public static void main(String[] args) {
		int t = 0;
		double duration;
		int pitch;
		int[] APentatonicChords = new int[]{0,2,4,7,9};
		int[] APentatonic = new int[]{0,2,4,7,9,12,14,16,19,21};
		double[] Duration = new double[]{0.25,0.5,1.0};
		int x = APentatonicChords[rand3.nextInt(APentatonicChords.length - 1)] - 12;

		// repeat as long as there are more integers to read in
		//creates an array of integers of all of the input on one line
		while(t < 64){
			// read in the pitch, where 0 = Concert A (A4)
			{
				if (t == 64){
					pitch = APentatonic[0];
				}
				else
					pitch = APentatonic[rand.nextInt(APentatonic.length - 1)];
			}
			// read in duration in seconds
			{
				if (t == 64){
					duration = 1;
				}
				else 
					duration = Duration[rand2.nextInt(Duration.length - 1)];
			}

			// build sine wave with desired frequency
			double hz = 440 * Math.pow(2, pitch / 12.0);
			int N = (int) (StdAudio.SAMPLE_RATE * duration);
			double[] a = new double[N+1];
			for (int i = 0; i <= N; i++) {
				a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
			}

			//adding chords to the sound track
			double[] b = new double[N+1];
			if (t % 4 == 0){
				x = APentatonicChords[rand3.nextInt(APentatonicChords.length - 1)] - 12;
				b = PlayThatTuneDeluxe.createMajorChord(x, duration);
			}
			else {
				b = PlayThatTuneDeluxe.createMajorChord(x, duration);
			}
			

			double[] combo = new double[N+1];
			combo = PlayThatTuneDeluxe.sum(a, b, .7, .3);

			System.out.println(t);
			// play it using standard audio
			StdAudio.play(combo);
			t++; 
		}
	}
}

