/**
 * 
 */

/*
File Name: Block_Cipher.java
Author: Aadhya Bhatt
Date: 11-Feb-2019 4:10:50 PM
Description:
*/




import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Block_Cipher 
{
    public static int padding;
    public static int padding_1;
    public static String orig_txt="cryptography is an important tool for network security. but there are other issues for network security.";
    public static String m="";
    public static String mtempa="";
 
    
    static Encryption_Sys egg =new Encryption_Sys();
    
    Block_Cipher()
    {
      	padding_1=0;
        ArrayList<Integer>  list= new ArrayList<Integer>();
        list.add(1);
        list.add(0);
        
        // INITAIALIZING THE ITERATION TO 16 SINCE THE SIZE OF THE BLOCK IS 16
        
        for(int i=0;i<16;i++)
        {
        	// SHUFFLING THE LIST
        	
            Collections.shuffle(list);
            m+=list.get(0);
        }
     }
   
    
    public static List<String> de(String str)
    {
        
        List<String> str_lst=new ArrayList<>();
        List<String> tempa=new ArrayList<>();
        
        // INITIALIZATION OF THE LENGTH OF THE STRING
        
        for(int i=0;i<str.length();i=i+16)
        {
            tempa.add(str.substring(i,i+16));
        }
        
        // INITIALIZATION OF THE SIZE OF TEMPORARY ARRAY LIST
        
        for(int w=0; w<tempa.size(); w++)
        {
            String temp_main=""; 
            String x=Encryption_Sys.dec(tempa.get(w)).toString();
            x=x.substring(1, x.length()-1);
            
            
            // INITIALIZING THE SIZE WHICH SHOULD NOT BE GREATER TAN 16 , SINCE THE BLOCK SIZE IS 16
            
            for(int k=0;k<16;k++)
            {
            	temp_main+=x.charAt(k)^mtempa.charAt(k);
            }
            
            // ADDING THE TEMPRARY VARIABLE TO STRING 
            
            mtempa=tempa.get(w);
            str_lst.add(temp_main);
        }
        return str_lst;
    }
    
    
    public static List<String> en(String str)
    {
    	// GENERATING A CONDITION OF STRING LENGTH TO BE NOT MORE THAN  16
      while(str.length()%16!=0)
      {
      	// APPENDING "0" IN THE END OF THE STRING
          str+="0";
          padding_1++;
      }   
      String y;     
      List<String> lis=new ArrayList<String>();
      List<String> tempa=new ArrayList<String>();
      
      mtempa=m;
      for(int g=0;g<str.length();g=g+16)
      {
       String temp=str.substring(g,g+16);
          
       y="";
     
       // ENCRYPT Y AND FIND THE CHAR LOCATION OF H
       
       for(int h=0;h<16;h++)
       {
           y+=m.charAt(h)^temp.charAt(h);
       }
       tempa =Encryption_Sys.en(y);
       
       // THE TEMPRARY CHAR IS CONVERTED TO STRING
       
       m=tempa.toString().substring(1,tempa.toString().length()-1);
       lis.add(m);
      }
        return lis;
    }
    
    
    public static void main(String[] args)
    {
        	List<String> ciphertextbin = new ArrayList<String>();
    	    List<String> cipher_dec = new ArrayList<String>();
    	    
    	    //ENCRYPT THE ORIGINAL PLAIN TEXT
    	    
    	    int[] num_plaintext=Encryption_Sys.num(orig_txt);
    	    String tempa="";
    	    String ciphertext=Encryption_Sys.to_Alph(tempa);
    	    
    	    int[]  num_ciphertext=Encryption_Sys.num(ciphertext);
    	    String bin_ciphertext=Encryption_Sys.bit(num_ciphertext);
    	    String bin_plaintext= Encryption_Sys.bit(num_plaintext);
    	    String dec_text=Encryption_Sys.to_Alph(tempa);
    	    System.out.println("------THE OUTPUT OF THIS PROGRAM IS ------\n\n");
    	    System.out.println("THE BLOCK CIPHER MODE OF OPERATION (CBC)\n\n");
        
          Block_Cipher  blk = new Block_Cipher ();
          System.out.println("THE ORIGINAL PLAIN TEXT IS:");
          System.out.println(Block_Cipher .orig_txt);
          System.out.println("");
           
          System.out.println("THE KEY VALUE IS:");
          System.out.println(Encryption_Sys.key_value);
          System.out.println("");        
             tempa="";
          
             num_plaintext=Encryption_Sys.num(Block_Cipher .orig_txt);
             System.out.println("THE VALUE TILL Z32 IS:");
             for(int i=0;i<num_plaintext.length;i++)
             System.out.print(num_plaintext[i]);
             System.out.println("\n");
             bin_plaintext= Encryption_Sys.bit(num_plaintext);
           System.out.println("THE VALUE IN BITS IS:");
           System.out.println(bin_plaintext);
           System.out.println("");
    
        ciphertextbin=new ArrayList<String>();
        ciphertextbin=Block_Cipher .en(bin_plaintext);
        
        for(int i=0;i<ciphertextbin.size();i++)
           tempa+=ciphertextbin.get(i);
        
        System.out.println("THE VALUE OF CIPHER TEXT TO  BINARY IS :");
        System.out.println(tempa);
        System.out.println("");
                
        ciphertext=Encryption_Sys.to_Alph(tempa);
        System.out.println(" THE CIPHER TEXT VALUE IS:");
        System.out.println(ciphertext);
        System.out.println("");

        num_ciphertext=Encryption_Sys.num(ciphertext);
        bin_ciphertext=Encryption_Sys.bit(num_ciphertext);
        bin_ciphertext=bin_ciphertext.substring(0, bin_ciphertext.length()-padding);

    }
    }
    

