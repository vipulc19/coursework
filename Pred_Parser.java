import java.util.*;
public class Pred_Parsern 
{
	static String M[][] = { {"TZ",null,null,null},
							{null,"+TZ",null,"e"},
							{"FY",null,null,null},
							{null,"e","*FY","e"},
							{"i",null,null,null}};
	
	static String nonterminal[] = {"E","Z","T","Y","F"};
	static String terminal[] = {"i","+","*","$"};
	static Stack<Character> st = new Stack<Character>();
	
	static int isTerminal(char ch) 
	{
		for(int i=0;i<4;i++)
			if(ch == terminal[i].charAt(0))
				return i;
		return -1;
	}
	static int isNonTerminal(char ch) 
	{
		for(int i=0;i<5;i++)
			if(ch == nonterminal[i].charAt(0))
				return i;
		return -1;
	}
	
	public static String printS()
	{
		String s="";
		for(int i=0;i<st.size();i++)
			s=s+st.elementAt(i);
		return s;
	}
	
	public static void main(String args[])    
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input string:");
		String s = sc.nextLine();
		s=s+"$";
		
		st.push('$');
		st.push('E');
		
		int r,c,i=0;
		System.out.println("Stack\tInput\tAction");
		
		while(st.peek()!='$')
		{
			c = isTerminal(s.charAt(i));
			System.out.print(printS()+"\t"+s.substring(i));
			if(c==-1)
				break;
			//System.out.println(r+"**"+c);
			if(c!=-1 && i<s.length()) //If terminal do exists.
			{

				if(s.charAt(i)==st.peek())
				{
					i++;
					st.pop();
					System.out.println("\tpop "+terminal[c]);
 				}
				else if(st.peek()=='e')
				{
					st.pop();
					System.out.println("\tnull production");
				}
				
				else  //To check for presence of terminal on top of stack.
				{
					r = isNonTerminal(st.peek());
					if(M[r][c]==null)
						break;
					System.out.println("\t"+st.peek()+"->"+M[r][c]);
					st.pop();
					//For a Production
					for(int j=M[r][c].length()-1;j>=0;j--) 
						st.push(M[r][c].charAt(j));
					
				}
			}
			System.out.println();
		}
		
		if((st.peek()=='$') && (i==s.length()-1))
			System.out.println("$\t$\t"+"String Accepted.");
		else
			System.out.println("\tString Rejected");
		sc.close();
	}
}	