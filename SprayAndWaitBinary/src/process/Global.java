package process;

import java.util.ArrayList;

public class Global 
{
	
	public static double threshold=0.5;
	public static double init=0.75;
	public static double lambda=0.98;
	public static double beta=0.25;
	public static double totalReceived=0;
	public static int hopcount=6;
	public static int maxMessages;
	static ArrayList<Message> success;
	static double prob[][];
	static int lastTime=6207;
	static int maxRead=5000;
	
	public static ArrayList<Message> searchSuccessList(int id)
	{
		ArrayList<Message> m=new ArrayList<Message>();
		for(Message ms:Global.success)
		{
			if(ms.dest==id)
				m.add(new Message(ms));
		}
		return m;
	}
	
	public static boolean isMessagePresent(int id)
	{
		boolean ret=false;
		for(Message ms:Global.success)
		{
			if(ms.id==id)
			{	
				ret=true;
				break;
			}
		}
		return ret;
	}
	public static  void initializeProb(int a, int b)
	{
		int size=b+1;
		Global.prob=new double[size][size];
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				prob[i][j]=init;
				
			}
		}
	}
}


