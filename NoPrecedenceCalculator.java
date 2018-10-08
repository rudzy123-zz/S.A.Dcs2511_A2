/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;
import util.TokenDispenser;
/**
 *
 * @author rudolfmusika
 * This implementation uses Simple Calculator variables defined and
 * adds a new method to the evaluate method such that one is able 
 * to add multiple variables in a No Precedence fashion.
 */

public class NoPrecedenceCalculator extends SimpleCalculator{
    public NoPrecedenceCalculator(String title) {
        super(title);
    }
    
    public NoPrecedenceCalculator() {
        this("Calculator Without Operator Precedence");   
    }
    /**
     * 
     * @return The implementation will return the evaluated 
     */
    @Override
    public double evaluate() {
            setState(State.START);
        while (true) {
            switch (getState()) {
                 case START:                     
                    startNp(); 
                    break;
                case NUMBER:
                    numberNp();
                    reduce();
                    break;
                case OPERATOR:
                    operatorNp(); 
                    break;
                case END:
                    end();
                    return (Double)getStack().pop();
                                         
                default: 
                    throw new Error("Something is wrong in NoPrecedenceCalculator.evaluate"); // shouldn't happen
            }
        }          
    }       
    private void startNp() {
        dispenserNp = getDispenser();
        dispenserNp.advance();
        
        if (!dispenserNp.tokenIsNumber()) {
            syntaxError(NUM);
            }        
        setState(State.NUMBER);
    }
    
    private void numberNp() {
        super.shift();
        dispenserNp = getDispenser();
        dispenserNp.advance();
        if (dispenserNp.tokenIsEOF()) 
            setState(State.END);
        else if (dispenserNp.tokenIsOperator()) 
            setState(State.OPERATOR);
        else if (!dispenserNp.tokenIsEOF()) 
            syntaxError(OP_OR_END);
        else if(!dispenserNp.tokenIsEOF()&& !dispenserNp.tokenIsOperator()) 
            syntaxError(EOI);    
    }
        
    private void operatorNp() {
        super.shift();
        dispenserNp = getDispenser(); 
        dispenserNp.advance();
        if (!dispenserNp.tokenIsNumber()) 
            syntaxError(NUM);
        setState(State.NUMBER);
    } 
    /**
     * Throws a syntax error indicating what the parser was expecting and what it received.
     * @param expected a string indicating what the parser was expecting
     */
   
    @Override
    public void syntaxError(String expected) {
        throw new RuntimeException("Error -- " + expected + " expected. Found: " + dispenserNp.getToken());
    }
    
    private TokenDispenser dispenserNp;  // the tokenizing parser  

////////////////////////////////////////////////////////////////    
        
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
