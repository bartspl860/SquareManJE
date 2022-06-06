package scripts;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;

public class AudioManager {
    private final HashMap<String, Clip> clips = new HashMap<>();

    public void addClip(String name, String path) {
        try {
            var file = new File(path);
            var sound = AudioSystem.getAudioInputStream(file);
            var clip = AudioSystem.getClip();
            clip.open(sound);
            clips.put(name, clip);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void playClip(String name) {
        var clip = clips.get(name);
        if (clip == null)
            return;
        clip.setFramePosition(0);
        clip.start();
    }

    public void playLoopClip(String name) {
        var clip = clips.get(name);
        if (clip == null)
            return;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
