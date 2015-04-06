
public class Sequence {

	private String Number;
	private String Name;
	private String Sequence;
	private String line;


	
	public Sequence(String line)
	{
		this.line=line;
	}
	public void setNumber(String Number)
	{
		this.Number=Number;
	}
	
	public String getNumber()
	{
		return Number;
	}
	
	public void setName(String Name)
	{
		this.Name=Name;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public void setSequence(String Sequence)
	{
		this.Sequence=Sequence;
	}
	
	public String getSequence()
	{
		return Sequence;
	}
	
	public String toString() 
	{
		     return "("+Number+"-"+Name+")"+Sequence;
		   
	}

}
