package SemanticAnalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SemanticAnalyzer {
	
	public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    	
    	
    	String welcome; header();
    	welcome = "\nWelcome to JAVA Semantic Analyzer! " +
    			"Enter your single-line assignment statement, and this Semantic Analyzer "
    			+ "will meticulously assess it to ensure that the chosen data type "
    			+ "aligns perfectly with the assigned value, whether it's integers, strings, or doubles." 
    			+ "It will also confirm whether it's semantically correct or incorrect\n";
    	   	
    	
    	System.out.println(welcome);
    	 
    	while (true) {
    		
    		System.out.println("1. Semantic Analyzer Checker");
            System.out.println("2. Exit");
            
            System.out.print("\nEnter Number: ");
            int choice = sc.nextInt();
            
            System.out.println();
           
            switch (choice) {
            case 1:        	
            	input();
            	break;                                     
			case 2:
            	displayprogrammer();
                System.exit(0);
            default:
                System.out.println("Error. Please input number only from 1 - 2.\n");
                break;
            }  	
    	}  	
    }

    
    public static void input() {

       do {  	   
    	   System.out.print("Enter expression: ");
           Scanner sc = new Scanner(System.in);
           String input = sc.nextLine();

           ArrayList<String> lexemes = lex(input);
    	   
    	   if (analyzeSemantic(lexemes)) {
            System.out.println("Semantically Correct!");
    	   }  else {
    		   System.out.println("Semantically Incorrect!");
    	   }
    	   System.out.print("\nDo you want to check another Expression [Y/N]: ");
           String anotherCheck = sc.next();
                          
           if (!anotherCheck.equalsIgnoreCase("y")) {
           	displayprogrammer();
               System.exit(0);
           }
           System.out.println();
           
       } while (true);
    }
    
    
    public static boolean analyzeSemantic(ArrayList<String> lexemes) {
        String[] tokens = tokenize(lexemes).toArray(new String[0]);

        if (parse(tokens)){
            if (tokens.length == 3) return true; 
            try {
                String type = lexemes.get(0);
                String value = lexemes.get(3);

                if (type.equals("int")) {
                    Integer.parseInt(value);
                    return true;
                } else if (type.equals("double") && value.contains(".") && !value.contains("f")) {
                    Double.parseDouble(value);
                    return true;
                } else if (type.equals("String") && value.contains("\"")) {
                    return true;
                } else if (type.equals("boolean") && (value.equals("true")
                        || value.equals("false"))) {
                    return true;
                } else if (type.equals("float") && value.contains(".") && !value.contains("d")) {
                    Float.parseFloat(value);
                    return true;
                } else {
                    return (type.equals("char") && value.contains("'")
                            && value.length() == 3);
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

   
    public static ArrayList<String> tokenize(ArrayList<String> lexemes) {
        ArrayList<String> dataTypes = new ArrayList<>(
                Arrays.asList("int", "double", "char", "String", "float", "boolean")),
                tokens = new ArrayList<>();

        for (String lexeme : lexemes) {
            if (dataTypes.contains(lexeme)) {
                tokens.add("<data_type>");
            } else if (lexeme.contains("=")) {
                tokens.add("<assignment_operator>");
            } else if (lexeme.contains("\"") || lexeme.contains("'") ||
                    Character.isDigit(lexeme.charAt(0)) || lexeme.contains(".") ||
                    lexeme.equals("true") || lexeme.equals("false")) {
                tokens.add("<value>");
            } else if (lexeme.contains(";")) {
                tokens.add("<delimiter>");
            } else {
                tokens.add("<identifier>");
            }
        }
        return tokens;
    }

    public static ArrayList<String> lex(String input) {
        String[] individualChars = input.split("");

        ArrayList<String> lexemes = new ArrayList<>();

        StringBuilder temp = new StringBuilder(),
                quotedString = new StringBuilder();

        boolean isQuote = false;

        for (String c : individualChars) {
            if (c.equals("=") && !isQuote) {
                lexemes.add(temp.toString());
                lexemes.add(c);
                temp = new StringBuilder();
            } else if (c.equals(";") && !isQuote) {
                lexemes.add(temp.toString());
                lexemes.add(c);
                temp = new StringBuilder();
            } else if (c.equals(" ") && !isQuote) {
                lexemes.add(temp.toString());
                temp = new StringBuilder();
            } else if (c.equals("\"")) {
                quotedString.append(c);
                if (isQuote) {
                    lexemes.add(temp.toString());
                    temp = new StringBuilder();
                    lexemes.add(quotedString.toString());
                    quotedString = new StringBuilder();
                    isQuote = false;
                } else {
                    isQuote = true;
                }
            } else if (isQuote) {
                quotedString.append(c);
            } else {
                temp.append(c);
            }
        }
        lexemes.add(temp.toString());
        lexemes.removeIf(n -> (n.equals("")));
        return lexemes;
    }

    
    public static boolean parse(String[] tokens) {
        String[][] correctSyntax = {{"<data_type>", "<identifier>",
                "<assignment_operator>", "<value>", "<delimiter>"},
                {"<data_type>", "<identifier>", "<delimiter>"}};

        boolean state = false;

        if (tokens.length > correctSyntax[0].length) return false;

        for (String[] syntax : correctSyntax) {
            for (int j = 0; j < syntax.length; j++) {
                try {
                    state = tokens[j].equals(syntax[j]);
                    if (!state) break;
                } catch (Exception e) {
                    state = false;
                }
            }
            if (state) {
                break;
            }
        }
        return state;
    }
    

    public static void displayprogrammer() {
    	System.out.println("\n\t\t-----------> Program Terminated <--------------");
		System.out.println("# =========================================================================== #");		
		System.out.println("|                   2023 @ Sharon Grace T. Hangaan                            |");
		System.out.println("|             Follow me in: https://github.com/SG-Hangaan                     |");
		System.out.println("|                  Email: sharonygracehangaan@gmail.com                       |");
		System.out.println("# =========================================================================== #");	
        System.exit(0);
    }
    
    
    public static void header() {
    	System.out.println("\n\t(================= SEMANTIC ANALYSIS ==================)");
		System.out.println("\t(========== Laboratory005 - Semantic Analyzer ==========)");
		System.out.println("\t---------------------------------------------------------");
    }  
    
    
    
    
    
}
