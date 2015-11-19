import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class WAVEPacketMaker {
	
	
	public static void main(String[] args) throws IOException {
	
	//Ex. 	Channel 4, public service 3, north, high beam lights (10), no turn, no break,
	//		no e break, E: fuel low (001), civilian vehicle, 90km/h (01011010)

	boolean[] channel = intToBitsAndCut(4,3);		//value 4 in 3 bits
	//System.out.println("Output: " + channel[0] + channel[1] + channel[2]);
	boolean[] service = intToBitsAndCut(3,5);		//5
	boolean[] direction = intToBitsAndCut(0,4);		//4
	boolean[] lights = intToBitsAndCut(3,2);		//2
	boolean[] turnSignals = intToBitsAndCut(0,2);	//2
	boolean[] breaks = intToBitsAndCut(0,1);		//1
	boolean[] eBreak = intToBitsAndCut(0,1);		//1
	boolean[] emergency = intToBitsAndCut(1,3);		//3
	boolean[] vType = intToBitsAndCut(0,3);			//3
	boolean[] speed = intToBitsAndCut(90,8);		//8

	List<Boolean> packet =new ArrayList<Boolean>();
	//Huge difference between Boolean and boolean!
	packet = appendPacket(packet, channel, 3);
	packet = appendPacket(packet, service, 5);
	packet = appendPacket(packet, direction, 4);
	packet = appendPacket(packet, lights, 2);
	packet = appendPacket(packet, turnSignals, 2);
	packet = appendPacket(packet, breaks, 1);
	packet = appendPacket(packet, eBreak, 1);
	packet = appendPacket(packet, emergency, 3);
	packet = appendPacket(packet, vType, 3);
	packet = appendPacket(packet, speed, 8);
	
	byte[] WSApacket = toByteArray(packet);
	
	System.out.println("Packet: " + packet + "/n");
	System.out.println("WSApacket: " + Arrays.toString(WSApacket));
}
	
	public static byte[] toByteArray(List<Boolean> packetAL) {
		boolean[] packet = new boolean[packetAL.size()];
		//Must use loop because were using boolean, not Boolean for 'packet'
		//normally, would use x = y.toArray();
		for (int i = 0; i < packetAL.size(); i++) {
			packet[i] = packetAL.get(i);
			}
		
	    byte[] WSApacket = new byte[packet.length / 8];
	    	for (int entry = 0; entry < WSApacket.length; entry++) {
	    		for (int bit = 0; bit < 8; bit++) {
	    			if (packet[entry * 8 + bit]) {
	    				WSApacket[entry] |= (128 >> bit);
	    			}
	    		}
	    	}
	    return WSApacket;
	} 
	
	public static boolean[] intToBitsAndCut(int n, int size){
		final boolean[] b = new boolean[size];
		    for (int i = 0; i < size; i++) {
		        b[size - 1 - i] = (1 << i & n) != 0;
		    	}
		    return b;
		}
	
	public static List<Boolean> appendPacket(List<Boolean> packet, boolean[] added, int size){
			for(int i = 0; i<size; i++){
				packet.add(added[i]);
				}
		return packet;
		}
}