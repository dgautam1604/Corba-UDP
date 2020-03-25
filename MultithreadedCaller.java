import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import DEMSApp.Manager;
import DEMSApp.ManagerHelper;



public class MultithreadedCaller {

	public static void main(String[] args) {
		ORB orb = ORB.init(args, null);
		//-ORBInitialPort 1050 -ORBInitialHost localhost
		org.omg.CORBA.Object objRef;
		try {
			objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			Manager dem=(Manager) ManagerHelper.narrow(ncRef.resolve_str("MTL"));
			Manager dem1=(Manager) ManagerHelper.narrow(ncRef.resolve_str("QUE"));
			Manager dem2=(Manager) ManagerHelper.narrow(ncRef.resolve_str("SHE"));
			
			
			String customerID="MTLC0001";

			String customerID1="QUEC0002";

			String customerID2="SHEC0003";
			String eventID="MTLM020120";
			String eventType="Conference";
			
			
			
			new Thread() {
				@Override 
				public void run() {
					try {
						String add=dem.bookEvent(customerID, eventID, eventType,"MTL");
						System.out.println(add);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			new Thread() {
				@Override 
				public void run() {
					try {
						String add=dem1.bookEvent(customerID1, eventID, eventType,"QUE");
						System.out.println(add);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			
			new Thread() {
				@Override 
				public void run() {
					try {
						String add=dem2.bookEvent(customerID2, eventID, eventType,"SHE");
						System.out.println(add);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
		} catch (InvalidName e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CannotProceed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}
