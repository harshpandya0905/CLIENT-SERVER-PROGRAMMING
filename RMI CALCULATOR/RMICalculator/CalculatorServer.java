import java.rmi.Naming;

public class CalculatorServer

{

public CalculatorServer() 

{

try 

{

Calculator c = new CalculatorImpl();

//The server registers its remote objects with the RMIRegistry
//It passes the URL and the object reference
//The first argument is the URL
//The URL contains the IP address,default port number,
//name of Remote Object.
//The second argument is the remote object reference.

Naming.rebind("rmi://localhost:1099/CalculatorService", c);

System.out.println("Server is ready and waiting");

}

catch (Exception e)

{

System.out.println("There is an exception: " + e);

}

}

public static void main(String args[])

{

//call to the constructor
new CalculatorServer();

}

}
