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

	/**
	 * Create an empty HTTPTrace with default values
	 */
	public HTTPTrace(){
		this.counter = 0;
		this.gotRequest = false;
		this.madeResponse = false;
		this.requestList = FXCollections.observableArrayList();
		this.responseList = FXCollections.observableArrayList();
	}

	/**
	 * Prepare the trace to handle the next request
	 */
	public void reset(){
		this.gotRequest = this.madeResponse = false;
		this.counter++;
	}

	/**
	 * Add a request object to the trace
	 * @param req the request
	 * @return the value of the counter
	 */
	public int addRequest(Request req){
		synchronized (lockReq) {
			this.requestList.add(counter, req);
			this.gotRequest = true;
			this.setChanged();
			this.notifyObservers(this.counter);
			return this.counter;
		}
	}

	/**
	 * Add a response object to the trace
	 * @param counter the value returned by the addRequest (used to sync req/res)
	 * @param res the response
	 */
	public void addResponse(int counter, Response res){
		synchronized (lockRes) {
			this.responseList.add(counter, res);
			this.madeResponse = true;
			this.setChanged();
			this.notifyObservers(counter);
		}
		
	}

	/**
	 * @return the gotRequest
	 */
	public boolean hasGotRequest() {
		return this.gotRequest;
	}

	/**
	 * @return the madeResponse
	 */
	public boolean hasMadeResponse() {
		return this.madeResponse;
	}

	/**
	 * @return the requestList
	 */
	public ObservableList<Request> getRequestList() {
		return this.requestList;
	}

	/**
	 * @return the responseList
	 */
	public ObservableList<Response> getResponseList() {
		return this.responseList;
	}
	
	
	
	
	
	
}
