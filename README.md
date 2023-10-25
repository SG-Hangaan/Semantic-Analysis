# Semantic-Analysis




The Semantic Analysis makes sure that declarations and statements of program are semantically correct. This Semantic Analyzer program will accept a single line assignment statement written in Java.

Examples:
1) int x = 1;
2) String str = “Hello World”;
3) double number;

Sample input/output 1: <br>
Enter Expression: int x = 1; <br>
Semantically  Correct! <br>
 
Sample input/output 2: <br>
Enter Expression: int x = 2.0; <br>
Semantically  Incorrect! <br>

# Implementation of Semantic Analysis - Semantic Analyzer

Once the parser validates the syntax, the lexemes are forwarded to the semantic analyzer. This critical phase ensures the declarations and statements carry correct meaning or are semantically accurate. The semantic analyzer specifically focuses on variable assignments, verifying that assigned values align accurately with their specified data types. For instance, int x = 1; is considered correct, while int x = 1.0; is incorrect. A try block is employed to validate if assigned values match their intended data types, enhancing the program's accuracy and reliability.

<p align="center">
  <img src="https://github.com/SG-Hangaan/Semantic-Analysis/assets/127215110/d9b0b23e-9186-4359-8f19-16f094652a1c"/>
</p>


