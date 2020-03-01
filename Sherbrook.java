import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import DEMSApp.Manager;
import DEMSApp.ManagerHelper;

public class Sherbrook {

	public static HashMap<String, String> eventList = new HashMap<String, String>();
	public static HashMap<String, Integer> a = new HashMap<String, Integer>();
	public static HashMap<String, Integer> b = new HashMap<String, Integer>();
	public static HashMap<String, Integer> c = new HashMap<String, Integer>();
	public static HashMap<String, String> Muser = new HashMap<String, String>();

	public static void main(String[] args) {

		// get reference to rootpoa &amp; activate

		try {
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			DemsImplementation dem = new DemsImplementation();
			dem.setORB(orb);

			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(dem);

			Manager href = ManagerHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent path[] = ncRef.to_name("SHE");
			ncRef.rebind(path, href);

			eventList.put("Conference", "a");
			eventList.put("TradeShow", "b");
			eventList.put("Seminar", "c");

			System.out.println("Sherbrooke Server ready and waiting ...");

			// wait for invocations from clients
			for (;;) {
				orb.run();
			}

		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdapterInactive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServantNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized String getHashMap(String eventType) {
		// it sends a b or c depending on input
		String value = eventList.get(eventType);

		return value;
	}

	public synchronized String addHashMap(String var, String key, int Value) {
		if (var == "a") {
			// var=eventType sub_hashmap , key=eventID Value=booking Capacity
			if (a.get(key) != null) {

				int val = a.get(key);
				a.replace(key, val + Value);
				return ("Value updated for " + key + "to " + val);
			} else {
				a.put(key, Value);
				return ("Added Successfully " + key + "to " + a.get(key));

			}
		} else if (var == "b") {
			if (b.get(key) != null) {

				int val = b.get(key);
				b.replace(key, val + Value);
				return ("Value updated for " + key + "to " + val);
			} else {
				b.put(key, Value);
				return ("Added Successfully " + key + "to " + b.get(key));
			}
		} else if (var == "c") {
			if (c.get(key) != null) {

				int val = c.get(key);
				c.replace(key, val + Value);
				return ("Value updated for " + key + "to " + val);
			} else {
				c.put(key, Value);
				return ("Added Successfully " + key + "to " + a.get(key));
			}
		} else
			return ("Not Successfull ");

	}

	public synchronized String removeHashMap(String var, String key) {
		//kye=event id
		if (var == "a") {
			if (a.get(key) != null) {
				String key1=new String(key);
				StringBuffer remover=new StringBuffer("Removed "); 
				if (Muser.containsKey(key) ){
					remover.append("Booking found for customer "+Muser.get(key));
					char[] c = key.toCharArray();
					String str=Character.toString(c[4]);
					String str2=Character.toString(c[5]);
					String dat = str+str2;
					
					int i = Integer.parseInt(String.valueOf(dat));
					for (; i < 30; i++) {
						char[] c1 = key1.toCharArray();
										if(c1[3]=='M'){
											key1 = key.substring(0, 3)+"A" + key1.substring(4);
											if (a.containsKey(key1) && a.get(key1) != 0 ){
												Muser.put(key1, Muser.get(key));
												Muser.remove(key);
												remover.append(". "+"Booking got changed to "+key1);
												break;
												}
											} c1 = key1.toCharArray();
										if(c1[3]=='A'){
											key1 = key.substring(0, 3)+"E" + key1.substring(4);
											if (a.containsKey(key1) && a.get(key1) != 0 ){
												Muser.put(key1, Muser.get(key));
												Muser.remove(key);
												remover.append(". "+"Booking got changed to "+key1);
												break;
											}c1 = key1.toCharArray();
										}
										if (i+1 < 10)
													key1 = key1.substring(0, 4) + "0"+ Integer.toString(i+1) + key1.substring(6);
										else
													key1 = key1.substring(0, 4) + Integer.toString(i+1)+ key1.substring(6);
										c1 = key1.toCharArray();
										if(c1[3]=='E'){
													key1 = key.substring(0, 3)+"M" + key1.substring(4);
													if (a.containsKey(key1) && a.get(key1) != 0 ){
														Muser.put(key1, Muser.get(key));
														Muser.remove(key);
														remover.append(". "+"Booking got changed to "+key1);
														break;
													} }c1 = key1.toCharArray();
					}
					
				}
				a.remove(key);
				return(key + " ." +remover.toString());
			} else {

				return("No record");
			}
		} else if (var == "b") {
			if (b.get(key) != null) {
				String key1=new String(key);
				StringBuffer remover=new StringBuffer("Removed "); 
				if (Muser.containsKey(key) ){
					remover.append("Booking found for customer "+Muser.get(key));
					char[] c = key.toCharArray();
					String str=Character.toString(c[4]);
					String str2=Character.toString(c[5]);
					String dat = str+str2;
					
					int i = Integer.parseInt(String.valueOf(dat));
					for (; i < 30; i++) {
						char[] c1 = key1.toCharArray();
										if(c1[3]=='M'){
											key1 = key.substring(0, 3)+"A" + key1.substring(4);
											if (b.containsKey(key1) && b.get(key1) != 0 ){
												Muser.put(key1, Muser.get(key));
												Muser.remove(key);
												remover.append(". "+"Booking got changed to "+key1);
												break;
												}
											} c1 = key1.toCharArray();
										if(c1[3]=='A'){
											key1 = key.substring(0, 3)+"E" + key1.substring(4);
											if (b.containsKey(key1) && b.get(key1) != 0 ){
												Muser.put(key1, Muser.get(key));
												Muser.remove(key);
												remover.append(". "+"Booking got changed to "+key1);
												break;
											}c1 = key1.toCharArray();
										}
										if (i+1 < 10)
													key1 = key1.substring(0, 4) + "0"+ Integer.toString(i+1) + key1.substring(6);
										else
													key1 = key1.substring(0, 4) + Integer.toString(i+1)+ key1.substring(6);
										c1 = key1.toCharArray();
										if(c1[3]=='E'){
													key1 = key.substring(0, 3)+"M" + key1.substring(4);
													if (b.containsKey(key1) && b.get(key1) != 0 ){
														Muser.put(key1, Muser.get(key));
														Muser.remove(key);
														remover.append(". "+"Booking got changed to "+key1);
														break;
													} }c1 = key1.toCharArray();
					}
					
				}
				b.remove(key);
				return(key + " ." +remover.toString());
			} else {

				return("No record");
			}
		} else if (var == "c") {
			if (c.get(key) != null) {
				String key1=new String(key);
				StringBuffer remover=new StringBuffer("Removed "); 
				if (Muser.containsKey(key) ){
					remover.append("Booking found for customer "+Muser.get(key));
					char[] ch = key.toCharArray();
					String str=Character.toString(ch[4]);
					String str2=Character.toString(ch[5]);
					String dat = str+str2;
					
					int i = Integer.parseInt(String.valueOf(dat));
					for (; i < 30; i++) {
						char[] c1 = key1.toCharArray();
										if(c1[3]=='M'){
											key1 = key.substring(0, 3)+"A" + key1.substring(4);
											if (c.containsKey(key1) && c.get(key1) != 0 ){
												Muser.put(key1, Muser.get(key));
												Muser.remove(key);
												remover.append(". "+"Booking got changed to "+key1);
												break;
												}
											} c1 = key1.toCharArray();
										if(c1[3]=='A'){
											key1 = key.substring(0, 3)+"E" + key1.substring(4);
											if (c.containsKey(key1) && c.get(key1) != 0 ){
												Muser.put(key1, Muser.get(key));
												Muser.remove(key);
												remover.append(". "+"Booking got changed to "+key1);
												break;
											}c1 = key1.toCharArray();
										}
										if (i+1 < 10)
													key1 = key1.substring(0, 4) + "0"+ Integer.toString(i+1) + key1.substring(6);
										else
													key1 = key1.substring(0, 4) + Integer.toString(i+1)+ key1.substring(6);
										c1 = key1.toCharArray();
										if(c1[3]=='E'){
													key1 = key.substring(0, 3)+"M" + key1.substring(4);
													if (c.containsKey(key1) && c.get(key1) != 0 ){
														Muser.put(key1, Muser.get(key));
														Muser.remove(key);
														remover.append(". "+"Booking got changed to "+key1);
														break;
													} }c1 = key1.toCharArray();
					}
					
				}
				c.remove(key);
				return(key + " ." +remover.toString());
			} else {

				return("No record");
			}
		}
		return null;
		
	}

	public synchronized HashMap<String, Integer> display(String evenType) {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String value = eventList.get(evenType);
		System.out.println("List for event type " + evenType);
		String ss = " ";
		if (value.equalsIgnoreCase("a")) {
			a.entrySet().forEach(entry -> {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				// ss=ss+entry.getKey();
					temp.put(entry.getKey(), entry.getValue());
				});
			return temp;
		} else if (value.equalsIgnoreCase("b")) {
			b.entrySet().forEach(entry -> {
				temp.put(entry.getKey(), entry.getValue());
			});
			return temp;
		} else if (value.equalsIgnoreCase("c")) {
			c.entrySet().forEach(entry -> {
				temp.put(entry.getKey(), entry.getValue());
			});
			return temp;
		}
		return null;

	}

	public synchronized String checkAvailabilityOfEvent(String var, String key) {
		// key is event id

		if (var == "a") {
			if (a.containsKey(key) && a.get(key) != 0) {
				return ("Available ");
			} else {

				return ("Not");
			}
		} else if (var == "b") {
			if (b.containsKey(key) && b.get(key) != 0) {
				return ("Available ");
			} else {

				return ("Not");
			}
		} else if (var == "c") {
			if (c.containsKey(key) && c.get(key) != 0) {
				return ("Available ");
			} else {

				return ("Not");
			}
		}
		return null;
	}

	public synchronized String bookedEvent(String eventID, String customerID) {
		// TODO Auto-generated method stub
		Muser.put(eventID, customerID);
		String s = "booked event " + eventID + " for " + customerID;
		return s;
	}

	public synchronized String canceledEvent(String eventID, String customerID) {
		// TODO Auto-generated method stub

		Muser.remove(eventID);
		String s = "cancelled event " + eventID + " for " + customerID;
		return s;
	}

	public synchronized boolean checkUserBooking(String eventID,
			String customerID) {
		// TODO Auto-generated method stub
		Muser.put(eventID, customerID);
		if (Muser.containsKey(eventID)
				&& Muser.get(eventID).equalsIgnoreCase(customerID)) {
			return true;
		} else
			return false;
	}

	public synchronized HashMap<String, String> getUserData(String customerID) {
		HashMap<String, String> temp11 = new HashMap<String, String>();

		Muser.entrySet().forEach(entry -> {
			if (customerID.equalsIgnoreCase(entry.getValue()))
				temp11.put(entry.getKey(), entry.getValue());
		});
		return temp11;

	}
	public synchronized void UDPConnect(int serverPort, String combinedId) {
		DatagramSocket aSocket = null;
		try {
			System.out.println("Sherbrooke client started");
			aSocket = new DatagramSocket();
			byte[] message = combinedId.getBytes();

			InetAddress aHost = InetAddress.getByName("localhost");

			// int serverPort = this.;
			// DatagramPacket request =new DatagramPacket(m, args[0].length(),
			// aHost, serverPort);
			DatagramPacket request = new DatagramPacket(message,
					combinedId.length(), aHost, serverPort);

			aSocket.send(request);
			System.out.println("Request message sent via UDP");

			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}

}