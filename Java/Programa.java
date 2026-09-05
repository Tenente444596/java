public class Programa {
  public static void main(String[] args) {
    String s1 = new String("abc");
    String s2 = new String("abc");
    String s3 = "ABC";
    System.out.print(s1 == s2);
    System.out.print("-");
    System.out.print(s1 == "abc");
    System.out.print("-");
      System.out.print(s3.toLowerCase() ==
"abc");
    System.out.print("-");
System.out.print(s3.equalsIgnoreCase(s2));
    }
    }