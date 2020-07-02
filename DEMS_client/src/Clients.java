import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import DEMSApp.Manager;
import DEMSApp.ManagerHelper;


public class Clients {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		try {
			ORB orb = ORB.init(args, null);
			//-ORBInitialPort 1050 -ORBInitialHost localhost
			org.omg.CORBA.Object objRef;
			objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			
			System.out.println("Client Started");
			while(true) {
			System.out.print("Enter your id ");
			Scanner sc = new Scanner(System.in);
			
			//ID to upper case
			String id = sc.nextLine().toUpperCase();
			/*String location="G:\\workspace\\6231_project\\src\\logger\\clientlog\\"+id+".txt";;
			PrintStream o=new PrintStream(new File(location));
			System.setOut(o);*/
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    System.out.println(formatter.format(date)); 
			char[] ch = id.toCharArray();
			char[] ch2 = { ch[0], ch[1], ch[2] };
			String bookingServ = new String(ch2);
			
			Manager dem=(Manager) ManagerHelper.narrow(ncRef.resolve_str(bookingServ));
			
			
			System.out.println("What would you like to do today?(Select 1,2,3)");
			if(ch[3] == 'M'){
				System.out.println("1.addEvent"+"\n"+
						"2.removeEvent"+"\n"+
						"3.listEventAvailability"+"\n"+
						"4.bookEvent"+"\n"+
						"5.getBookingSchedule"+"\n"+
						"6.cancelEvent"+"\n"+
						"7.SwapEvent"+"\n"+
						"8.Exit");
			}
			else if (ch[3] == 'C') {
				System.out.println("4.bookEvent"+"\n"+
						"5.getBookingSchedule"+"\n"+
						"6.cancelEvent"+"\n"+
						"7.SwapEvent"+"\n"+
						"8.Exit");
			}
			/*BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			int m=br.read();*/
			Scanner br=new Scanner(System.in);
			int m=br.nextInt();
			
			if(m==1){
				System.out.println("Enter eventID(eg MTLM010120), eventType(Conference,TradeShow,Seminar) bookingCapacity in the same order");
				Scanner scin=new Scanner(System.in);
				String add=dem.addEvent(scin.next(), scin.next(), scin.nextInt(),bookingServ);
				System.out.println(add);
			}
			else if(m==2){
				System.out.println("Enter eventID(eg MTLM010120), eventType(Conference,TradeShow,Seminar) in the same order");
				Scanner scin=new Scanner(System.in);
				String add=dem.removeEvent(scin.next(), scin.next(),bookingServ);
				System.out.println(add);
			}
			else if(m==3){
				System.out.println("Enter  eventType(Conference,TradeShow,Seminar) ");
				Scanner scin=new Scanner(System.in);
				String add=dem.listEventAvailability(scin.next(),bookingServ);
				System.out.println(add);
			}else if(m==4){
				System.out.println("Enter customerID(eg MTLC0001), eventID(eg MTLM010120), eventType(Conference,TradeShow,Seminar) in the same order");
				Scanner scin=new Scanner(System.in);
				String add=dem.bookEvent(scin.next(), scin.next(), scin.next(),bookingServ);
				System.out.println(add);
			}
			else if(m==5){
				System.out.println("Enter customerID");
				Scanner scin=new Scanner(System.in);
				String add=dem.getBookingSchedule(scin.next(),bookingServ);
				System.out.println(add);
								
			}
			else if(m==6){
				System.out.println("Enter customerID, eventID, eventType(Conference,TradeShow,Seminar) in the same order");
				Scanner scin=new Scanner(System.in);
				String add=dem.cancelEvent(scin.next(), scin.next(), scin.next(),bookingServ);
				System.out.println(add);
			}
			else if(m==7){
//				swapEvent(String customerID, String newEventID,String newEventType, String oldEventID, String oldEventType,
				
				System.out.println("Enter customerID, new eventID,new eventType(Conference,TradeShow,Seminar),old eventId, old eventType in the same order");
				
				Scanner scin=new Scanner(System.in);
				String add=dem.swapEvent(scin.next(), scin.next(), scin.next(),scin.next(),scin.next(),bookingServ);
				System.out.println(add);
			}
			else{
				System.out.println("Bye");
				System.exit(0);
			}
			
		}
			
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
