package be.ephec.nsjc.jws.internal;

import java.io.IOException;

import be.ephec.nsjc.jws.model.Header;
import be.ephec.nsjc.jws.model.Request;
import be.ephec.nsjc.jws.model.Response;
import be.ephec.nsjc.jws.model.ResponseCode;

public class ResponseBuilder {
	
	private Request request;

	public ResponseBuilder(Request request) {
		this.request = request;
	}
	
	public Response buildResponse(){
		String path = request.getRequestURI();
		path = path.equals("/")?"/index.html":path;
		switch(request.getMethod()){
		case "GET":
			if(FileUtils.fileExists(path)){
				Response resp = new Response(ResponseCode.OK);
				resp.addHeader(new Header("Content-Type", "text/html"));
				try {
					resp.setBody(new String(FileUtils.getFileContent(path)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					resp.setHTTPCode(ResponseCode.INTERNAL_ERROR);
					resp.setBody(e.getMessage());
				}
				return resp;
			}else{
				Response resp = new Response(ResponseCode.NOT_FOUND);
				resp.setBody(ResponseCode.NOT_FOUND.getDescr());
				return resp;
			}
		case "POST":
			if(FileUtils.fileExists(path)){
				Response resp = new Response(ResponseCode.BAD_REQUEST);
				resp.setBody(ResponseCode.BAD_REQUEST.getDescr());
				return resp;
			}else{
				try {
					FileUtils.postFile(path, request.getBody().getBytes());
					Response resp = new Response(ResponseCode.OK);
					resp.setBody(ResponseCode.OK.getDescr());
					return resp;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Response resp = new Response(ResponseCode.INTERNAL_ERROR);
					resp.setBody(e.getMessage());
					return resp;
				}
				
			}
		case "DELETE":
			try {
				FileUtils.deleteFile(path);
				Response resp = new Response(ResponseCode.OK);
				resp.setBody(ResponseCode.OK.getDescr());
				return resp;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Response resp = new Response(ResponseCode.INTERNAL_ERROR);
				resp.setBody(e.getMessage());
				return resp;
			}
		default:
			Response resp = new Response(ResponseCode.NOT_IMPL);
			resp.setBody(ResponseCode.NOT_IMPL.getDescr());
			return resp;
		}
	}
}
