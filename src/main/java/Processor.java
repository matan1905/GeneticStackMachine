import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Stack;


public class Processor {

    int[] vars = new int[2];

    public static final byte PSH = 10;//<Stack> (int)x
    public static final byte PSHB = 0;//<Stack>(byte) x
    public static final byte GET = 8; //<stack> var x
    public static final byte SET = 9; //var x = y

    //Stack
    public static final byte ADD = 3; //x+y
    public static final byte SUB = 4; //x-y
    public static final byte DIV = 1; //x/y
    public static final byte MULT = 2; //x*y

    //Genetic testing vars
    public static final byte getX=5;
    public static final byte getY=6;

    public Integer process(byte[] code) {
        ByteBuffer bb = ByteBuffer.wrap(code);
        Stack<Integer> stack = new Stack<>();
        while (bb.hasRemaining()) {
            switch (bb.get()) {
                case PSH:
                    push(bb, stack);
                    break;
                    case PSHB:
                    pushByte(bb, stack);
                    break;
                case GET:get(bb,stack);
                    break;
                case SET:set(bb);
                    break;
                case ADD:
                    add( stack);
                    break;
                case SUB:
                    sub( stack);
                    break;
                case DIV:
                    div( stack);
                    break;
                case MULT:
                    mult( stack);
                    break;
                case getX:stack.push(vars[0]); break;
                case getY:stack.push(vars[1]); break;

            }
        }

        if(stack.size()>0)
        return stack.pop();


        return Integer.MIN_VALUE;
    }

    public void setVars(int x,int y){
        vars[0]=x;
        vars[1]=y;
    }
    private void get(ByteBuffer bb,Stack<Integer> stack){
        //stack.push((int)bb.getShort());//implement var drawer
    }
    private void set(ByteBuffer bb){
        //bb.getShort();
        //bb.getInt();
    }

    private void add( Stack<Integer> stack) {
        if (stack.size() > 1)
            stack.push(stack.pop() + stack.pop());
    }

    private void sub( Stack<Integer> stack) {
        if (stack.size() > 1)
            stack.push(stack.pop() - stack.pop());
    }

    private void mult( Stack<Integer> stack) {
        if (stack.size() > 1)
            stack.push(stack.pop() * stack.pop());
    }

    private void div( Stack<Integer> stack) {
        if (stack.size() < 2) return;
        int a = stack.pop();
        int b = stack.pop();
        if(b==0) return;
            stack.push(a / b);
    }


    private void push(ByteBuffer bb, Stack<Integer> stack) {
       try{ stack.push(bb.getInt());}
       catch (BufferUnderflowException e){}
    }

    private void pushByte(ByteBuffer bb, Stack<Integer> stack) {
        try{stack.push((int)bb.get());}
         catch (BufferUnderflowException e){}
    }

}
