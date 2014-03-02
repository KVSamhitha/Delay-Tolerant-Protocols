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
	static int nodeStart=0;
	static int nodeEnd=0;
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
		nodeStart=a;
		nodeEnd=b;
		int size=b+1;
		Global.prob=new double[size][size];
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				
				prob[i][j]=init;
				
			}
		}
//		System.out.println("Initialized Probabilities");
	}
	
	public static void updateTime(int t)
	{
		int diffTime=t-lastTime;
		lastTime=t;
		for(int i=nodeStart;i<=nodeEnd;i++)
		{
			for(int j=nodeStart;j<=nodeEnd;j++)
			{
				prob[i][j]=prob[i][j]*Math.pow(lambda, diffTime);
			}
		}
//		System.out.println("Updated time");
		
	}
	
	public static void updateProb(int send, int receive)
	{
		prob[send][receive]=prob[send][receive]+(1-prob[send][receive])*init;
		prob[receive][send]=prob[receive][send]+(1-prob[receive][send])*init;
		
		for(int i=nodeStart;i<=nodeEnd;i++)
		{
			if(i==send || i== receive)
				continue;
			else
			{
				double d=prob[send][i]+(1-prob[send][i])*prob[send][receive]*prob[receive][i]*beta;
				prob[send][i]=d;
				prob[i][send]=d;
				
				d=prob[receive][i]+(1-prob[receive][i])*prob[send][receive]*prob[send][i]*beta;
				prob[receive][i]=d;
				prob[i][receive]=d;
			}
		}
	}
	
	public static void printProb(int a, int b)
	{
		System.out.println("Probability Matrix for "+a);
		
		for(int i=nodeStart;i<=nodeEnd;i++)
			System.out.print(i+"->"+prob[a][i]+",");
		
		System.out.println();
		
		System.out.println("Probability Matrix for "+b);
		
		for(int i=nodeStart;i<=nodeEnd;i++)
			System.out.print(i+"->"+prob[b][i]+",");
	}
}


