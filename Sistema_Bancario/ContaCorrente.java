package SistemaBancario;

public class ContaCorrente  extends ContaBancaria{
    private double taxaSaque = 2.50;


    public ContaCorrente(String titular){
        super(titular);
    }
    

    @Override
    public void sacar(double valor){

        if(saldo >= (valor + taxaSaque)){
            saldo -= (valor + taxaSaque);
            System.out.println("Saque de R$ " +  valor + " realizado (Taxa): R$ "+ taxaSaque);
        }else {
            System.out.println("Saldo insuficiente para saque mais taxas");
        }
    }
}
