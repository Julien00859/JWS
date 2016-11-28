package be.ephec.nsjc.jws.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.IllegalFormatException;

import be.ephec.nsjc.jws.model.Header;
import be.ephec.nsjc.jws.model.Request;

public class RequestHandler {
	
	BufferedReader reader;
	
	public RequestHandler(BufferedReader reader){
		this.reader = reader;
	}
	
	public Request parseRequest() throws IOException{
		String requestLine = this.reader.readLine();
		if(requestLine == null){
			//BAD! 
			return null;
		}else{
			String[] reqSplit = requestLine.split(" ");
			if(reqSplit.length != 3){
				//BAD!
				return null;
			}
			Request r = new Request(reqSplit[0], reqSplit[1], reqSplit[2]);
			r = parseHeaders(r);
			if(r == null){
				//BAD!
				return r;
			}
			r = parseBody(r);
			return r;
		}
	}

	private Request parseBody(Request r) {
		// TODO
		return r;
	}

	private Request parseHeaders(Request r) throws IOException {
		String line = this.reader.readLine();
		Header last = null;
		while(line != null && line.length() != 0){
			if(line.startsWith("\t")){
				if(last == null){
					//BAD!
					return null;
				}else{
					last.setLabel(last.getLabel()+"\n"+line);
				}
			}else{
				if(last != null){
					r.addHeader(last);
				}
				
			}
		}
		return r;
	}
	
}
