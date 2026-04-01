/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonraiders;

/**
 *
 * @author Abulele
 */
class Skill {
    
    public String name;
    public double attack;
    public double potency; //-= for debuff or += for buff

    public Skill(String name, double attack, double potency) {
        this.name = name;
        this.attack = attack;
        this.potency = potency;
    }

    public void skillInformation() {
        System.out.println("=".repeat(60) + "\n[" + name + "]" + "\n" + "=".repeat(60) + "\nAttack: " + attack + "\nEffect: " + potency + "\n" + "=".repeat(60));
    }
    
}
