import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import DEMSApp.Manager;
import DEMSApp.ManagerHelper;


public class Client22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ORB orb = ORB.init(args, null);
			//-ORBInitialPort 1050 -ORBInitialHost localhost
			org.omg.CORBA.Object objRef;
			objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			Manager dem=(Manager) ManagerHelper.narrow(ncRef.resolve_str("mtl"));
			
			
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
