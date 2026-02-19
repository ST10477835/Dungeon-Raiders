/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;

/**
 *
 * @author Abulele
 */
class Enemy implements Serializable {

    String name;
    String description;
    
    Weapon currentWeapon;

    String weaknessType;
    String strengthType;

    int level;

    int VIT;
    double health;
    double currentHealth;

    int STR;
    double strength;
    double currentStrength;

    int INT;
    double intelligence;
    double currentIntelligence;

    int DEF;
    int LUC;

    Enemy(String name, int level) {
        this.name = name;
        this.level = level;

        switch (this.name) {
            case "Ogre" -> {
                loadOgre(this.level);
            }
            case "Crocotta" -> {
                loadCrocotta(this.level);
            }
            case "Bicorn" -> {
                loadBicorn(this.level);
            }
            case "Yale" -> {
                loadYale(this.level);
            }
            case "Wyvern" -> {
                loadWyvern(this.level);
            }
        }

    }

    public void loadOgre(int level) {
        this.weaknessType = "fire";
        this.strengthType = "earth";

        this.description = """
                            "The hulking brutes of the wild."
                            Ogres are muscular, human-like giants standing 9 to 10 feet tall and weighing over 600 pounds. 
                            They have hideous, disproportioned features, including large noses, tusks, warts, and often 
                            possess dull, earth-toned or sometimes greenish-grey skin. They have notoriously quick tempers,
                            often resorting to violent tantrums when frustrated.""";
        
        currentWeapon = new Weapon("Club", 10);

        this.VIT = 2;
        this.STR = 3;
        this.INT = 0;
        this.DEF = 2;
        this.LUC = 1;
        
        statSetup();
    }

    public void loadCrocotta(int level) {
        this.weaknessType = "physical";
        this.strengthType = "wind";

        this.description = """
                            "The Cackling Predators of the Badlands."
                            Crocottas are lean, hyena-like monstrosities roughly the size of a large lion, with 
                            powerful forequarters, sloped backs, and long, bone-crushing jaws filled with jagged
                            teeth. Their coarse fur ranges from dusty brown to ash-grey, often mottled to blend
                            into rocky plains and scrublands. Most unsettling is their intelligence and cruel
                            cunning crocottas are known to mimic human voices and familiar sounds to lure prey
                            into ambushes. They delight in panic and pursuit, hunting not just to feed, but to 
                            terrorize.""";
        
        currentWeapon = new Weapon("Claws", 10);

        this.VIT = 1;
        this.STR = 1;
        this.INT = 2;
        this.DEF = 1;
        this.LUC = 3;
        
        statSetup();
    }

    public void loadBicorn(int level) {
        this.weaknessType = "ice";
        this.strengthType = "fire";

        this.description = """
                            "The Two-Horned Wardens of the Wilds."
                            Bicorns are massive, bull-like beasts with thick, corded muscle and a low, powerful
                            stance built for unstoppable charges. They are distinguished by their pair of 
                            forward-curving horns, polished smooth from constant combat and territorial clashes.
                            Their hides are dense and leathery, often scarred from battles. When provoked, a bicorn's
                            charge can shatter shields and uproot trees.""";
        
        currentWeapon = new Weapon("Great Horn", 10);

        this.VIT = 2;
        this.STR = 1;
        this.INT = 3;
        this.DEF = 1;
        this.LUC = 2;
        
        statSetup();
    }

    public void loadYale(int level) {
        this.weaknessType = "wind";
        this.strengthType = "water";

        this.description = """
                            "The Shadow-Maned Hunters of the High Slopes."
                            Yales are powerful, goat- or antelope-like beasts with compact, muscular bodies
                            and dense coats suited for cold, mountainous terrain. Their most striking feature
                            is a pair of large, swiveling horns capable of rotating independently, making ambush
                            nearly impossible. Cunning and relentless, they pursue intruders across cliffs and
                            narrow ledges.""";
        
        currentWeapon = new Weapon("Javelins", 10);

        this.VIT = 2;
        this.STR = 2;
        this.INT = 1;
        this.DEF = 1;
        this.LUC = 3;
        
        statSetup();
    }

    public void loadWyvern(int level) {
        this.weaknessType = "fire";
        this.strengthType = "ice";

        this.description = """
                            "The Venom-Winged Terrors of the Sky."
                            Wyverns are ferocious, two-legged drakes with vast, leathery wings that double as forelimbs.
                            Their lean but powerful bodies are built for speed and aerial dominance, with long, barbed 
                            tails often ending in a venomous stinger. Though lacking the cunning of true dragons, their
                            raw aggression makes them a nightmare for travelers.""";
        
        currentWeapon = new Weapon("Talons", 10);

        this.VIT = 4;
        this.STR = 3;
        this.INT = 2;
        this.DEF = 3;
        this.LUC = 1;
        
        statSetup();
    }
    
    public void statSetup(){
        health = calculateVitality(VIT);
        strength = calculateCoreStats(STR);
        intelligence = calculateCoreStats(INT);
        
        currentHealth = health;
        currentStrength = strength;
        currentIntelligence = intelligence;
    }
    public double calculateCoreStats(int statLevel){
        return Math.floor(200*(1-Math.pow(0.8, statLevel))/(1-0.8));
    }
    public double calculateVitality(int statLevel){
        return Math.floor(262 * ((1-Math.pow(0.95, statLevel))/(1-0.95)));
    }

}
