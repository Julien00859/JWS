package be.ephec.nsjc.jws.model;


public class Header {
	private String label;
	private String value;
	
	/**
	 * Constructor
	 * @param label Label of the header
	 * @param value Value of the header
	 */
	public Header(String label, String value){
		this.label = label;
		this.value = value;
	}
	
	
	/**
	 * 
	 * @return a String in this template "Header [type=Type, label=Label, value=Value]"
	 */
	public String toString() {
		return "Header [label=" + label + ", value=" + value + "]";
	}
	
	
	
	

	/** 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}


	/**
	 * @return a boolean (true if o is an Header and has the same label).
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Header){
			Header h = (Header) o;
			return h.label.equals(this.label);
		}else{
			return false;
		}
	}


	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
