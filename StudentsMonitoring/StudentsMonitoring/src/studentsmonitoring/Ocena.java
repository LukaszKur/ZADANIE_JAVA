package studentsmonitoring;

import java.io.Serializable;

/**
 * Klasa abstrakcyjna opisuj¹ca ocenê
 */
public abstract class Ocena implements Serializable {
	private static final long serialVersionUID = 1L;
	private double nota;
	private Przedmiot przedmiot;

	public Ocena(double nota, Przedmiot przedmiot) {
		this.nota = nota;
		this.przedmiot = przedmiot;
		if (Aplikacja.CZY_DRUKOWAC_WYWOLANIA_KONSTRUKTOROW)
			System.out.println("Wywo³ano konstruktor klasy " + Ocena.class.getName());
	}

	public Ocena(Ocena ocena) {
		this.nota = ocena.nota;
		this.przedmiot = ocena.przedmiot;
	}

	/**
	 * Metoda podaj¹ca nazwê oceny
	 * 
	 * @return nazwa oceny
	 */
	public abstract String getNazwaOceny();

	/**
	 * Metoda podaj¹ca wagê oceny
	 * 
	 * @return waga oceny
	 */
	public abstract double getWaga();

	/**
	 * Typ wyliczeniowy przechowuj¹cy przedmioty szkolne
	 */
	public static enum Przedmiot {
		POL("jezyk polski"),
		ANG("jêzyk angielski"),
		NIE("jezyk niemiecki"),
		MAT("matematyka");
		

		private final String nazwa;

		private Przedmiot(String nazwa) {
			this.nazwa = nazwa;
		}

		public String getNazwa() {
			return nazwa;
		}
	}

	public double getNota() {
		return nota;
	}

	public Przedmiot getPrzedmiot() {
		return przedmiot;
	}
}