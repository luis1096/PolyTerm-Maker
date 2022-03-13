/*
 * This class represents a term in an algebraic expression. A term consists 
 * of an integer coefficient and a nonnegative integer exponent.
 */

/**
 * @version 4/10/2019
 * @author Luis Oliveros [6131616]
 */
 
/**
 * /*
 * This class represents a term in an algebraic expression. A term consists 
 * of an integer coefficient and a nonnegative integer exponent.
 */
public class Term 
{
    private int coeff; //coefficent of term
    private int exp; // exponent of term
    
    /**
     * constructor sets the coefficient and exponent 
     * @param coeff coefficient of term
     * @param exp exponent of term
     */
    public Term(int coeff, int exp)
    {
        //initializes and sets the values for
        //coefficent and exponent of the term.
        this.coeff = coeff;
        this.exp = exp;
    }
    
    /**
     * method that gets the exponent for the term.
     * @return the exponent for the ter.m
     */
    public int getExp()
    {
        return exp; //exponent returned
    }
    
    /**
     * method that gets the coefficient of the term.
     * @return the coefficient of the term.
     */
    public int getCoeff()
    {
        return coeff; //coefficient returned
    }
    
    /**
     * toString method returns the terms in a^xb format
     * @return the formated term.
     */
    public String toString()
    {
        //if coefficient and exponent are 1 return only x
        if(coeff == 1 && exp == 1)
        {
            return "x";// x in term only
        }
        //if coefficient is 1 return x^ along with the exponent only 
        else if(coeff == 1)
        {
            return "x^" + exp; //exponent and x^ only
        }
        //if exponent is 1 return just coefficient along with x
        else if(exp == 1)
        {
            return coeff + "x"; // coefficient and x only
        }
        //if exponent is 1 return only coefficient.
        else if(exp == 0)
        {
            return coeff + ""; // coefficient only
        }
        //return the whole complete term
        else
        {
            return coeff + "x^" + exp; //the term fully
        }
    }
}
        


    
    

