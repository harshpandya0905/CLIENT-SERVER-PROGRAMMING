//A container which holds all the interfaces,methods,parameters,
//exceptions,events etc.

//This interface is a remote interface

public interface Calculator extends java.rmi.Remote

{

//RemoteException occurs when the RMI procedure is unsuccessful.
//The methods handle Network Error Exceptions
 
public int calculate(int opcode,int op1,int op2) 
throws java.rmi.RemoteException;

public void exit()
throws java.rmi.RemoteException; 
 
    
} 
