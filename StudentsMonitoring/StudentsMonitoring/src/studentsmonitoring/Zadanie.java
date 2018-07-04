package studentsmonitoring;

/**
 * Klasa opisuje ocenê typu Zadanie
 */
public class Zadanie extends Ocena {
	private static final long serialVersionUID = 1L;
	private static double waga = 2.0;
	private static int liczbaObiektow;


	public Zadanie(Double nota, Przedmiot przedmiot) {
		super(nota, przedmiot);
		if (Aplikacja.CZY_DRUKOWAC_WYWOLANIA_KONSTRUKTOROW)
			System.out.println("Wywo³ano konstruktor klasy " + Zadanie.class.getName());
		liczbaObiektow++;
	}

	public Zadanie(Zadanie zadanie) {
		super(zadanie.getNota(), zadanie.getPrzedmiot());
		liczbaObiektow++;
	}

	@Override
	public String getNazwaOceny() {
		return "zadanie";
	}

	@Override
	public double getWaga() {
		return waga;
	}

	public static void setWaga(double waga) {
		Zadanie.waga = waga;
	}
	
	public static int getLiczbaObiektow() {
		return liczbaObiektow;
	}

	@Override
	public String toString() {
		return "[" + getNota() + "]<" + getNazwaOceny() + ">";
	}
}