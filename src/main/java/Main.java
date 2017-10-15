import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
static Random random = new Random(1);
    static Comparator<? super Creature> creatureComaperator= new Comparator<Creature>() {
        @Override
        public int compare(Creature o1, Creature o2) {
            return  Integer.compare(o2.score, o1.score);
        }
    };
public static void main(String[] args){
    Processor p = new Processor();
    byte [] b = new byte[]{0,1,0,1,3};
    p.process(b);
    int codeLength = 5;
    //Create a population V
    //eval population V
    //rank V
    //mate a new population with slight mutation chance
    //redo until satisfied
    ArrayList<Creature> creatures = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
        creatures.add(new Creature(codeLength));
    }

    do {
        System.out.println(Integer.compare(1,2) +" sss");

        getScores(p,creatures,5);
        creatures.sort(creatureComaperator);
        removeWorst(creatures,20);
        repopulateTop(creatures,10,40);
        System.out.println(Arrays.toString(creatures.get(0).code) + " " + creatures.get(0).score);
    } while(creatures.get(0).score !=0);

}

    private static void repopulateTop(ArrayList<Creature> creatures,int top,int amount) {
        for (int i = 0; i < amount; i++) {
            creatures.add(creatures.get(random.nextInt(top)).reproduce(creatures.get(random.nextInt(top))));
        }
    }

    private static void removeWorst(ArrayList<Creature> creatures, int num) {
    if(creatures.size() >num+1) return;
        for (int i = 0; i < num; i++) {
            creatures.remove(creatures.size()-1-num);
        }
    }

    private static void getScores(Processor p, ArrayList<Creature> creatures,int times) {
        for (Creature creature : creatures) {
            creature.score=0;
        }
        for (int i = 0; i < times; i++) {
            int x = random.nextInt(8);
            int y = random.nextInt(8);
            p.setVars(x, y);
            for (Creature cr : creatures) {
                cr.evaluate(p, x+y*y);
            }
        }


    }

}
