import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private Przychody przychody;

    public static void main (String[] args) throws SQLException {
        Main main = new Main();
        main.run();
    }

    private void run () throws SQLException{

        PrzychodyDao dao=new PrzychodyDao();
        Scanner scan=new Scanner(System.in);

        boolean repeat=true;
        System.out.println("==========================================================================");
        System.out.println("Przoszę wybrać odpowiednią opicję pisząc numer i wciskając enter");
        System.out.println("Opcje programu: ");

        do {
            System.out.println();
            System.out.println("==============================================================================================================================================================================");
            System.out.println();

            for (Opcje elem: Opcje.values()) {
                System.out.print("|  "+elem.getNr()+" -> "+elem.getName()+" |");
            }
            System.out.println();
            System.out.println("==============================================================================================================================================================================");
            System.out.println();

            int nrWybor=scan.nextInt();

            switch (nrWybor) {
                case 1: { przychody = wczytaj("przychody");
                    dao.save(przychody);
                    break;}
                case 2: { przychody = wczytaj("wydatki");
                    dao.save(przychody);
                    break;}
                case 3: { dao.show("przychody"); System.out.println();System.out.println();
                    break; }
                case 4: { dao.show("wydatki"); System.out.println();System.out.println();
                    break; }
                case 5: {repeat=false; break;}
            }
        } while (repeat);


    }

    public Przychody wczytaj (String rodzaj) throws SQLException{
      Scanner scan=new Scanner(System.in);
      System.out.println("Wpisz pozycję do bazy przychodów/wydatków");
      System.out.println("Opis transakcji:");
      String opis=scan.nextLine();
      System.out.println("Kwota:");
      long kwota=scan.nextLong();
      LocalDate data=LocalDate.now();
      Przychody przychody=new Przychody();
      przychody.setType(rodzaj);
      przychody.setDescription(opis);
      przychody.setAmount(kwota);
      przychody.setDate(data);

      return przychody;
    }
}
