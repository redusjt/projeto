package model;

public class LoginModel
{
   private int pos;
   
   public LoginModel()
   {
      setPos(0);
   }
   
   public boolean binaria(long vetorUsuariosDecifrados[], long usuarioAProcurar) {
		int ini = 0, meio, fim = vetorUsuariosDecifrados.length -1;
		do {
			meio = (ini + fim) / 2;
			if ( vetorUsuariosDecifrados[meio] == usuarioAProcurar){
				setPos(meio);  
	            return true;
			}
			else if (vetorUsuariosDecifrados[meio] > usuarioAProcurar){
				fim = meio - 1;
			}
			else if (vetorUsuariosDecifrados[meio] < usuarioAProcurar){
				ini = meio + 1;
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