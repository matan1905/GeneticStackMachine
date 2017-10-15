import java.util.Random;

public class Creature implements Comparable<Creature> {
    byte[] code;
    static float mutationRate = 0.01f;
    int score;
    static Random random = new Random(23);

    public Creature(byte[] newCode) {
        this.code=newCode;
    }

    Creature(int maxCode){
        code = new byte[maxCode];
        for (int i = 0; i < code.length; i++) {
            code[i]=(byte) random.nextInt(8);
        }
    }

    void evaluate(Processor processor,int expectedScore){
        int s=processor.process(code);
        if(s==Integer.MIN_VALUE) score=s;
        else {score += -Math.abs(expectedScore-s);
        }
    }

    Creature reproduce(Creature c){
        byte[] newCode = new byte[code.length];
        for (int i = 0; i < newCode.length; i++) {
            if(random.nextFloat() >mutationRate){
            if(random.nextBoolean()){
                newCode[i] = code[i];
            }
            else {
                newCode[i] = c.code[i];
            }
            }
            else newCode[i]= (byte) random.nextInt(8);
        }
        return new Creature(newCode);
    }

    @Override
    public int compareTo(Creature o) {
        return Integer.compare(score,o.score);
    }
}
