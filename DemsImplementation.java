import java.util.HashMap;

import org.omg.CORBA.ORB;

import DEMSApp.ManagerPOA;

public class DemsImplementation extends ManagerPOA {

	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	@Override
	public String addEvent(String eventID, String eventType,
			int bookingCapacity, String serv) {

		if (serv.equalsIgnoreCase("MTL")) {
			Montreal mn = new Montreal();
			String var = mn.getHashMap(eventType);
			// mn.addHashMap(var, eventID, bookingCapacity);
			return (mn.addHashMap(var, eventID, bookingCapacity));
		} else if (serv.equalsIgnoreCase("QUE")) {
			Quebec mn = new Quebec();

			String var = mn.getHashMap(eventType);
			// mn.addHashMap(var, eventID, bookingCapacity);
			return (mn.addHashMap(var, eventID, bookingCapacity));
		} else if (serv.equalsIgnoreCase("SHE")) {
			Sherbrook mn = new Sherbrook();
			String var = mn.getHashMap(eventType);
			// mn.addHashMap(var, eventID, bookingCapacity);
			return (mn.addHashMap(var, eventID, bookingCapacity));
		}
		return null;

	}

	@Override
	public String removeEvent(String eventID, String eventType, String serv) {

		if (serv.equalsIgnoreCase("MTL")) {
			Montreal mn = new Montreal();
			String var = mn.getHashMap(eventType);
			return mn.removeHashMap(var, eventID);
		} else if (serv.equalsIgnoreCase("QUE")) {
			Quebec mn = new Quebec();
			String var = mn.getHashMap(eventType);
			return mn.removeHashMap(var, eventID);
		} else if (serv.equalsIgnoreCase("SHE")) {
			Sherbrook mn = new Sherbrook();
			String var = mn.getHashMap(eventType);
			return mn.removeHashMap(var, eventID);
		}
		return null;

	}

	@Override
	public String listEventAvailability(String eventType, String serv) {

		String str = new String();
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";

		if (serv.equalsIgnoreCase("MTL")) {
			Montreal mn = new Montreal();
			String var = mn.getHashMap(eventType)+"display";
			
			temp1 = mn.display(var);

			temp2 = mn.UDPConnect(6001, var);

			temp3 = mn.UDPConnect(6002, var);

			str = temp1 + temp2 + temp3;

			return str;

		} else if (serv.equalsIgnoreCase("QUE")) {
			Quebec mn = new Quebec();
			String var = mn.getHashMap(eventType);
			temp1 = mn.display(eventType);

			temp2 = mn.UDPConnect(6001, var);

			temp3 = mn.UDPConnect(6002, var);

			str = temp1 + temp2 + temp3;

			return str;
		} else if (serv.equalsIgnoreCase("SHE")) {
			Sherbrook mn = new Sherbrook();
			String var = mn.getHashMap(eventType);
			temp1 = mn.display(eventType);

			temp2 = mn.UDPConnect(6001, var);

			temp3 = mn.UDPConnect(6002, var);

			str = temp1 + temp2 + temp3;

			return str;
		}

		else
			return null;
	}

	@Override
	public String bookEvent(String customerID, String eventID,
			String eventType, String serv) {
		char[] ch = eventID.toCharArray();
		char[] ch2 = { ch[0], ch[1], ch[2] };
		String bookingServ = new String(ch2);

		if (serv.equalsIgnoreCase("MTL")) {
			Montreal mn = new Montreal();
			String var = mn.getHashMap(eventType)+"booked "+customerID+eventID;

			if (serv.equalsIgnoreCase(bookingServ)) {
				if (mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
						"Available ")) {
					String r = mn.bookedEvent(eventID, customerID);

					return (r);
				} else {
					return ("No such event is available");
				}
			}
			else if(bookingServ.equalsIgnoreCase("QUE")){
				String count=mn.UDPConnect(6001, ("checkCount"+customerID));
				String count1=mn.UDPConnect(6002, ("checkCount"+customerID));
				int counter=Integer.parseInt(count)+Integer.parseInt(count1);
				if(counter>3){
					return "Cannot book.You already have 3 booking in the servers";
				}
				
				String temp2;
				temp2 = mn.UDPConnect(6001, var);
				return temp2;
				
			}else if(bookingServ.equalsIgnoreCase("SHE")){
				String count=mn.UDPConnect(6001, ("checkCount"+customerID));
				String count1=mn.UDPConnect(6002, ("checkCount"+customerID));
				int counter=Integer.parseInt(count)+Integer.parseInt(count1);
				if(counter>3){
					return "Cannot book.You already have 3 booking in the servers";
				}
				
				String temp3;
				temp3 = mn.UDPConnect(6002, var);
				return temp3;
			}
			
			
		} else if (serv.equalsIgnoreCase("QUE")) {
			Quebec mn = new Quebec();

			String var = mn.getHashMap(eventType)+"booked ";
			if (serv.equalsIgnoreCase(bookingServ)) {
				if (mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
						"Available ")) {
					String r = mn.bookedEvent(eventID, customerID);

					return (r);
				} else {
					return ("No such event is available");
				}
			}
			else if(bookingServ.equalsIgnoreCase("MTL")){
				String count=mn.UDPConnect(6000, ("checkCount"+customerID));
				String count1=mn.UDPConnect(6002, ("checkCount"+customerID));
				int counter=Integer.parseInt(count)+Integer.parseInt(count1);
				if(counter>3){
					return "Cannot book.You already have 3 booking in the servers";
				}
				String temp2;
				temp2 = mn.UDPConnect(6000, var);
				return temp2;
				
			}else if(bookingServ.equalsIgnoreCase("SHE")){
				String count=mn.UDPConnect(6000, ("checkCount"+customerID));
				String count1=mn.UDPConnect(6002, ("checkCount"+customerID));
				int counter=Integer.parseInt(count)+Integer.parseInt(count1);
				if(counter>3){
					return "Cannot book.You already have 3 booking in the servers";
				}
				String temp3;
				temp3 = mn.UDPConnect(6002, var);
				return temp3;
			}
			
		} else if (serv.equalsIgnoreCase("SHE")) {
			Sherbrook mn = new Sherbrook();

			String var = mn.getHashMap(eventType)+"booked ";
			if (serv.equalsIgnoreCase(bookingServ)) {
				if (mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
						"Available ")) {
					String r = mn.bookedEvent(eventID, customerID);

					return (r);
				} else {
					return ("No such event is available");
				}
			}
			else if(bookingServ.equalsIgnoreCase("QUE")){
				String count=mn.UDPConnect(6000, ("checkCount"+customerID));
				String count1=mn.UDPConnect(6001, ("checkCount"+customerID));
				int counter=Integer.parseInt(count)+Integer.parseInt(count1);
				if(counter>3){
					return "Cannot book.You already have 3 booking in the servers";
				}
				String temp2;
				temp2 = mn.UDPConnect(6001, var);
				return temp2;
				
			}else if(bookingServ.equalsIgnoreCase("MTL")){
				String count=mn.UDPConnect(6000, ("checkCount"+customerID));
				String count1=mn.UDPConnect(6001, ("checkCount"+customerID));
				int counter=Integer.parseInt(count)+Integer.parseInt(count1);
				if(counter>3){
					return "Cannot book.You already have 3 booking in the servers";
				}
				String temp3;
				temp3 = mn.UDPConnect(6000, var);
				return temp3;
			}
			
		}
		return null;

	}

	@Override
	public String getBookingSchedule(String customerID, String serv) {

		if (serv.equalsIgnoreCase("MTL")) {
			Montreal mn = new Montreal();
			String var ="Userdat"+customerID;

			String temp1 = mn.getUserData(customerID);
			String temp2 = mn.UDPConnect(6001, var);

			String temp3 = mn.UDPConnect(6002, var);

			String str = temp1 + temp2 + temp3;

			return str;

		} else if (serv.equalsIgnoreCase("QUE")) {
			Quebec mn = new Quebec();
			String var = "Userdat"+customerID;

			String temp1 = mn.getUserData(customerID);
			String temp2 = mn.UDPConnect(6001, var);

			String temp3 = mn.UDPConnect(6002, var);

			String str = temp1 + temp2 + temp3;

			return str;
		} else if (serv.equalsIgnoreCase("SHE")) {
			Sherbrook mn = new Sherbrook();
			String var = "Userdat"+customerID;

			String temp1 = mn.getUserData(customerID);
			String temp2 = mn.UDPConnect(6001, var);

			String temp3 = mn.UDPConnect(6002, var);

			String str = temp1 + temp2 + temp3;

			return str;
		}

		else
			return null;
	}

	@Override
	public String cancelEvent(String customerID, String eventID,
			String eventType, String serv) {

		char[] ch = eventID.toCharArray();
		char[] ch2 = { ch[0], ch[1], ch[2] };
		String bookingServ = new String(ch2);

		if (serv.equalsIgnoreCase("MTL")) {
			Montreal mn = new Montreal();
			String var = mn.getHashMap(eventType)+"cancel "+customerID+eventID;

			if (serv.equalsIgnoreCase(bookingServ)) {
				
				if (mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
						"available ")) {
					if (mn.checkUserBooking(eventID, customerID)) {
						String c = mn.canceledEvent(eventID, customerID);

						return (c);
					} else
						return ("EventId not registered for customerId");
				} else {
					return ("No such eventid is available in this eventType");
				}
			}
			else if(bookingServ.equalsIgnoreCase("QUE")){
				
				String temp2;
				temp2 = mn.UDPConnect(6001, var);
				return temp2;
				
			}else if(bookingServ.equalsIgnoreCase("SHE")){
				
				String temp3;
				temp3 = mn.UDPConnect(6002, var);
				return temp3;
			}
			
			
		} else if (serv.equalsIgnoreCase("QUE")) {
			Quebec mn = new Quebec();

			String var = mn.getHashMap(eventType)+"cancel ";
			if (serv.equalsIgnoreCase(bookingServ)) {
				if (mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
						"available ")) {
					if (mn.checkUserBooking(eventID, customerID)) {
						String c = mn.canceledEvent(eventID, customerID);

						return (c);
					} else
						return ("EventId not registered for customerId");
				} else {
					return ("No such eventid is available in this eventType");
				}
			}
			else if(bookingServ.equalsIgnoreCase("MTL")){
				
				String temp2;
				temp2 = mn.UDPConnect(6000, var);
				return temp2;
				
			}else if(bookingServ.equalsIgnoreCase("SHE")){
				
				String temp3;
				temp3 = mn.UDPConnect(6002, var);
				return temp3;
			}
			
		} else if (serv.equalsIgnoreCase("SHE")) {
			Sherbrook mn = new Sherbrook();

			String var = mn.getHashMap(eventType)+"cancel ";
			if (serv.equalsIgnoreCase(bookingServ)) {
				if (mn.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
						"available ")) {
					if (mn.checkUserBooking(eventID, customerID)) {
						String c = mn.canceledEvent(eventID, customerID);

						return (c);
					} else
						return ("EventId not registered for customerId");
				} else {
					return ("No such eventid is available in this eventType");
				}
			}
			else if(bookingServ.equalsIgnoreCase("QUE")){
				
				String temp2;
				temp2 = mn.UDPConnect(6001, var);
				return temp2;
				
			}else if(bookingServ.equalsIgnoreCase("MTL")){
				
				String temp3;
				temp3 = mn.UDPConnect(6000, var);
				return temp3;
			}
			
		}
		return null;
		

	}

	@Override
	public String swapEvent(String customerID, String newEventID,
			String newEventType, String oldEventID, String oldEventType,
			String serv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
