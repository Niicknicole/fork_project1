package SistemaBancario;

public abstract class ContaBancaria {
    private String titular;
    protected double saldo;
    private static int contador = 1000;
    private int numeroConta;

    public ContaBancaria(String titular){
        this.titular = titular;
        this.saldo = 0;
        this.numeroConta = contador++;
    }
    public int getNumeroConta(){
        return numeroConta;
    }
    public String getTitular() {
    return titular;
}

   public double getSaldo() {
    return saldo;
    }
      
    public void depositar(double valor){
        if (valor > 0 ){
            saldo += valor;
            System.out.println("Deposito de R$ " + valor + " realizado");
        } else{
            System.out.println("Valor invalido para deposito ");
        }
    }

    public abstract void sacar(double valor );

    public void exibirSaldo(){
        System.out.println("Conta #" + numeroConta +
        " | Titular: " + titular +
        " | Saldo: R$ " + saldo);
    }
}
