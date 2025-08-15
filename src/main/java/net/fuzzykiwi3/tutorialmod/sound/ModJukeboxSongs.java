package net.fuzzykiwi3.tutorialmod.sound;

import net.fuzzykiwi3.tutorialmod.TutorialMod;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModJukeboxSongs {
    public static final RegistryKey<JukeboxSong> BAR_BRAWL = RegistryKey.of(RegistryKeys.JUKEBOX_SONG,
            Identifier.of(TutorialMod.MOD_ID, "bar_brawl"));

    public static void bootstrap(Registerable<JukeboxSong> registerable) {
        register(registerable, BAR_BRAWL, Registries.SOUND_EVENT.getEntry(ModSounds.BAR_BRAWL));
    }

    private static void register(Registerable<JukeboxSong> registerable, RegistryKey<JukeboxSong> key,
                                 RegistryEntry<SoundEvent> sound) {
        JukeboxSong jukeboxSong = new JukeboxSong(sound,
                Text.translatable("item." + TutorialMod.MOD_ID + "." + key.getValue().getPath() + "_music_disc.desc"), 162.0f, 15);

        registerable.register(key, jukeboxSong);
    }
}