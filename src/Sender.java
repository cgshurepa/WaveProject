import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

//when receives packet info
	//create packet, 
	//send packet on group

public class Sender {

	private WaveManager waveManager;
	private MulticastSocket sendingProcess;
	private BreakService breakService;
	
	//service.getVariable();
	
	//int channel = service.getChannel();
	//int serviceNum = service.getServiceNum();
	//int direction = service.getDirection();
	//lights turnSignals  breaks  eBreak emergency  vType speed

	public Sender(WaveManager waveManager, BreakService breakService){
		this.waveManager=waveManager;
		this.breakService=breakService;
		try{
			
			sendingProcess = new MulticastSocket();
			//listener = new MulticastSocket(waveManager.port);			
			//listener.joinGroup(InetAddress.getByName(waveManager.controlGroup));
			//currentGroup = waveManager.controlGroup;
		}catch(Exception e){
			
		}
	}
	
	//Class Methods
	public void switchGroups(String group){
		try{		
			listener.joinGroup(InetAddress.getByName(group));
			currentGroup = group;
			System.out.println("Switched to group "+currentGroup);
			
		}catch(Exception e){
			
		}
		
	public void sendMessage(String type, String serviceGroup, String message){
		try{
			//Preparing packet envelope
			InetAddress InetDestination = InetAddress.getByName(serviceGroup);
			DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetDestination, waveManager.port);
			
			//Send packet
			sendingProcess.send(packet);
			
			String output = "Sent "+type+" message to "+serviceGroup+": "+message;
			System.out.println(output);
		}catch(Exception e){
			
		}
	}
	}