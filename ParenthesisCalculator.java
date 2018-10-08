/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;
/**
 *
 * @author rudolfmusika
 */
public class ParenthesisCalculator extends PrecedenceCalculator {

    public ParenthesisCalculator(String title) {
        super(title);
    }
    public ParenthesisCalculator() {
        this("Calculator With Operator Precedence and Parentheses");
    }
    /**
     * The implemented method inherits from its previous class PrecedenceCalculator
     * and there are changes made in that it now accounts for the Left and 
     * Right Parenthesis. 
     * @throws Error: Something is wrong if the calculator does not work & also
     *              if at the end the number of right parenthesis is not as
     *          equal to the left parenthesis.
     * @return (Double)getStack().pop() the result of the evaluation in the
     *          the calculated expression written out by the user.
     *   
     */
    @Override
    public double evaluate() {
        setState(State.START);
        while (true) {
            switch (getState()) {
                 case START:                     
                    startPr(); 
                    break;
                case NUMBER:
                    numberPr();
                    break;
                case LEFT_PAREN:
                    leftPr();
                    break;
                case RIGHT_PAREN:
                    rightPr();
                    break;
                case OPERATOR:
                    operatorPr(); 
                    break;
                case END:                    
                    if (leftPar!=rightPar) {
                        throw new RuntimeException("Error -- mismatched parentheses");
                    }
                    end();
                    return (Double)getStack().pop();
                                         
                default: 
                    throw new Error("Something is wrong in ParenthesisCalculator.evaluate"); // shouldn't happen
            }
        }

    }
    /**
     * This reducing method inherits from the extended class such that 
     * while there are numbers with operators on the stack and the same 
     * parameters from the last class ie: '+' or '-' or end of file it reduces
     * the numbers by performing operations as shown. The method then also 
     * accounts for the right parenthesis in reduction. while the stack size
     * is greater than 1 is peeks into the stack, if there no left parenthesis,
     * the program is set to pop the stack. and push the reduced number.
     */
    @Override
    public void reduce() {
        if(getDispenser().getChar()=='+'||
                getDispenser().getChar()=='-'||
                getDispenser().tokenIsEOF()){
            while(numOpNumOnStack()){
                reduceNumOpNum();
            }
        }
        else if (getDispenser().tokenIsRightParen()){
            while(numOpNumOnStack()){
                reduceNumOpNum();
            }
            double reducedNumPar = (Double)getStack().pop();
            if(getStack().size()<1==false){
                if(getStack().peek()!= State.LEFT_PAREN){getStack().pop();}
                getStack().push(reducedNumPar);
            }
        }
    }  
    /**
     * Mutates the start state such that if it receives a token of left or
     * number, it distributes them to the given states. It also accounts for 
     * error handling as shown.
     */
    private void startPr() {
        leftPar=0;
        rightPar=0;
        getDispenser().advance();             
        if(getDispenser().tokenIsLeftParen()) setState(State.LEFT_PAREN);      
        else if(getDispenser().tokenIsNumber()) setState(State.NUMBER);
        else syntaxError(NUM);
    }
    /**
     * Mutates the number state such that when it receives a token of
     * Operator or right Parenthesis or end of file, it sends it to the given 
     * states.It also accounts for error handling as shown.
     */
    private void numberPr() {
        shift();
        getDispenser().advance();
        if(!getStack().isEmpty()&&!getDispenser().tokenIsRightParen())
            reduce();
        
        if (getDispenser().tokenIsEOF()) setState(State.END);
        else if (getDispenser().tokenIsOperator()) setState(State.OPERATOR);
        else if(getDispenser().tokenIsRightParen())setState(State.RIGHT_PAREN);
        else if(getDispenser().tokenIsLeftParen()) syntaxError(OP_OR_END);
        else syntaxError(OP_OR_END);
    }
    /**
     * Mutates the given properties of The state Operator such that 
     * if the dispenser sends a number, it sends it to the number state also
     * accounting for the error handling as shown.
     * 
     */   
    private void operatorPr() {
        shift(); 
        getDispenser().advance();
        if(getDispenser().tokenIsLeftParen()) setState(State.LEFT_PAREN);
        else if (getDispenser().tokenIsNumber()) setState(State.NUMBER);
        else if(getDispenser().tokenIsRightParen()) syntaxError(OP);
        else syntaxError(NUM);
    }
    
    private void leftPr(){
        ++leftPar;
        shift();
        getDispenser().advance();
              
        if(getDispenser().tokenIsNumber()) setState(State.NUMBER);
        else if(getDispenser().tokenIsLeftParen()) setState(State.LEFT_PAREN);
        else syntaxError(NOL);        
    }
    
    private void rightPr(){
        ++rightPar;
        if(rightPar >leftPar ) 
        {throw new RuntimeException("Error -- mismatched parentheses");}
        if(!getStack().empty()) reduce();
        getDispenser().advance();
        
        if (getDispenser().tokenIsEOF()) setState(State.END);
        else if(getDispenser().tokenIsOperator()) setState(State.OPERATOR);
        else if(getDispenser().tokenIsRightParen()) setState(State.RIGHT_PAREN);
        else if(getDispenser().tokenIsLeftParen()) syntaxError(OP);
    }
    
    @Override
    public void syntaxError(String expected) {
        throw new RuntimeException("Error -- " + expected + " expected. Found: " + getDispenser().getToken());
    }
    
    
    public static final String NOL = "number or left parenthesis";
    public static final String OP = "operator";
    public static final String OPL = "(";
    private int leftPar;
    private int rightPar;
   
    
    
    
    /**
     * Tests the equality of two double operands after truncating all 
     * but the 10 most significant digits to the right of the decimal point.
     * @param op1 the first operand
     * @param op2 the second operand
     * @return true if the truncated operands are equal, false otherwise
     */
    public static boolean equals(double op1, double op2) {
        return equals(op1, op2, 10);
    }
    
    private static double truncate (double d, int places) {
        double multiplier = Math.pow(10,places);
        return Math.round(d * multiplier)/(double)multiplier;
    }
        public static void main(String[] args) {
	double op1 = 13.449420783078308;
        double op2 = 13.449420783078306;
        System.out.println(op1);
        System.out.println(op2);
        for (int p = 5; p <= 15; p++) {
            System.out.println(truncate(op1, p) + "  " + truncate(op2, p) + "  " + NoPrecedenceCalculator.equals(op1, op2, p));
        }
    }
}
