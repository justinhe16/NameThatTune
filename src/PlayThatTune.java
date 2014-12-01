import java.io.BufferedWriter;
import java.util.Random;

import java.io.*;



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
	static Random rand01 = new Random();

	public static void main(String[] args) {
		StdDraw.setXscale(0,1);
		StdDraw.setYscale(0,1);

		int t = 0;
		double duration;
		int pitch;
		double[] combo = null;
		int[] APentatonicChords = new int[]{0,2,4,7,9};
		int[] APentatonic = new int[]{0,2,4,7,9,12,14,16,19};
		double[] Duration = new double[]{0.25,0.5,1.0};
		int x = APentatonicChords[rand3.nextInt(APentatonicChords.length - 1)] - 12;

		//a while loop to put a limit on the songs limit
		while(t <= 1000){
			{
				if (t == 1000){
					pitch = APentatonic[0];
				}
				else
					// The pitch is based upon a random choice from an array
					pitch = APentatonic[rand.nextInt(APentatonic.length)];
			}
			{
				if (t == 1000){
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

			// play it using standard audio
			StdAudio.play(combo);

			//everything that has to do with drawing the performance
			StdDraw.clear();
			if (pitch == APentatonic[0]){
				StdDraw.setPenColor(StdDraw.MAGENTA);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if (pitch == APentatonic[1]){
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[2]){
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[3]){
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[4]){
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[5]){
				StdDraw.setPenColor(StdDraw.PINK);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[6]){
				StdDraw.setPenColor(StdDraw.CYAN);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[7]){
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}
			else if(pitch == APentatonic[8]){
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle(rand01.nextFloat(),rand01.nextFloat(), duration/5);
			}

			StdDraw.show(20);

			//;
			try {
		         File file = new File("Desktop/SheetMusic.txt");
		         BufferedWriter output = new BufferedWriter(new FileWriter(file));
		         output.write(pitch + ", " + duration);
		         output.close();
		       } catch ( IOException e ) {
		          e.printStackTrace();
		       }
			t++; 
		}
	}
}

