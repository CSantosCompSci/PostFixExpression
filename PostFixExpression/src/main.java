//Christopher Santos
//Assignment 1


import java.util.Scanner;
import java.lang.Double;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;



class PostFixExpression {
	static boolean divisionByZero = false;
	static boolean invalidChar = false;
	public static void main(String[]args)
	{
		double answer;
		Scanner sc = null;
		try {
			sc = new Scanner(new File("in.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello! This is a postfix expression calculator.");
		while(sc.hasNextLine()){
			
			String expression = sc.nextLine();
			answer = postFixEvaluate(expression);
			if ( divisionByZero){
				System.out.println("The expression " + expression + " contains division by zero");
				divisionByZero = false;
			}else if(invalidChar){
				System.out.println("The expression " + expression + " contain an invalid char");
				invalidChar = false;
			}else
			System.out.println("The value of " + expression + " is " + answer);
		}
		System.out.println("Bye Bye!!!");
		
		sc.close();
	}

		public static double postFixEvaluate(String expression){
				
			Stack<Double> operands = new Stack<Double>();
			Scanner reader = new Scanner(expression);
			double number1 = 0;
			double number2 = 0;
			String op;
			while(reader.hasNext())
			{
		
				if(reader.hasNextDouble()){
					op = reader.next();	
					operands.push(new Double(op));	
				}
				else{	
				 op = reader.next();
				 try{
						 switch( op)
						 {
						case"+":
							number2 = operands.pop();
							number1 = operands.pop();
							operands.push(number1 + number2);
							break;
						case"-":
							number2 = operands.pop();
							number1 = operands.pop();
							operands.push(number1 - number2);
							break;
						case"/":
							number2 = operands.pop();
							if(number2 == 0){
								divisionByZero = true;
								return 0;
							}
							else
							number1 = operands.pop();
							operands.push(number1 / number2);
							break;
						case"*":
							number2 = operands.pop();
							number1 = operands.pop();
							operands.push(number1 * number2);
							break;
						case"_":
							number1 = operands.pop();
							operands.push(number1 * -1);
							break;
						case"^":
							number2 = operands.pop();
							number1 = operands.pop();
							double exponent = 1;
							for(double i = 1; i <= number2; i ++)
							{
								exponent =  exponent * number1;
							}
							operands.push(exponent);
							break;
						case"#":
							number1 = operands.pop();
											
							operands.push(Math.sqrt(number1));
							break;
						default:
							throw new NumberFormatException();
							
							
					}
				
				 }
					catch(NumberFormatException e){
						
						invalidChar = true;
						return 0;
					}
					
			}
		}
			reader.close();
			return operands.pop();
		}
}
	
