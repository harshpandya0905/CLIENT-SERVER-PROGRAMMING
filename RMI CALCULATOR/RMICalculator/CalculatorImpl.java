//This class has the definitions of the interface methods.
//This class runs on the remote server
//UnicastRemoteObject is the base class for all remote objects
//It provides point to point TCP connectivity

public class CalculatorImpl 
extends 
java.rmi.server.UnicastRemoteObject 
implements
Calculator
 
{ 
 
//The constructor declares the RemoteException exception
 
public CalculatorImpl() 
throws java.rmi.RemoteException
{

//call to the superclass constructor 
super();
 
} 
 
public int calculate(int opcode,int op1,int op2) 
throws java.rmi.RemoteException
 
{ 
 
//ADDITION
if(opcode==43)
{
return (op1+op2);
}

//SUBTRACTION
else if(opcode==45)
{
return (op1-op2);
}

//MULTIPLICATION
else if(opcode==42)
{
return (op1*op2);
}

//DIVISION
else if(opcode==47)
{
return (op1/op2);
}

//DEFAULT
else
{
return 0;
}
       
}

public void exit()
throws java.rmi.RemoteException 
{

System.out.println("The server is out and exiting");

}

} 