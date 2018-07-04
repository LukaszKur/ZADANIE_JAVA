package studentsmonitoring;

/**
 * Klasa opisuje ocenê typu TEST
 */
public class Klasowka extends Test {
	private static final long serialVersionUID = 1L;
	private static double waga = 5.0;
	private static int liczbaObiektow;

	public Klasowka(Double nota, Przedmiot przedmiot) {
		super(nota, przedmiot);
		if (Aplikacja.CZY_DRUKOWAC_WYWOLANIA_KONSTRUKTOROW)
			System.out.println("Wywo³ano konstruktor klasy " + Klasowka.class.getName());
		liczbaObiektow++;
	}

	public Klasowka(Klasowka klasowka) {
		super(klasowka.getNota(), klasowka.getPrzedmiot());
		liczbaObiektow++;
	}

	@Override
	public String getNazwaOceny() {
		return "test";
	}

	@Override
	public double getWaga() {
		return waga;
	}

	public static void setWaga(double waga) {
		Klasowka.waga = waga;
	}

	public static int getLiczbaObiektow() {
		return liczbaObiektow;
	}


	@Override
	public String toString() {
		return "[" + getNota() + "]<" + getNazwaOceny() + ">";
	}
}