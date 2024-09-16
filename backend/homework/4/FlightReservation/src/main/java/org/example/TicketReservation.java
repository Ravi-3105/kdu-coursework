package org.example;

import java.util.*;

public class TicketReservation {
    int CONFIRMEDLIST_LIMIT = 0;
    int WAITINGLIST_LIMIT = 0;

    Collection<Passenger> confirmedList = new ArrayList<>();
    Deque<Passenger> waitingList = new ArrayDeque<>();
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber){

        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if(CONFIRMEDLIST_LIMIT < 10) {
            confirmedList.add(passenger);
            CONFIRMEDLIST_LIMIT++;
        }
        else if(WAITINGLIST_LIMIT < 3){
            waitingList.add(passenger);
            WAITINGLIST_LIMIT++;
        }
        else{
            return false;
        }
        return true;
    }

    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> iterator = confirmedList.iterator();
        if(removePassenger(iterator, confirmationNumber)){

            if(WAITINGLIST_LIMIT>0){

                confirmedList.add(waitingList.poll());
                waitingList.pop();
                WAITINGLIST_LIMIT--;
            }
            else {
                CONFIRMEDLIST_LIMIT--;
            }
            return true;
        }
        Iterator<Passenger> iterator1 = waitingList.iterator();
        if(removePassenger(iterator, confirmationNumber)){

            WAITINGLIST_LIMIT--;
            return true;
        }
        return false;
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()){
            Passenger passenger = iterator.next();
            if( passenger.getConfirmationNumber().equals(confirmationNumber)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        TicketReservation ticketReservation = new TicketReservation();
        if(ticketReservation.bookFlight("A","B",20,"Male","economy","C9")) {
            Logging.print("Successful Booking");
        }
        else{
            Logging.print("Unsuccessful Booking");
        }
        if (ticketReservation.cancel("C9")){
            Logging.print("Cancelled Successfully");
        }
        else{
            Logging.print("Not Cancelled");
        }
    }
}


