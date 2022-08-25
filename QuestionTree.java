/*
Anderson Lu
Cse 143 AN with May Wang
Homework 7, 20 questions
This program guesses the object or animal that the user is thinking
of by asking yes/no questions. With the user's input, the program
is able to narrow down the the possible animals that the user
is thinking of until it can guess the animal.
*/
import java.util.*;
import java.io.*;

public class QuestionTree {
   private QuestionNode root;
   private Scanner console;
   
   /*
   Defines the root for the overall tree, with a
   computer object. Also defines the scanner console.
   */
   public QuestionTree() {
      root = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   /*
   Takes in a Scanner input as a parameter and
   adds the questions and answers of the text to the tree.
   */
   public void read(Scanner input) {
      root = recurRead(input, root);
   }
   
   /*
   Takes in Scanner input and QuestionNode root as parameters.
   Recurs through the text and checks for the different types of
   values and the value of that type.
   */
   private QuestionNode recurRead(Scanner input, QuestionNode root) {
      //Sets the questions and the answers to each questions.
      String type = input.nextLine();
      String value = input.nextLine();
      //Sets a pointer to the current tree node.
      QuestionNode pointer = new QuestionNode(value);
      //Recurs through the text and checks if line is question or answer.
      if (type.equalsIgnoreCase("Q:")) {
         pointer.left = recurRead(input, pointer);
         pointer.right = recurRead(input, pointer);
      }
      return pointer;
   }
   
   /*
   Takes in a PrintStream output as a parameter and
   recurs through the tree to print the data of the tree
   to a text file.
   */
   public void write(PrintStream output) {
      recurWrite(output, root);
   }
   
   /*
   Takes in PrintStream output and QuestionNode cur as parameters.
   Checks if the treenodes are answers or questions. Prints the data
   of the tree to a text hall.
   */
   private void recurWrite(PrintStream output, QuestionNode cur) {
      //Checks the tree is empty.
      if (cur != null) {
         //Checks if the node in the tree is an answer or question.
         if (cur.isLeafNode()) {
            output.println("A:");
         } else {
            output.println("Q:");
         }
         //Adds the data along with the question or answer line.
         output.println(cur.data);
         //recurs through the tree.
         recurWrite(output, cur.left);
         recurWrite(output, cur.right);
      }
   }
   
   /*
   Asks the user questions to determine the object
   or animal that the user is thinking of. Changes the nodes
   of the tree to factor in the user's object or animal, if
   the computer can not guess the animal or object.
   */
   public void askQuestions() {
      root = recurQuestions(root);
   }
   
   /*
   Takes in a QuestionNode cur as a parameter and
   asks the users questions from the tree to determine the
   object or animal that the user is thinking of. If the computer
   can not guess the correct animal or object from the questions,
   the computer asks the user for the object and the question for that
   object. And adds the question and objects to the tree of answers
   and questions.
   */
   private QuestionNode recurQuestions(QuestionNode cur) {
      //Checks of the node of the tree is a question.
      if (!cur.isLeafNode()) {
         //Checks if the user input is true.
         boolean answer = yesTo(cur.data);
         //Recurs down the left side of the branch if true.
         //Recurs down the right side of the branch if false.
         if (answer) {
            cur.left = recurQuestions(cur.left);
         } else {
            cur.right = recurQuestions(cur.right);
         }
      } else {
         //Guesses the user's object or animal.
         boolean input = yesTo("Would your object happen to be " +
         cur.data + "?");
         if (input) {
            System.out.println("Great, I got it right!");
            //Adds the user's object or animals if guess is wrong.
         } else {
            cur = addInput(cur);
         }
      }
      return cur;
   }
   
   /*
   Takes in a QuestionNode cur as a parameter and asks the user
   to identify the object or animal of thought. Asks for the question
   of that object and the object itself. Adds the data to the
   tree.
   */
   private QuestionNode addInput(QuestionNode cur) {
      //Creates a String for the user's input.
      System.out.print("What is the name of your object? ");
      String object = console.nextLine();
      System.out.println("Please give me a yes/no question that");
      System.out.println("distinguishes between your object");
      System.out.print("and mine--> ");
      //Creates a String for the identification of the input.
      String question = console.nextLine();
      boolean add = yesTo("And what is the answer for your object?");
      //Creates a pointer to the old object guessed.
      QuestionNode temp = cur;
      cur = new QuestionNode(question);
      //Adds the object accordingly to the tree.
      //Adds in the respect identification in the tree.
      if (add) {
         cur.left = new QuestionNode(object);
         cur.right = temp;
      } else {
         cur.right = new QuestionNode(object);
         cur.left = temp;
      }
      return cur;
   }
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}