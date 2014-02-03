package opticraft.client.sounds;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class BuzzSound {

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("optcrft:buzz.ogg");
	}
}
