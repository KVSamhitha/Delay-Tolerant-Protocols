package process;

public class Message {
	
	int id;
	int src;
	int dest;
	int hopcount;
	
	
	public Message(int i, int sr, int des, int hc)
	{
		id=i;
		src=sr;
		dest=des;
		hopcount=hc;
	}
	public Message(Message m)
	{
		this.id=m.id;
		this.src=m.src;
		this.dest=m.dest;
		this.hopcount=m.hopcount-1;
	}
	public void printMessage()
	{
		System.out.println("Id "+id+" Source "+src+" Destination:"+dest+" Hop:"+hopcount);
	}
	

}
