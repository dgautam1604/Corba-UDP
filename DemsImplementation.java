import org.omg.CORBA.ORB;

import DEMSApp.ManagerPOA;



public class DemsImplementation extends ManagerPOA{

	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	@Override
	public void addEvent(String eventID, String eventType, int bookingCapacity) {
		// TODO Auto-generated method stub
		Montreal mn=new Montreal();
		String var=mn.getHashMap(eventType);
		mn.addHashMap(var, eventID, bookingCapacity);
	}

	@Override
	public void removeEvent(String eventID, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listEventAvailability(String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bookEvent(String customerID, String eventID, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBookingSchedule(String customerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelEvent(String customerID, String eventID, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swapEvent(String customerID, String newEventID,
			String newEventType, String oldEventID, String oldEventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
