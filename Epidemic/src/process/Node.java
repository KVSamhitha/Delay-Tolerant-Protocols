package process;

import java.util.ArrayList;

public class Node {

	int id;
	int bufferSize;
	ArrayList <Message> msg;
	double msgsReceived=0;
	double msgsSent=0;
	int sourceFor=0;
	int destFor=0;
	public Node(int i, int buff)
	{
		id=i;
		bufferSize=buff;
		msg=new ArrayList<Message>(bufferSize); 
	}
	
	
	public static ArrayList<Node> createNodes(int start, int end, int buffer)
	{
		ArrayList<Node> nodes=new ArrayList<Node>(end-start+1);
		for(int i=start;i<=end;i++)
		{
			Node n=new Node(i, buffer);
			nodes.add(n);
		}
		return nodes;
	}
	public static Node findNode(ArrayList<Node> nodes, int src)
	{
		Node ret=null;
		for(Node n:nodes)
		{
			if(n.id==src)
				ret=n;
		}
		
		return ret;
	}
	
	public void addMessage(Message m)
	{
		if(m.dest==this.id && !Global.isMessagePresent(m.id))
		{	
			
			//System.out.println("Adding message to success list");
			Global.success.add(m);
			this.msgsReceived++;
			return;
		}

		if(this.msg.size()==this.bufferSize || m.hopcount==0) 
		{
			
			return;		
		}
		else 
		{
			if(!isPresent(m))
			{
				Message n=new Message(m);
				msg.add(n);
			}	
		}
	}
	public boolean isPresent(Message m)
	{
		for(Message ms:msg)
			if(ms.id==m.id)
				return true;
		return false;
	}
	public void exchangeMessages(Node b)
	{
		//System.out.println("Node "+this.id+"Exchanging Message with node "+b.id);
		//this.printAllMessages();
		for(Message m:msg)
		{
			this.msgsSent++;
			b.addMessage(m);
			
		}
		//this.printAllMessages();
	}

	
	public void printAllMessages()
	{
		if(this.id>29)
			return;
		System.out.println("Printing message for Node "+this.id);
		for(Message m:msg)
		{
			m.printMessage();
		}
	}
	
}
