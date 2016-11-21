package be.ephec.nsjc.jws.model;


public class Header {
	private String label;
	private String value;
	
	/**
	 * Constructeur
	 * @param label Le label de l'header
	 * @param value La valeur de l'header
	 */
	public Header(String label, String value){
		this.label = label;
		this.value = value;
	}
	
	
	/**
	 * Retourne le Header sous forme de chaîne de caractères
	 * @return String dans le format Header [type=Type, label=Label, value=Value]
	 */
	public String toString() {
		return "Header [label=" + label + ", value=" + value + "]";
	}
	
	/**
	 * @return true si l'objet testé est de type Header et que ses propriétés sont identiques à celles de l'instance courante.
	 */
	public boolean equals(Object o){
		if(o instanceof Header){
			Header h = (Header) o;
			return h.label.equals(this.label) && h.value.equals(this.value);
		}else{
			return false;
		}
	}


	/**
	 * @return Le label de l'Header
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * @return La valeur de l'Header
	 */
	public String getValue() {
		return value;
	}

	
	
	
	
}
