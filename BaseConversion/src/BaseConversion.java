import java.util.Scanner;

public class BaseConversion {
	public static String convertBinary(String number) {
		// Convert binary to decimal
		int convertedNumber = 0;
		
		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) == '1')
				convertedNumber += (int) Math.pow(2, number.length() - i - 1);
		}
		
		return Integer.toString(convertedNumber); // int -> String
	}
	
	public static String toBinary(String number) {
		// Convert decimal to binary
		int result = Integer.parseInt(number); // String -> int
		String convertedNumber = "";
		
		while (result > 0) {
			int bit = result % 2;
			result /= 2;
			convertedNumber = Integer.toString(bit) + convertedNumber; // reverse order
		}
		
		return convertedNumber;
	}
	
	public static String convertToHex(String number) {
		// Convert numbers > 9 to letters
		switch (number) {
		case "10":
			return "A";
		case "11":
			return "B";
		case "12":
			return "C";
		case "13":
			return "D";
		case "14":
			return "E";
		case "15":
			return "F";
		default:
			return number;
		}
	}
	
	public static String letterValue(String number) {
		// Convert letters to numbers
		switch (number) {
		case "A":
			return "10";
		case "B":
			return "11";
		case "C":
			return "12";
		case "D":
			return "13";
		case "E":
			return "14";
		case "F":
			return "15";
		default:
			return number;
		}
	}
	
	public static String base2ToBase8(String number) {
		String convertedNumber = "";
		// Make number divisible by 3 by padding w/ zeros
		if (number.length() % 3 == 1)
			number = "00" + number;
		else if (number.length() % 3 == 2)
			number = "0" + number;
		
		for (int i = 0; i < number.length(); i+=3) {
			String subNumber = number.substring(i, i + 3);
			convertedNumber += convertBinary(subNumber);
		}
		
		return convertedNumber;
	}
	
	public static String base2ToBase10(String number) {
		return convertBinary(number);
	}
	
	public static String base2ToBase16(String number) {
		String convertedNumber = "";
		// Make number divisible by 4 by padding w/ zeros
		if (number.length() % 4 == 1)
			number = "000" + number;
		else if (number.length() % 4 == 2)
			number = "00" + number;
		else if (number.length() % 4 == 3)
			number = "0" + number;
		
		for (int i = 0; i < number.length(); i+=4) {
			String subNumber = number.substring(i, i + 4);
			String decimalNumber = convertBinary(subNumber);
			convertedNumber += convertToHex(decimalNumber);
		}
		
		return convertedNumber;
	}
	
	public static String base8ToBase2(String number) {
		String convertedNumber = "";
		
		for (int i = 0; i < number.length(); i++) {
			String result = toBinary(Character.toString(number.charAt(i)));
			
			if (result.length() == 0)
				convertedNumber += "000";
			else if (result.length() == 1)
				convertedNumber += "00" + result;
			else if (result.length() == 2)
				convertedNumber += "0" + result;
			else
				convertedNumber += result;
		}
		
		return convertedNumber;
	}
	
	public static String base8ToBase10(String number) {
		int convertedNumber = 0;
		
		for (int i = 0; i < number.length(); i++) {
			String result = Character.toString(number.charAt(i));
			int digit = Integer.parseInt(result);
			convertedNumber += digit * (int) Math.pow(8, number.length() - i - 1);
		}
		
		return Integer.toString(convertedNumber);
	}
	
	public static String base8ToBase16(String number) {
		String binaryNumber = base8ToBase2(number);
		return base2ToBase16(binaryNumber);
	}
	
	public static String base10ToBase2(String number) {
		return toBinary(number);
	}
	
	public static String base10ToBase8(String number) {
		int result = Integer.parseInt(number);
		int convertedNumber = 0;
		number = "";
		
		while (result > 0) {
			convertedNumber = result % 8;
			result /= 8;
			number = Integer.toString(convertedNumber) + number; // reverse order
		}
		
		return number;
	}
	
	public static String base10ToBase16(String number) {
		int result = Integer.parseInt(number);
		int convertedNumber = 0;
		number = "";
		
		while (result > 0) {
			convertedNumber = result % 16;
			result /= 16;
			number = convertToHex(Integer.toString(convertedNumber)) + number;
		}
		
		return number;
	}
	
	public static String base16ToBase2(String number) {
		String convertedNumber = "";
		
		for (int i = 0; i < number.length(); i++) {
			String decimalValue = letterValue(Character.toString(number.charAt(i)));
			String result = toBinary(decimalValue);
			
			if (result.length() == 0)
				convertedNumber += "0000";
			else if (result.length() == 1)
				convertedNumber += "000" + result;
			else if (result.length() == 2)
				convertedNumber += "00" + result;
			else if (result.length() == 3)
				convertedNumber += "0" + result;
			else
				convertedNumber += result;
		}
		
		return convertedNumber;
	}
	
	public static String base16ToBase8(String number) {
		String binaryNumber = base16ToBase2(number);
		return base2ToBase8(binaryNumber);
	}
	
	public static String base16ToBase10(String number) {
		int convertedNumber = 0;
		
		for (int i = 0; i < number.length(); i++) {
			String result = letterValue(Character.toString(number.charAt(i)));
			int digit = Integer.parseInt(result);
			convertedNumber += digit * (int) Math.pow(16, number.length() - i - 1);
		}
		
		return Integer.toString(convertedNumber);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.print("Enter base: ");
		int base = input.nextInt();
		System.out.print("Enter number: ");
		String number = input.next(); // number can contain letters
		System.out.print("Enter conversion base: ");
		int toBase = input.nextInt();
		
		switch (base) {
		case 2:
			switch (toBase) {
			case 8:
				System.out.println(number + " in base " + toBase + " is " +
						base2ToBase8(number));
				break;
			case 10:
				System.out.println(number + " in base " + toBase + " is " +
						base2ToBase10(number));
				break;
			case 16:
				System.out.println(number + " in base " + toBase + " is " +
						base2ToBase16(number));
				break;
			default:
				System.out.println("Cannot convert to base " + toBase);
			}
			break;
		case 8:
			switch (toBase) {
			case 2:
				System.out.println(number + " in base " + toBase + " is " +
						base8ToBase2(number));
				break;
			case 10:
				System.out.println(number + " in base " + toBase + " is " +
						base8ToBase10(number));
				break;
			case 16:
				System.out.println(number + " in base " + toBase + " is " +
						base8ToBase16(number));
				break;
			default:
				System.out.println("Cannot convert to base " + toBase);
			}
			break;
		case 10:
			switch (toBase) {
			case 2:
				System.out.println(number + " in base " + toBase + " is " +
						base10ToBase2(number));
				break;
			case 8:
				System.out.println(number + " in base " + toBase + " is " +
						base10ToBase8(number));
				break;
			case 16:
				System.out.println(number + " in base " + toBase + " is " +
						base10ToBase16(number));
				break;
			default:
				System.out.println("Cannot convert to base " + toBase);
			}
			break;
		case 16:
			switch (toBase) {
			case 2:
				System.out.println(number + " in base " + toBase + " is " +
						base16ToBase2(number));
				break;
			case 8:
				System.out.println(number + " in base " + toBase + " is " +
						base16ToBase8(number));
				break;
			case 10:
				System.out.println(number + " in base " + toBase + " is " +
						base16ToBase10(number));
				break;
			default:
				System.out.println("Cannot convert to base " + toBase);
			}
			break;
		default:
			System.out.println("Invalid base type, try again");
		}
		
		input.close();
	}

}
