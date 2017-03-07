package Controller;

public class Login
{
   private int pos;
   
   public Login()
   {
      setPos(0);
   }
   
   public boolean binaria(long v[], long find) {
		int ini = 0, meio, fim = v.length -1;
		do {
			meio = (ini + fim) / 2;
			if      (v[meio] > find) fim = meio - 1;
			else if (v[meio] < find) ini = meio + 1;
			else
         {
            setPos(meio);  
            return true;             
         }   
		} while (ini <= fim);
		return false;
	}
   
   public void setPos(int pos)
   {
      this.pos = pos;
   }
   
   public int getPos()
   {
      return pos;
   }
}