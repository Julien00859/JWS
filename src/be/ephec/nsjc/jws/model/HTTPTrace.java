package be.ephec.nsjc.jws.model;

import java.util.Observable;

import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HTTPTrace extends Observable{
	private boolean gotRequest;
	private boolean madeResponse;
	
	private ObservableList<Request> requestList;
	private ObservableList<Response> responseList;
	
	public int counter;
	
	private Object lockReq = new Object();
	private Object lockRes = new Object();
	
	public HTTPTrace(){
		counter = 0;
		gotRequest = false;
		madeResponse = false;
		requestList = FXCollections.observableArrayList();
		responseList = FXCollections.observableArrayList();
	}
	
	public void reset(){
		gotRequest = madeResponse = false;
		this.counter++;
	}
	
	public int addRequest(Request req){
		synchronized (lockReq) {
			requestList.add(counter, req);
			gotRequest = true;
			setChanged();
			notifyObservers(this.counter);
			return this.counter;
		}
	}
	
	public void addResponse(int counter, Response res){
		synchronized (lockRes) {
			responseList.add(counter, res);
			madeResponse = true;
			setChanged();
			notifyObservers(counter);
		}
		
	}

	/**
	 * @return the gotRequest
	 */
	public boolean hasGotRequest() {
		return gotRequest;
	}

	/**
	 * @return the madeResponse
	 */
	public boolean hasMadeResponse() {
		return madeResponse;
	}

	/**
	 * @return the requestList
	 */
	public ObservableList<Request> getRequestList() {
		return requestList;
	}

	/**
	 * @return the responseList
	 */
	public ObservableList<Response> getResponseList() {
		return responseList;
	}
	
	
	
	
	
	
}
