package process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Preprocess {

	String fname=new String("testContacts.csv");
	String msgFile=new String("msgfinal.csv");
	//Global g=new Global();
	
	//int maxMessage=0;
	public void readMsgFile(ArrayList<Node> nodes)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(msgFile));
			String str=new String();
			int id=0;
			int src=0;
			int dest=0;
			int newTime=0;
			Message m=null;
			while ((str = br.readLine()) != null  ) 
			{
				id++;
				String parts[]=str.split(",");
				src=Integer.parseInt(parts[0]);
				dest=Integer.parseInt(parts[1]);
				
				
				
				m=new Message(id, src, dest, Global.hopcount, 10);				
	
				for(Node n:nodes)
				{
					if(n.id==src)
					{	
						n.addMessage(m);
						n.sourceFor++;
					}
					if(n.id==dest)
					{	
						n.destFor++;
					}
				}
				//System.out.println("Source is "+src+"Dest: "+dest);
				//break;
			}
			Global.maxMessages=id;
			
		}
		catch(Exception e)
		{
			System.out.println("File1 exception "+e.toString());
		}
	}
	

	
	public void readContactsFile(ArrayList<Node> nodes)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String str=new String();
			int newTime=0;
			int i=0;
			while ((str = br.readLine()) != null && i<=Global.maxRead) 
			{
				i++;
				String parts[]=str.split(",");
				int src=Integer.parseInt(parts[0]);
				int dest=Integer.parseInt(parts[1]);
				newTime=Integer.parseInt(parts[2]);
				
				Node sender=Node.findNode(nodes, src);
				Node receiver=Node.findNode(nodes, dest);
				
				
				sender.exchangeMessages(receiver);
				//receiver.printAllMessages();
				receiver.exchangeMessages(sender);
				//sender.printAllMessages();
				
				
				//break;
			}
		}
		catch(Exception e)
		{
			System.out.println("File exception2 "+e.toString());
		}
	}

}
