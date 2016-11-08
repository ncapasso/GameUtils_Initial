package main.java;

import main.java.utils.RandomNumGenerator;

import java.util.HashMap;
import java.util.Random;

/**
 * main.java.DiceRoller method for testing and implementing main.java.utils.RandomNumGenerator.
 */
public class DiceRoller {
    private RandomNumGenerator roll;
    private int sides;

    private HashMap<Integer, Integer> retrievedRolls = new HashMap<>();

    private DiceRoller(){
        roll = new RandomNumGenerator();
    }

    public DiceRoller(int sides){
        this();
        setSides(sides);
        retrievedRolls.put(sides, rollDice(sides));
    }

    public DiceRoller(int sides, int rolls){
        this();
        setSides(sides);
        for(int i = 0; i<rolls; i++){
            retrievedRolls.put(sides, rollDice(sides));
        }
    }

    public void printDice(){
        if(retrievedRolls.size() == 0){
            System.err.println("Error: No dice have been rolled.");
            return;
        }

        System.out.println("Your rolls: ");
        for( Integer key : retrievedRolls.keySet()){
            System.out.println("d"+Integer.toString(key)+" : "+retrievedRolls.get(key));
        }
    }


    public void setSides(int sides){
        this.sides = sides;
    }

    public int getSides(){
        return this.sides;
    }

    public int rollDice(int sides){
        return new Random(roll.nextLong()).nextInt(sides) + 1;
    }

    public int rollDice(int sides, long seed){
        RandomNumGenerator generator = new RandomNumGenerator(seed);
        return new Random(generator.nextLong()).nextInt(sides) + 1;
    }


}
