package com.lunayo.parking.domain.model.parking;

import com.lunayo.parking.domain.model.Entity;

import java.util.*;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */

public class ParkingSlot extends Entity {

    private PriorityQueue<Integer> slotHeap;
    private int maxSlot;

    public ParkingSlot(int maxSlot) {
        this.setMaxSlot(maxSlot);
        this.setSlotHeap(maxSlot);
    }

    public ParkingSlot(Collection<Car> cars, int maxSlot) {
        this.setMaxSlot(maxSlot);

    }

    public int nextSlot() {
        if(slotHeap.peek() > maxSlot) {
            throw new IndexOutOfBoundsException("Parking Slot is Full.");
        }
        int currSlot = slotHeap.poll();
        if(slotHeap.isEmpty()) {
            // add the next increment id
            slotHeap.add(currSlot+1);
        }
        return currSlot;
    }

    public int deallocateSlot(int slot) {
        if(slot > maxSlot) {
            throw new IndexOutOfBoundsException("Slot Number Exceed Maximum Slot Number.");
        }
        // Find max of the current slot, can be improved with another max heap
        int max = slotHeap.stream().max(Integer::compare).orElse(0);
        // Can be improved to O(1) with hash map
        if(slotHeap.contains(slot) || slot > max) {
            throw new IllegalArgumentException("Parking Slot was Deallocated Before.");
        }
        slotHeap.add(slot);
        return slot;
    }

    private void setSlotHeap(Collection<Car> cars, int maxSlot) {
        this.slotHeap = new PriorityQueue<Integer>(maxSlot, (Integer i1, Integer i2) -> i1 - i2);
        if(cars.size() > maxSlot) {
            throw new IllegalArgumentException("Amount of cars exceed the parking slot.");
        }
        if(cars.size() <= 0) {
            this.slotHeap.add(1);
        } else {
            // Initialize minHeap with current available slot from cars
            List<Car> temp = new ArrayList<Car>(cars);
            // O(nLogn) sorting by slot id, can be improved with space
            Collections.sort(temp, (Car c1, Car c2) -> c1.slotID() - c2.slotID());
            for(int i=1;i<temp.size();++i) {
                int diff = temp.get(i).slotID() - temp.get(i-1).slotID() - 1;
                if(diff > 0) {
                    // adding all the available slot to the heap slot
                    for(int s=1;s<=diff;++s) {
                        this.slotHeap.add(temp.get(i-1).slotID()+s);
                    }
                }
            }
        }
    }

    private void setSlotHeap(int maxSlot) {
        // initialize minHeap
        this.slotHeap = new PriorityQueue<>(maxSlot, (Integer i1, Integer i2) -> i1 - i2);
        this.slotHeap.add(1);
    }

    private void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }

}
