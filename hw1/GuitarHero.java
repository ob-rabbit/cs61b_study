import synthesizer.GuitarString;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author Bunny
 * @create 2022-01-08 22:15
 */
public class GuitarHero {

    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] sounds;

    private static void init() {
        sounds = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            double fre = CONCERT_A * Math.pow(2, (i - 24) / 12);
            sounds[i] = new GuitarString(fre);
        }

    }

    public static void main(String[] args) {

        GuitarHero.init();

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                System.out.println(index);
                sounds[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < sounds.length; i++) {
                sample += sounds[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < sounds.length; i++) {
                sounds[i].tic();
            }
        }
    }

}
