/*
 * This class represents a polynomial. The polynomial is represented by a 
 * programmer-defined linked list of generic Nodes. There is an inner class 
 * Node that implements a Node.
 */

/**
 * @version 4/10/2019
 * @author Luis Oliveros [6131616]
 */
public class Polynomial 
{
    private Node head;    // points to the first Node of a Polynomial

   /**
    * Default constructor creates a Polynomial with no terms
    */
   public Polynomial()  // DO NOT MODIFY THIS CONSTRUCTOR
   {
      head = null;
   }
   
   /**
    * Creates a "deep copy" of a given Polynomial. I.e. a new Polynomial with
    * identical terms
    * @param p Polynomial to be copied
    */
   public Polynomial(Polynomial p)  // a "copy" constructor
   {
      //if the copy, first copy, and first copy terms are not empty 
      if (p != null && p.head != null && p.head.info != null) 
      {
          //create new Node from a new Term with the 
          //copies cofficient and exponent
          Node copy = new Node(new Term(p.head.info.getCoeff(),
          p.head.info.getExp()));
          copy.next = null; //pointer to copy set to null
          head = copy; // first equal to copy
          Node  temp1 = copy; // new node equals copy
          Node  temp2 = p.head; //new node equals first copy
          
          //while the pointer from first copy is not empty
          while (temp2.next != null) 
          {
              temp2 = temp2.next; // first copy points to next
              
              //points to a new node made up of a new Term with
              // the first copies coefficient and exponent
              copy = new Node(new Term(temp2.info.getCoeff(), 
              temp2.info.getExp())); 
              copy.next = null; //pointer set to null
              temp1.next = copy; //next copy set to copy
              temp1 = copy;//returns copy to temp1
          }
      }
   }
   
   /**
    * Creates a new Term and Node containing it and inserts it in its proper
    * place in this Polynomial (i.e. in ascending order by exponent) 
    * @param coeff the coefficient of the new Term
    * @param expo the exponent of the new Term
    */
   public void addTerm(int coeff, int expo)
   {
      Term t = new Term(coeff, expo); //new Term created with params
      Node first = new Node(t);//new Node created to contain Term
      
      //if the first node is empty
      if(head == null)
      {
          head = first;//first node is first in list
          head.next = null; //pointer to next node set to null
      }
      else
      {
          Node node = head; //node gets first node
	  Node lastNode = null;//set to null
          while(true)
          {
              //if the first node is not empty
              if(node != null)
              {
                  //if the first nodes exponent is less than or equal to the 
                  //new nodes exponent
                  if(node.info.getExp() <= first.info.getExp())
                  {
                      lastNode = node; //last node takes first nodes values
                      node = node.next;//first node points to next in line
                  }
                  else
                  {
                      break;//stop loop
                  }
              }
              else
              {
                  break; //exit loop
              }
          }
          //if lastNode is null
          if (lastNode == null) 
          {
              first.next = head; // sets next node to the first pointer
             head = first;//next node gets the values of the new node and Term
          }
          else 
          {
              Node temp = lastNode.next;// temp assigned pointer to next 
              //lastNode node
              lastNode.next = first;// pointer to next node of lastNode is 
              //assigned the new node with the new term created
              first.next = temp;// the next node from the new node created 
              //assigned to a pointer to next node in lastNode.
          }
      }
   }

  
   /**
    * Returns a polynomial as a String in this form: x + 3x^2 + 7x^3 + x^5
    * @return the polynomial as a String
    */
   public String toString()
   {
       if (head == null)  //if the first node is empty
       {
           return null; //returns nothing
       }
        
       String out = " ";
       Node temp = head;   //the pointer to first node
       collectTerms(); // terms collected to format
       
       while ( temp != null)//while not the last node
       {
            out += temp.info.toString();    //current node to output
            temp = temp.next;      //assigned to the next node
            if (temp != null)      //if not pointing to last node
            {
                out += " + ";
            }
       }
       return out + "\n";      
  }
   
   // collect terms of a Polynomial object. I.e. replace all terms having the 
   // same exponent with a single term which is their sum
   private void collectTerms()
   {
       Node temp = head ; //start at first node  
        while( temp.next != null )//while the pointer to the last node exists
        {
            Term thisTerm = temp.info; //Term for this method
            Term thatTerm = temp.next.info; //the next Term 
            //compare and add the coefficients while the exponents of the next
            //term is the same as this terms exponent
            while (thisTerm.getExp() == thatTerm.getExp())
            {
                int newCoef = thisTerm.getCoeff() + thatTerm.getCoeff();
                Term newTerm = new Term( newCoef, thisTerm.getExp() );
                temp.info = newTerm; //this.term gets the new resulting term
                temp.next = temp.next.next; //pointer to the term 
                //after the next
                thisTerm = temp.info; //new term assigned to this.term 
                thatTerm = temp.next.info; //new next term assigned to other 
                //term
            }
            //if the pointer returns empty
            if ( temp.next == null )
            {
                break; //exit the loop
            }    
            temp = temp.next; //assign to point to the next term
        }
   }
   
   /**
    * Multiply this Polynomial by another Polynomial
    * @param p the other Polynomial
    * @return the Polynomial product
    */
   public Polynomial polyMultiply(Polynomial p)
   {
       //resulting polynomial starts as the multiplying polynomial 
        Polynomial resultPoly = new Polynomial(); 
        Node temp1 = head ; //first node
        Node temp2 = p.head ; //first node of polyMultiply
        
         //Multiply each term from first node by each term of the
         // polyMultiply node
         while ( temp1 != null ) //until this node doesnt point to anything
        {
            while ( temp2 != null ) //until polyMultiply node doesnt point
                //to anything
            {
                //multiply the coeficients
                int newCoef = temp2.info.getCoeff() * temp1.info.getCoeff() ;
                //add the exponents
                int newExp = temp2.info.getExp() + temp1.info.getExp() ;
                //add the new term to the resulting 
                resultPoly.addTerm( newCoef, newExp ) ; 
                temp2 = temp2.next ;  //assigned to next node
            }
            temp2 = p.head ; //reset the polyMultiply nodes
            temp1 = temp1.next ; //move to the next node of this node
        }
        return resultPoly  ; //resulting polynomial
   }
   
   /**
    * Add this Polynomial and another Polynomial
    * @param p the other Polynomial
    * @return the Polynomial sum
    */
   public Polynomial polyAdd(Polynomial p)
   {  
        //resulting polynomial starts as the adding polynomial 
        Polynomial resultPoly = new Polynomial(p) ; 
        Node temp = head ; //start from the first node
        //while next node is not empty
        while ( temp != null )
        {
            //add the terms from this polynomial to the resulting polynomial
            resultPoly.addTerm( temp.info.getCoeff(), temp.info.getExp() ) ;
            temp = temp.next ; //assigned to the next node
        }
        return resultPoly; //resulting polynomial
   }
   
   // Node class definition - DO NOT MODIFY!
   class Node <E extends Term>
   {
      private E info ;     // each node stores an object of the 
                           // type-parameter class...
      private Node next ;  // ...and a pointer to another node

      // Node Constructor 
      // parameter x is an object of the type-parameter class
      Node(E x)         
      {
         info = x;	    // set info portion to parameter passed
         next = null;	
      }
   } // end of Node class definition ============================
} // end of Polynomial class definition =========================
    