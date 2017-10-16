import java.util.Random;

public class Creature implements Comparable<Creature> {
    byte[] code;
    static float mutationRate = 0.01f;
    double score;
    static Random random = new Random();

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
        else {
            score -= logistic(Math.abs(expectedScore-s));

        }
    }

    double logistic(int x){
        return Math.tanh(x);
        //return 1/(1+Math.pow(Math.E,-x));

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
        return Double.compare(score,o.score);
    }
}
