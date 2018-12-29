package  common ;

public  final  class  Modulo11 {

    private  Modulo11 () {
    }

    public  static  String  calculateModulo11 ( String  base , int [] multipliers ) {

        int sum =  0 ;

        for ( int i = base . length () -  1 , j =  0 ; i >=  0 ; i-- , j ++ ) {
            sum += multipliers [j] *  Integer . parseInt ( String . valueOf (base . charAt (i)));
        }
    
        int remainder = sum % 11;

        int dv = remainder >  1  ?  11  - remainder :  0;

        return  String . valueOf (dv);
    }

}