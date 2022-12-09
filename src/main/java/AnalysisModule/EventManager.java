package AnalysisModule;

import java.util.ArrayList;

import ViewerModule.Observer;

public class EventManager {
	
	private ArrayList<Observer> observers;
	
	public EventManager() {
		observers = new ArrayList<>();
	}
		
	public void registerObserver(Observer o) {
		
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		
		if(observers.indexOf(o) >= 0) {
			observers.remove(o);
		}
	}
	
	public void notifyObservers(AnalysisData data) {
		
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(data); 
		}
		
	}

}
