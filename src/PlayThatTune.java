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
		StdDraw.setXscale(-1,1);
		StdDraw.setYscale(-1,1);
		
		int t = 0;
		double duration;
		int pitch;
		double[] combo = null;
		int[] APentatonicChords = new int[]{0,2,4,7,9};
		int[] APentatonic = new int[]{0,2,4,7,9,12,14,16,19};
		double[] Duration = new double[]{0.25,0.5};
		int x = APentatonicChords[rand3.nextInt(APentatonicChords.length - 1)] - 12;

		//a while loop to put a limit on the songs limit
		while(t <= 64){
			{
				if (t == 64){
					pitch = APentatonic[0];
				}
				else
					// The pitch is based upon a random choice from an array
					pitch = APentatonic[rand.nextInt(APentatonic.length)];
			}
			{
				if (t == 64){
					duration = 2;
				}
				else 
					//The duration is based upon a random choice from an array
					duration = Duration[rand2.nextInt(Duration.length)];
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
			if (t == 64){
				x = -12;
				b = MusicLib.createMajorChord(x, 2);
			}
			else if (t % 4 == 0){
				x = APentatonicChords[rand3.nextInt(APentatonicChords.length)] - 12;
				b = MusicLib.createMajorChord(x, duration);
			}
			else {
				b = MusicLib.createMajorChord(x, duration);
			}
			
			// adds the chords to the melody
			combo = new double[N+1];
			combo = MusicLib.sum(a, b, .6, .4);
			System.out.println(t);
			
			//test volume
			a = MusicLib.VolumeChanger(0.5,a);
			
			// play it using standard audio
			StdAudio.play(a);
			StdDraw.show(20);
			//;
			t++; 
		}
	}
}

