import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/*Author: Scott Goldweber
 * Link the text based on the start and end (>=3 but <=8 characters) of each text piece. For
example, text 1 (AAABBBCCC) can be linked with text 2 (XXXYYYAAA) to become the
bigger text string XXXYYYAAABBBCCC, and in turn linked with other text. 
 */
public class Link_Text {

	
	public static void main(String[] args) throws IOException 
	{
		System.out.print("Author: Scott Goldweber ");
		Scanner user_input= new Scanner (System.in);
		System.out.print("Please enter source file ");
		String infile=user_input.nextLine();
		
		Scanner user_inputOut= new Scanner (System.in);
		System.out.print("Please filename to print to ");
		String outfile=user_inputOut.nextLine();
		
		ArrayList<Sequence> seqs = ParseFile(infile);
		ArrayList<String> Order= new ArrayList<String>(); 
		Join(seqs,Order,"");
		
		PrintWriter writer = new PrintWriter(outfile, "UTF-8");
		Print(Order,0,"",writer);
		writer.close();
		System.out.println("Complete");
		

	}
	
	public static void Join(ArrayList<Sequence> seqs,ArrayList<String> Order,String Concat)
	{
		Sequence i=new Sequence("");
		if(seqs.size()>0)
		{
			if(Concat=="")
			{
				Concat=seqs.get(0).getSequence();
				Order.add(seqs.get(0).toString());
				seqs.remove(0);

			}
			for (Sequence s : seqs)
			{
				 int joinstart=0; 
				 int joinback=0; 
				 int length=8;
				 if(s.getSequence().length()<8)
				 {
					 length=s.getSequence().length();
				 }
				for(int x=3;x<length;x++) 
				{
				  String start=s.getSequence().substring(0,x);
				  String end=s.getSequence().substring(Math.max(0, s.getSequence().length() - x));				  
				  String Seqstart=Concat.substring(0,x);
				  String Seqend=Concat.substring(Concat.length()-x);
				  if(Seqstart.equals(end))
				  {
					  joinstart=x;
				  }
				  else if(Seqend.equals(start))
				  {
					  joinback=x;
				  }
				}
				if(joinstart>0)
				{
					Concat=s.getSequence().substring(0,s.getSequence().length()-joinstart)+Concat;
					i=s;
					break;
				}
				else if(joinback>0)
				{
					Concat=Concat.substring(0,Concat.length()-joinback)+s.getSequence();
					i=s;
					break;
				}
			}
			Order.add(i.toString());
			seqs.remove(i);
			Join(seqs,Order,Concat);
		}
		
		
		
	}
	
	public static ArrayList<Sequence> ParseFile (String file) throws IOException
	{
		Scanner scanner = new Scanner(new File(file));
        ArrayList<Sequence> data = new ArrayList<Sequence>();
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) 
        {
        	Sequence seq= new Sequence(scanner.toString());
        	String[] values = scanner.nextLine().split(",");
            // Add grabbed data to ArrayList
        	seq.setNumber(values[0]);
        	seq.setName(values[1]);
        	String dna=values[2].replaceAll("\\s+","");
        	seq.setSequence(dna);
        	data.add(seq);

        }
        scanner.close();
		return data;
		
	}
	
	static void Print(ArrayList<String> List,int line,String tabs, PrintWriter writer)
	{
		
		if(line<List.size())
		{
			writer.println(tabs+List.get(line));
			tabs+="\t";
			line++;
			Print(List,line,tabs,writer);
			
		}
	}

}
