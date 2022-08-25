/*
Anderson Lu
Cse 143 AN with May Wang
Homework 7, 20 questions
This program creates and defines the tree
and the nodes in the tree. Defining and adding
values to the nodes in the tree.
*/
public class QuestionNode {
   //Creates varibles for the tree nodes.
   public String data;
   public QuestionNode left;
   public QuestionNode right;
   
   /*
   Takes a String answer as a parameter and
   sets the answer to a answer node in the tree.
   */
   public QuestionNode(String answer) {
      this.data = answer;
   }
   
   /*
   Takes in QuestionNode left and QuestionNode right as parameters.
   Sets both the nodes to either correct answer on the left, or a
   wrong answer on the right.
   */
   public QuestionNode(QuestionNode left, QuestionNode right) {
      this.left = left;
      this.right = right;
   }
   
   /*
   Checks if the node in the tree is a leaf node.
   Leafnode if no branches after leaf node.
   */
   public boolean isLeafNode() {
      return left == null || right == null;
   }
}