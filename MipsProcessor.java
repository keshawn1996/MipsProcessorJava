package mipsProcessor;


import java.util.Scanner;

public class MipsProcessor {
	static String t = "";
	
	
	private static String decToBi(int dec)
	{
		int rem = 0;
		boolean negative = false;
		StringBuilder bi = new StringBuilder();

		
		if(dec < 0) {
			negative = true;
			dec *= -1;
		}
		
		while(dec != 0)
		{
			rem = dec %2;
			bi.insert(0, rem);
			dec = dec/2;
		}

		

		while(bi.length() < 32)
		{
			bi.insert(0, '0');
		}
		
		if(negative) {
			bi = new StringBuilder(twosCompliment(bi.toString()));
		}
		
		return bi.toString();
	}
	private static String biToHex(String hex)
	{
		int dec = hexToDeci(hex);
		String bi = decToBi(dec);
		return bi;
	}
	
	
	private static int hexToDeci(String hex)
	{
		System.out.println("Hex: " + hex);
		StringBuilder str = new StringBuilder(hex);
		str = str.reverse();
		int length = str.length();
		int digit, pow, sum =0;
		
		for(int i=0; i< length; i++)
		{
			if(Character.isDigit(str.charAt(i)))
			{
				digit = str.charAt(i) - 48;
			}
			else
			{
				digit = getHexDigit(str.charAt(i));
			}
			pow = (int) Math.pow(16, i);
			sum = sum + digit * pow;
		}
		return sum;
	}
	
	
	private static int getHexDigit(char c)
	{
		int digit= 0;
		switch(c)
		{
		case 'A':
		case 'a':
			digit = 10;
			break;
		case 'B':
		case 'b':
			digit = 11;
			break;
		case 'C':
		case 'c':
			digit = 12;
			break;
		case 'D':
		case 'd':
			digit = 13;
			break;
		case 'E':
		case 'e':
			digit = 14;
			break;
		case 'F':
		case 'f':
			digit = 15;
			break;
		}

		return digit;
	}
	private static boolean isHexReal(String hex)
	{
		String pat = "^[0-9a-fA-F]+$";
		boolean result = false;
		if(hex.matches(pat))
		{
			result = true;
		}
		return result;
	}
	private static int biToDec(String bi)
	{
		int dec = 0, base = 1;
		int length = bi.length();

		for(int i = length - 1; i >= 0; i--)
		{
			if(bi.charAt(i) == '0')
			{
				dec += 0 * base;
				base = base *2;
			}
			else
			{
				dec += 1 * base;
				base = base *2;
			}
		}
		return dec;
	}
	private static String getOp(String bi)
	{
		String op =bi.substring(0, 6);
		System.out.println(op);
		return op;
	}
	private static String getFunct(String bi)
	{
		String funct = bi.substring(26, 32);
		return funct;
	}
	private static String iParsing(String bi, String t)
	{
		String rs = "";
		String rt = "";
		String immediate = "";
		int rsVal = 0;
		int rtVal =0;
		int imVal = 0;
		String im = "";


		rs = bi.substring(6, 11);
		rsVal = biToDec(rs);
		rt = bi.substring(11, 16);
		rtVal = biToDec(rt);
		immediate = bi.substring(16, 32);

		if(immediate.charAt(0) == '1' && t.equals("addi"))
		{
			im = twosCompliment(immediate);
			imVal = biToDec(im);
			imVal *= -1;
			System.out.println(im);
			System.out.println("$" + rsVal + "+ $" + imVal + " = " + "$rt");
			System.out.println("$rt reassigned:" + "$" + (rsVal +imVal));
			imVal = (rsVal + imVal);
		}
		else if(immediate.charAt(0) == '0' && t.equals("addi"))
		{
			imVal = biToDec(immediate);
			System.out.println("$" + rsVal + "+ $" + imVal + " = " + "$rt");
			System.out.println("$rt reassigned:" + "$" + (rsVal +imVal));
			imVal = (rsVal + imVal);
		}
		else if (immediate.charAt(0) == '0' && t.equals("sw"))
		{
			imVal = biToDec(immediate);
			System.out.println("sw" + "$" + rtVal + "+ $" + rsVal + "$" + imVal);
			System.out.println("sw" + "$" + rtVal + ":" + imVal + "($" + rsVal);
		}
		else if (immediate.charAt(0) == '0' && t.equals("lw"))
		{
			imVal = biToDec(immediate);
			System.out.println("lw" + "$" + rtVal + "+ $" + rsVal + "$" + imVal);
			System.out.println("lw" + "$" + rtVal + ":" + imVal + "($" + rsVal);
		}
		else if (immediate.charAt(0) == '0' && !t.equals("and"))
		{
			imVal = biToDec(immediate);
			System.out.println(imVal);
			System.out.println( "$" + rtVal + "+ $" + rsVal + "$" + imVal);
			System.out.println("$" + rsVal + "and $" + imVal + "=" + "$rt");
		}
		else if (immediate.charAt(0) == '1' && t.equals("sw"))
		{
			im = twosCompliment(immediate);
			imVal = biToDec(im);
			System.out.println("sw" + "$" + rtVal + "+ $" + rsVal + "$" + imVal);
			System.out.println("sw" + "$" + rtVal + ":" + imVal + "($" + rsVal);
		}
		else if (immediate.charAt(0) == '1' && t.equals("lw"))
		{
			im = twosCompliment(immediate);
			imVal = biToDec(im);
			System.out.println("lw" + "$" + rtVal + "+ $" + rsVal + "$" + imVal);
			System.out.println("lw" + "$" + rtVal + ":" + imVal + "($" + rsVal);
		}
		else if (immediate.charAt(0) == '1' && t.equals("and"))
		{
			imVal = biToDec(immediate);
			System.out.println(imVal);
			System.out.println( "$" + rtVal + "+ $" + rsVal + "$" + imVal);
			System.out.println("$" + rsVal + "and $" + imVal + "=" + "$rt");
		}
		return immediate;
	}

	private static void rParsing(String bi, String t)
	{
		String rs = "";
		String rt = "";
		String rd = "";
		String shift = "";
		String funct = "";
		int rsVal = 0;
		int rdVal= 0;
		int rtVal =0;
		int functVal =0;

		rs = bi.substring(6, 11);
		rsVal = biToDec(rs);
		rt = bi.substring(11, 16);
		rtVal = biToDec(rt);
		rd = bi.substring(16, 21);
		rdVal = biToDec(rd);
		shift = bi.substring(21,26);
		funct = bi.substring(26, 32);
		functVal = biToDec(funct);
		if(t == "add")
		{
			System.out.println(" $ " + rdVal + " : $ " + rsVal + " + " + "$ " + rtVal);
		}
		if(t == "sub")
		{
			System.out.println(" $ " + rdVal + " : $ " + rsVal + " - " +  "$ " + rtVal);
		}
		if(t == "and")
		{
			System.out.println(" $ " + rdVal + " : $ " + rsVal + "&&" + "$ " + rtVal);
		}
		if(t == "or")
		{
			System.out.println(" $ " + rdVal + " : $ " + rsVal + " || " + "$ " + rtVal);
		}
	}
	public static String twosCompliment(String immediate) {
		String twos = "", ones = "";

		for (int i = 0; i < immediate.length(); i++) {
			ones += flip(immediate.charAt(i));
		}
		//int number0 = Integer.parseInt(ones, 2);
		StringBuilder builder = new StringBuilder(ones);
		boolean b = false;
		for (int i = ones.length() - 1; i > 0; i--) {
			if (ones.charAt(i) == '1') {
				builder.setCharAt(i, '0');
			} else {
				builder.setCharAt(i, '1');
				b = true;
				break;
			}
		}
		if (!b)
			builder.append("1", 0, 7);

		twos = builder.toString();

		return twos;
	}

	public static char flip(char c)
	{
		return (c == '0') ? '1' : '0';
	}
	private static void bneBEQParsing(String bi, String t)
	{
		String rs ="";
		String opCode = "";
		String rt = "";
		String immediate = "";
		int rsVal = 0;
		int rtVal = 0;
		int imVal= 0;

		rs = bi.substring(4, 9);
		rsVal = biToDec(rs);
		rt = bi.substring(9, 14);
		rtVal = biToDec(rt);
		immediate = bi.substring(15, 30);

		if(rsVal == rtVal)
		{
			System.out.println("BEQ as $rt equals $rs");
		}
		else
		{
			System.out.println("BNE as $rt does not equal $rs");
		}

	}
	public static void main(String[] args) {
		Scanner scan = null;
		String i;
		String num = "";
		try
		{
			scan = new Scanner(System.in);

			while(true) {
				System.out.println("Enter a hexadecimal number :");
				num = scan.nextLine();
				{
				if(isHexReal(num))
				{
					String bi = biToHex(num);
					System.out.println("The binary number is :" + bi);
					String x = getOp(bi);
					String funct = "";
					int opDec = biToDec(x);
					System.out.println("The op code is:" + opDec);
					if(opDec == 8)
					{
						System.out.println("Type I: ADDI");
						t= "addi";
						i = iParsing(bi, t);
						twosCompliment(i);

					}
					else if(opDec == 35)
					{
						System.out.println("Type I: LW");
						t= "lw";
						i = iParsing(bi, t);
						twosCompliment(i);
					}
					else if(opDec == 43)
					{
						System.out.println("Type I: SW");
						t="sw";
						i = iParsing(bi, t);
						twosCompliment(i);
					}
					else if(opDec == 5)
					{
						System.out.println("Type I: BEQ or BNE");
						t = "bneOrbeq";
						bneBEQParsing(bi, t);
					}
					else if(opDec == 4)
					{
						System.out.println("Type I: BEQ or BNE");
						t = "bneOrbeq";
						bneBEQParsing(bi, t);
					}
					else if(opDec == 0)
					{
						funct = getFunct(bi);
						int decFunct = biToDec(funct);
						if(decFunct == 32)
						{
							System.out.println("Type R: ADD");
							t="add";
							rParsing(bi, t);
						}
						else if(decFunct == 34)
						{
							System.out.println("Type R: SUB");
							t="sub";
							rParsing(bi, t);
						}
						else if(decFunct == 37)
						{
							System.out.println("Type R: OR");
							t="or";
							rParsing(bi, t);
						}
						else if(decFunct == 36)
						{
							System.out.println("Type R: AND");
							t="and";
							rParsing(bi, t);
						}
					}
				}
			}
		}
		}
		finally
		{
			if(scan != null)
			{
				scan.close();
			}
		}

	}
}
