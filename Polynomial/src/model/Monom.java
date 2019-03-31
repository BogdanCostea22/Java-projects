package model;
import java.util.*;
public class Monom implements Comparable<Monom>{
private Number grad,coef;

//Constructori
public Monom(int grad,int coef)
{this.grad=grad;
 this.coef=coef;
}
public Monom(Integer grad,Double coef)
{this.grad=grad;
 this.coef=coef;
}
public Monom(){
this.grad=0;
this.coef=0;
}

//Gettere si Settere
public int getGrad() {
	return grad.intValue();
}
public void setGrad(int grad) {
	this.grad = new Integer(grad);
}
public int getCoef() {
	return coef.intValue();
}
public void setCoef(int coef) {
	this.coef =new Integer(coef);
}
public void setCoefDouble(double coef)
{//Trunchiem coeficientul ca sa nu aiba zerouri
	double result=Math.round(coef*100)/100.0;
	this.coef=result;
}
public double getCoefDouble()
{return this.coef.doubleValue();
}

//Metoda pentru adunarea a doua monoame
public Monom  add(Monom n)
{Monom result=new Monom();
	result.setCoef(this.coef.intValue()+n.getCoef());
	result.setGrad(this.grad.intValue());
return result;
}

//Metoda pentru scaderea a doua monoame
public Monom sub(Monom m)
{Monom result=new Monom();
 result.setGrad(this.grad.intValue());
 result.setCoef(this.coef.intValue()-m.getCoef());
 return result;
}

//Metoda pentru inmultirea a doua monoame
public Monom mul(Monom m)
{
 return new Monom(this.grad.intValue()+m.getGrad(),this.coef.intValue()*m.getCoef());
}

//Metoda pentru derivarea unui monom
public Monom derivationMonom()
{Monom result=new Monom();
 	result.setCoef(this.coef.intValue()*this.grad.intValue());
 	result.setGrad(this.grad.intValue()-1);
 return result;
}

//Metoda pentru integrarea unui monom
public Monom integrationMonom()
{Monom result =new Monom();
 int gradC=this.grad.intValue()+1;
 	result.setGrad(gradC);
 	result.setCoefDouble((double)this.coef.intValue()/gradC);
 return result;
}

//Change sign
public Monom changeSign()
{return new Monom(this.grad.intValue(),this.coef.intValue()*(-1));
}

//Metoda de convertire la String
public String monomToString()
{	if(grad.intValue()==0)
		{
		if(coef.intValue()>0)return "+"+coef.intValue();
		return coef.toString()+"";
		}
	if(coef.intValue()>0)return "+"+coef.toString()+"X^"+grad.intValue();
	return coef.toString()+"X^"+grad.intValue();
}
public String monomToStringDouble()
{
	if(grad.doubleValue()==0)
		{	
		if(coef.doubleValue()>0)return "+"+coef.doubleValue()+"X^"+grad.intValue();
		return coef.doubleValue()+"X^"+grad.intValue();
		}
	 
	if(coef.doubleValue()>0)return "+"+coef.doubleValue()+"X^"+grad.intValue();
	else return coef.doubleValue()+"X^"+grad.intValue();
}
public int compareTo(Monom m)
{int gradM=m.getGrad();
	if(this.grad.intValue()<gradM)return 1;
	else
		{if(this.grad.intValue()>gradM)return -1;
		else return 0;
		}
}
}
