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

public class PrecedenceCalculator extends NoPrecedenceCalculator {
    public PrecedenceCalculator(String title){
        super(title);
    }
        public PrecedenceCalculator() {
        this("Calculator With Operator Precedence");
    }        
    /**
     * Reduces the stack from either NUM or NUM OP NUM to NUM.
     * This method is public so that it can be used or overridden by subclasses.
     */
    @Override
    public void reduce() {
        if(getDispenser().getChar()=='+'||
                getDispenser().getChar()=='-'||
                getDispenser().tokenIsEOF()){
            while(getStack().size()<3 == false){
                reduceNumOpNum();
            }
        }            
    }

    
    
    
    
    
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
















//    private int getPrecedence(char in_opr)
//    {
//        /*Initialize the integer variable to set the precedence*/
//        int prece = -1;
//        //Switch to handle difference operators
//        switch(in_opr)
//        {
//                //In the case of '('
//            case "(":
//                //Set precedence as 0
//                prece = 0;
//                //Break the case
//                break;
//                //In the case of - and +
//            case "+":
//            case "-":
//                //Set precedence as 1
//                prece = 1;
//                //Break the case
//                break;
//            case "*":
//            case "/":
//                //Set precedence as 2
//                prece = 2;
//                //Break the case
//                break;
//        }
//        //Return the obtained precedence value
//        return prece;
//    }// End the function GetPrecedence
    
//        private Stack<Object> opStack;
//        private TokenDispenser dispenserP;
////////////////////////////////////////////////////////////////   
        
     /**
     * Tests the equality of two double operands after truncating all 
     * but the 10 most significant digits to the right of the decimal point.
     * @param op1 the first operand
     * @param op2 the second operand
     * @return true if the truncated operands are equal, false otherwise
     */
//    public static boolean equals(double op1, double op2) {
//        return equals(op1, op2, 10);
//    }
//    
//    private static double truncate (double d, int places) {
//        double multiplier = Math.pow(10,places);
//        return Math.round(d * multiplier)/(double)multiplier;
//    }
//        public static void main(String[] args) {
//	double op1 = 13.449420783078308;
//        double op2 = 13.449420783078306;
//        System.out.println(op1);
//        System.out.println(op2);
//        for (int p = 5; p <= 15; p++) {
//            System.out.println(truncate(op1, p) + "  " + truncate(op2, p) + "  " + NoPrecedenceCalculator.equals(op1, op2, p));
//        }
//    }
//}





//        dispenserP.advance();
        
//        opStack = (Stack<Object>) super.getStack();
//        opStack = super.getStack();
//        while(numOpNumOnStack()==true && 
//                getPrecedence(dispenserP.toString())<=
//                getPrecedence(opStack.peek().toString())){
//
///////////////////////////Works 50%/////////////////////            
//            dispenserP.advance();
//            if( dispenserP.tokenIsEOF()) {
//                if((opStack.get(0).equals("/")) ||
//                        opStack.get(0).equals("*"))                   
//                    super.reduceNumOpNum();
//                else if((opStack.get(0).equals("+"))||
//                    (opStack.get(0).equals("-")))
//                        super.setState(State.END);
//                }
//            else if (dispenserP.tokenIsOperator() ){
//                if((opStack.get(0).equals("*")) || opStack.get(0).equals("/"))
////                      super.setState(State.OPERATOR);
//                    super.reduceNumOpNum();
////                        super.setState(State.OPERATOR);
//                else if(opStack.get(0).equals("-"))
////                    super.reduceNumOpNum();
//                    super.setState(State.OPERATOR);
//                else if (opStack.get(0).equals("+")) 
////                    super.reduceNumOpNum(); 
//                    super.setState(State.OPERATOR);
//                }                
//        }    
//        super.setState(State.OPERATOR);
////            reduceNumOpNum();