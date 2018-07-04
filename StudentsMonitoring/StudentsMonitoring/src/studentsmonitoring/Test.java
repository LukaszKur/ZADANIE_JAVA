package studentsmonitoring;

/**
 * Klasa opisuje ocenê typu Test
 */
public class Test extends Ocena {
	private static final long serialVersionUID = 1L;
	private static int liczbaObiektow;

	public Test(Double nota, Przedmiot przedmiot) {
		super(nota, przedmiot);
		if (Aplikacja.CZY_DRUKOWAC_WYWOLANIA_KONSTRUKTOROW)
			System.out.println("Wywo³ano konstruktor klasy " + Test.class.getName());
		liczbaObiektow++;
	}

	public Test(Test test) {
		super(test.getNota(), test.getPrzedmiot());
		liczbaObiektow++;
	}

	@Override
	public String getNazwaOceny() {
		return "test";
	}

	@Override
	public double getWaga() {
		return 0;
	}

	public static int getLiczbaObiektow() {
		return liczbaObiektow;
	}
}