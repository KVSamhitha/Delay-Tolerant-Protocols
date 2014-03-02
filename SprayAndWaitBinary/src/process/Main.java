package process;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int NodeStart=21;
		int NodeEnd=98;
		int BufferSize=100;
		
		for(int i=10;i<=BufferSize;i=i+10)
		{
			for(int j=5000;j<=30000;j=j+5000)
			{
				
			Global.maxRead=j;	
			System.out.println("Buffer Size "+i+"Contacts Read "+j);
			
			ArrayList<Node> nodes=new ArrayList<Node>();
			nodes=Node.createNodes(NodeStart, NodeEnd, i); //Replace I by buffersize initially
			Preprocess p=new Preprocess();
			p.readMsgFile(nodes);
			
			Global.initializeProb(NodeStart, NodeEnd);
			
			
			
//			Global.prob=new double[NodeEnd-NodeStart+1][NodeEnd-NodeStart+1];
			
			
			for(Node n:nodes)
			{
				//n.printAllMessages();
			}
			Global.success=new ArrayList<Message>(Global.maxMessages);
			p.readContactsFile(nodes);
	
			System.out.println(" Number of messages received total are  "+Global.success.size());
			
			int sum=0;
			for(Node n:nodes)
			{
				//if(n.msgsReceived>0)
				//	System.out.println("Number of messages Received by node "+n.id+" are"+n.msgsReceived);
				sum+=n.msgsSent;
			}
			System.out.println("Total Number of Messages Sent "+sum);
			}
		}	
		/*	ArrayList<Message> m =new ArrayList<Message>();
			m=Global.searchSuccessList(n.id);
			for(Message m1:m)
			{
				m1.printMessage();
			}*/
		//}
		
			
			
		/*for(Message m1:Global.success)
		{
			m1.printMessage();
		}*/
		
				
	}

}

