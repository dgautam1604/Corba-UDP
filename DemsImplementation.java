import java.util.HashMap;

import org.omg.CORBA.ORB;

import DEMSApp.ManagerPOA;



public class DemsImplementation extends ManagerPOA{

	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	@Override
	public String addEvent(String eventID, String eventType, int bookingCapacity) {
		// TODO Auto-generated method stub
		Montreal mn=new Montreal();
		String var=mn.getHashMap(eventType);
		//mn.addHashMap(var, eventID, bookingCapacity);
		return (mn.addHashMap(var, eventID, bookingCapacity));
	}

	@Override
	public String removeEvent(String eventID, String eventType) {
		// TODO Auto-generated method stub
		Montreal mn=new Montreal();
		String var=mn.getHashMap(eventType);
		
		return mn.removeHashMap(var, eventID);
		
	}

	@Override
	public String listEventAvailability(String eventType) {
		Montreal mn=new Montreal();
		String var=mn.getHashMap(eventType);
		HashMap<String, Integer> temp1 = new HashMap<String, Integer>();
		temp1=	mn.display(eventType);
		StringBuffer str=new StringBuffer(" ");  
		temp1.entrySet().forEach(entry -> {
			
			str.append(entry.getKey()+" "+ entry.getValue() +"\n");
		});
		return str.toString();
	}

	@Override
	public String bookEvent(String customerID, String eventID, String eventType) {
		Montreal mn=new Montreal();
		String var=mn.getHashMap(eventType);
		if(mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase("Available ")){
			String r=mn.bookedEvent(eventID,customerID);
			
			return(r);
		}
		else{
			return("No such event is available");
		}
		
	}

	@Override
	public String getBookingSchedule(String customerID) {

		Montreal mn=new Montreal();
		HashMap<String, String> temp1 = new HashMap<String, String>();
		temp1=	mn.getUserData(customerID);
		
		StringBuffer str=new StringBuffer(" "); 
		temp1.entrySet().forEach(entry -> {
			str.append(entry.getKey()+" "+ entry.getValue() +"\n");
		});
		
		
		return str.toString();
	}

	@Override
	public String cancelEvent(String customerID, String eventID, String eventType) {
		// TODO Auto-generated method stub
		Montreal mn=new Montreal();
		String var=mn.getHashMap(eventType);
		if(mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase("available ")){
			if(mn.checkUserBooking(eventID,customerID)){
				String c=mn.canceledEvent(eventID,customerID);
				
				return(c);
			}
			else
				return("EventId not registered for customerId");
		}
		else{
			return("No such eventid is available in this eventType");
		}
		
		
	}

	@Override
	public String swapEvent(String customerID, String newEventID,
			String newEventType, String oldEventID, String oldEventType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
