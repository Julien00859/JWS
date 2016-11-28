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

	private Request parseBody(Request r) throws IOException {
		// TODO
		if(r.getMethod() == "GET"){
			//WE CAN SAFELY IGNORE BODY
			return r;
		}else{
			String body = "";
			String line = this.reader.readLine();
			while(line != null && line.length() != 0){
				body += line+"\r\n";
				line = this.reader.readLine();
			}
			body = body.substring(0, body.length()-2);
			r.setBody(body);
		}
		
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
				String[] rawHeader = line.split(": ");
				last = new Header(rawHeader[0], rawHeader[1]);
			}
			line = this.reader.readLine();
		}
		if(last != null){
			r.addHeader(last);
		}
		return r;
	}
	
}
