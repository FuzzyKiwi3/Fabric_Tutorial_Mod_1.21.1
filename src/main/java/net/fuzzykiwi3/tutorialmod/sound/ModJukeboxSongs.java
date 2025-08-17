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

public class ModJukeboxSongs {
    public static final RegistryKey<JukeboxSong> BAR_BRAWL = RegistryKey.of(RegistryKeys.JUKEBOX_SONG,
            Identifier.of(TutorialMod.MOD_ID, "bar_brawl"));
    public static final RegistryKey<JukeboxSong> MEGALOVANIA = RegistryKey.of(RegistryKeys.JUKEBOX_SONG,
            Identifier.of(TutorialMod.MOD_ID, "megalovania"));
    public static final RegistryKey<JukeboxSong> DEVOURER_OF_GODS = RegistryKey.of(RegistryKeys.JUKEBOX_SONG,
            Identifier.of(TutorialMod.MOD_ID, "devourer_of_gods"));

    public static void bootstrap(Registerable<JukeboxSong> registerable) {
        register(registerable, BAR_BRAWL, Registries.SOUND_EVENT.getEntry(ModSounds.BAR_BRAWL), 162.0f, 15);
        register(registerable, MEGALOVANIA, Registries.SOUND_EVENT.getEntry(ModSounds.MEGALOVANIA), 156.0f, 7);
        register(registerable, DEVOURER_OF_GODS, Registries.SOUND_EVENT.getEntry(ModSounds.DEVOURER_OF_GODS), 816.0f, 9);
    }

    private static void register(Registerable<JukeboxSong> registerable, RegistryKey<JukeboxSong> key,
                                 RegistryEntry<SoundEvent> sound, float duration, int comparatorOutput) {
        JukeboxSong jukeboxSong = new JukeboxSong(sound,
                Text.translatable("item." + TutorialMod.MOD_ID + "." + key.getValue().getPath() + "_music_disc.desc"), duration, comparatorOutput);

        registerable.register(key, jukeboxSong);
    }
}