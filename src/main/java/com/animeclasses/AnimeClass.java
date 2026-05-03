package com.animeclasses;

import org.bukkit.Material;
import java.util.List;

public enum AnimeClass {
    GOJO    ("Satoru Gojo",     "Jujutsu Kaisen",         Material.GLASS,       "§b",
             List.of("§7Passive: §fPermanent Hunger I",
                     "§7Passive: §fBlindness without a helmet in daylight",
                     "§7Ability: §fHollow Purple §8- §eRight-click sword",
                     "§7         §fDestroys all blocks in front of you")),

    ITADORI ("Yuji Itadori",   "Jujutsu Kaisen",         Material.IRON_SWORD,  "§c",
             List.of("§7Passive: §fResistance I + Speed II + Jump Boost I",
                     "§7Passive: §fWither below 3 hearts (Sukuna's toll)",
                     "§7Ability: §fBlack Flash §8- §eRight-click sword",
                     "§7         §fDeals massive damage in a single hit")),

    SAITAMA ("Saitama",        "One Punch Man",           Material.YELLOW_WOOL, "§e",
             List.of("§7Passive: §fOne-shot any mob on hit",
                     "§7Passive: §fGets Weakness + Slowness if bored (no combat 3 min)",
                     "§7Ability: §fSerious Punch §8- §eRight-click sword",
                     "§7         §fLaunches all nearby enemies away")),

    GOKU    ("Goku",           "Dragon Ball",             Material.ORANGE_WOOL, "§6",
             List.of("§7Passive: §fTriple hunger drain",
                     "§7Passive: §fStrong base attack damage",
                     "§7Ability: §fKi Blast §8- §eRight-click sword",
                     "§7         §fFires a piercing energy projectile")),

    LUFFY   ("Monkey D. Luffy","One Piece",               Material.HAY_BLOCK,   "§d",
             List.of("§7Passive: §f+3 block extended reach",
                     "§7Passive: §fSlowness I in rain (rubber hates water)",
                     "§7Passive: §fCan only eat meat",
                     "§7Ability: §fGum-Gum Rocket §8- §eRight-click sword",
                     "§7         §fLaunches you forward at high speed")),

    EREN    ("Eren Yeager",    "Attack on Titan",         Material.BONE_BLOCK,  "§7",
             List.of("§7Passive: §fSpeed I + reduced fall damage",
                     "§7Passive: §fStrong melee in normal form",
                     "§7Ability: §fColossal Titan Shift §8- §eRight-click sword",
                     "§7         §fTransforms into giant form: huge + strength")),

    WUKONG  ("Sun Wukong",     "Journey to the West",    Material.STICK,       "§a",
             List.of("§7Passive: §fReflects projectiles back at attackers",
                     "§7Ability: §fNimbus Cloud §8- §eRight-click sword",
                     "§7         §fSummons a cloud to launch you skyward")),

    L       ("L",              "Death Note",              Material.COOKIE,      "§f",
             List.of("§7Passive: §fOnly 6 hearts max (frail body)",
                     "§7Passive: §fSweets give double hunger",
                     "§7Ability: §fDetective Sense §8- §eRight-click sword",
                     "§7         §fReveals all nearby players through walls")),

    LIGHT   ("Light Yagami",   "Death Note",              Material.BOOK,        "§4",
             List.of("§7Passive: §f8 hearts max, faster than normal",
                     "§7Passive: §fDeal bonus damage to players",
                     "§7Ability: §fKira's Judgement §8- §eRight-click sword",
                     "§7         §fInstantly kills a targeted player")),

    NARUTO  ("Naruto",         "Naruto",                  Material.ORANGE_DYE,  "§6",
             List.of("§7Passive: §fJump Boost II + Speed I",
                     "§7Passive: §fRamen gives double saturation",
                     "§7Ability: §fShadow Clones §8- §eRight-click sword",
                     "§7         §fSpawns 3 wolf clones to fight for you")),

    ASH     ("Ash Ketchum",    "Pokémon",                 Material.BONE,        "§9",
             List.of("§7Passive: §fWolves follow and protect you",
                     "§7Passive: §fAnimals will not attack you",
                     "§7Ability: §fWolf Pack Recall §8- §eRight-click sword",
                     "§7         §fTeleports all your wolves to your side")),

    LEBRON  ("LeBron James",   "Real Life / Basketball",  Material.ORANGE_WOOL, "§6",
             List.of("§7Passive: §f1.2x size + Jump Boost III",
                     "§7Passive: §fSpeed burst after sprinting 3 seconds",
                     "§7Ability: §fAlley-Oop §8- §eRight-click sword",
                     "§7         §fSlams a target down dealing fall damage"));

    private final String displayName;
    private final String series;
    private final Material icon;
    private final String color;
    private final List<String> description;

    AnimeClass(String displayName, String series, Material icon, String color, List<String> description) {
        this.displayName = displayName;
        this.series = series;
        this.icon = icon;
        this.color = color;
        this.description = description;
    }

    public String getDisplayName()      { return displayName; }
    public String getSeries()           { return series; }
    public Material getIcon()           { return icon; }
    public String getColor()            { return color; }
    public String getColoredName()      { return color + displayName; }
    public List<String> getDescription(){ return description; }
}
