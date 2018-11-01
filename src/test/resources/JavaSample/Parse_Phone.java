package JavaSample;

public class Parse_Phone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long phoneNum = 1234567891;
	    System.out.println(String.valueOf(phoneNum).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3"));
	    
	    System.out.println(String.valueOf(phoneNum).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3"));
	}

}
