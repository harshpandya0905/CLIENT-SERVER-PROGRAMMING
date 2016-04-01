package HarshCalcy;


/**
* HarshCalcy/CalculatorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calculator.idl
* Sunday, October 19, 2014 5:50:27 PM PDT
*/

abstract public class CalculatorHelper
{
  private static String  _id = "IDL:HarshCalcy/Calculator:1.0";

  public static void insert (org.omg.CORBA.Any a, HarshCalcy.Calculator that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HarshCalcy.Calculator extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (HarshCalcy.CalculatorHelper.id (), "Calculator");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HarshCalcy.Calculator read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CalculatorStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HarshCalcy.Calculator value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static HarshCalcy.Calculator narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HarshCalcy.Calculator)
      return (HarshCalcy.Calculator)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HarshCalcy._CalculatorStub stub = new HarshCalcy._CalculatorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static HarshCalcy.Calculator unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HarshCalcy.Calculator)
      return (HarshCalcy.Calculator)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HarshCalcy._CalculatorStub stub = new HarshCalcy._CalculatorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}