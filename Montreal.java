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


public class Montreal {
	
	public static HashMap<String, String> eventList = new HashMap<String, String>();
	public static HashMap<String, Integer> a = new HashMap<String, Integer>();
	public static HashMap<String, Integer> b = new HashMap<String, Integer>();
	public static HashMap<String, Integer> c = new HashMap<String, Integer>();
	public static HashMap<String, String> Muser = new HashMap<String, String>();

	public static void main(String[] args) {
		
		
		// get reference to rootpoa &amp; activate
		
		try {
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
			DemsImplementation dem=new DemsImplementation();
			dem.setORB(orb);
			
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(dem);
			
			Manager href=ManagerHelper.narrow(ref);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent path[] = ncRef.to_name("mtl");
			ncRef.rebind(path, href);
			
			eventList.put("Conference", "a");
			eventList.put("TradeShow", "b");
			eventList.put("Seminar", "c");
			
			System.out.println("Montreal Server ready and waiting ...");

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

	public String getHashMap(String eventType) {
		//it sends a b or c depending on input
		String value = eventList.get(eventType);

		return value;
	}

	public void addHashMap(String var, String key, int Value) {
		if (var == "a") {
			// var=eventType sub_hashmap , key=eventID Value=booking Capacity
			if (a.get(key) != null) {

				int val = a.get(key);
				a.replace(key, val + Value);
				System.out.println("Value updated for " + key + "to " + val);
			} else {
				a.put(key, Value);
				System.out.println("Added Successfully ");
				a.put(key, Value);
			}
		} else if (var == "b") {
			if (b.get(key) != null) {

				int val = b.get(key);
				b.replace(key, val + Value);
				System.out.println("Value updated for " + key + "to " + val);
			} else {
				b.put(key, Value);
				System.out.println("Added Successfully");
			}
		} else if (var == "c") {
			if (c.get(key) != null) {

				int val = c.get(key);
				c.replace(key, val + Value);
				System.out.print("Value updated for " + key + "to " + val);
			} else {
				c.put(key, Value);
				System.out.print("Added Successfully");
			}
		}
		
	}

}
