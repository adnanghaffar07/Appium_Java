package common;

import  java.security.SecureRandom ;

public  final  class  CPFGenerator {

   private  static  final  int [] MULTIPLICATORS_CPF  = { 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 };
   private  static  final  int [] MULTIPLICATORS_CNPJ  = { 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 2 , 3 , 4 , 5 , 6 };

   private  static  final  SecureRandom  RND  =  new  SecureRandom ();

   private  CPFGenerator () {

   }

   public  static  String  generateCPF () {
       return generateCPFCNPJ ( 9 , MULTIPLICATORS_CPF );
   }

   public  static  String  generateCNPJ () {
       return generateCPFCNPJ ( 12 , MULTIPLICATORS_CNPJ );
   }

   private  static  String  generateCPFCNPJ ( int  size , int [] multipliers ) {
       String base = generateStringNumber (size);
       String dv1 =  common.Modulo11 . calculateModulo11 (base, multipliers);
       String dv2 =  common.Modulo11 . calculateModulo11 (base . concat (dv1), multipliers);
       return base . concat (dv1) . concat (dv2);
   }

   private  static  String  generateStringNumber ( int  size ) {
       StringBuilder sb =  new  StringBuilder (size);
       for ( int i =  0 ; i < size; i ++ ) {
           sb . append ( RND . nextInt ( 10 ));
       }
       return sb . toString ();
   }

}