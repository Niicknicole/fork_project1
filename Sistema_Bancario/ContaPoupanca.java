package SistemaBancario;

public class ContaPoupanca extends ContaBancaria {

    public ContaPoupanca(String titular){
        super(titular);
    }
    
    @Override
    public void sacar(double valor ){
        if (saldo >= valor){
            saldo -= valor;
            System.out.println(" Saque de r$ " + valor + " realizado da poupança.");
        }else{
            System.out.println("Saldo Insuficiente ");
        }
    }
}
