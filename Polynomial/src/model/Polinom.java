package model;
import java.util.*;
public class Polinom {
private List<Monom>  listaMonom=new ArrayList<Monom>();

//Constructori
public Polinom()
{}

//Adauga un nou monom
public void addMonom(int coef,int grad)
{Monom m=new Monom(grad,coef);
	listaMonom.add(m);
}
public void addFormaMonom(Monom m)
{this.listaMonom.add(m);
}

//Gettere si Settere
public void setListaMonom(List<Monom> listaMonoame)
{this.clearList();
 this.listaMonom=listaMonoame;
}
public List<Monom> getListaMonom() {
	return listaMonom;
}

//Adunarea a doua polinoame
public Polinom addPolinom(Polinom m)
{Polinom resultAdd=new Polinom();
 int nrM=m.getListaMonom().size();
 int a[]=new int[nrM];
 for(Monom monom:this.listaMonom)
 	{int grad=monom.getGrad();
	 int i=0; 
	 boolean check=true;
 		for(Monom monom1:m.getListaMonom())
	 		 if(grad==monom1.getGrad())
	 		 {resultAdd.addFormaMonom(monom.add(monom1));
	 		  a[i]=1;
	 		  check=false;
	 		  i++;
	 		 }
	 		 else i++;
 	 if(check)resultAdd.addFormaMonom(monom);
 	}
 int i=0;
 for(Monom monom1:m.getListaMonom())
 {if(a[i]==0)resultAdd.addFormaMonom(monom1);
	i++;
 }
 return resultAdd;
}

//Scaderea a doua polinoame
public Polinom subPolinom(Polinom m)
{Polinom result=new Polinom();
 int []ap=new int[m.getListaMonom().size()];
 	for(Monom monom:this.listaMonom)
 		{boolean check=true;
 		int i=0;
 		int gradCheck=monom.getGrad();
 			for(Monom monom1:m.getListaMonom())
 				if(gradCheck==monom1.getGrad()) 
 				{check=false;
 				 Monom resultMonom=monom.sub(monom1);
				 if(resultMonom.getCoef()>0)result.addFormaMonom(resultMonom);
				 ap[i]=1;
				 i++;
				}
 				else i++;
			if(check)result.addFormaMonom(monom);
 		}
int i=0;
	for(Monom monom:m.getListaMonom())
		if(ap[i]==0) 
			{result.addFormaMonom(monom.changeSign());
			i++;
			}
		else i++;
return result;
}

//Inmultirea a doua polinoamelor
public Polinom mulPolinom(Polinom m)
{Polinom result=new Polinom();
for(Monom monom:this.listaMonom)
		for(Monom monom1:m.getListaMonom())
			{	boolean check=true;
				Monom resultMonom=monom.mul(monom1);
				int gradResult=resultMonom.getGrad();
					for(Monom checker:result.getListaMonom())
							if(checker.getGrad()==gradResult)
								{result.addFormaMonom(checker.add(resultMonom));
								 checker.setCoef(0);
								 checker.setGrad(0);
								 check=false;
								break;
								}
				if(check)result.addFormaMonom(resultMonom);
			}
result.sort();
return result;
}

//Impartirea a doua polinoame
public String divisionPolinom(Polinom m)
{Polinom quotient=new Polinom();
 Monom firstElement=listaMonom.get(0);
 this.sort();
 int grad=firstElement.getGrad();
 int gradDivisor=m.getListaMonom().get(0).getGrad();
 double coefDivisor=m.getListaMonom().get(0).getCoef();
 m.sort();
 	if(grad<gradDivisor)return "Restul este:"+this.polinomString();
 	 else {
 		while(grad>=gradDivisor)
 				{
 		 		//Determinam gradul si coeficientul catului ,cream un nou monom si-l adaugam in polinomul pentru cat
 				int gradCat=grad-gradDivisor;
 				double coefCat=coefDivisor*firstElement.getCoef()/coefDivisor;
 				Monom quotienMonom=new Monom(gradCat,coefCat);
 				quotient.addFormaMonom(quotienMonom);
 				
 				//Efectuam operatiile de scadere,inmultire
 				Polinom reminder=new Polinom();
 				reminder=m.mulPolinomMonom(quotienMonom);
 				
 				Polinom acc=this.subPolinom(reminder);//Retinem polinomul care se obtine din scaderea listei si polinomul obtinut prin inmultire
 				
 				this.copyList(acc);//Copiem continutul in lista
 				
 				//Verificam daca lista mai contine elemente
 				if(!this.listaMonom.isEmpty())firstElement=listaMonom.get(0);
 				else break;
 				grad=firstElement.getGrad();
 				}
 			return"Catul :"+quotient.polinomString()+" Restul : "+this.polinomString();
 	 	  }
}

//Inmultirea cu un monom
private Polinom mulPolinomMonom(Monom m)
{	Polinom resultPolinom=new Polinom();
	for(Monom monom:this.listaMonom)
	{
		Monom result=monom.mul(m);
		resultPolinom.addFormaMonom(result);
	}
	return resultPolinom;
}

//Copiere lista
private void copyList(Polinom m)
{this.clearList();
	for(Monom monom:m.getListaMonom())
		this.listaMonom.add(monom);
	
}

//Sortare lista
private void sort()
{Collections.sort(this.listaMonom);
}

//Afisarea polinomului
public String polinomString()
{String s="";
if(this.listaMonom.isEmpty())return "0";
for(Monom monom:listaMonom)
 {if(monom.getCoef()!=0)s=s+monom.monomToString();}
return s;	 
}
public String polinomDoubleString()
{
	String s="";
	if(this.listaMonom.isEmpty())return "0";
	for(Monom monom:this.listaMonom)
		if(monom.getCoefDouble()!=0)s=s+monom.monomToStringDouble();
	return s;
}

//Stergerea listei
public void clearList()
{this.listaMonom.clear();}

//Determinarea polinomului derivat
public Polinom polinomDerivation()
{Polinom result=new Polinom();
for(Monom monom:this.listaMonom)
	if(monom.getGrad()!=0) 
		result.addFormaMonom(monom.derivationMonom());
return result;
}

//Determinare polinomului integrat
public Polinom polinomIntegration()
{Polinom result=new Polinom();
 for(Monom monom:this.listaMonom)
	result.addFormaMonom(monom.integrationMonom());
 return result;	
}

//Crearea listei de polinoame
public void convertToPolinom(String polinom1) throws WrongPolinom
	{String stringSemn="+-";
String numere="0123456789";
boolean determinareCoefPutere=true;
boolean semn=true;
int putere=0;
int coef=0;
//int check=0;
for(int i=0;i<polinom1.length();i++)
	{
if(stringSemn.indexOf(polinom1.charAt(i))>-1)
 	{if(polinom1.charAt(i)=='+')semn=true;
 	else semn=false;
 	//System.out.println("Semn"+polinom1.charAt(i));
 	} 
 else
 	{if(polinom1.charAt(i)=='X'||polinom1.charAt(i)=='x')
 	{	
 		if(determinareCoefPutere) {coef=1*(semn?1:-1);
 									determinareCoefPutere=false;
 								  }
 		if(polinom1.charAt(i+1)=='^')i=i+1;
 		//System.out.println(polinom1.charAt(i-1));
 		//System.out.println(polinom1.charAt(i));
 		//System.out.println(i);
 	}
 	else
 	{
 		if(numere.indexOf(polinom1.charAt(i))>-1)
 		{//Trebuie sa verificam daca ii numar 
 			int poz=-1;
 			for(int j=i;j<polinom1.length()&&poz==-1;j++)
 				{if(numere.indexOf(polinom1.charAt(j))<0)poz=j;
 				//System.out.println("In for:"+polinom1.charAt(j)+"   "+poz);&&i!=(polinom1.length()-1)
 				}
 			if(poz==-1)poz=polinom1.length();
 			if(determinareCoefPutere) {String sub=polinom1.substring(i, poz);
 										try{coef=Integer.parseInt(sub)*(semn?1:-1);}
 										catch(NumberFormatException e)
 										{
 											System.out.println("Exista o problema aici!");
 										}
 										determinareCoefPutere=false;
 										i=poz-1;
 									   }
 			else
 			{String sub=polinom1.substring(i, poz);
				try{putere=Integer.parseInt(sub);}
				catch(NumberFormatException e)
				{
					System.out.println("Exista o problema la determinarea puterii!");
				}
				//System.out.println("Puterea care se va transmite este:"+putere);
 				determinareCoefPutere=true;
 				System.out.println("Transmitem coef si puterea "+coef+" "+putere);
 				
 				//Verificam daca mai exista un monom cu acelasi grad
 				boolean checker=true;
 				for(Monom monom:this.listaMonom)
 					if(monom.getGrad()==putere) 
 									{
 									 checker=false;
 									 monom.setCoef(monom.getCoef()+coef);
 									}
 				if(checker)this.listaMonom.add(new Monom(putere,coef));
 				i=poz-1;
 				coef=0;
 				putere=0;
 			 }
 			
 		}
 		else throw new WrongPolinom("Polinomul introdus nu este corect!");
 	} 
 	}
	}
	if(!determinareCoefPutere&&coef!=0)this.listaMonom.add(new Monom(0,coef));
	
	//Sortam polinomul
	this.sort();
    }

}
