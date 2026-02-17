/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Abulele
 */
class Enemies implements Serializable {
    
    ArrayList<Enemy> enemies = new ArrayList<>(Arrays.asList(new Enemy("Ogre", """
                            "The hulking brutes of the wild."
                            Ogres are muscular, human-like giants standing 9 to 10 feet tall and weighing over 600 pounds. 
                            They have hideous, disproportioned features, including large noses, tusks, warts, and often possess dull, 
                            earth-toned or sometimes greenish-grey skin. They have notoriously quick tempers, often resorting to violent 
                            tantrums when frustrated.""", 1, 1, 1, 1, 1, 1), new Enemy("Crocotta", """
                                "The Cackling Predators of the Badlands."
                                Crocottas are lean, hyena-like monstrosities roughly the size of a large lion, with powerful forequarters, 
                                sloped backs, and long, bone-crushing jaws filled with jagged teeth. Their coarse fur ranges from dusty brown 
                                to ash-grey, often mottled to blend into rocky plains and scrublands. Most unsettling is their intelligence 
                                and cruel cunning\u2014crocottas are known to mimic human voices and familiar sounds to lure prey into ambushes. 
                                They delight in panic and pursuit, hunting not just to feed, but to terrorize.""", 1, 1, 1, 1, 1, 1), new Enemy("Bicorn", """
                              "The Two-Horned Wardens of the Wilds."
                              Bicorns are massive, bull-like beasts with thick, corded muscle and a low, powerful stance built for 
                              unstoppable charges. They are distinguished by their pair of forward-curving horns, polished smooth from 
                              constant combat and territorial clashes. Their hides are dense and leathery, often scarred from battles. 
                              When provoked, a bicorn\u2019s charge can shatter shields and uproot trees.""", 1, 1, 1, 1, 1, 1), new Enemy("Yale", """
                            "The Shadow-Maned Hunters of the High Slopes."
                            Yales are powerful, goat- or antelope-like beasts with compact, muscular bodies and dense coats suited for 
                            cold, mountainous terrain. Their most striking feature is a pair of large, swiveling horns capable of rotating 
                            independently, making ambush nearly impossible. Cunning and relentless, they pursue intruders across cliffs 
                            and narrow ledges.""", 1, 1, 1, 1, 1, 1), new Enemy("Wyvern", """
                              "The Venom-Winged Terrors of the Sky."
                              Wyverns are ferocious, two-legged drakes with vast, leathery wings that double as forelimbs. Their lean but 
                              powerful bodies are built for speed and aerial dominance, with long, barbed tails often ending in a venomous 
                              stinger. Though lacking the cunning of true dragons, their raw aggression makes them a nightmare for travelers.""", 1, 1, 1, 1, 1, 1)));
    
}
